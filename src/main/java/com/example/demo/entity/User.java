package com.example.demo.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "users", catalog = "diplom_work", schema="DBO")
public class User {
    @Id
    @GeneratedValue
    @Column(name="`id_Person`")
    private Integer idPerson;
    @Column(nullable=false)
    protected String firstname;
    @Column(nullable=false)
    protected String middlename;
    @Column(nullable=false)
    protected String lastname;
    @Column(unique=true, nullable = false)
    protected String username;
    @Column(nullable=false)
    protected String password;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles", catalog = "diplom_work", schema="DBO", joinColumns = @JoinColumn(name = "id_User"),
            inverseJoinColumns = @JoinColumn(name = "id_Role"))
    private Set<Role> roles;

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Integer getIdPerson() {
        return idPerson;
    }

    public void setIdPerson(Integer idPerson) {
        this.idPerson = idPerson;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
}
