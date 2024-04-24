package com.pavan.todo.exceptions;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class InternalServerErrorException extends Exception {

    private HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

    public InternalServerErrorException(String message) {
        super(message);
    }

    public InternalServerErrorException(HttpStatus httpStatus, String message) {
        super(message);

        this.httpStatus = httpStatus;
    }

}
