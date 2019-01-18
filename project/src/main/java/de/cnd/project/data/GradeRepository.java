package de.cnd.project.data;

import de.cnd.project.data.model.Exam;
import de.cnd.project.data.model.Grade;
import de.cnd.project.data.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GradeRepository extends JpaRepository<Grade, Long> {
    List<Grade> findByStudent(Student student);
    List<Grade> findByExam(Exam exam);
}
