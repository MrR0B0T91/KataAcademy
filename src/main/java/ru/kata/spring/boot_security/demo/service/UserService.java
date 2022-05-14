package ru.kata.spring.boot_security.demo.service;

import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserService {

    List<User> findAll();

    void save(User user);

    void deleteById(Long id);

    void update(Long id, User user);

    User findById(Long id);

    User findByUsername(String username);
}
