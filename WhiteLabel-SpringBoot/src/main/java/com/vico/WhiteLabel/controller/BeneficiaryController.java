package com.vico.WhiteLabel.controller;

import com.vico.WhiteLabel.domain.Beneficiaries;
import com.vico.WhiteLabel.exception.ResourceNotFoundException;
import com.vico.WhiteLabel.service.BeneficiaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class BeneficiaryController {

    @Autowired
    BeneficiaryService service;

    @GetMapping("/beneficiaries")
    public Iterable<Beneficiaries> getAllBeneficiaries(){
        return service.getAll();
    }

    @GetMapping("/beneficiary/{id}")
    public ResponseEntity<Object> getBeneficiaryById(@PathVariable(value = "id") Long id) throws Throwable{
        Object address = service.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Beneficiaries not found for id:: " + id));
        return ResponseEntity.ok().body(address);
    }
}
