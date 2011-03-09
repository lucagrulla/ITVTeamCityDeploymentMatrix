package itv;

import jetbrains.buildServer.serverSide.ProjectManager;
import jetbrains.buildServer.serverSide.SBuildServer;
import jetbrains.buildServer.serverSide.SBuildType;
import jetbrains.buildServer.serverSide.SFinishedBuild;
import jetbrains.buildServer.serverSide.artifacts.SArtifactDependency;

import java.util.*;

public class DeployedBuildInformation {

    private final static String deploymentProject = "Deployments";
    private SBuildServer server;
    public final static List<String> knownEnvironments = new ArrayList<String>();
    static {
        knownEnvironments.add("rc1");
        knownEnvironments.add("rc2");
        knownEnvironments.add("rc3");
        knownEnvironments.add("rc4");
    }


    public DeployedBuildInformation(SBuildServer server) {
        this.server = server;
    }

    private List<SBuildType> GetDeploymentBuilds() {
        ProjectManager projectManager = server.getProjectManager();
        return projectManager.findProjectByName(deploymentProject).getBuildTypes();
    }

    private Set<SBuildType> GetBuildConfigurations() {
        Set<SBuildType> buildConfigurations = new HashSet<SBuildType>();

        for (SBuildType deploymentBuild : GetDeploymentBuilds()) {

            for (SArtifactDependency artifactDependency : deploymentBuild.getArtifactDependencies()) {
                final String buildTypeId = artifactDependency.getSourceBuildTypeId();
                SBuildType buildConfiguration = server.getProjectManager().findBuildTypeById(buildTypeId);
                 buildConfigurations.add(buildConfiguration);
            }
        }

        return buildConfigurations;
    }

    public Map<String, EnvironmentData> GetDeploymentInformation() {

        Map environmentsBlob = new HashMap<String, EnvironmentData>();
        for (SBuildType buildConfiguration : GetBuildConfigurations()) {

            for (SFinishedBuild finishedBuild : buildConfiguration.getHistory()) {

                final SuccessfulBuild build = new SuccessfulBuild(finishedBuild);

                for (String env : knownEnvironments) {
                    if (build.hasBeenDeployedTo(env)) {
                        environmentsBlob.put(env, new EnvironmentData(env, build));
                    }
                }
            }
        }

        return environmentsBlob;
    }

}
