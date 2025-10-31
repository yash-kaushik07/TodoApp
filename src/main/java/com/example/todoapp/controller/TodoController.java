package com.example.todoapp.controller;

import com.example.todoapp.model.Todo;
import com.example.todoapp.service.TodoService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/todos")
public class TodoController {

    private final TodoService service;

    public TodoController(TodoService service) {
        this.service = service;
    }

    @GetMapping
    public String list(@RequestParam(value = "filter", required = false, defaultValue = "ALL") String filter,
                       Model model) {
        if ("PENDING".equalsIgnoreCase(filter)) {
            model.addAttribute("todos", service.findByStatus(Todo.Status.PENDING));
        } else if ("DONE".equalsIgnoreCase(filter)) {
            model.addAttribute("todos", service.findByStatus(Todo.Status.DONE));
        } else {
            model.addAttribute("todos", service.findAllSorted());
        }
        model.addAttribute("filter", filter.toUpperCase());
        return "todos/list";
    }

    @GetMapping("/new")
    public String newTodoForm(Model model) {
        model.addAttribute("todo", new Todo());
        return "todos/form";
    }

    @PostMapping
    public String create(@Valid @ModelAttribute("todo") Todo todo, BindingResult br) {
        if (br.hasErrors()) {
            return "todos/form";
        }
        service.save(todo);
        return "redirect:/todos";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        service.findById(id).ifPresent(t -> model.addAttribute("todo", t));
        return "todos/form";
    }

    @PostMapping("/update/{id}")
    public String update(@PathVariable Long id, @Valid @ModelAttribute("todo") Todo todo, BindingResult br) {
        if (br.hasErrors()) {
            return "todos/form";
        }
        todo.setId(id);
        service.save(todo);
        return "redirect:/todos";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        service.deleteById(id);
        return "redirect:/todos";
    }

    @PostMapping("/done/{id}")
    public String markDone(@PathVariable Long id, @RequestHeader(value = "referer", required = false) String referer) {
        service.markDone(id);
        return "redirect:" + (referer == null ? "/todos" : referer);
    }
}
