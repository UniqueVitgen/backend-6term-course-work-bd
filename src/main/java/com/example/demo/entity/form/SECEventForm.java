package com.example.demo.entity.form;

import com.example.demo.entity.sec.SEC;

import java.util.Date;

public class SECEventForm {

    private Integer id;

    private Date date;

    private Date endDate;

    private SEC sec;

    private String address;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public SEC getSec() {
        return sec;
    }

    public void setSec(SEC sec) {
        this.sec = sec;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
