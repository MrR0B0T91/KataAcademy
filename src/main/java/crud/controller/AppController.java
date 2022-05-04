package crud.controller;

import crud.model.User;
import crud.service.UserServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class AppController {

    private final UserServiceImpl userService;

    public AppController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/")
    public String printWelcome(ModelMap model) {
        List<String> messages = new ArrayList<>();
        messages.add("Hello!");
        messages.add("I'm Spring MVC CRUD application");
        messages.add("v1.0");
        model.addAttribute("messages", messages);
        return "index";
    }

    @GetMapping(value = "/users")
    public String getList(ModelMap model) {
        model.addAttribute("users", userService.getAllUsers());
        return "users";
    }

    @GetMapping(value = "/users/new")
    public String newUser(@ModelAttribute("user") User user) {
        return "new";
    }

    @PostMapping(value = "/users")
    public String create(@ModelAttribute("user") User user) {
        userService.add(user);
        return "redirect:/users";
    }

    @GetMapping(value = "/users/{id}/edit")
    public String getUser(@PathVariable("id") Long id, ModelMap model) {
        model.addAttribute("user", userService.show(id));
        return "edit";
    }

    @PatchMapping("/users/{id}")
    public String updateUser(@ModelAttribute("user") User user, @PathVariable("id") Long id) {
        userService.update(id, user);
        return "redirect:/users";
    }

    @DeleteMapping("users/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.remove(id);
        return "redirect:/users";
    }
}
