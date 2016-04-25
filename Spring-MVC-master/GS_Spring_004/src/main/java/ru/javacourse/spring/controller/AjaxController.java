package ru.javacourse.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;
import java.util.List;

/**
 * Author: Georgy Gobozov
 * Date: 11.04.13
 */
@Controller
public class AjaxController {

    @RequestMapping(value = "/ajax", method = RequestMethod.GET)
    public String getAjaxPage() {
        return "index";
    }


    @RequestMapping(value = "/helloajax", method = RequestMethod.GET)
    public @ResponseBody String sayHello() {
        return "Hello Ajax!";
    }

    @RequestMapping(value = "/plus", method = RequestMethod.GET)
    public @ResponseBody  String plus(@RequestParam String d1, @RequestParam String d2, HttpServletResponse response) {
        return String.valueOf(Integer.parseInt(d1) + Integer.parseInt(d2));
    }

    @RequestMapping(value = "/plus2", method = RequestMethod.GET)
    public void plus2(@RequestParam String d1, @RequestParam String d2, HttpServletResponse response) throws IOException {
        Integer result = Integer.parseInt(d1) + Integer.parseInt(d2);
        System.out.println("result = " + result);
        response.setContentType("text/plain");
        Writer writer = response.getWriter();
        writer.write(result + "");
        writer.flush();
        writer.close();
    }


}
