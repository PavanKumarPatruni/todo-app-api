package com.pavan.todo.repository.interfaces;

import com.pavan.todo.entity.Todo;

import java.util.List;
import java.util.Optional;

public interface ITodoRepository {

    List<Todo> findByDeletedFalse();

    Optional<Todo> findByIdAndDeletedFalse(long id);

}
