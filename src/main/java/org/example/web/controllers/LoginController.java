package org.example.web.controllers;


import org.apache.log4j.Logger;
import org.example.app.exceptions.BookShelfLoginException;
import org.example.app.services.LoginService;
import org.example.web.dto.LoginForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/login")
public class LoginController {

    private LoginService loginService;
    private Logger logger = Logger.getLogger(LoginController.class);

    @Autowired
    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @GetMapping
    public String login(Model model) {
        logger.info("welcome to loginForm");
        model.addAttribute("loginForm", new LoginForm());
        return "login_page";
    }

    @PostMapping(value = "/auth")
    public String authenticate(LoginForm loginForm) {
        if (loginService.authenticate(loginForm)) {
            logger.info("login is CORRECT");
            return "redirect:/books/shelf";
        } else {
            logger.info("login is FAIL");
            return new BookShelfLoginException("invalid username or password");
        }
    }
}
