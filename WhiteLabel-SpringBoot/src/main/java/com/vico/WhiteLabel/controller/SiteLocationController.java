package com.vico.WhiteLabel.controller;

import com.vico.WhiteLabel.domain.Address;
import com.vico.WhiteLabel.domain.Member;
import com.vico.WhiteLabel.domain.SiteLocation;
import com.vico.WhiteLabel.domain.SiteLocationRequest;
import com.vico.WhiteLabel.exception.ResourceNotFoundException;
import com.vico.WhiteLabel.service.SiteLocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class SiteLocationController {

    @Autowired
    SiteLocationService service;

    @PostMapping("/addSite")
    public SiteLocation uploadSite(@RequestBody SiteLocationRequest request){
        return service.uploadSite(request);
    }

    @GetMapping("/sites")
    public Iterable<SiteLocation> getAllSites(){
        return service.getAll();
    }

    @GetMapping("/site/{id}")
    public ResponseEntity<Object> getSiteById(@PathVariable(value = "id") Long id) throws Throwable{
        Object address = service.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Site Location not found for id:: " + id));
        return ResponseEntity.ok().body(address);
    }

    @DeleteMapping("/deleteSite/{id}")
    public Map<String, Boolean> deleteSite(@PathVariable(value = "id") Long id)
            throws Throwable {
        SiteLocation site = (SiteLocation) service.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Site Location not found for this id :: " + id));

        service.deleteSite(id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
