/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.SNMPExceptions;
import Model.DB.UsuarioDB;
import Model.Enums.TipoUsuario;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.faces.context.FacesContext;
import Model.Usuario;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.context.ExternalContext;
/**
 *
 * @author Alberto
 */
@Named(value = "obtenerDatosSesion")
@SessionScoped
public class BeanObtenerDatosSesion implements Serializable {
    
    Usuario UsuarioLogin= new Usuario();
    String datos;
    
    public Usuario getUsuarioLogin() {
        consultarSesion();
        return UsuarioLogin;
    }

    public void setUsuarioLogin(Usuario UsuarioLogin) {
        this.UsuarioLogin = UsuarioLogin;
    }

    public String getDatos() {
        return datos;
    }

    public void setDatos(String datos) {
        this.datos = datos;
    }
    /**
     * Creates a new instance of ObtenerDatosSesion
     */
    public BeanObtenerDatosSesion() {
        
    }
    
    public void consultarSesion(){
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("Usuario");
        
        final ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
	final Map<String, Object> session = context.getSessionMap();
	final Object user = session.get("Usuario");

	if (user != null) {
		try {
                    System.out.println("Tengo una sesion");
			String userId = user.toString();
                        this.setDatos(userId);
                        this.UsuarioLogin=UsuarioDB.obtenerUsuarioPorID(userId);
			
		} catch (ClassCastException e) {
			
			
		} catch (SNMPExceptions ex) {
                    System.out.println(ex.getMensajeParaDesarrollador());
            }
	}else{
            System.out.println("No tengo una sesion");
            this.UsuarioLogin= null;
		
	}
	
	
    }
            
}
