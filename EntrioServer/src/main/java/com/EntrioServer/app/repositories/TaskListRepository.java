package com.EntrioServer.app.repositories;

import com.EntrioServer.app.models.TaskList;
import com.EntrioServer.app.models.DesktopUser;
import com.EntrioServer.app.literals.TaskStatus;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface TaskListRepository extends JpaRepository<TaskList, Long> {

	List<TaskList> findByDesktopUser(DesktopUser desktopUser);

	List<TaskList> findByAssignedOnBetween(LocalDateTime start, LocalDateTime end);

	List<TaskList> findByDesktopUserAndAssignedOnBetween(DesktopUser desktopUser, LocalDateTime start,
			LocalDateTime end);

	List<TaskList> findByDesktopUserAndAssignedOnBetweenAndStatus(DesktopUser desktopUser, LocalDateTime start,
			LocalDateTime end, TaskStatus status);

	Optional<TaskList> findByDesktopUserAndAssignedOnBetweenAndTask(DesktopUser desktopUser, LocalDateTime start,
			LocalDateTime end, String task);
}
