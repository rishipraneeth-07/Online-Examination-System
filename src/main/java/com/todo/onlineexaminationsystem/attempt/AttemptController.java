package com.todo.onlineexaminationsystem.attempt;

import com.todo.onlineexaminationsystem.dto.ExamSubmissionRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AttemptController {
    private final AttemptService attemptService;
    private final ExamAttemptRepo attemptRepository;

    public AttemptController(AttemptService attemptService,
                             ExamAttemptRepo attemptRepository) {
        this.attemptService = attemptService;
        this.attemptRepository = attemptRepository;
    }
    @PostMapping("/student/attempts")
    public ResponseEntity<ExamAttempt> submitExam(
            @RequestBody ExamSubmissionRequest request,
            Authentication authentication) {
        String studentEmail = authentication.getName();

        ExamAttempt attempt =
                attemptService.submitExam(request, studentEmail);

        return ResponseEntity.ok(attempt);
    }

    @GetMapping("/student/attempts")
    public ResponseEntity<List<ExamAttempt>> getMyAttempts(
            Authentication authentication) {

        String studentEmail = authentication.getName();

        List<ExamAttempt> attempts =
                attemptService.getAttemptsByStudent(studentEmail);

        return ResponseEntity.ok(attempts);
    }
}
