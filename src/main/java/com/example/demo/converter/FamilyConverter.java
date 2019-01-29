package com.example.demo.converter;

import com.example.demo.dto.FamilyDto;
import com.example.demo.entity.Family;

import java.util.stream.Collectors;

public class FamilyConverter {

    public static Family dtoToEntity(FamilyDto familyDto) {
        Family family = new Family(familyDto.getFamily_surname(),familyDto.getPassword(),familyDto.getValid());
        family.setFamilyId(familyDto.getFamilyID());
        return family;
    }

    public static FamilyDto entityToDto(Family family) {
        return new FamilyDto(family.getFamilyId(),family.getFamily_surname(),family.getPassword(),family.getValid());
    }
}
