package pl.sdacademy.springdatauserdemo.web;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import pl.sdacademy.springdatauserdemo.model.User;
import pl.sdacademy.springdatauserdemo.service.UserService;

@Controller
@RequiredArgsConstructor
@RequestMapping("/mvc/user")
public class UserMvcController {
    private final UserService service;

    @GetMapping("/{id}") // GET /mvc/user/9
    public String getUser(@PathVariable(name = "id") long id, ModelMap model) {
        User user = service.findById(id);
        model.addAttribute("user", user);
        return "user";
    }

    @GetMapping("/add") // GET /mvc/user/add
    public String add(ModelMap model) {
        model.addAttribute("user", new User());
        model.addAttribute("roles", User.Role.values());
        return "new-user";
    }

    @PostMapping("/add") // POST /mvc/user/add
    public String add(@ModelAttribute User user) {
        service.create(user);
        return "redirect:";
    }

    @GetMapping// GET /mvc/user
    public String list(ModelMap model) {
        model.addAttribute("users", service.findAll());
        return "users";
    }

}
