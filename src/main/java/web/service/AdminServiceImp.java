package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import web.dao.AdminrDao;
import web.model.Role;
import web.model.User;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdminServiceImp implements AdminServiceDao {
    private final RoleServiceDao roleServiceDao;
    private final AdminrDao userDao;
    private final PasswordEncoder passwordEncoder;

    public AdminServiceImp(AdminrDao userDao, RoleServiceDao roleServiceDao, PasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.roleServiceDao = roleServiceDao;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void add(User user) {
        setRolesUser(user);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userDao.add(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    @Override
    public void userEdit(User user) {
        setRolesUser(user);
        user.setPassword(passwordEncoder.encode(user.getPasswordNew()));
        userDao.userEdit(user);
    }

    @Override
    public void userDelete(User user) {
        userDao.userDelete(user);
    }

    @Override
    public User findUserById(Long id) {
        return userDao.findUserById(id);
    }

    // «Пользователь» – это просто Object. В большинстве случаев он может быть
    //  приведен к классу UserDetails.
    // Для создания UserDetails используется интерфейс UserDetailsService, с единственным методом:
    @Override
    public UserDetails loadUserByUsername(String firstName) throws UsernameNotFoundException {
        return userDao.getUserByUsername(firstName);
    }

    @Override
    public boolean checkNewPassword(User newUser, User userFromDb) {
        return passwordEncoder.matches(newUser.getPassword(), userFromDb.getPassword());
    }

    private void setRolesUser(User user) {
        user.setRoles(roleServiceDao
                .getRoles(
                        user.getRoles().stream()
                                .map(Role::getRole).collect(Collectors.toList())));

    }
}
