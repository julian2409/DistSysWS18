package de.cnd.project.data.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter @RequiredArgsConstructor
public class Student {

    @Id
    @GeneratedValue
    private Long studentId;
    private String name;
    private String firstName;
}
