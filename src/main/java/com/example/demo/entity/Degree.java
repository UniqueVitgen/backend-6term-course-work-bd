package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "ученая_степень", catalog = "diplom_work", schema="DBO")
public class Degree {
    @Id
    @GeneratedValue
    @Column(name = "id_Degree")
    private Integer idDegree;

    private String name;


    @JsonIgnore
    @OneToMany(mappedBy = "degree", cascade = CascadeType.ALL)
    private Set<Lector> lectors;


    public Degree() {

    }

    public Integer getIdDegree() {
        return idDegree;
    }

    public void setIdDegree(Integer idDegree) {
        this.idDegree = idDegree;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Lector> getLectors() {
        return lectors;
    }

    public void setLectors(Set<Lector> lectors) {
        this.lectors = lectors;
    }
}
