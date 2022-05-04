package crud.service;

import crud.dao.UserDaoImpl;
import crud.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserDaoImpl userDao;

    public UserServiceImpl(UserDaoImpl userDao) {

        this.userDao = userDao;
    }

    @Override
    public List<User> getAllUsers() {

        return userDao.getAllUsers();
    }

    @Override
    public void add(User user) {

        userDao.add(user);
    }

    @Override
    public void remove(Long id) {

        userDao.remove(id);
    }

    @Override
    public void update(Long id, User user) {

        userDao.update(id, user);
    }

    public User show(Long id) {

        return userDao.show(id);
    }
}
