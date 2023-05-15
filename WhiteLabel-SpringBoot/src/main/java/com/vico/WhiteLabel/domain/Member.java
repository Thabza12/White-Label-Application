package com.vico.WhiteLabel.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Members")
@Getter
@Setter
@ToString
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String surname;
    private MaritalStatus maritalStatus;
    private String identityNumber;
    private String policyNumber;
    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "addressId", referencedColumnName = "id")
    private Address address;
    private String mobileNumber;
//    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "plan", referencedColumnName = "id", nullable = false)
    private SubscriptionPlan plan;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date commencementDate;
    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    private Boolean deleted;
//    @JsonIgnore
    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<Beneficiaries> beneficiaries;

    public Member() {
    }

    public Member(Long id, String name, String surname, MaritalStatus maritalStatus, String identityNumber, String policyNumber, String mobileNumber, Date commencementDate, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.maritalStatus = maritalStatus;
        this.identityNumber = identityNumber;
        this.policyNumber = policyNumber;
        this.mobileNumber = mobileNumber;
        this.commencementDate = commencementDate;
    }

    public Member(MemberRequest memberRequest){
        this.name = memberRequest.getName();
        this.surname = memberRequest.getSurname();
        this.maritalStatus = memberRequest.getMaritalStatus();
        this.identityNumber = memberRequest.getIdentityNumber();
        this.policyNumber = memberRequest.getPolicyNumber();
        this.mobileNumber = memberRequest.getMobileNumber();
        this.commencementDate = memberRequest.getCommencementDate();

    }
}
