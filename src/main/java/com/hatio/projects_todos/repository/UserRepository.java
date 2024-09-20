package com.hatio.projects_todos.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.hatio.projects_todos.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
