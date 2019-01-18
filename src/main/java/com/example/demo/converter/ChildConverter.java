package com.example.demo.converter;

import com.example.demo.dto.ChildDto;
import com.example.demo.entity.Child;


public class ChildConverter {
    public static Child dtoToEntity(ChildDto childDto) {
        Child child = new Child(childDto.getName(),childDto.getSurname(),childDto.getPESEL(),childDto.getBirthDate(),childDto.getGender());
        child.setFamily(FamilyConverter.dtoToEntity(childDto.getFamilyDto()));
        return child;
    }

    public static ChildDto entityToDto(Child child) {
        ChildDto childDto = new ChildDto(child.getChildId(), child.getName(),child.getSurname(),child.getPESEL(),child.getBirthDate(),child.getGender());
        childDto.setFamilyDto(FamilyConverter.entityToDto(child.getFamily()));
        return childDto;
    }
}
