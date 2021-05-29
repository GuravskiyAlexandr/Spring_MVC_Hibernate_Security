package web.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import web.model.User;

import java.util.List;

public interface AdminServiceDao extends UserDetailsService {

    void add(User user);

    List<User> getAllUsers();

    void userDelete(User user);

    User findUserById(Long id);
    boolean checkNewPassword(User newUser, User userFromDb);

    void userEdit(User newUser);
}
