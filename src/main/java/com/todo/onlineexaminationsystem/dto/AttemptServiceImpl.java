package com.todo.onlineexaminationsystem.dto;


import com.todo.onlineexaminationsystem.attempt.ExamAttempt;
import com.todo.onlineexaminationsystem.attempt.ExamAttemptRepo;
import com.todo.onlineexaminationsystem.attempt.StudentAnswer;
import com.todo.onlineexaminationsystem.attempt.StudentAnswerRepo;
import com.todo.onlineexaminationsystem.exam.Exam;
import com.todo.onlineexaminationsystem.exam.ExamRepo;
import com.todo.onlineexaminationsystem.question.Question;
import com.todo.onlineexaminationsystem.question.QuestionRepo;
import com.todo.onlineexaminationsystem.user.User;
import com.todo.onlineexaminationsystem.user.UserService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
public class AttemptServiceImpl implements AttemptService {

    private final ExamAttemptRepo attemptRepository;
    private final StudentAnswerRepo answerRepository;
    private final ExamRepo examRepo;
    private final QuestionRepo questionRepository;
    private final UserService userService;

    public AttemptServiceImpl(ExamAttemptRepo attemptRepository,
                              StudentAnswerRepo answerRepository,
                              ExamRepo examRepo,
                              QuestionRepo questionRepository,
                              UserService userService) {
        this.attemptRepository = attemptRepository;
        this.answerRepository = answerRepository;
        this.examRepo = examRepo;
        this.questionRepository = questionRepository;
        this.userService = userService;
    }

    @Override
    public ExamAttempt submitExam(ExamSubmissionRequest request, String studentEmail) {

        User student = userService.getUserByEmail(studentEmail)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        Exam exam = examRepo.findById(request.getExamId())
                .orElseThrow(() -> new RuntimeException("Exam not found"));

        if (!student.getRole().getName().equals("STUDENT")) {
            throw new RuntimeException("Only students can attempt exams");
        }
        if (attemptRepository.existsByStudentAndExam(student, exam)) {
            throw new RuntimeException("You have already attempted this exam");
        }
        LocalDateTime now = LocalDateTime.now();
        if (now.isBefore(exam.getStartTime()) || now.isAfter(exam.getEndTime())) {
            throw new RuntimeException("Exam is not active");
        }
        List<Question> questions = questionRepository.findByExam(exam);

        Map<Long, String> submittedAnswers = request.getAnswers();

        int score = 0;

        for (Question question : questions) {
            String correctAnswer = question.getCorrectAnswer();
            String studentAnswer = submittedAnswers.get(question.getId());

            if (correctAnswer.equals(studentAnswer)) {
                score++;
            }
        }

        ExamAttempt attempt = new ExamAttempt();
        attempt.setStudent(student);
        attempt.setExam(exam);
        attempt.setScore(score);
        attempt.setSubmittedAt(LocalDateTime.now());

        ExamAttempt savedAttempt = attemptRepository.save(attempt);

        // Save each student answer
        for (Question question : questions) {
            StudentAnswer answer = new StudentAnswer();
            answer.setExamAttempt(savedAttempt);
            answer.setQuestion(question);
            answer.setSelectedAnswer(submittedAnswers.get(question.getId()));

            answerRepository.save(answer);
        }

        return savedAttempt;
    }
}
