package itv;

import jetbrains.buildServer.serverSide.*;
import jetbrains.buildServer.serverSide.artifacts.SArtifactDependency;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DeployedBuildInformation {
    private SBuildServer server;
    public final List<String> knownEnvironments;

    public DeployedBuildInformation(SBuildServer server) {
        this.server = server;
        knownEnvironments = new ArrayList<String>();
        knownEnvironments.add("rc1");
        knownEnvironments.add("rc2");
        knownEnvironments.add("rc3");
    }

    //TODO: Refactor 
    private List<SBuildType> GetDeploymentBuildConfigurations() {
        List<SBuildType> deploymentBuildConfigurations = new ArrayList<SBuildType>();

        ProjectManager projectManager = server.getProjectManager();

        List<SProject> projects = projectManager.getProjects();

        for (SProject project : projects) {
            for (SBuildType buildConfiguration : project.getBuildTypes()) {
                if (buildConfiguration.getArtifactDependencies().size() > 0) {
                    deploymentBuildConfigurations.add(buildConfiguration);
                }
            }
        }

        return deploymentBuildConfigurations;
    }
      //TODO: Refactor
    private List<SBuildType> GetBuildConfigurations() {
        List<SBuildType> buildConfigurations = new ArrayList<SBuildType>();

        for (SBuildType deploymentBuildType : GetDeploymentBuildConfigurations()) {
            List<SArtifactDependency> artifactDependencies = deploymentBuildType.getArtifactDependencies();

            for (SArtifactDependency artifactDependency : artifactDependencies) {
                SBuildType buildConfiguration = server.getProjectManager().findBuildTypeById(artifactDependency.getSourceBuildTypeId());

                if (!buildConfigurations.contains(buildConfiguration)) {
                    buildConfigurations.add(buildConfiguration);
                }
            }
        }

        return buildConfigurations;
    }

    public Map<String, EnvironmentData> GetDeploymentInformation() {

        Map environmentsBlob = new HashMap<String, EnvironmentData>();
        for (SBuildType buildConfiguration : GetBuildConfigurations()) {

            for (SFinishedBuild finishedBuild : buildConfiguration.getHistory()) {

                final SuccessfulBuild build = new SuccessfulBuild(finishedBuild);

                for (String env: knownEnvironments) {
                    if (build.hasBeenDeployedTo(env)) {
                        environmentsBlob.put(env, new EnvironmentData(env, build));
                    }
                }
            }
        }

         return environmentsBlob;
    }

}
