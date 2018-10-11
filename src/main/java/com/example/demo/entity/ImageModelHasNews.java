package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="`image_has_news`")
public class ImageModelHasNews extends ImageModel {

    @JsonIgnore
    @OneToMany(mappedBy = "imageModel", cascade = CascadeType.ALL)
    private Set<News> news;

    public Set<News> getNews() {
        return news;
    }

    public void setNews(Set<News> news) {
        this.news = news;
    }
}
