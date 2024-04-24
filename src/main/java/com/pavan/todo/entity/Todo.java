package com.pavan.todo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pavan.todo.enums.Status;
import com.pavan.todo.enums.Type;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serializable;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Todo implements Serializable {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @NonNull
    private String todo;

    @NonNull
    @Enumerated(EnumType.STRING)
    private Type type = Type.RANDOM;

    @CreationTimestamp
    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;

    @Enumerated(EnumType.STRING)
    private Status status = Status.ACTIVE;

    @JsonIgnore
    private boolean deleted;

    public Todo(@NonNull String todo) {
        this.todo = todo;
    }

    public Todo(@NonNull String todo, @NonNull Type type) {
        this.todo = todo;
        this.type = type;
    }
}
