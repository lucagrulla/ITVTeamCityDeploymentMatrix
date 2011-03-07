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

    @Override
    protected ModelAndView doHandle(HttpServletRequest request, HttpServletResponse httpServletResponse) throws Exception
    {
        return null;
    }
}