/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.DB.HistorialDB;
import Model.Historial;
import Model.ProductosCarrito;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.LinkedList;

/**
 *
 * @author César
 */
@Named(value = "beanHistorial")
@SessionScoped
public class BeanHistorial implements Serializable {

    /**
     * Creates a new instance of BeanHistorial
     */
    HistorialDB historialDB= new HistorialDB();
    LinkedList<Historial> listaH = new LinkedList<Historial>();
    public BeanHistorial() {
        listaH=historialDB.moTodo();
    }
     

    public HistorialDB getHistorialDB() {
        return historialDB;
    }

    public void setHistorialDB(HistorialDB historialDB) {
        this.historialDB = historialDB;
    }

    public LinkedList<Historial> getListaH() {
        return listaH;
    }

    public void setListaH(LinkedList<Historial> listaH) {
        this.listaH = listaH;
    }

    public LinkedList<ProductosCarrito> obtenerListaProductos(int cod){
        for (Historial historial : listaH) {
            if(historial.getCodFactura()==cod){
                return historial.getPedidoFactura().getListaProductos();
            }
        }
        return null;
    }
    
}
