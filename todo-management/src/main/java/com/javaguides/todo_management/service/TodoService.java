package com.javaguides.todo_management.service;

import com.javaguides.todo_management.dto.TodoDto;
import org.springframework.stereotype.Service;

import java.util.List;


public interface TodoService {

    TodoDto addTodo(TodoDto todoDto);
    TodoDto getTodo(Long id);

    List<TodoDto> getAllTodos();

    TodoDto updateTodo(TodoDto todoDto, Long id);
    void deleteTodo(Long id);

    TodoDto completedTodo(Long id);

    TodoDto inCompleteTodo(Long id);
}
