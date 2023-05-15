package com.vico.WhiteLabel.repository;

import com.vico.WhiteLabel.domain.SiteLocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SiteLocationRepository extends JpaRepository<com.vico.WhiteLabel.domain.SiteLocation, Long> {
    SiteLocation findBySiteName(String siteName);

    SiteLocation findByIdIs(Long id);

}
