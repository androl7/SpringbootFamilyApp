package com.example.demo.controller;

import com.example.demo.service.FamilyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@RequestMapping("/")
@Controller
public class FamilyController {
    @Autowired
    private FamilyService familyService;

    @RequestMapping("/readChildren")
    public String readAllChildren(Model model){
        model.addAttribute("children",familyService.readAllChildren());
        return "Children";
    }

    @RequestMapping("/readFamilies")
    public String readFamily(Model model){
        model.addAttribute("families",familyService.readFamilies());
        return "FamiliesTab";
    }
}
