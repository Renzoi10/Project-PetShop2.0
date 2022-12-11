package com.utp.petfriendly.model;

import java.io.Serializable;

public class VeterinariaModel implements Serializable {

    String nombreVeterinaria;
    String imagen;
    String atencion;
    String telefono;
    String direccion;
    String descripcion;


    public String getNombreVeterinaria() {
        return nombreVeterinaria;
    }

    public void setNombreVeterinaria(String nombreVeterinaria) {
        this.nombreVeterinaria = nombreVeterinaria;
    }

    public String getAtencion() {
        return atencion;
    }

    public void setAtencion(String atencion) {
        this.atencion = atencion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}

