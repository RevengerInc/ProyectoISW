/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Enums.OpcionesComboUsuario;
import Model.Enums.TipoUsuario;
import Model.Usuario;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;

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
    private BeanObtenerDatosSesion obtenerSesion= new BeanObtenerDatosSesion();
    public BeanUsuario() {
        
        
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
                        return "";
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
                        return "Reportes";
                    case BODEGUERO:
                        return "";
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
    
}
