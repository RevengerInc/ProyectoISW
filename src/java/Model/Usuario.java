/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Model.Enums.TipoUsuario;

/**
 *
 * @author César
 */
public class Usuario {
    private String nombre;
    private TipoUsuario tipoUsuario;
    private String cedula;
    private String telefono;
    private String correoID;

    public Usuario(String nombre, TipoUsuario tipoUsuario, String cedula, String telefono, String correoID) {
        this.nombre = nombre;
        this.tipoUsuario = tipoUsuario;
        this.cedula = cedula;
        this.telefono = telefono;
        this.correoID = correoID;
    }

    public Usuario() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public TipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(TipoUsuario tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreoID() {
        return correoID;
    }

    public void setCorreoID(String correoID) {
        this.correoID = correoID;
    }
    
    
}
