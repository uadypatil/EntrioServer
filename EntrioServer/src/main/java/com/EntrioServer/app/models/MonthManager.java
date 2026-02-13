package com.EntrioServer.app.models;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "month_manager")
public class MonthManager {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "month_name", nullable = false)
    private String monthName;

    @Column(name = "year", nullable = false)
    private Integer year;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "desktop_user_id", nullable = false)
    private DesktopUser desktopUser;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return "MonthManager{" +
                "id=" + id +
                ", monthName='" + monthName + '\'' +
                ", year=" + year +
                ", desktopUserId=" + (desktopUser != null ? desktopUser.getId() : null) +
                ", createdAt=" + createdAt +
                '}';
    }

    // getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMonthName() {
        return monthName;
    }

    public void setMonthName(String monthName) {
        this.monthName = monthName;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public DesktopUser getDesktopUser() {
        return desktopUser;
    }

    public void setDesktopUser(DesktopUser desktopUser) {
        this.desktopUser = desktopUser;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
