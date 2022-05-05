package crud.service;

import crud.dao.UserDao;
import crud.model.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserDao userDao;

    public UserServiceImpl(UserDao userDao) {

        this.userDao = userDao;
    }

    @Override
    public List<User> getAllUsers() {

        return userDao.getAllUsers();
    }

    @Override
    @Transactional
    public void add(User user) {

        userDao.add(user);
    }

    @Override
    @Transactional
    public void remove(Long id) {

        userDao.remove(id);
    }

    @Override
    @Transactional
    public void update(Long id, User user) {

        userDao.update(id, user);
    }

    public User show(Long id) {

        return userDao.show(id);
    }
}
