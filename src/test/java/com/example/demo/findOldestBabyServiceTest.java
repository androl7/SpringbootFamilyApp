package com.example.demo;


import com.example.demo.dto.ChildDto;
import com.example.demo.dto.FamilyDto;
import com.example.demo.repository.ChildRepository;
import com.example.demo.repository.FamilyRepository;
import com.example.demo.service.FamilyService;
import static org.junit.Assert.assertEquals;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("it")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class findOldestBabyServiceTest {
    @Autowired
    private
    FamilyService familyService;

    @Before
    public void setUp(){
        FamilyDto familyDto = new FamilyDto(1);
        familyService.createFamily(familyDto);
    }


    @Test
    public void checkFamilyWithoutChildren_ShouldReturnMessage(){
        assertEquals("There is no baby in this family",familyService.findOldestBabyService(1));
    }

    @Test
    public void checkFamilyWithoutBaby_ShouldReturnMessage(){
        familyService.addChildToFamily(new ChildDto("Andrzej","Wyrwigrosz","9412011221","01/08/2016","M"),1);
        familyService.addChildToFamily(new ChildDto("Adam","Golota","9412011221","01/01/2015","M"),1);
        familyService.addChildToFamily(new ChildDto("Adam","Golota","9412011221","01/01/2015","M"),1);
        assertEquals("There is no baby in this family",familyService.findOldestBabyService(1));

    }


    @Test
    public void shouldReturnOldestBaby() {

        familyService.addChildToFamily(new ChildDto("Andrzej","Golota","9412011221","01/06/2018","M"),1);
        familyService.addChildToFamily(new ChildDto("Andrzej","Wyrwigrosz","9412011221","01/08/2018","M"),1);
        familyService.addChildToFamily(new ChildDto("Adam","Golota","9412011221","01/01/2019","M"),1);
        familyService.addChildToFamily(new ChildDto("Adam","Golota","9412011221","01/01/2017","M"),1);
        assertEquals("7",familyService.findOldestBabyService(1));

    }

}






