/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.SNMPExceptions;
import Model.DB.HistorialDB;
import Model.Historial;
import Model.ProductosCarrito;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

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
    LinkedList<Historial> listaEntregados = new LinkedList<Historial>();
    LinkedList<Historial> listaProcesando = new LinkedList<Historial>();
    String error;
    public BeanHistorial() {
        try {
            listaEntregados=historialDB.moTodo("cesarbadilla98@hotmail.com", "E");
            listaProcesando=historialDB.moTodo("cesarbadilla98@hotmail.com", "P");
        } catch (SNMPExceptions ex) {
            error=ex.getMensajeParaDesarrollador();
        }
    }
     

    public HistorialDB getHistorialDB() {
        return historialDB;
    }

    public void setHistorialDB(HistorialDB historialDB) {
        this.historialDB = historialDB;
    }

    public LinkedList<Historial> getListaEntregados() {
        return listaEntregados;
    }

    public void setListaEntregados(LinkedList<Historial> listaEntregados) {
        this.listaEntregados = listaEntregados;
    }

    public LinkedList<ProductosCarrito> obtenerListaProductos(int cod){
        for (Historial historial : listaEntregados) {
            if(historial.getCodFactura()==cod){
                return historial.getPedidoFactura().getListaProductos();
            }
        }
        return null;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public LinkedList<Historial> getListaProcesando() {
        return listaProcesando;
    }

    public void setListaProcesando(LinkedList<Historial> listaProcesando) {
        this.listaProcesando = listaProcesando;
    }
    
}
