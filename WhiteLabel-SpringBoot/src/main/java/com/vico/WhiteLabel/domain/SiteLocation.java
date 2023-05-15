package com.vico.WhiteLabel.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Entity
@Table(name = "SiteLocation")
@Getter
@Setter
@ToString
public class SiteLocation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String siteName;
    private Boolean deleted;
    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "addressId", referencedColumnName = "id")
    private Address address;
    @JsonIgnore
    @OneToMany(mappedBy = "location", cascade = CascadeType.ALL)
    private List<Employee> employee;

    public SiteLocation() {
    }

    public SiteLocation(Long id, String siteName) {
        this.id = id;
        this.siteName = siteName;
    }

    public SiteLocation(SiteLocationRequest request){
        this.siteName = request.getSiteName();
    }
}
