/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.DB.FacturaDB;
import Model.Enums.EstadoFactura;
import Model.Factura;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.LinkedList;

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
    private LinkedList <Factura> listaF = new LinkedList<>();
    private Factura factura = new Factura();
    
    public BeanFactura() {
        listaF = facturaDB.mostrarFacturasPendientes();
        
    }
    
    public LinkedList<Factura> mostrarFacturasPendientes(){
        LinkedList <Factura> facturasPendientes = new LinkedList<>();
        for (Factura factura1 : listaF) {
            if(factura1.getEstado().equals(EstadoFactura.Pendiente)){
                facturasPendientes.add(factura1);
            }
        }
        return facturasPendientes;
    }
    
    public String completarPedido(Factura f){
        this.factura.setEstado(EstadoFactura.Entregado);
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

    public LinkedList<Factura> getListaF() {
        return listaF;
    }

    public void setListaF(LinkedList<Factura> listaF) {
        this.listaF = listaF;
    }

    public Factura getFactura() {
        return factura;
    }

    public void setFactura(Factura factura) {
        this.factura = factura;
    }
    
    
    
    
    
    
}
