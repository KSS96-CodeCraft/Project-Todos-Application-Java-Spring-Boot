package com.hatio.projects_todos.services;

import com.hatio.projects_todos.entity.User;

public interface UserService {
    User findByUsername(String username);
}
