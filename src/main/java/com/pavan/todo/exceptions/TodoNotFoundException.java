package com.pavan.todo.exceptions;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class TodoNotFoundException extends Exception {

    private HttpStatus httpStatus = HttpStatus.NOT_FOUND;

    public TodoNotFoundException() {
        super("Todo not found!");
    }

    public TodoNotFoundException(String message) {
        super(message);
    }

    public TodoNotFoundException(HttpStatus httpStatus, String message) {
        super(message);

        this.httpStatus = httpStatus;
    }

}
