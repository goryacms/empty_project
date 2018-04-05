package ru.bellintegrator.practice.util.exceptionhandling;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import ru.bellintegrator.practice.util.Wrapper;


@ControllerAdvice
public class ExceptionResponse   {
    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public @ResponseBody Wrapper handleResourceNotFound(final ResourceNotFoundException exception) {

        Wrapper wrap = new Wrapper();
        wrap.setError(exception.getMessage());

        return wrap;
    }

    @ExceptionHandler(ResourceNotValidException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public @ResponseBody Wrapper handleResourceNotValid(final ResourceNotValidException exception) {

        Wrapper wrap = new Wrapper();
        wrap.setError(exception.getMessage());

        return wrap;
    }

    /*@ExceptionHandler(value = {Exception.class})
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public @ResponseBody Wrapper handleException(final Exception exception) {

        Wrapper wrap = new Wrapper();
        wrap.setError(exception.getMessage());

        return wrap;
    }*/

    @ExceptionHandler(value = {ResourceInternalException.class})
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public @ResponseBody Wrapper handleInternalException(final ResourceInternalException exception) {

        Wrapper wrap = new Wrapper();
        wrap.setError(exception.getMessage());

        return wrap;
    }

}
