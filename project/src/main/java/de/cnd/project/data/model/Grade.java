package de.cnd.project.data.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter @RequiredArgsConstructor
public class Grade {

    @Id
    @GeneratedValue
    private Long gId;
    private double grade;
    @ManyToOne
    @JoinColumn(name="studentId")
    private Student student;
    @ManyToOne
    @JoinColumn(name="eId")
    private Exam exam;
}
