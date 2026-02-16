package com.todo.onlineexaminationsystem.attempt;

import com.todo.onlineexaminationsystem.exam.Exam;
import com.todo.onlineexaminationsystem.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "exam_attempts")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ExamAttempt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    private User student;

    @ManyToOne
    @JoinColumn(name = "exam_id", nullable = false)
    private Exam exam;

    @Column(nullable = false)
    private Integer score;

    @Column(nullable = false)
    private LocalDateTime submittedAt;
}
