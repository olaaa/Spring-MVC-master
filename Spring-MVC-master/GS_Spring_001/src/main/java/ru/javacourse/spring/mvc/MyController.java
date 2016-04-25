package ru.javacourse.spring.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Author: Georgy Gobozov
 * Date: 10.04.13
 */
@Controller
@RequestMapping("/spring")
public class MyController {


    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String sayHello(ModelMap model) {
        model.addAttribute("message", "Spring 3 MVC Hello World");
        return "hello";
    }

    @RequestMapping(value = "/bye" ,method = RequestMethod.GET)
     public String sayBye(ModelMap model) {
        model.addAttribute("message", "Spring 3 MVC bye-bye!");
        return "bye";
    }

    @RequestMapping(value = {"/hello2", "/hello3"} ,method = {RequestMethod.GET, RequestMethod.POST},
            headers = "User-Agent: Android")
    public ModelAndView sayHello2(ModelMap model) {
        model.addAttribute("message", "Spring 3 MVC Hello World 2");
        return new ModelAndView("hello", model);
    }

}
