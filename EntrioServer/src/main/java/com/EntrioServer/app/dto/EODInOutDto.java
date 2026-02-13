package com.EntrioServer.app.dto;

import java.time.LocalDate;
import java.time.LocalTime;

import com.EntrioServer.app.literals.AttendanceStatus;

public class EODInOutDto {

	private Long desktopUserId;
	private Long monthId;

	private LocalTime timeIn;
	private LocalTime timeOut;

	private LocalDate date;

	private AttendanceStatus attendanceStatus;

	private String todaysWork;
	private String assignedBy;
	private String remarks;

	// Constructors
	public EODInOutDto() {
	}

	public EODInOutDto(Long desktopUserId, Long monthId, LocalTime timeIn, LocalTime timeOut, LocalDate date,
			AttendanceStatus attendanceStatus, String todaysWork, String assignedBy, String remarks) {
		this.desktopUserId = desktopUserId;
		this.monthId = monthId;
		this.timeIn = timeIn;
		this.timeOut = timeOut;
		this.date = date;
		this.attendanceStatus = attendanceStatus;
		this.todaysWork = todaysWork;
		this.assignedBy = assignedBy;
		this.remarks = remarks;
	}

	// Getters and Setters

	public Long getDesktopUserId() {
		return desktopUserId;
	}

	public void setDesktopUserId(Long desktopUserId) {
		this.desktopUserId = desktopUserId;
	}

	public Long getMonthId() {
		return monthId;
	}

	public void setMonthId(Long monthId) {
		this.monthId = monthId;
	}

	public LocalTime getTimeIn() {
		return timeIn;
	}

	public void setTimeIn(LocalTime timeIn) {
		this.timeIn = timeIn;
	}

	public LocalTime getTimeOut() {
		return timeOut;
	}

	public void setTimeOut(LocalTime timeOut) {
		this.timeOut = timeOut;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public AttendanceStatus getAttendanceStatus() {
		return attendanceStatus;
	}

	public void setAttendanceStatus(AttendanceStatus attendanceStatus) {
		this.attendanceStatus = attendanceStatus;
	}

	public String getTodaysWork() {
		return todaysWork;
	}

	public void setTodaysWork(String todaysWork) {
		this.todaysWork = todaysWork;
	}

	public String getAssignedBy() {
		return assignedBy;
	}

	public void setAssignedBy(String assignedBy) {
		this.assignedBy = assignedBy;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
}
