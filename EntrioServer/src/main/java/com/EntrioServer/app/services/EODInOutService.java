package com.EntrioServer.app.services;

import java.time.LocalDate;
import java.util.List;

import com.EntrioServer.app.dto.EODInOutDto;

public interface EODInOutService {

	// CREATE
	EODInOutDto createEODRecord(EODInOutDto eodInOutDto);

	// READ - get all
	List<EODInOutDto> getAllEODRecords();

	// READ - get by id
	EODInOutDto getEODRecordById(Long id);

	// READ - get by user
	List<EODInOutDto> getEODRecordsByUser(Long desktopUserId);

	// READ - get by date
	List<EODInOutDto> getEODRecordsByDate(LocalDate date);

	// UPDATE - full update (PUT)
	EODInOutDto updateEODRecord(Long id, EODInOutDto eodInOutDto);

	// UPDATE - partial update (PATCH)
	EODInOutDto patchEODRecord(Long id, EODInOutDto eodInOutDto);

	// DELETE
	void deleteEODRecord(Long id);

	// Fill today's EOD (create or update today's entry)
	EODInOutDto fillTodayEodInOut(Long userId, EODInOutDto eodDto);

	// Get today's EOD
	EODInOutDto getTodayEodInOut(Long desktopUserId);
}
