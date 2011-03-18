package itv;

import jetbrains.buildServer.controllers.BaseController;
import jetbrains.buildServer.serverSide.SBuildServer;
import jetbrains.buildServer.web.openapi.WebControllerManager;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

public class ChangeSetController extends BaseController {

     public ChangeSetController(SBuildServer server, WebControllerManager webManager) {
        super(server);
        webManager.registerController("/changeset.html", this);
    }

    @Override
    protected ModelAndView doHandle(HttpServletRequest httpServletRequest,
                                    HttpServletResponse httpServletResponse) throws Exception {

        final String buildId = httpServletRequest.getParameter("buildId");
        final String buildNumber = httpServletRequest.getParameter("buildNumber");

        final HashMap<String, Object> model = new HashMap<String, Object>();
        model.put("data", new ChangeSetService(myServer).getChangeSetFor(buildId, buildNumber));
        model.put("buildNumber", buildNumber);
        return new ModelAndView("plugins/matrix/changeset.jsp", model);
    }
}
