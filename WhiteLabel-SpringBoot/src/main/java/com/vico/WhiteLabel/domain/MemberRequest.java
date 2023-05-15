package com.vico.WhiteLabel.domain;

import lombok.Getter;
import lombok.Setter;
import java.util.Date;
import java.util.List;

@Getter
@Setter
public class MemberRequest {

    private String name;
    private String surname;
    private MaritalStatus maritalStatus;
    private String identityNumber;
    private String policyNumber;
    private AddressRequest addressRequest;
    private String mobileNumber;
    private SubscriptionPlanRequest planRequest;
    private Date commencementDate;
    private List<BeneficiariesRequest> beneficiariesRequest;
}
