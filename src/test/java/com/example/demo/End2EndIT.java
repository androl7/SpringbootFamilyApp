package com.example.demo;

import com.example.demo.dto.ChildDto;
import com.example.demo.dto.FamilyDto;
import com.example.demo.dto.FatherDto;
import com.example.demo.service.FamilyService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;



import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("it")
public class End2EndIT {

	@Autowired
	private MockMvc mvc;

	@Autowired
	private FamilyService familyService;

	@Test
	public void exampleTest() throws Exception {

		FatherDto father = new FatherDto("Marek","Maly","7612011221","01/12/1976");
		ChildDto child = new ChildDto("Andrzej","Maly","9412011663","01/12/1994","M");
		ChildDto child2 = new ChildDto("Adam","Maly","9412011663","01/12/1994","M");
		ChildDto child3 = new ChildDto("Piotr","Maly","9412011663","01/12/1994","M");
		ChildDto child4 = new ChildDto("Andrzej","Maly","9412011663","01/12/1994","M");

		// stworz rodzine
		FamilyDto family = new FamilyDto();
		familyService.createFamily(family);

		// dodaj ojca
		familyService.addFatherToFamily(father,1);

		// dodaj dzieci
		familyService.addChildToFamily(child,1);
		familyService.addChildToFamily(child2,1);
		familyService.addChildToFamily(child3,1);
		familyService.addChildToFamily(child4,1);



		//sprawdz czy zapisalo poprawnie rodzine
		//check child Andrzej
		mvc.perform(get("/family/searchChild/1/Andrzej")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$[0].name").value("Andrzej"))
				.andExpect(jsonPath("$[0].surname").value("Maly"))
				.andExpect(jsonPath("$[1].name").value("Andrzej"))
				.andExpect(jsonPath("$[1].surname").value("Maly"));

		//check child Adam
		mvc.perform(get("/family/searchChild/1/Adam")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$[0].name").value("Adam"))
				.andExpect(jsonPath("$[0].surname").value("Maly"));

		//check child Piotr
		mvc.perform(get("/family/searchChild/1/Piotr")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$[0].name").value("Piotr"))
				.andExpect(jsonPath("$[0].surname").value("Maly"));


		//check father Marek
		mvc.perform(get("/family/readFather/1")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.name").value("Marek"))
				.andExpect(jsonPath("$.surname").value("Maly"));
	}

}
