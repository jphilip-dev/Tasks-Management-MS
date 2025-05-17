package com.jphilip.tm.task.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TaskRequestDTO {

    private Long assignedBy;

    private Long assignedTo;

    private String taskDescription;

    private String taskNotes;

    private LocalDateTime completedDate;
}
