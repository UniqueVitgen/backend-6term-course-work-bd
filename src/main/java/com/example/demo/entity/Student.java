package com.example.demo.entity;


import com.example.demo.entity.sec.SEC;
import com.example.demo.entity.sec.SECEvent;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
@Table(name="`student`")
@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(name = "create_student",
                procedureName = "dbo.create_student",
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "firstname", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "middlename", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "lastname", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "username", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "number", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.INOUT, name = "id_person", type = Integer.class)
                })
})
public class Student extends User  {

    @NotNull
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_Group")
    private Group group;

    @JsonIgnore
    @OneToOne(mappedBy = "student", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private DiplomWork diplomWork;

//    @JsonIgnore
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "id_sec_event")
//    private SECEvent secEvent;


    public Student() {

    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public DiplomWork getDiplomWork() {
        return diplomWork;
    }

    public void setDiplomWork(DiplomWork diplomWork) {
        this.diplomWork = diplomWork;
    }

//    public SECEvent getSecEvent() {
//        return secEvent;
//    }
//
//    public void setSecEvent(SECEvent secEvent) {
//        this.secEvent = secEvent;
//    }
}
