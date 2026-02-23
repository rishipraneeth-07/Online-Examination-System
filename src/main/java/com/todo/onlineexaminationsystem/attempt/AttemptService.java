package com.todo.onlineexaminationsystem.attempt;

import com.todo.onlineexaminationsystem.dto.ExamSubmissionRequest;

import java.util.List;

public interface AttemptService {
    ExamAttempt submitExam(ExamSubmissionRequest request, String studentEmail);
    List<ExamAttempt> getAttemptsByStudent(String studentEmail);
}
