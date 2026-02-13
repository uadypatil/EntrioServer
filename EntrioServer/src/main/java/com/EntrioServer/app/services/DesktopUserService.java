package com.EntrioServer.app.services;

import java.util.List;
import java.util.Optional;

import com.EntrioServer.app.models.DesktopUser;

public interface DesktopUserService {

    List<DesktopUser> getAllDesktopUsers();

    Optional<DesktopUser> getDesktopUserById(Long id);

    DesktopUser createDesktopUser(DesktopUser desktopUser);

    Optional<DesktopUser> updateDesktopUser(Long id, DesktopUser desktopUser);

    boolean deleteDesktopUser(Long id);
}
