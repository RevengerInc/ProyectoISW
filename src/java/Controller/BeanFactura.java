/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.SNMPExceptions;
import Model.DB.FacturaDB;
import Model.DB.HorarioDB;
import Model.Enums.EstadoFactura;
import Model.Enums.TipoEnvio;
import Model.Enums.TipoVenta;
import Model.Factura;
import Model.Horario;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Fabian
 */
@Named(value = "beanFactura")
@SessionScoped
public class BeanFactura implements Serializable {

    /**
     * Creates a new instance of BeanFactura
     */
    private FacturaDB facturaDB = new FacturaDB();
    private LinkedList <Factura> listaPendientesDirecto = new LinkedList<>();
    private LinkedList <Factura> listaPendientesEncomienda = new LinkedList<>();
    private LinkedList <Factura> listaPendientesPresencial = new LinkedList<>();
    private Factura factura = new Factura();
    private HorarioDB horarioDB = new HorarioDB();
    private String horaEntrega = "";
    private TipoEnvio tipoEnvioElegido;
    private TipoVenta tipoVentaElegido;
    private String error= "";
    public BeanFactura() {
    }
    
    public LinkedList<Factura> mostrarFacturasPendientes(){
        LinkedList <Factura> facturasPendientes = new LinkedList<>();
        for (Factura factura1 : listaPendientesDirecto) {
            if(factura1.getEstado().equals(EstadoFactura.Pendiente)){
                facturasPendientes.add(factura1);
            }
        }
        return facturasPendientes;
    }
    
    public TipoEnvio[] tiposEnvio (){
        return TipoEnvio.values();
    }
    
    public TipoVenta[] tipoVenta (){
        return TipoVenta.values();
    }
    
    public LinkedList<Horario> mostrarHorarios(){
        try {
            return horarioDB.obtenerHorarios();
        } catch (SNMPExceptions ex) {
            Logger.getLogger(BeanFactura.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public String completarPedido(Factura f){
        try {
            this.facturaDB.FinalizarFactura(factura.getId());
        } catch (SNMPExceptions ex) {
            error+=ex.getMensajeParaDesarrollador();
        }
        return "PrincipalBodega";
    }
    
     public String detalleFactura(Factura f){
        this.factura = f;
        return "DetallePedido";
    }
    public FacturaDB getFacturaDB() {
        return facturaDB;
    }

    public void setFacturaDB(FacturaDB facturaDB) {
        this.facturaDB = facturaDB;
    }

    public LinkedList<Factura> getListaPendientesDirecto() {
        try {
            listaPendientesDirecto = facturaDB.mostrarFacturasPendientesPorTipoEnvio(TipoEnvio.EnvioDirecto);
        } catch (SNMPExceptions ex) {
            error+=ex.getMensajeParaDesarrollador();
        }
        return listaPendientesDirecto;
    }

    public void setListaPendientesDirecto(LinkedList<Factura> listaPendientesDirecto) {
        this.listaPendientesDirecto = listaPendientesDirecto;
    }

    public TipoEnvio getTipoEnvioElegido() {
        return tipoEnvioElegido;
    }

    public void setTipoEnvioElegido(TipoEnvio tipoEnvioElegido) {
        this.tipoEnvioElegido = tipoEnvioElegido;
    }

    
    public Factura getFactura() {
        return factura;
    }

    public void setFactura(Factura factura) {
        this.factura = factura;
    }

    public TipoVenta getTipoVentaElegido() {
        return tipoVentaElegido;
    }

    public void setTipoVentaElegido(TipoVenta tipoVentaElegido) {
        this.tipoVentaElegido = tipoVentaElegido;
    }
    
    

    public HorarioDB getHorarioDB() {
        return horarioDB;
    }

    public void setHorarioDB(HorarioDB horarioDB) {
        this.horarioDB = horarioDB;
    }

    public String getHoraEntrega() {
        return horaEntrega;
    }

    public void setHoraEntrega(String horaEntrega) {
        this.horaEntrega = horaEntrega;
    }

    public LinkedList<Factura> getListaPendientesEncomienda() {
        try {
            listaPendientesEncomienda = facturaDB.mostrarFacturasPendientesPorTipoEnvio(TipoEnvio.Encomienda);
        } catch (SNMPExceptions ex) {
            error+=ex.getMensajeParaDesarrollador();
        }
        return listaPendientesEncomienda;
    }

    public void setListaPendientesEncomienda(LinkedList<Factura> listaPendientesEncomienda) {
        this.listaPendientesEncomienda = listaPendientesEncomienda;
    }

    public LinkedList<Factura> getListaPendientesPresencial() {
        try {
            listaPendientesPresencial = facturaDB.mostrarFacturasPendientesPorTipoEnvio(TipoEnvio.Presencial);
        } catch (SNMPExceptions ex) {
            error+=ex.getMensajeParaDesarrollador();
        }
        return listaPendientesPresencial;
    }

    public void setListaPendientesPresencial(LinkedList<Factura> listaPendientesPresencial) {
        this.listaPendientesPresencial = listaPendientesPresencial;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
    
    
    
    
    
    
    
}
