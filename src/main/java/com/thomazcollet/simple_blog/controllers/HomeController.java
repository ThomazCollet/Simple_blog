package com.thomazcollet.simple_blog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;



@Controller
public class HomeController {

    @GetMapping("/index")
    public String home() {
        return "index"; // Retorna o arquivo "index.html" da pasta templates
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/singup")
    public String singUp() {
        return "singup";
    }

    @GetMapping("/forgotPassword")
    public String forgotPassword(){
        return "forgotPassword";
    }
    
}

