package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    Util util = new Util();

    private final String QUERY_CREATE_TABLE = "CREATE TABLE IF NOT EXISTS Users (\n" +
            "  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,\n" +
            "  `name` VARCHAR(45) NULL,\n" +
            "  `last_name` VARCHAR(45) NULL,\n" +
            "  `age` SMALLINT(3) NULL,\n" +
            "  PRIMARY KEY (`id`));";
    private final String QUERY_DROP_TABLE = "DROP TABLE IF EXISTS Users;";
    private final String QUERY_SAVE_USER = "INSERT INTO Users (`name`, `last_name`, `age`) VALUES (?,?,?)";
    private final String QUERY_REMOVE_USER = "DELETE FROM Users WHERE ID = ?";
    private final String QUERY_GET_USERS = "SELECT id,name,last_name,age FROM Users";
    private final String QUERY_CLEAN_TABLE = "TRUNCATE TABLE Users";


    public UserDaoJDBCImpl() {

    }


    public void createUsersTable() {
        try (Connection connection = util.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(QUERY_CREATE_TABLE)) {

            preparedStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        try (Connection connection = util.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(QUERY_DROP_TABLE)) {

            preparedStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try (Connection connection = util.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(QUERY_SAVE_USER)) {

            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);

            preparedStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        try (Connection connection = util.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(QUERY_REMOVE_USER)) {

            preparedStatement.setLong(1, id);
            preparedStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        try (Connection connection = util.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(QUERY_GET_USERS)) {

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                User user = new User();
                user.setId(rs.getLong("id"));
                user.setName(rs.getString("name"));
                user.setLastName(rs.getString("last_name"));
                user.setAge(rs.getByte("age"));

                userList.add(user);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }

    public void cleanUsersTable() {
        try (Connection connection = util.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(QUERY_CLEAN_TABLE)) {

            preparedStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
