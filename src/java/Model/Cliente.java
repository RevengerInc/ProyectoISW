/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Model.Enums.TipoUsuario;

/**
 *
 * @author Fabian
 */
public class Cliente extends Usuario{

    public Cliente(String nombre, TipoUsuario tipoUsuario, String cedula, String telefono, String correoID, String estado) {
        super(nombre, tipoUsuario, cedula, telefono, correoID, estado);
    }
    
    public Cliente() {
        super();
    }
    
 
}
