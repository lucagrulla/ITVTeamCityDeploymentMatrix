package itv;

import jetbrains.buildServer.serverSide.*;
import jetbrains.buildServer.serverSide.artifacts.SArtifactDependency;

import java.util.ArrayList;
import java.util.List;

public class DeployedBuildInformation {
    private SBuildServer server;
    public final List<String> tags;

    public DeployedBuildInformation(SBuildServer server) {
        this.server = server;
        tags = new ArrayList<String>();
        tags.add("rc1");
        tags.add("rc2");
        tags.add("rc3");
    }

    public List<SBuildType> GetDeploymentBuildConfigurations() {
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

    public List<SBuildType> GetBuildConfigurations() {
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

    public List<List<String>> GetDeploymentInformation() {
        List<List<String>> deploymentMatrix = new ArrayList<List<String>>();

        AddDeploymentMatrixHeader(deploymentMatrix);

        List<SBuildType> buildConfigurations = GetBuildConfigurations();

        for (SBuildType buildConfiguration : buildConfigurations) {
            List<Long> timeStamps = new ArrayList<Long>();
            for (int i = 0; i < tags.size(); ++i) {
                timeStamps.add(Long.MIN_VALUE);
            }

            List<String> deploymentInfo = new ArrayList<String>();

            for (SFinishedBuild finishedBuild : buildConfiguration.getHistory()) {
                List<String> buildTags = finishedBuild.getTags();

                if (buildTags.size() > 0) {
                    if (!deploymentInfo.contains(finishedBuild.getFullName())) {
                        deploymentInfo.add(finishedBuild.getFullName());
                        for (int i = 0; i < tags.size(); ++i) {
                            deploymentInfo.add("");
                        }
                    }

                    for (int i = 0; i < tags.size(); ++i) {
                        if (buildTags.contains(tags.get(i))) {
                            if (timeStamps.get(i) < finishedBuild.getFinishDate().getTime()) {
                                timeStamps.set(i, finishedBuild.getFinishDate().getTime());
                                deploymentInfo.set(i + 1, finishedBuild.getBuildNumber());
                            }
                        }
                    }
                }
            }

            if (deploymentInfo.size() > 0) {
                deploymentMatrix.add(deploymentInfo);
            }
        }

        return deploymentMatrix;
    }

    private void AddDeploymentMatrixHeader(List<List<String>> deploymentMatrix) {
        List<String> matrixHeader = new ArrayList<String>();
        matrixHeader.add("Project \\ Environment");
        matrixHeader.addAll(tags);

        deploymentMatrix.add(matrixHeader);
    }
}
