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
       
    String correoIngresado;
    String contrasennia;
    String error;
    Usuario usuario;

     /**
     * Creates a new instance of LoginControlador
     */
    public BeanLoginControlador() {
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
                System.out.println("HOLA1");
                error=usuario.getNombre();
            }else{
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("Usuario",correoIngresado);
                if(usuario.getTipoUsuario().equals(TipoUsuario.ADMINISTRADOR)){
                    System.out.println("HOLA2");
                    FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("TipoUsuario","Administrador");
                    
                }else if(usuario.getTipoUsuario().equals(TipoUsuario.BODEGUERO)){
                    System.out.println("HOLA3");
                    FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("TipoUsuario","Bodeguero");
                    FacesContext.getCurrentInstance().getExternalContext().redirect("PrincipalBodega.xhtml");
                }else{
                    System.out.println("HOLA4");
                    FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("TipoUsuario","Cliente");
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
