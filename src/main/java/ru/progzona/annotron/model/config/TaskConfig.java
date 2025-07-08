package ru.progzona.annotron.model.config;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;

public record TaskConfig (
        @Valid
        @NotEmpty(message = "The input component must have at least one element.")
        List<InputConfig> inputs,
        @Valid
        @NotEmpty(message = "The output component must have at least one element.")
        List<OutputConfig> outputs
) {}
