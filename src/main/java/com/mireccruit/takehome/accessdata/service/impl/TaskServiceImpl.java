package com.mireccruit.takehome.accessdata.service.impl;

import com.mireccruit.takehome.accessdata.domain.Task;
import com.mireccruit.takehome.accessdata.exceptions.TaskNotFoundException;
import com.mireccruit.takehome.accessdata.repository.TaskRepository;
import com.mireccruit.takehome.accessdata.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public Task getTaskByID(long taskID) {
        Task task = taskRepository.findById(taskID)
                .orElseThrow(() -> new TaskNotFoundException(taskID));

        return task;
    }

    public void newTask(Task newTask) {
        taskRepository.save(newTask);
    }

    public void updateTask(Task existingTask, long taskID) {
         Task foundtask = taskRepository.findById(taskID)
                 .orElseThrow(() -> new TaskNotFoundException(taskID));

         foundtask.setName(existingTask.getName());
         foundtask.setDescription(existingTask.getDescription());
         foundtask.setStatus(existingTask.getStatus());

         taskRepository.save(foundtask);

    }

    public void deleteTaskById(long taskID) {
        taskRepository.deleteById(taskID);
    }
}
