/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.DB;

import DAO.AccesoDatos;
import DAO.SNMPExceptions;
import Model.Direccion;
import Model.Usuario;
import java.sql.SQLException;
import java.util.LinkedList;

/**
 *
 * @author Fabian
 */
public class DireccionDB {
    LinkedList<Direccion> listaDirecciones = new LinkedList<>();
    
    public LinkedList<Direccion> obtenerDirecciones (){
        listaDirecciones.add(new Direccion(1, "500 metros sur este del templo catolico de san rafale de poás", "P"));
        listaDirecciones.add(new Direccion(1, "200 metros norte de la granja pipasa en calle liles", "S"));
        
        return listaDirecciones;
    }
    
    public int insertarDireccion(Usuario usuario, int idBarrio, int idDistrito, int idCanton, int idProvincia, Direccion direccion) throws SNMPExceptions {
        String insert = "";
        int resultInsert;
        try {
            //Se intancia la clase de acceso a datos
            AccesoDatos accesoDatos = new AccesoDatos();

            //Se crea la sentencia de Busqueda
            insert = "EXEC PA_InsertarDireccion '" + usuario.getCorreoID() + "' ," + idBarrio + "," + idDistrito + "," + idCanton + "," + idProvincia + ", '" + direccion.getDescripcion() + "' , '1'";

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
}
