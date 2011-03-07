package itv;

import com.intellij.util.enumeration.ArrayListEnumeration;
import jetbrains.buildServer.serverSide.ProjectManager;
import jetbrains.buildServer.serverSide.SBuildServer;
import jetbrains.buildServer.serverSide.SBuildType;
import jetbrains.buildServer.serverSide.SProject;
import jetbrains.buildServer.serverSide.artifacts.SArtifactDependency;
import jetbrains.buildServer.web.openapi.WebControllerManager;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.stub;

public class TestDeployedBuildsController
{
    private SBuildServer server;
    private ProjectManager manager;
    private WebControllerManager webController;

    private DeployedBuildsController controller;

    @Before
    public void Setup()
    {
        manager = mock(ProjectManager.class);
        server = mock(SBuildServer.class);
        stub(server.getProjectManager()).toReturn(manager);

        webController = mock(WebControllerManager.class);
        controller = new DeployedBuildsController(server, webController);
    }

    @Test
    public void ShouldGetAllTheDeploymentProjects()
    {
//        List<SProject> projects =new List<SProject>
//                CreateDeploymentProjects("TestDeployment", "TestBuild");
//
//        stub(manager.getProjects()).toReturn(projects);
//
//        List<SBuildType> deploymentProjects = controller.GetDeploymentProjects();
//
//        Assert.assertEquals(1, deploymentProjects.size());
//        Assert.assertEquals(deploymentProjects.get(0).getFullName(), "TestDeployment");
    }

    private List<SProject> GetProjectWithDeploymentBuildType(String deploymentProjectName, String buildProjectName)
    {
//        SBuildType deploymentProject = mock(SBuildType.class);
//        stub(deploymentProject.getFullName()).toReturn(deploymentProjectName);
//
//        List<SArtifactDependency> artifactDependencies = new ArrayList<SArtifactDependency>();
//        SArtifactDependency artifactDependency = mock(SArtifactDependency.class);
//        artifactDependencies.add(artifactDependency);
//
//        stub(deploymentProject.getArtifactDependencies()).toReturn(artifactDependencies);
//
//
//
//
//
//        List<SArtifactDependency> dependencies = new ArrayList<SArtifactDependency>();
//        dependencies.add((SArtifactDependency) buildProject);
//
//        stub(deploymentProject.getArtifactDependencies()).toReturn(dependencies);
//
//        List<SBuildType> buildTypes = new ArrayList<SBuildType>();
//        buildTypes.add(deploymentProject);
//        buildTypes.add(buildProject);
//
//        SProject project = mock(SProject.class);
//        stub(project.getBuildTypes()).toReturn(buildTypes);
//
//        List<SProject> projects = new ArrayList<SProject>();
//        projects.add(project);
//
//        return projects;
        return null;
    }
}
