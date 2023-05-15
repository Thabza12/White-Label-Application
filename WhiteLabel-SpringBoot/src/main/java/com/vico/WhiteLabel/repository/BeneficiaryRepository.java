package com.vico.WhiteLabel.repository;

import com.vico.WhiteLabel.domain.Beneficiaries;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BeneficiaryRepository extends JpaRepository<Beneficiaries, Long> {
}
