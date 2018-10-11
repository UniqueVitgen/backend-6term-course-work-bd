package com.example.demo.entity.sec;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "`sec_event`")
public class SECEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "`id_sec_event`")
    private Integer id;

    @NotNull
    @Column(name="`date`")
    private Date date;


    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "id_SEC")
    private SEC sec;

    @NotNull
    @Column(name="address")
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
}
