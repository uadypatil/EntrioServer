package com.EntrioServer.app.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.EntrioServer.app.models.MonthManager;

public interface MonthManagerRepository extends JpaRepository<MonthManager, Long> {

    List<MonthManager> findByDesktopUserId(Long desktopUserId);

    List<MonthManager> findByYear(Integer year);
}
