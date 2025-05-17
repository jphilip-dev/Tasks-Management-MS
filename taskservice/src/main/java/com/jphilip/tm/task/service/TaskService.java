package com.jphilip.tm.task.service;

import com.jphilip.tm.task.ErrorCode;
import com.jphilip.tm.task.dto.TaskRequestDTO;
import com.jphilip.tm.task.dto.TaskResponseDTO;
import com.jphilip.tm.task.entity.Task;
import com.jphilip.tm.task.exception.custom.TaskNotFoundException;
import com.jphilip.tm.task.mapper.TaskMapper;
import com.jphilip.tm.task.repository.TaskRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;

    public List<TaskResponseDTO> getAllTasksByAssignedTo(Long assignedTo){
        return taskRepository.findByAssignedTo(assignedTo).stream()
                .map(taskMapper::toDto)
                .toList();
    }

    public List<TaskResponseDTO> getAllTasksByAssignedBy(Long assignedBy){
        return taskRepository.findByAssignedBy(assignedBy).stream()
                .map(taskMapper::toDto)
                .toList();
    }

    public TaskResponseDTO getTaskById(Long id){

        var task = validateTaskId(id);

        return taskMapper.toDto(task);
    }

    @Transactional
    public TaskResponseDTO createTask(TaskRequestDTO taskRequestDTO) {

        Task newTask = taskMapper.toEntity(taskRequestDTO);

        taskRepository.save(newTask);

        return taskMapper.toDto(newTask);
    }

    @Transactional
    public TaskResponseDTO updateTask(TaskRequestDTO taskRequestDTO, Long id) {

        var existingTask = validateTaskId(id);

        existingTask.setAssignedTo(taskRequestDTO.getAssignedTo());
        existingTask.setTaskDescription(taskRequestDTO.getTaskDescription());
        existingTask.setTaskNotes(taskRequestDTO.getTaskNotes());
        existingTask.setCompletedDate(taskRequestDTO.getCompletedDate());

        taskRepository.save(existingTask);

        return taskMapper.toDto(existingTask);
    }

    public void deleteTask(Long id){
        taskRepository.delete(validateTaskId(id));
    }

    /*
     *
     * Helper method/s
     *
     */

    private Task validateTaskId(Long id){
        return taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException(ErrorCode.ERROR_TASK_NOT_FOUND, id.toString()));
    }
}
