package com.vico.WhiteLabel.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SubscriptionPlanRequest {

    private String planName;
    private String description;
    private String benefits;
    private String price;
}
