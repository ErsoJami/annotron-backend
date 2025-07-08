package ru.progzona.annotron.model.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import ru.progzona.annotron.model.config.TaskConfig;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record TaskDto(
        @NotBlank(message = "The 'name' field cannot be empty.")
        String name,
        @NotBlank(message = "The 'description' field cannot be empty.")
        String description,
        @NotNull(message = "The 'task_config' field cannot be missing.")
        @Valid
        TaskConfig taskConfig
) {}
