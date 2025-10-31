package com.example.todoapp.service;

import com.example.todoapp.model.Todo;
import com.example.todoapp.repository.TodoRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TodoService {
    private final TodoRepository repo;
    public TodoService(TodoRepository repo) { this.repo = repo; }

    public List<Todo> findAllSorted() {
        return repo.findAll(Sort.by(Sort.Direction.DESC, "createdAt"));
    }

    public List<Todo> findByStatus(Todo.Status status) {
        return repo.findByStatus(status, Sort.by(Sort.Direction.DESC, "createdAt"));
    }

    public Todo save(Todo t) { return repo.save(t); }
    public Optional<Todo> findById(Long id) { return repo.findById(id); }
    public void deleteById(Long id) { repo.deleteById(id); }

    public void markDone(Long id) {
        repo.findById(id).ifPresent(t -> { t.setStatus(Todo.Status.DONE); repo.save(t); });
    }
}
