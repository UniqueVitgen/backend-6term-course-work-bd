package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "Факультет", catalog = "diplom_work", schema="DBO")
public class Faculty {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_faculty")
    private Integer idFaculty;

    private String name;

    private String shortName;

    @JsonIgnore
    @OneToMany(mappedBy = "faculty", cascade = CascadeType.ALL)
    private Set<Specialization> specializations;

    public Faculty() {

    }

//    public Integer getId_Group() {
//        return id_Group;
//    }
//
//    public void setId_Group(Integer id_Group) {
//        this.id_Group = id_Group;
//    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getIdFaculty() {
        return idFaculty;
    }

    public void setIdFaculty(Integer idFaculty) {
        this.idFaculty = idFaculty;
    }

    public Set<Specialization> getSpecializations() {
        return specializations;
    }

    public void setSpecializations(Set<Specialization> specializations) {
        this.specializations = specializations;
    }

    //    public Set<Group> getGroups() {
//        return groups;
//    }
//
//    public void setGroups(Set<Group> groups) {
//        this.groups = groups;
//    }

    //    public String getShort_name() {
//        return short_name;
//    }
//
//    public void setShort_name(String short_name) {
//        this.short_name = short_name;
//    }

//    public Set<Department> getDepartments() {
//        return departments;
//    }
//
//    public void setDepartments(Set<Department> departments) {
//        this.departments = departments;
//    }
}
