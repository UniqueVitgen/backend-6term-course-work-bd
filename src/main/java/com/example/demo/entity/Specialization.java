package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "Специальность", catalog = "diplom_work", schema="DBO")
public class Specialization {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "`id_Specialization`")
    private Integer idSpecialization;

    @Column(name = "`name`")
    private String name;

    private String qualification;

    @Column(name = "`code`")
    private String code;

    @ManyToOne
    @JoinColumn(name = "`id_faculty`")
    private Faculty faculty;

    @JsonIgnore
    @OneToMany(mappedBy = "specialization", cascade = CascadeType.ALL)
    private Set<Group> groups;

    public Specialization() {

    }

    public Integer getIdSpecialization() {
        return idSpecialization;
    }

    public void setIdSpecialization(Integer idSpecialization) {
        this.idSpecialization = idSpecialization;
    }

    public Faculty getFaculty() {
        return faculty;
    }

    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
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

    //    public Department getDepartment() {
//        return department;
//    }
//
//    public void setDepartment(Department department) {
//        this.department = department;
//    }
}
