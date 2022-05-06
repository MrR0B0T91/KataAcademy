package com.kata.boot.service;

import com.kata.boot.model.User;

import java.util.List;

public interface UserService {

    public List<User> findAll();

    public void save(User user);

    public void deleteById(Long id);

    public User findById(Long id);
}
