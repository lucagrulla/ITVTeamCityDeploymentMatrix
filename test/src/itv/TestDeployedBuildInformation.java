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

import static org.mockito.Matchers.anyString;
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
        Assert.assertNotNull(new DeployedBuildInformation(server).GetDeploymentBuildConfigurations());
    }

    @Test
    public void ShouldGetAllDeploymentBuildConfigurations() {
        List<SProject> projects = new ArrayList<SProject>();
        projects.add(CreateProjectWithArtifactDependency());
        stub(projectManager.getProjects()).toReturn(projects);

        DeployedBuildInformation buildInformation = new DeployedBuildInformation(server);
        List<SBuildType> deploymentBuildConfigurations = buildInformation.GetDeploymentBuildConfigurations();

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
        List<SBuildType> deploymentBuildConfigurations = buildInformation.GetDeploymentBuildConfigurations();

        Assert.assertEquals(1, deploymentBuildConfigurations.size());
        Assert.assertEquals(deploymentBuildConfigurations.get(0), projects.get(0).getBuildTypes().get(0));
    }

    @Test
    public void ShouldGetAllBuildConfigurationsWhichDoNotHaveConfigurationsDependant() {
        List<SProject> projects = new ArrayList<SProject>();
        projects.add(CreateProjectWithArtifactDependency());

        SBuildType buildConfiguration = projects.get(0).getBuildTypes().get(0);

        stub(projectManager.getProjects()).toReturn(projects);
        stub(projectManager.findBuildTypeById(anyString())).toReturn(buildConfiguration);

        DeployedBuildInformation buildInformation = new DeployedBuildInformation(server);
        List<SBuildType> buildConfigurations = buildInformation.GetBuildConfigurations();

        Assert.assertEquals(1, buildConfigurations.size());
        Assert.assertEquals(buildConfigurations.get(0), buildConfiguration);
    }

//    @Test
//    public void ShouldGetTheDeploymentInformation() {
//        List<SProject> projects = new ArrayList<SProject>();
//        projects.add(CreateProjectWithArtifactDependency());
//
//        SBuildType buildConfiguration = projects.get(0).getBuildTypes().get(0);
//
//        stub(projectManager.getProjects()).toReturn(projects);
//        stub(projectManager.findBuildTypeById(anyString())).toReturn(buildConfiguration);
//
//        List<String> knownEnvironments = new ArrayList<String>();
//        knownEnvironments.add("rc2");
//        knownEnvironments.add("rc1");
//
//        SFinishedBuild finishedBuild = mock(SFinishedBuild.class);
//        stub(finishedBuild.getTags()).toReturn(knownEnvironments);
//        stub(finishedBuild.getBuildNumber()).toReturn("54");
//        stub(finishedBuild.getFullName()).toReturn("DeployedBuild");
//
//        Calendar calendar = Calendar.getInstance();
//        calendar.set(2011, 2, 1);
//
//        stub(finishedBuild.getFinishDate()).toReturn(calendar.getTime());
//
//        SFinishedBuild oldFinishedBuild = mock(SFinishedBuild.class);
//        stub(oldFinishedBuild.getTags()).toReturn(knownEnvironments);
//        stub(oldFinishedBuild.getBuildNumber()).toReturn("33");
//        stub(oldFinishedBuild.getFullName()).toReturn("DeployedBuild");
//
//        calendar.set(2011, 1, 1);
//
//        stub(oldFinishedBuild.getFinishDate()).toReturn(calendar.getTime());
//
//        SFinishedBuild anotherFinishedBuild = mock(SFinishedBuild.class);
//        stub(anotherFinishedBuild.getTags()).toReturn(new ArrayList<String>());
//        stub(anotherFinishedBuild.getBuildNumber()).toReturn("1");
//        stub(anotherFinishedBuild.getFullName()).toReturn("DeployedBuild");
//
//        List<SFinishedBuild> finishedBuilds = new ArrayList<SFinishedBuild>();
//        finishedBuilds.add(oldFinishedBuild);
//        finishedBuilds.add(finishedBuild);
//        finishedBuilds.add(anotherFinishedBuild);
//
//        stub(buildConfiguration.getHistory()).toReturn(finishedBuilds);
//        DeployedBuildInformation buildInformation = new DeployedBuildInformation(server);
//        List<List<String>> deploymentMatrix = buildInformation.GetDeploymentInformation();
//
//        Assert.assertEquals(2, deploymentMatrix.size());
//
//        List<String> deploymentInfo = deploymentMatrix.get(1);
//        List<String> expectedRow = new ArrayList<String>();
//        expectedRow.add("DeployedBuild");
//        expectedRow.add("54");
//        expectedRow.add("54");
//        expectedRow.add("");
//
//        Assert.assertEquals(expectedRow, deploymentInfo);
//    }

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
}
