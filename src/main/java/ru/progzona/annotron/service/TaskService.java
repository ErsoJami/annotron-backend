package ru.progzona.annotron.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.progzona.annotron.exception.ResourceNotFoundException;
import ru.progzona.annotron.model.config.TaskConfig;
import ru.progzona.annotron.model.dto.TaskAssignDto;
import ru.progzona.annotron.model.dto.TaskDto;
import ru.progzona.annotron.model.entity.TaskAssignEntity;
import ru.progzona.annotron.model.entity.TaskEntity;
import ru.progzona.annotron.repository.TaskRepository;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;
    private final ObjectMapper objectMapper;

    @Transactional
    public TaskEntity createTask(TaskDto taskDto) {
        TaskEntity task = TaskEntity.builder()
                .name(taskDto.name())
                .description(taskDto.description())
                .taskConfig(objectMapper.valueToTree(taskDto.taskConfig()))
                .build();
        return taskRepository.save(task);
    }

    @Transactional
    public TaskDto getTask(Long taskId) {
        return toDto(taskRepository.findById(taskId)
                .orElseThrow(() -> new ResourceNotFoundException("Task with id=" + taskId + " not found.")));
    }

    private TaskDto toDto(TaskEntity entity) {
        TaskConfig taskConfig;
        try {
            taskConfig = objectMapper.treeToValue(entity.getTaskConfig(), TaskConfig.class);
        } catch (JsonProcessingException e) {
            throw new IllegalStateException("Failed to parse task_config JSON from database", e);
        }
        return new TaskDto(
                entity.getName(),
                entity.getDescription(),
                taskConfig
        );
    }
}
