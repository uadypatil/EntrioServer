package com.EntrioServer.app.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.EntrioServer.app.dto.DesktopUserDto;
import com.EntrioServer.app.models.DesktopUser;
import com.EntrioServer.app.services.DesktopUserService;

@RestController
@RequestMapping("/api/desktop-user")
public class DesktopUserController {

    private final DesktopUserService desktopUserService;

    @Autowired
    public DesktopUserController(DesktopUserService desktopUserService) {
        this.desktopUserService = desktopUserService;
    }

    // ✅ Convert Entity → DTO
    private DesktopUserDto convertToDto(DesktopUser user) {
        return new DesktopUserDto(
                user.getDesktopId(),
                user.getDesktopUserName(),
                user.getDesktopIp(),
                user.getCurrentActivity(),
                user.getActivityStatus()
        );
    }

    // ✅ Convert DTO → Entity
    private DesktopUser convertToEntity(DesktopUserDto dto) {
        DesktopUser user = new DesktopUser();
        user.setDesktopId(dto.getDesktopId());
        user.setDesktopUserName(dto.getDesktopUserName());
        user.setDesktopIp(dto.getDesktopIp());
        user.setCurrentActivity(dto.getCurrentActivity());
        user.setActivityStatus(dto.getActivityStatus());
        return user;
    }

    // ✅ Get all desktop users
    @GetMapping
    public List<DesktopUserDto> getAllDesktopUsers() {
        return desktopUserService.getAllDesktopUsers()
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    // ✅ Get desktop user by ID
    @GetMapping("/{id}")
    public ResponseEntity<DesktopUserDto> getDesktopUserById(@PathVariable Long id) {
        return desktopUserService.getDesktopUserById(id)
                .map(this::convertToDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // ✅ Create a new desktop user
    @PostMapping
    public DesktopUserDto createDesktopUser(@RequestBody DesktopUserDto desktopUserDto) {
        DesktopUser savedUser = desktopUserService.createDesktopUser(
                convertToEntity(desktopUserDto)
        );
        return convertToDto(savedUser);
    }

    // ✅ Update an existing desktop user
    @PutMapping("/{id}")
    public ResponseEntity<DesktopUserDto> updateDesktopUser(
            @PathVariable Long id,
            @RequestBody DesktopUserDto updatedUserDto) {

        return desktopUserService.updateDesktopUser(id, convertToEntity(updatedUserDto))
                .map(this::convertToDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // ✅ Delete a desktop user
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDesktopUser(@PathVariable Long id) {
        boolean deleted = desktopUserService.deleteDesktopUser(id);
        return deleted ? ResponseEntity.noContent().build()
                       : ResponseEntity.notFound().build();
    }
}
