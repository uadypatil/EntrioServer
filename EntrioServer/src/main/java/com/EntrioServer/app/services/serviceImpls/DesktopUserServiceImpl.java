	package com.EntrioServer.app.services.serviceImpls;

	import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.EntrioServer.app.models.DesktopUser;
import com.EntrioServer.app.repositories.DesktopUserRepository;
import com.EntrioServer.app.services.DesktopUserService;

	@Service
	public class DesktopUserServiceImpl implements DesktopUserService {

	    private final DesktopUserRepository desktopUserRepository;

	    @Autowired
	    public DesktopUserServiceImpl(DesktopUserRepository desktopUserRepository) {
	        this.desktopUserRepository = desktopUserRepository;
	    }

	    @Override
	    public List<DesktopUser> getAllDesktopUsers() {
	        return desktopUserRepository.findAll();
	    }

	    @Override
	    public Optional<DesktopUser> getDesktopUserById(Long id) {
	        return desktopUserRepository.findById(id);
	    }

	    @Override
	    public DesktopUser createDesktopUser(DesktopUser desktopUser) {
	        return desktopUserRepository.save(desktopUser);
	    }
	    
	    @Override
	    public Optional<DesktopUser> updateDesktopUser(Long id, DesktopUser updatedUser) {
	        return desktopUserRepository.findById(id).map(user -> {

	            if (updatedUser.getDesktopId() != null) {
	                user.setDesktopId(updatedUser.getDesktopId());
	            }

	            if (updatedUser.getDesktopUserName() != null) {
	                user.setDesktopUserName(updatedUser.getDesktopUserName());
	            }

	            if (updatedUser.getDesktopIp() != null) {
	                user.setDesktopIp(updatedUser.getDesktopIp());
	            }

	            if (updatedUser.getCurrentActivity() != null) {
	                user.setCurrentActivity(updatedUser.getCurrentActivity());
	            }

	            if (updatedUser.getActivityStatus() != null) {
	                user.setActivityStatus(updatedUser.getActivityStatus());
	            }

	            return desktopUserRepository.save(user);
	        });
	    }


	    @Override
	    public boolean deleteDesktopUser(Long id) {
	        return desktopUserRepository.findById(id).map(user -> {
	            desktopUserRepository.delete(user);
	            return true;
	        }).orElse(false);
	    }
	}
