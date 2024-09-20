package com.hatio.projects_todos.services;

import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hatio.projects_todos.entity.Project;
import com.hatio.projects_todos.entity.ToDo;
import com.hatio.projects_todos.entity.TodoStatus;
import com.hatio.projects_todos.repository.ProjectRepository;
import com.hatio.projects_todos.repository.ToDoRepository;

@Service
public class ToDoService {

	@Autowired
	private ToDoRepository todoRepository;
	@Autowired
    private ProjectService projectService; // Make sure to inject this

    public ToDo addTodoToProject(UUID projectId, ToDo todo) {
        Project project = projectService.findById(projectId); // Retrieve the project
        todo.setProject(project); // Link ToDo to the Project
        todo.setCreatedDate(new Date()); // Set created date
        return todoRepository.save(todo); // Save the ToDo
    }

	public ToDo updateTodoStatus(UUID id, TodoStatus status) {
		ToDo todo = todoRepository.findById(id).orElseThrow(() -> new RuntimeException("Todo not found"));
		todo.setStatus(status);
		todo.setUpdatedDate(new Date());
		return todoRepository.save(todo);
	}

	public ToDo deleteTodoById(UUID id) {
		todoRepository.deleteById(id);
		return null;
	}
}
