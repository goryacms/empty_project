package ru.bellintegrator.practice.users.view;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserView {
    @ApiModelProperty
    public Long id;

    public String firstName;

    public String secondName;

    public String middleName;

    public String position;

    public String phone;

    public String docName;

    public Long docNumber;

    public Date docDate;

    public String citizenshipName;

    public Integer citizenshipCode;

    public Boolean isIdentified;

    public Double salary;

    public Date registrationDate;


    public UserView() {
    }

    @Override
    public String toString() {
        return "{" +
                "id:" + id +
                ";firstName:" + firstName +
                ";secondName:" + secondName +
                ";middleName:" + middleName +
                ";position:" + position +
                ";phone:" + phone +
                ";docName:" + docName +
                ";docNumber:" + docNumber +
                ";docDate:" + docDate +
                ";citizenshipName:" + citizenshipName +
                ";citizenshipCode:" + citizenshipCode +
                ";isIdentified:" + isIdentified +
                ";salary:" + salary +
                ";registrationDate:" + registrationDate +
                '}';
    }
}
