package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
@Table(name = "`degree`")
public class Degree {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_Degree")
    private Integer id;

    @NotNull
    private String name;

    @NotNull
    @Column(name = "short_name")
    private String shortName;

    @JsonIgnore
    @OneToMany(mappedBy = "degree", cascade = CascadeType.ALL)
    private Set<LectorUniversity> lectors;


    public Degree() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<LectorUniversity> getLectors() {
        return lectors;
    }

    public void setLectors(Set<LectorUniversity> lectors) {
        this.lectors = lectors;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }
}
