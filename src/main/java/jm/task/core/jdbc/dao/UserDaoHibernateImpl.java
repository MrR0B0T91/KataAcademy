package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    private List<User> list = new ArrayList<>();

    public UserDaoHibernateImpl() {

    }

    @Override
    public void createUsersTable() {

        Session session = Util.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        String SQL = "CREATE TABLE IF NOT EXISTS user " +
                "(id BIGINT not NULL AUTO_INCREMENT, " +
                " name VARCHAR(50), " +
                " last_name VARCHAR (50), " +
                " age TINYINT not NULL, " +
                " PRIMARY KEY (id))";

        session.createSQLQuery(SQL).addEntity(User.class).executeUpdate();

        transaction.commit();
        session.close();
    }

    @Override
    public void dropUsersTable() {

        Session session = Util.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        String SQL = "DROP TABLE IF EXISTS user";

        session.createSQLQuery(SQL).addEntity(User.class).executeUpdate();

        transaction.commit();
        session.close();
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {

        Session session = Util.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        User user = new User();

        user.setName(name);
        user.setLastName(lastName);
        user.setAge(age);

        session.save(user);

        transaction.commit();
        session.close();
    }

    @Override
    public void removeUserById(long id) {

        Session session = Util.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        User user = session.load(User.class, id);
        session.delete(user);

        transaction.commit();
        session.close();
    }

    @Override
    public List<User> getAllUsers() {

        Session session = Util.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        list = session.createQuery("FROM User").list();

        transaction.commit();
        session.close();

        return list;
    }

    @Override
    public void cleanUsersTable() {

        Session session = Util.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        String SQL = "DELETE FROM User";
        session.createQuery(SQL).executeUpdate();

        session.createSQLQuery(SQL).addEntity(User.class).executeUpdate();

        transaction.commit();
        session.close();
    }
}