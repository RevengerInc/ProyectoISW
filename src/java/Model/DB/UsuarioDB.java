/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.DB;

import DAO.AccesoDatos;
import DAO.SNMPExceptions;
import Model.Cliente;
import Model.Enums.TipoUsuario;
import Model.Producto;
import Model.ProductosCarrito;
import Model.Usuario;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import javax.faces.model.SelectItem;

/**
 *
 * @author Alberto
 */
public class UsuarioDB {

    public static Usuario Autenticar(String Login, String Password) throws SNMPExceptions {

        String select = "";
        Usuario user = new Usuario();

        try {
            //Se intancia la clase de acceso a datos
            AccesoDatos accesoDatos = new AccesoDatos();

            //Se crea la sentencia de Busqueda
            select = "EXEC ConsultarLoginPorUsuarioID '" + Login + "'";

            //se ejecuta la sentencia sql
            ResultSet rsPA = accesoDatos.ejecutaSQLRetornaRS(select);
            //se llama el array con los proyectos  
            if (rsPA.next() != false) {
                //rsPA.next();
                if (rsPA.getString("contrasena").equals(Password)) {
                    user = obtenerUsuarioPorID(Login);
                } else {
                    user.setNombre("Contraseña incorrecta");
                }

            } else {
                user.setNombre("Correo incorrecto");
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
        return user;
    }

    public static Usuario obtenerUsuarioPorID(String correo) throws SNMPExceptions {
        String select = "";
        Usuario user = new Usuario();

        try {
            //Se intancia la clase de acceso a datos
            AccesoDatos accesoDatos = new AccesoDatos();

            //Se crea la sentencia de Busqueda
            select = "EXEC PA_ConsultarUsuarioPorID '" + correo + "'";

            //se ejecuta la sentencia sql
            ResultSet rsPA = accesoDatos.ejecutaSQLRetornaRS(select);
            //se llama el array con los proyectos  
            while (rsPA.next()) {
                user = new Cliente(rsPA.getString("NOMBRE"), rsPA.getString("IDTIPOUSUARIO").equals("C") ? TipoUsuario.CLIENTE : rsPA.getString("IDTIPOUSUARIO").equals("B") ? TipoUsuario.BODEGUERO : TipoUsuario.ADMINISTRADOR, rsPA.getString("CEDULA"), rsPA.getString("TELEFONO"), correo, rsPA.getString("ESTADO"), rsPA.getString("CONTRASENA"));
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
        return user;

    }

    public int insertarUsuario(Usuario usuario) throws SNMPExceptions {
        String insert = "";
        int resultInsert;
        try {
            //Se intancia la clase de acceso a datos
            AccesoDatos accesoDatos = new AccesoDatos();

            //Se crea la sentencia de Busqueda
            insert = "EXEC PA_InsertarCliente '" + usuario.getCedula() + "' , '" + usuario.getTelefono() + "' , '" + usuario.getCorreoID() + "' , '" + usuario.getPassword() + "' , '" + usuario.getNombre() + "' , '" + usuario.getTipoUsuario() + "' , '" + usuario.getEstado() + "'";

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
