package itv;

import jetbrains.buildServer.serverSide.SBuildServer;
import jetbrains.buildServer.serverSide.SBuildType;
import jetbrains.buildServer.web.openapi.WebControllerManager;
import junit.framework.Assert;
import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.stub;

public class TestDeployedBuildsController {
    @Test
    public void ShouldGetModelAndViewWithDeployedBuildConfigurationsFromDeployedBuildInformation() throws Exception {
        DeployedBuildInformation buildInformation = mock(DeployedBuildInformation.class);
        List<SBuildType> buildTypes = new ArrayList<SBuildType>();
        stub(buildInformation.GetDeploymentBuildConfiguration()).toReturn(buildTypes);
        DeployedBuildsController controller = new DeployedBuildsController(mock(SBuildServer.class), mock(WebControllerManager.class), buildInformation);
        ModelAndView modelAndView = controller.doHandle(mock(HttpServletRequest.class), mock(HttpServletResponse.class));
        Assert.assertEquals(buildTypes, modelAndView.getModelMap().get("buildTypes"));
    }
}
