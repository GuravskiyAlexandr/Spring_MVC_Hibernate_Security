package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import web.model.User;
import web.service.UserServiceImp;

import java.util.List;

@Controller
public class UserController {

    private UserServiceImp userServiceImp;

    @Autowired
    public void setUserServiceImp(UserServiceImp userServiceImp) {
        this.userServiceImp = userServiceImp;
    }


    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView allUsers() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        List<User> users = userServiceImp.getAllUsers();
        modelAndView.addObject("usersList", users);
        return modelAndView;
    }

    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public ModelAndView add(@ModelAttribute("user") User user) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/");
        userServiceImp.add(user);
        return modelAndView;
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public ModelAndView edit(@ModelAttribute("user") User user) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/");
        System.out.println(user);
        userServiceImp.userEdit(user);
        return modelAndView;
    }
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public ModelAndView delete(@ModelAttribute("user") User user) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/");
        System.out.println(user);
        System.out.println("delete");
        userServiceImp.userDelete(user);
        return modelAndView;
    }


}