package com.vico.WhiteLabel.service;

import com.vico.WhiteLabel.domain.*;
import com.vico.WhiteLabel.repository.AddressRepository;
import com.vico.WhiteLabel.repository.EmployeeRepository;
import com.vico.WhiteLabel.repository.SiteLocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    EmployeeRepository repository;

    @Autowired
    AddressService addressService;

    @Autowired
    AddressRepository addressRepo;

    @Autowired
    SiteLocationRepository siteRepo;

    public Employee uploadEmployee(EmployeeRequest employeeRequest, Long siteId){

        Employee employee = new Employee(employeeRequest);

        Address employeeAddress = new Address(employeeRequest.getAddressRequest());
        if (employeeRequest.getAddressRequest() != null){
            employeeAddress.setUnitNumber(employeeRequest.getAddressRequest().getUnitNumber());
            employeeAddress.setStreet(employeeRequest.getAddressRequest().getStreet());
            employeeAddress.setCity(employeeRequest.getAddressRequest().getCity());
            employeeAddress.setProvince(employeeRequest.getAddressRequest().getProvince());
            employeeAddress.setZip(employeeRequest.getAddressRequest().getZip());
            addressService.uploadAddress(employeeAddress);
            employee.setAddress(employeeAddress);
        }

        if (siteId != null){
            SiteLocation site = siteRepo.findByIdIs(siteId);
            employee.setLocation(site);
        }

        repository.save(employee);



        return employee;
    }


    public Iterable<Employee> getAll(){
        return repository.findAll();
    }

    public Optional<Employee> findById(Long id){
        return repository.findById(id);
    }

    public void deleteEmp(Long id) {
        Employee employee = repository.findById(id).get();
        employee.setDeleted(true);

        Address empAddress = addressRepo.findByEmployee(employee);
        empAddress.setDeleted(true);

        repository.save(employee);
    }
}
