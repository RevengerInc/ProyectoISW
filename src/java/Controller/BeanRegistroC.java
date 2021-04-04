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

    private int provinciaUsuario;
    private int cantonUsuario;
    private int distritoUsuario;
    private int barrioUsuario;

    private ProvinciaDB provinciaDB = new ProvinciaDB();
    private CantonDB cantonDB = new CantonDB();
    private DistritoDB distritoDB = new DistritoDB();
    private BarrioDB barrioDB = new BarrioDB();

    private String error = "";

    public BeanRegistroC() {
    }

    public LinkedList<SelectItem> moTodoProvincias() {
        LinkedList<SelectItem> listaProvincias = new LinkedList<>();
        try {
            listaProvincias = provinciaDB.moTodo();
        } catch (SNMPExceptions ex) {
            error = ex.getMensajeParaDesarrollador();
        }

        return listaProvincias;
    }

    public LinkedList<SelectItem> moTodoCantones() {
        LinkedList<SelectItem> listaCantones = new LinkedList<>();
        try {


            if (provinciaUsuario != 0) {
                listaCantones = cantonDB.moTodo(provinciaUsuario);

            }

        } catch (SNMPExceptions ex) {
            error = ex.getMensajeParaDesarrollador();
        }

        return listaCantones;
    }

    public LinkedList<SelectItem> moTodoDistritos() {
        LinkedList<SelectItem> listaDistritos = new LinkedList<>();
        try {
            if (provinciaUsuario != 0 && cantonUsuario != 0) {
                listaDistritos = distritoDB.moTodo(provinciaUsuario, cantonUsuario);
            }

        } catch (SNMPExceptions ex) {
            error = ex.getMensajeParaDesarrollador();
        }

        return listaDistritos;
    }
    
    public LinkedList<SelectItem> moTodoBarrios() {
        LinkedList<SelectItem> listaBarrios = new LinkedList<>();
        try {
            if (provinciaUsuario != 0 && cantonUsuario != 0 && distritoUsuario != 0) {
                listaBarrios = barrioDB.moTodo(provinciaUsuario, cantonUsuario, distritoUsuario);
            }

        } catch (SNMPExceptions ex) {
            error = ex.getMensajeParaDesarrollador();
        }

        return listaBarrios;
    }
    
    public int getProvinciaUsuario() {
        return provinciaUsuario;
    }

    public void setProvinciaUsuario(int provinciaUsuario) {
        this.provinciaUsuario = provinciaUsuario;
    }

    public int getCantonUsuario() {
        return cantonUsuario;
    }

    public void setCantonUsuario(int cantonUsuario) {
        this.cantonUsuario = cantonUsuario;
    }

    public int getDistritoUsuario() {
        return distritoUsuario;
    }

    public void setDistritoUsuario(int distritoUsuario) {
        this.distritoUsuario = distritoUsuario;
    }

    public int getBarrioUsuario() {
        return barrioUsuario;
    }

    public void setBarrioUsuario(int barrioUsuario) {
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
