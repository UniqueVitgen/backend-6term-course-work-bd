package com.example.demo.entity;


//import javax.persistence.Id
import com.example.demo.entity.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.Formula;

import javax.persistence.*;
import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "`lector`")
@Inheritance(strategy = InheritanceType.JOINED)
public class Lector extends User {

  @Column(name = "`max_count_of_diplom`")
  private  Integer maxCountOfDiplom;

  @Column(nullable = true)
  @Formula("(select count(*) < max_count_of_diplom from diplom_work  dw where dw.id_Leader= id_Person)")
  private Boolean free;

  @Formula("(select count(*) from diplom_work  dw where dw.id_Leader= id_Person)")
  @Column(name = "`count_of_diplom`")
  private Integer countOfDiplom;
  @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
  @OneToMany(mappedBy = "leader", cascade = CascadeType.ALL)
  private List<DiplomWork> diplomWorksLikeLeader;

  @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
  @OneToMany(mappedBy = "recensor", cascade = CascadeType.ALL)
  private Set<DiplomWork> diplomWorksLikeRecensor;

  @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
  @OneToMany(mappedBy = "scienceConsultor", cascade = CascadeType.ALL)
  private Set<DiplomWork> diplomWorksLikeScienceConsultor;

  @JsonIgnore
  @OneToMany(mappedBy = "otConsultor", cascade = CascadeType.ALL)
  private Set<DiplomWork> diplomWorksLikeOtConsultor;

  @JsonIgnore
  @OneToMany(mappedBy = "teoConsultor", cascade = CascadeType.ALL)
  private Set<DiplomWork> diplomWorksLikeTeoConsultor;

  public Lector() {

  }

  public Integer getMaxCountOfDiplom() {
    return maxCountOfDiplom;
  }

  public void setMaxCountOfDiplom(Integer maxCountOfDiplom) {
    this.maxCountOfDiplom = maxCountOfDiplom;
  }

  public List<DiplomWork> getDiplomWorksLikeLeader() {
    return diplomWorksLikeLeader;
  }

  public void setDiplomWorksLikeLeader(List<DiplomWork> diplomWorksLikeLeader) {
    this.diplomWorksLikeLeader = diplomWorksLikeLeader;
  }

  public Set<DiplomWork> getDiplomWorksLikeRecensor() {
    return diplomWorksLikeRecensor;
  }

  public void setDiplomWorksLikeRecensor(Set<DiplomWork> diplomWorksLikeRecensor) {
    this.diplomWorksLikeRecensor = diplomWorksLikeRecensor;
  }

  public Set<DiplomWork> getDiplomWorksLikeScienceConsultor() {
    return diplomWorksLikeScienceConsultor;
  }

  public void setDiplomWorksLikeScienceConsultor(Set<DiplomWork> diplomWorksLikeScienceConsultor) {
    this.diplomWorksLikeScienceConsultor = diplomWorksLikeScienceConsultor;
  }

  public Set<DiplomWork> getDiplomWorksLikeOtConsultor() {
    return diplomWorksLikeOtConsultor;
  }

  public void setDiplomWorksLikeOtConsultor(Set<DiplomWork> diplomWorksLikeOtConsultor) {
    this.diplomWorksLikeOtConsultor = diplomWorksLikeOtConsultor;
  }

  public Set<DiplomWork> getDiplomWorksLikeTeoConsultor() {
    return diplomWorksLikeTeoConsultor;
  }

  public void setDiplomWorksLikeTeoConsultor(Set<DiplomWork> diplomWorksLikeTeoConsultor) {
    this.diplomWorksLikeTeoConsultor = diplomWorksLikeTeoConsultor;
  }

  public Boolean getFree() {
    return free;
  }

  public void setFree(Boolean free) {
    this.free = free;
  }

  public Integer getCountOfDiplom() {
    return countOfDiplom;
  }

  public void setCountOfDiplom(Integer countOfDiplom) {
    this.countOfDiplom = countOfDiplom;
  }
}

