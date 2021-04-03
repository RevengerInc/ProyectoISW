/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.DB;

import Model.Pedido;
import Model.Producto;
import Model.ProductosCarrito;
import java.util.LinkedList;

/**
 *
 * @author Fabian
 */
public class PedidoDB {

   
       
      public LinkedList<Pedido> moTodo(){
      String select = "";
      LinkedList<Pedido> listaPedidos = new LinkedList<Pedido>();
      LinkedList<ProductosCarrito> listaProductos1 = new LinkedList<ProductosCarrito>();
      LinkedList<ProductosCarrito> listaProdcutos2 = new LinkedList<ProductosCarrito>();
      Pedido pedido1 = new Pedido();
      Pedido pedido2 = new Pedido();
      
     
          try {
               
                              
                listaProductos1.add(new ProductosCarrito(new Producto("nvklal433", "Black Watch", "Product Description", "black-watch.jpg", 72, 1, 61),5));
                listaProductos1.add(new ProductosCarrito(new Producto("nfdlal433", "Blue Watch", "Product Description", "black-watch.jpg", 72, 1, 61),5));
                listaProductos1.add(new ProductosCarrito(new Producto("nfslal433", "Red Watch", "Product Description", "black-watch.jpg", 72, 1, 61),5));

                listaProductos1.add(new ProductosCarrito(new Producto("nhglal433", "Black Watch2", "Product Description", "black-watch.jpg", 72, 1, 61),5));
                listaProductos1.add(new ProductosCarrito(new Producto("ndslal433", "Blue Watch2", "Product Description", "black-watch.jpg", 72, 1, 61),5));
                listaProductos1.add(new ProductosCarrito(new Producto("ngflal433", "Red Watch2", "Product Description", "black-watch.jpg", 72, 1, 61),5));
                
                
                pedido1.setListaProductos(listaProductos1);
                pedido2.setListaProductos(listaProdcutos2);
                
                listaPedidos.add(pedido1);
                listaPedidos.add(pedido2);
                
              //}
              //rsPA.close();
              
         /* } catch (SQLException e) {
              throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, 
                                      e.getMessage(), e.getErrorCode());
          */}catch (Exception e) {
              /*throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, 
                                      e.getMessage());*/
          } finally {
              
          }
          return listaPedidos;
      }
}
