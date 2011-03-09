package itv;

import jetbrains.buildServer.serverSide.SFinishedBuild;
import java.util.List;

public class SuccessfulBuild {

    private SFinishedBuild finishedBuild;

    public SuccessfulBuild(SFinishedBuild finishedBuild) {
        this.finishedBuild = finishedBuild;
    }

    public boolean hasBeenDeployedTo(String env) {
        final List<String> tags = finishedBuild.getTags();
        return tags.contains(env);
    }

    public String getBuildNumber() {
        return finishedBuild.getBuildNumber();
    }

    public String getFinishDate() {
        return finishedBuild.getFinishDate().toString();
    }
}
