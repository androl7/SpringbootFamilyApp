package com.example.demo.dto;


import com.example.demo.entity.FamilyRole;

public class FamilyDto {

    private Integer familyID;
    private String family_surname;
    private String password;
    private Boolean valid;
    private FamilyRole familyRole;



    public int getFamilyID() {
        return familyID;
    }

    public String getFamily_surname() {
        return family_surname;
    }

    public String getPassword() {
        return password;
    }

    public Boolean getValid() {
        return valid;
    }

    public FamilyRole getFamilyRole() {
        return familyRole;
    }

    public void setFamilyRole(FamilyRole familyRole) {
        this.familyRole = familyRole;
    }

    public void setFamilyID(int familyID) {
        this.familyID = familyID;
    }

    public void setFamily_surname(String family_surname) {
        this.family_surname = family_surname;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setValid(Boolean valid) {
        this.valid = valid;
    }

    public FamilyDto(int familyID) {
        this.familyID = familyID;

    }

    public FamilyDto(int familyID, String family_surname, String password, Boolean valid, FamilyRole familyRole) {
        this.familyID = familyID;
        this.family_surname = family_surname;
        this.password = password;
        this.valid = valid;
        this.familyRole = familyRole;
    }

    public FamilyDto(String family_surname, String password, Boolean valid, FamilyRole familyRole) {
        this.family_surname = family_surname;
        this.password = password;
        this.valid = valid;
        this.familyRole = familyRole;
    }

    public FamilyDto() {
    }

}
