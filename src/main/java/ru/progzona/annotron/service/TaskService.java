package ru.progzona.annotron.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.progzona.annotron.model.dto.TaskDto;
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
}
