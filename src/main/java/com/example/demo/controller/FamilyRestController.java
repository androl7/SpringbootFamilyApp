package com.example.demo.controller;

import com.example.demo.dto.ChildDto;
import com.example.demo.dto.FamilyDto;
import com.example.demo.dto.FatherDto;
import com.example.demo.service.FamilyService;
import com.example.demo.service.ReadFamilyWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/family")
@RestController
public class FamilyRestController {
    @Autowired
    private FamilyService familyService;


    @RequestMapping("/readChildrenRest")
    public List<ChildDto> readAllChildren(){
        return familyService.readAllChildren();
    }

    @PostMapping("/createFamily")
    public void createFamily(@RequestBody FamilyDto familyDto) {
        familyService.createFamily(familyDto);
    }

    @PostMapping(value = "/addFatherToFamily")
    public void addFatherToFamily(@PathVariable Integer familyId, @RequestBody FatherDto fatherDto) {
        familyService.addFatherToFamily(fatherDto,familyId);
    }

    @PostMapping(value = "/addChildToFamily")
    public void addFatherToFamily(@PathVariable Integer familyId, @RequestBody ChildDto childDto) {
        familyService.addChildToFamily(childDto,familyId);
    }

    @RequestMapping("/searchChild/{familyId}/{name}")
    public List<ChildDto> searchChild(@PathVariable Integer familyId, @PathVariable String name){
        return familyService.searchChild(familyId,name);
    }
    @RequestMapping("/readFather/{familyId}")
    public FatherDto readFather(@PathVariable Integer familyId){
        return familyService.readFather(familyId);
    }

    @RequestMapping("/readChild/{familyId}/{childId}")
    public ChildDto readChild(@PathVariable Integer familyId,@PathVariable Integer childId){
        return familyService.readChild(familyId,childId);
    }

    @RequestMapping("/readChildren/{familyId}")
    public List<ChildDto> readChildren(@PathVariable Integer familyId){
        return familyService.readChildren(familyId);
    }

    @RequestMapping("/readFamilyRest/{familyId}")
    public ReadFamilyWrapper readFamily(@PathVariable Integer familyId){
        return familyService.readFamily(familyId);
    }

    @RequestMapping("/{familyId}/oldestBaby")
    public String findOldestBaby(@PathVariable Integer familyId){
        return familyService.findOldestBaby(familyId);
    }


    @RequestMapping("/WithOldestFather")
    public String findFamilyWithOldestFather(){
        return familyService.findFamilyWithOldestFather();
    }

}


