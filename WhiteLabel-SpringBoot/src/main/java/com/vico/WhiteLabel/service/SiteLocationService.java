package com.vico.WhiteLabel.service;

import com.vico.WhiteLabel.domain.Address;
import com.vico.WhiteLabel.domain.Employee;
import com.vico.WhiteLabel.domain.SiteLocation;
import com.vico.WhiteLabel.domain.SiteLocationRequest;
import com.vico.WhiteLabel.repository.AddressRepository;
import com.vico.WhiteLabel.repository.SiteLocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SiteLocationService {

    @Autowired
    SiteLocationRepository repository;

    @Autowired
    AddressRepository addressRepo;


    public SiteLocation uploadSite(SiteLocationRequest request){

        SiteLocation site = new SiteLocation(request);


        Address siteAddress = new Address(request.getAddressRequest());
        if (request.getAddressRequest() != null){

            siteAddress.setUnitNumber(request.getAddressRequest().getUnitNumber());
            siteAddress.setStreet(request.getAddressRequest().getStreet());
            siteAddress.setCity(request.getAddressRequest().getCity());
            siteAddress.setProvince(request.getAddressRequest().getProvince());
            siteAddress.setZip(request.getAddressRequest().getZip());

            addressRepo.save(siteAddress);
            site.setAddress(siteAddress);
        }

        repository.save(site);


        return site;
    }

    public Iterable<SiteLocation> getAll(){
        return repository.findAll();
    }

    public Optional findById(Long id){
        return repository.findById(id);
    }

    public SiteLocation findBySiteName(String siteName){
        return repository.findBySiteName(siteName);
    }

    public void deleteSite(Long id) {
        SiteLocation site = repository.findById(id).get();
        site.setDeleted(true);

        Address memberAddress = addressRepo.getReferenceById(id);
        memberAddress.setDeleted(true);

        repository.save(site);
    }
}
