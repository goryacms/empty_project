package ru.bellintegrator.practice.guides.view;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class DocView {
    public Integer code;

    public String name;

    public DocView() {
    }
}
