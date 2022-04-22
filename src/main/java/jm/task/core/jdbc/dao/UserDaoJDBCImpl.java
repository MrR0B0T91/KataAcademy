package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    private Integer idCounter = 1;
    private List<User> list = new ArrayList<>();

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {

        try (Connection connection = Util.getConnection(); Statement statement = connection.createStatement()) {

            String SQL = "CREATE TABLE user " +
                    "(id BIGINT not NULL, " +
                    " name VARCHAR(50), " +
                    " last_name VARCHAR (50), " +
                    " age TINYINT not NULL, " +
                    " PRIMARY KEY (id))";

            statement.executeUpdate(SQL);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void dropUsersTable() {

        try (Connection connection = Util.getConnection(); Statement statement = connection.createStatement()) {

            String SQL = "DROP TABLE user";

            statement.executeUpdate(SQL);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void saveUser(String name, String lastName, byte age) {

        try (Connection connection = Util.getConnection(); Statement statement = connection.createStatement()) {

//            StringBuilder builder = new StringBuilder();
//            builder.append(idCounter++)
//                    .append(", ")
//                    .append("'");

            String SQL = "INSERT INTO user VALUES (" + idCounter++ + ", '" + name + "', '" + lastName + "', " + age + ")";

            statement.executeUpdate(SQL);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void removeUserById(long id) {

        try (Connection connection = Util.getConnection(); Statement statement = connection.createStatement()) {

            String SQL = "DELETE FROM user WHERE id = " + id;

            statement.executeUpdate(SQL);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {

        try (Connection connection = Util.getConnection(); Statement statement = connection.createStatement()) {

            String SQL = "SELECT * FROM user";

            ResultSet resultSet = statement.executeQuery(SQL);
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

            String SQL = "DELETE FROM user";

            statement.executeUpdate(SQL);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
