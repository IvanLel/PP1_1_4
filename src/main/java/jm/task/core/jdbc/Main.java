package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();

        userService.createUsersTable();

        userService.saveUser("Kira", "Knightly", (byte) 38);
        userService.saveUser("Kianu", "Reevs", (byte) 45);
        userService.saveUser("Gnom", "Gnomich", (byte) 10);
        userService.saveUser("Pablo", "Escobar", (byte) 35);

        List<User> userList = userService.getAllUsers();
        userList.forEach(System.out::println);

        userService.cleanUsersTable();
        userService.dropUsersTable();

    }
}
