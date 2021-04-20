/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.DB;

import DAO.AccesoDatos;
import DAO.SNMPExceptions;
import Model.Direccion;
import Model.Horario;
import Model.Usuario;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import javax.faces.model.SelectItem;

/**
 *
 * @author Fabian
 */
public class DireccionDB {

    public LinkedList<SelectItem> obtenerDirecciones(String idUsuario) throws SNMPExceptions {
        String select = "";
        LinkedList<SelectItem> listaDirecciones = new LinkedList<>();
        try {
            //Se intancia la clase de acceso a datos
            AccesoDatos accesoDatos = new AccesoDatos();

            //Se crea la sentencia de Busqueda
            select = "EXEC PA_ConsultarDirecciones '" + idUsuario + "'";

            //se ejecuta la sentencia sql
            ResultSet rsPA = accesoDatos.ejecutaSQLRetornaRS(select);
            //se llama el array con los proyectos  
            while (rsPA.next()) {
                Direccion objDireccion = new Direccion(rsPA.getInt("Id"), rsPA.getString("SENAS"), rsPA.getString("TIPODIRECCION"));
                listaDirecciones.add(new SelectItem(objDireccion.getIdDireccion(), objDireccion.getDescripcion()));
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
