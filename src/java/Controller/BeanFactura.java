/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.SNMPExceptions;
import Model.Cliente;
import Model.DB.DireccionDB;
import Model.DB.FacturaDB;
import Model.DB.HistorialDB;
import Model.DB.HorarioDB;
import Model.DB.PedidoDB;
import Model.Direccion;
import Model.Enums.EstadoFactura;
import Model.Enums.TipoEnvio;
import Model.Enums.TipoVenta;
import Model.Factura;
import Model.Historial;
import Model.Horario;
import Model.Pedido;
import Model.Producto;
import Model.ProductosCarrito;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.model.SelectItem;

/**
 *
 * @author Fabian
 */
@Named(value = "beanFactura")
@SessionScoped
public class BeanFactura implements Serializable {

    /**
     * Creates a new instance of BeanFactura
     */
    private FacturaDB facturaDB = new FacturaDB();
    private PedidoDB pedidoDB = new PedidoDB();
    private HistorialDB historialDB = new HistorialDB();
    private LinkedList<Factura> listaPendientesDirecto = new LinkedList<>();
    private LinkedList<Factura> listaPendientesEncomienda = new LinkedList<>();
    private LinkedList<Factura> listaPendientesPresencial = new LinkedList<>();
    private BeanObtenerDatosSesion obtenerDatosSesion = new BeanObtenerDatosSesion();
    private DireccionDB objDireccionDB = new DireccionDB();
    private Factura factura = new Factura();
    private HorarioDB horarioDB = new HorarioDB();
    private Pedido objPedido = new Pedido();

    private String horaEntrega;
    private TipoEnvio tipoEnvioElegido = TipoEnvio.NoIndica;
    private TipoVenta tipoVentaElegido;
    private String error = "";
    private int direccionEntrega;
    private int cantidadSolicitada;
    double total = 0;

    String mensaje = "";

    public BeanFactura() {
    }

    public LinkedList<SelectItem> obtenerDirecciones() {
        LinkedList<SelectItem> listaDirecciones = new LinkedList<>();
        try {
            listaDirecciones = objDireccionDB.obtenerDirecciones(obtenerDatosSesion.getUsuarioLogin().getCorreoID());
        } catch (SNMPExceptions ex) {
            error = ex.getMensajeParaDesarrollador();
        }
        return listaDirecciones;
    }

    public LinkedList<Factura> mostrarFacturasPendientes() {
        LinkedList<Factura> facturasPendientes = new LinkedList<>();
        for (Factura factura1 : listaPendientesDirecto) {
            if (factura1.getEstado().equals(EstadoFactura.Pendiente)) {
                facturasPendientes.add(factura1);
            }
        }
        return facturasPendientes;
    }

    public TipoEnvio[] tiposEnvio() {
        return TipoEnvio.values();
    }

    public DireccionDB getObjDireccionDB() {
        return objDireccionDB;
    }

    public void setObjDireccionDB(DireccionDB objDireccionDB) {
        this.objDireccionDB = objDireccionDB;
    }

    public int getDireccionEntrega() {
        return direccionEntrega;
    }

    public void setDireccionEntrega(int direccionEntrega) {
        this.direccionEntrega = direccionEntrega;
    }

    public TipoVenta[] tipoVenta() {
        return TipoVenta.values();
    }

    public LinkedList<SelectItem> mostrarHorarios() {
        try {
            return horarioDB.obtenerHorarios();
        } catch (SNMPExceptions ex) {
            Logger.getLogger(BeanFactura.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void encomienda() {

        if (tipoEnvioElegido == TipoEnvio.Encomienda && total != 0) {
            if (total == objPedido.totalPagar()) {

                total += 2500;
            }
        } else {
            if (total > objPedido.totalPagar()) {

                total = total - 2500;
            }
        }

    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String crearPedido() {
        if (total == 0) {
            mensaje = "Debe de haber seleccionado almenos 1 producto";
            return "";
        } else if (horaEntrega.equals("NA")) {
            mensaje = "Debe de indicar el horario";
            return "";
        } else if (tipoEnvioElegido == TipoEnvio.NoIndica) {
            mensaje = "Debe de indicar la forma de envío";
            return "";
        } else if (tipoVentaElegido == TipoVenta.NoIndica) {
            mensaje = "Debe de indicar la forma de pago";
            return "";
        } else if (direccionEntrega == -1) {
            mensaje = "Debe de indicar la dirección de entrga";
            return "";
        }

        Factura factura = new Factura(objPedido, ((Cliente) obtenerDatosSesion.getUsuarioLogin()), "", EstadoFactura.Pendiente, LocalDate.now(), tipoEnvioElegido, tipoVentaElegido, 0, new Horario(horaEntrega, ""));
        Historial historial = new Historial(0, objPedido);
        try {

            pedidoDB.CrearPedido(factura, direccionEntrega);
            historialDB.insertarHistorialEntrega(historial, tipoEnvioElegido.getCodigo(), horaEntrega);

            for (int i = 0; i < objPedido.getListaProductos().size(); i++) {

                facturaDB.insertarDetFactura(objPedido.getListaProductos().get(i).getProducto().getId(), objPedido.getListaProductos().get(i).getCantidadSolicita(), objPedido.getListaProductos().get(i).getProducto().getPrecio());

            }

        } catch (SNMPExceptions ex) {
            mensaje = "Lo sentimos algo ha salido mal";
            System.out.println(ex.getMensajeParaDesarrollador());
        }

        mensaje = "Todo está correcto, gracias por su compra";
        limpiar();
        return "Principal.xhtml";

    }

    public void limpiar() {
        horaEntrega = "NA";
        tipoEnvioElegido = TipoEnvio.NoIndica;
        total = 0;
        tipoVentaElegido = TipoVenta.NoIndica;
        direccionEntrega = -1;
        mensaje = "";
        objPedido = new Pedido();
    }

    public void agregarProductoPedido(Producto producto) {
        objPedido.controlCantidad(producto, cantidadSolicitada);
        objPedido.agregarProducto(producto, cantidadSolicitada);

    }

    public double totalPagar() {
        return objPedido.totalPagar();
    }

    public void eliminarProductoCarrito(ProductosCarrito p) {
        objPedido.eliminarProducto(p);
    }

    public int getCantidadSolicitada() {
        return cantidadSolicitada;
    }

    public void setCantidadSolicitada(int cantidadSolicitada) {
        this.cantidadSolicitada = cantidadSolicitada;
    }

    public Pedido getObjPedido() {
        return objPedido;
    }

    public void setObjPedido(Pedido objPedido) {
        this.objPedido = objPedido;
    }

    public String completarPedido(Factura f) {
        try {
            this.facturaDB.FinalizarFactura(factura.getId());
        } catch (SNMPExceptions ex) {
            error += ex.getMensajeParaDesarrollador();
        }
        return "PrincipalBodega";
    }

    public String detalleFactura(Factura f) {
        this.factura = f;
        return "DetallePedido";
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public FacturaDB getFacturaDB() {
        return facturaDB;
    }

    public void setFacturaDB(FacturaDB facturaDB) {
        this.facturaDB = facturaDB;
    }

    public LinkedList<Factura> getListaPendientesDirecto() {
        try {
            listaPendientesDirecto = facturaDB.mostrarFacturasPendientesPorTipoEnvio(TipoEnvio.EnvioDirecto);
        } catch (SNMPExceptions ex) {
            error += ex.getMensajeParaDesarrollador();
        }
        return listaPendientesDirecto;
    }

    public void setListaPendientesDirecto(LinkedList<Factura> listaPendientesDirecto) {
        this.listaPendientesDirecto = listaPendientesDirecto;
    }

    public TipoEnvio getTipoEnvioElegido() {
        return tipoEnvioElegido;
    }

    public void setTipoEnvioElegido(TipoEnvio tipoEnvioElegido) {
        this.tipoEnvioElegido = tipoEnvioElegido;
    }

    public Factura getFactura() {
        return factura;
    }

    public void setFactura(Factura factura) {
        this.factura = factura;
    }

    public TipoVenta getTipoVentaElegido() {
        return tipoVentaElegido;
    }

    public void setTipoVentaElegido(TipoVenta tipoVentaElegido) {
        this.tipoVentaElegido = tipoVentaElegido;
    }

    public HorarioDB getHorarioDB() {
        return horarioDB;
    }

    public BeanObtenerDatosSesion getObtenerDatosSesion() {
        return obtenerDatosSesion;
    }

    public void setObtenerDatosSesion(BeanObtenerDatosSesion obtenerDatosSesion) {
        this.obtenerDatosSesion = obtenerDatosSesion;
    }

    public void setHorarioDB(HorarioDB horarioDB) {
        this.horarioDB = horarioDB;
    }

    public String getHoraEntrega() {
        return horaEntrega;
    }

    public void setHoraEntrega(String horaEntrega) {
        this.horaEntrega = horaEntrega;
    }

    public LinkedList<Factura> getListaPendientesEncomienda() {
        try {
            listaPendientesEncomienda = facturaDB.mostrarFacturasPendientesPorTipoEnvio(TipoEnvio.Encomienda);
        } catch (SNMPExceptions ex) {
            error += ex.getMensajeParaDesarrollador();
        }
        return listaPendientesEncomienda;
    }

    public void setListaPendientesEncomienda(LinkedList<Factura> listaPendientesEncomienda) {
        this.listaPendientesEncomienda = listaPendientesEncomienda;
    }

    public LinkedList<Factura> getListaPendientesPresencial() {
        try {
            listaPendientesPresencial = facturaDB.mostrarFacturasPendientesPorTipoEnvio(TipoEnvio.Presencial);
        } catch (SNMPExceptions ex) {
            error += ex.getMensajeParaDesarrollador();
        }
        return listaPendientesPresencial;
    }

    public void setListaPendientesPresencial(LinkedList<Factura> listaPendientesPresencial) {
        this.listaPendientesPresencial = listaPendientesPresencial;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

}
