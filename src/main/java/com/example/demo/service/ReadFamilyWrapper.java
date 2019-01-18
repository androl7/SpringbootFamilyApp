package com.example.demo.service;

import com.example.demo.dto.ChildDto;
import com.example.demo.dto.FatherDto;

import java.util.List;

public class ReadFamilyWrapper {
    private Integer familyId;
    private FatherDto fatherDto;
    private List<ChildDto> childList;

    public ReadFamilyWrapper(Integer familyId,FatherDto fatherDto, List<ChildDto> childList) {
        this.familyId = familyId;
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

    public Integer getFamilyId() {
        return familyId;
    }

    public void setFamilyId(Integer familyId) {
        this.familyId = familyId;
    }
}
