/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.SNMPExceptions;
import Model.Producto;
import Model.DB.ProductoDB;
import Model.Pedido;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.file.UploadedFile;
import org.primefaces.shaded.commons.io.FilenameUtils;

/**
 *
 * @author César
 */
@Named(value = "beanProducto")
@SessionScoped
public class BeanProducto implements Serializable {

    /**
     * Creates a new instance of BeanProducto1
     */
    private ProductoDB productoDB= new ProductoDB();
    LinkedList<Producto> listaP = new LinkedList<Producto>();
    LinkedList<Producto> listaPInactivos = new LinkedList<Producto>();
    private Producto producto= new Producto();
    private String error="";
    private String mensaje = "";
    private UploadedFile archivoImagen;
    
    
    private String id = "";
    private String nombre = "";
    private String descripcion = "";
    private String strImagen = "";
    private String strImagen2 = "";
    
    private double precio = 0;
    private int min = 0;
    private int cantidad = 0;
    
    
    
    
    public BeanProducto() {
        try {
            listaP=productoDB.moTodo();
            listaPInactivos= productoDB.moTodoInactivos();
        } catch (SNMPExceptions ex) {
            error=ex.getMensajeParaDesarrollador();
        }
    }

    public LinkedList<Producto> getListaP() {
        return listaP;
    }
    
    
    public void setListaP(LinkedList<Producto> listaP) {
        this.listaP = listaP;
    }
    
    public String detalle(Producto p){
        this.producto = p;
        return "DetalleProducto";
    }
    
    public void verProducto(Producto p){
        this.producto = p;
        id = producto.getId();
        nombre = producto.getNombre();
        descripcion = producto.getDescripcion();
        strImagen = producto.getImagen();
        precio = producto.getPrecio();
        min = producto.getMinimo();
        cantidad = producto.getCantidad();
        strImagen2 = strImagen.substring(strImagen.lastIndexOf("/")+1);
    }
    public void moTodoInactivos(){
        try {
            listaPInactivos = productoDB.moTodoInactivos();
        } catch (Exception e) {
           mensaje = "Error al mostrar los productos inactivos";
        }
    }
    public void buscarProductoPorID() throws SNMPExceptions{
        this.producto = productoDB.obtenerProductoPorID(id);
    }
    public void eliminarProducto(){
        try {
            productoDB.EliminarProductoPorID(id);
            listaP = productoDB.moTodo();
            listaPInactivos = productoDB.moTodoInactivos();
            
         
        } catch (Exception e) {
            mensaje = "Error al eliminar el producto, verifique que haya seleccionado uno";
        }   
    } 
    public void HabilitarProducto(Producto p){
        try {
            productoDB.HabilitarProductoPorID(p.getId());
            listaPInactivos = productoDB.moTodoInactivos();
            listaP = productoDB.moTodo();
        } catch (Exception e) {
            mensaje = "Error al activar el producto, verifique que haya seleccionado uno";
        }   
    } 
            
     public String disponibilidad(){
        return producto.getCantidad()==0?"Agotado":"Disponible";
    }
    
    public String color(){
        return producto.getCantidad()==0?"red":"green";
    }
    
    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public ProductoDB getProductoDB() {
        return productoDB;
    }

    public void setProductoDB(ProductoDB productoDB) {
        this.productoDB = productoDB;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
    
    public void nuevoProducto(){
        id = "";
        nombre = "";
        descripcion = "";
        strImagen = "";
        precio = 0;
        min = 0;
        cantidad = 0;
        producto = null;
    }

    public String getMensaje() {
        return mensaje;
    }

    public LinkedList<Producto> getListaPInactivos() {
        return listaPInactivos;
    }

    public void setListaPInactivos(LinkedList<Producto> listaPInactivos) {
        this.listaPInactivos = listaPInactivos;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public UploadedFile getArchivoImagen() {
        return archivoImagen;
    }

    public String getStrImagen() {
        return strImagen;
    }

    public void setStrImagen(String strImagen) {
        this.strImagen = strImagen;
    }
    
    public void setArchivoImagen(UploadedFile archivoImagen) {
        this.archivoImagen = archivoImagen;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
    
    public void upload() {
        if (archivoImagen != null) {
            FacesMessage message = new FacesMessage("Successful", archivoImagen.getFileName() + " is uploaded.");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    public void handleFileUpload(FileUploadEvent event) {
        FacesMessage message = new FacesMessage("Successful", event.getFile().getFileName() + " is uploaded.");
        FacesContext.getCurrentInstance().addMessage(null, message);
    } 
    
     public void subirImagen() {
        if (archivoImagen != null) {
            try {
                Producto productoTemp = new Producto(id, nombre, descripcion, strImagen, precio, cantidad, min);
                InputStream in = archivoImagen.getInputStream();
                String ruta = this.getClass().getProtectionDomain().getCodeSource().getLocation().toString();
                int ubicacionBuild = ruta.indexOf("build");
                String rutaImagenParteB = ruta.substring(6, ubicacionBuild) + "web/IMAGES/PRODUCTOS/";
                String a = rutaImagenParteB + archivoImagen.getFileName();
                String b = a.replace('/', '\\');
                OutputStream out = new FileOutputStream(new File(b));

                int read = 0;
                byte[] bytes = new byte[1024];

                while ((read = in.read(bytes)) != -1) {
                    out.write(bytes, 0, read);
                }
                
                in.close();
                out.flush();
                out.close();
                
               
                id = productoTemp.getId();
                strImagen = "IMAGES/PRODUCTOS/"+archivoImagen.getFileName();
                strImagen2 = archivoImagen.getFileName();
                
                
                FacesMessage message = new FacesMessage("Succesful", archivoImagen.getFileName() + " is uploaded.");
                FacesContext.getCurrentInstance().addMessage(null, message);

            } catch (IOException ex) {
                FacesMessage message = new FacesMessage(ex.toString());
                FacesContext.getCurrentInstance().addMessage(null, message);
            }
        }
    }
     
    public void insertarProducto(){
        mensaje = "";
        if(id.trim().equals("")){
            mensaje = "Debe Ingresar el código del producto";
            return;
        } else {
            if (nombre.trim().equals("")) {
                mensaje = "Debe Ingresar el nombre del producto";
                return;
            } else {
                if (descripcion.trim().equals("")) {
                    mensaje = "Debe Ingresar la descripción del producto";
                    return;
                } else {

                    if (cantidad == 0) {
                        mensaje = "Debe Ingresar la cantidad del producto";
                        return;
                    }
                    else {
                        if (min == 0) {
                            mensaje = "Debe Ingresar la cantidad minima del producto";
                            return;
                        } else {
                            if (precio == 0) {
                                mensaje = "Debe Ingresar el precio del producto";
                                return;
                            } else {

                                if (strImagen.trim().equals("")) {
                                    mensaje = "Debe subir la imagen del producto";
                                    return;
                                } else {

                                    if (!mensaje.isEmpty()) {
                                        FacesMessage message = new FacesMessage(mensaje);
                                        FacesContext.getCurrentInstance().addMessage(null, message);
                                    } else {
                                        mensaje = "";
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        
        try {

            if (productoDB.obtenerProductoPorID(id).getId() == null) {
                producto = new Producto(id, nombre, descripcion, strImagen2, precio, cantidad, min);
                productoDB.insertarProducto(producto);
                mensaje = "Producto registrado correctamente";
                listaP = productoDB.moTodo();
            } else {
                mensaje = "El producto que desea registrar ya existe";
            }
            FacesMessage message = new FacesMessage(mensaje);
            FacesContext.getCurrentInstance().addMessage(null, message);
        } catch (Exception e) {
            mensaje = "Error al intentar realizar el registro intente nuevamente más tarde";
        }
    }
    public void modificarProducto(){
       mensaje = "";
        if(id.trim().equals("")){
            mensaje = "Debe Ingresar el código del producto";
            return;
        } else {
            if (nombre.trim().equals("")) {
                mensaje = "Debe Ingresar el nombre del producto";
                return;
            } else {
                if (descripcion.trim().equals("")) {
                    mensaje = "Debe Ingresar la descripción del producto";
                    return;
                } else {

                    if (cantidad == 0) {
                        mensaje = "Debe Ingresar la cantidad del producto";
                        return;
                    }
                    else {
                        if (min == 0) {
                            mensaje = "Debe Ingresar la cantidad minima del producto";
                            return;
                        } else {
                            if (precio == 0) {
                                mensaje = "Debe Ingresar el precio del producto";
                                return;
                            } else {

                                if (strImagen.trim().equals("")) {
                                    mensaje = "Debe subir la imagen del producto";
                                    return;
                                } else {

                                    if (!mensaje.isEmpty()) {
                                        FacesMessage message = new FacesMessage(mensaje);
                                        FacesContext.getCurrentInstance().addMessage(null, message);
                                    } else {
                                        mensaje = "";
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        
        try {

            if (productoDB.obtenerProductoPorID(id).getId() != null) {
                producto = new Producto(id, nombre, descripcion, strImagen2, precio, cantidad, min);
                productoDB.modificarProductoPorID(producto);
                mensaje = "Producto modificado correctamente";
                listaP = productoDB.moTodo();
            } else {
                mensaje = "El producto que desea modificar no existe";
            }
            FacesMessage message = new FacesMessage(mensaje);
            FacesContext.getCurrentInstance().addMessage(null, message);
        } catch (Exception e) {
            mensaje = "No puedes modificar el id del producto seleccionado";
        } 
    }
}
