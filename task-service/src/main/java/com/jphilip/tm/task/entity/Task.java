package com.jphilip.tm.task.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Table(name = "tasks")
@EntityListeners(AuditingEntityListener.class)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long assignedBy;

    @Column(nullable = false)
    private Long assignedTo;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String taskDescription;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String taskNotes;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime assignedDate;

    @LastModifiedDate
    @Column(nullable = false)
    private LocalDateTime updateDate;

    private LocalDateTime completedDate;

}
