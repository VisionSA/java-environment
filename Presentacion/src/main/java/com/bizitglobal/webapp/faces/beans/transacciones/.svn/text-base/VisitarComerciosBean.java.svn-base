package com.bizitglobal.webapp.faces.beans.transacciones;

import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.faces.model.SelectItem;

import com.bizitglobal.webapp.faces.util.Validador;
import com.bizitglobal.tarjetafiel.transacciones.negocio.TipoAsesorComercial;
import com.bizitglobal.webapp.faces.beans.BaseBean;
import com.bizitglobal.webapp.faces.util.Session;
import java.text.Format;
import java.text.SimpleDateFormat;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpServletRequest;


@SuppressWarnings({"rawtypes","unchecked"})
public class VisitarComerciosBean extends BaseBean {

	private static final Logger log = Logger.getLogger(VisitarComerciosBean.class);
	// Promotor que contiene las propiedades para el bean.
	private List lstPromotores;
	private Integer mes;
	// Propiedad para ejecutar el popup del reporte.
	private String popupReport = new String("");
	private Long promotorSeleccionado;
	private Long promotorUltimo;
	private boolean listaCargada;
	private String tipoLista;
	private Date fechaDesde;

	
	public VisitarComerciosBean() {

		super();
		mes = null;

	}


	public Integer getMes() {
		return mes;
	}


	public void setMes(Integer mes) {
		this.mes = mes;
	}


	public Date getFechaDesde() {
		return fechaDesde;
	}


	public void setFechaDesde(Date fechaDesde) {
		this.fechaDesde = fechaDesde;
	}


	public List getLstPromotores() {
		return lstPromotores;
	}


	public void setLstPromotores(List lstPromotores) {
		this.lstPromotores = lstPromotores;
	}


	public String getPopupReport() {
		return popupReport;
	}


	public void setPopupReport(String popupReport) {
		this.popupReport = popupReport;
	}


	public Long getPromotorSeleccionado() {
		return promotorSeleccionado;
	}


	public void setPromotorSeleccionado(Long promotorSeleccionado) {
		this.promotorSeleccionado = promotorSeleccionado;
	}


	public boolean isListaCargada() {
		return listaCargada;
	}


	public void setListaCargada(boolean listaCargada) {
		this.listaCargada = listaCargada;
	}


	public Long getPromotorUltimo() {
		return promotorUltimo;
	}


	public void setPromotorUltimo(Long promotorUltimo) {
		this.promotorUltimo = promotorUltimo;
	}


	public String getTipoLista() {
		return tipoLista;
	}


	public void setTipoLista(String tipoLista) {
		this.tipoLista = tipoLista;
	}


	public void borrar() {
		popupReport = new String("");
		this.lstPromotores = cargarListaPromotores();
		promotorSeleccionado = new Long(0);
		listaCargada = false;
		tipoLista = "D";
		Calendar fecha = Calendar.getInstance();
		this.fechaDesde = fecha.getTime();
		Format dateFormat = new SimpleDateFormat("MM");
		mes = new Integer(dateFormat.format(fechaDesde));

	}


	public void borrarCerrar() {
		popupReport = new String("");
		this.lstPromotores = cargarListaPromotores();
		promotorSeleccionado = new Long(0);
		listaCargada = false;
		tipoLista = "D";

	}


	/************************************************************************
	 * ACCIONES DEL BEAN DE VISITAR COMERCIO
	 ************************************************************************/

	private List cargarListaPromotores() {
		List result = new ArrayList();
		List promotores = null;

		/* @I5607 */
		// result.add(new SelectItem(new Long(0), "Todos "));
		// result.add(new SelectItem(new Long(1), "Mauricio Sosa"));
		// result.add(new SelectItem(new Long(2), "Mario Clavel"));

		promotores = transaccionesService.getTipoAsesorComercialService().listTipoAsesorComecial();

		Iterator iter = promotores.iterator();
		while (iter.hasNext()) {
			SelectItem item = new SelectItem();
			TipoAsesorComercial aux = (TipoAsesorComercial) iter.next();
			item.setValue(aux.getIdAsesorComercial());
			item.setLabel(aux.getAsesorComercial());
			result.add(item);

			/* @F5607 */}

		return result;
	}


	public String inicializar() {
		borrar();

		tituloLargo = "LISTAR";
		tituloCorto = "Listar Comercios a Visitar";

		return "listarVisitasComercios";
	}


	public String inicializarCerrar() {
		borrarCerrar();

		tituloLargo = "LISTAR";
		tituloCorto = "Listar Comercios a Visitar";

		return "listarVistasComercios";
	}


	public boolean validarMes() {
		error.borrar();

		// if(promotorSeleccionado != null && idBancoSeleccionado.equals(new Long(0)))
		// error.agregar("Debe seleccionar una cuenta");

		if (Validador.esNulo(mes) || mes == 0) {
			error.agregar("Debe ingresar valor numerico en el mes, entre 1 y 12");

		} else if (mes < 1 || mes > 12) {
			error.agregar("Debe ingresar valor numerico en el mes, entre 1 y 12");
		}
		return (error.cantidad() == 0) ? true : false;
	}


	public void listarVisitasComercios(ActionEvent event) {
		popupReport = new String("");
		if (validarMes()) {
			HttpServletRequest request = Session.getRequest();
			String p1 = "";
			String p2 = "";
			String p3 = "";
			String p4 = "";
			p1 = "?mes=" + mes;
			/* @I5607 */// p2 = "&URLImagen="+Session.getHomePath()+"/img/fiel/logo_fiel.jpg";
			// if (promotorSeleccionado == 0 ) {
			// p2 = "&asesor_desde="+new Long(1);
			// p3 = "&asesor_hasta="+new Long(2);
			//
			// } else {
			p2 = "&asesor_desde=" + promotorSeleccionado;
			p3 = "&asesor_hasta=" + promotorSeleccionado;

			// }
			/* @F5607 */

			p4 = "&URLImagen=" + Session.getHomePath() + "/img/fiel/logo_fiel.jpg";

			String page = request.getContextPath() + "/report/imp_comercio_visitar_cuit.jrxml";

			popupReport = "popup('" + page + p1 + p2 + p3 + p4 + "',1000,600)";
			log.info(popupReport);
		}
	}


	public boolean validar() {
		error.borrar();

		// if (Validador.esNulo(proveedor)) {
		// error.agregar(Error.PROVEEDOR_CODIGO_INVALIDO);
		// }
		//
		// if(Validador.esNulo(getFechaDesde()) || getFechaDesde().equals(new Date(0)) || Validador.esNulo(getFechaHasta()) ||
		// getFechaHasta().equals(new Date(0))){
		// error.agregar(Error.COMPROBANTE_FECHA_REQUERIDA);
		// }else {
		// if (getFechaDesde().after(getFechaHasta())){
		// error.agregar(Error.REPORTE_FECHA_MENOR);
		// }
		// }
		//
		// if(error.cantidad()!=0){
		// return false;
		// }else{
		// long idOperador = Session.getOperador().getCodigo().longValue();
		// long idProveedor = proveedor.getIdProveedor().longValue();
		// if (proveedoresService.getProveedorReporteDao().InicializarReporteCompSaldo(idOperador, idProveedor, fechaDesde, fechaHasta)){
		// return true;
		// }else{
		return false;
		// }
		// }
	}

}