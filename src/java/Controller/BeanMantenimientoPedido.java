/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Pedido;
import Model.Producto;
import Model.ProductosCarrito;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javafx.scene.control.Alert;

/**
 *
 * @author Fabian
 */
@Named(value = "beanPedido")
@SessionScoped
public class BeanMantenimientoPedido implements Serializable {

    private Pedido objPedido = new Pedido();
    private int cantidadSolicitada;
    
    public BeanMantenimientoPedido() {

    }
    
    public void agregarProductoPedido(Producto producto) {
        objPedido.controlCantidad(producto, cantidadSolicitada);
        objPedido.agregarProducto(producto, cantidadSolicitada);
        
    }
    
    public double totalPagar (){
        return objPedido.totalPagar();
    }

    public void eliminarProductoCarrito(ProductosCarrito p){
        objPedido.eliminarProducto(p);
    }
    public int getCantidadSolicitada() {
        return cantidadSolicitada;
    }

    public void setCantidadSolicitada(int cantidadSolicitada) {
        this.cantidadSolicitada = cantidadSolicitada;
    }

    public Pedido getObjPedido() {
        return objPedido;
    }

    public void setObjPedido(Pedido objPedido) {
        this.objPedido = objPedido;
    }

    
}
