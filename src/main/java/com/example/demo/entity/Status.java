package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;


@Entity
@Table(name="`status`")
public class Status {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="`id_Status`")
    private Integer id;

    @NotNull
    @Column(name = "`name`")
    private String name;

    @Lob
    private String comment;

    public Integer getId() {
        return id;
    }

    @JsonIgnore
    @OneToOne(mappedBy = "status", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private DiplomWork diplomWork;

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public DiplomWork getDiplomWork() {
        return diplomWork;
    }

    public void setDiplomWork(DiplomWork diplomWork) {
        this.diplomWork = diplomWork;
    }
}
