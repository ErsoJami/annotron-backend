package ru.progzona.annotron.model.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.NotNull;
import ru.progzona.annotron.model.entity.StatusEntity;

import java.time.LocalDateTime;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record TaskAssignDto (
        Long id,
        Long taskId,
        String taskName,
        Long userId,
        String username,
        StatusEntity.StatusName status,
        LocalDateTime assignedAt
) {}
