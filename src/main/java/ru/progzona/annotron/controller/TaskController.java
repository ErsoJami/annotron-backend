package ru.progzona.annotron.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.progzona.annotron.model.dto.TaskDto;
import ru.progzona.annotron.service.TaskService;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;

    @PostMapping("/create")
    public ResponseEntity<?> createTask(@Valid @RequestBody TaskDto taskDto) {
        return new ResponseEntity<>(taskService.createTask(taskDto),
                HttpStatus.CREATED);
    }


    @PutMapping("/{id}/status")
    public ResponseEntity<?> completingTask(@Valid @PathVariable @Min(value = 1, message = "The ID must be a positive number.") Long id) {
        return new ResponseEntity<>(
                HttpStatus.OK);
    }


}
