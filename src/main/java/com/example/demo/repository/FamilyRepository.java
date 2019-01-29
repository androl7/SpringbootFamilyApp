package com.example.demo.repository;

import com.example.demo.entity.Child;
import com.example.demo.entity.Family;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FamilyRepository extends JpaRepository<Family,Integer> {


   @Query("SELECT family FROM Family family  WHERE family.family_surname= (:name)")
   Family findByName(@Param("name") String name);


}
