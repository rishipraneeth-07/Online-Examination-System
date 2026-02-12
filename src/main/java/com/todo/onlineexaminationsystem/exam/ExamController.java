package com.todo.onlineexaminationsystem.exam;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ExamController {
    private final ExamService examService;

    public ExamController(ExamService examService) {
        this.examService = examService;
    }
    @PostMapping("/teacher/exams")
    public ResponseEntity<Exam> createExam(@RequestBody Exam exam,
                                           Authentication authentication) {
        String teacherEmail = authentication.getName();
        Exam createdExam = examService.createExam(exam, teacherEmail);
        return ResponseEntity.ok(createdExam);
    }

    @GetMapping("/teacher/exams")
    public ResponseEntity<List<Exam>> getTeacherExams(Authentication authentication) {
        String teacherEmail = authentication.getName();
        List<Exam> exams = examService.getExamsByTeacher(teacherEmail);
        return ResponseEntity.ok(exams);
    }

    @GetMapping("/student/exams")
    public ResponseEntity<List<Exam>> getActiveExams() {
        List<Exam> exams = examService.getActiveExams(LocalDateTime.now());
        return ResponseEntity.ok(exams);
    }

    @GetMapping("/exams/{id}")
    public ResponseEntity<Exam> getExamById(@PathVariable Long id) {
        Exam exam = examService.getExamById(id);
        return ResponseEntity.ok(exam);
    }
}
