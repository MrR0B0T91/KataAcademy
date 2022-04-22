package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.util.Util;

public class Main {
    public static void main(String[] args) throws Exception {
        // реализуйте алгоритм здесь

        UserDao userDao = new UserDaoJDBCImpl();
//
//        userDao.createUsersTable();

//        userDao.saveUser("Zaur", "Tregulov", (byte) 30);
//        userDao.saveUser("Ivan", "Ivanov", (byte) 30);
//        userDao.saveUser("Petr", "Petrov", (byte) 30);
//        userDao.saveUser("Maria", "Marieva", (byte) 30);
//        userDao.saveUser("Elena", "Elenova", (byte) 30);

        userDao.cleanUsersTable();

        System.out.println(userDao.getAllUsers());


//        userDao.removeUserById(1);

//        userDao.dropUsersTable();
    }
}
