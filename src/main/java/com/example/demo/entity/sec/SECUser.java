package com.example.demo.entity.sec;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "`sec_user`")
public class SECUser {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name ="id_sec_user")
    private Integer id;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "`sec_users_roles`",  joinColumns = @JoinColumn(name = "id_sec_user"),
            inverseJoinColumns = @JoinColumn(name = "id_sec_role"))
    private Set<SECRole> roles = new HashSet<>();

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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Set<SECRole> getRoles() {
        return roles;
    }

    public void setRoles(Set<SECRole> roles) {
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
}
