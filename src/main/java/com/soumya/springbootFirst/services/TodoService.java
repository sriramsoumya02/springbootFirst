package com.soumya.springbootFirst.services;

import com.soumya.springbootFirst.model.Todo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class TodoService {
    private static List<Todo> myTodos = new ArrayList<Todo>();
    private static int count = 3;

    static {
        myTodos.add(new Todo(1, "soumya", "Learn Spring MVC", new Date(),
                false));
        myTodos.add(new Todo(2, "soumya", "Learn Struts", new Date(), false));
        myTodos.add(new Todo(3, "soumya", "Learn Hibernate", new Date(),
                false));
    }

    public void addTodo(String user, String desc, Date targetDate) {
        myTodos.add(new Todo(++count, user, desc, targetDate, false));
    }

    public void deleteTodo(int id) {
        myTodos.removeIf(todo -> todo.getId() == id);
    }

    public List<Todo> getMyTodos(String user) {
        List<Todo> userTodo = new ArrayList<Todo>();
        for (Todo todo : myTodos) {
            if (todo.getUser().equals(user)) {
                userTodo.add(todo);
            }
        }
        return userTodo;
    }

    public Todo retriveTodo(int id) {
        for (Todo todo : myTodos) {
            if (todo.getId() == id)
                return todo;
        }
        return null;
    }

    public void updateTodo(Todo todo) {
        myTodos.remove(todo);
        myTodos.add(todo);
    }
}
