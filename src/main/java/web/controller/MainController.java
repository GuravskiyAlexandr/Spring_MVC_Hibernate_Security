package web.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import web.model.User;

@Controller
public class MainController {


    @GetMapping("/")
    public String mainPage(Model model, @AuthenticationPrincipal User user) {
        if (user != null) {
            model.addAttribute("hello", "Hello user " + user.getFirstName());
        }
        return "index";
    }
}
