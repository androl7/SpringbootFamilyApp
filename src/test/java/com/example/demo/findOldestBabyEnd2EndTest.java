package com.example.demo;

import com.example.demo.dto.ChildDto;
import com.example.demo.dto.FamilyDto;
import com.example.demo.service.FamilyService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("it")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class findOldestBabyEnd2EndTest {
    @Autowired
    private MockMvc mvc;

    @Autowired
    private FamilyService familyService;

    @Before
    public void setUp(){
        FamilyDto familyDto = new FamilyDto(1);
        familyService.createFamily(familyDto);
    }

    @Test
    public void checkFamilyWithoutChildren_ShouldReturnMessage() throws Exception {
        mvc.perform(get("/family/1/oldestBabyService")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value("There is no baby in this family"));
    }

    @Test
    public void checkFamilyWithoutBaby_ShouldReturnMessage() throws Exception {
        familyService.addChildToFamily(new ChildDto("Andrzej","Wyrwigrosz","9412011221","01/08/2016","M"),1);
        familyService.addChildToFamily(new ChildDto("Adam","Golota","9412011221","01/01/2015","M"),1);
        familyService.addChildToFamily(new ChildDto("Adam","Golota","9412011221","01/01/2015","M"),1);
        mvc.perform(get("/family/1/oldestBabyService")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value("There is no baby in this family"));

    }
    @Test
    public void checkFamilyWithoutChildren() throws Exception {

        familyService.addChildToFamily(new ChildDto("Andrzej","Golota","9412011221","01/06/2018","M"),1);
        familyService.addChildToFamily(new ChildDto("Andrzej","Wyrwigrosz","9412011221","01/08/2018","M"),1);
        familyService.addChildToFamily(new ChildDto("Adam","Golota","9412011221","01/01/2019","M"),1);
        familyService.addChildToFamily(new ChildDto("Adam","Golota","9412011221","01/01/2017","M"),1);

        mvc.perform(get("/family/1/oldestBabyService")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value("7"));
    }


}
