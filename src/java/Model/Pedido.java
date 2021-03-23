/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;


import java.util.LinkedList;
/**
 *
 * @author Fabian
 */
public class Pedido {
    
    private LinkedList<ProductosCarrito> listaProductos = new LinkedList<ProductosCarrito>();
    private Direccion direccionEntrega;
    
    
    public Pedido() {
        
    }
    
    public void agregarProducto (Producto producto, int cantidad){
        listaProductos.add(new ProductosCarrito(producto, cantidad));
    }

    public LinkedList<ProductosCarrito> getListaProductos() {
        return listaProductos;
    }

    public void setListaProductos(LinkedList<ProductosCarrito> listaProductos) {
        this.listaProductos = listaProductos;
    }



    public Direccion getDireccionEntrega() {
        return direccionEntrega;
    }

    public void setDireccionEntrega(Direccion direccionEntrega) {
        this.direccionEntrega = direccionEntrega;
    }
    
    
      
}
