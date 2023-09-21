package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();

        userService.dropUsersTable();
        userService.createUsersTable();

        userService.saveUser("te", "te", (byte) 1);
        userService.saveUser("yr", "yr", (byte) 2);
        userService.saveUser("ut", "ut", (byte) 3);
        userService.saveUser("bi", "ib", (byte) 4);

        List<User> userList = userService.getAllUsers();
        System.out.println(userList);

        userService.cleanUsersTable();

    }
}
