package itv;

import jetbrains.buildServer.serverSide.ProjectManager;
import jetbrains.buildServer.serverSide.SBuildServer;
import jetbrains.buildServer.serverSide.SBuildType;
import jetbrains.buildServer.serverSide.SProject;
import jetbrains.buildServer.serverSide.artifacts.SArtifactDependency;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.stub;

public class TestDeployedBuildInformation {
    private SBuildServer server;
    private ProjectManager projectManager;

    @Before
    public void Setup() {
        server = mock(SBuildServer.class);
        projectManager = mock(ProjectManager.class);
        stub(server.getProjectManager()).toReturn(projectManager);
    }

    @Test
    public void ShouldReturnAnEmptyListOfDeploymentBuildConfigurations() {
        List<SProject> projects = new ArrayList<SProject>();
        stub(projectManager.getProjects()).toReturn(projects);
        Assert.assertNotNull(new DeployedBuildInformation(server).GetDeploymentBuildConfiguration());
    }

    private SBuildType CreateBuildTypeWithArtifactDependency() {
        SBuildType buildType = mock(SBuildType.class);
        List<SArtifactDependency> artifactDependencies = new ArrayList<SArtifactDependency>();
        SArtifactDependency artifactDependency = mock(SArtifactDependency.class);
        artifactDependencies.add(artifactDependency);
        stub(buildType.getArtifactDependencies()).toReturn(artifactDependencies);
        return buildType;
    }

    private SBuildType CreateBuildTypeWithoutArtifactDependency() {
        SBuildType buildType = mock(SBuildType.class);
        List<SArtifactDependency> artifactDependencies = new ArrayList<SArtifactDependency>();
        stub(buildType.getArtifactDependencies()).toReturn(artifactDependencies);
        return buildType;
    }

    private SProject CreateProjectWithArtifactDependency() {
        SProject project = mock(SProject.class);
        List<SBuildType> buildTypes = new ArrayList<SBuildType>();
        buildTypes.add(CreateBuildTypeWithArtifactDependency());
        stub(project.getBuildTypes()).toReturn(buildTypes);
        return project;
    }

    private SProject CreateProjectWithoutArtifactDependency() {
        SProject project = mock(SProject.class);
        List<SBuildType> buildTypes = new ArrayList<SBuildType>();
        buildTypes.add(CreateBuildTypeWithoutArtifactDependency());
        stub(project.getBuildTypes()).toReturn(buildTypes);
        return project;
    }

    @Test
    public void ShouldGetAllDeploymentBuildConfigurations() {
        List<SProject> projects = new ArrayList<SProject>();
        projects.add(CreateProjectWithArtifactDependency());
        stub(projectManager.getProjects()).toReturn(projects);

        DeployedBuildInformation buildInformation = new DeployedBuildInformation(server);
        List<SBuildType> deploymentBuildConfigurations = buildInformation.GetDeploymentBuildConfiguration();

        Assert.assertEquals(1, deploymentBuildConfigurations.size());
        Assert.assertEquals(deploymentBuildConfigurations.get(0), projects.get(0).getBuildTypes().get(0));
    }

    @Test
    public void ShouldGetAllDeploymentBuildConfigurationsThatHaveArtifactDependencies() {
        List<SProject> projects = new ArrayList<SProject>();
        projects.add(CreateProjectWithArtifactDependency());
        projects.add(CreateProjectWithoutArtifactDependency());
        stub(projectManager.getProjects()).toReturn(projects);

        DeployedBuildInformation buildInformation = new DeployedBuildInformation(server);
        List<SBuildType> deploymentBuildConfigurations = buildInformation.GetDeploymentBuildConfiguration();

        Assert.assertEquals(1, deploymentBuildConfigurations.size());
        Assert.assertEquals(deploymentBuildConfigurations.get(0), projects.get(0).getBuildTypes().get(0));
    }

}
