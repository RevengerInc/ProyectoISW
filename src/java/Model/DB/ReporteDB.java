/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.DB;

import DAO.AccesoDatos;
import DAO.SNMPExceptions;
import Model.Cliente;
import Model.Enums.EstadoFactura;
import Model.Enums.TipoEnvio;
import Model.Enums.TipoUsuario;
import Model.Enums.TipoVenta;
import Model.Factura;
import Model.Historial;
import Model.Horario;
import Model.Pedido;
import Model.Producto;
import Model.ProductosCarrito;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.LinkedList;

/**
 *
 * @author César
 */
public class ReporteDB {
    public  LinkedList<Factura> reportesPorEstado(String estado) throws SNMPExceptions{
     String select = "";
     ProductoDB pDB= new ProductoDB();
     ClienteDB cDB= new ClienteDB();
      LinkedList<Factura> listaP = new LinkedList<Factura>();
          
          try {
              //Se intancia la clase de acceso a datos
            AccesoDatos accesoDatos= new AccesoDatos();
            
            //Se crea la sentencia de Busqueda
            select="EXEC PA_ConsultarFacturasPorEstado '"+estado+"'";
                    
            //se ejecuta la sentencia sql
            ResultSet rsPA= accesoDatos.ejecutaSQLRetornaRS(select);
            //se llama el array con los proyectos  
              while (rsPA.next()) {
                listaP.add(new Factura(new Pedido(pDB.consultarProductosPorFacturaID(rsPA.getInt("IdFactura"))), cDB.obtenerClientePorID(rsPA.getString("IdUsuario")),rsPA.getString("IdFactura"),rsPA.getString("IdEstado").equals("F")?EstadoFactura.Finalizado:EstadoFactura.Pendiente , LocalDate.parse(rsPA.getString("fecha")), rsPA.getString("tipoDespacho").equals("D")?TipoEnvio.EnvioDirecto:rsPA.getString("tipoDespacho").equals("E")?TipoEnvio.Encomienda:TipoEnvio.Presencial, rsPA.getString("IdTipoVenta").equals("CON")?TipoVenta.Contado:TipoVenta.Credito,0, new Horario(rsPA.getString("IdHorario"), rsPA.getString("Horas"))));
              }
              rsPA.close();
              
         } catch (SQLException e) {
              throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, 
                                      e.getMessage(), e.getErrorCode());
          }catch (Exception e) {
              throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, 
                                      e.getMessage());
          } finally {
              
          }
          return listaP;
      }
    public  LinkedList<Factura> reportesPorTipoVenta(String tipoVenta) throws SNMPExceptions{
      String select = "";
     ProductoDB pDB= new ProductoDB();
     ClienteDB cDB= new ClienteDB();
      LinkedList<Factura> listaP = new LinkedList<Factura>();
          
          try {
              //Se intancia la clase de acceso a datos
            AccesoDatos accesoDatos= new AccesoDatos();
            
            //Se crea la sentencia de Busqueda
            select="EXEC ConsultarFacturasPorTipoVenta '"+tipoVenta+"'";
                    
            //se ejecuta la sentencia sql
            ResultSet rsPA= accesoDatos.ejecutaSQLRetornaRS(select);
            //se llama el array con los proyectos  
              while (rsPA.next()) {
                listaP.add(new Factura(new Pedido(pDB.consultarProductosPorFacturaID(rsPA.getInt("IdFactura"))), cDB.obtenerClientePorID(rsPA.getString("IdUsuario")),rsPA.getString("IdFactura"),rsPA.getString("IdEstado").equals("F")?EstadoFactura.Finalizado:EstadoFactura.Pendiente , LocalDate.parse(rsPA.getString("fecha")), rsPA.getString("tipoDespacho").equals("D")?TipoEnvio.EnvioDirecto:rsPA.getString("tipoDespacho").equals("E")?TipoEnvio.Encomienda:TipoEnvio.Presencial, rsPA.getString("IdTipoVenta").equals("CON")?TipoVenta.Contado:TipoVenta.Credito, 0, new Horario(rsPA.getString("IdHorario"), rsPA.getString("Horas"))));
              }
              rsPA.close();
              
         } catch (SQLException e) {
              throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, 
                                      e.getMessage(), e.getErrorCode());
          }catch (Exception e) {
              throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, 
                                      e.getMessage());
          } finally {
              
          }
          return listaP;
      }
    
}
