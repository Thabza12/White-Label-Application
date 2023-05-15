package com.vico.WhiteLabel.repository;

import com.vico.WhiteLabel.domain.Address;
import com.vico.WhiteLabel.domain.Employee;
import com.vico.WhiteLabel.domain.Member;
import com.vico.WhiteLabel.domain.SiteLocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

    Address findByEmployee(Employee employee);

    Address findByLocation(SiteLocation site);

    Address findByMember(Member member);


}
