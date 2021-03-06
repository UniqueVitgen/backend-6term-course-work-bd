package com.example.demo.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name="`news`")
public class News {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="`id_News`")
    private Integer id;

    @NotNull
    @Column(name = "`title`")
    private String title;

    @NotNull
    @Lob
    private String content;

    public Integer getId() {
        return id;
    }

    @NotNull
    @Column(name="created")
    private Date created;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "`id_image`")
    private ImageModelHasNews imageModel;

    @ManyToOne
    @JoinColumn(name = "`id_user`")
    private User user;

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public ImageModelHasNews getImageModel() {
        return imageModel;
    }

    public void setImageModel(ImageModelHasNews imageModel) {
        this.imageModel = imageModel;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
