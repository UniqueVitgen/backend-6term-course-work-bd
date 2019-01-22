package com.example.demo.entity.sec;

import com.example.demo.entity.Department;
import com.example.demo.entity.Group;
import com.example.demo.entity.Specialization;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "`sec`")
public class SEC {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "`id_sec`")
    private Integer id;


    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "`id_Department`")
    private Department department;

//    @One
//    private List<Department> departments

    @OneToMany(mappedBy = "sec", cascade = CascadeType.ALL)
    private Set<SECEvent> events;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "`sec_users_secs`", joinColumns = @JoinColumn(name = "id_sec"),
            inverseJoinColumns = @JoinColumn(name = "id_sec_user"))
    private Set<SECUser> users;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "`secs_specializations`", joinColumns = @JoinColumn(name = "id_sec"),
            inverseJoinColumns = @JoinColumn(name = "`id_Specialization`"))
    private Set<Specialization> specializations;

    @NotNull
    @Column(name="`start_date`")
    private Date startDate;


    @NotNull
    @Column(name="`end_date`")
    private Date endDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Set<SECEvent> getEvents() {
        return events;
    }

    public void setEvents(Set<SECEvent> events) {
        this.events = events;
    }

    public Set<SECUser> getUsers() {
        return users;
    }

    public void setUsers(Set<SECUser> users) {
        this.users = users;
    }

    public Set<Specialization> getSpecializations() {
        return specializations;
    }

    public void setSpecializations(Set<Specialization> specializations) {
        this.specializations = specializations;
    }
}
