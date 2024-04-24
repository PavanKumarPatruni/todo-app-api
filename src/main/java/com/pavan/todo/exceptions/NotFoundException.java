package com.pavan.todo.exceptions;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class NotFoundException extends Exception {

    private HttpStatus httpStatus = HttpStatus.NOT_FOUND;

    public NotFoundException(String message) {
        super(message);
    }

    public NotFoundException(HttpStatus httpStatus, String message) {
        super(message);

        this.httpStatus = httpStatus;
    }

}
