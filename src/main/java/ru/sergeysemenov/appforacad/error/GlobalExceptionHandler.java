package ru.sergeysemenov.appforacad.error;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.persistence.EntityNotFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<AppError> handleEntityNotFoundException(EntityNotFoundException e) {
        return new ResponseEntity<>(new AppError(404, "Item not found"), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<AppError> handleValidationFailedException(ValidationException e){
        return new ResponseEntity<>(new AppError(400, e.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<AppError> handleDataIntegrityViolationException(DataIntegrityViolationException e){
        return new ResponseEntity<>(new AppError(400, "Validation Failed"), HttpStatus.BAD_REQUEST);
    }

}
