package jm.task.core.jdbc.util;



import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Util {

    public static Connection getConnection() {
        Connection conn = null;
        String URL = "jdbc:mysql://localhost:3306/users?autoReconnect=true&useSSL=false&useLegacyDatetimeCode=false&serverTimezone=UTC";
        String USER = "root";
        String PASS = "rip2010";
        try {
            conn = DriverManager.getConnection(URL, USER, PASS);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return conn;
    }

    public static SessionFactory getSessionFactory() {
        SessionFactory sf = null;

        try {
            Configuration config = new Configuration();



        } catch (RuntimeException e) {
            return null;
        }
        return sf;
    }




}

