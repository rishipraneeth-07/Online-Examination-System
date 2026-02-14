package com.todo.onlineexaminationsystem.question;

import java.util.List;

public interface QuestionService {
    Question addQuestion(Long examId, Question question, String teacherEmail);

    List<Question> getQuestionsByExam(Long examId);
}
