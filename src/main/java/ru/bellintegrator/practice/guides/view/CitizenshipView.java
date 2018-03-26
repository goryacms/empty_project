package ru.bellintegrator.practice.guides.view;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CitizenshipView {
    public Long code;

    public String name;

    public CitizenshipView() {
    }
}
