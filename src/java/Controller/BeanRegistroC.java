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
import Model.DB.ClienteDB;
import Model.DB.DireccionDB;
import Model.DB.DistritoDB;
import Model.DB.ProvinciaDB;
import Model.DB.UsuarioDB;
import Model.Direccion;
import Model.Distrito;
import Model.Enums.TipoUsuario;
import Model.Provincia;
import Model.Usuario;
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
    
    private int provinciaUsuario = 0;
    private int cantonUsuario = 0;
    private int distritoUsuario = 0;
    private int barrioUsuario = 0;
    
    private String nombre = "";
    private String telefono = "";
    private String cedula = "";
    private String correo = "";
    private String password = "";
    private String otrasSenas = "";
    
    private ProvinciaDB provinciaDB = new ProvinciaDB();
    private CantonDB cantonDB = new CantonDB();
    private DistritoDB distritoDB = new DistritoDB();
    private BarrioDB barrioDB = new BarrioDB();
    private UsuarioDB usuarioDB = new UsuarioDB();
    private DireccionDB direccionDB = new DireccionDB();
    private ClienteDB clienteDB = new ClienteDB();
    
    private String error = "";
    private String mensaje = "";
    
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
    
    public void registrarse() {
        if (nombre.trim().equals("")) {
            mensaje = "Debe de ingresar su nombre";
            return;
        } else if (telefono.trim().equals("")) {
            mensaje = "Debe de ingresar un número telefónico";
            return;
        } else if (cedula.trim().equals("")) {
            mensaje = "Debe de ingresar su número de identificación";
            return;
        } else if (correo.trim().equals("")) {
            mensaje = "De de indicar un correo electronico para realizar el registro";
            return;
        } else if (!correo.matches("^[\\w-]+(\\.[\\w-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")) {
            mensaje = "El correo debe de llevar el siguiente formato correoEjemplo@....com";
            return;
        } else if (password.trim().equals("")) {
            mensaje = "Debe de ingresar una contraseña";
            return;
        } else if (provinciaUsuario == 0) {
            mensaje = "Debe de indicar la provincia donde vive";
            return;
        } else if (cantonUsuario == 0) {
            mensaje = "Debe de indicar el cantón donde vive";
            return;
        } else if (distritoUsuario == 0) {
            mensaje = "Dedebe de indicar el distrito donde vive";
            return;
        } else if (barrioUsuario == 0) {
            mensaje = "Debe de indicar el barrio donde vive";
            return;
        } else if (otrasSenas.trim().equals("")) {
            mensaje = "Debe de indicar señas especificas de la casa, por ejemplo: casa color amarillo";
            return;
        }
        
        mensaje = "";
        Usuario usuario = new Usuario(nombre, TipoUsuario.CLIENTE, cedula, telefono, correo, "ESPERA", password);
        Direccion direccion = new Direccion(0, otrasSenas, "P");
        
        try {
            if (clienteDB.obtenerClientePorID(usuario.getCorreoID()).getCorreoID() == null) {
                usuarioDB.insertarUsuario(usuario);
                direccionDB.insertarDireccion(usuario, barrioUsuario, distritoUsuario, cantonUsuario, provinciaUsuario, direccion);
                mensaje = "Registrado correctamente, debe de esperar a ser aprobado, se le notificará al correo";
            } else {
                mensaje = "El correo con el que se desea registrar ya existe";
            }
            
        } catch (Exception e) {
            mensaje = "Error al intentar realizar el registro intente nuevamente más tarde";
        }
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
    
    public String getMensaje() {
        return mensaje;
    }
    
    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
    
    public String getNombre() {
        return nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    
    public void setCedula(String cedula) {
        this.cedula = cedula;
    }
    
    public void setCorreo(String correo) {
        this.correo = correo;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public void setOtrasSenas(String otrasSenas) {
        this.otrasSenas = otrasSenas;
    }
    
    public void setUsuarioDB(UsuarioDB usuarioDB) {
        this.usuarioDB = usuarioDB;
    }
    
    public void setDireccionDB(DireccionDB direccionDB) {
        this.direccionDB = direccionDB;
    }
    
    public String getTelefono() {
        return telefono;
    }
    
    public String getCedula() {
        return cedula;
    }
    
    public String getCorreo() {
        return correo;
    }
    
    public String getPassword() {
        return password;
    }
    
    public String getOtrasSenas() {
        return otrasSenas;
    }
    
    public UsuarioDB getUsuarioDB() {
        return usuarioDB;
    }
    
    public DireccionDB getDireccionDB() {
        return direccionDB;
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
