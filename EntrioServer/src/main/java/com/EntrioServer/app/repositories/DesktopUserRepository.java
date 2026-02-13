package com.EntrioServer.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.EntrioServer.app.models.DesktopUser;

public interface DesktopUserRepository extends JpaRepository<DesktopUser, Long> {

}
