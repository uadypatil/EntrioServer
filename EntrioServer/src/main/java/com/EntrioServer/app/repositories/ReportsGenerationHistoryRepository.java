package com.EntrioServer.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.EntrioServer.app.models.ReportsGenerationHistory;

public interface ReportsGenerationHistoryRepository extends JpaRepository<ReportsGenerationHistory, Long> {

}
