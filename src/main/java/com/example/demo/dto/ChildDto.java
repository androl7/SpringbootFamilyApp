package com.example.demo.dto;

public class ChildDto {
    private Integer childId;
    private String name;
    private String surname;
    private String PESEL;
    private String birthDate;
    private String gender;
    private FamilyDto familyDto;

    public FamilyDto getFamilyDto() {
        return familyDto;
    }

    public void setFamilyDto(FamilyDto familyDto) {
        this.familyDto = familyDto;
    }

    public ChildDto(Integer childId, String name, String surname, String PESEL, String birthDate, String gender) {
        this.childId = childId;
        this.name = name;
        this.surname = surname;
        this.PESEL = PESEL;
        this.birthDate = birthDate;
        this.gender = gender;
    }

    public ChildDto(String name, String surname, String PESEL, String birthDate, String gender) {
        this.name = name;
        this.surname = surname;
        this.PESEL = PESEL;
        this.birthDate = birthDate;
        this.gender = gender;
    }

    public ChildDto() {
    }

    public Integer getChildId() {
        return childId;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getPESEL() {
        return PESEL;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public String getGender() {
        return gender;
    }

    public void setChildId(Integer childId) {
        this.childId = childId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setPESEL(String PESEL) {
        this.PESEL = PESEL;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }


}
