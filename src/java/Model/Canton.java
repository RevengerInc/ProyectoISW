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
public class Canton {
    private int codProvincia;
    private int codCanton;
    private String dscCanton;
    private int activo;

    public Canton(int codProvincia, int codCanton, String dscCanton, int activo) {
        this.codProvincia = codProvincia;
        this.codCanton = codCanton;
        this.dscCanton = dscCanton;
        this.activo = activo;
    }

    public int getCodProvincia() {
        return codProvincia;
    }

    public void setCodProvincia(int codProvincia) {
        this.codProvincia = codProvincia;
    }

    public int getCodCanton() {
        return codCanton;
    }

    public void setCodCanton(int codCanton) {
        this.codCanton = codCanton;
    }

    public String getDscCanton() {
        return dscCanton;
    }

    public void setDscCanton(String dscCanton) {
        this.dscCanton = dscCanton;
    }

    public int getActivo() {
        return activo;
    }

    public void setActivo(int activo) {
        this.activo = activo;
    }
    
    public String toString(){
        return dscCanton;
    }
    
     
}
