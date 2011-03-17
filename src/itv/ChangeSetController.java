package itv;

import jetbrains.buildServer.controllers.BaseController;
import jetbrains.buildServer.serverSide.SBuildServer;
import jetbrains.buildServer.web.openapi.WebControllerManager;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Set;

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

        final HashMap<String, Set> model = new HashMap<String, Set>();
        model.put("data", new ChangeSetService(myServer).getChangeSetFor(buildId, buildNumber));
        return new ModelAndView("plugins/matrix/changeset.jsp", model);
    }
}
