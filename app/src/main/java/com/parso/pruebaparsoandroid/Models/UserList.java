package com.parso.pruebaparsoandroid.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Luis.Matamoros on 22/03/2018.
 */

public class UserList {

    @SerializedName("results")
    @Expose
    private List<User> user = null;

    public List<User> getUser() {
        return user;
    }

    public void setUser(List<User> user) {
        this.user = user;
    }
}