package ru.progzona.annotron.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.progzona.annotron.exception.ResourceNotFoundException;
import ru.progzona.annotron.exception.TaskAlreadyAssignedException;
import ru.progzona.annotron.exception.TaskLimitExceededException;
import ru.progzona.annotron.model.dto.TaskAssignDto;
import ru.progzona.annotron.model.entity.StatusEntity;
import ru.progzona.annotron.model.entity.TaskAssignEntity;
import ru.progzona.annotron.model.entity.TaskEntity;
import ru.progzona.annotron.model.entity.UserEntity;
import ru.progzona.annotron.repository.StatusRepository;
import ru.progzona.annotron.repository.TaskAssignRepository;
import ru.progzona.annotron.repository.TaskRepository;
import ru.progzona.annotron.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final TaskRepository taskRepository;
    private final TaskAssignRepository taskAssignRepository;
    private final StatusRepository statusRepository;
    private static final int MAX_ACTIVE_TASKS_PER_USER = 5;

    @Transactional
    public TaskAssignDto assignTask(Long userId, Long taskId) {
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User with id=" + userId + " not found."));

        TaskEntity task = taskRepository.findById(taskId)
                .orElseThrow(() -> new ResourceNotFoundException("Task with id=" + taskId + " not found."));

        List<StatusEntity.StatusName> statuses = List.of(
                StatusEntity.StatusName.ASSIGNED,
                StatusEntity.StatusName.IN_PROGRESS
        );

        long activeUserTaskCount = taskAssignRepository.countByUser_IdAndStatus_NameIn(userId, statuses);
        if (activeUserTaskCount >= MAX_ACTIVE_TASKS_PER_USER) {
            throw new TaskLimitExceededException(
                    String.format("User with id=%d has reached the active task limit of %d.",
                            userId, MAX_ACTIVE_TASKS_PER_USER)
            );
        }
        if (taskAssignRepository.existsByUser_IdAndTask_Id(userId, taskId)) {
            throw new TaskAlreadyAssignedException("Task with id=" + taskId + " is already assigned to this user.");
        }

        StatusEntity assignedStatus = statusRepository.findByName(StatusEntity.StatusName.ASSIGNED)
                .orElseThrow(() -> new IllegalStateException("Critical status 'ASSIGNED' not found in the database."));

        TaskAssignEntity newAssignment = TaskAssignEntity.builder()
                .user(user)
                .task(task)
                .status(assignedStatus)
                .assignedAt(LocalDateTime.now())
                .build();

        TaskAssignEntity savedAssignment = taskAssignRepository.save(newAssignment);

        return toDto(savedAssignment);
    }

    @Transactional
    public List<TaskAssignDto> getTasksForUser(Long userId) {

        if (!userRepository.existsById(userId)) {
            throw new ResourceNotFoundException("User with id=" + userId + " not found.");
        }

        return taskAssignRepository.findByUserId(userId).stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    private TaskAssignDto toDto(TaskAssignEntity entity) {
        return new TaskAssignDto(
                entity.getId(),
                entity.getTask().getId(),
                entity.getTask().getName(),
                entity.getUser().getId(),
                entity.getUser().getUsername(),
                entity.getStatus().getName(),
                entity.getAssignedAt()
        );
    }


}
