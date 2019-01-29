package com.example.demo.service;

import com.example.demo.converter.ChildConverter;
import com.example.demo.converter.FamilyConverter;
import com.example.demo.converter.FatherConverter;
import com.example.demo.dto.ChildDto;
import com.example.demo.dto.FamilyDto;
import com.example.demo.dto.FatherDto;
import com.example.demo.entity.Child;
import com.example.demo.entity.Family;
import com.example.demo.repository.ChildRepository;
import com.example.demo.repository.FamilyRepository;
import com.example.demo.repository.FatherRepository;
import com.google.common.collect.ComparisonChain;
import org.hibernate.annotations.NotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FamilyService {
    @Autowired
    FamilyRepository familyRepository;
    @Autowired
    FatherRepository fatherRepository;
    @Autowired
    ChildRepository childRepository;

    @Transactional
    public void createFamily(FamilyDto familyDto) {
        familyRepository.save(FamilyConverter.dtoToEntity(familyDto));
    }


    @Transactional
    public void addFatherToFamily(FatherDto fatherDto,Integer familyId) {
        fatherDto.setFamilyDto(FamilyConverter.entityToDto(familyRepository.getOne(familyId)));
        fatherRepository.save(FatherConverter.dtoToEntity(fatherDto));

    }

    @Transactional
    public void addChildToFamily(ChildDto childDto,Integer familyId) {
        childDto.setFamilyDto(FamilyConverter.entityToDto(familyRepository.getOne(familyId)));
        childRepository.save(ChildConverter.dtoToEntity(childDto));
    }



    @Transactional(readOnly = true)
    public ChildDto readChild(Integer familyId, Integer childId){
        return ChildConverter.entityToDto(familyRepository.getOne(familyId).readChild(childId).get());
    }

    @Transactional(readOnly = true)
    public List<ChildDto> readChildren(Integer familyId){
        return familyRepository.getOne(familyId).getChildren().stream().map(ChildConverter::entityToDto).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<ChildDto> readAllChildren(){
        return childRepository.findAll().stream().map(ChildConverter::entityToDto).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public FatherDto readFather(Integer familyId){
        if(familyRepository.getOne(familyId).getFather()!=null) {
            return FatherConverter.entityToDto(familyRepository.getOne(familyId).getFather());
        }else {
            return null;
        }
    }

    @Transactional(readOnly = true)
    public List<ChildDto> searchChild(Integer familyId, String name){
        return familyRepository.getOne(familyId).searchChild(name).stream().map(ChildConverter::entityToDto).collect(Collectors.toList());
    }


    @Transactional(readOnly = true)
    public ReadFamilyWrapper readFamily(Integer familyId){
        return new ReadFamilyWrapper(FamilyConverter.entityToDto(familyRepository.getOne(familyId)),readFather(familyId),readChildren(familyId));
    }


    @Transactional(readOnly = true)
    public String findOldestBaby(Integer familyId){
        return familyRepository.getOne(familyId).findOldestBaby();
    }

    //Date have to be in dd/mm/yyyy format
    @Transactional(readOnly = true)
    public String findOldestBabyService(Integer familyId){

        LocalDate todayDate = LocalDate.now();
        LocalDate dateYearAgo = LocalDate.of(todayDate.getYear() - 1, todayDate.getMonth(), todayDate.getDayOfMonth());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        //take all babies -> 1 year from now
        List<Child> babiesList = familyRepository.getOne(familyId).getChildren().stream().filter(child -> LocalDate.parse(child.getBirthDate(),formatter).isAfter(dateYearAgo)&&(LocalDate.parse(child.getBirthDate(),formatter)).isBefore(todayDate)).collect(Collectors.toList());

        //sort from oldest to youngest
        babiesList.sort((o1, o2) -> ComparisonChain.start()
                .compare(o1.getBirthDate().substring(6, 10),o2.getBirthDate().substring(6, 10))
                .compare(o1.getBirthDate().substring(3, 5), o2.getBirthDate().substring(3, 5))
                .compare(o1.getBirthDate().substring(0, 2), o2.getBirthDate().substring(0, 2))
                .result());

        //return oldest
        if(babiesList.size()>0) {
            return String.valueOf(Period.between(LocalDate.parse(babiesList.get(0).getBirthDate(), formatter), todayDate).getMonths());
        }else {
            return "There is no baby in this family";
        }
    }

    @Transactional(readOnly = true)
    public String findFamilyWithOldestFather(){

        //take families with Father
        List<Family> helpingArray = familyRepository.findAll().stream().filter(family -> family.getFather()!=null).collect(Collectors.toList());

        //sort from oldest to youngest
        helpingArray.sort((o1, o2) -> ComparisonChain.start()
                .compare(o1.getFather().getBirth_date().substring(6, 10),o2.getFather().getBirth_date().substring(6, 10))
                .compare(o1.getFather().getBirth_date().substring(3, 5), o2.getFather().getBirth_date().substring(3, 5))
                .compare(o1.getFather().getBirth_date().substring(0, 2), o2.getFather().getBirth_date().substring(0, 2))
                .result());

        List<Family> finalHelpingArray = helpingArray;
        helpingArray = helpingArray.stream().filter(family -> finalHelpingArray.get(0).getFather().getBirth_date().equals(family.getFather().getBirth_date())).collect(Collectors.toList());



        if(helpingArray.size()==1) {
            return helpingArray.get(0).toString();
        }else if(helpingArray.size()>1){
            StringBuilder resultStr = new StringBuilder();
            for(Family f:helpingArray){
                resultStr.append(f.toString());
                resultStr.append(" ");
            }
            return resultStr.toString();
        }else{
            return "There is no family with father in repo";
        }
    }

}
