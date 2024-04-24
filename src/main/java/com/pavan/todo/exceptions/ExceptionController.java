package com.pavan.todo.exceptions;

import com.pavan.todo.dto.ResponseDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseDTO<Object>> handleException(Exception exception) {
        log.info(exception.getMessage());
        exception.printStackTrace();
        return new ResponseDTO<>(HttpStatus.INTERNAL_SERVER_ERROR, "Unable to process the request", exception.getMessage()).formatResponse();
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ResponseDTO<Object>> handleHttpRequestMethodNotSupportedException(Exception exception) {
        log.info(exception.getMessage());
        exception.printStackTrace();
        return new ResponseDTO<>(HttpStatus.NOT_FOUND, exception.getMessage()).formatResponse();
    }

    @ExceptionHandler(InternalServerErrorException.class)
    public ResponseEntity<ResponseDTO<Object>> handleInternalServerException(InternalServerErrorException exception) {
        log.info(exception.getMessage());
        exception.printStackTrace();
        return new ResponseDTO<>(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage()).formatResponse();
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ResponseDTO<Object>> handleBadRequestException(BadRequestException exception) {
        log.info(exception.getMessage());
        exception.printStackTrace();
        return new ResponseDTO<>(HttpStatus.BAD_REQUEST, exception.getMessage()).formatResponse();
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ResponseDTO<Object>> handleNotFoundException(NotFoundException exception) {
        log.info(exception.getMessage());
        exception.printStackTrace();
        return new ResponseDTO<>(HttpStatus.NOT_FOUND, exception.getMessage()).formatResponse();
    }

    @ExceptionHandler(ConflictException.class)
    public ResponseEntity<ResponseDTO<Object>> handleConflictException(ConflictException exception) {
        log.info(exception.getMessage());
        exception.printStackTrace();
        return new ResponseDTO<>(HttpStatus.CONFLICT, exception.getMessage()).formatResponse();
    }

}
