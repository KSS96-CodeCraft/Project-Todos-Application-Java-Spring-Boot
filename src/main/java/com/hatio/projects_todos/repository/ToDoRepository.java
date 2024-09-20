package com.hatio.projects_todos.repository;

import java.util.UUID;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.JpaRepository;

import com.hatio.projects_todos.entity.ToDo;
@SpringBootApplication
public interface ToDoRepository extends JpaRepository<ToDo, UUID> {
}


