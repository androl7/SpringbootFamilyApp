package com.example.demo.repository;

import com.example.demo.entity.Child;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChildRepository extends JpaRepository<Child, Integer> {

    @Query("SELECT child FROM Child child  WHERE child.name= (:name)")
    List<Child> findByName(@Param("name") String name);

}
