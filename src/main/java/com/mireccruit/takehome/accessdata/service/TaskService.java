package com.mireccruit.takehome.accessdata.service;


import com.mireccruit.takehome.accessdata.domain.Task;

import java.util.List;
import java.util.Optional;

public interface TaskService {

    public List<Task> getAllTasks();
    public Task getTaskByID(long id);
    public void deleteTaskById(long id);
    public void newTask(Task newTask);
    public void updateTask(Task existingTask, long id);

}
