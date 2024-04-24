package com.pavan.todo.dto.todo;

import com.pavan.todo.dto.BaseDTO;
import com.pavan.todo.enums.Status;
import com.pavan.todo.exceptions.BadRequestException;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.util.ObjectUtils;

@Getter
@Setter
@NoArgsConstructor
public class UpdateTodoDTO implements BaseDTO {

    String todo;

    Status status;

    public void isValid() throws BadRequestException {
        if (ObjectUtils.isEmpty(todo) && ObjectUtils.isEmpty(status))
            throw new BadRequestException("Nothing to update");

        if (!ObjectUtils.isEmpty(todo) && todo.length() <= 3)
            throw new BadRequestException("Should be at least 3 characters");
    }
}
