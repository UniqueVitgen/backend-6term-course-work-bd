package com.example.demo.entity.sec;

import com.example.demo.entity.DiplomWork;
import com.example.demo.entity.Group;
import com.example.demo.entity.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Formula;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "`sec_user`")
public class SECUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="id_sec_user")
    private Integer id;

    @Formula("(select sr.priority from sec_role sr where sr.id_role in \n" +
            "            (select sur.id_sec_role from  sec_users_roles sur where sur.id_sec_user = id_sec_user)\n" +
            "            order by sr.priority asc limit 1)")
    private Integer priority;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "`id_Person`")
    private User user;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "`sec_users_roles`",  joinColumns = @JoinColumn(name = "id_sec_user"),
            inverseJoinColumns = @JoinColumn(name = "id_sec_role"))
    private List<SECRole> roles = new ArrayList<>();

    @JsonIgnore
    @ManyToMany(mappedBy = "users",fetch = FetchType.EAGER)
    private Set<SEC> secs;

    @NotNull
    @Column(nullable=false)
    protected String firstname;

    @NotNull
    @Column(nullable=false)
    protected String middlename;

    @NotNull
    @Column(nullable=false)
    protected String lastname;

    @Formula("concat(lastname, \" \", firstname, \" \", middlename)")
    protected String fullname;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<SECRole> getRoles() {
        return roles;
    }

    public void setRoles(List<SECRole> roles) {
        this.roles = roles;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getMiddlename() {
        return middlename;
    }

    public void setMiddlename(String middlename) {
        this.middlename = middlename;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Set<SEC> getSecs() {
        return secs;
    }

    public void setSecs(Set<SEC> secs) {
        this.secs = secs;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }
}
