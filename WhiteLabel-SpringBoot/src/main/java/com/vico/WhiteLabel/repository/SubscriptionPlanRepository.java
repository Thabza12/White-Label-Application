package com.vico.WhiteLabel.repository;

import com.vico.WhiteLabel.domain.SubscriptionPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubscriptionPlanRepository extends JpaRepository<SubscriptionPlan, Long> {


    SubscriptionPlan findByPlanName(String planName);
}
