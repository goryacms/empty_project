package ru.bellintegrator.practice.organization.view;

import io.swagger.annotations.ApiModelProperty;

public class OrganizationView {
    @ApiModelProperty(hidden = true)
    public String id;

    public String name;

    public String fullName;

    public Long inn;

    public Long kpp;

    public String address;

    public String phone;

    public boolean isActive;


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
