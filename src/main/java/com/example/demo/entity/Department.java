package com.example.demo.entity;


import com.example.demo.entity.sec.SEC;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="`department`")
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "`id_Department`")
    private Integer id;



    @JsonIgnore
    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<SEC> secs;

    @JsonIgnore
    @OneToOne(mappedBy = "department", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private LectorUniversity lector;

    @NotNull
    private String name;

    @NotNull
    @Column(name = "`short_name`")
    private String shortName;

    @JsonIgnore
    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL)
    private Set<Specialization> specializations;

    @ManyToOne
    @JoinColumn(name = "`id_Faculty`")
    private Faculty faculty;

    @Column(name = "`disabled_edit_sec`")
    private Boolean disabledEditSec;

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

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public Set<Specialization> getSpecializations() {
        return specializations;
    }

    public void setSpecializations(Set<Specialization> specializations) {
        this.specializations = specializations;
    }

    public Faculty getFaculty() {
        return faculty;
    }

    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }

    public LectorUniversity getLector() {
        return lector;
    }

    public void setLector(LectorUniversity lector) {
        this.lector = lector;
    }

    public Set<SEC> getSecs() {
        return secs;
    }

    public void setSecs(Set<SEC> secs) {
        this.secs = secs;
    }

    public Boolean getDisabledEditSec() {
        return disabledEditSec;
    }

    public void setDisabledEditSec(Boolean disabledEditSec) {
        this.disabledEditSec = disabledEditSec;
    }
}
