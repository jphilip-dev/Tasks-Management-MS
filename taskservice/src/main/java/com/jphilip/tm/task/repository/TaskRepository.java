package com.jphilip.tm.task.repository;

import com.jphilip.tm.task.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public interface TaskRepository extends JpaRepository<Task, Long> {
}
