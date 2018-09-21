package com.example.demo.entity;


//import javax.persistence.Id
import com.example.demo.entity.User;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
@Table(name = "Преподователь", catalog = "diplom_work", schema="DBO")
public class Lector extends User {


  @NotNull
  @ManyToOne(cascade = CascadeType.MERGE)
  @JoinColumn(name = "`id_Title`")
  private Title title;

  @NotNull
  @ManyToOne(cascade = CascadeType.MERGE)
  @JoinColumn(name = "`id_Degree`")
  private Degree degree;

  @NotNull
  @ManyToOne(cascade = CascadeType.MERGE)
  @JoinColumn(name = "`id_Post`")
  private Post post;


  @JsonIgnore
  @OneToMany(mappedBy = "leader", cascade = CascadeType.ALL)
  private Set<DiplomWork> diplomWorksLikeLeader;

  @JsonIgnore
  @OneToMany(mappedBy = "recensor", cascade = CascadeType.ALL)
  private Set<DiplomWork> diplomWorksLikeRecensor;

  @JsonIgnore
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

  public Title getTitle() {
    return title;
  }

  public void setTitle(Title title) {
    this.title = title;
  }

  public Degree getDegree() {
    return degree;
  }

  public void setDegree(Degree degree) {
    this.degree = degree;
  }

  public Post getPost() {
    return post;
  }

  public void setPost(Post post) {
    this.post = post;
  }

  public Set<DiplomWork> getDiplomWorksLikeLeader() {
    return diplomWorksLikeLeader;
  }

  public void setDiplomWorksLikeLeader(Set<DiplomWork> diplomWorksLikeLeader) {
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
}
