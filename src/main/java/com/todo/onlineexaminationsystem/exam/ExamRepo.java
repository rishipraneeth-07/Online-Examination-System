package com.todo.onlineexaminationsystem.exam;

import org.springframework.data.jpa.repository.JpaRepository;
import com.todo.onlineexaminationsystem.user.User;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ExamRepo extends JpaRepository<Exam, Long> {
    List<Exam> findByCreatedBy(User teacher);

    List<Exam> findByStartTimeBeforeAndEndTimeAfter(LocalDateTime now1, LocalDateTime now2);
}
