package com.hatio.projects_todos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hatio.projects_todos.entity.User;
import com.hatio.projects_todos.services.UserService;


@Controller
public class LoginController {

	@Autowired
    private UserService userService;

    @GetMapping("/login")
    public String loginPage() {
        return "index"; // JSP view name
    }

    @PostMapping("/login")
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        Model model) {
        System.out.println("Login attempt for username: " + username);
        User user = userService.findByUsername(username);
        if (user != null) {
            System.out.println("User found: " + user.getUsername());
            if (user.getPassword().equals(password)) {
                return "redirect:/project/listProjects"; // Redirect to the project list
            } else {
                System.out.println("Invalid password for user: " + username);
                model.addAttribute("error", "Invalid username or password");
            }
        } else {
            System.out.println("No user found with username: " + username);
            model.addAttribute("error", "Invalid username or password");
        }
        return "index"; // Return to the login page with an error message
    }

}

