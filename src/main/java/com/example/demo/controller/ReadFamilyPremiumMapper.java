package com.example.demo.controller;

import com.example.demo.entity.FamilyRole;
import com.example.demo.service.FamilyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import java.util.Collection;

@Component
public class ReadFamilyPremiumMapper implements ReadFamilyMapper {

    @Autowired
    FamilyService familyService;

    @Override
    public boolean isForRole(Collection<? extends GrantedAuthority> authorities) {
        return authorities.contains(new SimpleGrantedAuthority("ROLE_" + FamilyRole.PREMIUM.toString()));
    }

    @Override
    public String readFamily(Model model) {
        model.addAttribute("families", familyService.readFamilies());
        return "FamiliesTab";
    }
}
