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
public class Barrio {
    private int codProvincia;
    private int codCanton;
    private int codDistrito;
    private int codBarrio;
    private String dscBarrio;
    private int activo;

    public Barrio(int codProvincia, int codCanton, int codDistrito, int codBarrio, String dscBarrio, int activo) {
        this.codProvincia = codProvincia;
        this.codCanton = codCanton;
        this.codDistrito = codDistrito;
        this.codBarrio = codBarrio;
        this.dscBarrio = dscBarrio;
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

    public int getCodDistrito() {
        return codDistrito;
    }

    public void setCodDistrito(int codDistrito) {
        this.codDistrito = codDistrito;
    }

    public int getCodBarrio() {
        return codBarrio;
    }

    public void setCodBarrio(int codBarrio) {
        this.codBarrio = codBarrio;
    }

    public String getDscBarrio() {
        return dscBarrio;
    }

    public void setDscBarrio(String dscBarrio) {
        this.dscBarrio = dscBarrio;
    }

    public int getActivo() {
        return activo;
    }

    public void setActivo(int activo) {
        this.activo = activo;
    }
    
    
}
