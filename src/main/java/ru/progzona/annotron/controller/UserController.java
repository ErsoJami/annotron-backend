package ru.progzona.annotron.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.progzona.annotron.model.dto.TaskAssignDto;
import ru.progzona.annotron.model.dto.TaskAssignRequestDto;
import ru.progzona.annotron.service.UserService;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @PostMapping("/{userId}/assign")
    public ResponseEntity<?> assignTaskToUser(
            @PathVariable @Min(1) Long userId,
            @Valid @RequestBody TaskAssignRequestDto requestDto) {
        return new ResponseEntity<>(userService.assignTask(userId, requestDto.taskId()), HttpStatus.OK);
    }
}
