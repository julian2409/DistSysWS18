package de.cnd.project.rest;

import de.cnd.project.data.*;
import de.cnd.project.data.model.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class GradeController {

    private final GradeRepository gradeRepository;
    private final StudentRepository studentRepository;
    private final ExamRepository examRepository;

    GradeController(GradeRepository gradeRepository,
                    StudentRepository studentRepository, ExamRepository examRepository) {
        this.gradeRepository = gradeRepository;
        this.studentRepository = studentRepository;
        this.examRepository = examRepository;
    }

    @GetMapping("/grades")
    public List<Grade> getAllGrades() {
        return gradeRepository.findAll();
    }

    @PostMapping("/students/{studentId}/exams/{eId}")
    Grade createGradeForStudentForExam(@PathVariable(value = "studentId") Long studentId,
                                       @PathVariable(value = "eId") Long eId, @RequestBody Grade newGrade) {
        newGrade.setStudent(studentRepository.findById(studentId).orElseThrow(() -> new StudentNotFoundException(studentId)));
        newGrade.setExam(examRepository.findById(eId).orElseThrow(() -> new ExamNotFoundException(eId)));
        return gradeRepository.save(newGrade);
    }

    @DeleteMapping("/grades/{gId}")
    void deleteGrade(@PathVariable Long gId) {
        gradeRepository.deleteById(gId);
    }
}

