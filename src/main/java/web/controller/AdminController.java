package web.controller;

import com.google.common.collect.Sets;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.model.Role;
import web.model.User;
import web.service.AdminServiceDao;


@Controller
@PreAuthorize("hasAuthority('ROLE_ADMIN')")
public class AdminController {
    private final AdminServiceDao userServiceDao;

    public AdminController(AdminServiceDao userServiceDao) {
        this.userServiceDao = userServiceDao;
    }


    @GetMapping(value = "/admin")
    public String allUsers(Model model) {
        model.addAttribute("myUsersList", userServiceDao.getAllUsers());
        model.addAttribute("user", new User(Sets.newHashSet(new Role("ROLE_USER"), new Role("ROLE_ADMIN"))));
        return "admin";
    }

    @PostMapping("/admin/userAdd")
    public String addNewUser(@ModelAttribute("user") User user) {
        userServiceDao.add(user);
        return "redirect:/admin";
    }

    @PostMapping(value = "/admin/edit")
    public String editUser(@ModelAttribute("user") User newUser, Model model) {
        User oldUser = userServiceDao.findUserById(newUser.getId());
        if (!userServiceDao.checkNewPassword(newUser, oldUser)) {
            newUser.setRoles(oldUser.getRoles());
            model.addAttribute("user", newUser);
            model.addAttribute("passwordOld", "Старый пароль не совпадают");
            return "/edit";
        }
        userServiceDao.userEdit(newUser);
        return "redirect:/admin";
    }

    @GetMapping(value = "/edit")
    public String setForm(@RequestParam("id") Long id, Model model) {
        model.addAttribute("user", userServiceDao.findUserById(id));
        model.addAttribute("userRoles", Sets.newHashSet(new Role(2L,"ROLE_USER"), new Role(1L,"ROLE_ADMIN")));
        return "edit";
    }

    @GetMapping(value = "/admin/delete")
    public String delete(@RequestParam("id") Long id) {
        userServiceDao.userDelete(userServiceDao.findUserById(id));
        return "redirect:/admin";
    }


}