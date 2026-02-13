package com.EntrioServer.app.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.EntrioServer.app.dto.MonthManagerDto;
import com.EntrioServer.app.services.MonthManagerService;

@RestController
@RequestMapping("/api/months")
public class MonthManagerController {

    private final MonthManagerService monthService;

    public MonthManagerController(MonthManagerService monthService) {
        this.monthService = monthService;
    }

    // ================= CREATE =================
    @PostMapping
    public ResponseEntity<MonthManagerDto> createMonth(
            @RequestBody MonthManagerDto dto) {
        return ResponseEntity.ok(monthService.createMonth(dto));
    }

    // ================= GET ALL =================
    @GetMapping
    public ResponseEntity<List<MonthManagerDto>> getAllMonths() {
        return ResponseEntity.ok(monthService.getAllMonths());
    }

    // ================= GET BY ID =================
    @GetMapping("/{id}")
    public ResponseEntity<MonthManagerDto> getMonthById(
            @PathVariable Long id) {
        return ResponseEntity.ok(monthService.getMonthById(id));
    }

    // ================= GET BY USER =================
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<MonthManagerDto>> getMonthsByUser(
            @PathVariable Long userId) {
        return ResponseEntity.ok(monthService.getMonthsByUser(userId));
    }

    // ================= GET BY YEAR =================
    @GetMapping("/year/{year}")
    public ResponseEntity<List<MonthManagerDto>> getMonthsByYear(
            @PathVariable Integer year) {
        return ResponseEntity.ok(monthService.getMonthsByYear(year));
    }

    // ================= PUT (FULL UPDATE) =================
    @PutMapping("/{id}")
    public ResponseEntity<MonthManagerDto> updateMonth(
            @PathVariable Long id,
            @RequestBody MonthManagerDto dto) {
        return ResponseEntity.ok(monthService.updateMonth(id, dto));
    }

    // ================= PATCH (PARTIAL UPDATE) =================
    @PatchMapping("/{id}")
    public ResponseEntity<MonthManagerDto> patchMonth(
            @PathVariable Long id,
            @RequestBody MonthManagerDto dto) {
        return ResponseEntity.ok(monthService.patchMonth(id, dto));
    }

    // ================= DELETE =================
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteMonth(
            @PathVariable Long id) {
        monthService.deleteMonth(id);
        return ResponseEntity.ok("Month deleted successfully");
    }
}
