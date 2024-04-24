package com.pavan.todo.dto.todo.v2;

import com.pavan.todo.dto.BaseDTO;
import com.pavan.todo.enums.Type;
import com.pavan.todo.exceptions.BadRequestException;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.ObjectUtils;

@Getter
@Setter
@NoArgsConstructor
@Slf4j
public class AddTodoV2DTO implements BaseDTO {

    String todo;

    Type type;

    public void isValid() throws BadRequestException {
        log.info(todo, type);
        if (ObjectUtils.isEmpty(todo) || todo.length() <= 3)
            throw new BadRequestException("Should be at least 3 characters");

        if (ObjectUtils.isEmpty(type)) throw new BadRequestException("Should contain a type");
    }

}
