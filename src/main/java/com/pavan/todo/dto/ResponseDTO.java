package com.pavan.todo.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ResponseDTO<T> {

    private boolean success;

    private int statusCode;

    private String message = null;

    private T data = null;

    private String debugMessage = null;

    public ResponseDTO(boolean success) {
        this.success = success;
    }

    public ResponseDTO(HttpStatus httpStatus, String message) {
        this.statusCode = httpStatus.value();
        this.message = message;
    }

    public ResponseDTO(HttpStatus httpStatus, String message, String debugMessage) {
        this.statusCode = httpStatus.value();
        this.message = message;
        this.debugMessage = debugMessage;
    }

    public ResponseDTO(HttpStatus httpStatus, String message, T data) {
        this.statusCode = httpStatus.value();
        this.message = message;
        this.data = data;
    }

    public ResponseDTO(HttpStatus httpStatus, String message, T data, String debugMessage) {
        this.statusCode = httpStatus.value();
        this.message = message;
        this.data = data;
        this.debugMessage = debugMessage;
    }

    public ResponseEntity<ResponseDTO<T>> formatResponse() {
        this.success = this.statusCode == HttpStatus.OK.value();
        return new ResponseEntity<>(this, HttpStatus.valueOf(this.statusCode));
    }

}
