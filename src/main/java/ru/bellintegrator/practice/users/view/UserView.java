package ru.bellintegrator.practice.users.view;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserView {
    @ApiModelProperty
    public Long id;

    public Long officeId;

    public String officeName;

    public String firstName;

    public String secondName;

    public String middleName;

    public String fullName;

    public String position;

    public String phone;

    public Integer docCode;

    public String docName;

    public Long docNumber;

    public String docDate;

    public String citizenshipName;

    public Long citizenshipCode;

    public Boolean isIdentified;

    public Double salary;

    public String registrationDate;

    public String result;


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
