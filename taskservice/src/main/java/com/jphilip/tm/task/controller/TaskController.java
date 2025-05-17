package com.jphilip.tm.task.controller;

import com.jphilip.tm.task.dto.TaskRequestDTO;
import com.jphilip.tm.task.dto.TaskResponseDTO;
import com.jphilip.tm.task.service.TaskService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
@RequiredArgsConstructor
public class TaskController {
    private final TaskService taskService;

    @GetMapping("/assigned-to/{assignedTo}")
    @ResponseStatus(HttpStatus.OK)
    public List<TaskResponseDTO> getAllTaskByAssignedTo(@PathVariable Long assignedTo){
        return taskService.getAllTasksByAssignedTo(assignedTo);
    }

    @GetMapping("/assigned-by/{assignedBy}")
    @ResponseStatus(HttpStatus.OK)
    public List<TaskResponseDTO> getAllTaskByAssignedBy(@PathVariable Long assignedBy){
        return taskService.getAllTasksByAssignedBy(assignedBy);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public TaskResponseDTO getTaskById(@PathVariable Long id){
        return taskService.getTaskById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TaskResponseDTO createTask(@Valid @RequestBody TaskRequestDTO taskRequestDTO){
        return taskService.createTask(taskRequestDTO);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public TaskResponseDTO updateTask(@Valid @RequestBody TaskRequestDTO taskRequestDTO, @PathVariable Long id){
        return  taskService.updateTask(taskRequestDTO, id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTask(@PathVariable Long id){
        taskService.deleteTask(id);
    }


}
