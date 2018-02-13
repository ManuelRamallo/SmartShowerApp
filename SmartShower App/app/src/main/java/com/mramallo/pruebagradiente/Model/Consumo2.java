package com.mramallo.pruebagradiente.Model;

import java.util.Date;

/**
 * Created by GUAY on 06/02/2018.
 */

public class Consumo2 {

    private int consumo;
    private Date fecha_Inicio;
    private Date fecha_Fin;

    public Consumo2() {
    }

    public Consumo2(int consumo, Date fecha_Inicio, Date fecha_Fin) {
        this.consumo = consumo;
        this.fecha_Inicio = fecha_Inicio;
        this.fecha_Fin = fecha_Fin;
    }

    public int getConsumo() {
        return consumo;
    }

    public void setConsumo(int consumo) {
        this.consumo = consumo;
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

    @Override
    public String toString() {
        return "Consumo2{" +
                "consumo=" + consumo +
                ", fecha_Inicio=" + fecha_Inicio +
                ", fecha_Fin=" + fecha_Fin +
                '}';
    }
}
