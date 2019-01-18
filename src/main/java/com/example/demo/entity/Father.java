package com.example.demo.entity;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name = "Father")
public class Father implements Serializable {
    @Id
    @Column(name = "father_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer fatherId;
    @Column(name = "name")
    private String name;
    @Column(name = "surname")
    private String surname;
    @Column(name = "PESEL")
    private String PESEL;
    @Column(name = "birth_date")
    private String birth_date;
    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "family_id")
    private Family family;

    public Integer getFatherId() {
        return fatherId;
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

    public String getBirth_date() {
        return birth_date;
    }

    public Family getFamily() {
        return family;
    }

    public void setFatherId(Integer fatherId) {
        this.fatherId = fatherId;
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

    public void setBirth_date(String birth_date) {
        this.birth_date = birth_date;
    }

    public void setFamily(Family family) {
        this.family = family;
    }

    public Father(String name, String surname, String PESEL, String birth_date, Family family) {
        this.name = name;
        this.surname = surname;
        this.PESEL = PESEL;
        this.birth_date = birth_date;
        this.family = family;
    }

    public Father() {
    }

    public Father(String name, String surname, String PESEL, String birth_date) {
        this.name = name;
        this.surname = surname;
        this.PESEL = PESEL;
        this.birth_date = birth_date;
    }

}
