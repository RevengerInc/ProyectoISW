/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.DB;

import DAO.AccesoDatos;
import DAO.SNMPExceptions;
import Model.Producto;
import Model.ProductosCarrito;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

/**
 *
 * @author César
 */
public class ProductoDB {
    private AccesoDatos accesoDatos = new AccesoDatos();
    private Connection conn;
    public ProductoDB() {
        accesoDatos= new AccesoDatos();
        accesoDatos.setDbConn(conn);
    }
    
    public  LinkedList<Producto> moTodo() throws SNMPExceptions{
      String select = "";
      LinkedList<Producto> listaP = new LinkedList<Producto>();
          
          try {
              //Se intancia la clase de acceso a datos
            AccesoDatos accesoDatos= new AccesoDatos();
            
            //Se crea la sentencia de Busqueda
            select="EXEC PA_ConsultarProductosActivos";
                    
            //se ejecuta la sentencia sql
            ResultSet rsPA= accesoDatos.ejecutaSQLRetornaRS(select);
            //se llama el array con los proyectos  
              while (rsPA.next()) {
                listaP.add(new Producto(rsPA.getString("ID"), rsPA.getString("NOMBRE"), rsPA.getString("DESCRIPCION"), "IMAGES/PRODUCTOS/"+rsPA.getString("IMG"), rsPA.getDouble("PRECIO"), rsPA.getInt("CANTDISPONIBLE"), rsPA.getInt("CANTMIN")));
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
    public  LinkedList<Producto> moTodoInactivos() throws SNMPExceptions{
      String select = "";
      LinkedList<Producto> listaP = new LinkedList<Producto>();
          
          try {
              //Se intancia la clase de acceso a datos
            AccesoDatos accesoDatos= new AccesoDatos();
            
            //Se crea la sentencia de Busqueda
            select="EXEC PA_ConsultarProductosInactivos";
                    
            //se ejecuta la sentencia sql
            ResultSet rsPA= accesoDatos.ejecutaSQLRetornaRS(select);
            //se llama el array con los proyectos  
              while (rsPA.next()) {
                listaP.add(new Producto(rsPA.getString("ID"), rsPA.getString("NOMBRE"), rsPA.getString("DESCRIPCION"), "IMAGES/PRODUCTOS/"+rsPA.getString("IMG"), rsPA.getDouble("PRECIO"), rsPA.getInt("CANTDISPONIBLE"), rsPA.getInt("CANTMIN")));
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
    public  LinkedList<ProductosCarrito> consultarProductosPorFacturaID(int idFactura) throws SNMPExceptions{
      String select = "";
      LinkedList<ProductosCarrito> listaP = new LinkedList<ProductosCarrito>();
          
          try {
              //Se intancia la clase de acceso a datos
            AccesoDatos accesoDatos= new AccesoDatos();
            
            //Se crea la sentencia de Busqueda
            select="EXEC PA_ConsultarProductosPorIDFactura "+idFactura;
                    
            //se ejecuta la sentencia sql
            ResultSet rsPA= accesoDatos.ejecutaSQLRetornaRS(select);
            //se llama el array con los proyectos  
              while (rsPA.next()) {
                listaP.add(new ProductosCarrito(new Producto(rsPA.getString("ID"), rsPA.getString("NOMBRE"), rsPA.getString("DESCRIPCION"), "IMAGES/PRODUCTOS/"+rsPA.getString("IMG"), rsPA.getDouble("PRECIO"), rsPA.getInt("CANTDISPONIBLE"), rsPA.getInt("CANTMIN")),rsPA.getInt("CANTIDAD")));
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
    public int insertarProducto(Producto producto) throws SNMPExceptions{
        String insert = "";
        int resultInsert;
        
        try {
            //Se intancia la clase de acceso a datos
            AccesoDatos accesoDatos = new AccesoDatos();

            //Se crea la sentencia de Busqueda
            insert = "EXEC PA_InsertarProducto '" + producto.getId() + "' , '" + producto.getNombre() + "' , '" + producto.getPrecio() + "' , '" + producto.getImagen()+ "' , '" + producto.getMinimo()+ "' , '" + producto.getCantidad() + "' , '" + producto.getDescripcion()+ "'";

            //se ejecuta la sentencia sql
            resultInsert = accesoDatos.ejecutaSQL(insert);
            //se llama el array con los proyectos  

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
    
    public Producto obtenerProductoPorID(String id) throws SNMPExceptions{
        String select = "";
        Producto producto = new Producto();

        try {
            //Se intancia la clase de acceso a datos
            AccesoDatos accesoDatos = new AccesoDatos();

            //Se crea la sentencia de Busqueda
            select = "EXEC PA_ConsultarProductoPorID '"+id+"'";

            //se ejecuta la sentencia sql
            ResultSet rsPA = accesoDatos.ejecutaSQLRetornaRS(select);
            //se llama el array con los proyectos  
            while (rsPA.next()) {
                producto = new Producto(rsPA.getString("ID"), rsPA.getString("NOMBRE"), rsPA.getString("DESCRIPCION"), "IMAGES/PRODUCTOS/"+rsPA.getString("IMG"), rsPA.getDouble("PRECIO"), rsPA.getInt("CANTDISPONIBLE"), rsPA.getInt("CANTMIN"));
            }
            rsPA.close();

        } catch (SQLException e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION,
                    e.getMessage(), e.getErrorCode());
        } catch (Exception e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION,
                    e.getMessage());
        } finally {

        }
        return producto;
    }
    
    public int EliminarProductoPorID(String id) throws SNMPExceptions{
        String elimina = "";
        int resultDelete;
        
        try {
            //Se intancia la clase de acceso a datos
            AccesoDatos accesoDatos = new AccesoDatos();

            //Se crea la sentencia de Busqueda
            elimina = "EXEC PA_EliminarProductoPorID '"+id+"'" ;

            //se ejecuta la sentencia sql
            resultDelete = accesoDatos.ejecutaSQL(elimina);
            //se llama el array con los proyectos  

        } catch (SQLException e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION,
                    e.getMessage(), e.getErrorCode());
        } catch (Exception e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION,
                    e.getMessage());
        } finally {

        }
        return resultDelete;
    }
    public int modificarProductoPorID(Producto producto) throws SNMPExceptions{
        String insert = "";
        int resultInsert;
        
        try {
            //Se intancia la clase de acceso a datos
            AccesoDatos accesoDatos = new AccesoDatos();

            //Se crea la sentencia de Busqueda
            insert = "EXEC PA_ModificarProductoPorID '" + producto.getId() + "' , '" + producto.getNombre() + "' , '" + producto.getPrecio() + "' , '" + producto.getImagen()+ "' , '" + producto.getMinimo()+ "' , '" + producto.getCantidad() + "' , '" + producto.getDescripcion()+ "'";

            //se ejecuta la sentencia sql
            resultInsert = accesoDatos.ejecutaSQL(insert);
            //se llama el array con los proyectos  

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
    
    public int HabilitarProductoPorID(String id) throws SNMPExceptions{
        String habilita = "";
        int resultDelete;
        
        try {
            //Se intancia la clase de acceso a datos
            AccesoDatos accesoDatos = new AccesoDatos();

            //Se crea la sentencia de Busqueda
            habilita = "EXEC PA_HabilitarProductoPorID'"+id+"'" ;

            //se ejecuta la sentencia sql
            resultDelete = accesoDatos.ejecutaSQL(habilita);
            //se llama el array con los proyectos  

        } catch (SQLException e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION,
                    e.getMessage(), e.getErrorCode());
        } catch (Exception e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION,
                    e.getMessage());
        } finally {

        }
        return resultDelete;
    }
}

    
   

