package com.example.demo;


import com.example.demo.dto.ChildDto;
import com.example.demo.dto.FamilyDto;
import com.example.demo.dto.FatherDto;
import com.example.demo.service.FamilyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class BootDemoApplication {

	@Autowired
	FamilyService familyService;

	@Autowired
	PasswordEncoder passwordEncoder;
	public static void main(String[] args) {
		SpringApplication.run(BootDemoApplication.class, args);
	}

	/*@Bean
	CommandLineRunner demo() {
		return new TestDataCommandLineRunner();
	}*/

	@PostConstruct
	public void setupDbWithData(){


		//System.out.println(passwordEncoder.encode("12345"));

		FamilyDto familyDto = new FamilyDto("Golota","$2a$10$LcbjSnXcXXoVprYWfmrmLOgo03ns/fQShjZtKiajvjnp2RnqCdU3G",true);
		familyService.createFamily(familyDto);


		FatherDto father = new FatherDto("Andrzej","Golota","9412011221","01/12/1980");
		familyService.addFatherToFamily(father,1);

		ChildDto child = new ChildDto("Andrzej","Golota","9412011221","01/06/2018","M");
		familyService.addChildToFamily(child,1);

		ChildDto child2 = new ChildDto("Andrzej","Wyrwigrosz","9412011221","01/08/2018","M");
		familyService.addChildToFamily(child2,1);

		ChildDto child3 = new ChildDto("Adam","Golota","9412011221","01/01/2019","M");
		familyService.addChildToFamily(child3,1);

	}
}
