/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.DB;

import DAO.AccesoDatos;
import DAO.SNMPExceptions;
import Model.Historial;
import Model.Pedido;
import Model.Producto;
import Model.ProductosCarrito;
import java.sql.ResultSet;
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
}
