package com.example.todo.controller;

import com.example.todo.Entity.TodoEntity;
import com.example.todo.serviceinterface.TodoInterface;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/todos")
public class TodoController {

    
    private TodoInterface service;

    public TodoController(TodoInterface service) {
        this.service = service;
    }

    @GetMapping("/")
    public List<TodoEntity> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TodoEntity> getById(@PathVariable Long id) {
        return service.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/add")
    public ResponseEntity<TodoEntity> create(@RequestBody TodoEntity todo) {
          return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(service.addTodo(todo));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TodoEntity> update(@PathVariable Long id, @RequestBody TodoEntity todo) {
        return ResponseEntity.ok(service.update(id, todo));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
