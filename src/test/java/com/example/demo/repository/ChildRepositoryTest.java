package com.example.demo.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import com.example.demo.entity.Child;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest

public class ChildRepositoryTest {
    @Autowired
    ChildRepository childRepository;
    @Test
    public void it_can_find_child_by_name(){
        childRepository.save(new Child("Adam","Golota","9412011221","01/12/1980","M"));
        childRepository.save(new Child("Mariusz","Golota","9412011221","01/12/1980","M"));
        childRepository.save(new Child("Franek","Golota","9412011221","01/12/1980","M"));

        List<Child> childenFraneks = childRepository.findByName("Franek");

        assertEquals("Franek",childenFraneks.get(0).getName());
        assertEquals("Golota",childenFraneks.get(0).getSurname());
        assertEquals("9412011221",childenFraneks.get(0).getPESEL());
        assertEquals("01/12/1980",childenFraneks.get(0).getBirthDate());
        assertEquals("M",childenFraneks.get(0).getGender());
    }
}
