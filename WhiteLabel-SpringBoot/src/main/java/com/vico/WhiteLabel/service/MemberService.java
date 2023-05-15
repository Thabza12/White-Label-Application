package com.vico.WhiteLabel.service;

import com.vico.WhiteLabel.domain.*;
import com.vico.WhiteLabel.repository.AddressRepository;
import com.vico.WhiteLabel.repository.BeneficiaryRepository;
import com.vico.WhiteLabel.repository.MemberRepository;
import com.vico.WhiteLabel.repository.SubscriptionPlanRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class MemberService {

    @Autowired
    MemberRepository repository;

    @Autowired
    BeneficiaryRepository benRepo;

    @Autowired
    AddressService addressService;

    @Autowired
    AddressRepository addressRepo;

    @Autowired
    SubscriptionPlanService planService;

    @Autowired
    SubscriptionPlanRepository planRepo;

    public Member uploadMember(MemberRequest request, String planName){

        Member member = new Member(request);

        Address memberAddress = new Address(request.getAddressRequest());
        log.debug("uploading member address");
        if (request.getAddressRequest() != null){

            memberAddress.setUnitNumber(request.getAddressRequest().getUnitNumber());
            memberAddress.setStreet(request.getAddressRequest().getStreet());
            memberAddress.setCity(request.getAddressRequest().getCity());
            memberAddress.setProvince(request.getAddressRequest().getProvince());
            memberAddress.setZip(request.getAddressRequest().getZip());

            addressService.uploadAddress(memberAddress);
            member.setAddress(memberAddress);
        }

        log.debug("uploading member subscription plan");
        if (planName != null){
            SubscriptionPlan plan = planService.findByPlanName(planName);
            if (plan != null){
                member.setPlan(plan);
            }
        }

        repository.save(member);

        List<Beneficiaries> beneficiariesList = new ArrayList<>();
        if (request.getBeneficiariesRequest() != null){
            for (BeneficiariesRequest beneficiariesRequest : request.getBeneficiariesRequest()){
                Beneficiaries beneficiaries = new Beneficiaries(beneficiariesRequest);
                beneficiaries.setName(beneficiariesRequest.getName());
                beneficiaries.setSurname(beneficiariesRequest.getSurname());
                beneficiaries.setIdentityNumber(beneficiaries.getIdentityNumber());
                beneficiaries.setMobileNumber(beneficiaries.getMobileNumber());
                beneficiaries.setRelation(beneficiaries.getRelation());
                beneficiaries.setMember(member);

                beneficiariesList.add(beneficiaries);
            }

            benRepo.saveAll(beneficiariesList);

        }


        return member;
    }

    public Iterable<Member> getAll(){
        return repository.findAll();
    }

    public Optional findById(Long id){
        return repository.findById(id);
    }

    public void deleteMember(Long id) {
        Member member = repository.findById(id).get();
        member.setDeleted(true);

        for(Beneficiaries beneficiaries : member.getBeneficiaries()) {
            beneficiaries.setDeleted(true);
        }

        Address memberAddress = addressRepo.getReferenceById(id);
        memberAddress.setDeleted(true);

//        SubscriptionPlan plan = planRepo.getReferenceById(id);
//        plan.setDeleted(true);]


        repository.save(member);
    }
}
