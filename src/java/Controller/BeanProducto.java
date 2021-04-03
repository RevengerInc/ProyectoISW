/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.SNMPExceptions;
import Model.Producto;
import Model.DB.ProductoDB;
import Model.Pedido;
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
@Named(value = "beanProducto")
@SessionScoped
public class BeanProducto implements Serializable {

    /**
     * Creates a new instance of BeanProducto1
     */
    private ProductoDB productoDB= new ProductoDB();
    LinkedList<Producto> listaP = new LinkedList<Producto>();
    private Producto producto= new Producto();
    private String error="";
    
    
    public BeanProducto() {
        try {
            listaP=productoDB.moTodo();
        } catch (SNMPExceptions ex) {
            error=ex.getMensajeParaDesarrollador();
        }
    }

    public LinkedList<Producto> getListaP() {
        return listaP;
    }
    
    

    public void setListaP(LinkedList<Producto> listaP) {
        this.listaP = listaP;
    }
    
    public String detalle(Producto p){
        this.producto = p;
        return "DetalleProducto";
    }
     public String disponibilidad(){
        return producto.getCantidad()==0?"Agotado":"Disponible";
    }
    
    public String color(){
        return producto.getCantidad()==0?"red":"green";
    }
    
    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public ProductoDB getProductoDB() {
        return productoDB;
    }

    public void setProductoDB(ProductoDB productoDB) {
        this.productoDB = productoDB;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
    
    
}
