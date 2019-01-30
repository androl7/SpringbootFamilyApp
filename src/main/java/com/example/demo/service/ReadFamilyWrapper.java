package com.example.demo.service;

import com.example.demo.dto.ChildDto;
import com.example.demo.dto.FamilyDto;
import com.example.demo.dto.FatherDto;

import java.util.List;

public class ReadFamilyWrapper {
    //had to be public for ThymeLeaf
     public FamilyDto familyDto;
     public FatherDto fatherDto;
     public List<ChildDto> childList;

    public ReadFamilyWrapper(FamilyDto familyDto,FatherDto fatherDto, List<ChildDto> childList) {
        this.familyDto = familyDto;
        this.fatherDto = fatherDto;
        this.childList = childList;
    }

    public ReadFamilyWrapper() {
    }

    public FatherDto getFatherDto() {
        return fatherDto;
    }

    public List<ChildDto> getChildList() {
        return childList;
    }

    public void setFatherDto(FatherDto fatherDto) {
        this.fatherDto = fatherDto;
    }

    public void setChildList(List<ChildDto> childDtoList) {
        this.childList = childDtoList;
    }

    public FamilyDto getFamily() {
        return familyDto;
    }

    public void setFamily(FamilyDto familyDto) {
        this.familyDto = familyDto;
    }
}
