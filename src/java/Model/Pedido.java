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
    
    private LinkedList<Producto> listaProductos = new LinkedList<Producto>();
    private Direccion direccionEntrega;
    
    
    public Pedido() {
        
    }

    public LinkedList<Producto> getListaProductos() {
        return listaProductos;
    }

    public void setListaProductos(LinkedList<Producto> listaProductos) {
        this.listaProductos = listaProductos;
    }

    public Direccion getDireccionEntrega() {
        return direccionEntrega;
    }

    public void setDireccionEntrega(Direccion direccionEntrega) {
        this.direccionEntrega = direccionEntrega;
    }
    
      
}
