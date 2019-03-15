package com.example.demo.entity.form;

import com.example.demo.entity.Group;
import com.example.demo.entity.Student;
import com.example.demo.entity.sec.SEC;

import java.util.Date;
import java.util.List;
import java.util.Set;

public class SECEventForm {

    private Integer id;

    private Date date;

    private Date endDate;

    private SEC sec;

    private String address;

    private Set<Group> groups;

    private Set<Student> students;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public SEC getSec() {
        return sec;
    }

    public void setSec(SEC sec) {
        this.sec = sec;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Set<Group> getGroups() {
        return groups;
    }

    public void setGroups(Set<Group> groups) {
        this.groups = groups;
    }

    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }
}
