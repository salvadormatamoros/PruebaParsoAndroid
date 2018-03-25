package com.parso.pruebaparsoandroid.Services;

/**
 * Created by Luis.Matamoros on 22/03/2018.
 */

import com.parso.pruebaparsoandroid.Models.UserList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface UserService {

    @GET("/api?results=10")
    Call<UserList> getUsers();
}
