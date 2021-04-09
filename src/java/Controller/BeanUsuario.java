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
    public BeanUsuario() {
        
        usuario.setTipoUsuario(TipoUsuario.NOINGRESADO);
    }
    

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    public String opcion1(){
        return usuario.getTipoUsuario()==TipoUsuario.NOINGRESADO?"Registrarse":"";
    }
    public String opcion1(int n){
        return usuario.getTipoUsuario()==TipoUsuario.NOINGRESADO?"FormRegistroC":"";
    }
    public String opcion2(){
        return usuario.getTipoUsuario()==TipoUsuario.NOINGRESADO?"Ingresar":"";
    }
    public String opcion2(int n){
        return usuario.getTipoUsuario()==TipoUsuario.NOINGRESADO?"index":"";
    }
    public String opcion3(){
        return usuario.getTipoUsuario()==TipoUsuario.NOINGRESADO?"Bodega":"";
    }
     public String opcion3(int n){
        return usuario.getTipoUsuario()==TipoUsuario.NOINGRESADO?"PrincipalBodega":"";
    }
    
    public String opcion4(){
        return usuario.getTipoUsuario()==TipoUsuario.NOINGRESADO?"":"";
    }
    
    
}
