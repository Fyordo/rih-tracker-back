package com.rentinhand.rihtracker.services;

import com.rentinhand.rihtracker.dto.requests.task.TaskCreateRequest;
import com.rentinhand.rihtracker.dto.requests.task.TaskUpdateRequest;
import com.rentinhand.rihtracker.entities.Task;
import com.rentinhand.rihtracker.entities.User;

import java.util.List;
import java.util.Optional;

public interface TaskService {
    Optional<Task> findById(Long taskId);
    List<Task> findAll();
    Task createTask(TaskCreateRequest taskData);
    Task addUser(Long userId, Task task);
    Task updateTask(Task task, TaskUpdateRequest taskData);
    boolean deleteTask(Task task);
}