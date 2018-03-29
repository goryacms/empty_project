package ru.bellintegrator.practice.register.view;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class RegisterView {
    @ApiModelProperty(hidden = true)
    public Long id;

    public String login;

    public String password;

    public String name;

    public String email;

    public String code;

    public RegisterView() {
    }
}
