package com.jphilip.tm.task.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TaskRequestDTO {

    @NotNull
    private Long assignedBy;

    @NotNull
    private Long assignedTo;

    @NotBlank
    private String taskDescription;

    @NotBlank
    private String taskNotes;

    private LocalDateTime completedDate;
}
