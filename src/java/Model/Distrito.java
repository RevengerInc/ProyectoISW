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
public class Distrito {
    
    private int codProvincia;
    private int codCanton;
    private int codDistrito;
    private String dscDistrito;
    private int activo;

    public Distrito(int codProvincia, int codCanton, int codDistrito, String dscDistrito, int activo) {
        this.codProvincia = codProvincia;
        this.codCanton = codCanton;
        this.codDistrito = codDistrito;
        this.dscDistrito = dscDistrito;
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

    public String getDscDistrito() {
        return dscDistrito;
    }

    public void setDscDistrito(String dscDistrito) {
        this.dscDistrito = dscDistrito;
    }

    public int getActivo() {
        return activo;
    }

    public void setActivo(int activo) {
        this.activo = activo;
    }
    
    
    
}
