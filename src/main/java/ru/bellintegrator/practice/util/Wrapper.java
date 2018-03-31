package ru.bellintegrator.practice.util;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@JsonSerialize
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Wrapper<T> {

    private Object data;
    private Object error;

    public void setData(Object data) {
        this.data = data;
    }

    public void setError(Object error) {
        this.error = error;
    }

    public Object getData() {
        return data;
    }

    public Object getError() {
        return error;
    }

}