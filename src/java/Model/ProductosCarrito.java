/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author Johan
 */
public class ProductosCarrito {
    private Producto producto;
    private int cantidadSolicita;

    public ProductosCarrito(Producto producto, int cantidad) {
        this.producto = producto;
        this.cantidadSolicita = cantidad;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public int getCantidadSolicita() {
        return cantidadSolicita;
    }

    public void setCantidadSolicita(int cantidad) {
        this.cantidadSolicita = cantidad;
    }
    
    
    
}
