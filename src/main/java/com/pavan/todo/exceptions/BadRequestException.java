package com.pavan.todo.exceptions;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class BadRequestException extends Exception {

    private HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

    public BadRequestException(String message) {
        super(message);
    }

    public BadRequestException(HttpStatus httpStatus, String message) {
        super(message);

        this.httpStatus = httpStatus;
    }

}
