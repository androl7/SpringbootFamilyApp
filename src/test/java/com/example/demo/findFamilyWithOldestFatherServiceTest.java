package com.example.demo;


import com.example.demo.dto.FamilyDto;
import com.example.demo.dto.FatherDto;
import com.example.demo.service.FamilyService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;


@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("it")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class findFamilyWithOldestFatherServiceTest {

    @Autowired
    FamilyService familyService;


    @Test
    public void checkRepoWithoutFamily_ShouldReturnMessage() {
        assertEquals("There is no family with father in repo", familyService.findFamilyWithOldestFather());
    }

    @Test
    public void shouldReturnFamilyWithOldestFather() {
        FamilyDto familyDto = new FamilyDto(1);
        familyService.createFamily(familyDto);
        FamilyDto familyDto2 = new FamilyDto(2);
        familyService.createFamily(familyDto2);
        FamilyDto familyDto3 = new FamilyDto(3);
        familyService.createFamily(familyDto3);

        familyService.addFatherToFamily(new FatherDto("Maciek","Wyrwigrosz","9412011221","01/12/1990"),1);
        familyService.addFatherToFamily(new FatherDto("Jacek","Golota","9412011221","01/12/1980"),2);
        familyService.addFatherToFamily(new FatherDto("Adam","Pol","9412011221","01/12/2000"),3);

        assertEquals("FamilyID: 2 FatherName: Jacek Golota",familyService.findFamilyWithOldestFather());
    }

    @Test
    public void checkNoFathersInFamilies() {
        FamilyDto familyDto = new FamilyDto(1);
        familyService.createFamily(familyDto);
        FamilyDto familyDto2 = new FamilyDto(2);
        familyService.createFamily(familyDto2);
        FamilyDto familyDto3 = new FamilyDto(3);
        familyService.createFamily(familyDto3);

        assertEquals("There is no family with father in repo",familyService.findFamilyWithOldestFather());
    }

    @Test
    public void shouldReturnFamilyWithOldestFathers() {
        FamilyDto familyDto = new FamilyDto(1);
        familyService.createFamily(familyDto);
        FamilyDto familyDto2 = new FamilyDto(2);
        familyService.createFamily(familyDto2);
        FamilyDto familyDto3 = new FamilyDto(3);
        familyService.createFamily(familyDto3);

        familyService.addFatherToFamily(new FatherDto("Maciek","Wyrwigrosz","9412011221","01/12/1990"),1);
        familyService.addFatherToFamily(new FatherDto("Jacek","Golota","9412011221","01/12/1990"),2);
        familyService.addFatherToFamily(new FatherDto("Adam","Pol","9412011221","01/12/2000"),3);

        assertEquals("FamilyID: 1 FatherName: Maciek Wyrwigrosz FamilyID: 2 FatherName: Jacek Golota ",familyService.findFamilyWithOldestFather());
    }


}
