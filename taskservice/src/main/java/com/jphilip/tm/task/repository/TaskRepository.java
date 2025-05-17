package com.jphilip.tm.task.repository;

import com.jphilip.tm.task.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findByAssignedTo(Long assignedTo);

    List<Task> findByAssignedBy(Long assignedBy);
}
