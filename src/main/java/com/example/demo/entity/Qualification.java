package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
@Table(name = "`qualification`")
public class Qualification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "`id_Qualification`")
    private Integer id;

    @NotNull
    @Column(name = "`name`")
    private String name;

    @JsonIgnore
    @OneToMany(mappedBy = "qualification", cascade = CascadeType.ALL)
    private Set<Specialization> specializations;

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
}
