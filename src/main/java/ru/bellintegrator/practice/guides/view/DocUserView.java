package ru.bellintegrator.practice.guides.view;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class DocUserView {
    public Integer docId;

    public Long userId;

    public DocUserView() {
    }
}
