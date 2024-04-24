package com.pavan.todo.repository;

import com.pavan.todo.entity.Todo;
import com.pavan.todo.repository.interfaces.ITodoRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Long>, ITodoRepository {

    List<Todo> findByDeletedFalseOrderByStatusAscCreatedAtDesc();

    Optional<Todo> findByIdAndDeletedFalse(Long id);

}
