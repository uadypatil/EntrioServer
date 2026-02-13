package com.EntrioServer.app.models;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

@Entity
@Table(name = "reports_generation_history")
public class ReportsGenerationHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "desktop_user_id", nullable = false)
    private DesktopUser desktopUser;

    @Column(name = "for_month", nullable = false)
    private Integer forMonth; // 1 = Jan, 12 = Dec

    @Column(name = "for_year", nullable = false)
    private Integer forYear;

    @Column(name = "report_generated_at", nullable = false)
    private LocalDateTime reportGeneratedAt;

    @PrePersist
    protected void onCreate() {
        if (this.reportGeneratedAt == null) {
            this.reportGeneratedAt = LocalDateTime.now();
        }
    }

    @Override
    public String toString() {
        return "ReportsGenerationHistory{" +
                "id=" + id +
                ", desktopUserId=" + (desktopUser != null ? desktopUser.getId() : null) +
                ", forMonth=" + forMonth +
                ", forYear=" + forYear +
                ", reportGeneratedAt=" + reportGeneratedAt +
                '}';
    }

    // getters and setters

    public Long getId() {
        return id;
    }

    public DesktopUser getDesktopUser() {
        return desktopUser;
    }

    public void setDesktopUser(DesktopUser desktopUser) {
        this.desktopUser = desktopUser;
    }

    public Integer getForMonth() {
        return forMonth;
    }

    public void setForMonth(Integer forMonth) {
        this.forMonth = forMonth;
    }

    public Integer getForYear() {
        return forYear;
    }

    public void setForYear(Integer forYear) {
        this.forYear = forYear;
    }

    public LocalDateTime getReportGeneratedAt() {
        return reportGeneratedAt;
    }

    public void setReportGeneratedAt(LocalDateTime reportGeneratedAt) {
        this.reportGeneratedAt = reportGeneratedAt;
    }
}
