package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "ученое_звание", catalog = "diplom_work", schema="DBO")
public class Title {
    @Id
    @GeneratedValue
    @Column(name = "id_Title")
    private Integer idTitle;

    private String name;

    @JsonIgnore
    @OneToMany(mappedBy = "title", cascade = CascadeType.ALL)
    private Set<Lector> lectors;


    public Title() {

    }

    public Title(String name) {
        this.name = name;
    }

    public Integer getIdTitle() {
        return idTitle;
    }

    public void setIdTitle(Integer idTitle) {
        this.idTitle = idTitle;
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
