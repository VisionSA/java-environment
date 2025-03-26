package com.bizitglobal.webapp.faces.beans.evaluacion;

import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.faces.model.SelectItem;

import com.bizitglobal.webapp.faces.util.Error;
import com.bizitglobal.webapp.faces.util.Validador;
import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.evaluacion.exception.PromotorException;
import com.bizitglobal.tarjetafiel.evaluacion.negocio.Promotor;
import com.bizitglobal.webapp.faces.beans.BaseBean;
import com.bizitglobal.webapp.faces.util.Session;
import java.text.Format;
import java.text.SimpleDateFormat;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpServletRequest;


@SuppressWarnings({"rawtypes","unchecked"})
public class PromotorProductividadBean extends BaseBean {

	private static final Logger log = Logger.getLogger(PromotorProductividadBean.class);
	// Promotor que contiene las propiedades para el bean.

	private List lstPromotores;

	/* @F5703 */private Integer mes;

	private Date fechaDesde;
	private Date fechaHasta;

	private Date fechaDesdeCierre;
	private Date fechaHastaCierre;

	// Propiedad para ejecutar el popup del reporte.
	private String popupReport = new String("");

	private Long promotorSeleccionado;

	private Long promotorUltimo;

	private boolean listaCargada;

	private String tipoLista;


	public PromotorProductividadBean() {

		super();
		fechaDesde = null;
		fechaHasta = null;
		fechaDesdeCierre = null;
		fechaHastaCierre = null;
		/* @F5703 */mes = null;

	}


	public Date getFechaDesde() {
		return fechaDesde;
	}


	public void setFechaDesde(Date fechaDesde) {
		this.fechaDesde = fechaDesde;
	}


	public Date getFechaHasta() {
		return fechaHasta;
	}


	public void setFechaHasta(Date fechaHasta) {
		this.fechaHasta = fechaHasta;
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


	public Date getFechaDesdeCierre() {
		return fechaDesdeCierre;
	}


	public void setFechaDesdeCierre(Date fechaDesdeCierre) {
		this.fechaDesdeCierre = fechaDesdeCierre;
	}


	public Date getFechaHastaCierre() {
		return fechaHastaCierre;
	}


	public void setFechaHastaCierre(Date fechaHastaCierre) {
		this.fechaHastaCierre = fechaHastaCierre;
	}


	public void borrar() {
		Calendar fecha = Calendar.getInstance();
		fechaHasta = fecha.getTime();
		fecha.add(Calendar.DAY_OF_MONTH, -1);
		// fecha.add(Calendar.MONTH, -12);
		fechaDesde = fecha.getTime();
		popupReport = new String("");
		this.lstPromotores = cargarListaPromotores();
		promotorSeleccionado = promotorUltimo;
		listaCargada = false;
		tipoLista = "D";
		/* @F5703 */mes = 2;

	}


	public void borrarCerrar() {
		Calendar fecha = Calendar.getInstance();
		fechaHasta = fecha.getTime();
		fecha.add(Calendar.DAY_OF_MONTH, -1);
		// fecha.add(Calendar.MONTH, -12);
		fechaDesde = fecha.getTime();
		popupReport = new String("");
		this.lstPromotores = cargarListaPromotores();
		promotorSeleccionado = promotorUltimo;
		listaCargada = false;
		tipoLista = "D";

	}


	/************************************************************************
	 * ACCIONES DEL BEAN DE PROMOTORES
	 ************************************************************************/

	private List cargarListaPromotores() {
		List result = new ArrayList();
		List promotores = null;

		try {
			Filtro filtro = new Filtro();
			filtro.agregarCampoOperValor("estado", Filtro.LIKEXS, "A");
			filtro.agregarOrderBy("idPromotor");
			promotores = evaluacionService.getPromotorService().getPromotor(filtro);
		} catch (PromotorException e) {
			e.printStackTrace();
		}

		if (!promotores.isEmpty()) {
			Iterator iter = promotores.iterator();
			while (iter.hasNext()) {
				SelectItem item = new SelectItem();
				Promotor aux = (Promotor) iter.next();
				item.setValue(aux.getIdPromotor());
				this.promotorUltimo = aux.getIdPromotor();
				item.setLabel(new String(aux.getApellido() + ", " + aux.getNombre()));
				result.add(item);

			}

		}

		SelectItem aux = new SelectItem();
		aux.setValue(++promotorUltimo);
		aux.setLabel("Todos los Promotores.");
		result.add(aux);
		return result;
	}


	public String inicializar() {
		borrar();

		tituloLargo = "LISTAR";
		tituloCorto = "Listar Productividad Promotores";

		return "listarPromotorProductividad";
	}


	public String inicializarCerrar() {
		borrarCerrar();

		tituloLargo = "LISTAR";
		tituloCorto = "Listar Cierre de Productividad Promotores";

		return "listarCierrePromotorProductividad";
	}


	/**
	 * @id: 5703 Method: validarMes Description: El mes de control de las activaciones debe ser de 1 a 12
	 * @return
	 */
	public boolean validarMes() {
		error.borrar();

		if (Validador.esNulo(mes)) {
			error.agregar("Debe ingresar valor numerico en meses de activaciones, entre 0 y 12");

		} else if (mes < 0 || mes > 12) {
			error.agregar("Debe ingresar valor numerico en meses de activaciones, entre 0 y 12");
		}
		return (error.cantidad() == 0) ? true : false;
	}


	public boolean validarFecha() {
		error.borrar();

		// if(promotorSeleccionado != null && idBancoSeleccionado.equals(new Long(0)))
		// error.agregar("Debe seleccionar una cuenta");

		if (Validador.esNulo(this.getFechaDesde()) || getFechaDesde().equals(new Date(0))
				|| Validador.esNulo(getFechaHasta()) || getFechaHasta().equals(new Date(0))) {
			error.agregar(Error.COMPROBANTE_FECHA_REQUERIDA);
		} else {
			if (getFechaDesde().after(getFechaHasta())) {
				error.agregar("La fecha desde no puede ser mayor a la fecha hasta");
			}
		}
		return (error.cantidad() == 0) ? true : false;
	}


	public void listarPromotoresProductividad(ActionEvent event) {
		popupReport = new String("");
		/* @I5703 */if (validarFecha() && validarMes()) {
			HttpServletRequest request = Session.getRequest();
			Format dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			String p1 = "";
			String p2 = "";
			String p3 = "";
			String p4 = "";
			String p5 = "";
			String p6 = "";
			String p7 = "";
			p1 = "?fecha_desde=" + dateFormat.format(fechaDesde);
			// p2 = "&URLImagen="+Session.getHomePath()+"/img/fiel/logo_fiel.jpg";
			p2 = "&fecha_hasta=" + dateFormat.format(fechaHasta);
			p7 = "&AExcel=excel";
			/* @F5703 */p5 = "&mes=" + mes;

			Calendar fecha = Calendar.getInstance();
			fecha.setTime(fechaDesde);
			fecha.add(Calendar.MONTH, -mes);
			p6 = "&fecha_desde_activ=" + dateFormat.format(fecha.getTime());

			String page = "";

			if (promotorSeleccionado.equals(promotorUltimo)) {

				p3 = "&promotor_desde=" + new Long(0);
				p4 = "&promotor_hasta=" + promotorUltimo;
			} else {
				p3 = "&promotor_desde=" + promotorSeleccionado;
				p4 = "&promotor_hasta=" + promotorSeleccionado;
			}

			if (tipoLista.equals("T")) {
				p3 = "&promotor_desde=" + new Long(0);
				p4 = "&promotor_hasta=" + promotorUltimo;
				page = request.getContextPath() + "/report/ProductividadPromotoresGeneral.jrxml";
			} else {
				page = request.getContextPath() + "/report/ProductividadPromotores.jrxml";
			}

			/* @F5703 */popupReport = "popup('" + page + p1 + p2 + p3 + p4 + p5 + p6 + p7 + "',1000,600)";
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


	/* @I5703 */
	public Integer getMes() {
		return mes;
	}


	public void setMes(Integer mes) {
		this.mes = mes;
	}
	/* @F5703 */

}