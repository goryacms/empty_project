package ru.bellintegrator.practice.util.exceptionhandling;

public class ResourceInternalException extends RuntimeException {
    public ResourceInternalException() {
        super();
    }

    public ResourceInternalException(final String message) {
        super(message);
    }
}