package ru.kata.spring.boot_security.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AppController {

    @GetMapping(value = "/admin")
    public String admin() {

        return "admin";
    }

    @GetMapping(value = "/user")
    public String user() {

        return "user";
    }
}
