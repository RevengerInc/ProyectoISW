/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;

/**
 *
 * @author Fabian
 */
@Named(value = "beanPedido")
@SessionScoped
public class BeanMantenimientoPedido implements Serializable {

    /**
     * Creates a new instance of BeanPedido
     */
    public BeanMantenimientoPedido() {
        
    }
     private int cantidadSolicitada;

    public int getCantidadSolicitada() {
        return cantidadSolicitada;
    }

    public void setCantidadSolicitada(int cantidadSolicitada) {
        this.cantidadSolicitada = cantidadSolicitada;
    }
    
}
