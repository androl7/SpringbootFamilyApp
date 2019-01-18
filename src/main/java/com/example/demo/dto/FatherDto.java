package com.example.demo.dto;


public class FatherDto {
    private Integer fatherId;
    private String name;
    private String surname;
    private String PESEL;
    private String birthDate;
    private FamilyDto familyDto;

    public FamilyDto getFamilyDto() {
        return familyDto;
    }

    public void setFamilyDto(FamilyDto familyDto) {
        this.familyDto = familyDto;
    }

    public FatherDto(Integer fatherId, String name, String surname, String PESEL, String birthDate) {
        this.fatherId = fatherId;
        this.name = name;
        this.surname = surname;
        this.PESEL = PESEL;
        this.birthDate = birthDate;

    }

    public FatherDto(String name, String surname, String PESEL, String birthDate) {
        this.name = name;
        this.surname = surname;
        this.PESEL = PESEL;
        this.birthDate = birthDate;
    }

    public FatherDto() {
    }

    public Integer getFatherId() {
        return fatherId;
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


    public void setFatherId(Integer fatherId) {
        this.fatherId = fatherId;
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

}
