package com.example.demo.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "`lector_organizer`")
public class LectorOrganization extends Lector {

    @Getter
    @Setter
    @NotNull
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "`id_Post_organization`")
    private PostOrganization postOrganization;

    @Getter
    @Setter
    @NotNull
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "`id_Organization`")
    private Organization organization;

    public PostOrganization getPostOrganization() {
        return postOrganization;
    }

    public void setPostOrganization(PostOrganization postOrganization) {
        this.postOrganization = postOrganization;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }
}
