package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
@Table(name="`Дипломная_работа`", catalog = "diplom_work", schema="DBO")
public class DiplomWork {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "`id_Diplom_work`")
    private Integer id;

    @NotNull
    private String name;

    @Lob
    private String comment;

    @NotNull
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "`id_Student`")
    private Student student;

    @NotNull
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "`id_Leader`")
    private Lector leader;


    @NotNull
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "`id_Recensor`")
    private Lector recensor;

    @NotNull
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "`id_Science_consultor`")
    private Lector scienceConsultor;

    @NotNull
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "`id_Ot_consultor`")
    private Lector otConsultor;

    @NotNull
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "`id_Teo_consultor`")
    private Lector teoConsultor;


    @OneToMany(mappedBy = "diplomWork", cascade = CascadeType.ALL)
    @OrderBy("startDate ASC")
    private Set<Percentage> percentages;

    @NotNull
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "`id_Status`")
    private Status status;

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

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Lector getLeader() {
        return leader;
    }

    public void setLeader(Lector leader) {
        this.leader = leader;
    }

    public Lector getRecensor() {
        return recensor;
    }

    public void setRecensor(Lector recensor) {
        this.recensor = recensor;
    }

    public Lector getScienceConsultor() {
        return scienceConsultor;
    }

    public void setScienceConsultor(Lector scienceConsultor) {
        this.scienceConsultor = scienceConsultor;
    }

    public Lector getOtConsultor() {
        return otConsultor;
    }

    public void setOtConsultor(Lector otConsultor) {
        this.otConsultor = otConsultor;
    }

    public Lector getTeoConsultor() {
        return teoConsultor;
    }

    public void setTeoConsultor(Lector teoConsultor) {
        this.teoConsultor = teoConsultor;
    }

    public Set<Percentage> getPercentages() {
        return percentages;
    }

    public void setPercentages(Set<Percentage> percentages) {
        this.percentages = percentages;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
