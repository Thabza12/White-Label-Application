package com.vico.WhiteLabel.domain;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "Address")
@Getter
@Setter
@ToString
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String unitNumber;
    private String street;
    private String city;
    private String province;
    private String zip;

    private Boolean deleted;

//    @JsonIgnore
    @OneToOne(cascade =  CascadeType.ALL, mappedBy = "address")
    private SiteLocation location;

//    @JsonIgnore
    @OneToOne(cascade =  CascadeType.ALL, mappedBy = "address")
    private Member member;

//    @JsonIgnore
    @OneToOne(cascade =  CascadeType.ALL, mappedBy = "address")
    private Employee employee;

    public Address() {
    }

    public Address(Long id, String unitNumber, String street, String city, String province, String zip) {
        this.id = id;
        this.unitNumber = unitNumber;
        this.street = street;
        this.city = city;
        this.province = province;
        this.zip = zip;
    }

    public Address(AddressRequest request) {
        this.unitNumber = request.getUnitNumber();
        this.street = request.getStreet();
        this.city = request.getCity();
        this.province = request.getProvince();
        this.zip = request.getZip();
    }


}
