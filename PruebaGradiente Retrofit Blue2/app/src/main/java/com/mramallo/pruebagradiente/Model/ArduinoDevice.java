package com.mramallo.pruebagradiente.Model;

/**
 * Created by mrdiaz on 05/02/2018.
 */

public class ArduinoDevice {

    String id_placa;
    String email;

    public ArduinoDevice() { }

    public ArduinoDevice(String id_placa, String email) {
        this.id_placa = id_placa;
        this.email = email;
    }

    public ArduinoDevice(String id_placa) {
        this.id_placa = id_placa;
    }

    public String getId_placa() {
        return id_placa;
    }

    public void setId_placa(String id_placa) {
        this.id_placa = id_placa;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "ArduinoDevice{" +
                "id_placa='" + id_placa + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
