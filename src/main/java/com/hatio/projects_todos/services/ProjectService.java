package com.hatio.projects_todos.services;

import java.util.List;
import java.util.UUID;

import com.hatio.projects_todos.entity.Project;

public interface ProjectService {
	List<Project> getAllProjects();
	Project getProjectById(UUID id);
	Project createProject(Project project);
	Project updateProject(UUID id, Project updatedProject);
	void deleteProject(UUID id);
	Project findById(UUID id);
}
