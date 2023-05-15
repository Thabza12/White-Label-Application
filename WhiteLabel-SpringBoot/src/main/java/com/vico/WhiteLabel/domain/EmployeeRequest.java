package com.vico.WhiteLabel.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class EmployeeRequest {

    private String name;
    private String surname;
    private MaritalStatus maritalStatus;
    private SiteLocationRequest locationRequest;
    private String department;
    private Date commencementDate;
    private AddressRequest addressRequest;
}
