/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.DB.DireccionDB;
import Model.Direccion;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.LinkedList;

/**
 *
 * @author Johan
 */
@Named(value = "beanDireccion")
@SessionScoped
public class BeanDireccion implements Serializable {

    private DireccionDB objDireccionDB = new DireccionDB();
    private Direccion direccionEntrega;
    public BeanDireccion() {
    }
    
    public LinkedList<Direccion> obtenerDirecciones (){
        return objDireccionDB.obtenerDirecciones();
    }

    public DireccionDB getObjDireccion() {
        return objDireccionDB;
    }

    public void setObjDireccion(DireccionDB objDireccion) {
        this.objDireccionDB = objDireccion;
    }

    public DireccionDB getObjDireccionDB() {
        return objDireccionDB;
    }

    public void setObjDireccionDB(DireccionDB objDireccionDB) {
        this.objDireccionDB = objDireccionDB;
    }

    public Direccion getDireccionEntrega() {
        return direccionEntrega;
    }

    public void setDireccionEntrega(Direccion direccionEntrega) {
        this.direccionEntrega = direccionEntrega;
    }
    
    
}
