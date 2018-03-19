package ru.bellintegrator.practice.office.view;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class OfficeView {
    @ApiModelProperty(hidden = true)
    public Long id;

    public Long orgId;

    public String name;

    public String address;

    public String phone;

    public Boolean isActive;


    public OfficeView() {
    }

    @Override
    public String toString() {
        return "{" +
                "id:" + id +
                "orgId:" + orgId +
                ";name:" + name +
                ";address:" + address +
                ";phone:" + phone +
                ";isActive:" + isActive +
                '}';
    }
}
