package com.jphilip.tm.task.mapper;

import com.jphilip.tm.task.dto.TaskRequestDTO;
import com.jphilip.tm.task.dto.TaskResponseDTO;
import com.jphilip.tm.task.entity.Task;
import org.springframework.stereotype.Component;

@Component
public class TaskMapper {
    public TaskResponseDTO toDto(Task task){
        return TaskResponseDTO.builder()
                .id(task.getId())
                .assignedTo(task.getAssignedTo())
                .assignedBy(task.getAssignedBy())
                .taskDescription(task.getTaskDescription())
                .taskNotes(task.getTaskNotes())
                .assignedDate(task.getAssignedDate())
                .updateDate(task.getUpdateDate())
                .completedDate(task.getCompletedDate())
                .build();
    }

    public Task toEntity(TaskRequestDTO taskRequestDTO){

        // assignedDate and updateDate are handled by jpa audit

        return Task.builder()
                .assignedBy(taskRequestDTO.getAssignedBy())
                .assignedTo(taskRequestDTO.getAssignedTo())
                .taskDescription(taskRequestDTO.getTaskDescription())
                .taskNotes(taskRequestDTO.getTaskNotes())
                .completedDate(taskRequestDTO.getCompletedDate())
                .build();
    }
}
