package com.hatio.projects_todos.services;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hatio.projects_todos.entity.Project;
import com.hatio.projects_todos.repository.ProjectRepository;

@Service
public class ProjectServiceImpl implements ProjectService{

	@Autowired
	private ProjectRepository projectRepository;

	public List<Project> getAllProjects() {
		return projectRepository.findAll();
	}

	public Project getProjectById(UUID id) {
		return projectRepository.findById(id).orElseThrow(() -> new RuntimeException("Project not found"));
	}

	public Project createProject(Project project) {
		project.setCreatedDate(new Date());
		return projectRepository.save(project);
	}

	public Project updateProject(UUID id, Project updatedProject) {
		Project project = getProjectById(id);
		project.setTitle(updatedProject.getTitle());
		return projectRepository.save(project);
	}

	public void deleteProject(UUID id) {
		projectRepository.deleteById(id);
	}

	@Override
	public Project findById(UUID projectId) {
	    return projectRepository.findById(projectId).orElseThrow(() -> new RuntimeException("Project not found"));
	}

}
