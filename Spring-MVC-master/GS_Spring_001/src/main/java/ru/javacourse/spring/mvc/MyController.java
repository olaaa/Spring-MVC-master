package ru.javacourse.spring.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

// Controller обозначаются сервлеты
@Controller
// над классом может и не быть. Конкатенируется с путями до методов
@RequestMapping("/spring")
public class MyController {

//    ModelMap объект, в который кладём данные, которые должны уйти на вью
    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String sayHello(ModelMap model) {
        model.addAttribute("message", "Spring 3 MVC Hello World");
        // страница, на которую надо перейти.
        return "hello";
    }

    @RequestMapping(value = "/bye" ,method = RequestMethod.GET)
     public String sayBye(ModelMap model) {
        model.addAttribute("message", "Spring 3 MVC bye-bye!");
        return "bye";
    }

    // supported method argument type
    @RequestMapping(value = {"/hello2", "/hello3"} ,method = {RequestMethod.GET, RequestMethod.POST},
            headers = "User-Agent: Android")
    public ModelAndView sayHello2(ModelMap model) {
        model.addAttribute("message", "Spring 3 MVC Hello World 2");
        // разницы нет, возвращать стринг или ModelAndView
        return new ModelAndView("hello", model);
    }

}
