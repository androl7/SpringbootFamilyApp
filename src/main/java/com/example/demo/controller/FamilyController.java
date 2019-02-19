package com.example.demo.controller;

import com.example.demo.service.FamilyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collection;
import java.util.List;


@RequestMapping("/family")
@Controller
public class FamilyController {
    @Autowired
    private FamilyService familyService;

    @Autowired
    private List<ReadFamilyMapper> readFamilyMappers;

    Authentication authentication;

    @RequestMapping("/readChildren")
    public String readAllChildren(Model model){
        model.addAttribute("children",familyService.readAllChildren());
        return "Children";
    }

    @RequestMapping("/readFamilies")
    public String readFamily(Model model){
        /*authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_" + FamilyRole.PREMIUM.toString()))) {
            model.addAttribute("families", familyService.readFamilies());
            return "FamiliesTab";
        }else {
            model.addAttribute("familyDetails",familyService.readFamily(familyService.findFamilyBySurname(authentication.getName()).getFamilyId()));
            return "FamilyDetails";
        }*/
//        Collection<? extends GrantedAuthority> authorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
//        return readFamilyMappers.stream().filter(mapper -> mapper.isForRole(authorities)).map(mapper -> mapper.readFamily(model)).findAny().orElse("");
        model.addAttribute("families", familyService.readFamilies());
        return "FamiliesTab";
    }

    @RequestMapping("/readFamily/{familyId}")
    public String readFamily(@PathVariable Integer familyId,Model model){
        model.addAttribute("familyDetails",familyService.readFamily(familyId));
        return "FamilyDetails";
    }

}