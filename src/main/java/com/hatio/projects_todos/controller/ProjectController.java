package com.hatio.projects_todos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.hatio.projects_todos.entity.Project;
import com.hatio.projects_todos.services.GistService;
import com.hatio.projects_todos.services.ProjectService;

import java.util.List;
import java.util.UUID;
@SpringBootApplication
@Controller
@RequestMapping("/project")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @Autowired
    private GistService gistService;

    @GetMapping("/listProjects")
    public String listProjects(Model model) {
        List<Project> projects = projectService.getAllProjects();
        model.addAttribute("projects", projects);
        return "listProjects";
    }

    @PostMapping("/createProject")
    public String createProject(@RequestParam String title) {
        Project project = new Project();
        project.setTitle(title);
        projectService.createProject(project);
        return "redirect:/project/listProjects";
    }

    @GetMapping("/view")
    public String viewProject(@RequestParam UUID id, Model model) {
        Project project = projectService.getProjectById(id);
        model.addAttribute("project", project);
        return "viewProject";
    }

    @PostMapping("/exportGist")
    public String exportGist(@RequestParam UUID projectId, Model model) {
    	Project project = projectService.getProjectById(projectId); // Fetch project details
    	String gistUrl = gistService.exportProjectToGist(project); // Export to Gist
        model.addAttribute("gistUrl", gistUrl);
        model.addAttribute("project", project); 
        return "redirect:/project/view?id=" + projectId;
    }
}
