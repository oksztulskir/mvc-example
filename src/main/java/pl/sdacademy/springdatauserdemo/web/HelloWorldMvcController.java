package pl.sdacademy.springdatauserdemo.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloWorldMvcController {

    @GetMapping("/hello-world") // GET http://localhost:8080/hello-world
    public String helloWorld(ModelMap model) {
        model.addAttribute("myAttribute", "I love my world!");
        return "hello";
    }

}
