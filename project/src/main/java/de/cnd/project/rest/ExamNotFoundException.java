package de.cnd.project.rest;

public class ExamNotFoundException extends RuntimeException {

    ExamNotFoundException(Long eId) {
        super("Could not find student with Id " + eId);
    }
}
