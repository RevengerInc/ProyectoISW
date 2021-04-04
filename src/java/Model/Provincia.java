/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author Fabian
 */
public class Provincia {
    private int codProvincia;
    private String dscProvincia;
    private int activo;

    public Provincia(int codProvincia, String dscProvincia, int activo) {
        this.codProvincia = codProvincia;
        this.dscProvincia = dscProvincia;
        this.activo = activo;
    }

    public Provincia() {
    }

    
    public int getCodProvincia() {
        return codProvincia;
    }

    public void setCodProvincia(int codProvincia) {
        this.codProvincia = codProvincia;
    }

    public String getDscProvincia() {
        return dscProvincia;
    }

    public void setDscProvincia(String dscProvincia) {
        this.dscProvincia = dscProvincia;
    }


    public int getActivo() {
        return activo;
    }

    public void setActivo(int activo) {
        this.activo = activo;
    }
    
    public String toString (){
        return dscProvincia;
    }
}
