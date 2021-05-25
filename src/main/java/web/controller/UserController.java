package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.UserServiceImp;


@Controller
public class UserController {
    private UserServiceImp userServiceImp;

    @Autowired
    public void setUserServiceImp(UserServiceImp userServiceImp) {
        this.userServiceImp = userServiceImp;
    }


    @GetMapping(value = "/")
    public String allUsers(Model model) {
        model.addAttribute("users", userServiceImp.getAllUsers());
        return "index";
    }

    @RequestMapping(value = "/user")
    public String add(@ModelAttribute("user") User user) {
        System.out.println(user);
        userServiceImp.add(user);
        return "redirect:/";
    }

    @GetMapping(value = "/user")
    @ResponseBody
    public User findUser(Long id) {
        System.out.println(id);
        User user = userServiceImp.findUserById(id);
        System.out.println(user);
        return user;
    }

    @PostMapping(value = "/edit")
    public String edit(@ModelAttribute("user") User user) {
        userServiceImp.userEdit(user);
        return "redirect:/";
    }

    @GetMapping(value = "/delete")
    public String delete(@ModelAttribute("user") User user) {
        System.out.println("delete");
        userServiceImp.userDelete(user);
        return "redirect:/";
    }


}