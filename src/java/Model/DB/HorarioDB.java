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
import Model.Horario;
import Model.Pedido;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.LinkedList;

/**
 *
 * @author Johan
 */
public class HorarioDB {
    private LinkedList<Horario> listaHorarios = new LinkedList<>();
    public LinkedList<Horario> obtenerHorarios () throws SNMPExceptions{
        String select = "";
          
          try {
              //Se intancia la clase de acceso a datos
            AccesoDatos accesoDatos= new AccesoDatos();
            
            //Se crea la sentencia de Busqueda
            select="EXEC PA_ConsultarHorarios";
                    
            //se ejecuta la sentencia sql
            ResultSet rsPA= accesoDatos.ejecutaSQLRetornaRS(select);
            //se llama el array con los proyectos  
              while (rsPA.next()) {
                listaHorarios.add(new Horario(rsPA.getString("Id"), rsPA.getString("Horas")));
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
          return listaHorarios;
    }
}
