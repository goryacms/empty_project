package ru.bellintegrator.practice.organization.view;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrganizationView {
    @ApiModelProperty(hidden = true)
    public Long id;

    public String name;

    public String fullName;

    public Long inn;

    public Long kpp;

    public String address;

    public String phone;

    public Boolean isActive;


    public OrganizationView() {
    }

    @Override
    public String toString() {
        return "{" +
                "id:" + id +
                ";name:" + name +
                ";fullName:" + fullName +
                ";inn:" + inn +
                ";kpp:" + kpp +
                ";address:" + address +
                ";phone:" + phone +
                ";isActive:" + isActive +
                '}';
    }
}
