package web.service;

import web.model.User;

import java.util.List;

public interface UserServiceDao {
    void add(User user);

    List<User> getAllUsers();

    void userEdit(User user);

    void userDelete(User user);
}
