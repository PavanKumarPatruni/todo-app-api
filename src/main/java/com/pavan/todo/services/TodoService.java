package com.pavan.todo.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.pavan.todo.dto.todo.AddTodoDTO;
import com.pavan.todo.dto.todo.UpdateTodoDTO;
import com.pavan.todo.dto.todo.v2.AddTodoV2DTO;
import com.pavan.todo.dto.todo.v2.UpdateTodoV2DTO;
import com.pavan.todo.entity.Todo;
import com.pavan.todo.exceptions.BadRequestException;
import com.pavan.todo.exceptions.TodoNotFoundException;
import com.pavan.todo.repository.TodoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class TodoService {

    @Autowired
    private TodoRepository todoRepository;

    @Cacheable("todos")
    public List<Todo> getTodos() {
        return todoRepository.findByDeletedFalseOrderByStatusAscCreatedAtDesc();
    }

    @Cacheable(value = "todo", key = "#id")
    public Todo getTodo(Long id) throws TodoNotFoundException {
        return todoRepository.findByIdAndDeletedFalse(id).orElseThrow(TodoNotFoundException::new);
    }

    @CacheEvict(value = "todos", allEntries = true)
    public Todo addTodo(AddTodoDTO todoDTO) throws BadRequestException {
        todoDTO.isValid();

        return todoRepository.save(new Todo(todoDTO.getTodo()));
    }

    @CacheEvict(value = "todos", allEntries = true)
    public Todo addTodoV2(AddTodoV2DTO todoDTO) throws BadRequestException {
        todoDTO.isValid();

        return todoRepository.save(new Todo(todoDTO.getTodo(), todoDTO.getType()));
    }

    @CachePut(value = "todo", key = "#id")
    @CacheEvict(value = "todos", allEntries = true)
    public Todo updateTodo(Long id, UpdateTodoDTO todoDTO) throws TodoNotFoundException, BadRequestException {
        todoDTO.isValid();

        Todo todoData = getTodo(id);
        if (todoDTO.getTodo() != null) {
            todoData.setTodo(todoDTO.getTodo());
        }
        if (todoDTO.getStatus() != null) {
            todoData.setStatus(todoDTO.getStatus());
        }
        todoRepository.save(todoData);

        return todoData;
    }

    @CachePut(value = "todo", key = "#id")
    @CacheEvict(value = "todos", allEntries = true)
    public Todo updateTodoV2(Long id, UpdateTodoV2DTO todoDTO) throws BadRequestException, TodoNotFoundException {
        todoDTO.isValid();

        Todo todoData = getTodo(id);
        if (todoDTO.getTodo() != null) {
            todoData.setTodo(todoDTO.getTodo());
        }
        if (todoDTO.getType() != null) {
            todoData.setType(todoDTO.getType());
        }
        if (todoDTO.getStatus() != null) {
            todoData.setStatus(todoDTO.getStatus());
        }
        todoRepository.save(todoData);

        return todoData;
    }

    @Caching(evict = {
            @CacheEvict(value = "todo", key = "#id"),
            @CacheEvict(value = "todos", allEntries = true)
    })
    public Todo deleteTodo(Long id) throws TodoNotFoundException {
        Todo todoData = getTodo(id);
        todoData.setDeleted(true);
        todoRepository.save(todoData);

        return todoData;
    }
}
