package com.EntrioServer.app.models;
import java.time.LocalDateTime;

import com.EntrioServer.app.literals.TaskStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;

@Entity
@Table(name = "task_list")
public class TaskList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "desktop_user_id", nullable = false)
    private DesktopUser desktopUser;

    @Column(name = "task", length = 1000, nullable = false)
    private String task;

    @Column(name = "assigned_by")
    private String assignedBy;

    @Column(name = "assigned_on")
    private LocalDateTime assignedOn;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private TaskStatus status;

    @Column(name = "status_updated_on")
    private LocalDateTime statusUpdatedOn;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return "TaskList{" +
                "id=" + id +
                ", desktopUserId=" + (desktopUser != null ? desktopUser.getId() : null) +
                ", task='" + task + '\'' +
                ", assignedBy='" + assignedBy + '\'' +
                ", assignedOn=" + assignedOn +
                ", status=" + status +
                ", statusUpdatedOn=" + statusUpdatedOn +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }

    // getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public DesktopUser getDesktopUser() {
        return desktopUser;
    }

    public void setDesktopUser(DesktopUser desktopUser) {
        this.desktopUser = desktopUser;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public String getAssignedBy() {
        return assignedBy;
    }

    public void setAssignedBy(String assignedBy) {
        this.assignedBy = assignedBy;
    }

    public LocalDateTime getAssignedOn() {
        return assignedOn;
    }

    public void setAssignedOn(LocalDateTime assignedOn) {
        this.assignedOn = assignedOn;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
        this.statusUpdatedOn = LocalDateTime.now();
    }

    public LocalDateTime getStatusUpdatedOn() {
        return statusUpdatedOn;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
}
