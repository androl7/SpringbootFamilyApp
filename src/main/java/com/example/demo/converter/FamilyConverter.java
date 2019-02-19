package com.example.demo.converter;

import com.example.demo.dto.FamilyDto;
import com.example.demo.entity.Family;

public class FamilyConverter {

    public static Family dtoToEntity(FamilyDto familyDto) {
        return new Family(familyDto.getFamily_surname(),familyDto.getPassword(),familyDto.getValid(),familyDto.getFamilyRole(),true);
    }

    public static FamilyDto entityToDto(Family family) {
        return new FamilyDto(family.getFamilyId(),family.getFamily_surname(),family.getPassword(),family.getValid(),family.getFamilyRole());
    }
}
