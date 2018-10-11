package com.example.demo.entity;

import java.sql.Blob;
import java.util.Date;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name="`iamge`")
public class ImageModel {

    @Id
    @Column(name="`id_Image`")
    private Integer id;


    @Column(name="description")
    private String description;

    @NotNull
    @Column(name="filename")
    private String filename;

    @NotNull
    @Column(name="`content_image`")
    @Lob
    private byte[] content;

    @NotNull
    @Column(name="content_type")
    private String contentType;

    @NotNull
    @Column(name="created")
    private Date created;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }
}
