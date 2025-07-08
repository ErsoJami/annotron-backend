CREATE TABLE tasks
(
    id          BIGSERIAL PRIMARY KEY,
    name        VARCHAR(255) NOT NULL,
    description TEXT,
    task_config JSONB
);

CREATE TABLE status
(
    id          BIGSERIAL,
    name VARCHAR(50) NOT NULL UNIQUE,
    PRIMARY KEY (id)
);

CREATE TABLE users
(
    id       BIGSERIAL PRIMARY KEY,
    username VARCHAR(255) NOT NULL UNIQUE
);

CREATE TABLE task_assignments
(
    id          BIGSERIAL PRIMARY KEY,
    task_id     BIGINT NOT NULL,
    user_id     BIGINT NOT NULL,
    status_id   BIGINT NOT NULL,
    assigned_at TIMESTAMP WITHOUT TIME ZONE NOT NULL,

    CONSTRAINT fk_task FOREIGN KEY (task_id) REFERENCES tasks (id),
    CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES users (id),
    CONSTRAINT fk_status FOREIGN KEY (status_id) REFERENCES status (id)
);



INSERT INTO status (name)
VALUES ('ASSIGNED'),
       ('IN_PROGRESS'),
       ('COMPLETED'),
       ('REJECTED');