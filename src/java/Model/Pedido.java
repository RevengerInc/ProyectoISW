/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;


import Controller.BeanProducto;
import java.util.LinkedList;
/**
 *
 * @author Fabian
 */
public class Pedido {
    
    private LinkedList<ProductosCarrito> listaProductos = new LinkedList<ProductosCarrito>();
    
    
    public Pedido() {
        
    }
    public Pedido(LinkedList<ProductosCarrito> listaProductos) {
        this.listaProductos= listaProductos;
    }
    
    public void agregarProducto (Producto producto, int cantidad){
        
        for(int i = 0; i < listaProductos.size(); i++){
            if(listaProductos.get(i).getProducto().getId().equals(producto.getId())){
                listaProductos.get(i).setCantidadSolicita(listaProductos.get(i).getCantidadSolicita() + cantidad);
                return;
            }
        }
        listaProductos.add(new ProductosCarrito(producto, cantidad));
    }
    
    public void eliminarProducto (ProductosCarrito p){
        for(int i = 0; i < listaProductos.size(); i++){
            if(listaProductos.get(i).getProducto().getId().equals(p.getProducto().getId())){
                p.getProducto().setCantidad(p.getProducto().getCantidad() + p.getCantidadSolicita());
                listaProductos.remove(p);
            }
        }
    }
    
    public void controlCantidad (Producto producto, int cantidadSolicita){
        producto.setCantidad(producto.getCantidad() - cantidadSolicita);
    }

    public LinkedList<ProductosCarrito> getListaProductos() {
        return listaProductos;
    }

    public void setListaProductos(LinkedList<ProductosCarrito> listaProductos) {
        this.listaProductos = listaProductos;
    }



   
   
      
}
