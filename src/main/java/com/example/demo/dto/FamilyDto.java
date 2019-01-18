package com.example.demo.dto;

import java.util.ArrayList;
import java.util.List;

public class FamilyDto {

    private int familyID;


    public int getFamilyID() {
        return familyID;
    }


    public void setFamilyID(int familyID) {
        this.familyID = familyID;
    }


    public FamilyDto(int familyID) {
        this.familyID = familyID;

    }

    public FamilyDto() {
    }
}
