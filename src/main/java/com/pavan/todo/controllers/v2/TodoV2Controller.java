package com.pavan.todo.controllers.v2;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.pavan.todo.dto.ResponseDTO;
import com.pavan.todo.dto.todo.v2.AddTodoV2DTO;
import com.pavan.todo.dto.todo.v2.UpdateTodoV2DTO;
import com.pavan.todo.entity.Todo;
import com.pavan.todo.exceptions.BadRequestException;
import com.pavan.todo.exceptions.TodoNotFoundException;
import com.pavan.todo.services.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v2/todo")
public class TodoV2Controller {

    @Autowired
    private TodoService todoService;

    @PostMapping
    public ResponseEntity<ResponseDTO<Todo>> addTodoV2(@RequestBody AddTodoV2DTO todo) throws BadRequestException, JsonProcessingException {
        return new ResponseDTO<>(HttpStatus.OK, "OK", todoService.addTodoV2(todo)).formatResponse();
    }

    @PutMapping("{id}")
    public ResponseEntity<ResponseDTO<Todo>> updateTodoV2(@PathVariable Long id, @RequestBody UpdateTodoV2DTO todo) throws TodoNotFoundException, BadRequestException, JsonProcessingException {
        return new ResponseDTO<>(HttpStatus.OK, "OK", todoService.updateTodoV2(id, todo)).formatResponse();
    }

}
