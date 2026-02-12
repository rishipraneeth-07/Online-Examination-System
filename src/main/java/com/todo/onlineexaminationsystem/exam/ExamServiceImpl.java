package com.todo.onlineexaminationsystem.exam;

import com.todo.onlineexaminationsystem.user.User;
import com.todo.onlineexaminationsystem.user.UserService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ExamServiceImpl implements ExamService {
    private final ExamRepo examRepo;
    private final UserService userService;

    public ExamServiceImpl(ExamRepo examRepo, UserService userService) {
        this.examRepo = examRepo;
        this.userService = userService;
    }


    @Override
    public Exam createExam(Exam exam, String teacherEmail) {
        User teacher = userService.getUserByEmail(teacherEmail)
                .orElseThrow(() -> new RuntimeException("Teacher not found"));

        if (!teacher.getRole().getName().equals("TEACHER")) {
            throw new RuntimeException("Only teachers can create exams");
        }

        if (exam.getStartTime().isAfter(exam.getEndTime())) {
            throw new RuntimeException("Start time must be before end time");
        }
        if (exam.getDuration() <= 0) {
            throw new RuntimeException("Duration must be positive");
        }
        if (exam.getStartTime().isBefore(LocalDateTime.now())) {
            throw new RuntimeException("Exam must be scheduled in future");
        }
        exam.setCreatedBy(teacher);
        return examRepo.save(exam);
    }

    @Override
    public Exam getExamById(Long examId) {
        return examRepo.findById(examId)
                .orElseThrow(() -> new RuntimeException("Exam not found"));
    }

    @Override
    public List<Exam> getExamsByTeacher(String teacherEmail) {
        User teacher = userService.getUserByEmail(teacherEmail)
                .orElseThrow(() -> new RuntimeException("Teacher not found"));
        return examRepo.findByCreatedBy(teacher);
    }

    @Override
    public List<Exam> getActiveExams(LocalDateTime now) {
        return examRepo.findByStartTimeBeforeAndEndTimeAfter(now, now);
    }
}
