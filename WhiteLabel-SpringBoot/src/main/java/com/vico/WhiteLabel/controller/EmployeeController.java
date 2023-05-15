package com.vico.WhiteLabel.controller;

import com.vico.WhiteLabel.domain.*;
import com.vico.WhiteLabel.exception.ResourceNotFoundException;
import com.vico.WhiteLabel.repository.EmployeeRepository;
import com.vico.WhiteLabel.repository.SiteLocationRepository;
import com.vico.WhiteLabel.service.AddressService;
import com.vico.WhiteLabel.service.EmployeeService;
import com.vico.WhiteLabel.service.SiteLocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class EmployeeController {

    @Autowired
    EmployeeService service;

    @Autowired
    EmployeeRepository repository;

    @Autowired
    AddressService addressService;

    @Autowired
    SiteLocationRepository siteRepo;

    @PostMapping("/addEmployee/{siteId}")
    public Employee uploadEmployee(@RequestBody EmployeeRequest employeeRequest, @PathVariable(value = "siteId") Long siteId){

        return service.uploadEmployee(employeeRequest, siteId);
    }

    @GetMapping("/employees")
    public Iterable<Employee> getAllEmployees(){
        return service.getAll();
    }

    @GetMapping("/employee/{id}")
    public ResponseEntity<Object> getEmployeeById(@PathVariable(value = "id") Long id) throws Throwable{
        Object address = service.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found for id:: " + id));
        return ResponseEntity.ok().body(address);
    }

    @DeleteMapping("/deleteEmp/{id}")
    public Map<String, Boolean> deleteEmp(@PathVariable(value = "id") Long id)
            throws Throwable {
        Employee employee = (Employee) service.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + id));

        service.deleteEmp(id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
