/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.DB;

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
import java.time.LocalDate;
import java.util.LinkedList;

/**
 *
 * @author César
 */
public class ReporteDB {
    public  LinkedList<Factura> reportesPorEstado(EstadoFactura estado){
      String select = "";
      LinkedList<Factura> listaF = new LinkedList<Factura>();
          
          try {
    
             /* //Se instancia la clase de acceso a datos
              AccesoDatos accesoDatos = new AccesoDatos();  

              //Se crea la sentencia de búsqueda
              select = 
                      "SELECT COD_PROVINCIA,DSC_CORTA_PROVINCIA,"
                      + "DSC_PROVINCIA,LOG_ACTIVO FROM Provincias";
              //Se ejecuta la sentencia SQL
              ResultSet rsPA = accesoDatos.ejecutaSQLRetornaRS(select);
             //Se llena el arryaList con los proyectos   
              while (rsPA.next()) {

                float codigoProvincia = rsPA.getFloat("COD_PROVINCIA");
                String dscCortaProvincia = rsPA.getString(""
                        + "DSC_CORTA_PROVINCIA");
                String dscProvincia = rsPA.getString("DSC_PROVINCIA");
                float logActivo= rsPA.getFloat("LOG_ACTIVO");*/
                LinkedList<ProductosCarrito> listaPC = new LinkedList<ProductosCarrito>();
                listaPC.add(new ProductosCarrito(new Producto("f230fh0g3", "Bamboo Watch", "Product Description", "bamboo-watch.jpg", 65, "Accessories", 24), 2));
                listaPC.add(new ProductosCarrito(new Producto("nvklal433", "Black Watch", "Product Description", "black-watch.jpg", 72, "Accessories", 61),5));
                listaPC.add(new ProductosCarrito(new Producto("zz21cz3c1", "Blue Band", "Product Description", "blue-band.jpg", 79, "Fitness", 2), 3));
                
                if(estado.EnProceso.equals(estado)){
                    listaF.add(new Factura(new Pedido(listaPC), new Cliente("Cesar", TipoUsuario.Cliente, "111111111", "11111", "cesarbadilla@hotmail.com"), EstadoFactura.EnProceso, LocalDate.now(), TipoEnvio.Encomienda, TipoVenta.Contado, new Horario(1, "8am") ,"1"));
                
                }else if(estado.Entregado.equals(estado)){
                    listaF.add(new Factura(new Pedido(listaPC), new Cliente("Fabian", TipoUsuario.Cliente, "111111111", "11111", "fabianespinoza@hotmail.com"), EstadoFactura.Entregado, LocalDate.now(), TipoEnvio.EnvioDirecto, TipoVenta.Credito, new Horario(1, "8am") ,"2"));
                
                }else{
                   listaF.add(new Factura(new Pedido(listaPC), new Cliente("Johan", TipoUsuario.Cliente, "111111111", "11111", "johanchaves@hotmail.com"), EstadoFactura.Pendiente, LocalDate.now(), TipoEnvio.PersonalEmpresa, TipoVenta.Contado, new Horario(1, "8am") ,"3"));
                 
                }
              //}
              //rsPA.close();
              
         /* } catch (SQLException e) {
              throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, 
                                      e.getMessage(), e.getErrorCode());
          */}catch (Exception e) {
              /*throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, 
                                      e.getMessage());*/
          } finally {
              
          }
          return listaF;
      }
    public  LinkedList<Factura> reportesPorTipoVentaContado(){
      String select = "";
      LinkedList<Factura> listaF = new LinkedList<Factura>();
          
          try {
    
             /* //Se instancia la clase de acceso a datos
              AccesoDatos accesoDatos = new AccesoDatos();  

              //Se crea la sentencia de búsqueda
              select = 
                      "SELECT COD_PROVINCIA,DSC_CORTA_PROVINCIA,"
                      + "DSC_PROVINCIA,LOG_ACTIVO FROM Provincias";
              //Se ejecuta la sentencia SQL
              ResultSet rsPA = accesoDatos.ejecutaSQLRetornaRS(select);
             //Se llena el arryaList con los proyectos   
              while (rsPA.next()) {

                float codigoProvincia = rsPA.getFloat("COD_PROVINCIA");
                String dscCortaProvincia = rsPA.getString(""
                        + "DSC_CORTA_PROVINCIA");
                String dscProvincia = rsPA.getString("DSC_PROVINCIA");
                float logActivo= rsPA.getFloat("LOG_ACTIVO");*/
                LinkedList<ProductosCarrito> listaPC = new LinkedList<ProductosCarrito>();
                listaPC.add(new ProductosCarrito(new Producto("f230fh0g3", "Bamboo Watch", "Product Description", "bamboo-watch.jpg", 65, "Accessories", 24), 2));
                listaPC.add(new ProductosCarrito(new Producto("nvklal433", "Black Watch", "Product Description", "black-watch.jpg", 72, "Accessories", 61),5));
                listaPC.add(new ProductosCarrito(new Producto("zz21cz3c1", "Blue Band", "Product Description", "blue-band.jpg", 79, "Fitness", 2), 3));
                
                
                    listaF.add(new Factura(new Pedido(listaPC), new Cliente("Cesar", TipoUsuario.Cliente, "111111111", "11111", "cesarbadilla@hotmail.com"), EstadoFactura.EnProceso, LocalDate.now(), TipoEnvio.Encomienda, TipoVenta.Contado, new Horario(1, "8am") ,"1"));
                
                
                   listaF.add(new Factura(new Pedido(listaPC), new Cliente("Johan", TipoUsuario.Cliente, "111111111", "11111", "johanchaves@hotmail.com"), EstadoFactura.Pendiente, LocalDate.now(), TipoEnvio.PersonalEmpresa, TipoVenta.Contado, new Horario(1, "8am") ,"3"));
                 
                
              //}
              //rsPA.close();
              
         /* } catch (SQLException e) {
              throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, 
                                      e.getMessage(), e.getErrorCode());
          */}catch (Exception e) {
              /*throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, 
                                      e.getMessage());*/
          } finally {
              
          }
          return listaF;
      }
    public  LinkedList<Factura> reportesPorTipoVentaCredito(){
      String select = "";
      LinkedList<Factura> listaF = new LinkedList<Factura>();
          
          try {
    
             /* //Se instancia la clase de acceso a datos
              AccesoDatos accesoDatos = new AccesoDatos();  

              //Se crea la sentencia de búsqueda
              select = 
                      "SELECT COD_PROVINCIA,DSC_CORTA_PROVINCIA,"
                      + "DSC_PROVINCIA,LOG_ACTIVO FROM Provincias";
              //Se ejecuta la sentencia SQL
              ResultSet rsPA = accesoDatos.ejecutaSQLRetornaRS(select);
             //Se llena el arryaList con los proyectos   
              while (rsPA.next()) {

                float codigoProvincia = rsPA.getFloat("COD_PROVINCIA");
                String dscCortaProvincia = rsPA.getString(""
                        + "DSC_CORTA_PROVINCIA");
                String dscProvincia = rsPA.getString("DSC_PROVINCIA");
                float logActivo= rsPA.getFloat("LOG_ACTIVO");*/
                LinkedList<ProductosCarrito> listaPC = new LinkedList<ProductosCarrito>();
                listaPC.add(new ProductosCarrito(new Producto("f230fh0g3", "Bamboo Watch", "Product Description", "bamboo-watch.jpg", 65, "Accessories", 24), 2));
                listaPC.add(new ProductosCarrito(new Producto("nvklal433", "Black Watch", "Product Description", "black-watch.jpg", 72, "Accessories", 61),5));
                listaPC.add(new ProductosCarrito(new Producto("zz21cz3c1", "Blue Band", "Product Description", "blue-band.jpg", 79, "Fitness", 2), 3));
                
                
                
                    listaF.add(new Factura(new Pedido(listaPC), new Cliente("Fabian", TipoUsuario.Cliente, "111111111", "11111", "fabianespinoza@hotmail.com"), EstadoFactura.Entregado, LocalDate.now(), TipoEnvio.EnvioDirecto, TipoVenta.Credito,new Horario(1, "8am") , "2"));
                
                 
                
              //}
              //rsPA.close();
              
         /* } catch (SQLException e) {
              throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, 
                                      e.getMessage(), e.getErrorCode());
          */}catch (Exception e) {
              /*throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, 
                                      e.getMessage());*/
          } finally {
              
          }
          return listaF;
      }
}
