package com.todo.onlineexaminationsystem.attempt;

import com.todo.onlineexaminationsystem.exam.Exam;
import com.todo.onlineexaminationsystem.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExamAttemptRepo extends JpaRepository<ExamAttempt,Long> {
    List<ExamAttempt> findByStudent(User student);

    List<ExamAttempt> findByExam(Exam exam);

    boolean existsByStudentAndExam(User student, Exam exam);
}
