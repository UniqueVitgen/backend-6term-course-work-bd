package com.example.demo.entity;

import com.example.demo.entity.User;

import javax.persistence.*;

@Entity
@Table(name="Студент", catalog = "diplom_work", schema="DBO")
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

//    @Column(name = "`group`", nullable = false)
//    private String group;


//    @ManyToOne
//    @JoinColumn(name = "id_Specialization", insertable = false, updatable = false)
//    private Specialization specialization;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_Group")
    private Group group;

    public Student() {

    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    //    public String getGroup() {
//        return group;
//    }
//
//    public void setGroup(String group) {
//        this.group = group;
//    }
}
