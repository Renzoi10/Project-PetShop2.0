package com.utp.petfriendly.model;

import java.io.Serializable;

public class AdopcionModel implements Serializable {
    String raza;
    String rangoEdad;
    String tamanio;
    String descripcion;
    String especialidad;
    String imagen;
    Integer tipo;

    public String getRaza() {
        return raza;
    }

    public void setRaza(String raza) {
        this.raza = raza;
    }

    public String getRangoEdad() {
        return rangoEdad;
    }

    public void setRangoEdad(String rangoEdad) {
        this.rangoEdad = rangoEdad;
    }

    public String getTamanio() {
        return tamanio;
    }

    public void setTamanio(String tamanio) {
        this.tamanio = tamanio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public Integer getTipo() {
        return tipo;
    }

    public void setTipo(Integer tipo) {
        this.tipo = tipo;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    @Override
    public String toString() {
        return "AdopcionModel{" +
                "raza='" + raza + '\'' +
                ", rangoEdad='" + rangoEdad + '\'' +
                ", tamanio='" + tamanio + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", especialidad='" + especialidad + '\'' +
                ", imagen='" + imagen + '\'' +
                ", tipo=" + tipo +
                '}';
    }
}
