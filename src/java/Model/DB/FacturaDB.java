/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.DB;

import Model.Cliente;
import Model.Enums.EstadoFactura;
import Model.Enums.TipoEnvio;
import Model.Enums.TipoVenta;
import Model.Factura;
import Model.Pedido;
import Model.Producto;
import Model.ProductosCarrito;
import java.time.LocalDate;
import java.util.LinkedList;

/**
 *
 * @author Fabian
 */
public class FacturaDB {
    
    public LinkedList <Factura>  mostrarFacturasPendientes(){
        Factura factura1 = new Factura();
        Factura factura2 = new Factura();
        LinkedList <Factura> listaFacturas = new LinkedList<>();
        
        Pedido pedido1 = new Pedido();
        Pedido pedido2 = new Pedido();
        
        pedido1.getListaProductos().add(new ProductosCarrito(new Producto("nvklal433", "Black Watch", "Product Description", "black-watch.jpg", 72, "Accessories", 61),5));
        pedido1.getListaProductos().add(new ProductosCarrito(new Producto("nvklal433", "Black Watch", "Product Description", "black-watch.jpg", 72, "Accessories", 61),7));
        
        pedido2.getListaProductos().add(new ProductosCarrito(new Producto("nvklal433", "Black Watch", "Product Description", "black-watch.jpg", 72, "Accessories", 61),7));
        pedido2.getListaProductos().add(new ProductosCarrito(new Producto("nvklal433", "Blue Watch", "Product Description", "black-watch.jpg", 72, "Accessories", 61),10));

        
        factura1.setCliente(new ClienteDB().obtenerCliente());
        factura1.setPedido(pedido1);
        factura1.setId("1");
        factura1.setEstado(EstadoFactura.Pendiente);
        factura1.setFechaPedido(LocalDate.now());
        factura1.setTipoEnvio(TipoEnvio.EnvioDirecto);
        factura1.setTipoVenta(TipoVenta.Contado);
        
        factura2.setCliente(new ClienteDB().obtenerCliente());
        factura2.setPedido(pedido2);
        factura2.setId("2");
        factura2.setEstado(EstadoFactura.Pendiente);
        factura2.setFechaPedido(LocalDate.now());
        factura2.setTipoEnvio(TipoEnvio.PersonalEmpresa);
        factura2.setTipoVenta(TipoVenta.Credito);
        
        
        listaFacturas.add(factura1);
        listaFacturas.add(factura2);
        return listaFacturas;
    }

}
