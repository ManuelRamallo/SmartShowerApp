package com.mramallo.pruebagradiente.API;

import com.mramallo.pruebagradiente.Model.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by jamores on 25/01/2018.
 */

public interface InterfaceRequestApi {

    @POST("/auth/register")
    public Call<User> registerUser(@Body User newUser);

    @POST("/auth/login")
    public Call<User> loginUser(@Body User userLoged);

}
