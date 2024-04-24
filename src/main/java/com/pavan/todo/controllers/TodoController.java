package com.pavan.todo.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.pavan.todo.dto.ResponseDTO;
import com.pavan.todo.dto.todo.AddTodoDTO;
import com.pavan.todo.dto.todo.UpdateTodoDTO;
import com.pavan.todo.entity.Todo;
import com.pavan.todo.exceptions.BadRequestException;
import com.pavan.todo.exceptions.TodoNotFoundException;
import com.pavan.todo.services.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/todo")
public class TodoController {

    @Autowired
    private TodoService todoService;

    @GetMapping
    public ResponseEntity<ResponseDTO<List<Todo>>> getTodos() throws JsonProcessingException {
        return new ResponseDTO<>(HttpStatus.OK, "OK", todoService.getTodos()).formatResponse();
    }

    @GetMapping("{id}")
    public ResponseEntity<ResponseDTO<Todo>> getTodo(@PathVariable Long id) throws TodoNotFoundException, JsonProcessingException {
        return new ResponseDTO<>(HttpStatus.OK, "OK", todoService.getTodo(id)).formatResponse();
    }

    @PostMapping
    public ResponseEntity<ResponseDTO<Todo>> addTodo(@RequestBody AddTodoDTO todo) throws BadRequestException, JsonProcessingException {
        return new ResponseDTO<>(HttpStatus.OK, "OK", todoService.addTodo(todo)).formatResponse();
    }

    @PutMapping("{id}")
    public ResponseEntity<ResponseDTO<Todo>> updateTodo(@PathVariable Long id, @RequestBody UpdateTodoDTO todo) throws TodoNotFoundException, BadRequestException, JsonProcessingException {
        return new ResponseDTO<>(HttpStatus.OK, "OK", todoService.updateTodo(id, todo)).formatResponse();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<ResponseDTO<Todo>> deleteTodo(@PathVariable Long id) throws TodoNotFoundException, JsonProcessingException {
        return new ResponseDTO<>(HttpStatus.OK, "OK", todoService.deleteTodo(id)).formatResponse();
    }

}
