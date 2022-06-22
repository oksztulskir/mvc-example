package pl.sdacademy.springdatauserdemo.web;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pl.sdacademy.springdatauserdemo.model.User;
import pl.sdacademy.springdatauserdemo.service.UserService;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserRestController {
    private final UserService service;

    @PostMapping
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    public User create(@RequestBody User user) {
        return service.create(user);
    }

    @PutMapping
    @ResponseBody
    public User update(@RequestBody User user) {
        return service.update(user);
    }

    @DeleteMapping("/{id}") // DELETE /user/2
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable(name = "id") long id) {
        service.delete(id);
    }

    @GetMapping
    @ResponseBody
    public List<User> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}") // GET /user/2
    @ResponseBody
    public User findById(@PathVariable(name = "id") long id) {
        return service.findById(id);
    }
}
