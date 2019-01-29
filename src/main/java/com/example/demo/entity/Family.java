package com.example.demo.entity;

import com.google.common.collect.ComparisonChain;
import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Entity
@Table(name = "Family")
public class Family implements Serializable {
    @Id
    @Column(name = "family_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer familyId;


    @OneToOne(mappedBy = "family",cascade = CascadeType.MERGE)
    private Father father;


    @OneToMany(mappedBy = "family",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<Child> children= new LinkedList<>();

    public Integer getFamilyId() {
        return familyId;
    }

    public Father getFather() {
        return father;
    }

    public List<Child> getChildren() {
        return children;
    }

    public void setFamilyId(int familyId) {
        this.familyId = familyId;
    }

    public void setFather(Father father) {
        this.father = father;
    }

    public void setChildens(List<Child> childrens) {
        this.children = childrens;
    }
    public void setChild(Child child) {
        this.children.add(child);
    }

    public Family(Father father, List<Child> children) {
        this.father = father;
        this.children = children;
    }

    public Family() {
    }

    public Optional<Child> readChild(Integer childId) {
        return children.stream().filter(child -> child.getChildId().equals(childId)).findAny();
    }

    public List<Child> searchChild(String childName) {
        return children.stream().filter(child -> child.getName().equals(childName)).collect(Collectors.toList());
    }

    //Date have to be in dd/mm/yyyy format
    public String findOldestBaby() {
        LocalDate todayDate = LocalDate.now();
        LocalDate dateYearAgo = LocalDate.of(todayDate.getYear() - 1, todayDate.getMonth(), todayDate.getDayOfMonth());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        //take all babies -> 1 year from now
        List<Child> babiesList = children.stream().filter(child -> LocalDate.parse(child.getBirthDate(),formatter).isAfter(dateYearAgo)&&(LocalDate.parse(child.getBirthDate(),formatter)).isBefore(todayDate)).collect(Collectors.toList());

        //sort from oldest to youngest
        babiesList.sort((o1, o2) -> ComparisonChain.start()
                .compare(o1.getBirthDate().substring(6, 10),o2.getBirthDate().substring(6, 10))
                .compare(o1.getBirthDate().substring(3, 5), o2.getBirthDate().substring(3, 5))
                .compare(o1.getBirthDate().substring(0, 2), o2.getBirthDate().substring(0, 2))
                .result());

        //return oldest
        if(babiesList.size()>0) {
            return String.valueOf(Period.between(LocalDate.parse(babiesList.get(0).getBirthDate(), formatter), todayDate).getMonths());
        }else {
            return "There is no baby in this family";
        }
    }

    @Override
    public String toString() {
        return "FamilyID: "+familyId+" FatherName: "+father.getName()+" "+father.getSurname();
    }
}
