package com.EntrioServer.app.dto;

import com.EntrioServer.app.literals.ActivityStatus;

public class DesktopUserDto {

    private String desktopId;
    private String desktopUserName;
    private String desktopIp;
    private ActivityStatus currentActivity;
    private ActivityStatus activityStatus;

    public DesktopUserDto() {
    }

    public DesktopUserDto(String desktopId, String desktopUserName, String desktopIp,
                          ActivityStatus currentActivity, ActivityStatus activityStatus) {
        this.desktopId = desktopId;
        this.desktopUserName = desktopUserName;
        this.desktopIp = desktopIp;
        this.currentActivity = currentActivity;
        this.activityStatus = activityStatus;
    }

    public String getDesktopId() {
        return desktopId;
    }

    public void setDesktopId(String desktopId) {
        this.desktopId = desktopId;
    }

    public String getDesktopUserName() {
        return desktopUserName;
    }

    public void setDesktopUserName(String desktopUserName) {
        this.desktopUserName = desktopUserName;
    }

    public String getDesktopIp() {
        return desktopIp;
    }

    public void setDesktopIp(String desktopIp) {
        this.desktopIp = desktopIp;
    }

    public ActivityStatus getCurrentActivity() {
        return currentActivity;
    }

    public void setCurrentActivity(ActivityStatus currentActivity) {
        this.currentActivity = currentActivity;
    }

    public ActivityStatus getActivityStatus() {
        return activityStatus;
    }

    public void setActivityStatus(ActivityStatus activityStatus) {
        this.activityStatus = activityStatus;
    }
}
