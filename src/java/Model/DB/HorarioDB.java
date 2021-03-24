/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.DB;

import Model.Horario;
import java.util.LinkedList;

/**
 *
 * @author Johan
 */
public class HorarioDB {
    private LinkedList<Horario> listaHorarios = new LinkedList<>();
    public LinkedList<Horario> obtenerHorarios (){
        listaHorarios.add(new Horario(1,"8:00"));
        listaHorarios.add(new Horario(2,"12:00"));
        listaHorarios.add(new Horario(3,"16:00"));
        listaHorarios.add(new Horario(4,"18:00"));
        
        return listaHorarios;
    }
}
