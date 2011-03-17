package itv;

import jetbrains.buildServer.Build;
import jetbrains.buildServer.serverSide.SBuildServer;
import jetbrains.buildServer.serverSide.SBuildType;
import jetbrains.buildServer.vcs.VcsModification;

import java.util.HashSet;
import java.util.Set;

public class ChangeSetService {

    private SBuildServer myServer;

    public ChangeSetService(SBuildServer myServer) {
        this.myServer = myServer;
    }

    public Set getChangeSetFor(String buildId, String buildNumber) {
        final SBuildType buildType = myServer.getProjectManager().findBuildTypeById(buildId);
        final Build build = buildType.getBuildByBuildNumber(buildNumber);
        final Set<String> changes = new HashSet<String>();

        for (VcsModification modification : build.getContainingChanges()) {
            changes.add(modification.getDescription());
        }
        return changes;
    }
}

