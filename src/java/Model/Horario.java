/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

public class Horario {
    private int id;
    private String hora;

    public Horario(int id, String hora) {
        this.id = id;
        this.hora = hora;
    }
    
    public String toString (){
        return hora;
    }
}
