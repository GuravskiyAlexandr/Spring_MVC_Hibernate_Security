package web.controller;


import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import web.model.User;

@Controller
public class UserController {

    @GetMapping("/user")
    public String getUser(@AuthenticationPrincipal User user,  Model model){
        model.addAttribute("user", user);
        return "user";
    }
}
