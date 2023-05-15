package com.vico.WhiteLabel.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "Employees")
@Getter
@Setter
@ToString
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String surname;
    private MaritalStatus maritalStatus;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "siteLocationId", referencedColumnName = "id", nullable = false)
    private SiteLocation location;
    private String department;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date commencementDate;

    private Boolean deleted;
//    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "addressId", referencedColumnName = "id", nullable = false)
    private Address address;
    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public Employee() {
    }

    public Employee(Long id, String name, String surname, MaritalStatus maritalStatus, String department, Date commencementDate) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.maritalStatus = maritalStatus;
        this.department = department;
        this.commencementDate = commencementDate;
    }

    public Employee(EmployeeRequest request){
        this.name = request.getName();
        this.surname = request.getSurname();
        this.maritalStatus = request.getMaritalStatus();
        this.department = request.getDepartment();
        this.commencementDate = request.getCommencementDate();
    }
}
