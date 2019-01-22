package com.example.demo.entity;

import com.example.demo.entity.sec.SEC;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
@Table(name = "`specialization`")
public class Specialization {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "`id_Specialization`")
    private Integer idSpecialization;

    @NotNull
    @Column(name = "`name`")
    private String name;


    @NotNull
    @ManyToOne
    @JoinColumn(name = "`id_Qualification`")
    private Qualification qualification;

    @Column(name = "`code`")
    private String code;

    @ManyToOne
    @JoinColumn(name = "`id_Department`")
    private Department department;

    @JsonIgnore
    @OneToMany(mappedBy = "specialization", cascade = CascadeType.ALL)
    private Set<Group> groups;


    @JsonIgnore
    @ManyToMany(mappedBy = "specializations",fetch = FetchType.EAGER)
    private Set<SEC> secs;

    public Specialization() {

    }

    public Integer getIdSpecialization() {
        return idSpecialization;
    }

    public void setIdSpecialization(Integer idSpecialization) {
        this.idSpecialization = idSpecialization;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Qualification getQualification() {
        return qualification;
    }

    public void setQualification(Qualification qualification) {
        this.qualification = qualification;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Set<Group> getGroups() {
        return groups;
    }

    public void setGroups(Set<Group> groups) {
        this.groups = groups;
    }

    public Set<SEC> getSecs() {
        return secs;
    }

    public void setSecs(Set<SEC> secs) {
        this.secs = secs;
    }

    //    public Department getDepartment() {
//        return department;
//    }
//
//    public void setDepartment(Department department) {
//        this.department = department;
//    }
}
