package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    private Long idCounter = 1L;
    private List<User> list = new ArrayList<>();

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {

        try (Connection connection = Util.getConnection(); Statement statement = connection.createStatement()) {

            statement.executeUpdate("CREATE TABLE IF NOT EXISTS user " +
                    "(id BIGINT not NULL, " +
                    " name VARCHAR(50), " +
                    " last_name VARCHAR (50), " +
                    " age TINYINT not NULL, " +
                    " PRIMARY KEY (id))");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void dropUsersTable() {

        try (Connection connection = Util.getConnection(); Statement statement = connection.createStatement()) {

            statement.execute("DROP TABLE IF EXISTS user");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void saveUser(String name, String lastName, byte age) {

        try (Connection connection = Util.getConnection();
             PreparedStatement statement = connection.prepareStatement("INSERT INTO user (id, name, last_name, age) VALUES (?,?,?,?)")) {

            statement.setLong(1, idCounter++);
            statement.setString(2, name);
            statement.setString(3, lastName);
            statement.setByte(4, age);

            statement.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void removeUserById(long id) {

        try (Connection connection = Util.getConnection();
             PreparedStatement statement = connection.prepareStatement("DELETE FROM user WHERE id = ?")) {

            statement.setLong(1, id);

            statement.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {

        try (Connection connection = Util.getConnection(); Statement statement = connection.createStatement()) {

            ResultSet resultSet = statement.executeQuery("SELECT * FROM user");
            while (resultSet.next()) {

                Long id = resultSet.getLong("id");
                String name = resultSet.getString("name");
                String lastName = resultSet.getString("last_name");
                Byte age = resultSet.getByte("age");

                User user = new User(id, name, lastName, age);

                list.add(user);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    public void cleanUsersTable() {

        try (Connection connection = Util.getConnection(); Statement statement = connection.createStatement()) {

            statement.executeUpdate("DELETE FROM user");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
