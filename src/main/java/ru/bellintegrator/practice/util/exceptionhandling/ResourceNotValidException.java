package ru.bellintegrator.practice.util.exceptionhandling;

public class ResourceNotValidException  extends RuntimeException {

    public ResourceNotValidException() {
        super();
    }

    public ResourceNotValidException(final String message) {
        super(message);
    }
}
