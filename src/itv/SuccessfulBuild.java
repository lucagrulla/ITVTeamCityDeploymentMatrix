package itv;

import jetbrains.buildServer.serverSide.SFinishedBuild;

import java.text.SimpleDateFormat;
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
        final SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm a dd/MM/yy");
        return dateFormat.format(finishedBuild.getFinishDate());
    }

    public String getBuildName() {
        return finishedBuild.getFullName();
    }

    public String getBuildId() {
        return finishedBuild.getBuildTypeId();
    }
}
