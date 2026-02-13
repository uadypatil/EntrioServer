package com.EntrioServer.app.services.serviceImpls;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.EntrioServer.app.dto.MonthManagerDto;
import com.EntrioServer.app.models.DesktopUser;
import com.EntrioServer.app.models.MonthManager;
import com.EntrioServer.app.repositories.DesktopUserRepository;
import com.EntrioServer.app.repositories.MonthManagerRepository;
import com.EntrioServer.app.services.MonthManagerService;

import jakarta.persistence.EntityNotFoundException;

@Service
@Transactional
public class MonthManagerServiceImpl implements MonthManagerService {

    private final MonthManagerRepository monthRepository;
    private final DesktopUserRepository desktopUserRepository;

    public MonthManagerServiceImpl(MonthManagerRepository monthRepository,
                                   DesktopUserRepository desktopUserRepository) {
        this.monthRepository = monthRepository;
        this.desktopUserRepository = desktopUserRepository;
    }

    // ================= CREATE =================

    @Override
    public MonthManagerDto createMonth(MonthManagerDto dto) {

        DesktopUser user = desktopUserRepository.findById(dto.getDesktopUserId())
                .orElseThrow(() -> new EntityNotFoundException("DesktopUser not found"));

        MonthManager month = new MonthManager();
        month.setMonthName(dto.getMonthName());
        month.setYear(dto.getYear());
        month.setDesktopUser(user);

        return mapToDto(monthRepository.save(month));
    }

    // ================= READ =================

    @Override
    public List<MonthManagerDto> getAllMonths() {
        return monthRepository.findAll()
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public MonthManagerDto getMonthById(Long id) {
        MonthManager month = monthRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Month not found"));
        return mapToDto(month);
    }

    @Override
    public List<MonthManagerDto> getMonthsByUser(Long desktopUserId) {
        return monthRepository.findByDesktopUserId(desktopUserId)
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<MonthManagerDto> getMonthsByYear(Integer year) {
        return monthRepository.findByYear(year)
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    // ================= UPDATE (PUT) =================

    @Override
    public MonthManagerDto updateMonth(Long id, MonthManagerDto dto) {

        MonthManager existing = monthRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Month not found"));

        DesktopUser user = desktopUserRepository.findById(dto.getDesktopUserId())
                .orElseThrow(() -> new EntityNotFoundException("DesktopUser not found"));

        existing.setMonthName(dto.getMonthName());
        existing.setYear(dto.getYear());
        existing.setDesktopUser(user);

        return mapToDto(monthRepository.save(existing));
    }

    // ================= PATCH =================

    @Override
    public MonthManagerDto patchMonth(Long id, MonthManagerDto dto) {

        MonthManager existing = monthRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Month not found"));

        if (dto.getMonthName() != null)
            existing.setMonthName(dto.getMonthName());

        if (dto.getYear() != null)
            existing.setYear(dto.getYear());

        if (dto.getDesktopUserId() != null) {
            DesktopUser user = desktopUserRepository.findById(dto.getDesktopUserId())
                    .orElseThrow(() -> new EntityNotFoundException("DesktopUser not found"));
            existing.setDesktopUser(user);
        }

        return mapToDto(monthRepository.save(existing));
    }

    // ================= DELETE =================

    @Override
    public void deleteMonth(Long id) {
        if (!monthRepository.existsById(id)) {
            throw new EntityNotFoundException("Month not found");
        }
        monthRepository.deleteById(id);
    }

    // ================= MAPPER =================

    private MonthManagerDto mapToDto(MonthManager month) {
        return new MonthManagerDto(
                month.getId(),
                month.getMonthName(),
                month.getYear(),
                month.getDesktopUser().getId()
        );
    }
}
