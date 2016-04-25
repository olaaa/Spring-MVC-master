package ru.javacourse.spring.controller;

import ru.javacourse.spring.entity.User;
import ru.javacourse.spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Author: Georgy Gobozov
 * Date: 11.04.13
 */
@Controller
//можно использоватт аннотацию @RestController
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping(method = RequestMethod.GET, value = "/users")
    public @ResponseBody List<User> getAllUsers(ModelMap model) {
        List<User> users = userService.getAll();
// возвращается json
//        @ResponseBody говорит Спринг, что возвращаемый оъект должен записаться в response
        return users;
    }

//@PathVariable("id") здесь будет строка, и это значение будет принимать userId
    @RequestMapping(method = RequestMethod.GET, value = "/users/{id}")
    public  @ResponseBody User getUser(@PathVariable("id") String userId, ModelMap model) {
        // возвращается json, так как у нас проаннотированы jaxb в пакете entity!
        return userService.getById(Long.parseLong(userId));
    }

}
