package org.example.app.services;

import org.example.web.dto.LoginForm;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    public boolean authenticate(LoginForm loginForm) {
        return loginForm.getUsername().equals("root") && loginForm.getPassword().equals("123");
    }
}
