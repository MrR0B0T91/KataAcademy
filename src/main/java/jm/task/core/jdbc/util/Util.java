package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class Util {
    // реализуйте настройку соеденения с БД
    private static final String url = "jdbc:mysql://localhost:3306/katajdbc";
    private static final String user = "root";
    private static final String pass = "testtest";

    public static Connection getConnection() throws Exception {

        return DriverManager.getConnection(url, user, pass);
    }

}
