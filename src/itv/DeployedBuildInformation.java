package itv;

import jetbrains.buildServer.serverSide.SBuildServer;
import jetbrains.buildServer.serverSide.SBuildType;
import jetbrains.buildServer.serverSide.SProject;

import java.util.ArrayList;
import java.util.List;

public class DeployedBuildInformation {
    private SBuildServer server;

    public DeployedBuildInformation(SBuildServer server) {
        this.server = server;
    }

    public List<SBuildType> GetDeploymentBuildConfiguration() {
        List<SBuildType> deploymentBuildConfigurations = new ArrayList<SBuildType>();

        for (SProject project : server.getProjectManager().getProjects()) {
            for (SBuildType buildConfiguration : project.getBuildTypes()) {
                if (buildConfiguration.getArtifactDependencies().size() > 0) {
                    deploymentBuildConfigurations.add(buildConfiguration);
                }
            }
        }

        return deploymentBuildConfigurations;
    }
}
