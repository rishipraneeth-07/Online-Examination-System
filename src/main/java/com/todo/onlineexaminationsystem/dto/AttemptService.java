package com.todo.onlineexaminationsystem.dto;

import com.todo.onlineexaminationsystem.attempt.ExamAttempt;

public interface AttemptService {
    ExamAttempt submitExam(ExamSubmissionRequest request, String studentEmail);
}
