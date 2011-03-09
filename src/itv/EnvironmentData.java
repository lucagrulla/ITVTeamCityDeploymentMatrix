package itv;

public class EnvironmentData {

    private String tag;
    private SuccessfulBuild build;

    public EnvironmentData(String tag, SuccessfulBuild build) {

        this.tag = tag;
        this.build = build;
    }

    public String getTag() {
        return tag;
    }

    public String getBuildNumber() {
        return build.getBuildNumber();
    }

    public String getFinishDate() {
        return build.getFinishDate();
    }

    public String getBuildName() {
        return build.getBuildName();
    }
}
