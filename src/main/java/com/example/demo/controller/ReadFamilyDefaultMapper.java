package com.example.demo.controller;

import com.example.demo.entity.FamilyRole;
import com.example.demo.service.FamilyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import java.util.Collection;

@Component
public class ReadFamilyDefaultMapper implements ReadFamilyMapper {

    @Autowired
    FamilyService familyService;


    @Override
    public boolean isForRole(Collection<? extends GrantedAuthority> authorities) {
        return authorities.contains(new SimpleGrantedAuthority("ROLE_" + FamilyRole.NORMAL.toString()));
    }

    @Override
    public String readFamily(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("familyDetails",familyService.readFamily(familyService.findFamilyBySurname(authentication.getName()).getFamilyId()));
        return "FamilyDetails";
    }
}
