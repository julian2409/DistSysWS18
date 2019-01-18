package de.cnd.project.data.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter @RequiredArgsConstructor
public class Exam {

    @Id
    @GeneratedValue
    private Long eId;
    private String lecture;
}
