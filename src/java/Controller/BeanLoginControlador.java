/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.SNMPExceptions;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import Model.Usuario;
import Model.DB.UsuarioDB;
import Model.Enums.TipoUsuario;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.context.FacesContext;

/**
 *
 * @author Alberto
 */
@Named(value = "loginControlador")
@SessionScoped
public class BeanLoginControlador implements Serializable {
       
    private String correoIngresado;
    private String contrasennia;
    private String error;
    private Usuario usuario;
    private BeanUsuario beanUsuario= new BeanUsuario();

     /**
     * Creates a new instance of LoginControlador
     */
    public BeanLoginControlador() {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("Usuario","");
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("TipoUsuario","");
    }
    
    public String getCorreoIngresado() {
        return correoIngresado;
    }

    public void setCorreoIngresado(String Usuario) {
        this.correoIngresado = Usuario;
    }

    public String getContrasennia() {
        return contrasennia;
    }

    public void setContrasennia(String contrasennia) {
        this.contrasennia = contrasennia;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
    
    
   public void autenticar(){
       
        try {
            usuario=UsuarioDB.Autenticar(this.getCorreoIngresado(), this.getContrasennia());
            
            if (usuario.getNombre().equals("Contraseña incorrecta") || usuario.getNombre().equals("Correo incorrecto")){
                error=usuario.getNombre();
            }else{
                beanUsuario.setUsuario(usuario);
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().replace("Usuario",correoIngresado);
                if(usuario.getTipoUsuario().equals(TipoUsuario.ADMINISTRADOR)){
                    FacesContext.getCurrentInstance().getExternalContext().getSessionMap().replace("TipoUsuario","Administrador");
                    FacesContext.getCurrentInstance().getExternalContext().redirect("Principal.xhtml");
                }else if(usuario.getTipoUsuario().equals(TipoUsuario.BODEGUERO)){
                    FacesContext.getCurrentInstance().getExternalContext().getSessionMap().replace("TipoUsuario","Bodeguero");
                    FacesContext.getCurrentInstance().getExternalContext().redirect("PrincipalBodega.xhtml");
                }else{
                    FacesContext.getCurrentInstance().getExternalContext().getSessionMap().replace("TipoUsuario","Cliente");
                    FacesContext.getCurrentInstance().getExternalContext().redirect("Principal.xhtml");
                }
                
            }
        } catch (SNMPExceptions ex) {
            error+=ex.getMensajeParaDesarrollador();
        } catch (IOException ex) {
           error+=ex.toString();
        }
       
       
   }
    
}
