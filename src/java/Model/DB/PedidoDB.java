/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.DB;

import DAO.AccesoDatos;
import DAO.SNMPExceptions;
import Model.Enums.EstadoFactura;
import Model.Enums.TipoEnvio;
import Model.Enums.TipoVenta;
import Model.Factura;
import java.sql.Date;
import java.sql.SQLException;

public class PedidoDB {

    public int CrearPedido (Factura factura, int idDireccion) throws SNMPExceptions {
        String insert = "";
        int resultInsert;
        try {
            AccesoDatos accesoDatos = new AccesoDatos();

            insert = "EXEC PA_CrearPedido '" + factura.getCliente().getCorreoID() + "' , '" + factura.getFechaPedido() + "' , " + (factura.getSubTotal()-factura.getPedido().totalPagar()) + " , " + factura.getPedido().totalPagar() + " , " + factura.getSubTotal() + " , '" + "P" + "' , "+ (factura.getTipoEnvio().getCodigo().equals("E")?2500:0) + " , " + factura.getDescuento() + " , '" + (factura.getTipoVenta()==TipoVenta.Contado? "CON":"CRE") +"' , " + idDireccion;

            resultInsert = accesoDatos.ejecutaSQL(insert);

        } catch (SQLException e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION,
                    e.getMessage(), e.getErrorCode());
        } catch (Exception e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION,
                    e.getMessage());
        } finally {

        }
        return resultInsert;
    }
    
    
}
