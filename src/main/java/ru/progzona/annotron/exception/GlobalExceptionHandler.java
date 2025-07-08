package ru.progzona.annotron.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<AppError> handleValidationException(MethodArgumentNotValidException e) {
        String errorMessage = e.getBindingResult().getFieldErrors().stream()
                .map(error -> "Field '" + error.getField() + "': " + error.getDefaultMessage())
                .collect(Collectors.joining("; "));

        AppError appError = new AppError(HttpStatus.BAD_REQUEST.value(), errorMessage);
        return new ResponseEntity<>(appError, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(TaskAlreadyAssignedException.class)
    public ResponseEntity<AppError> handleTaskAlreadyAssignedException(TaskAlreadyAssignedException e) {
        AppError appError = new AppError(HttpStatus.CONFLICT.value(), e.getMessage());
        return new ResponseEntity<>(appError, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<AppError> handleResourceNotFoundException(ResourceNotFoundException e) {
        AppError appError = new AppError(HttpStatus.NOT_FOUND.value(), e.getMessage());
        return new ResponseEntity<>(appError, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(TaskLimitExceededException.class)
    public ResponseEntity<AppError> handleTaskLimitExceededException(TaskLimitExceededException e) {
        AppError appError = new AppError(HttpStatus.CONFLICT.value(), e.getMessage());
        return new ResponseEntity<>(appError, HttpStatus.CONFLICT);
    }
}
