package com.mramallo.pruebagradiente.Model;


/**
 * Created by jamores on 25/01/2018.
 */

public class User {

    private String token;
    private long id;
    private String nombre;
    private String apellidos;
    private String email;
    private String password;
    private int num_personas;
    private String direccion;
    private int limite_consumo;

    public User(String token, long id, String nombre, String apellidos, String email, String password, int num_personas, String direccion, int limite_consumo) {
        this.token = token;
        this.id = id;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.email = email;
        this.password = password;
        this.num_personas = num_personas;
        this.direccion = direccion;
        this.limite_consumo = limite_consumo;
    }

    public User() {
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getNum_personas() {
        return num_personas;
    }

    public void setNum_personas(int num_personas) {
        this.num_personas = num_personas;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public int getLimite_consumo() {
        return limite_consumo;
    }

    public void setLimite_consumo(int limite_consumo) {
        this.limite_consumo = limite_consumo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (id != user.id) return false;
        if (num_personas != user.num_personas) return false;
        if (limite_consumo != user.limite_consumo) return false;
        if (token != null ? !token.equals(user.token) : user.token != null) return false;
        if (nombre != null ? !nombre.equals(user.nombre) : user.nombre != null) return false;
        if (apellidos != null ? !apellidos.equals(user.apellidos) : user.apellidos != null)
            return false;
        if (email != null ? !email.equals(user.email) : user.email != null) return false;
        if (password != null ? !password.equals(user.password) : user.password != null)
            return false;
        return direccion != null ? direccion.equals(user.direccion) : user.direccion == null;
    }

    @Override
    public int hashCode() {
        int result = token != null ? token.hashCode() : 0;
        result = 31 * result + (int) (id ^ (id >>> 32));
        result = 31 * result + (nombre != null ? nombre.hashCode() : 0);
        result = 31 * result + (apellidos != null ? apellidos.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + num_personas;
        result = 31 * result + (direccion != null ? direccion.hashCode() : 0);
        result = 31 * result + limite_consumo;
        return result;
    }

    @Override
    public String toString() {
        return "User{" +
                "token='" + token + '\'' +
                ", id=" + id +
                ", nombre='" + nombre + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", num_personas=" + num_personas +
                ", direccion='" + direccion + '\'' +
                ", limite_consumo=" + limite_consumo +
                '}';
    }
}
