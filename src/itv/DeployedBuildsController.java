package itv;

import jetbrains.buildServer.controllers.BaseController;
import jetbrains.buildServer.serverSide.SBuildServer;
import jetbrains.buildServer.web.openapi.WebControllerManager;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;


public class DeployedBuildsController extends BaseController {
    private final WebControllerManager myWebManager;

    public DeployedBuildsController(SBuildServer server, WebControllerManager webManager) {
        super(server);
        myWebManager = webManager;

    }

    public void register() {
        myWebManager.registerController("/deployedBuildProjects.html", this);
    }

    @Override
    protected ModelAndView doHandle(HttpServletRequest request, HttpServletResponse httpServletResponse) throws Exception {
        HashMap<String, Object> model = new HashMap<String, Object>();
        model.put("buildTypes", new DeployedBuildInformation(myServer).GetDeploymentBuildConfiguration());
        return new ModelAndView("/plugins/matrix/deployedBuildsView.jsp", model);
    }
}