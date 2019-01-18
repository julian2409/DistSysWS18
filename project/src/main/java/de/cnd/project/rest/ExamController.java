package de.cnd.project.rest;

import de.cnd.project.data.ExamRepository;
import de.cnd.project.data.GradeRepository;
import de.cnd.project.data.model.Exam;
import de.cnd.project.data.model.Grade;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ExamController {

    private final ExamRepository examRepository;
    private final GradeRepository gradeRepository;

    ExamController(ExamRepository repository, GradeRepository gradeRepository) {
        this.examRepository = repository;
        this.gradeRepository = gradeRepository;
    }

    @GetMapping("/exams")
    public List<Exam> getAllExams() {
        return examRepository.findAll();
    }

    @GetMapping("/exams/{eId}")
    Exam getExamById(@PathVariable Long eId) {
        return examRepository.findById(eId)
                .orElseThrow(() -> new ExamNotFoundException(eId));
    }

    @GetMapping("/exams/{eId}/grades")
    List<Grade> getAllGrades(@PathVariable Long eId) {
        return gradeRepository.findByExam(examRepository.findById(eId)
                .orElseThrow(() -> new ExamNotFoundException(eId)));
    }

    @PostMapping("/exams")
    Exam newExam(@RequestBody Exam newExam) {
        return examRepository.save(newExam);
    }

    @PutMapping("/exams/{eId}")
    Exam modifyExam(@RequestBody Exam newExam, @PathVariable Long eId) {
        return examRepository.findById(eId)
                .map(exam -> {
                    exam.setLecture(newExam.getLecture());
                    return examRepository.save(exam);
                }).orElseGet(() -> {
                    newExam.setEId(eId);
                    return examRepository.save(newExam);
                });
    }

    @DeleteMapping("/exams/{eId}")
    void deleteExam(@PathVariable Long eId) {
        examRepository.deleteById(eId);
    }
}

