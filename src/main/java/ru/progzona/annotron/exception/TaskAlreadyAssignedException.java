package ru.progzona.annotron.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class TaskAlreadyAssignedException extends RuntimeException {
    public TaskAlreadyAssignedException(String message) {
        super(message);
    }
}