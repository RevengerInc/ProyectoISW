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
 * @author Fabian
 */
public class FacturaDB {
    
    public LinkedList <Factura>  mostrarFacturasPendientes(TipoEnvio envio) throws SNMPExceptions{
        
        ProductoDB prodDB= new ProductoDB();
        String select = "";
        LinkedList<Factura> listaP = new LinkedList<Factura>();
        Factura factura1;
          
          try {
              //Se intancia la clase de acceso a datos
            AccesoDatos accesoDatos= new AccesoDatos();
            
            //Se crea la sentencia de Busqueda
            select="EXEC PA_ConsultarProductosPorIDFactura ";
                    
            //se ejecuta la sentencia sql
            ResultSet rsPA= accesoDatos.ejecutaSQLRetornaRS(select);
            //se llama el array con los proyectos  
              while (rsPA.next()) {
                factura1 = new Factura();  
                factura1.setCliente(new ClienteDB().obtenerClientePorID(rsPA.getString("IdUsuario")));
                factura1.setPedido(new Pedido(prodDB.consultarProductosPorFacturaID(rsPA.getInt("ID"))));
                factura1.setId(rsPA.getString("ID"));
                factura1.setEstado(rsPA.getString("IdEstado").equals("P")?EstadoFactura.Pendiente:rsPA.getString("IdEstado").equals("E")?EstadoFactura.EnProceso:EstadoFactura.Finalizado);
                factura1.setFechaPedido(LocalDate.parse(rsPA.getString("fecha")));
                factura1.setTipoEnvio(rsPA.getString("TipoDespacho").equals("D")?TipoEnvio.EnvioDirecto:rsPA.getString("TipoDespacho").equals("E")?TipoEnvio.Encomienda:TipoEnvio.Presencial);
                factura1.setTipoVenta(rsPA.getString("IdTipoVenta").equals("CON")?TipoVenta.Contado:TipoVenta.Credito);
                factura1.setHorario(new Horario(rsPA.getString("IdHorario"), rsPA.getString("Horas")));
                listaP.add(factura1);
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
          return null;
    }
    public  LinkedList<Integer> ConsultarFacturasIDPorUsuarioID(String correoUsuario, String estado) throws SNMPExceptions{
      String select = "";
      LinkedList<Integer> listaP = new LinkedList<Integer>();
          
          try {
              //Se intancia la clase de acceso a datos
            AccesoDatos accesoDatos= new AccesoDatos();
            
            //Se crea la sentencia de Busqueda
            select="EXEC PA_ConsultarFacturasIDPorUsuarioID '"+correoUsuario+"', '"+estado+"'";
                    
            //se ejecuta la sentencia sql
            ResultSet rsPA= accesoDatos.ejecutaSQLRetornaRS(select);
            //se llama el array con los proyectos  
              while (rsPA.next()) {
                listaP.add(rsPA.getInt("ID"));
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
