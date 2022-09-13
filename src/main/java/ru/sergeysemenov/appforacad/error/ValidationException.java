package ru.sergeysemenov.appforacad.error;

public class ValidationException extends RuntimeException{

    private static final String MESSAGE = "Validation Failed";

    public ValidationException(){
        super(MESSAGE);
    }
}
