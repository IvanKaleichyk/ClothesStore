package com.koleychik.clothesstore.models;

import java.util.Objects;

public class User {

    private String name, email, imgUrl;

    public User(String name, String email, String imgUrl) {
        this.name = name;
        this.email = email;
        this.imgUrl = imgUrl;
    }

    public User(){

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return Objects.equals(getName(), user.getName()) &&
                Objects.equals(getEmail(), user.getEmail()) &&
                Objects.equals(getImgUrl(), user.getImgUrl());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getEmail(), getImgUrl());
    }
}
