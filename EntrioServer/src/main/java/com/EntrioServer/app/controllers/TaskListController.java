package com.EntrioServer.app.controllers;

import com.EntrioServer.app.models.TaskList;
import com.EntrioServer.app.services.TaskListService;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/tasks")
@CrossOrigin(origins = "*")
public class TaskListController {

    private final TaskListService taskService;

    public TaskListController(TaskListService taskService) {
        this.taskService = taskService;
    }

    // ================= CREATE =================

    @PostMapping
    public ResponseEntity<TaskList> create(@RequestBody TaskList task) {
        TaskList created = taskService.create(task);
        return ResponseEntity.ok(created);
    }

    // ================= GET ALL =================

    @GetMapping
    public ResponseEntity<List<TaskList>> getAll() {
        return ResponseEntity.ok(taskService.getAll());
    }

    // ================= GET BY ID =================

    @GetMapping("/{id}")
    public ResponseEntity<TaskList> getById(@PathVariable Long id) {
        return ResponseEntity.ok(taskService.getById(id));
    }

    // ================= GET BY USER =================

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<TaskList>> getByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(taskService.getByUser(userId));
    }

    // ================= GET BY DATE =================

    @GetMapping("/date")
    public ResponseEntity<List<TaskList>> getByDate(
            @RequestParam
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate date) {

        return ResponseEntity.ok(taskService.getByDate(date));
    }

    // ================= GET CURRENT MONTH =================

    @GetMapping("/current-month")
    public ResponseEntity<List<TaskList>> getCurrentMonth() {
        return ResponseEntity.ok(taskService.getCurrentMonthTasks());
    }

    // ================= UPDATE (FULL) =================

    @PutMapping("/{id}")
    public ResponseEntity<TaskList> update(
            @PathVariable Long id,
            @RequestBody TaskList task) {

        return ResponseEntity.ok(taskService.update(id, task));
    }

    // ================= PARTIAL UPDATE =================

    @PatchMapping("/{id}")
    public ResponseEntity<TaskList> partialUpdate(
            @PathVariable Long id,
            @RequestBody TaskList task) {

        return ResponseEntity.ok(taskService.partialUpdate(id, task));
    }

    // ================= DELETE =================

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        taskService.delete(id);
        return ResponseEntity.ok("Task deleted successfully");
    }

    // ================= FILL TODAY'S TASK =================

    @PostMapping("/fill-today")
    public ResponseEntity<TaskList> fillTodaysTask(
            @RequestParam Long userId,
            @RequestParam String task,
            @RequestParam String assignedBy) {

        return ResponseEntity.ok(
                taskService.fillTodaysTask(userId, task, assignedBy)
        );
    }
}
