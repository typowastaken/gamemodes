package com.typocreates.gamemodes.data;

public class UpdateCheckResponse {
    private final int statusCode;
    private final String latestVersionNumber;
    private final String latestVersionTitle;
    private final String changeLog;
    private final String[] splitChangeLog;

    public UpdateCheckResponse(int statusCode, String latestVersionNumber, String latestVersionTitle, String changeLog, String[] splitChangeLog) {
        this.statusCode = statusCode;
        this.latestVersionNumber = latestVersionNumber;
        this.latestVersionTitle = latestVersionTitle;
        this.changeLog = changeLog;
        this.splitChangeLog = splitChangeLog;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public String getLatestVersionNumber() {
        return latestVersionNumber;
    }

    public String getLatestVersionTitle() {
        return latestVersionTitle;
    }

    public String getChangeLog() {
        return changeLog;
    }

    public String[] getSplitChangeLog() {
        return splitChangeLog;
    }
}
