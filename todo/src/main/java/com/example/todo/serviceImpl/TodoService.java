package com.example.todo.serviceImpl;

import com.example.todo.Entity.TodoEntity;
import com.example.todo.repository.TodoRepository;
import com.example.todo.serviceinterface.TodoInterface;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TodoService implements TodoInterface {

    private final TodoRepository todoRepository;

    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    @Override
    public List<TodoEntity> getAll() {
        return todoRepository.findAll();
    }

    @Override
    public Optional<TodoEntity> getById(Long id) {
        return todoRepository.findById(id);
    }

    @Override
    public TodoEntity addTodo(TodoEntity todo) {
        return todoRepository.save(todo);
    }

    @Override
    public TodoEntity update(Long id, TodoEntity todo) {
    	TodoEntity existingTodo = todoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Todo not found with id: " + id));

        existingTodo.setTitle(todo.getTitle());
        existingTodo.setStatus(todo.getStatus());
        return todoRepository.save(existingTodo);
    }

    @Override
    public void delete(Long id) {
        todoRepository.deleteById(id);
    }
}
