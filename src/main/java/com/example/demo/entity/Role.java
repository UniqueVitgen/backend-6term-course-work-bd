package com.example.demo.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;
import com.example.demo.entity.User;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "`role`")
public class Role {

    @Id
    private Integer id_Role;

    @NotNull
    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @JsonIgnore
    @ManyToMany(mappedBy = "roles",fetch = FetchType.EAGER)
    private Set<User> users;

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public Role() {
    }

    public Integer getId_Role() {
        return id_Role;
    }

    public void setId_Role(Integer id_Role) {
        this.id_Role = id_Role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



    @Override
    public String toString() {
        return "Role{" +
                "id=" + id_Role +
                ", name='" + name + '\'' +
//                ", users=" + users +
                '}';
    }
}
