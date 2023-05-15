package com.vico.WhiteLabel.service;

import com.vico.WhiteLabel.domain.Address;
import com.vico.WhiteLabel.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AddressService {

    @Autowired
    AddressRepository repository;

    public Address uploadAddress(Address address){
        repository.save(address);
        return address;
    }

    public Iterable<Address> getAll(){
        return repository.findAll();
    }

    public Optional findById(Long id){
        return repository.findById(id);
    }



}
