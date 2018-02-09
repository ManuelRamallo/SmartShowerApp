package com.mramallo.pruebagradiente.API;

import com.mramallo.pruebagradiente.Model.ArduinoDevice;
import com.mramallo.pruebagradiente.Model.Consumo;
import com.mramallo.pruebagradiente.Model.Consumo2;
import com.mramallo.pruebagradiente.Model.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

/**
 * Created by jamores on 25/01/2018.
 */

public interface InterfaceRequestApi {

    @POST("/auth/register")
    public Call<User> registerUser(@Body User newUser);

    @POST("/auth/login")
    public Call<User> loginUser(@Body User userLoged);

    @POST("/placa")
    public Call<ArduinoDevice> registerDevice(@Header("Authorization") String token, @Body ArduinoDevice device);

    @PUT("/auth/config")
    public Call<User> editUser(@Header("Authorization") String token, @Body User userEdited);

    @POST("/data/consumo")
    //public Call<Consumo> showConsume(@Header("token") String token, @Body String id_placa);
    public Call<Consumo2[]> showConsume(@Header("Authorization") String token, @Body String id_placa);

    @POST("/data/consumo/hoy")
    //public Call<Consumo2[]> showConsumeToday(@Header("token") String token, @Body String id_placa);
    public Call<Consumo2[]> showConsumeToday(@Header("Authorization") String token, @Body ArduinoDevice arduinoDevice);

    @POST("/data/consumo/fecha/{fecha}") //yyyy-MM-dd
    public Call<Consumo2[]> showConsumeDate(@Header("Authorization") String token, @Body String id_placa, @Path("fecha") String fecha);



}
