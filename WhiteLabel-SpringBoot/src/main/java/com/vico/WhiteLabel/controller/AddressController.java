package com.vico.WhiteLabel.controller;

import com.vico.WhiteLabel.domain.Address;
import com.vico.WhiteLabel.exception.ResourceNotFoundException;
import com.vico.WhiteLabel.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class AddressController {

    @Autowired
    AddressService service;

    @PostMapping("/addAddress")
    public Address addAddress(@RequestBody Address address){
        return service.uploadAddress(address);
    }

    @GetMapping("/addresses")
    public Iterable<Address> getAllAddresses(){
        return service.getAll();
    }

    @GetMapping("/address/{id}")
    public ResponseEntity<Object> getAddressById(@PathVariable(value = "id") Long id) throws Throwable{
        Object address = service.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Address not found for id:: " + id));
        return ResponseEntity.ok().body(address);
    }
}
