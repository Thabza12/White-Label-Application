package com.vico.WhiteLabel.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "Subscriptions")
@Getter
@Setter
@ToString
public class SubscriptionPlan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String planName;
    private String description;
    private String benefits;
    private String price;
    private Boolean deleted;
    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;
    @JsonIgnore
    @OneToMany(mappedBy = "plan", cascade = CascadeType.ALL)
    private List<Member> member;

    public SubscriptionPlan() {
    }

    public SubscriptionPlan(Long id, String planName, String description, String benefits, String price) {
        this.id = id;
        this.planName = planName;
        this.description = description;
        this.benefits = benefits;
        this.price = price;
    }

    public SubscriptionPlan(SubscriptionPlanRequest planRequest){
        this.planName = planRequest.getPlanName();
        this.description = planRequest.getDescription();
        this.benefits = planRequest.getBenefits();
        this.price = planRequest.getPrice();
    }
}
