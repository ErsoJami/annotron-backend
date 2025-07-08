package ru.progzona.annotron.model.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record TaskAssignRequestDto(
        @NotNull(message = "Task ID cannot be null")
        @Min(value = 1, message = "Task ID must be a positive number")
        Long taskId
) {}
