/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Enums;

/**
 *
 * @author César
 */
public enum TipoUsuario {
    ADMINISTRADOR("A"){
        public String toString (){
            return "Administrador";
        }
    },
    BODEGUERO("B"){
        public String toString (){
            return "Bodeguero";
        }
    },
    CLIENTE("C"){
        public String toString (){
            return "Cliente";
        }
    }, 
    NOINGRESADO("N"){
        public String toString (){
            return "No Ingresado";
        }
    };
    
    String atributo;
    private TipoUsuario (String atributo){
        this.atributo = atributo;
    }

    public String getAtributo() {
        return atributo;
    }
    
    
}
