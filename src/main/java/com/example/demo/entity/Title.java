package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
@Table(name = "Ученое_звание", catalog = "diplom_work", schema="DBO")
public class Title {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_Title")
    private Integer idTitle;

    @NotNull
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
