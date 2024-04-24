package com.pavan.todo.exceptions;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class ConflictException extends Exception {

    private HttpStatus httpStatus = HttpStatus.NOT_FOUND;

    public ConflictException(String message) {
        super(message);
    }

    public ConflictException(HttpStatus httpStatus, String message) {
        super(message);

        this.httpStatus = httpStatus;
    }

}
