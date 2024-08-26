package com.javaguides.todo_management.service.impl;

import com.javaguides.todo_management.dto.TodoDto;
import com.javaguides.todo_management.entity.Todo;
import com.javaguides.todo_management.exception.ResourceNotFoundException;
import com.javaguides.todo_management.repository.TodoRepository;
import com.javaguides.todo_management.service.TodoService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TodoServiceImpl implements TodoService {
   private TodoRepository todoRepository;

   private ModelMapper modelMapper;
    @Override
    public TodoDto addTodo(TodoDto todoDto) {
        // convert TodoDto into Todo Jpa entity
//        Todo todo = new Todo();
//        todo.setTitle(todoDto.getTitle());
//        todo.setDescription(todoDto.getDescription());
//        todo.setCompleted(todoDto.isCompleted());
        //model-mapper usage
        Todo todo = modelMapper.map(todoDto,Todo.class);

        // todo jpa entity
        Todo savedTodo = todoRepository.save(todo);

        //convert saved Todo Jpa entity object into TodoDto object

        return modelMapper.map(savedTodo,TodoDto.class);
    }

    @Override
    public TodoDto getTodo(Long id) {
        Todo todo = todoRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("there is nothing todo in this id"+id));

        return modelMapper.map(todo, TodoDto.class);
    }

    @Override
    public List<TodoDto> getAllTodos() {

        List<Todo> todos = todoRepository.findAll();

        return todos.stream().map(todo -> modelMapper.map(todo,TodoDto.class)).collect(Collectors.toList());
    }

    @Override
    public TodoDto updateTodo(TodoDto todoDto, Long id) {
        Todo todo = todoRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("todo not found with id"));
        todo.setTitle(todoDto.getTitle());
        todo.setCompleted(todoDto.isCompleted());
        todo.setDescription(todo.getDescription());
        Todo savedTodo = todoRepository.save(todo);
        return modelMapper.map(savedTodo, TodoDto.class);
    }

    @Override
    public void deleteTodo(Long id) {
         todoRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("NOT FOUND"));
        todoRepository.deleteById(id);
    }

    @Override
    public TodoDto completedTodo(Long id) {

        Todo todo = todoRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("NOT FOUND ID"));
        todo.setCompleted(Boolean.TRUE);
        Todo updatedTodo = todoRepository.save(todo);
        return modelMapper.map(updatedTodo,TodoDto.class);
    }

    @Override
    public TodoDto inCompleteTodo(Long id) {
        Todo todo = todoRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("NOT FOUND ID"));
        todo.setCompleted(Boolean.FALSE);
        Todo updatedTodo = todoRepository.save(todo);
        return modelMapper.map(updatedTodo, TodoDto.class);
    }
}
