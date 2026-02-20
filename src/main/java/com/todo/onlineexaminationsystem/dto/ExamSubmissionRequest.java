package com.todo.onlineexaminationsystem.dto;

import lombok.*;

import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ExamSubmissionRequest {

    private Long examId;
    private Map<Long, String> answers;
}