package ru.kata.spring.boot_security.demo.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.repository.RoleRepository;
import ru.kata.spring.boot_security.demo.repository.UserRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserServiceImpl implements UserDetailsService, UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder bCryptPasswordEncoder;
    private final RoleRepository roleRepository;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder bCryptPasswordEncoder, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.roleRepository = roleRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        return user;
    }

    @Override
    public List<User> findAll() {

        return userRepository.findAll();
    }

    @Override
    public void save(User user, List<String> roles) {

        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setUsername(user.getUsername());
        user.setAge(user.getAge());
        user.setRoles(toSetRoles(roles));

        userRepository.save(user);
    }

    @Override
    public void deleteById(Long id) {

        if (userRepository.findById(id).isPresent()) {
            userRepository.deleteById(id);
        }
    }

    @Override
    public void update(Long id, User user, List<String> roles) {

        Optional<User> optionalUser = userRepository.findById(id);

        if (optionalUser.isPresent()) {
            User updatedUser = optionalUser.get();

            updatedUser.setUsername(user.getUsername());
            updatedUser.setName(user.getName());
            updatedUser.setSurname(user.getSurname());
            updatedUser.setAge(user.getAge());

            if (roles != null) {
                updatedUser.setRoles(toSetRoles(roles));
            }

            if (!user.getPassword().equals(updatedUser.getPassword())) {
                updatedUser.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            } else {
                updatedUser.setPassword(user.getPassword());
            }
            userRepository.save(updatedUser);
        }
    }

    @Override
    public User findById(Long id) {

        return userRepository.findById(id).orElse(null);
    }

    @Override
    public User findByUsername(String username) {

        return userRepository.findByUsername(username);
    }

    @Override
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    private Set<Role> toSetRoles(List<String> roles) {

        Set<Role> roleSet = new HashSet<>();
        for (String i : roles) {
            roleSet.add(roleRepository.getById(Long.parseLong(i)));
        }

        return roleSet;
    }
}
