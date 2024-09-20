package com.hatio.projects_todos.repository;


import java.util.UUID;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.JpaRepository;

import com.hatio.projects_todos.entity.Project;
@SpringBootApplication
public interface ProjectRepository extends JpaRepository<Project, UUID> {
}

