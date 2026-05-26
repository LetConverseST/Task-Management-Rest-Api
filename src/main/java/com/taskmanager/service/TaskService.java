package com.taskmanager.service;

import com.taskmanager.dto.TaskDTO;
import com.taskmanager.entity.TaskPriority;
import com.taskmanager.entity.TaskStatus;

import java.util.List;

public interface TaskService {

    TaskDTO createTask(TaskDTO taskDTO);

    List<TaskDTO> getAllTasks();

    TaskDTO getTaskById(Long id);

    TaskDTO updateTask(Long id, TaskDTO taskDTO);

    void deleteTask(Long id);

    TaskDTO markTaskAsCompleted(Long id);

    List<TaskDTO> getTasksByStatus(TaskStatus status);

    List<TaskDTO> getTasksByPriority(TaskPriority priority);
}
