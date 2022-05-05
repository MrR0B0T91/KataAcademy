package crud.service;

import crud.model.User;

import java.util.List;

public interface UserService {

    List<User> getAllUsers();

    void add(User user);

    void remove(Long id);

    void update(Long id, User user);

    User show(Long id);
}
