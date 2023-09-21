package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    Util util = new Util();



    public UserDaoJDBCImpl() {

    }


    public void createUsersTable() {
        Statement statement = null;
        try (Connection connection = util.getConnection()) {
            statement = connection.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS `My_scheme`.`Users` (\n" +
                    "  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,\n" +
                    "  `name` VARCHAR(45) NULL,\n" +
                    "  `last_name` VARCHAR(45) NULL,\n" +
                    "  `age` SMALLINT(3) NULL,\n" +
                    "  PRIMARY KEY (`id`));";

            statement.execute(sql);


        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void dropUsersTable() {
        Statement statement = null;
        try (Connection connection = util.getConnection()) {
            statement = connection.createStatement();
            String sql = "DROP TABLE IF EXISTS `My_scheme`.`Users`;";

            statement.execute(sql);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        PreparedStatement preparedStatement = null;
        try (Connection connection = util.getConnection()) {
            String sql = "INSERT INTO `My_scheme`.`Users` (`name`, `last_name`, `age`) VALUES (?,?,?)";
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);

            preparedStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void removeUserById(long id) {
        PreparedStatement preparedStatement = null;
        try (Connection connection = util.getConnection()) {
            String sql = "DELETE FROM `My_scheme`.`Users` WHERE ID = ?";
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setLong(1, id);
            preparedStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        Statement statement = null;

        try (Connection connection = util.getConnection()) {
            statement = connection.createStatement();
            String sql = "SELECT id,name,last_name,age FROM My_scheme.Users;";

            ResultSet rs = statement.executeQuery(sql);

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
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return userList;
    }

    public void cleanUsersTable() {
        Statement statement = null;
        try (Connection connection = util.getConnection()) {

            statement = connection.createStatement();
            String sql = "TRUNCATE TABLE My_scheme.Users;";

            statement.execute(sql);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
