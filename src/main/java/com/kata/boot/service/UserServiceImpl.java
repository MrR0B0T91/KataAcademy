package com.kata.boot.service;

import com.kata.boot.model.User;
import com.kata.boot.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {

        this.userRepository = userRepository;
    }

    public List<User> findAll() {

        return userRepository.findAll();
    }

    public void save(User user) {

        userRepository.save(user);
    }

    public void deleteById(Long id) {

        userRepository.deleteById(id);
    }

    public User findById(Long id) {

        return userRepository.findById(id).orElse(null);
    }
}
