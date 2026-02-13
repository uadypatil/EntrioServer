package com.EntrioServer.app.controllers;

import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.EntrioServer.app.dto.EODInOutDto;
import com.EntrioServer.app.services.EODInOutService;

@RestController
@RequestMapping("/api/eod")
public class EODInOutController {

    private final EODInOutService eodService;

    public EODInOutController(EODInOutService eodService) {
        this.eodService = eodService;
    }

    // ================= CREATE =================
    @PostMapping
    public ResponseEntity<EODInOutDto> createEODRecord(@RequestBody EODInOutDto dto) {
        return ResponseEntity.ok(eodService.createEODRecord(dto));
    }

    // ================= GET ALL =================
    @GetMapping
    public ResponseEntity<List<EODInOutDto>> getAllEODRecords() {
        return ResponseEntity.ok(eodService.getAllEODRecords());
    }

    // ================= GET BY ID =================
    @GetMapping("/{id}")
    public ResponseEntity<EODInOutDto> getEODRecordById(@PathVariable Long id) {
        return ResponseEntity.ok(eodService.getEODRecordById(id));
    }

    // ================= GET BY USER =================
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<EODInOutDto>> getEODRecordsByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(eodService.getEODRecordsByUser(userId));
    }

    // ================= GET BY DATE =================
    @GetMapping("/date")
    public ResponseEntity<List<EODInOutDto>> getEODRecordsByDate(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return ResponseEntity.ok(eodService.getEODRecordsByDate(date));
    }

    // ================= PUT =================
    @PutMapping("/{id}")
    public ResponseEntity<EODInOutDto> updateEODRecord(
            @PathVariable Long id,
            @RequestBody EODInOutDto dto) {
        return ResponseEntity.ok(eodService.updateEODRecord(id, dto));
    }

    // ================= PATCH =================
    @PatchMapping("/{id}")
    public ResponseEntity<EODInOutDto> patchEODRecord(
            @PathVariable Long id,
            @RequestBody EODInOutDto dto) {
        return ResponseEntity.ok(eodService.patchEODRecord(id, dto));
    }

    // ================= DELETE =================
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEODRecord(@PathVariable Long id) {
        eodService.deleteEODRecord(id);
        return ResponseEntity.ok("EOD record deleted successfully");
    }

    // ================= TODAY - GET =================
    @GetMapping("/today/{userId}")
    public ResponseEntity<EODInOutDto> getTodayEodInOut(@PathVariable Long userId) {
        return ResponseEntity.ok(eodService.getTodayEodInOut(userId));
    }

    // ================= TODAY - CREATE OR UPDATE =================
    @PostMapping("/today/{userId}")
    public ResponseEntity<EODInOutDto> fillTodayEodInOut(
            @PathVariable Long userId,
            @RequestBody EODInOutDto eodDto) {

        return ResponseEntity.ok(eodService.fillTodayEodInOut(userId, eodDto));
    }
}
