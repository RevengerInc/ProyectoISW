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
    private String Descripcion;

    public Direccion(int idDireccion, String Descripcion) {
        this.idDireccion = idDireccion;
        this.Descripcion = Descripcion;
    }
    
    public String toString (){
        return Descripcion;
    }
    
    
    
    
}
