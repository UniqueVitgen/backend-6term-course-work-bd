package com.example.demo.entity.form;

import com.example.demo.entity.User;

public class UserUploadForm {
    private User user;
    private String filename;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }
}
