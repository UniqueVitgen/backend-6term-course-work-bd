package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="`Изображение_с_новостью`", catalog = "diplom_work", schema="DBO")
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
