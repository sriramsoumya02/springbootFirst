package com.soumya.springbootFirst.controlllers;

import com.soumya.springbootFirst.model.Todo;
import com.soumya.springbootFirst.services.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@SessionAttributes("name")
public class TodoController {
    @Autowired
    TodoService todoService;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat formattedDate = new SimpleDateFormat("dd-MM-yyyy");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(formattedDate, false));
    }

    @RequestMapping(value = "/welcome", method = RequestMethod.GET)
    public String getMyTodos(ModelMap model) {
        //@RequestParam String user,
        List<Todo> userTodos = todoService.getMyTodos(getName(model));
        model.put("TodosList", userTodos);
        return "welcome";
    }

    private String getName(ModelMap model) {
        return (String) model.get("name");
    }


    @RequestMapping(value = "/addTodo", method = RequestMethod.GET)
    public String showTodo(ModelMap model) {
        model.addAttribute(new Todo(0, getName(model), "", new Date(), false));
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
        todo.setUser(getName(model));
        todoService.updateTodo(todo);
        return "redirect:welcome";
    }

    @RequestMapping(value = "/addTodo", method = RequestMethod.POST)
    public String addmyTodo(@Valid Todo todo, BindingResult result, ModelMap model) throws ParseException {
        // Date formattedDate = new SimpleDateFormat("yyyy-MM-dd").parse(targetDate);
        //System.out.println("My Name:  " + (String) model.get("name"));
        if (result.hasErrors()) {
            return "addTodo";
        }
        todoService.addTodo(getName(model), todo.getDesc(), todo.getTargetDate());
        return "redirect:welcome";
    }


    @RequestMapping(value = "/deleteTodo", method = RequestMethod.GET)
    public String removeMyTodo(@RequestParam int id) {
        todoService.deleteTodo(id);
        return "redirect:welcome";
    }
}
