package com.example.demo;


import com.example.demo.dto.ChildDto;
import com.example.demo.dto.FamilyDto;
import com.example.demo.dto.FatherDto;
import com.example.demo.entity.FamilyRole;
import com.example.demo.service.FamilyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.PostConstruct;
import javax.validation.ClockProvider;
import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;

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

	@Bean
	public ClockProvider clockProvider() {
		return Clock::systemDefaultZone;
	}

	@PostConstruct
	public void setupDbWithData(){


		//System.out.println(passwordEncoder.encode("12345"));

		/*FamilyDto familyDto = new FamilyDto("Golota","$2a$10$LcbjSnXcXXoVprYWfmrmLOgo03ns/fQShjZtKiajvjnp2RnqCdU3G",true, FamilyRole.PREMIUM);
		familyService.createFamily(familyDto);

		FamilyDto familyDto2 = new FamilyDto("Malysz","$2a$10$LcbjSnXcXXoVprYWfmrmLOgo03ns/fQShjZtKiajvjnp2RnqCdU3G",true, FamilyRole.NORMAL);
		familyService.createFamily(familyDto2);


		FatherDto father = new FatherDto("Andrzej","Golota","9412011221","01/12/1980");
		familyService.addFatherToFamily(father,1);

		ChildDto child = new ChildDto("Roman","Golota","9412011221","01/06/2018","M");
		familyService.addChildToFamily(child,1);

		ChildDto child2 = new ChildDto("Krystyna","Golota","9412011221","01/08/2018","K");
		familyService.addChildToFamily(child2,1);

		ChildDto child3 = new ChildDto("Adam","Golota","9412011221","01/01/2019","M");
		familyService.addChildToFamily(child3,1);


		FatherDto father2 = new FatherDto("Mariusz","Malysz","9412011221","01/12/1980");
		familyService.addFatherToFamily(father2,2);

		ChildDto child4 = new ChildDto("Krystyna","Malysz","9412011221","01/08/2018","K");
		familyService.addChildToFamily(child4,2);

		ChildDto child5 = new ChildDto("Adam","Malysz","9412011221","01/01/2019","M");
		familyService.addChildToFamily(child5,2);*/

	}
}
