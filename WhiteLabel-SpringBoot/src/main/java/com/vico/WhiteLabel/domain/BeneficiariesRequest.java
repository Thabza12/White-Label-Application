package com.vico.WhiteLabel.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BeneficiariesRequest {

    private String name;
    private String surname;
    private String identityNumber;
    private String mobileNumber;
    private Relation relation;
}
