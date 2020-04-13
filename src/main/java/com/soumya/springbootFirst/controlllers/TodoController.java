package com.soumya.springbootFirst.controlllers;

import com.soumya.springbootFirst.model.Todo;
import com.soumya.springbootFirst.services.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class TodoController {
    @Autowired
    TodoService todoService;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat formattedDate = new SimpleDateFormat("dd-MM-yyyy");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(formattedDate, false));
    }

    @RequestMapping(value = "/todo", method = RequestMethod.GET)
    public String getMyTodos(ModelMap model) {
        //@RequestParam String user,
        List<Todo> userTodos = todoService.getMyTodos(getLoggedinUserName());
        model.put("TodosList", userTodos);
        return "todo";
    }


    @RequestMapping(value = "/addTodo", method = RequestMethod.GET)
    public String showTodo(ModelMap model) {
        model.addAttribute(new Todo(0, getLoggedinUserName(), "", new Date(), false));
        return "addTodo";
    }

    @RequestMapping(value = "/updatetodo", method = RequestMethod.GET)
    public String showUpdateTodo(@RequestParam int id, ModelMap model) {
        model.put("todo", todoService.retriveTodo(id));
        return "addTodo";
    }

    @RequestMapping(value = "/updatetodo", method = RequestMethod.POST)
    public String updateUpdateTodo(@Valid Todo todo, BindingResult result, ModelMap model) {
        if (result.hasErrors())
            return "addTodo";
        todo.setUser(getLoggedinUserName());
        todoService.updateTodo(todo);
        return "redirect:todo";
    }

    @RequestMapping(value = "/addTodo", method = RequestMethod.POST)
    public String addmyTodo(@Valid Todo todo, BindingResult result, ModelMap model) throws ParseException {
        // Date formattedDate = new SimpleDateFormat("yyyy-MM-dd").parse(targetDate);
        //System.out.println("My Name:  " + (String) model.get("name"));
        if (result.hasErrors()) {
            return "addTodo";
        }
        todoService.addTodo(getLoggedinUserName(), todo.getDesc(), todo.getTargetDate());
        return "redirect:todo";
    }


    @RequestMapping(value = "/deleteTodo", method = RequestMethod.GET)
    public String removeMyTodo(@RequestParam int id) {
        if (id == 1)
            throw new RuntimeException("something went wrong");
        todoService.deleteTodo(id);
        return "redirect:todo";
    }

    public String getLoggedinUserName() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails)
            return ((UserDetails) principal).getUsername();
        return principal.toString();
    }
}
