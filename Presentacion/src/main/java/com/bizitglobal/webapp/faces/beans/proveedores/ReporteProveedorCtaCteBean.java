package com.bizitglobal.webapp.faces.beans.proveedores;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.sql.Timestamp;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.commons.util.PropertieFile;
import com.bizitglobal.tarjetafiel.general.exception.RankingProveedoresException;
import com.bizitglobal.tarjetafiel.proveedores.exception.CuitNoValidoException;
import com.bizitglobal.tarjetafiel.proveedores.exception.ProveedorException;
import com.bizitglobal.tarjetafiel.proveedores.exception.ProveedorSICOREException;
import com.bizitglobal.tarjetafiel.proveedores.negocio.CuitValido;
import com.bizitglobal.tarjetafiel.proveedores.negocio.Proveedor;
import com.bizitglobal.webapp.faces.beans.BaseBean;
import com.bizitglobal.webapp.faces.util.Error;
import com.bizitglobal.webapp.faces.util.Session;
import com.bizitglobal.webapp.faces.util.Validador;


@SuppressWarnings({"rawtypes"})
public class ReporteProveedorCtaCteBean extends BaseBean {
	private static final Logger log = Logger.getLogger(ReporteProveedorCtaCteBean.class);
	private String cuit;
	private String cuitBusqueda;
	private Date fechaDesde;
	private Date fechaHasta;
	private Long idDesde;
	private Long idHasta;
	private String selectRadioButton;
	private Calendar fecha;

	FileReader fr = null;
	FileWriter fw = null;
	BufferedReader br = null;

	// Propiedad para ejecutar el popup del reporte.
	private String popupReport = new String("");


	public ReporteProveedorCtaCteBean() {
		super();
		cuit = null;
		fechaDesde = null;
		fechaHasta = null;
		cuitBusqueda = null;
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


	public String getCuit() {
		return cuit;
	}


	public void setCuit(String cuit) {
		if (cuitBusqueda != null) {
			this.cuit = cuitBusqueda;
			cuitBusqueda = null;
		} else {
			this.cuit = cuit;
		}
	}


	public String getCuitBusqueda() {
		return cuitBusqueda;
	}


	public void setCuitBusqueda(String cuitBusqueda) {
		this.cuitBusqueda = cuitBusqueda;
	}


	public String getPopupReport() {
		return popupReport;
	}


	public void setPopupReport(String popupReport) {
		this.popupReport = popupReport;
	}


	public String getSelectRadioButton() {
		return selectRadioButton;
	}


	public void setSelectRadioButton(String selectRadioButton) {
		this.selectRadioButton = selectRadioButton;
	}


	public void borrar() {
		cuit = null;
		fechaHasta = new Timestamp(new java.util.Date().getTime());

		fecha = Calendar.getInstance();
		fecha.setTime(new Date(fechaHasta.getTime()));
		fecha.add(Calendar.YEAR, -1);
		fechaDesde = new Timestamp(fecha.getTime().getTime());

		cuitBusqueda = null;
		popupReport = new String("");
		idDesde = null;
		idHasta = null;
		tituloLargo = "";
		tituloCorto = "";
		error.borrar();

		selectRadioButton = "1";
	}


	public String inicializar() {
		borrar();

		tituloLargo = "PROVEEDORES";
		tituloCorto = "Reporte Cuenta Corriente";

		if (Session.getBean("BuscarProveedorBean") != null) {
			BuscarProveedorBean bean = (BuscarProveedorBean) Session.getBean("BuscarProveedorBean");
			bean.borrar();
		}

		return "reporteProveedoresCtaCte";
	}


	public boolean validar() {
		error.borrar();
		return false;
	}


	public boolean validarRangoProveedor() {
		error.borrar();

		if (Validador.esNulo(idDesde) || Validador.esNulo(idHasta)
				|| idDesde.equals(new Long(0)) || idHasta.equals(new Long(0))) {
			error.agregar("Debe ingresar un rango de codigos. El 0(cero) no es valido");
		} else {
			if (idDesde.longValue() > idHasta.longValue()) {
				error.agregar("Rango invalido.");
			}
		}
		return (error.cantidad() == 0) ? true : false;
	}


	public boolean validarFecha() {
		error.borrar();

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


	public boolean validarFechaHasta() {
		error.borrar();

		if (Validador.esNulo(getFechaHasta()) || getFechaHasta().equals(new Date(0))) {
			error.agregar(Error.COMPROBANTE_FECHA_HASTA_REQUERIDA);
		}
		return (error.cantidad() == 0) ? true : false;
	}


	public Long getIdDesde() {
		return idDesde;
	}


	public void setIdDesde(Long idDesde) {
		this.idDesde = idDesde;
	}


	public Long getIdHasta() {
		return idHasta;
	}


	public void setIdHasta(Long idHasta) {
		this.idHasta = idHasta;
	}


	public String ctePendientesInicializar() {
		borrar();
		tituloLargo = "PROVEEDORES";
		tituloCorto = "Reporte Comprobantes Pendientes";
		return "ctePendientesInicializar";
	}


	public String cteVencidosPorVencer() {
		borrar();
		tituloLargo = "PROVEEDORES";
		tituloCorto = "Reporte Cte Vencidos y por Vencer";
		return "cteVencidosPorVencer";
	}


	public String comprobantesIngresados() {
		borrar();
		tituloLargo = "PROVEEDORES";
		tituloCorto = "Reporte Comprobantes Ingresados";
		return "reporteComprobantesIngresados";
	}


	public String comprobantesPendientesIngresar() {
		borrar();
		tituloLargo = "PROVEEDORES";
		tituloCorto = "Reporte Comprobantes A Imputar";
		return "reporteComprobantesPendientesDeImputar";
	}


	public String retencionesPracticadas() {
		borrar();
		tituloLargo = "PROVEEDORES";
		tituloCorto = "Reporte Retenciones Practicadas";
		return "reporteRetencionesPracticadas";
	}


	public String nominasProveedores() {
		borrar();
		tituloLargo = "PROVEEDORES";
		tituloCorto = "Nomina de Porveedores";
		return "reporteProveedoresNominas";
	}


	public String rankingDeProveedores() {
		borrar();
		tituloLargo = "PROVEEDORES";
		tituloCorto = "Ranking de Porveedores";
		return "reporteRankingDeProveedores";
	}


	public String proveedoresSubdiarioIVA() {
		borrar();
		fecha.setTime(fechaHasta);
		fecha.add(Calendar.MONTH, -1);
		fechaDesde = new Timestamp(fecha.getTime().getTime());
		tituloLargo = "PROVEEDORES";
		tituloCorto = "Subdiario I.V.A. Compras - Detallado";
		return "reporteProveedoresSubdiarioIVA";
	}


	public String proveedoresSaldo() {
		borrar();
		tituloLargo = "PROVEEDORES";
		tituloCorto = "Saldo Proveedores";
		return "proveedorSaldo";
	}


	public String exportacionRetenciones() {
		borrar();
		tituloLargo = "PROVEEDORES";
		tituloCorto = "Exportación de Retenciones Practicadas";
		return "exportacionRetenciones";
	}


	public String proveedoresSICORE() {
		borrar();
		fecha.setTime(fechaHasta);
		fecha.add(Calendar.MONTH, -1);
		fechaDesde = new Timestamp(fecha.getTime().getTime());
		tituloLargo = "PROVEEDORES";
		tituloCorto = "Archivo SICORE Proveedores";
		return "proveedorSICORE";
	}


	public String generar(ActionEvent event) {
		HttpServletRequest request = Session.getRequest();
		error.borrar();
		popupReport = new String("");
		try {
			CuitValido cuitValido = new CuitValido(cuit);
		} catch (CuitNoValidoException e1) {
			error.agregar(Error.PROVEEDOR_CUIT_INVALIDO);
			e1.printStackTrace();
			return null;
		} catch (Exception e) {
			error.agregar(Error.PROVEEDOR_CUIT_INVALIDO);
			e.printStackTrace();
			return null;
		}
		Proveedor proveedor = null;
		try {
			List proveedores = proveedoresService.getProveedorService().getProveedores(new Filtro("cuit", Filtro.IGUAL, cuit));
			if (!proveedores.isEmpty()) {
				proveedor = (Proveedor) proveedores.get(0);
			} else {
				error.agregar("El número de CUIT no corresponde a ningun proveedor cargado.");
				return null;
			}
		} catch (ProveedorException e) {
			e.printStackTrace();
			error.agregar("Error al leer el proveedor.");
			return null;
		}

		if (proveedor != null || validarFecha()) {
			Format dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			String p1 = "?id_prov_desde=" + proveedor.getIdProveedor();
			String p2 = "&fecha_desde=" + dateFormat.format(fechaDesde);
			String p3 = "&fecha_hasta=" + dateFormat.format(fechaHasta);
			String p4 = "&id_prov_hasta=" + proveedor.getIdProveedor();
			String p5 = "&URLImagen=" + Session.getHomePath() + "/img/fiel/logo_fiel.jpg";

			String page = request.getContextPath() + "/report/ProveedoresCtaCte.jrxml";

			popupReport = "popup('" + page + p1 + p2 + p3 + p4 + p5 + "',1000,600)";

			log.info(popupReport);
		} else {
			error.agregar("Error en el rango de fechas.");
			return null;
		}
		return null;
	}


	public String generarAExcel(ActionEvent event) {
		HttpServletRequest request = Session.getRequest();
		error.borrar();
		popupReport = new String("");
		try {
			CuitValido cuitValido = new CuitValido(cuit);
		} catch (CuitNoValidoException e1) {
			error.agregar(Error.PROVEEDOR_CUIT_INVALIDO);
			e1.printStackTrace();
			return null;
		} catch (Exception e) {
			error.agregar(Error.PROVEEDOR_CUIT_INVALIDO);
			e.printStackTrace();
			return null;
		}
		Proveedor proveedor = null;
		try {
			List proveedores = proveedoresService.getProveedorService().getProveedores(new Filtro("cuit", Filtro.IGUAL, cuit));
			if (!proveedores.isEmpty()) {
				proveedor = (Proveedor) proveedores.get(0);
			} else {
				error.agregar("El número de CUIT no corresponde a ningun proveedor cargado.");
				return null;
			}
		} catch (ProveedorException e) {
			e.printStackTrace();
			error.agregar("Error al leer el proveedor.");
			return null;
		}

		if (proveedor != null || validarFecha()) {
			Format dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			String p1 = "?id_prov_desde=" + proveedor.getIdProveedor();
			String p2 = "&fecha_desde=" + dateFormat.format(fechaDesde);
			String p3 = "&fecha_hasta=" + dateFormat.format(fechaHasta);
			String p4 = "&id_prov_hasta=" + proveedor.getIdProveedor();
			String p5 = "&URLImagen=" + Session.getHomePath() + "/img/fiel/logo_fiel.jpg";
			String p6 = "&AExcel=excel";
			String page = request.getContextPath() + "/report/ProveedoresCtaCteExcel.jrxml";

			popupReport = "popup('" + page + p1 + p2 + p3 + p4 + p5 + p6 + "',1000,600)";
			log.info(popupReport);
		} else {
			error.agregar("Error en el rango de fechas.");
			return null;
		}
		return null;
	}


	public String buscarProveedorPopup() {
		log.info("Buscar proveedor!!!");
		popupReport = new String("");
		BuscarProveedorBean bean = (BuscarProveedorBean) Session.getBean("BuscarProveedorBean");
		bean.inicializar(BuscarProveedorBean.REPORTE_PROV_CTA_CTE);

		String path = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
		path += "/tarjetafiel/proveedores/popup/buscarProveedor.jsf";
		ejecutarJavaScript("popup('" + path + "',700,400), 'titlebar=no';");
		return null;
	}


	public void generarCtesPendientesImputar(ActionEvent event) {
		HttpServletRequest request = Session.getRequest();
		popupReport = new String("");
		if (validarRangoProveedor()) {
			String p1 = "?id_prov_desde=" + idDesde;
			String p2 = "&id_prov_hasta=" + idHasta;
			String p3 = "&URLImagen=" + Session.getHomePath() + "/img/fiel/logo_fiel.jpg";

			String page = request.getContextPath() + "/report/ProvCtesPendImputar.jrxml";

			popupReport = "popup('" + page + p1 + p2 + p3 + "',1000,600)";

			log.info(popupReport);
		}
	}


	public void generarCtesPendientesImputarAExcel(ActionEvent event) {
		HttpServletRequest request = Session.getRequest();
		popupReport = new String("");
		if (validarRangoProveedor()) {
			String p1 = "?id_prov_desde=" + idDesde;
			String p2 = "&id_prov_hasta=" + idHasta;
			String p3 = "&URLImagen=" + Session.getHomePath() + "/img/fiel/logo_fiel.jpg";
			String p6 = "&AExcel=excel";
			String page = request.getContextPath() + "/report/ProvCtesPendImputarExcel.jrxml";
			popupReport = "popup('" + page + p1 + p2 + p3 + p6 + "',1000,600)";

			log.info(popupReport);
		}
	}


	public void generarCtesVencidosPorVencer(ActionEvent event) {
		HttpServletRequest request = Session.getRequest();
		popupReport = new String("");
		if (validarRangoProveedor()) {
			String p1 = "?id_prov_desde=" + idDesde;
			String p2 = "&id_prov_hasta=" + idHasta;
			String p3 = "&URLImagen=" + Session.getHomePath() + "/img/fiel/logo_fiel.jpg";

			String page = request.getContextPath() + "/report/ProveedoresCtesVecidosPorVencer.jrxml";

			popupReport = "popup('" + page + p1 + p2 + p3 + "',1000,600)";

			log.info(popupReport);
		}
	}


	public void generarCtesVencidosPorVencerAExcel(ActionEvent event) {
		HttpServletRequest request = Session.getRequest();
		popupReport = new String("");
		if (validarRangoProveedor()) {
			String p1 = "?id_prov_desde=" + idDesde;
			String p2 = "&id_prov_hasta=" + idHasta;
			String p3 = "&URLImagen=" + Session.getHomePath() + "/img/fiel/logo_fiel.jpg";
			String p6 = "&AExcel=excel";
			String page = request.getContextPath() + "/report/ProveedoresCtesVecidosPorVencerExcel.jrxml";
			popupReport = "popup('" + page + p1 + p2 + p3 + p6 + "',1000,600)";

			log.info(popupReport);
		}
	}


	public void generarComprobantesIngresados(ActionEvent e) {
		HttpServletRequest request = Session.getRequest();
		popupReport = new String("");
		Format dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		if (validarFecha()) {
			if (validarRangoProveedor()) {
				String p1 = "?fecha_desde=" + dateFormat.format(fechaDesde);
				String p2 = "&fecha_hasta=" + dateFormat.format(fechaHasta);
				String p3 = "&id_prov_desde=" + idDesde;
				String p4 = "&id_prov_hasta=" + idHasta;
				String p5 = "&URLImagen=" + Session.getHomePath() + "/img/fiel/logo_fiel.jpg";

				String page = request.getContextPath() + "/report/ProvCompIngresados.jrxml";
				popupReport = "popup('" + page + p1 + p2 + p3 + p4 + p5 + "',1000,600)";
				log.info(popupReport);
			}
		}
	}


	public void generarComprobantesIngresadosAExcel(ActionEvent e) {
		HttpServletRequest request = Session.getRequest();
		popupReport = new String("");
		Format dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		if (validarFecha()) {
			if (validarRangoProveedor()) {
				String p1 = "?fecha_desde=" + dateFormat.format(fechaDesde);
				String p2 = "&fecha_hasta=" + dateFormat.format(fechaHasta);
				String p3 = "&id_prov_desde=" + idDesde;
				String p4 = "&id_prov_hasta=" + idHasta;
				String p5 = "&URLImagen=" + Session.getHomePath() + "/img/fiel/logo_fiel.jpg";
				String p6 = "&AExcel=excel";
				String page = request.getContextPath() + "/report/ProvCompIngresados.jrxml";
				popupReport = "popup('" + page + p1 + p2 + p3 + p4 + p5 + p6 + "',1000,600)";
				log.info(popupReport);
			}
		}
	}


	public String generarComprobantesPendientesDeImputar(ActionEvent event) {
		HttpServletRequest request = Session.getRequest();
		popupReport = new String("");
		if (validarRangoProveedor()) {
			String p1 = "?id_prov_desde=" + idDesde;
			String p2 = "&id_prov_hasta=" + idHasta;
			String p3 = "&URLImagen=" + Session.getHomePath() + "/img/fiel/logo_fiel.jpg";

			String page = request.getContextPath() + "/report/ProvCtesPendImputar.jrxml";
			popupReport = "popup('" + page + p1 + p2 + p3 + "',1000,600)";

			log.info(popupReport);
		}
		return null;
	}


	public String generarComprobantesPendientesDeImputarAExcel(ActionEvent event) {
		HttpServletRequest request = Session.getRequest();
		popupReport = new String("");
		if (validarRangoProveedor()) {
			String p1 = "?id_prov_desde=" + idDesde;
			String p2 = "&id_prov_hasta=" + idHasta;
			String p3 = "&URLImagen=" + Session.getHomePath() + "/img/fiel/logo_fiel.jpg";
			String p6 = "&AExcel=excel";
			String page = request.getContextPath() + "/report/ProvCtesPendImputar.jrxml";
			popupReport = "popup('" + page + p1 + p2 + p3 + p6 + "',1000,600)";

			log.info(popupReport);
		}
		return null;
	}


	public void generarRetencionesPracticadas(ActionEvent event) {
		HttpServletRequest request = Session.getRequest();
		popupReport = new String("");
		if (validarFecha()) {
			Format dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			String p1 = "?fecha_desde=" + dateFormat.format(fechaDesde);
			String p2 = "&fecha_hasta=" + dateFormat.format(fechaHasta);
			String p3 = "&URLImagen=" + Session.getHomePath() + "/img/fiel/logo_fiel.jpg";
			String page = request.getContextPath() + "/report/ProvRetPracticadas.jrxml";
			log.info("p1: " + p1 + ", p2: " + p2 + ", p3: " + p3 + ", page: " + page);
			popupReport = "popup('" + page + p1 + p2 + p3 + "',1000,600)";

			log.info(popupReport);
		}
	}


	public void generarRetencionesPracticadasAExcel(ActionEvent event) {
		HttpServletRequest request = Session.getRequest();
		popupReport = new String("");
		if (validarFecha()) {
			Format dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			String p1 = "?fecha_desde=" + dateFormat.format(fechaDesde);
			String p2 = "&fecha_hasta=" + dateFormat.format(fechaHasta);
			String p3 = "&URLImagen=" + Session.getHomePath() + "/img/fiel/logo_fiel.jpg";
			String p6 = "&AExcel=excel";
			String page = request.getContextPath() + "/report/ProvRetPracticadas.jrxml";
			log.info("p1: " + p1 + ", p2: " + p2 + ", p3: " + p3 + ", page: " + page);
			popupReport = "popup('" + page + p1 + p2 + p3 + p6 + "',1000,600)";

			log.info(popupReport);
		}
	}


	public void generarNominasProveedor(ActionEvent event) {
		HttpServletRequest request = Session.getRequest();
		popupReport = new String("");

		if (validarFecha()) {
			Format dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			String p1 = "?Fecha_desde=" + dateFormat.format(fechaDesde);
			String p2 = "&Fecha_hasta=" + dateFormat.format(fechaHasta);
			String p3 = "&URLImagen=" + Session.getHomePath() + "/img/fiel/logo_fiel.jpg";

			String page = request.getContextPath() + "/report/Proveedores_Nomina_Proveedores.jrxml";

			popupReport = "popup('" + page + p1 + p2 + p3 + "',1000,600)";

			log.info(popupReport);
		}
	}


	public void generarNominasProveedorAExcel(ActionEvent event) {
		HttpServletRequest request = Session.getRequest();
		popupReport = new String("");

		if (validarFecha()) {
			Format dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			String p1 = "?Fecha_desde=" + dateFormat.format(fechaDesde);
			String p2 = "&Fecha_hasta=" + dateFormat.format(fechaHasta);
			String p3 = "&URLImagen=" + Session.getHomePath() + "/img/fiel/logo_fiel.jpg";
			String p6 = "&AExcel=excel";
			String page = request.getContextPath() + "/report/Proveedores_Nomina_Proveedores.jrxml";

			popupReport = "popup('" + page + p1 + p2 + p3 + p6 + "',1000,600)";

			log.info(popupReport);
		}
	}


	public void generarRankingDeProveedor(ActionEvent event) {
		HttpServletRequest request = Session.getRequest();
		popupReport = new String("");
		
			Calendar cal = Calendar.getInstance();
			cal.setTime(fechaHasta);

			try {
				generalService.getRankingProveedoresService().generarRankingProveedores(
						Session.getOperador().getCodigo(), new Timestamp(fechaHasta.getTime()));
			} catch (RankingProveedoresException e) {
				error.agregar("Error al traer listado de ranking de proveedores.");
				e.printStackTrace();
			} catch (Exception e1) {
				error.agregar("Error al traer listado de ranking de proveedores.");
				e1.printStackTrace();
			}
			if (error.cantidad() == 0) {
				String p1 = "?URLImagen=" + Session.getHomePath() + "/img/fiel/logo_fiel.jpg";
				String p2 = "&op_desde=" + Session.getOperador().getCodigo();
				String p14 = "&per12=" + (cal.get(Calendar.MONTH) + 1) + "/" + cal.get(Calendar.YEAR);
				cal.add(Calendar.MONTH, -1);
				String p13 = "&per11=" + (cal.get(Calendar.MONTH) + 1) + "/" + cal.get(Calendar.YEAR);
				cal.add(Calendar.MONTH, -1);
				String p12 = "&per10=" + (cal.get(Calendar.MONTH) + 1) + "/" + cal.get(Calendar.YEAR);
				cal.add(Calendar.MONTH, -1);
				String p11 = "&per9=" + (cal.get(Calendar.MONTH) + 1) + "/" + cal.get(Calendar.YEAR);
				cal.add(Calendar.MONTH, -1);
				String p10 = "&per8=" + (cal.get(Calendar.MONTH) + 1) + "/" + cal.get(Calendar.YEAR);
				cal.add(Calendar.MONTH, -1);
				String p9 = "&per7=" + (cal.get(Calendar.MONTH) + 1) + "/" + cal.get(Calendar.YEAR);
				cal.add(Calendar.MONTH, -1);
				String p8 = "&per6=" + (cal.get(Calendar.MONTH) + 1) + "/" + cal.get(Calendar.YEAR);
				cal.add(Calendar.MONTH, -1);
				String p7 = "&per5=" + (cal.get(Calendar.MONTH) + 1) + "/" + cal.get(Calendar.YEAR);
				cal.add(Calendar.MONTH, -1);
				String p6 = "&per4=" + (cal.get(Calendar.MONTH) + 1) + "/" + cal.get(Calendar.YEAR);
				cal.add(Calendar.MONTH, -1);
				String p5 = "&per3=" + (cal.get(Calendar.MONTH) + 1) + "/" + cal.get(Calendar.YEAR);
				cal.add(Calendar.MONTH, -1);
				String p4 = "&per2=" + (cal.get(Calendar.MONTH) + 1) + "/" + cal.get(Calendar.YEAR);
				cal.add(Calendar.MONTH, -1);
				String p3 = "&per1=" + (cal.get(Calendar.MONTH) + 1) + "/" + cal.get(Calendar.YEAR);
				cal.add(Calendar.MONTH, -1);

				String page = request.getContextPath() + "/report/AnalisisDeProv.jrxml";
				popupReport = "popup('" + page + p1 + p2 + p3 + p4 + p5 + p6 + p7 + p8 + p9 + p10 + p11 + p12 + p13 + p14 + "',1000,600)";

				log.info(popupReport);
			}
		
	}


	public void generarRankingDeProveedorAExcel(ActionEvent event) {
		HttpServletRequest request = Session.getRequest();
		popupReport = new String("");
		
			Calendar cal = Calendar.getInstance();
			cal.setTime(fechaHasta);

			try {
				generalService.getRankingProveedoresService().generarRankingProveedores(
						Session.getOperador().getCodigo(), new Timestamp(fechaHasta.getTime()));
			} catch (RankingProveedoresException e) {
				error.agregar("Error al traer listado de ranking de proveedores.");
				e.printStackTrace();
			} catch (Exception e1) {
				error.agregar("Error al traer listado de ranking de proveedores.");
				e1.printStackTrace();
			}
			if (error.cantidad() == 0) {
				String p1 = "?URLImagen=" + Session.getHomePath() + "/img/fiel/logo_fiel.jpg";
				String p2 = "&op_desde=" + Session.getOperador().getCodigo();
				String p14 = "&per12=" + (cal.get(Calendar.MONTH) + 1) + "/" + cal.get(Calendar.YEAR);
				cal.add(Calendar.MONTH, -1);
				String p13 = "&per11=" + (cal.get(Calendar.MONTH) + 1) + "/" + cal.get(Calendar.YEAR);
				cal.add(Calendar.MONTH, -1);
				String p12 = "&per10=" + (cal.get(Calendar.MONTH) + 1) + "/" + cal.get(Calendar.YEAR);
				cal.add(Calendar.MONTH, -1);
				String p11 = "&per9=" + (cal.get(Calendar.MONTH) + 1) + "/" + cal.get(Calendar.YEAR);
				cal.add(Calendar.MONTH, -1);
				String p10 = "&per8=" + (cal.get(Calendar.MONTH) + 1) + "/" + cal.get(Calendar.YEAR);
				cal.add(Calendar.MONTH, -1);
				String p9 = "&per7=" + (cal.get(Calendar.MONTH) + 1) + "/" + cal.get(Calendar.YEAR);
				cal.add(Calendar.MONTH, -1);
				String p8 = "&per6=" + (cal.get(Calendar.MONTH) + 1) + "/" + cal.get(Calendar.YEAR);
				cal.add(Calendar.MONTH, -1);
				String p7 = "&per5=" + (cal.get(Calendar.MONTH) + 1) + "/" + cal.get(Calendar.YEAR);
				cal.add(Calendar.MONTH, -1);
				String p6 = "&per4=" + (cal.get(Calendar.MONTH) + 1) + "/" + cal.get(Calendar.YEAR);
				cal.add(Calendar.MONTH, -1);
				String p5 = "&per3=" + (cal.get(Calendar.MONTH) + 1) + "/" + cal.get(Calendar.YEAR);
				cal.add(Calendar.MONTH, -1);
				String p4 = "&per2=" + (cal.get(Calendar.MONTH) + 1) + "/" + cal.get(Calendar.YEAR);
				cal.add(Calendar.MONTH, -1);
				String p3 = "&per1=" + (cal.get(Calendar.MONTH) + 1) + "/" + cal.get(Calendar.YEAR);
				cal.add(Calendar.MONTH, -1);
				String p15 = "&AExcel=excel";
				String page = request.getContextPath() + "/report/AnalisisDeProv.jrxml";
				popupReport = "popup('" + page + p1 + p2 + p3 + p4 + p5 + p6 + p7 + p8 + p9 + p10 + p11 + p12 + p13 + p14 + p15 + "',1000,600)";

				log.info(popupReport);
			}
		
	}


	public void generarSubdiarioIVA(ActionEvent event) {
		HttpServletRequest request = Session.getRequest();
		popupReport = new String("");
		if (validarFecha()) {
			Calendar desde = Calendar.getInstance();
			Calendar hasta = Calendar.getInstance();
			desde.set(1900, 0, 1);
			hasta.set(2999, 11, 31);
			Format dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Date desdeEm = new Date(desde.getTime().getTime());
			Date hastaEm = new Date(hasta.getTime().getTime());
			Date desdeCo = new Date(desde.getTime().getTime());
			Date hastaCo = new Date(hasta.getTime().getTime());
			// if(selectRadioButton != null && !selectRadioButton.equals("") ){
			// if(selectRadioButton.equals("1")){
			// hastaCo = fechaHasta;
			// desdeCo = fechaDesde;
			// }
			// if(selectRadioButton.equals("2")){
			// hastaEm = fechaHasta;
			// desdeEm = fechaDesde;
			// }
			// }else{
			// error.agregar(Error.REPORTE_SELECT_RADIO_BUTTON_REQUERIDO);
			// }
			if (error.cantidad() == 0) {
				// try {
				// proveedoresService.getSubDiarioIvaService().proveedoresSubDiarioIva(Session.getOperador().getCodigo(),
				// new Timestamp(desdeEm.getTime()), new Timestamp(hastaEm.getTime()),
				// new Timestamp(desdeCo.getTime()), new Timestamp(hastaCo.getTime()));
				// String p1 = "?URLImagen="+Session.getHomePath()+"/img/fiel/logo_fiel.jpg";
				// String p2 = "&id_operador="+Session.getOperador().getCodigo();
				String p1 = "?fecha_desde=" + dateFormat.format(fechaDesde);
				String p2 = "&fecha_hasta=" + dateFormat.format(fechaHasta);
				// String page = request.getContextPath()+"/report/ProveedoresSubDiarioIVA.jrxml";
				String page = request.getContextPath() + "/report/Prov_libro_iva_nvo001.jrxml";
				popupReport = "popup('" + page + p1 + p2 + "',1000,600)";
				log.info(popupReport);
				// }catch (SubDiarioIvaException e){
				// error.agregar("Error al generar el Sub Diario IVA.");
				// e.printStackTrace();
				// }catch (Exception e1){
				// error.agregar("Error al generar el Sub Diario IVA.");
				// e1.printStackTrace();
				// }
			}
		}
	}


	public void generarSubdiarioIVAAExcel(ActionEvent event) {
		HttpServletRequest request = Session.getRequest();
		popupReport = new String("");
		if (validarFecha()) {
			Calendar desde = Calendar.getInstance();
			Calendar hasta = Calendar.getInstance();
			desde.set(1900, 0, 1);
			hasta.set(2999, 11, 31);
			Format dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			// Format dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			Date desdeEm = new Date(desde.getTime().getTime());
			Date hastaEm = new Date(hasta.getTime().getTime());
			Date desdeCo = new Date(desde.getTime().getTime());
			Date hastaCo = new Date(hasta.getTime().getTime());
			// if(selectRadioButton != null && !selectRadioButton.equals("") ){
			// if(selectRadioButton.equals("1")){
			// hastaCo = fechaHasta;
			// desdeCo = fechaDesde;
			// }
			// if(selectRadioButton.equals("2")){
			// hastaEm = fechaHasta;
			// desdeEm = fechaDesde;
			// }
			// }else{
			// error.agregar(Error.REPORTE_SELECT_RADIO_BUTTON_REQUERIDO);
			// }
			// if (error.cantidad() == 0) {
			// try {
			// proveedoresService.getSubDiarioIvaService().proveedoresSubDiarioIva(Session.getOperador().getCodigo(),
			// new Timestamp(desdeEm.getTime()), new Timestamp(hastaEm.getTime()),
			// new Timestamp(desdeCo.getTime()), new Timestamp(hastaCo.getTime()));
			// }catch (SubDiarioIvaException e){
			// error.agregar("Error al generar el Sub Diario IVA.");
			// e.printStackTrace();
			// }catch (Exception e1){
			// error.agregar("Error al generar el Sub Diario IVA.");
			// e1.printStackTrace();
			// }
			// String p1 = "?URLImagen="+Session.getHomePath()+"/img/fiel/logo_fiel.jpg";
			// String p2 = "&id_operador="+Session.getOperador().getCodigo();
			String p1 = "?fecha_desde=" + dateFormat.format(fechaDesde);
			String p2 = "&fecha_hasta=" + dateFormat.format(fechaHasta);
			String p5 = "&AExcel=excel";
			// String page = request.getContextPath()+"/report/ProveedoresSubDiarioIVA.jrxml";
			String page = request.getContextPath() + "/report/Prov_libro_iva_nvo001exel.jrxml";
			popupReport = "popup('" + page + p1 + p2 + p5 + "',1000,600)";
			// }
			log.info(popupReport);
		}
	}


	public void generarProveedorSaldoAExcel(ActionEvent event) {
		popupReport = new String("");
		Calendar hasta = Calendar.getInstance();
		hasta.set(2999, 11, 31);
		Date hasta_aux = new Date(hasta.getTimeInMillis());
		if (validarFechaHasta()) {
			HttpServletRequest request = Session.getRequest();
			Format dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			String p1 = "";
			String p2 = "";
			String p3 = "";
			String p4 = "";
			if (selectRadioButton != null && !selectRadioButton.equals("")) {
				if (selectRadioButton.equals("1")) {
					p1 = "?fecha_hasta_c=" + dateFormat.format(fechaHasta);
					p2 = "&URLImagen=" + Session.getHomePath() + "/img/fiel/logo_fiel.jpg";
					p3 = "&fecha_hasta_e=" + dateFormat.format(hasta_aux);
					p4 = "&fecha=" + dateFormat.format(fechaHasta);
				}
				if (selectRadioButton.equals("2")) {
					p1 = "?fecha_hasta_e=" + dateFormat.format(fechaHasta);
					p2 = "&URLImagen=" + Session.getHomePath() + "/img/fiel/logo_fiel.jpg";
					p3 = "&fecha_hasta_c=" + dateFormat.format(hasta_aux);
					p4 = "&fecha=" + dateFormat.format(fechaHasta);
				}

			} else {
				error.agregar(Error.REPORTE_SELECT_RADIO_BUTTON_REQUERIDO);
			}
			String p5 = "&AExcel=excel";
			String page = request.getContextPath() + "/report/ProveedoresSaldos.jrxml";

			// popupReport = "popup('"+page+p1+p2+p5+"',1000,600)";
			popupReport = "popup('" + page + p1 + p2 + p3 + p4 + p5 + "',1000,600)";
			log.info(popupReport);
		}
	}


	public void generarProveedorSaldo(ActionEvent event) {
		popupReport = new String("");
		Calendar hasta = Calendar.getInstance();
		hasta.set(2999, 11, 31);
		Date hasta_aux = new Date(hasta.getTimeInMillis());
		if (validarFechaHasta()) {
			HttpServletRequest request = Session.getRequest();
			Format dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			String p1 = "";
			String p2 = "";
			String p3 = "";
			String p4 = "";
			if (selectRadioButton != null && !selectRadioButton.equals("")) {
				if (selectRadioButton.equals("1")) {
					p1 = "?fecha_hasta_c=" + dateFormat.format(fechaHasta);
					p2 = "&URLImagen=" + Session.getHomePath() + "/img/fiel/logo_fiel.jpg";
					p3 = "&fecha_hasta_e=" + dateFormat.format(hasta_aux);
					p4 = "&fecha=" + dateFormat.format(fechaHasta);
				}
				if (selectRadioButton.equals("2")) {
					p1 = "?fecha_hasta_e=" + dateFormat.format(fechaHasta);
					p2 = "&URLImagen=" + Session.getHomePath() + "/img/fiel/logo_fiel.jpg";
					p3 = "&fecha_hasta_c=" + dateFormat.format(hasta_aux);
					p4 = "&fecha=" + dateFormat.format(fechaHasta);
				}

			} else {
				error.agregar(Error.REPORTE_SELECT_RADIO_BUTTON_REQUERIDO);
			}

			String page = request.getContextPath() + "/report/ProveedoresSaldos.jrxml";

			popupReport = "popup('" + page + p1 + p2 + p3 + p4 + "',1000,600)";
			log.info(popupReport);
		}
	}


	public void generarProveedorSICORE(ActionEvent event) {
		List sicoreList = new ArrayList();
		if (validarFecha()) {
			try {
				sicoreList = proveedoresService.getProveedorSICOREService().obtenerSICORE(
						new Timestamp(fechaDesde.getTime()), new Timestamp(fechaHasta.getTime()));
			} catch (ProveedorSICOREException e) {
				e.printStackTrace();
			}
			String key = "catalina.home";
			key = System.getProperty(key);
			PropertieFile prop = new PropertieFile(key + "/webapps/contexto.properties");
			String linea = null;
			try {
				File f = null;
				try {
					f = new File(key + "/" + prop.getProperties("directorioArchivos") + "/" + "SICORE.txt");
					f.delete();
				} catch (Exception e) {
					e.printStackTrace();
				}
				fw = new FileWriter(key + "/" + prop.getProperties("directorioArchivos") + "/" + "SICORE.txt");
				if (!sicoreList.isEmpty()) {
					Iterator iter = sicoreList.iterator();
					while (iter.hasNext()) {
						linea = iter.next().toString() + "\r\n";
						if (linea != null) {
							fw.write(linea);
						}
					}
				}
				fw.close();
				ejecutarJavaScript("popup('" + "/../archivos/" + "SICORE.txt" + "',700,400), 'titlebar=no';");
			} catch (Exception e) {
				e.getMessage();
			}
		}
	}
}
