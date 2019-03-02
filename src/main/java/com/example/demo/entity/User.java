package com.example.demo.entity;

import com.example.demo.entity.sec.SECUser;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Formula;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "`user`")
@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(name = "in_only_test",
                procedureName = "test_pkg.in_only_test",
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "inParam1", type = String.class)
                }),
        @NamedStoredProcedureQuery(name = "in_and_out_test",
                procedureName = "test_pkg.in_and_out_test",
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "inParam1", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.OUT, name = "outParam1", type = String.class)
                })
})
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="`id_Person`")
    private Integer idPerson;

    @NotNull
    @Column(nullable=false)
    protected String firstname;

    @Formula("left(firstname,1)")
    @Column(name = "`firstname_initial`")
    protected String firstnameInitial;

    @NotNull
    @Column(nullable=false)
    protected String middlename;


    @Formula("left(middlename,1)")
    @Column(name = "`middlename_initial`")
    protected String middlenameInitial;


    @NotNull
    @Column(nullable=false)
    protected String lastname;

    @Formula("concat(lastname, \" \", firstname, \" \", middlename)")
    protected String fullname;

    @NotNull
    @Column(unique=true, nullable = false)
    protected String username;

    @NotNull
    @Column(nullable=false)
    protected String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "`users_roles`", catalog = "diplom_work", schema="DBO", joinColumns = @JoinColumn(name = "id_User"),
            inverseJoinColumns = @JoinColumn(name = "id_Role"))
    private Set<Role> roles;

    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<News> news;

    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<SECUser> secUsers;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "`id_image`")
    private ImageModelHasUser imageModel;

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

    public Set<News> getNews() {
        return news;
    }

    public void setNews(Set<News> news) {
        this.news = news;
    }

    public Set<SECUser> getSecUsers() {
        return secUsers;
    }

    public void setSecUsers(Set<SECUser> secUsers) {
        this.secUsers = secUsers;
    }

    public String getFirstnameInitial() {
        return firstnameInitial;
    }

    public void setFirstnameInitial(String firstnameInitial) {
        this.firstnameInitial = firstnameInitial;
    }

    public String getMiddlenameInitial() {
        return middlenameInitial;
    }

    public void setMiddlenameInitial(String middlenameInitial) {
        this.middlenameInitial = middlenameInitial;
    }

    public ImageModelHasUser getImageModel() {
        return imageModel;
    }

    public void setImageModel(ImageModelHasUser imageModel) {
        this.imageModel = imageModel;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }
}
