package de.cnd.project.rest;

public class StudentNotFoundException extends RuntimeException {

    StudentNotFoundException(Long matrikelNummer) {
        super("Could not find student with Id " + matrikelNummer);
    }
}
