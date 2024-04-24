package com.pavan.todo.dto;

import com.pavan.todo.exceptions.BadRequestException;

public interface BaseDTO {

    void isValid() throws BadRequestException;

}
