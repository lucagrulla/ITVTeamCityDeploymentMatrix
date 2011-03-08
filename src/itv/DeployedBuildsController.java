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
    private DeployedBuildInformation myBuildInformation;

    public DeployedBuildsController(SBuildServer server, WebControllerManager webManager, DeployedBuildInformation buildInformation) {
        super(server);
        myWebManager = webManager;
        myBuildInformation = buildInformation;
    }

    public void register() {
        myWebManager.registerController("/DeployedBuildProjects.html", this);
    }

    @Override
    protected ModelAndView doHandle(HttpServletRequest request, HttpServletResponse httpServletResponse) throws Exception {
        HashMap<String, Object> model = new HashMap<String, Object>();
       model.put("buildTypes", myBuildInformation.GetDeploymentBuildConfiguration());
        return new ModelAndView("/plugins/matrix/deployedBuildsView.jsp", model);
    }
}