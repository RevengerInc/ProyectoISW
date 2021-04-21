/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.SNMPExceptions;
import Model.DB.DireccionDB;
import Model.DB.ProvinciaDB;
import Model.DB.UsuarioDB;
import Model.Enums.TipoUsuario;
import Model.Usuario;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.LinkedList;
import javax.faces.model.SelectItem;

/**
 *
 * @author César
 */
@Named(value = "beanUsuario")
@SessionScoped
public class BeanUsuario implements Serializable {

    /**
     * Creates a new instance of BeanUsuario
     * 
     */
    private Usuario usuario = new Usuario();
    private Usuario usuarioMantenimiento = new Usuario();
    private LinkedList<SelectItem> listaDirecciones = new LinkedList<>();
    private LinkedList<SelectItem> provincia = new LinkedList<>();
    private BeanObtenerDatosSesion obtenerSesion= new BeanObtenerDatosSesion();
    private int numDireccion = 0;
    private String error = "";
    private String contrasenniaMantenimiento = "";
    private String contrasenniaConfirmacionMantenimiento = "";
    private UsuarioDB usuarioDB = new UsuarioDB();
    
    public BeanUsuario() {
        
        
    }
    public void guardarCambiosMantenimiento(){
        if(usuarioMantenimiento.getNombre().trim().isEmpty()){
            
        }
    }
    public void refrescarUsuarioLogeado(){
        if(obtenerSesion.getUsuarioLogin()==null){
            usuario.setTipoUsuario(TipoUsuario.NOINGRESADO);
            usuario.setNombre("Usuario");
        }else{
            usuario=obtenerSesion.getUsuarioLogin();
        }
    }
    public Usuario getUsuario() {
        refrescarUsuarioLogeado();
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    public String linkOpciones(int numOpcion){
        TipoUsuario tipo= this.usuario.getTipoUsuario();
        refrescarUsuarioLogeado();
        switch(numOpcion){
            case 1:
                return tipo.equals(TipoUsuario.NOINGRESADO)?"FormRegistroC":"MantenimientoPerfil";
            case 2:
                switch(tipo){
                    case ADMINISTRADOR:
                        return "Reportes";
                    case BODEGUERO:
                        return "index";
                    case CLIENTE:
                        return "HistorialPedido";
                    case NOINGRESADO:
                        return "index";
                    default:
                        return "Opción inválida";
                }
            case 3:
                
                return "index";
            default:
                return "Opción inválida";
        }
    }
    
    public String etiquetaOpciones(int numOpcion){
        refrescarUsuarioLogeado();
        TipoUsuario tipo= this.usuario.getTipoUsuario();
        switch(numOpcion){
            case 1:
                return tipo.equals(TipoUsuario.NOINGRESADO)?"Registrarse":"Modificar perfil";
            case 2:
                switch(tipo){
                    case ADMINISTRADOR:
                        return "Reportes y Solicitudes";
                    case BODEGUERO:
                        return " ";
                    case CLIENTE:
                        return "Historial";
                    case NOINGRESADO:
                        return "Ingresar";
                    default:
                        return "Opción inválida";
                }
            case 3:
                return tipo.equals(TipoUsuario.NOINGRESADO)?"":"Cerrar sesión";
            default:
                return "Opción inválida";
        }
    }

    public LinkedList<SelectItem> getListaDirecciones() {
        try {
            DireccionDB dirDB = new DireccionDB();
            listaDirecciones = dirDB.obtenerDirecciones(usuario.getCorreoID());
        } catch (SNMPExceptions ex) {
            error = ex.getMensajeParaDesarrollador();
        }
        return listaDirecciones;
    }
    
    public void prueba (String id){
        System.out.println("Hello");
        aceptarUsuario(id);
    }
    
    public LinkedList<Usuario> usuariosEspera(){
        LinkedList<Usuario> listaUsuariosEspera = new LinkedList<>();
        try {
            listaUsuariosEspera = usuarioDB.obtenerUsuarioPorEstado("ESPERA");
        } catch (SNMPExceptions ex) {
            System.out.println(ex.getMensajeParaDesarrollador());
        }
        
        return listaUsuariosEspera;
    }
    
    public LinkedList<Usuario> usuariosDenegados(){
        LinkedList<Usuario> listaUsuariosNegados = new LinkedList<>();
        try {
            listaUsuariosNegados = usuarioDB.obtenerUsuarioPorEstado("NEGADO");
        } catch (SNMPExceptions ex) {
            System.out.println(ex.getMensajeParaDesarrollador());
        }
        
        return listaUsuariosNegados;
    }
    
    public void aceptarUsuario(String usuarioId){
        try {
            usuarioDB.ModificarEstadoUsuario("ACTIVO", usuarioId);
        } catch (SNMPExceptions ex) {
            System.out.println(ex.getMensajeParaDesarrollador());
        }
    }
    
    public void denegarUsuario(String usuarioId){
        try {
            usuarioDB.ModificarEstadoUsuario("NEGADO", usuarioId);
        } catch (SNMPExceptions ex) {
            System.out.println(ex.getMensajeParaDesarrollador());
        }
    }

    public void setListaDirecciones(LinkedList<SelectItem> listaDirecciones) {
        this.listaDirecciones = listaDirecciones;
    }

    public int getNumDireccion() {
        return numDireccion;
    }

    public void setNumDireccion(int numDireccion) {
        this.numDireccion = numDireccion;
    }

    public LinkedList<SelectItem> getProvincia() {
        ProvinciaDB pDB= new ProvinciaDB();
        try {
            provincia=pDB.moTodo();
        } catch (SNMPExceptions ex) {
            error = ex.getMensajeParaDesarrollador();
        }
        return provincia;
    }

    public void setProvincia(LinkedList<SelectItem> provincia) {
        this.provincia = provincia;
    }

    public Usuario getUsuarioMantenimiento() {
        if(obtenerSesion.getUsuarioLogin()==null){
            usuarioMantenimiento.setTipoUsuario(TipoUsuario.NOINGRESADO);
            usuarioMantenimiento.setNombre("Usuario");
        }else{
            usuarioMantenimiento=obtenerSesion.getUsuarioLogin();
        }
        return usuarioMantenimiento;
    }

    public void setUsuarioMantenimiento(Usuario usuarioMantenimiento) {
        this.usuarioMantenimiento = usuarioMantenimiento;
    }

    public String getContrasenniaMantenimiento() {
        return contrasenniaMantenimiento;
    }

    public void setContrasenniaMantenimiento(String contrasenniaMantenimiento) {
        this.contrasenniaMantenimiento = contrasenniaMantenimiento;
    }

    public String getContrasenniaConfirmacionMantenimiento() {
        return contrasenniaConfirmacionMantenimiento;
    }

    public void setContrasenniaConfirmacionMantenimiento(String contrasenniaConfirmacionMantenimiento) {
        this.contrasenniaConfirmacionMantenimiento = contrasenniaConfirmacionMantenimiento;
    }
    
    
}
