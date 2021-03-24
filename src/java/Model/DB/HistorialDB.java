/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.DB;

import Model.Historial;
import Model.Pedido;
import Model.Producto;
import Model.ProductosCarrito;
import java.util.LinkedList;

/**
 *
 * @author César
 */
public class HistorialDB {
    public  LinkedList<Historial> moTodo(){
      String select = "";
      LinkedList<Historial> listaH = new LinkedList<Historial>();
          
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
                
                
                listaH.add(new Historial(1, new Pedido(listaPC)));
                listaH.add(new Historial(2, new Pedido(listaPC)));
                listaH.add(new Historial(3, new Pedido(listaPC)));
               /* listaH.add(new Producto("zz21cz3c1", "Blue Band", "Product Description", "blue-band.jpg", 79, "Fitness", 2));
                listaH.add(new Producto("244wgerg2", "Blue T-Shirt", "Product Description", "blue-t-shirt.jpg", 29, "Clothing", 25));
                listaH.add(new Producto("h456wer53", "Bracelet", "Product Description", "bracelet.jpg", 15, "Accessories", 73));
                listaH.add(new Producto("av2231fwg", "Brown Purse", "Product Description", "brown-purse.jpg", 120, "Accessories", 0));
                listaH.add(new Producto("bib36pfvm", "Chakra Bracelet", "Product Description", "chakra-bracelet.jpg", 32, "Accessories", 5));
                listaH.add(new Producto("mbvjkgip5", "Galaxy Earrings", "Product Description", "galaxy-earrings.jpg", 34, "Accessories", 23));
                listaH.add(new Producto("vbb124btr", "Game Controller", "Product Description", "game-controller.jpg", 99, "Electronics", 2));
                listaH.add(new Producto("cm230f032", "Gaming Set", "Product Description", "gaming-set.jpg", 299, "Electronics", 63));
                listaH.add(new Producto("plb34234v", "Gold Phone Case", "Product Description", "gold-phone-case.jpg", 24, "Accessories", 0));
                listaH.add(new Producto("4920nnc2d", "Green Earbuds", "Product Description", "green-earbuds.jpg", 89, "Electronics", 23));
                listaH.add(new Producto("250vm23cc", "Green T-Shirt", "Product Description", "green-t-shirt.jpg", 49, "Clothing", 74));
                listaH.add(new Producto("fldsmn31b", "Grey T-Shirt", "Product Description", "grey-t-shirt.jpg", 48, "Clothing", 0));
                listaH.add(new Producto("waas1x2as", "Headphones", "Product Description", "headphones.jpg", 175, "Electronics", 8));
                listaH.add(new Producto("vb34btbg5", "Light Green T-Shirt", "Product Description", "light-green-t-shirt.jpg", 49, "Clothing", 34));
                listaH.add(new Producto("k8l6j58jl", "Lime Band", "Product Description", "lime-band.jpg", 79, "Fitness", 12));
                listaH.add(new Producto("v435nn85n", "Mini Speakers", "Product Description", "mini-speakers.jpg", 85, "Clothing", 42));
                listaH.add(new Producto("09zx9c0zc", "Painted Phone Case", "Product Description", "painted-phone-case.jpg", 56, "Accessories", 41));
                listaH.add(new Producto("mnb5mb2m5", "Pink Band", "Product Description", "pink-band.jpg", 79, "Fitness", 63));
                listaH.add(new Producto("r23fwf2w3", "Pink Purse", "Product Description", "pink-purse.jpg", 110, "Accessories", 0));
                listaH.add(new Producto("pxpzczo23", "Purple Band", "Product Description", "purple-band.jpg", 79, "Fitness", 6));
                listaH.add(new Producto("2c42cb5cb", "Purple Gemstone Necklace", "Product Description", "purple-gemstone-necklace.jpg", 45, "Accessories", 62));
                listaH.add(new Producto("5k43kkk23", "Purple T-Shirt", "Product Description", "purple-t-shirt.jpg", 49, "Clothing", 2));
                listaH.add(new Producto("lm2tny2k4", "Shoes", "Product Description", "shoes.jpg", 64, "Clothing", 0));
                listaH.add(new Producto("nbm5mv45n", "Sneakers", "Product Description", "sneakers.jpg", 78, "Clothing", 52));
                listaH.add(new Producto("zx23zc42c", "Teal T-Shirt", "Product Description", "teal-t-shirt.jpg", 49, "Clothing", 3));
                listaH.add(new Producto("acvx872gc", "Yellow Earbuds", "Product Description", "yellow-earbuds.jpg", 89, "Electronics", 35));
                listaH.add(new Producto("tx125ck42", "Yoga Mat", "Product Description", "yoga-mat.jpg", 20, "Fitness", 15));
                listaH.add(new Producto("gwuby345v", "Yoga Set", "Product Description", "yoga-set.jpg", 20, "Fitness", 25));*/
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
          return listaH;
      }
}
