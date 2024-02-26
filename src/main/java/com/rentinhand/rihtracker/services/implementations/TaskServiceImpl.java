package com.rentinhand.rihtracker.services.implementations;

import com.rentinhand.rihtracker.dto.TaskDTO;
import com.rentinhand.rihtracker.dto.requests.task.TaskDataRequest;
import com.rentinhand.rihtracker.entities.Project;
import com.rentinhand.rihtracker.entities.ScrumColumn;
import com.rentinhand.rihtracker.entities.Task;
import com.rentinhand.rihtracker.entities.User;
import com.rentinhand.rihtracker.exceptions.ModelNotFoundException;
import com.rentinhand.rihtracker.repos.TaskRepository;
import com.rentinhand.rihtracker.services.TaskService;
import com.rentinhand.rihtracker.services.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;
    private final ScrumColumnService scrumColumnService;
    private final UserService userService;
    private ModelMapper mapper = new ModelMapper();

    @Override
    public Collection<Task> getProjectTasks(Project project) {
        return project.getTasks();
    }

    @Override
    public Collection<ScrumColumn> getProjectTasksByColumns(Project project) {
        Set<ScrumColumn> columns = new HashSet<>();
        project.getTasks().forEach((Task task) -> columns.add(task.getScrumColumn()));

        return columns;
    }

    @Override
    public Optional<Task> findById(Long taskId) {
        return taskRepository.findById(taskId);
    }

    @Override
    public Task createTask(TaskDataRequest taskData) {
        TaskDTO taskDTO = taskDataToDTO(taskData);

        Task task = mapper.map(taskDTO, Task.class);
        taskRepository.save(task);

        return task;
    }

    @Override
    public Task updateTask(Task task, TaskDataRequest taskData) {
        TaskDTO taskDTO = taskDataToDTO(taskData);

        taskRepository.updateTask(
                Optional.of(taskDTO.getTitle()).orElse(task.getTitle()),
                Optional.of(taskDTO.getDescription()).orElse(task.getDescription()),
                Optional.of(taskDTO.getDeadline()).orElse(task.getDeadline()),
                Optional.of(taskDTO.getTaskType()).orElse(task.getTaskType()),
                Optional.of(taskDTO.getScrumColumn()).orElse(task.getScrumColumn()),
                Optional.of(taskDTO.getMaintainer()).orElse(task.getMaintainer()),
                task.getId()
        );

        return task;
    }

    @Override
    public void deleteTask(Task task) {
        taskRepository.delete(task);
    }

    @Override
    public Task addUserToTask(Task task, User user) {
        task.getUsers().add(user);

        taskRepository.save(task);

        return task;
    }

    private TaskDTO taskDataToDTO(TaskDataRequest taskData){
        TaskDTO taskDTO = new TaskDTO();
        taskDTO.setTitle(taskData.getTitle());
        taskDTO.setDescription(taskData.getDescription());
        taskDTO.setDeadline(taskData.getDeadline());
        taskDTO.setTaskType(taskData.getTaskType());
        taskDTO.setScrumColumn(scrumColumnService.findById(taskData.getScrumColumnId()).orElseThrow(ModelNotFoundException::new));
        taskDTO.setMaintainer(userService.findById(taskData.getMaintainerUserId()).orElseThrow(ModelNotFoundException::new));
        taskDTO.setCreatedUser(userService.findById(taskData.getCreatedUserId()).orElseThrow(ModelNotFoundException::new));

        return taskDTO;
    }
}