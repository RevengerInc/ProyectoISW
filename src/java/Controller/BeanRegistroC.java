/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.SNMPExceptions;
import Model.Barrio;
import Model.Canton;
import Model.DB.BarrioDB;
import Model.DB.CantonDB;
import Model.DB.DistritoDB;
import Model.DB.ProvinciaDB;
import Model.Distrito;
import Model.Provincia;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.LinkedList;
import javax.faces.model.SelectItem;
import javax.swing.JOptionPane;

/**
 *
 * @author Johan
 */
@Named(value = "beanRegistroC")
@SessionScoped
public class BeanRegistroC implements Serializable {

    private Provincia provinciaUsuario;
    private Canton cantonUsuario;
    private Distrito distritoUsuario;
    private Barrio barrioUsuario;

    private ProvinciaDB provinciaDB = new ProvinciaDB();
    private CantonDB cantonDB = new CantonDB();
    private DistritoDB distritoDB = new DistritoDB();
    private BarrioDB barrioDB = new BarrioDB();

    private String error = "";

    public BeanRegistroC() {
    }

    public LinkedList<Provincia> moTodoProvincias() {
        LinkedList<Provincia> listaProvincias = new LinkedList<>();
        try {
            listaProvincias = provinciaDB.moTodo();
        } catch (SNMPExceptions ex) {
            error = ex.getMensajeParaDesarrollador();
        }

        return listaProvincias;
    }

    public LinkedList<Canton> moTodoCantones() {
        LinkedList<Canton> listaCantones = new LinkedList<>();
        try {
            if (provinciaUsuario!=null) {
                listaCantones = cantonDB.moTodo(provinciaUsuario.getCodProvincia());
            }

        } catch (SNMPExceptions ex) {
            error = ex.getMensajeParaDesarrollador();
        }

        return listaCantones;
    }

    public Provincia getProvinciaUsuario() {
        return provinciaUsuario;
    }

    public void setProvinciaUsuario(Provincia provinciaUsuario) {
        this.provinciaUsuario = provinciaUsuario;
    }

    public Canton getCantonUsuario() {
        return cantonUsuario;
    }

    public void setCantonUsuario(Canton cantonUsuario) {
        this.cantonUsuario = cantonUsuario;
    }

    public Distrito getDistritoUsuario() {
        return distritoUsuario;
    }

    public void setDistritoUsuario(Distrito distritoUsuario) {
        this.distritoUsuario = distritoUsuario;
    }

    public Barrio getBarrioUsuario() {
        return barrioUsuario;
    }

    public void setBarrioUsuario(Barrio barrioUsuario) {
        this.barrioUsuario = barrioUsuario;
    }


    public ProvinciaDB getProvinciaDB() {
        return provinciaDB;
    }

    public void setProvinciaDB(ProvinciaDB provinciaDB) {
        this.provinciaDB = provinciaDB;
    }

    public CantonDB getCantonDB() {
        return cantonDB;
    }

    public void setCantonDB(CantonDB cantonDB) {
        this.cantonDB = cantonDB;
    }

    public DistritoDB getDistritoDB() {
        return distritoDB;
    }

    public void setDistritoDB(DistritoDB distritoDB) {
        this.distritoDB = distritoDB;
    }

    public BarrioDB getBarrioDB() {
        return barrioDB;
    }

    public void setBarrioDB(BarrioDB barrioDB) {
        this.barrioDB = barrioDB;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

}
