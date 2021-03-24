/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.DB;

import Model.Direccion;
import java.util.LinkedList;

/**
 *
 * @author Fabian
 */
public class DireccionDB {
    LinkedList<Direccion> listaDirecciones = new LinkedList<>();
    
    public LinkedList<Direccion> obtenerDirecciones (){
        listaDirecciones.add(new Direccion(1, "500 metros sur este del templo catolico de san rafale de poás"));
        listaDirecciones.add(new Direccion(1, "200 metros norte de la granja pipasa en calle liles"));
        
        return listaDirecciones;
    }
}
