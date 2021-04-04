/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.DB;

import DAO.AccesoDatos;
import DAO.SNMPExceptions;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import javax.faces.model.SelectItem;

/**
 *
 * @author Fabian
 */
public class BarrioDB {
    private AccesoDatos accesoDatos = new AccesoDatos();
    private Connection conn;
    
    public BarrioDB() {
        accesoDatos= new AccesoDatos();
        accesoDatos.setDbConn(conn);
    }
    
    public  LinkedList<SelectItem> moTodo(int idProvincia, int idCanton, int idDistrito) throws SNMPExceptions{
      String select = "";
      LinkedList<SelectItem> listaBarrios = new LinkedList<SelectItem>();
          
          try {
              //Se intancia la clase de acceso a datos
            AccesoDatos accesoDatos= new AccesoDatos();
            
            //Se crea la sentencia de Busqueda
            select="PA_ConsultarBarriosPorIdDistritoIdCantonIdProvincia " + idDistrito + "," + idCanton + "," + idProvincia;
                    
            //se ejecuta la sentencia sql
            ResultSet rsPA= accesoDatos.ejecutaSQLRetornaRS(select);
            //se llama el array con los proyectos  
              while (rsPA.next()) {
                listaBarrios.add(new SelectItem(rsPA.getInt("ID"), rsPA.getString("NOMBRE")));
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
          return listaBarrios;
      }
}
