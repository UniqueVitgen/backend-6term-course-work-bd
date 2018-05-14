package com.example.demo.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "Кафедра", catalog = "diplom_work", schema="DBO")
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id_Department;

    private String name;

    private String short_name;

//    @ManyToOne
//    @JoinColumn(name = "id_Faculty", insertable = false, updatable = false)
//    private Faculty faculty;


//    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL)
//    private Set<Specialization> specializations;

    public Department() {

    }

    public Integer getId_Department() {
        return id_Department;
    }

    public void setId_Department(Integer id_Department) {
        this.id_Department = id_Department;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

//    public Faculty getFaculty() {
//        return faculty;
//    }
//
//    public void setFaculty(Faculty faculty) {
//        this.faculty = faculty;
//    }

    public String getShort_name() {
        return short_name;
    }

    public void setShort_name(String short_name) {
        this.short_name = short_name;
    }


}
