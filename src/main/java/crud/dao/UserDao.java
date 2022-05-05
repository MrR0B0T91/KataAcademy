package crud.dao;

import crud.model.User;

import java.util.List;

public interface UserDao {

    List<User> getAllUsers();

    void add(User user);

    void remove(Long id);

    void update(Long id, User user);

    User show(Long id);
}
