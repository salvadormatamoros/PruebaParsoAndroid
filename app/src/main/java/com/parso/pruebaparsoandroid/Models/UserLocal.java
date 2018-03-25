package com.parso.pruebaparsoandroid.Models;

import android.graphics.Bitmap;

/**
 * Created by Luis.Matamoros on 23/03/2018.
 */

public class UserLocal {

    private String name;
    private String email;
    private Bitmap picture;

    public UserLocal() {
    }

    public UserLocal(String name, String email, Bitmap picture) {
        this.name = name;
        this.email = email;
        this.picture = picture;
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

    public Bitmap getPicture() {
        return picture;
    }

    public void setPicture(Bitmap picture) {
        this.picture = picture;
    }
}
