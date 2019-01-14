package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
@Table(name="`post_organization`")
public class PostOrganization {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="`id_Post_organization`")
    private Integer id;

    @NotNull
    private String name;

    @NotNull
    @Column(name = "short_name")
    private String shortName;

    @JsonIgnore
    @OneToMany(mappedBy = "postOrganization", cascade = CascadeType.ALL)
    private Set<LectorOrganization> lectors;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public Set<LectorOrganization> getLectors() {
        return lectors;
    }

    public void setLectors(Set<LectorOrganization> lectors) {
        this.lectors = lectors;
    }
}
