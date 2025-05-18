package com.jphilip.tm.task.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Builder
public class TaskResponseDTO {
    private Long id;

    private Long assignedBy;

    private Long assignedTo;

    private String taskDescription;

    private String taskNotes;

    private LocalDateTime assignedDate;

    private LocalDateTime updateDate;

    private LocalDateTime completedDate;
}
