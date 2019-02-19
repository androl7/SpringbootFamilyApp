package com.example.demo.entity;

import com.google.common.collect.ComparisonChain;
import org.springframework.data.domain.Persistable;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Clock;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

@Entity
@Table(name = "Family")
public class Family implements Serializable, Persistable {
    @Id
    @Column(name = "family_id")
//  @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer familyId = 2;

    @Column(name = "family_surname")
    private String family_surname;

    @Column(name = "password")
    private String password;

    @Column(name = "role")
    private FamilyRole familyRole;

    @Column(name = "valid")
    private Boolean valid;

    @Transient
    private boolean isNew = false;


    @OneToOne(mappedBy = "family", cascade = CascadeType.MERGE)
    private Father father;


    @OneToMany(mappedBy = "family", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Child> children = new LinkedList<>();

    public Integer getFamilyId() {
        return familyId;
    }

    public Father getFather() {
        return father;
    }

    public List<Child> getChildren() {
        return children;
    }

    public String getFamily_surname() {
        return family_surname;
    }

    public String getPassword() {
        return password;
    }

    public Boolean getValid() {
        return valid;
    }

    public FamilyRole getFamilyRole() {
        return familyRole;
    }

    public void setFamilyRole(FamilyRole familyRole) {
        this.familyRole = familyRole;
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

    public void setFamily_surname(String family_surname) {
        this.family_surname = family_surname;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setValid(Boolean valid) {
        this.valid = valid;
    }

    public void setNew(boolean aNew) {
        isNew = aNew;
    }

    public Family(Father father, List<Child> children) {
        this.father = father;
        this.children = children;
    }

    public Family(String family_surname, String password, Boolean valid, FamilyRole familyRole,Boolean isNew) {
        this.family_surname = family_surname;
        this.password = password;
        this.valid = valid;
        this.familyRole = familyRole;
        this.isNew = isNew;
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
    public Optional<Child> findOldestBaby(Clock clock) {
        LocalDate todayDate = LocalDate.now(clock);
        LocalDate dateYearAgo = LocalDate.of(todayDate.getYear() - 1, todayDate.getMonth(), todayDate.getDayOfMonth());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        //take all babies -> 1 year from now and sort oldest to youngest
        List<Child> babiesList = children.stream()
                .filter(child -> LocalDate.parse(child.getBirthDate(), formatter).isAfter(dateYearAgo) && (LocalDate.parse(child.getBirthDate(), formatter)).isBefore(todayDate))
                .sorted((o1, o2) -> ComparisonChain.start()
                        .compare(o1.getBirthDate().substring(6, 10), o2.getBirthDate().substring(6, 10))
                        .compare(o1.getBirthDate().substring(3, 5), o2.getBirthDate().substring(3, 5))
                        .compare(o1.getBirthDate().substring(0, 2), o2.getBirthDate().substring(0, 2))
                        .result()).collect(Collectors.toList());

        //return oldest
        return (!babiesList.isEmpty()) ? Optional.of(babiesList.get(0)) : Optional.empty();
    }

    @Override
    public String toString() {
        return "FamilyID: " + familyId + " FatherName: " + father.getName() + " " + father.getSurname();
    }

    @Override
    public Integer getId() {
        return familyId;
    }

    @Override
    public boolean isNew() {
        return isNew;
    }
}
