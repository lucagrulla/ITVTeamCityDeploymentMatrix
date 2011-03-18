package itv;

import jetbrains.buildServer.controllers.BaseController;
import jetbrains.buildServer.serverSide.SBuildServer;
import jetbrains.buildServer.web.openapi.WebControllerManager;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

public class DeployedBuildsController extends BaseController {

    public DeployedBuildsController(SBuildServer server, WebControllerManager webManager) {
        super(server);
        webManager.registerController("/environments.html", this);
    }

    @Override
    protected ModelAndView doHandle(HttpServletRequest request, HttpServletResponse httpServletResponse) throws Exception {
        Map<String, Object> model = new HashMap<String, Object>();
        model.put("data", GetData());

        return new ModelAndView("/plugins/matrix/deployedBuildsView.jsp", model);
    }

    public Map<String, EnvironmentData> GetData() {
        return new DeployedBuildInformation(myServer).GetDeploymentInformation();
    }
}