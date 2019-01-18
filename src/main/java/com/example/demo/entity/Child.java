package com.example.demo.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Child")
public class Child implements Serializable {
    @Id
    @Column(name = "child_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer childId;
    @Column(name = "name")
    private String name;
    @Column(name = "surname")
    private String surname;
    @Column(name = "PESEL")
    private String PESEL;
    @Column(name = "birth_date")
    private String birthDate;
    @Column(name = "gender")
    private String gender;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "family_id")
    private Family family;

    public Integer getChildId() {
        return childId;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getPESEL() {
        return PESEL;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public String getGender() {
        return gender;
    }

    public Family getFamily() {
        return family;
    }

    public void setChildId(Integer childId) {
        this.childId = childId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setPESEL(String PESEL) {
        this.PESEL = PESEL;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setFamily(Family family) {
        this.family = family;
    }

    public Child(String name, String surname, String PESEL, String birthDate, String gender, Family family) {
        this.name = name;
        this.surname = surname;
        this.PESEL = PESEL;
        this.birthDate = birthDate;
        this.gender = gender;
        this.family = family;
    }

    public Child(String name, String surname, String PESEL, String birthDate, String gender) {
        this.name = name;
        this.surname = surname;
        this.PESEL = PESEL;
        this.birthDate = birthDate;
        this.gender = gender;
    }

    public Child() {
    }
}
