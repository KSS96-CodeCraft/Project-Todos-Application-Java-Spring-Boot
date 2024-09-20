package com.hatio.projects_todos.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.hatio.projects_todos.entity.Project;
import com.hatio.projects_todos.entity.TodoStatus;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import java.util.HashMap;
import java.util.Map;
@SpringBootApplication
@Service
public class GistService {

	@Value("${github.token}")
	private String githubToken;

	public String exportProjectToGist(Project project) {
		String gistContent = generateGistContent(project);

		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set("Authorization", "token " + githubToken);

		Map<String, Object> gistMap = new HashMap<>();
		Map<String, String> fileContent = new HashMap<>();
		fileContent.put("content", gistContent);
		Map<String, Object> files = new HashMap<>();
		files.put(project.getTitle() + ".md", fileContent);
		gistMap.put("files", files);
		gistMap.put("description", "Project Summary for " + project.getTitle());
		gistMap.put("public", false); // Secret gist

		HttpEntity<Map<String, Object>> request = new HttpEntity<>(gistMap, headers);
		@SuppressWarnings("unchecked")
		Map<String, Object> response = restTemplate.postForObject("https://api.github.com/gists", request, Map.class);

		return (String) response.get("html_url");
	}

	private String generateGistContent(Project project) {
	    StringBuilder content = new StringBuilder("# " + project.getTitle() + "\n\n");
	    long completedCount = project.getTodos().stream().filter(todo -> todo.getStatus() == TodoStatus.COMPLETED).count();
	    content.append("Summary: ").append(completedCount).append(" / ").append(project.getTodos().size()).append(" completed.\n\n");

	    content.append("## Pending Todos:\n");
	    project.getTodos().stream().filter(todo -> todo.getStatus() == TodoStatus.PENDING)
	            .forEach(todo -> content.append("- [ ] ").append(todo.getDescription()).append("\n"));

	    content.append("\n## Completed Todos:\n");
	    project.getTodos().stream().filter(todo -> todo.getStatus() == TodoStatus.COMPLETED)
	            .forEach(todo -> content.append("- [x] ").append(todo.getDescription()).append("\n"));

	    return content.toString();
	}

}
