package com.bizitglobal.webapp.faces.beans.fondos;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.bizitglobal.webapp.faces.beans.BaseBean;
import com.bizitglobal.webapp.faces.util.Error;
import com.bizitglobal.webapp.faces.util.Session;
import com.bizitglobal.webapp.faces.util.Util;
import com.bizitglobal.webapp.faces.util.Validador;
import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.fondos.exception.BancoPropioException;


public class AcreditacionesBean extends BaseBean {
	private static final Logger log = Logger.getLogger(AcreditacionesBean.class);
	private Date fechaDesde;
	private Date fechaHasta;
	private List bancoItems = new ArrayList();
	private List loteItems = new ArrayList();
	private boolean listaCargada;
	private boolean loteCargada;
	private Integer loteDesde;
	private Integer loteHasta;
	private Filtro filtro = new Filtro();
	private List bancoPropioList;
	private List loteInterbankList;
	private Long idBancoSeleccionado;
	private Long idLoteInterbanking;
	private String tipoLista;

	// Propiedad para ejecutar el popup del reporte.
	private String popupReport = new String("");


	public AcreditacionesBean() {
		super();
		fechaDesde = null;
		fechaHasta = null;
		loteDesde = null;
		loteHasta = null;
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


	public String getPopupReport() {
		return popupReport;
	}


	public void setPopupReport(String popupReport) {
		this.popupReport = popupReport;
	}


	public List getBancoItems() {
		return bancoItems;
	}


	public void setBancoItems(List bancoItems) {
		this.bancoItems = bancoItems;
	}


	public boolean getListaCargada() {
		return listaCargada;
	}


	public void setListaCargada(boolean listaCargada) {
		this.listaCargada = listaCargada;
	}


	public Long getIdBancoSeleccionado() {
		return idBancoSeleccionado;
	}


	public void setIdBancoSeleccionado(Long idBancoSeleccionado) {
		this.idBancoSeleccionado = idBancoSeleccionado;
	}


	public String getTipoLista() {
		return tipoLista;
	}


	public void setTipoLista(String tipoLista) {
		this.tipoLista = tipoLista;
	}


	public Long getIdLoteInterbanking() {
		return idLoteInterbanking;
	}


	public void setIdLoteInterbanking(Long idLoteInterbanking) {
		this.idLoteInterbanking = idLoteInterbanking;
	}


	public List getLoteItems() {
		return loteItems;
	}


	public void setLoteItems(List loteItems) {
		this.loteItems = loteItems;
	}


	public Integer getLoteDesde() {
		return loteDesde;
	}


	public void setLoteDesde(Integer loteDesde) {
		this.loteDesde = loteDesde;
	}


	public Integer getLoteHasta() {
		return loteHasta;
	}


	public void setLoteHasta(Integer loteHasta) {
		this.loteHasta = loteHasta;
	}


	public void borrar() {

		Calendar fecha = Calendar.getInstance();
		fechaHasta = fecha.getTime();
		fecha.add(Calendar.DAY_OF_MONTH, -1);
		// fecha.add(Calendar.MONTH, -12);
		fechaDesde = fecha.getTime();
		listaCargada = false;
		loteCargada = true;
		idBancoSeleccionado = new Long(0);
		tipoLista = "N";
		popupReport = new String("");
		loteDesde = null;
		loteHasta = null;
	}


	public void borrarComercio() {

		Calendar fecha = Calendar.getInstance();
		fechaHasta = fecha.getTime();
		// fecha.add(Calendar.DAY_OF_MONTH, -1);
		fecha.add(Calendar.MONTH, -6);
		fechaDesde = fecha.getTime();

		popupReport = new String("");
	}


	public String inicializar() {
		borrar();

		try {
			filtro.reset();
			bancoPropioList = fondosService.getBancoPropioService().getBancoPropios(filtro);
			bancoItems.clear();
			bancoItems.add(new SelectItem(new Long(0), "Seleccione banco"));
			bancoItems.addAll(Util.cargarSelectItem(bancoPropioList));

		} catch (BancoPropioException e) {
			e.printStackTrace();

		}

		tituloLargo = "LISTAR";
		tituloCorto = "Listar Acreditaciones";

		return "listarAcreditaciones";
	}


	public String inicializarLote() {
		borrar();

		try {
			filtro.reset();
			bancoPropioList = fondosService.getBancoPropioService().getBancoPropios(filtro);
			bancoItems.clear();
			bancoItems.add(new SelectItem(new Long(0), "Seleccione banco"));
			bancoItems.addAll(Util.cargarSelectItem(bancoPropioList));

		} catch (BancoPropioException e) {
			e.printStackTrace();

		}

		tituloLargo = "LISTAR";
		tituloCorto = "Listar Acreditaciones por Lotes";

		return "listarAcreditacionesLotes";
	}


	// public void cambiarMetodoDeBusqueda(ValueChangeEvent event) {
	// log.info("El método de busqueda venia siendo : " + event.getNewValue());
	// try{
	// String p4 = "banco_propio="+idBancoSeleccionado;
	// if (event.getNewValue().equals("S")) {
	// filtro.reset();
	// loteCargada = false;
	// loteItems.clear();
	// if (!(getFechaDesde().equals(getFechaHasta()))) {
	// filtro.agregarCampoOperValor("fechaGenerado", Filtro.MAYOR_IGUAL, Filtro.getTO_DATE(fechaDesde));
	// filtro.agregarCampoOperValor("fechaGenerado", Filtro.MENOR, Filtro.getTO_DATE(Fecha.addDias(fechaHasta,1)));
	// filtro.agregarOrderBy("idLoteInterbank DESC");
	// } else {
	// filtro.agregarCampoOperValor("fechaGenerado", Filtro.IGUAL, Filtro.getTO_DATE(fechaDesde));
	// filtro.agregarCampoOperValor("fechaGenerado", Filtro.MENOR, Filtro.getTO_DATE(Fecha.addDias(fechaDesde,1)));
	//
	// }
	//
	//
	// loteInterbankList = fondosService.getLoteInterbankService().getLoteInterbanks(filtro);
	// loteItems.addAll(Util.cargarSelectItem(loteInterbankList));
	// } else{
	// loteInterbankList.clear();
	// loteCargada = true;
	// }
	// } catch (LoteInterbankException e) {
	// error.agregar("Error al leer lista de loteInterbankList");
	// e.printStackTrace();
	// }
	//
	// }

	public String inicializarComercio() {
		// borrar();
		borrarComercio();

		tituloLargo = "INFORMES";
		tituloCorto = "Resumen Liquidaciónes de Comercios";

		return "resumenLiquidacionesComercios";
	}


	public String inicializarConceptos() {
		borrar();

		tituloLargo = "INFORMES";
		tituloCorto = "General Conceptos";

		return "generalConceptos";
	}


	public String inicializarPagosComercios() {
		borrar();

		tituloLargo = "INFORMES";
		tituloCorto = "Listar Pagos Comercios";

		return "listarPagosComercios";
	}


	public String inicializarConsumoComercio() {
		// borrar();
		borrarComercio();

		tituloLargo = "INFORMES";
		tituloCorto = "Revisar Consumos Comercios";

		return "revisarConsumosComercios";
	}


	public boolean validarFecha() {
		error.borrar();

		if (idBancoSeleccionado != null && idBancoSeleccionado.equals(new Long(0)))
			error.agregar("Debe seleccionar una cuenta");

		if (Validador.esNulo(getFechaDesde()) || getFechaDesde().equals(new Date(0))
				|| Validador.esNulo(getFechaHasta()) || getFechaHasta().equals(new Date(0))) {
			error.agregar(Error.COMPROBANTE_FECHA_REQUERIDA);
		} else {
			if (getFechaDesde().after(getFechaHasta())) {
				error.agregar("La fecha desde no puede ser mayor a la fecha hasta");
			}
		}
		return (error.cantidad() == 0) ? true : false;
	}


	public boolean validarLote() {
		error.borrar();

		if (idBancoSeleccionado != null && idBancoSeleccionado.equals(new Long(0)))
			error.agregar("Debe seleccionar una cuenta");

		if (Validador.esNulo(loteDesde) || loteDesde == 0
				|| Validador.esNulo(loteHasta) || loteHasta == 0) {
			error.agregar("Debe ingresar valor numerico en el lote desde y lote hasta, distintos de ceros");
		} else {
			if (loteDesde > loteHasta) {
				error.agregar("El lote desde no puede ser mayor que el lote hasta");
			}
		}
		return (error.cantidad() == 0) ? true : false;
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


	public void listarAcreditacionesLotes(ActionEvent event) {
		popupReport = new String("");
		if (validarLote()) {
			HttpServletRequest request = Session.getRequest();
			String p1 = "";
			String p2 = "";
			String p3 = "";
			String p4 = "";
			p1 = "?lote_desde=" + loteDesde;
			// p2 = "&URLImagen="+Session.getHomePath()+"/img/fiel/logo_fiel.jpg";
			p2 = "&lote_hasta=" + loteHasta;
			// p3 = "&procesado="+tipoLista;
			p3 = "&banco_propio=" + idBancoSeleccionado;

			String page = request.getContextPath() + "/report/ListarAcreditacionesXLote.jrxml";

			popupReport = "popup('" + page + p1 + p2 + p3 + "',1000,600)";
			log.info(popupReport);
		}

	}


	public void listarAcreditaciones(ActionEvent event) {
		popupReport = new String("");
		if (validarFecha()) {
			HttpServletRequest request = Session.getRequest();
			Format dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			String p1 = "";
			String p2 = "";
			String p3 = "";
			String p4 = "";
			p1 = "?fecha_desde=" + dateFormat.format(fechaDesde);
			// p2 = "&URLImagen="+Session.getHomePath()+"/img/fiel/logo_fiel.jpg";
			p2 = "&fecha_hasta=" + dateFormat.format(fechaHasta);
			p3 = "&procesado=" + tipoLista;
			p4 = "&banco_propio=" + idBancoSeleccionado;

			String page = request.getContextPath() + "/report/ListarAcreditaciones.jrxml";

			popupReport = "popup('" + page + p1 + p2 + p3 + p4 + "',1000,600)";
			log.info(popupReport);
		}

	}


	public void generarResumenLiquidacionesComercios(ActionEvent event) {
		popupReport = new String("");
		if (validarFecha()) {
			HttpServletRequest request = Session.getRequest();

			// reporte de cartas prejudiciales
			// Format dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			// String p1 ="";
			// String p2 ="";
			// String p3 ="";
			// String p4 ="";
			// String p5 ="";
			// p1 = "?cliente="+"LEIVA, GUSTAVO FABIAN";
			// p2 = "&domicilio="+"PAULA ALBARRACINY FORMOSA s/n - Mz. D Mb. 12 Dpto. C BÂ?. LUZ Y FUERZA III";
			// p3 = "&localidad="+"Loc: - Partido: CHIMBAS Prov: San Juan C.P: 5413";
			// p4 = "&numero_cuenta="+new Long("33763");
			// p5 = "&fecha_hoy="+"27/12/2010";

			// String page = request.getContextPath()+"/report/Aviso_Carta_Prejudicial.jrxml";

			// fin de reporte de cartas prejudiciales

			// reporte consumos de comercios entre fechas llamando a un SP
			Format dateFormat = new SimpleDateFormat("yyyyMM");
			String pFechaDesde = dateFormat.format(fechaDesde);
			String pFechaHasta = dateFormat.format(fechaHasta);
			Long pIdOperador = Session.getOperador().getCodigo();

			transaccionesService.getGeneracionClientesOcaService().
					generarConsumosComerciosEntrefechas(pIdOperador, pFechaDesde, pFechaHasta);

			String p1 = "?id_operador=" + Session.getOperador().getCodigo();
			// String p4 = "&id_operador="+Session.getOperador().getCodigo();
			// String p5 = "&URLImagen="+Session.getHomePath()+"/img/fiel/logo_fiel.jpg";
			String p2 = "&AExcel=excel";

			String page = request.getContextPath() + "/report/ComercioListaPrecioYConsumoSp.jrxml";
			popupReport = "popup('" + page + p1 + p2 + "',1000,600)";

			// reporte de columnas dinamicas
			// Format dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			// String p1 = "?fechadesde="+dateFormat.format(fechaDesde);
			// String p2 = "&fechahasta="+dateFormat.format(fechaHasta);
			// // String p4 = "&id_operador="+Session.getOperador().getCodigo();
			// // String p5 = "&URLImagen="+Session.getHomePath()+"/img/fiel/logo_fiel.jpg";
			// String p3 = "&AExcel=excel";

			// String page = request.getContextPath()+"/report/Aviso_Carta_Prejudicial.jrxml";

			// popupReport = "popup('"+page+p1+p2+p3+p4+p5+"',1000,600)";
			log.info(popupReport);
		}

	}


	public void revisarConsumosComercios(ActionEvent event) {
		popupReport = new String("");
		if (validarFecha()) {
			HttpServletRequest request = Session.getRequest();

			// reporte consumos de comercios entre fechas llamando a un SP
			Format dateFormat = new SimpleDateFormat("yyyyMM");
			String pFechaDesde = dateFormat.format(fechaDesde);
			String pFechaHasta = dateFormat.format(fechaHasta);
			Long pIdOperador = Session.getOperador().getCodigo();

			transaccionesService.getGeneracionClientesOcaService().
					generarConsumosComerciosEntrefechas(pIdOperador, pFechaDesde, pFechaHasta);

			String p1 = "?id_operador=" + Session.getOperador().getCodigo();
			// String p4 = "&id_operador="+Session.getOperador().getCodigo();
			// String p5 = "&URLImagen="+Session.getHomePath()+"/img/fiel/logo_fiel.jpg";
			String p2 = "&AExcel=excel";

			String page = request.getContextPath() + "/report/ComercioListaPrecioYConsumoSp.jrxml";
			popupReport = "popup('" + page + p1 + p2 + "',1000,600)";

			log.info(popupReport);
		}

	}


	public void generarConceptos(ActionEvent event) {
		popupReport = new String("");
		if (validarFecha()) {
			HttpServletRequest request = Session.getRequest();
			Format dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			String p1 = "";
			String p2 = "";
			String p3 = "";
			p1 = "?fecha_desde=" + dateFormat.format(fechaDesde);
			p2 = "&URLImagen=" + Session.getHomePath() + "/img/fiel/logo_fiel.jpg";
			p3 = "&fecha_hasta=" + dateFormat.format(fechaHasta);

			String page = request.getContextPath() + "/report/GeneralConceptos.jrxml";

			popupReport = "popup('" + page + p1 + p2 + p3 + "',1000,600)";
			log.info(popupReport);
		}

	}


	public void listarPagosComercios(ActionEvent event) {
		popupReport = new String("");
		if (validarFecha()) {
			HttpServletRequest request = Session.getRequest();
			Format dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			String p1 = "";
			String p2 = "";
			// String p3 ="";
			p1 = "?fecha_desde=" + dateFormat.format(fechaDesde);
			// p2 = "&URLImagen="+Session.getHomePath()+"/img/fiel/logo_fiel.jpg";
			p2 = "&fecha_hasta=" + dateFormat.format(fechaHasta);

			String page = request.getContextPath() + "/report/ValoresLiquidacionComercios.jrxml";

			popupReport = "popup('" + page + p1 + p2 + "',1000,600)";
			log.info(popupReport);
		}

	}


	public String generarAExcel(ActionEvent event) {
		// HttpServletRequest request = Session.getRequest();
		// proveedor = null;
		// error.borrar();
		// popupReport = new String("");
		//
		// try {
		// proveedor = proveedoresService.getProveedorService().leerProveedor(new Long(idProveedor));
		// } catch (ProveedorException e) {
		// e.printStackTrace();
		// }
		//
		// if(validar()) {
		// Format dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		// String p1 = "?id_proveedor="+idProveedor;
		// String p2 = "&fecha_desde="+dateFormat.format(fechaDesde);
		// String p3 = "&fecha_hasta="+dateFormat.format(fechaHasta);
		// String p4 = "&id_operador="+Session.getOperador().getCodigo();
		// String p5 = "&URLImagen="+Session.getHomePath()+"/img/fiel/logo_fiel.jpg";
		// String p6 = "&AExcel=excel";
		// String page = request.getContextPath()+"/report/ProveedoresCompSaldoExcel.jrxml";
		//
		// popupReport = "popup('"+page+p1+p2+p3+p4+p5+p6+"',1000,600)";
		//
		// log.info(popupReport);
		// }
		return null;
	}

}
