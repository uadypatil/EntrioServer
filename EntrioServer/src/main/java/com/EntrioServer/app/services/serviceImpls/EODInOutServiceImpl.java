package com.EntrioServer.app.services.serviceImpls;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.EntrioServer.app.dto.EODInOutDto;
import com.EntrioServer.app.models.DesktopUser;
import com.EntrioServer.app.models.EODInOutRecords;
import com.EntrioServer.app.repositories.DesktopUserRepository;
import com.EntrioServer.app.repositories.EODInOutRecordsRepository;
import com.EntrioServer.app.services.EODInOutService;

import jakarta.persistence.EntityNotFoundException;

@Service
@Transactional
public class EODInOutServiceImpl implements EODInOutService {

    private final EODInOutRecordsRepository eodRepository;
    private final DesktopUserRepository desktopUserRepository;

    public EODInOutServiceImpl(
            EODInOutRecordsRepository eodRepository,
            DesktopUserRepository desktopUserRepository) {

        this.eodRepository = eodRepository;
        this.desktopUserRepository = desktopUserRepository;
    }

    // ================= CREATE =================

    @Override
    public EODInOutDto createEODRecord(EODInOutDto dto) {

        DesktopUser user = desktopUserRepository.findById(dto.getDesktopUserId())
                .orElseThrow(() -> new EntityNotFoundException("DesktopUser not found"));

        EODInOutRecords entity = mapToEntity(dto);
        entity.setDesktopUser(user);

        return mapToDto(eodRepository.save(entity));
    }

    // ================= READ =================

    @Override
    public List<EODInOutDto> getAllEODRecords() {
        return eodRepository.findAll()
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public EODInOutDto getEODRecordById(Long id) {
        EODInOutRecords entity = eodRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("EOD record not found"));
        return mapToDto(entity);
    }

    @Override
    public List<EODInOutDto> getEODRecordsByUser(Long desktopUserId) {
        return eodRepository.findByDesktopUserId(desktopUserId)
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<EODInOutDto> getEODRecordsByDate(LocalDate date) {
        return eodRepository.findByDate(date)
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    // ================= PUT =================

    @Override
    public EODInOutDto updateEODRecord(Long id, EODInOutDto dto) {

        EODInOutRecords existing = eodRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("EOD record not found"));

        DesktopUser user = desktopUserRepository.findById(dto.getDesktopUserId())
                .orElseThrow(() -> new EntityNotFoundException("DesktopUser not found"));

        existing.setDesktopUser(user);
        existing.setTimeIn(dto.getTimeIn());
        existing.setTimeOut(dto.getTimeOut());
        existing.setDate(dto.getDate());
        existing.setAttendanceStatus(dto.getAttendanceStatus());
        existing.setTodaysWork(dto.getTodaysWork());
        existing.setAssignedBy(dto.getAssignedBy());
        existing.setRemarks(dto.getRemarks());

        return mapToDto(eodRepository.save(existing));
    }

    // ================= PATCH =================

    @Override
    public EODInOutDto patchEODRecord(Long id, EODInOutDto dto) {

        EODInOutRecords existing = eodRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("EOD record not found"));

        if (dto.getDesktopUserId() != null) {
            DesktopUser user = desktopUserRepository.findById(dto.getDesktopUserId())
                    .orElseThrow(() -> new EntityNotFoundException("DesktopUser not found"));
            existing.setDesktopUser(user);
        }

        if (dto.getTimeIn() != null)
            existing.setTimeIn(dto.getTimeIn());

        if (dto.getTimeOut() != null)
            existing.setTimeOut(dto.getTimeOut());

        if (dto.getDate() != null)
            existing.setDate(dto.getDate());

        if (dto.getAttendanceStatus() != null)
            existing.setAttendanceStatus(dto.getAttendanceStatus());

        if (dto.getTodaysWork() != null)
            existing.setTodaysWork(dto.getTodaysWork());

        if (dto.getAssignedBy() != null)
            existing.setAssignedBy(dto.getAssignedBy());

        if (dto.getRemarks() != null)
            existing.setRemarks(dto.getRemarks());

        return mapToDto(eodRepository.save(existing));
    }

    // ================= DELETE =================

    @Override
    public void deleteEODRecord(Long id) {
        if (!eodRepository.existsById(id)) {
            throw new EntityNotFoundException("EOD record not found");
        }
        eodRepository.deleteById(id);
    }

    // ================= TODAY OPERATIONS =================

    @Override
    public EODInOutDto getTodayEodInOut(Long desktopUserId) {

        LocalDate today = LocalDate.now();

        EODInOutRecords entity = eodRepository
                .findByDesktopUserIdAndDate(desktopUserId, today)
                .orElseThrow(() -> new EntityNotFoundException("No EOD found for today"));

        return mapToDto(entity);
    }

    @Override
    public EODInOutDto fillTodayEodInOut(Long userId, EODInOutDto dto) {

        LocalDate today = LocalDate.now();

        Optional<EODInOutRecords> existingRecordOpt =
                eodRepository.findByDesktopUserIdAndDate(userId, today);

        EODInOutRecords record;

        if (existingRecordOpt.isPresent()) {
            record = existingRecordOpt.get();
        } else {
            record = new EODInOutRecords();
            record.setDesktopUser(
                    desktopUserRepository.findById(userId)
                            .orElseThrow(() -> new EntityNotFoundException("DesktopUser not found"))
            );
            record.setDate(today);
        }

        record.setTimeIn(dto.getTimeIn());
        record.setTimeOut(dto.getTimeOut());
        record.setTodaysWork(dto.getTodaysWork());
        record.setAssignedBy(dto.getAssignedBy());
        record.setRemarks(dto.getRemarks());
        record.setAttendanceStatus(dto.getAttendanceStatus());

        return mapToDto(eodRepository.save(record));
    }

    // ================= MAPPER =================

    private EODInOutDto mapToDto(EODInOutRecords entity) {
        EODInOutDto dto = new EODInOutDto();
        dto.setDesktopUserId(entity.getDesktopUser().getId());
        dto.setTimeIn(entity.getTimeIn());
        dto.setTimeOut(entity.getTimeOut());
        dto.setDate(entity.getDate());
        dto.setAttendanceStatus(entity.getAttendanceStatus());
        dto.setTodaysWork(entity.getTodaysWork());
        dto.setAssignedBy(entity.getAssignedBy());
        dto.setRemarks(entity.getRemarks());
        return dto;
    }

    private EODInOutRecords mapToEntity(EODInOutDto dto) {
        EODInOutRecords entity = new EODInOutRecords();
        entity.setTimeIn(dto.getTimeIn());
        entity.setTimeOut(dto.getTimeOut());
        entity.setDate(dto.getDate());
        entity.setAttendanceStatus(dto.getAttendanceStatus());
        entity.setTodaysWork(dto.getTodaysWork());
        entity.setAssignedBy(dto.getAssignedBy());
        entity.setRemarks(dto.getRemarks());
        return entity;
    }
}
