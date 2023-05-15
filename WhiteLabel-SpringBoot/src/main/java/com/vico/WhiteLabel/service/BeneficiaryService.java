package com.vico.WhiteLabel.service;

import com.vico.WhiteLabel.domain.Address;
import com.vico.WhiteLabel.domain.Beneficiaries;
import com.vico.WhiteLabel.domain.Member;
import com.vico.WhiteLabel.repository.BeneficiaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BeneficiaryService {

    @Autowired
    BeneficiaryRepository repository;

    public Beneficiaries addBeneficiaryToMember(Beneficiaries beneficiaries, Member member){
        repository.save(beneficiaries);
        beneficiaries.setMember(member);
        return beneficiaries;
    }


    public Iterable<Beneficiaries> getAll(){
        return repository.findAll();
    }

    public Optional findById(Long id){
        return repository.findById(id);
    }
}
