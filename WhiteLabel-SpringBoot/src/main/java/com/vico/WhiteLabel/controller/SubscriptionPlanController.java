package com.vico.WhiteLabel.controller;

import com.vico.WhiteLabel.domain.Member;
import com.vico.WhiteLabel.domain.SubscriptionPlan;
import com.vico.WhiteLabel.exception.ResourceNotFoundException;
import com.vico.WhiteLabel.service.SubscriptionPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class SubscriptionPlanController {

    @Autowired
    SubscriptionPlanService service;

    @PostMapping("/addPlan")
    public SubscriptionPlan uploadPlan(@RequestBody SubscriptionPlan plan){
        return service.uploadPlan(plan);
    }

    @GetMapping("/plans")
    public Iterable<SubscriptionPlan> getAllPlans(){
        return service.getAll();
    }

    @GetMapping("/plan/{id}")
    public ResponseEntity<Object> getPlanById(@PathVariable(value = "id") Long id) throws Throwable{
        Object address = service.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Subscription Plan not found for id:: " + id));
        return ResponseEntity.ok().body(address);
    }

    @DeleteMapping("/deletePlan/{id}")
    public Map<String, Boolean> deletePlan(@PathVariable(value = "id") Long id)
            throws Throwable {
        SubscriptionPlan plan = (SubscriptionPlan) service.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Subscription Plan not found for this id :: " + id));

        service.deletePlan(id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
