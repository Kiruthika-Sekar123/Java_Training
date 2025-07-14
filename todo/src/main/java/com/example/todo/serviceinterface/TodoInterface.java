package com.example.todo.serviceinterface;


import com.example.todo.Entity.TodoEntity;
import java.util.List;
import java.util.Optional;

public interface TodoInterface {
     List<TodoEntity> getAll();
     Optional<TodoEntity> getById(Long id);
	 TodoEntity addTodo(TodoEntity todo);
	 TodoEntity update(Long id, TodoEntity todo);
     void delete(Long id);
}
