package com.todo.onlineexaminationsystem.question;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class QuestionController {
    private final QuestionService questionService;

    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @PostMapping("/teacher/exams/{examId}/questions")
    public ResponseEntity<Question> addQuestion(@PathVariable Long examId,
                                                @RequestBody Question question,
                                                Authentication authentication) {

        String teacherEmail = authentication.getName();

        Question savedQuestion =
                questionService.addQuestion(examId, question, teacherEmail);

        return ResponseEntity.ok(savedQuestion);
    }

    @GetMapping("/exams/{examId}/questions")
    public ResponseEntity<List<Question>> getQuestionsByExam(@PathVariable Long examId) {

        List<Question> questions =
                questionService.getQuestionsByExam(examId);

        return ResponseEntity.ok(questions);
    }
}
