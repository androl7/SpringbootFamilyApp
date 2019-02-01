package com.example.demo;


import com.example.demo.entity.Child;
import com.example.demo.entity.Family;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

import org.junit.Test;
import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;
import java.util.Optional;


public class findOldestBabyUnitTest {

    private Family family = new Family();

    private Clock fixedClock = Clock.fixed(Instant.parse("2019-01-10T10:15:30.00Z"), ZoneId.systemDefault());


    @Test
    public void shouldReturnOldestBaby(){

        Child child1 = new Child("Andrzej","Golota","9412011221","01/06/2018","M");
        Child child2 = new Child("Andrzej","Wyrwigrosz","9412011221","01/08/2018","M");
        Child child3 = new Child("Adam","Golota","9412011221","01/01/2019","M");
        Child child4 = new Child("Adam","Golota","9412011221","01/01/2017","M");
        family.setChild(child1);
        family.setChild(child2);
        family.setChild(child3);
        family.setChild(child4);

        assertThat(family.findOldestBaby(fixedClock)).hasValue(child1);
    }

    @Test
    public void checkFamilyWithoutChildren(){
        assertEquals(Optional.empty(),family.findOldestBaby(fixedClock));
        assertThat(family.findOldestBaby(fixedClock)).isEmpty();
    }

    @Test
    public void checkFamilyWithoutBaby(){
        family.setChild(new Child("Andrzej","Wyrwigrosz","9412011221","01/08/2016","M"));
        family.setChild(new Child("Adam","Golota","9412011221","01/01/2015","M"));
        family.setChild(new Child("Adam","Golota","9412011221","01/01/2015","M"));

        assertThat(family.findOldestBaby(fixedClock)).isEmpty();
    }

}