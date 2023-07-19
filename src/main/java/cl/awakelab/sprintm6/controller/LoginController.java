package cl.awakelab.sprintm6.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {

    @RequestMapping("/login")
    @GetMapping
    public String login(Model model) {
        return "login";
    }

    @PostMapping("/bienvenida")
    public String logedin() {
        // Variables temporales hasta aprender security
        return "bienvenida";
    }

    @GetMapping("/inicio")
    public String sesionIniciada() {
        return "bienvenida";
    }
}
