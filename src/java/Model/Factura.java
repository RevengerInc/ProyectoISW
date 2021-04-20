/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Model.Enums.EstadoFactura;
import Model.Enums.TipoEnvio;
import Model.Enums.TipoVenta;
import java.time.LocalDate;

/**
 *
 * @author Fabian
 */
public class Factura {
    
    private Pedido pedido;
    
    private Cliente cliente;
    
    private String id;
    private EstadoFactura estado;
    private LocalDate fechaPedido;
    private TipoEnvio tipoEnvio;
    private TipoVenta tipoVenta;
    private final double IVA = 0.15;
    private double descuento;
    private Horario horario;
    private double subTotal;
    
    public Factura() {
        
    }

    public Factura(Pedido pedido, Cliente cliente, String id, EstadoFactura estado, LocalDate fechaPedido, TipoEnvio tipoEnvio, TipoVenta tipoVenta, double descuento, Horario horario) {
        this.pedido = pedido;
        this.cliente = cliente;
        this.id = id;
        this.estado = estado;
        this.fechaPedido = fechaPedido;
        this.tipoEnvio = tipoEnvio;
        this.tipoVenta = tipoVenta;
        this.descuento = descuento;
        this.horario = horario;
        this.subTotal = pedido.totalPagar() + pedido.totalPagar()*IVA;
    }


    public EstadoFactura getEstado() {
        return estado;
    }
    
    public void setEstado(EstadoFactura estado) {
        this.estado = estado;
    }
    
    
    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
  
    public LocalDate getFechaPedido() {
        return fechaPedido;
    }

    public void setFechaPedido(LocalDate fechaPedido) {
        this.fechaPedido = fechaPedido;
    }

    public TipoEnvio getTipoEnvio() {
        return tipoEnvio;
    }

    public void setTipoEnvio(TipoEnvio tipoEnvio) {
        this.tipoEnvio = tipoEnvio;
    }

    public TipoVenta getTipoVenta() {
        return tipoVenta;
    }

    public void setTipoVenta(TipoVenta tipoVenta) {
        this.tipoVenta = tipoVenta;
    }

    public double getDescuento() {
        return descuento;
    }

    public void setDescuento(double descuento) {
        this.descuento = descuento;
    }

    public Horario getHorario() {
        return horario;
    }

    public void setHorario(Horario horario) {
        this.horario = horario;
    }

    public double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(double subTotal) {
        this.subTotal = subTotal;
    }

    public double getIVA() {
        return IVA;
    }

     
    
}
