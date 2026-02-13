package com.EntrioServer.app.services;

import com.EntrioServer.app.models.TaskList;

import java.time.LocalDate;
import java.util.List;

public interface TaskListService {

	TaskList create(TaskList task);

	List<TaskList> getAll();

	TaskList getById(Long id);

	List<TaskList> getByUser(Long userId);

	List<TaskList> getByDate(LocalDate date);

	List<TaskList> getCurrentMonthTasks();

	TaskList update(Long id, TaskList task);

	TaskList partialUpdate(Long id, TaskList task);

	void delete(Long id);

	TaskList fillTodaysTask(Long userId, String task, String assignedBy);
}
