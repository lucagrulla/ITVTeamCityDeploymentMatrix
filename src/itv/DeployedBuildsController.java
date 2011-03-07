package itv;

import jetbrains.buildServer.artifacts.RevisionRules;
import jetbrains.buildServer.controllers.BaseController;
import jetbrains.buildServer.serverSide.SBuildServer;
import jetbrains.buildServer.serverSide.SBuildType;
import jetbrains.buildServer.serverSide.SFinishedBuild;
import jetbrains.buildServer.serverSide.SProject;
import jetbrains.buildServer.serverSide.artifacts.SArtifactDependency;
import jetbrains.buildServer.web.openapi.WebControllerManager;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;


public class DeployedBuildsController extends BaseController {

    private final WebControllerManager myWebManager;

    public DeployedBuildsController(SBuildServer server, WebControllerManager webManager)
    {
        super(server);
        myWebManager = webManager;
    }

    public void register()
    {
      myWebManager.registerController("/DeployedBuildProjects.html", this);
    }

    @Override
    protected ModelAndView doHandle(HttpServletRequest request, HttpServletResponse httpServletResponse) throws Exception
    {
        return null;
    }

    public List<SBuildType> GetDeploymentProjects()
    {
        List<SBuildType> deploymentProjects = new ArrayList<SBuildType>();

        for (SProject project : myServer.getProjectManager().getProjects())
        {
            for(SBuildType buildType : project.getBuildTypes())
            {
                if(buildType.getArtifactDependencies().size() > 0)
                {
                    deploymentProjects.add(buildType);
                }
            }
        }

        return deploymentProjects;
    }
}