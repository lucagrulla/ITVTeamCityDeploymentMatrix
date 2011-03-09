package itv;

import jetbrains.buildServer.controllers.BaseController;
import jetbrains.buildServer.serverSide.SBuildServer;
import jetbrains.buildServer.serverSide.SBuildType;
import jetbrains.buildServer.web.openapi.WebControllerManager;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DeployedBuildsController extends BaseController {

    public DeployedBuildsController(SBuildServer server, WebControllerManager webManager) {
        super(server);
        webManager.registerController("/deployedBuildProjects.html", this);
    }

    @Override
    protected ModelAndView doHandle(HttpServletRequest request, HttpServletResponse httpServletResponse) throws Exception {
        HashMap<String, Object> model = new HashMap<String, Object>();
        List<SBuildType> buildTypes = new DeployedBuildInformation(myServer).GetBuildConfigurations();

        List<List<String>> data = new ArrayList<List<String>>();
        model.put("data", GetData());

        return new ModelAndView("/plugins/matrix/deployedBuildsView.jsp", model);
    }

    public List<List<String>> GetData() {
        return new DeployedBuildInformation(myServer).GetDeploymentInformation();
    }
}