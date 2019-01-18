package com.example.demo.converter;

import com.example.demo.dto.FatherDto;
import com.example.demo.entity.Father;


public class FatherConverter {
    public static Father dtoToEntity(FatherDto fatherDto) {
        Father father = new Father(fatherDto.getName(),fatherDto.getSurname(),fatherDto.getPESEL(),fatherDto.getBirthDate());
        father.setFamily(FamilyConverter.dtoToEntity(fatherDto.getFamilyDto()));
        return father;
    }

    public static FatherDto entityToDto(Father father) {
        FatherDto fatherDto = new FatherDto(father.getFatherId(), father.getName(),father.getSurname(),father.getPESEL(),father.getBirth_date());
        fatherDto.setFamilyDto(FamilyConverter.entityToDto(father.getFamily()));
        return fatherDto;
    }
}
