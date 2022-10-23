package com.mireccruit.takehome.accessdata.repository;

import com.mireccruit.takehome.accessdata.domain.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    @Query(value = "select task from Task AS task where Task.id = :taskID", nativeQuery = true)
    Optional<Task> findTaskByID(long taskID);

    @Modifying
    @Query(value = "delete from Task where Task.id = :taskID", nativeQuery = true)
    Optional<Task> deleteTaskById(long taskID);
}
