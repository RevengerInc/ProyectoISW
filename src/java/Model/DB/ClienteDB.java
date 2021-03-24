/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.DB;

import Model.Cliente;
import Model.Enums.TipoUsuario;

/**
 *
 * @author Fabian
 */
public class ClienteDB {
    
    public Cliente obtenerCliente(){
        return new Cliente("Fabian Espinoza", TipoUsuario.Cliente, "118110563","88888888","fabian16viquez@gmail.com");
    }
}
