package com.springboot.restapisecuritymain.controller;

import com.springboot.restapisecuritymain.entity.Todo;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TodoController {

    public static final List<Todo> TODO_LIST = List.of(new Todo(1, "Aryan", "Learn Spring Boot"), new Todo(2, "Aryan", "React"));

    @GetMapping("/todos")
    public List<Todo> listAllTodos(){
        return TODO_LIST;
    }
    @PreAuthorize("hasRole('USER' and #username==authentication.name")
    @GetMapping("users/{name}/todo")
    public Todo retreiveTodoById(@PathVariable String name){
        return TODO_LIST.get(0);
    }
    @PostMapping("users/{name}/todo")
    public void addTodoForSpecificUser(@PathVariable String name, @RequestBody Todo todo){
        TODO_LIST.add(todo);
    }

    @GetMapping("/csrf")
    public CsrfToken retreiveCsrfToken(HttpServletRequest request){
        return (CsrfToken) request.getAttribute("_csrf");
    }


}
