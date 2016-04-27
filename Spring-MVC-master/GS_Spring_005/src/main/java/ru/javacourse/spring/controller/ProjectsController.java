package ru.javacourse.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.javacourse.spring.dao.AbstractDao;
import ru.javacourse.spring.model.Project;
import ru.javacourse.spring.validation.ProjectValidator;

import java.util.List;

@Controller
public class ProjectsController {

    @Autowired
    @Qualifier(value = "projectDao") // так как у нас несколько реализаций
    private AbstractDao projectDao;

    @Autowired
    private ProjectValidator projectValidator;


    @RequestMapping("/")
    public String foo() {
        return "redirect:/projects";
    }

    // для каждого запроса будет добавлять в модель по ключу "projects", значение — то, что возвращается
    @ModelAttribute("projects")
    public List<Project> getAllProjects() {
        return projectDao.findAll();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/projects")
    public String get(Model model) {
        model.addAttribute("project", new Project());
        return "projects";
    }

    /**
     * после выполнения Пост запросов, а также в урлах подобных "/projects/{action}/{id}", надо делать редирект,
     * чтобы параметры запроса очищались!
     */
    @RequestMapping(method = RequestMethod.GET, value = "/projects/{action}/{id}")
    public String handleAction(@PathVariable Integer id, @PathVariable String action, Model model) {
        Project project = (Project) projectDao.getById(id);
        if (action.equalsIgnoreCase("edit")) {
            model.addAttribute("project", project);
            return "projects";
        } else if (action.equalsIgnoreCase("delete")) {
            projectDao.delete(project);
        }
        return "redirect:/projects";

    }

    // атрибута value нет. всё, что приходит с методом Пост, отправляется в этот метод
    @RequestMapping(method = RequestMethod.POST)
    // @ModelAttribute("project") Project project
    // нужно для того, чтобы спиринг взял поля с формы и слепил из них объект Project
    // форма дб объявлена так
    // <form:form commandName="project" method="POST" >
    public String add(@ModelAttribute("project") Project project, BindingResult result) {
        projectValidator.validate(project, result);
        if (result.hasErrors())
            return "projects";

        projectDao.update(project);
        return "redirect:/projects";

    }


}
