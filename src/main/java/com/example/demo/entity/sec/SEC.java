package com.example.demo.entity.sec;

import com.example.demo.entity.Group;
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

    @OneToMany(mappedBy = "sec", cascade = CascadeType.ALL)
    private List<Group> groups;

    @OneToMany(mappedBy = "sec", cascade = CascadeType.ALL)
    private Set<SECEvent> events;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "`sec_users_secs`", joinColumns = @JoinColumn(name = "id_sec"),
            inverseJoinColumns = @JoinColumn(name = "id_sec_user"))
    private Set<SECUser> users;

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

    public List<Group> getGroups() {
        return groups;
    }

    public void setGroups(List<Group> groups) {
        this.groups = groups;
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
}
