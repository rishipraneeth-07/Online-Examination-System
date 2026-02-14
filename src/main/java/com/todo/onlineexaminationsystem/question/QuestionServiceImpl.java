package com.todo.onlineexaminationsystem.question;

import com.todo.onlineexaminationsystem.exam.Exam;
import com.todo.onlineexaminationsystem.exam.ExamRepo;
import com.todo.onlineexaminationsystem.user.User;
import com.todo.onlineexaminationsystem.user.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {

    private final QuestionRepo questionRepository;
    private final ExamRepo examRepo;
    private final UserService userService;

    public QuestionServiceImpl(QuestionRepo questionRepository,
                               ExamRepo examRepo,
                               UserService userService) {
        this.questionRepository = questionRepository;
        this.examRepo = examRepo;
        this.userService = userService;
    }
    @Override
    public Question addQuestion(Long examId, Question question, String teacherEmail) {
        Exam exam = examRepo.findById(examId)
                .orElseThrow(() -> new RuntimeException("Exam not found"));
        User teacher = userService.getUserByEmail(teacherEmail)
                .orElseThrow(() -> new RuntimeException("Teacher not found"));
        if (!exam.getCreatedBy().getId().equals(teacher.getId())) {
            throw new RuntimeException("You are not allowed to add questions to this exam");
        }
        if (!question.getCorrectAnswer().matches("[ABCD]")) {
            throw new RuntimeException("Correct answer must be A, B, C, or D");
        }
        question.setExam(exam);
        return questionRepository.save(question);
    }

    @Override
    public List<Question> getQuestionsByExam(Long examId) {
        Exam exam = examRepo.findById(examId)
                .orElseThrow(() -> new RuntimeException("Exam not found"));

        return questionRepository.findByExam(exam);
    }
}

