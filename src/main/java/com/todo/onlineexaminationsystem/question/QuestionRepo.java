package com.todo.onlineexaminationsystem.question;

import com.todo.onlineexaminationsystem.exam.Exam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepo extends JpaRepository<Question, Integer> {
    List<Question> findByExam(Exam exam);
}
