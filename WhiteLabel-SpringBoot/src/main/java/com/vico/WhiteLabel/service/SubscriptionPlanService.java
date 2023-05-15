package com.vico.WhiteLabel.service;

import com.vico.WhiteLabel.domain.Address;
import com.vico.WhiteLabel.domain.Employee;
import com.vico.WhiteLabel.domain.SiteLocation;
import com.vico.WhiteLabel.domain.SubscriptionPlan;
import com.vico.WhiteLabel.repository.SubscriptionPlanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SubscriptionPlanService {

    @Autowired
    SubscriptionPlanRepository repository;

    public SubscriptionPlan uploadPlan(SubscriptionPlan plan){
        return repository.save(plan);
    }

    public Iterable<SubscriptionPlan> getAll(){
        return repository.findAll();
    }

    public Optional findById(Long id){
        return repository.findById(id);
    }

    public SubscriptionPlan findByPlanName(String planName){
        return repository.findByPlanName(planName);
    }

    public void deletePlan(Long id) {
        SubscriptionPlan plan = repository.findById(id).get();
        plan.setDeleted(true);

        repository.save(plan);
    }
}
