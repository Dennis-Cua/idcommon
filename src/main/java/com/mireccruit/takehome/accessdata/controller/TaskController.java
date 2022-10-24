package com.mireccruit.takehome.accessdata.controller;

import com.mireccruit.takehome.accessdata.domain.Task;
import com.mireccruit.takehome.accessdata.exceptions.NoTaskAvailableException;
import com.mireccruit.takehome.accessdata.service.TaskService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class TaskController {

    @Autowired
    private TaskService taskService;

    @GetMapping(value = "/GetTaskList")
    public List<Task> getTaskList() {
        List<Task> taskLists = taskService.getAllTasks();
        if  (taskLists.isEmpty())
            throw new NoTaskAvailableException();

        return taskLists;
    }

    @GetMapping(value = "/GetTask/{id}")
    public Task getTask(@PathVariable Long id) {
        return taskService.getTaskByID(id);

    }

    @PutMapping(value = "/newTask")
    public ResponseEntity<Task> newTask(@RequestBody Task newTask) {
        taskService.newTask(newTask);

        return new ResponseEntity<Task>(newTask, HttpStatus.CREATED);
    }

    @PostMapping("/updateTask/{id}")
    public ResponseEntity<Task> updateTask(@RequestBody Task existingTask, @PathVariable Long id) {
        taskService.updateTask(existingTask, id);

        return new ResponseEntity<Task>(existingTask, HttpStatus.OK);
    }

    @DeleteMapping("/deleteTask/{id}")
    public ResponseEntity<HttpStatus> deleteTask(@PathVariable Long id) {
        taskService.deleteTaskById(id);

        return new ResponseEntity<HttpStatus>(HttpStatus.ACCEPTED);
    }


}
