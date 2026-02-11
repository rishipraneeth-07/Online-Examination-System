package com.todo.onlineexaminationsystem.exam;

import java.time.LocalDateTime;
import java.util.List;

public interface ExamService {
    Exam createExam(Exam exam, String teacherEmail);

    Exam getExamById(Long examId);

    List<Exam> getExamsByTeacher(String teacherEmail);

    List<Exam> getActiveExams(LocalDateTime now);
}
