package com.vico.WhiteLabel.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressRequest {

    private String unitNumber;
    private String street;
    private String city;
    private String province;
    private String zip;
}
