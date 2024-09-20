package com.hatio.projects_todos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.*;

import com.hatio.projects_todos.entity.Project;
import com.hatio.projects_todos.entity.ToDo;
import com.hatio.projects_todos.entity.TodoStatus;
import com.hatio.projects_todos.repository.ToDoRepository;
import com.hatio.projects_todos.services.ProjectService;
import com.hatio.projects_todos.services.ToDoService;

import java.util.Date;
import java.util.UUID;

@SpringBootApplication
@Controller
@RequestMapping("/todo")
public class TodoController {

	@Autowired
	private ToDoService todoService;

	@PostMapping("/add")
	public String addTodo(@RequestParam UUID projectId, @RequestParam String description) {
		ToDo todo = new ToDo();
		todo.setDescription(description);
		todo.setStatus(TodoStatus.PENDING);
		todoService.addTodoToProject(projectId, todo); // This now correctly links ToDo to Project
		return "redirect:/project/view?id=" + projectId;
	}

	@GetMapping("/updateStatus")
	public String updateTodoStatus(@RequestParam UUID id, @RequestParam TodoStatus status) {
		ToDo todo = todoService.updateTodoStatus(id, status);
		return "redirect:/project/view?id=" + todo.getProject().getId();
	}

	@GetMapping("/delete")
	public String deleteTodo(@RequestParam UUID id) {
		ToDo todo = todoService.deleteTodoById(id);
		return "redirect:/project/view?id=" + todo.getProject().getId();
	}
}
