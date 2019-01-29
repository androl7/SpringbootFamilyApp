package com.example.demo;

import com.example.demo.entity.Child;
import com.example.demo.entity.Family;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;


import org.hamcrest.CoreMatchers;
import org.junit.Test;


public class findOldestBabyUnitTest {

    private Family family = new Family();

    @Test
    public void shouldReturnOldestBaby(){
        family.setChild(new Child("Andrzej","Golota","9412011221","01/06/2018","M"));
        family.setChild(new Child("Andrzej","Wyrwigrosz","9412011221","01/08/2018","M"));
        family.setChild(new Child("Adam","Golota","9412011221","01/01/2019","M"));
        family.setChild(new Child("Adam","Golota","9412011221","01/01/2017","M"));

        assertEquals("7",family.findOldestBaby());
    }

    @Test
    public void checkFamilyWithoutChildren(){
        assertEquals("There is no baby in this family",family.findOldestBaby());
        assertThat(family.findOldestBaby(), CoreMatchers.containsString("There is no baby in this family"));
    }

    @Test
    public void checkFamilyWithoutBaby(){
        family.setChild(new Child("Andrzej","Wyrwigrosz","9412011221","01/08/2016","M"));
        family.setChild(new Child("Adam","Golota","9412011221","01/01/2015","M"));
        family.setChild(new Child("Adam","Golota","9412011221","01/01/2015","M"));

        assertEquals("There is no baby in this family",family.findOldestBaby());
    }



}