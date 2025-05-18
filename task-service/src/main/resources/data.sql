INSERT INTO tasks (assigned_by, assigned_to, task_description, task_notes, assigned_date, update_date, completed_date)
SELECT 1, 2, 'Sample Task', 'Sample notes', NOW(), NOW(), NULL
WHERE NOT EXISTS (
    SELECT 1 FROM tasks WHERE task_description = 'Sample Task'
);
