package com.example.demo.entity.form;

import com.example.demo.entity.DiplomWork;
import com.example.demo.entity.sec.SEC;

import java.util.Date;

public class PercentageForm {

    private Integer id;

    private String name;

    private String comment;

    public Integer getId() {
        return id;
    }

    private Date startDate;

    private Date endDate;

    private Integer planPercent;

    private Integer factPercent;

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
