package com.example.demo.entity.sec;

import com.example.demo.entity.Group;
import com.example.demo.entity.Student;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "`sec_event`")
public class SECEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "`id_sec_event`")
    private Integer id;

    @NotNull
    @Column(name="`date`")
    private Date date;

    @Column(name = "`end_date`")
    private Date endDate;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "id_SEC")
    private SEC sec;

    @NotNull
    @Column(name="address")
    private String address;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="sec_event_id")
    private Set<Student> students;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "`sec_events_groups`", joinColumns = @JoinColumn(name = "id_sec_event"),
            inverseJoinColumns = @JoinColumn(name = "`id_Group`"))
    private Set<Group> groups;

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
