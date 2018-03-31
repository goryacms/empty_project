package ru.bellintegrator.practice.util.exceptionhandling;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import ru.bellintegrator.practice.util.Wrapper;


@ControllerAdvice
public class ExceptionResponse   {
    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public @ResponseBody Wrapper handleException(final Exception exception) {

        Wrapper wrap = new Wrapper();
        wrap.setError(exception.getMessage());

        return wrap;
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public @ResponseBody Wrapper handleResourceNotFound(final Exception exception) {

        Wrapper wrap = new Wrapper();
        wrap.setError(exception.getMessage());

        return wrap;
    }
}
