package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class UserDaoJDBCImpl implements UserDao {


    public UserDaoJDBCImpl() {
    }

    public void createUsersTable() {
        String SQL = "CREATE TABLE IF NOT EXISTS User (" + "id INT NOT NULL AUTO_INCREMENT , " +
                "name VARCHAR(255), " + "lastname VARCHAR(255), " + "age INT, " + "PRIMARY KEY (id)" + ")";
        try (Connection conn = Util.getConnection(); PreparedStatement stmt = conn.prepareStatement(SQL)) {
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void dropUsersTable() {
        String DROP_USERS_SQL = "DROP TABLE IF EXISTS User;";
        try (Connection conn = Util.getConnection(); PreparedStatement stmt = conn.prepareStatement(DROP_USERS_SQL)) {
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String INSERT_USERS_SQL = "INSERT INTO User (name, lastName,  age) VALUES (?, ?, ?);";
        try (Connection conn = Util.getConnection(); PreparedStatement stmt = conn.prepareStatement(INSERT_USERS_SQL)) {
            stmt.setString(1, name);
            stmt.setString(2, lastName);
            stmt.setInt(3, age);
            stmt.executeUpdate();
            System.out.println("User с именем " + name + " добавлен в базу данных");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void removeUserById(long id) {
        String DELETE_USERS_SQL = "DELETE FROM User WHERE id = ?;";
        try (Connection conn = Util.getConnection(); PreparedStatement stmt = conn.prepareStatement(DELETE_USERS_SQL)) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public List<User> getAllUsers() {
        String SELECT_ALL_USERS_SQL = "SELECT * FROM User";
        List<User> users = new ArrayList<>();
        try (Connection conn = Util.getConnection(); PreparedStatement stmt = conn.prepareStatement(SELECT_ALL_USERS_SQL)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getLong("id"));
                user.setName(rs.getString("name"));
                user.setLastName(rs.getString("lastName"));
                user.setAge(rs.getByte("age"));
                users.add(user);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return users;
    }
    public void cleanUsersTable() {
        String CLEAN_USERS_SQL = "TRUNCATE TABLE User;";
        try (Connection conn = Util.getConnection(); PreparedStatement stmt = conn.prepareStatement(CLEAN_USERS_SQL)) {
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
