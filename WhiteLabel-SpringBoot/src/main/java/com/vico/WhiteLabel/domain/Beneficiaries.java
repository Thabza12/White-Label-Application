package com.vico.WhiteLabel.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "Beneficiaries")
@Getter
@Setter
@ToString
public class Beneficiaries {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String surname;
    private String identityNumber;
    private String mobileNumber;
    private Relation relation;

    private Boolean deleted;
    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "memberId", referencedColumnName = "id", nullable = false)
    private Member member;

    public Beneficiaries() {
    }

    public Beneficiaries(Long id, String name, String surname, String identityNumber, String mobileNumber, Relation relation) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.identityNumber = identityNumber;
        this.mobileNumber = mobileNumber;
        this.relation = relation;
    }

    public Beneficiaries(BeneficiariesRequest request){
        this.name = request.getName();
        this.surname = request.getSurname();
        this.identityNumber = request.getIdentityNumber();
        this.mobileNumber = request.getMobileNumber();
        this.relation = request.getRelation();
    }
}
