package com.EntrioServer.app.repositories;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.EntrioServer.app.models.EODInOutRecords;

@Repository
public interface EODInOutRecordsRepository extends JpaRepository<EODInOutRecords, Long> {

    // Get all records by desktop user id
    List<EODInOutRecords> findByDesktopUserId(Long desktopUserId);

    // Get all records by attendance date
    List<EODInOutRecords> findByDate(LocalDate date);

    // Optional but very useful 👇
    // Prevent duplicate entry for same user & date
    boolean existsByDesktopUserIdAndDate(Long desktopUserId, LocalDate date);
    
    Optional<EODInOutRecords> findByDesktopUserIdAndDate(Long desktopUserId, LocalDate date);

}
