/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.DB;

import DAO.AccesoDatos;
import DAO.SNMPExceptions;
import Model.Cliente;
import Model.Enums.TipoUsuario;
import Model.Producto;
import Model.ProductosCarrito;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

/**
 *
 * @author Fabian
 */
public class ClienteDB {
    
    public Cliente obtenerCliente(){
        return new Cliente("Fabian Espinoza", TipoUsuario.Cliente, "118110563","88888888","fabian16viquez@gmail.com");
    }
    public Cliente obtenerClientePorID(String correo)throws SNMPExceptions{
      String select = "";
      Cliente cliente= new Cliente();
          
          try {
              //Se intancia la clase de acceso a datos
            AccesoDatos accesoDatos= new AccesoDatos();
            
            //Se crea la sentencia de Busqueda
            select="EXEC PA_ConsultarUsuarioPorID '"+correo+"'";
                    
            //se ejecuta la sentencia sql
            ResultSet rsPA= accesoDatos.ejecutaSQLRetornaRS(select);
            //se llama el array con los proyectos  
              while (rsPA.next()) {
                  cliente= new Cliente(rsPA.getString("NOMBRE"), rsPA.getString("IDTIPOUSUARIO").equals("C")?TipoUsuario.Cliente:rsPA.getString("IDTIPOUSUARIO").equals("B")?TipoUsuario.Bodeguero:TipoUsuario.Administrador, rsPA.getString("CEDULA"), rsPA.getString("TELEFONO"), correo);
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
          return cliente;
      
    }
    public  LinkedList<String> consultarIDUsuarioPorEstadoFactura(String estadoFactura) throws SNMPExceptions{
      String select = "";
      LinkedList<String> listaP = new LinkedList<String>();
          
          try {
              //Se intancia la clase de acceso a datos
            AccesoDatos accesoDatos= new AccesoDatos();
            
            //Se crea la sentencia de Busqueda
            select="EXEC PA_ConsultarIDUsuarioPorEstadoFactura "+estadoFactura;
                    
            //se ejecuta la sentencia sql
            ResultSet rsPA= accesoDatos.ejecutaSQLRetornaRS(select);
            //se llama el array con los proyectos  
              while (rsPA.next()) {
                listaP.add(rsPA.getString("IDUSUARIO"));
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
