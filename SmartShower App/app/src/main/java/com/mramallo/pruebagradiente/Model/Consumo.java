package com.mramallo.pruebagradiente.Model;

import java.util.Date;
import java.util.List;

/**
 * Created by Jorge Amores on 05/02/2018.
 */

public class Consumo {

    private String id_placa;
    private List<Integer> consumos;
    private int segundos;
    private Date fecha_Inicio;
    private Date fecha_Fin;

    public Consumo(String id_placa, List<Integer> consumos, int segundos, Date fecha_Inicio, Date fecha_Fin) {
        this.id_placa = id_placa;
        this.consumos = consumos;
        this.segundos = segundos;
        this.fecha_Inicio = fecha_Inicio;
        this.fecha_Fin = fecha_Fin;
    }

    public Consumo() {
    }

    public String getId_placa() {
        return id_placa;
    }

    public void setId_placa(String id_placa) {
        this.id_placa = id_placa;
    }

    public List<Integer> getConsumos() {
        return consumos;
    }

    public void setConsumos(List<Integer> consumos) {
        this.consumos = consumos;
    }

    public int getSegundos() {
        return segundos;
    }

    public void setSegundos(int segundos) {
        this.segundos = segundos;
    }

    public Date getFecha_Inicio() {
        return fecha_Inicio;
    }

    public void setFecha_Inicio(Date fecha_Inicio) {
        this.fecha_Inicio = fecha_Inicio;
    }

    public Date getFecha_Fin() {
        return fecha_Fin;
    }

    public void setFecha_Fin(Date fecha_Fin) {
        this.fecha_Fin = fecha_Fin;
    }
}
