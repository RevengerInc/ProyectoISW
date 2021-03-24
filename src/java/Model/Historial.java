/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.LinkedList;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

/**
 *
 * @author César
 */
public class Historial {
    private int codFactura;
    private Pedido pedidoFactura= new Pedido();

    public Historial(int codFactura, Pedido pedido) {
        this.codFactura = codFactura;
        this.pedidoFactura= pedido;
    }

    
    public int getCodFactura() {
        return codFactura;
    }

    public void setCodFactura(int codFactura) {
        this.codFactura = codFactura;
    }

    public Pedido getPedidoFactura() {
        return pedidoFactura;
    }

    public void setPedidoFactura(Pedido pedidoFactura) {
        this.pedidoFactura = pedidoFactura;
    }
    
    
    
    
}
