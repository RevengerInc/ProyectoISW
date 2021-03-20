/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Producto;
import Model.ProductoDB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.LinkedList;

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
    
    public BeanProducto() {
        listaP=productoDB.moTodo();
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

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }
}
