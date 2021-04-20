/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.DB;

import DAO.AccesoDatos;
import DAO.SNMPExceptions;
import Model.Enums.TipoEnvio;
import Model.Historial;
import Model.Pedido;
import java.sql.SQLException;
import java.util.LinkedList;

/**
 *
 * @author César
 */
public class HistorialDB {
    public  LinkedList<Historial> moTodo(String correoUsuario, String estado) throws SNMPExceptions{
      String select = "";
      LinkedList<Historial> listaP = new LinkedList<Historial>();
      LinkedList<Integer> listaI = new LinkedList<Integer>();
      FacturaDB factDB= new FacturaDB();
      ProductoDB prodDB= new ProductoDB();
      listaI = factDB.ConsultarFacturasIDPorUsuarioID(correoUsuario, estado);    
          try {
              
              for (Integer id : listaI) {
                  listaP.add(new Historial(id, new Pedido(prodDB.consultarProductosPorFacturaID(id))));
              }
              
            }catch (Exception e) {
              throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, 
                                      e.getMessage());
          } finally {
              
          }
          return listaP;
      }
    
    public int insertarHistorialEntrega(Historial historial, String envio, String horaRetiro) throws SNMPExceptions {
        String insert = "";
        int resultInsert;
        try {
            AccesoDatos accesoDatos = new AccesoDatos();

            insert = "PA_InsertarHistorialEntrega " + null + " , '" + envio + "' , '" + horaRetiro + " ' ";

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
