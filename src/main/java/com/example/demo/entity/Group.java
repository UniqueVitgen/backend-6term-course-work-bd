package com.example.demo.entity;

import com.example.demo.entity.sec.SEC;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sun.deploy.panel.SpecialTreeListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "`group`")
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "`id_Group`")
    private Integer idGroup;

    @NotNull
    private String number;

    @NotNull
    @Column(name = "`amount_student`")
    private int amountStudent;


    @JsonBackReference
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_sec")
    private SEC sec;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_Specialization")
    private Specialization specialization;


    @JsonIgnore
    @OneToMany(mappedBy = "group", cascade = CascadeType.MERGE)
    private List<Student> students;

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
        return amountStudent;
    }

    public void setAmount_student(int amount_student) {
        this.amountStudent = amount_student;
    }

    public Specialization getSpecialization() {
        return specialization;
    }

    public void setSpecialization(Specialization specialization) {
        this.specialization = specialization;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public int getAmountStudent() {
        return amountStudent;
    }

    public void setAmountStudent(int amountStudent) {
        this.amountStudent = amountStudent;
    }

    public SEC getSec() {
        return sec;
    }

    public void setSec(SEC sec) {
        this.sec = sec;
    }
}
