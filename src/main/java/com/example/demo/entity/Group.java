package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sun.deploy.panel.SpecialTreeListener;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "Группа", catalog = "diplom_work", schema="DBO")
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "`id_Group`")
    private Integer idGroup;

    private String number;

    private int amount_student;


    @ManyToOne
    @JoinColumn(name = "id_Specialization")
    private Specialization specialization;


    @JsonIgnore
    @OneToMany(mappedBy = "group", cascade = CascadeType.ALL)
    private Set<Student> students;

    public Group(String number) {
        this.number = number;
    }

    public Group() {

    }

    public Integer getIdGroup() {
        return idGroup;
    }

    public void setIdGroup(Integer idGroup) {
        this.idGroup = idGroup;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public int getAmount_student() {
        return amount_student;
    }

    public void setAmount_student(int amount_student) {
        this.amount_student = amount_student;
    }

    public Specialization getSpecialization() {
        return specialization;
    }

    public void setSpecialization(Specialization specialization) {
        this.specialization = specialization;
    }

    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }
}
