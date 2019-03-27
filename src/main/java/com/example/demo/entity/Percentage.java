package com.example.demo.entity;

import com.example.demo.entity.sec.SEC;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name="`percentage`")
public class Percentage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="`id_Percentage`")
    private Integer id;

    @NotNull
    @Column(name = "`name`")
    private String name;

    @Lob
    private String comment;

    public Integer getId() {
        return id;
    }

    @NotNull
    @Column(name="`start_date`")
    private Date startDate;

    @NotNull
    @Column(name="`end_date`")
    private Date endDate;

    @NotNull
    @Column(name = "`plan_percent`")
    private Integer planPercent;

    @Column(name = "`fact_percent`")
    private Integer factPercent;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "`id_sec`")
    private SEC sec;

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

    public Integer getPlanPercent() {
        return planPercent;
    }

    public void setPlanPercent(Integer planPercent) {
        this.planPercent = planPercent;
    }

    public Integer getFactPercent() {
        return factPercent;
    }

    public void setFactPercent(Integer factPercent) {
        this.factPercent = factPercent;
    }

    public SEC getSec() {
        return sec;
    }

    public void setSec(SEC sec) {
        this.sec = sec;
    }
}
