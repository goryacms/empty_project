package ru.bellintegrator.practice.util.exceptionhandling;

public class ResourceNotValidException  extends Exception {
    public ResourceNotValidException() {
        super();
    }

    public ResourceNotValidException(final String message) {
        super(message);
    }
}
