package com.example.demo.entity;


//import javax.persistence.Id
import com.example.demo.entity.User;
import javax.persistence.*;

@Entity
@Table(name = "Преподователь", catalog = "diplom_work", schema="DBO")
public class Lector extends User {


  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "id_Title", insertable = false, updatable = false)
  private Title title;

  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "`id_Degree`", insertable = false, updatable = false)
  private Degree degree;

  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "`id_Post`", insertable = false, updatable = false)
  private Post post;

  public Lector() {

  }

  public Lector(Integer idLector) {
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
}
