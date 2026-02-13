package com.EntrioServer.app.models;

import java.time.LocalDateTime;

import com.EntrioServer.app.literals.ActivityStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;

@Entity
@Table(name = "desktop_user")
public class DesktopUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "desktop_id", nullable = false)
    private String desktopId;

    @Column(name = "desktop_user_name", nullable = false)
    private String desktopUserName;

    @Column(name = "desktop_ip") // nullable by default
    private String desktopIp;

    // New fields with defaults
    @Column(name = "current_activity")
    private ActivityStatus currentActivity = ActivityStatus.INACTIVE;

    @Enumerated(EnumType.STRING)
    @Column(name = "activity_status")
    private ActivityStatus activityStatus = ActivityStatus.UNVERIFIED;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();

        // Ensure defaults in case entity is created without setting them
        if (this.currentActivity == null) {
            this.currentActivity = ActivityStatus.INACTIVE;
        }
        if (this.activityStatus == null) {
            this.activityStatus = ActivityStatus.UNVERIFIED;
        }
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return "DesktopUser{" +
                "id=" + id +
                ", desktopId='" + desktopId + '\'' +
                ", desktopUserName='" + desktopUserName + '\'' +
                ", desktopIp='" + desktopIp + '\'' +
                ", currentActivity='" + currentActivity + '\'' +
                ", activityStatus=" + activityStatus +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
}
