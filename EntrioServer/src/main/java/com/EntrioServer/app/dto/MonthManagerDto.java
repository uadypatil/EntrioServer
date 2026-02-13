package com.EntrioServer.app.dto;

public class MonthManagerDto {

    private Long id;
    private String monthName;
    private Integer year;
    private Long desktopUserId;

    // Constructors
    public MonthManagerDto() {
    }

    public MonthManagerDto(Long id, String monthName, Integer year, Long desktopUserId) {
        this.id = id;
        this.monthName = monthName;
        this.year = year;
        this.desktopUserId = desktopUserId;
    }

    // Getters & Setters

    public Long getId() {
        return id;
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

    public Long getDesktopUserId() {
        return desktopUserId;
    }

    public void setDesktopUserId(Long desktopUserId) {
        this.desktopUserId = desktopUserId;
    }
}
