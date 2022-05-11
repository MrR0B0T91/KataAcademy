package ru.kata.spring.boot_security.demo.service;

import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserService {

    List<User> findAll();

    void save(User user, List<String> roles);

    void deleteById(Long id);

    void update(Long id, User user, List<String> roles);

    User findById(Long id);

    User findByUsername(String username);

    List<Role> getAllRoles();
}
