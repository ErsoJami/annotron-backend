package ru.progzona.annotron.model.config;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public abstract class Config {
    @NotBlank(message = "The component name cannot be empty.")
    @Size(min = 1, max = 50, message = "The component name must be between 1 and 50 characters long.")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
