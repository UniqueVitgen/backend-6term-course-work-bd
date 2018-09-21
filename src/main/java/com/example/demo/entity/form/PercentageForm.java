package com.example.demo.entity.form;

import com.example.demo.entity.DiplomWork;

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

    private Integer percent;

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

    public Integer getPercent() {
        return percent;
    }

    public void setPercent(Integer percent) {
        this.percent = percent;
    }

    public DiplomWork getDiplomWork() {
        return diplomWork;
    }

    public void setDiplomWork(DiplomWork diplomWork) {
        this.diplomWork = diplomWork;
    }
}
