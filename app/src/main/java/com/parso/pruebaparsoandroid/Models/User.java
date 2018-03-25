package com.parso.pruebaparsoandroid.Models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Luis.Matamoros on 22/03/2018.
 */

public class User {
    @SerializedName("name")
    private Name name;

    @SerializedName("cell")
    private String last;

    @SerializedName("phone")
    private String title;

    @SerializedName("email")
    private String email;

    @SerializedName("picture")
    private Picture picture;

    private String fullName;

    public User() {
    }

    public User(Name name, String last, String title, String email, Picture picture, String fullName) {
        this.name = name;
        this.last = last;
        this.title = title;
        this.email = email;
        this.picture = picture;
        this.fullName = fullName;
    }

    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }

    public String getLast() {
        return last;
    }

    public void setLast(String last) {
        this.last = last;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Picture getPicture() {
        return picture;
    }

    public void setPicture(Picture picture) {
        this.picture = picture;
    }

    public String getFullName() {
        String title = getName().getTitle().substring(0,1).toUpperCase() + "" + getName().getTitle().substring(1) + ". ";
        String fisrName = getName().getFirst().substring(0, 1).toUpperCase() + getName().getFirst().substring(1);
        String lastName = getName().getLast().substring(0, 1).toUpperCase() + getName().getLast().substring(1);

        String fullName = title + fisrName + " " + lastName;
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public class Name {
        @SerializedName("title")
        private String title;

        @SerializedName("first")
        private String first;

        @SerializedName("last")
        private String last;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getFirst() {
            return first;
        }

        public void setFirst(String first) {
            this.first = first;
        }

        public String getLast() {
            return last;
        }

        public void setLast(String last) {
            this.last = last;
        }
    }

    public class Picture {
        @SerializedName("large")
        private String large;

        @SerializedName("medium")
        private String medium;

        @SerializedName("thumbnail")
        private String thumbnail;

        public String getLarge() {
            return large;
        }

        public void setLarge(String large) {
            this.large = large;
        }

        public String getMedium() {
            return medium;
        }

        public void setMedium(String medium) {
            this.medium = medium;
        }

        public String getThumbnail() {
            return thumbnail;
        }

        public void setThumbnail(String thumbnail) {
            this.thumbnail = thumbnail;
        }
    }
}
