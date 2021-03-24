/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.DB.HistorialDB;
import Model.DB.ReporteDB;
import Model.Enums.EstadoFactura;
import Model.Factura;
import Model.Historial;
import Model.ProductosCarrito;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.LinkedList;

/**
 *
 * @author César
 */
@Named(value = "beanReporte")
@SessionScoped
public class BeanReporte implements Serializable {

    /**
     * Creates a new instance of BeanReporte
     */
    ReporteDB reporteDB= new ReporteDB();
     private LinkedList<Factura> listaPendiente = new LinkedList<Factura>();
    private LinkedList<Factura> listaEnProceso = new LinkedList<Factura>();
    private LinkedList<Factura> listaEntregada = new LinkedList<Factura>();
    private LinkedList<Factura> listaCredito = new LinkedList<Factura>();
    private LinkedList<Factura> listaContado = new LinkedList<Factura>();
    
    public BeanReporte() {
        this.listaEnProceso=reporteDB.reportesPorEstado(EstadoFactura.EnProceso);
        this.listaPendiente=reporteDB.reportesPorEstado(EstadoFactura.Pendiente);
        this.listaEntregada=reporteDB.reportesPorEstado(EstadoFactura.Entregado);
        this.listaContado=reporteDB.reportesPorTipoVentaContado();
        this.listaCredito=reporteDB.reportesPorTipoVentaCredito();
    }
    
   

    public ReporteDB getReporteDB() {
        return reporteDB;
    }

    public void setReporteDB(ReporteDB reporteDB) {
        this.reporteDB = reporteDB;
    }

    public LinkedList<Factura> getListaPendiente() {
        return listaPendiente;
    }

    public void setListaPendiente(LinkedList<Factura> listaPendiente) {
        this.listaPendiente = listaPendiente;
    }
    
    public LinkedList<Factura> obtenerListaFacturas(EstadoFactura estado){
        switch (estado) {
            case EnProceso:
                listaEnProceso=reporteDB.reportesPorEstado(estado);
                break;
            case Entregado:
                listaEntregada=reporteDB.reportesPorEstado(estado);
                break;
            default:
                listaPendiente=reporteDB.reportesPorEstado(estado);
                break;
        }
        
        return reporteDB.reportesPorEstado(estado);
    }

    public LinkedList<Factura> getListaEnProceso() {
        return listaEnProceso;
    }

    public void setListaEnProceso(LinkedList<Factura> listaEnProceso) {
        this.listaEnProceso = listaEnProceso;
    }

    public LinkedList<Factura> getListaEntregada() {
        return listaEntregada;
    }

    public void setListaEntregada(LinkedList<Factura> listaEntregada) {
        this.listaEntregada = listaEntregada;
    }

    public LinkedList<Factura> getListaCredito() {
        return listaCredito;
    }

    public void setListaCredito(LinkedList<Factura> listaCredito) {
        this.listaCredito = listaCredito;
    }

    public LinkedList<Factura> getListaContado() {
        return listaContado;
    }

    public void setListaContado(LinkedList<Factura> listaContado) {
        this.listaContado = listaContado;
    }
    

    public String obtenerListaProductos(){
        String listaProductos="";
        for (Factura factura : listaPendiente) {
            for (ProductosCarrito producto : factura.getPedido().getListaProductos()) {
                listaProductos+=producto.getCantidadSolicita()+" x "+producto.getProducto().getId()+" "+producto.getProducto().getNombre()+"\n";
            }
        }
        return listaProductos;
    }
}
