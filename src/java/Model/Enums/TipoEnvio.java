/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Enums;

/**
 *
 * @author Fabian
 */
public enum TipoEnvio {
    NoIndica,
    EnvioDirecto,
    Presencial,
    Encomienda;
    private String cod;

    static {
        NoIndica.cod = "N";
        EnvioDirecto.cod = "D";
        Presencial.cod = "P";
        Encomienda.cod = "E";
    }

    public String getCodigo() {
        return cod;
    }
}
