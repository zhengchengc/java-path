package com.example.postgresdemo.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Data
@Table(name = "questions")
public class Question extends AuditModel{

    @Id
    @GeneratedValue(generator = "question_generator")
    @SequenceGenerator(
            name = "question_generator",
            sequenceName = "question_sequence",
            initialValue = 1000
    )
    private Long id;

    @NotNull
    @Size(min = 3, max = 100)
    private String title;

    @Column(columnDefinition = "text")
    private String description;
}
