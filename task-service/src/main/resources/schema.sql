CREATE TABLE IF NOT EXISTS tasks (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    assigned_by BIGINT NOT NULL,
    assigned_to BIGINT NOT NULL,
    task_description TEXT NOT NULL,
    task_notes TEXT NOT NULL,
    assigned_date TIMESTAMP NOT NULL,
    update_date TIMESTAMP NOT NULL,
    completed_date TIMESTAMP NULL
);
