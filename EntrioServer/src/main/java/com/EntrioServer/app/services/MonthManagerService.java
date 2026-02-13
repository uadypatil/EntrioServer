package com.EntrioServer.app.services;

import java.util.List;

import com.EntrioServer.app.dto.MonthManagerDto;

public interface MonthManagerService {

    // CREATE
    MonthManagerDto createMonth(MonthManagerDto dto);

    // READ
    List<MonthManagerDto> getAllMonths();

    MonthManagerDto getMonthById(Long id);

    List<MonthManagerDto> getMonthsByUser(Long desktopUserId);

    List<MonthManagerDto> getMonthsByYear(Integer year);

    // UPDATE
    MonthManagerDto updateMonth(Long id, MonthManagerDto dto);

    MonthManagerDto patchMonth(Long id, MonthManagerDto dto);

    // DELETE
    void deleteMonth(Long id);
}
