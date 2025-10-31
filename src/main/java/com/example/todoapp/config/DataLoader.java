package com.example.todoapp.config;

import com.example.todoapp.model.Todo;
import com.example.todoapp.service.TodoService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final TodoService todoService;

    public DataLoader(TodoService todoService) {
        this.todoService = todoService;
    }

    @Override
    public void run(String... args) throws Exception {
        if (todoService.findAllSorted().isEmpty()) {
            Todo t1 = new Todo();
            t1.setTitle("Buy groceries");
            t1.setDescription("Milk, eggs, bread");
            todoService.save(t1);

            Todo t2 = new Todo();
            t2.setTitle("Read a book");
            t2.setDescription("Finish chapter 3");
            todoService.save(t2);

            Todo t3 = new Todo();
            t3.setTitle("Pay bills");
            t3.setDescription("Electricity & Internet");
            t3.setStatus(Todo.Status.DONE);
            todoService.save(t3);

            System.out.println("Seeded sample todos");
        }
    }
}
