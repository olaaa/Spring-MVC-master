package ru.javacourse.spring.rest;

import ru.javacourse.spring.entity.User;
import ru.javacourse.spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Author: Georgy Gobozov
 * Date: 11.04.13
 */
@Controller
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping(method = RequestMethod.GET, value = "/users.html")
    public String getAllUsers(ModelMap model) {
        List<User> users = userService.getAll();
        model.put("users", users);
        return "users";
    }


    @RequestMapping(method = RequestMethod.GET, value = "/users/{id}.html")
    public ModelAndView getUser(@PathVariable("id") String userId, ModelMap model) {
        model.put("user", userService.getById(Long.parseLong(userId)));
        return new ModelAndView("user", model);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/users/delete")
    public String deleteUser(@RequestParam("userId") String userId) {
        User user = userService.getById(Long.parseLong(userId));
        userService.delete(user);
        return "redirect:/users.html";
    }

}
