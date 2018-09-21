package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name="Процентовка", catalog = "diplom_work",schema = "dbo")
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
    @Column(name = "`percent`")
    private Integer percent;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "`id_Diplom_work`")
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
