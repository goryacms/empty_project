package ru.bellintegrator.practice.organization.view;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;
import org.h2.util.New;

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

    public String result;


    public OrganizationView() {
    }

    @Override
    public String toString() {
        return "data:{" +
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
