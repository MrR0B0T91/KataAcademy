package ru.kata.spring.boot_security.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.security.Principal;
import java.util.List;

@Controller
public class AppController {

    private final UserService userService;

    public AppController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/admin")
    public String admin() {

        return "admin";
    }

    @GetMapping(value = "/user")
    public String userInfo(ModelMap model, Principal principal) {
        User user = userService.findByUsername(principal.getName());
        model.addAttribute("user", user);
        model.addAttribute("roles", user.getRoles());
        return "user";
    }

    @PostMapping("/admin/create")
    public String create(@ModelAttribute("user") User user, @RequestParam("role") List<String> roles) {
        userService.save(user);
        return "redirect:/admin";
    }

    @PatchMapping("/admin/{id}/edit")
    public String update(@ModelAttribute("user") User user, @PathVariable("id") Long id,
                             @RequestParam(value = "role", required = false) List<String> roles) {
        userService.update(id, user);
        return "redirect:/admin";
    }

    @GetMapping("/admin/{id}/delete")
    public String delete(@PathVariable("id") Long id) {
        userService.deleteById(id);
        return "redirect:/admin";
    }
}
