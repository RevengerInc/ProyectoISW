/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author Fabian
 */
public class Direccion {
     
    private int idDireccion;
    private String descripcion;
    private String tipoDireccion;
    private Provincia provincia;
    private Canton canton;
    private Distrito distrito;
    private Barrio barrio;

    public Direccion(int idDireccion, String descripcion, String tipoDireccion, Provincia provincia, Canton canton, Distrito distrito, Barrio barrio) {
        this.idDireccion = idDireccion;
        this.descripcion = descripcion;
        this.tipoDireccion = tipoDireccion;
        this.provincia = provincia;
        this.canton = canton;
        this.distrito = distrito;
        this.barrio = barrio;
    }

    public Direccion(int idDireccion, String Descripcion, String tipoDireccion) {
        this.idDireccion = idDireccion;
        this.descripcion = Descripcion;
        this.tipoDireccion = tipoDireccion;
    }
    
    public String toString (){
        return descripcion;
    }

    public int getIdDireccion() {
        return idDireccion;
    }

    public void setIdDireccion(int idDireccion) {
        this.idDireccion = idDireccion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String Descripcion) {
        this.descripcion = Descripcion;
    }

    public String getTipoDireccion() {
        return tipoDireccion;
    }

    public void setTipoDireccion(String tipoDireccion) {
        this.tipoDireccion = tipoDireccion;
    }
    
    
    
    
    
    
}
