package com.vico.WhiteLabel.controller;

import com.vico.WhiteLabel.domain.Member;
import com.vico.WhiteLabel.domain.MemberRequest;
import com.vico.WhiteLabel.exception.ResourceNotFoundException;
import com.vico.WhiteLabel.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
@Slf4j
public class MemberController {

    @Autowired
    MemberService service;

    @PostMapping("/addMember/{planName}")
    public Member uploadMember(@RequestBody MemberRequest member, @PathVariable(value = "planName") String planName){
        log.debug("Uploading Member");
        return service.uploadMember(member, planName);
    }

    @GetMapping("/members")
    public Iterable<Member> getAllMembers(){
        return service.getAll();
    }

    @GetMapping("/member/{id}")
    public ResponseEntity<Object> getMemberById(@PathVariable(value = "id") Long id) throws Throwable{
        Object address = service.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Member not found for id:: " + id));
        return ResponseEntity.ok().body(address);
    }

    @DeleteMapping("/deleteMember/{id}")
    public Map<String, Boolean> deleteMember(@PathVariable(value = "id") Long id)
            throws Throwable {
        Member member = (Member) service.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Member not found for this id :: " + id));

        service.deleteMember(id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
