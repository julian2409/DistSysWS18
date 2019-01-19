package de.cnd.project.rest;

import de.cnd.project.data.GradeRepository;
import de.cnd.project.data.StudentRepository;
import de.cnd.project.data.model.Grade;
import de.cnd.project.data.model.Student;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentController {

    private final StudentRepository studentRepository;
    private final GradeRepository gradeRepository;

    StudentController(StudentRepository repository, GradeRepository gradeRepository) {
        this.studentRepository = repository;
        this.gradeRepository = gradeRepository;
    }

    @GetMapping("/students")
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @GetMapping("/students/{studentId}")
    Student getStudentByMatrNr(@PathVariable Long studentId) {
        return studentRepository.findById(studentId)
                .orElseThrow(() -> new StudentNotFoundException(studentId));
    }

    @GetMapping("/students/{studentId}/grades")
    List<Grade> getAllGrades(@PathVariable Long studentId) {
        return gradeRepository.findByStudent(studentRepository.findById(studentId)
                .orElseThrow(() -> new StudentNotFoundException(studentId)));
    }

    @PostMapping("/students")
    Student newStudent(@RequestBody Student newStudent) {
        return studentRepository.save(newStudent);
    }

    @PutMapping("/students/{studentId}")
    Student modifyStudent(@RequestBody Student newStudent, @PathVariable Long studentId) {
        return studentRepository.findById(studentId)
                .map(student -> {
                    student.setName(newStudent.getName());
                    student.setFirstName(newStudent.getFirstName());
                    return studentRepository.save(student);
                }).orElseGet(() -> {
                    newStudent.setStudentId(studentId);
                    return studentRepository.save(newStudent);
                });
    }

    @DeleteMapping("/students/{studentId}")
    void deleteStudent(@PathVariable Long studentId) {
        studentRepository.deleteById(studentId);
    }
}
