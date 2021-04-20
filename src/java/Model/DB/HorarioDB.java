/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.DB;

import DAO.AccesoDatos;
import DAO.SNMPExceptions;
import Model.Horario;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import javax.faces.model.SelectItem;

/**
 *
 * @author Johan
 */
public class HorarioDB {

    public LinkedList<SelectItem> obtenerHorarios() throws SNMPExceptions {
        String select = "";
        LinkedList<SelectItem> listaHorarios = new LinkedList<>();

        try {
            //Se intancia la clase de acceso a datos
            AccesoDatos accesoDatos = new AccesoDatos();

            //Se crea la sentencia de Busqueda
            select = "EXEC PA_ConsultarHorarios";

            //se ejecuta la sentencia sql
            ResultSet rsPA = accesoDatos.ejecutaSQLRetornaRS(select);
            //se llama el array con los proyectos  
            while (rsPA.next()) {
                Horario objHorario = new Horario(rsPA.getString("Id"), rsPA.getString("Horas"));
                listaHorarios.add(new SelectItem(objHorario.getId(), objHorario.getHora()));
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
        return listaHorarios;
    }
}
