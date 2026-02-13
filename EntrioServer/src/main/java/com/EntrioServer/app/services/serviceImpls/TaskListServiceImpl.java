package com.EntrioServer.app.services.serviceImpls;

import com.EntrioServer.app.models.TaskList;
import com.EntrioServer.app.models.DesktopUser;
import com.EntrioServer.app.literals.TaskStatus;
import com.EntrioServer.app.repositories.TaskListRepository;
import com.EntrioServer.app.repositories.DesktopUserRepository;
import com.EntrioServer.app.services.TaskListService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.*;
import java.util.List;

@Service
@Transactional
public class TaskListServiceImpl implements TaskListService {

	private final TaskListRepository taskRepository;
	private final DesktopUserRepository userRepository;

	public TaskListServiceImpl(TaskListRepository taskRepository, DesktopUserRepository userRepository) {
		this.taskRepository = taskRepository;
		this.userRepository = userRepository;
	}

	// ================= CREATE =================

	@Override
	public TaskList create(TaskList task) {
		if (task.getAssignedOn() == null) {
			task.setAssignedOn(LocalDateTime.now());
		}
		if (task.getStatus() == null) {
			task.setStatus(TaskStatus.PENDING);
		}
		return taskRepository.save(task);
	}

	// ================= GET ALL =================

	@Override
	public List<TaskList> getAll() {
		return taskRepository.findAll();
	}

	// ================= GET BY ID =================

	@Override
	public TaskList getById(Long id) {
		return taskRepository.findById(id).orElseThrow(() -> new RuntimeException("Task not found with id: " + id));
	}

	// ================= GET BY USER =================

	@Override
	public List<TaskList> getByUser(Long userId) {
		DesktopUser user = getUser(userId);
		return taskRepository.findByDesktopUser(user);
	}

	// ================= GET BY DATE =================

	@Override
	public List<TaskList> getByDate(LocalDate date) {
		LocalDateTime start = date.atStartOfDay();
		LocalDateTime end = date.atTime(LocalTime.MAX);

		return taskRepository.findByAssignedOnBetween(start, end);
	}

	// ================= CURRENT MONTH =================

	@Override
	public List<TaskList> getCurrentMonthTasks() {
		LocalDate now = LocalDate.now();

		LocalDateTime start = now.withDayOfMonth(1).atStartOfDay();
		LocalDateTime end = now.withDayOfMonth(now.lengthOfMonth()).atTime(LocalTime.MAX);

		return taskRepository.findByAssignedOnBetween(start, end);
	}

	// ================= UPDATE (FULL) =================

	@Override
	public TaskList update(Long id, TaskList updatedTask) {

		TaskList existing = getById(id);

		existing.setTask(updatedTask.getTask());
		existing.setAssignedBy(updatedTask.getAssignedBy());
		existing.setAssignedOn(updatedTask.getAssignedOn());
		existing.setStatus(updatedTask.getStatus());
		existing.setDesktopUser(updatedTask.getDesktopUser());

		return taskRepository.save(existing);
	}

	// ================= PARTIAL UPDATE =================

	@Override
	public TaskList partialUpdate(Long id, TaskList updatedTask) {

		TaskList existing = getById(id);

		if (updatedTask.getTask() != null)
			existing.setTask(updatedTask.getTask());

		if (updatedTask.getAssignedBy() != null)
			existing.setAssignedBy(updatedTask.getAssignedBy());

		if (updatedTask.getAssignedOn() != null)
			existing.setAssignedOn(updatedTask.getAssignedOn());

		if (updatedTask.getStatus() != null)
			existing.setStatus(updatedTask.getStatus());

		if (updatedTask.getDesktopUser() != null)
			existing.setDesktopUser(updatedTask.getDesktopUser());

		return taskRepository.save(existing);
	}

	// ================= DELETE =================

	@Override
	public void delete(Long id) {
		TaskList task = getById(id);
		taskRepository.delete(task);
	}

	// ================= FILL TODAY'S TASK =================

	@Override
	public TaskList fillTodaysTask(Long userId, String task, String assignedBy) {

		DesktopUser user = getUser(userId);

		LocalDate today = LocalDate.now();
		LocalDateTime start = today.atStartOfDay();
		LocalDateTime end = today.atTime(LocalTime.MAX);

		// Prevent duplicate task for today
		taskRepository.findByDesktopUserAndAssignedOnBetweenAndTask(user, start, end, task).ifPresent(t -> {
			throw new RuntimeException("Task already exists for today");
		});

		TaskList newTask = new TaskList();
		newTask.setDesktopUser(user);
		newTask.setTask(task);
		newTask.setAssignedBy(assignedBy);
		newTask.setAssignedOn(LocalDateTime.now());
		newTask.setStatus(TaskStatus.PENDING);

		return taskRepository.save(newTask);
	}

	// ================= HELPER =================

	private DesktopUser getUser(Long userId) {
		return userRepository.findById(userId)
				.orElseThrow(() -> new RuntimeException("User not found with id: " + userId));
	}
}
