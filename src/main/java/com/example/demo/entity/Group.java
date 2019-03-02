package com.example.demo.entity;

import com.example.demo.entity.sec.SEC;
import com.example.demo.entity.sec.SECEvent;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.annotations.Formula;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "`group`")
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "`id_Group`")
    private Integer idGroup;

    @NotNull
    private String number;

    @Formula("(select count(*) from student  st where st.id_Group= id_Group)")
    @Column(name = "`amount_student`", nullable = true)
    private Integer amountStudent = 0;

    @JsonIgnore
    @ManyToMany(mappedBy = "groups",fetch = FetchType.EAGER)
    private Set<SEC> secs;

    @JsonIgnore
    @ManyToMany(mappedBy = "groups",fetch = FetchType.EAGER)
    private Set<SECEvent> secEvents;


//    @JsonBackReference
//    @ManyToOne(cascade = CascadeType.MERGE)
//    @JoinColumn(name = "id_sec")
//    private SEC sec;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_Specialization")
    private Specialization specialization;


    @JsonIgnore
    @OneToMany(mappedBy = "group", cascade = CascadeType.MERGE)
    private List<Student> students;

    public Group(String number) {
        this.number = number;
    }

    public Group() {

    }

    public Integer getIdGroup() {
        return idGroup;
    }

    public void setIdGroup(Integer idGroup) {
        this.idGroup = idGroup;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }


    public Specialization getSpecialization() {
        return specialization;
    }

    public void setSpecialization(Specialization specialization) {
        this.specialization = specialization;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }


    public Integer getAmountStudent() {
        return amountStudent;
    }

    public void setAmountStudent(Integer amountStudent) {
        this.amountStudent = amountStudent;
    }

    public Set<SEC> getSecs() {
        return secs;
    }

    public void setSecs(Set<SEC> secs) {
        this.secs = secs;
    }

    public Set<SECEvent> getSecEvents() {
        return secEvents;
    }

    public void setSecEvents(Set<SECEvent> secEvents) {
        this.secEvents = secEvents;
    }

    //    public SEC getSec() {
//        return sec;
//    }
//
//    public void setSec(SEC sec) {
//        this.sec = sec;
//    }
}
