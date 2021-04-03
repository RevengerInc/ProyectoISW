/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.SNMPExceptions;
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
import java.util.logging.Level;
import java.util.logging.Logger;

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
    private String error="";
    
    public BeanReporte() {
        try {
            this.listaEnProceso=reporteDB.reportesPorEstado("E");
            this.listaPendiente=reporteDB.reportesPorEstado("P");
            this.listaEntregada=reporteDB.reportesPorEstado("F");
            this.listaContado=reporteDB.reportesPorTipoVenta("CON");
            this.listaCredito=reporteDB.reportesPorTipoVenta("CRE");
        } catch (SNMPExceptions ex) {
            error+=ex.getMensajeParaDesarrollador();
        }
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
    
    public void obtenerListaFacturas(EstadoFactura estado){
        try {
        switch (estado) {
            case EnProceso:
                listaEnProceso=reporteDB.reportesPorEstado("E");
                break;
            case Finalizado:
                listaEntregada=reporteDB.reportesPorEstado("F");
                break;
            default:
                listaPendiente=reporteDB.reportesPorEstado("P");
                break;
        }
        } catch (SNMPExceptions ex) {
            error+=ex.getMensajeParaDesarrollador();
        }
        
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
    public String obtenerListaProductos(LinkedList<ProductosCarrito> lista){
        String listaProductos="";
            for (ProductosCarrito producto : lista) {
                listaProductos+=producto.getCantidadSolicita()+" x "+producto.getProducto().getId()+" "+producto.getProducto().getNombre()+"\n";
            }
        
        return listaProductos;
    }
}
