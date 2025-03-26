package com.bizitglobal.webapp.faces.beans.contabilidad;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.faces.component.html.HtmlSelectOneMenu;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.contabilidad.exception.EjercicioException;
import com.bizitglobal.tarjetafiel.contabilidad.negocio.Ejercicio;
import com.bizitglobal.tarjetafiel.general.exception.SucursalFielException;
import com.bizitglobal.tarjetafiel.general.negocio.SucursalFiel;
import com.bizitglobal.webapp.faces.beans.BaseBean;
import com.bizitglobal.webapp.faces.util.Error;
import com.bizitglobal.webapp.faces.util.Session;


@SuppressWarnings({"rawtypes","unchecked","unused"})
public class LibroMayorMensualizadoBean extends BaseBean {
	private static final Logger log = Logger.getLogger(LibroMayorMensualizadoBean.class);

	/**
	 * Este es un campo oculto para guardar los id de los elementos modificados.
	 */
	private List sucursalesFielAuxiliar; // todas las sucursales
	private List sucursalesFielSelectItem; // todas las sucursales como select
	private List ejercicios;
	private List ejerciciosSelectItem; // todos los ejercicios como select item

	// objetos para trabajar con el select item de sucursales
	private Long idSucSeleccionadaParaFiel;
	private HtmlSelectOneMenu idSucursalDeFielSeleccionada;
	// objetos para trabajar con el select item de ejercicios
	private Long idEjercicioSeleccionado;
	private HtmlSelectOneMenu idEjercicioSeleccionadoItem;

	Ejercicio ejerActual;
	private boolean modificarFechasEjerciciosFin;
	private boolean modificarFechasEjerciciosIni;

	private String nombreCuenta;
	private String cuentaABuscarEnLibro;
	private Date fechaInicio;
	private Date fechaCierre;
	private Date fechaInicioBusqueda;
	private Date fechaCierreBusqueda;

	// Objetos necesarios para el filtro
	private Long sucursal;
	private Long ejercicio;
	private Long nroDeCuentaBuscadaEnLibro;
	// Propiedad para ejecutar el popup del reporte.
	private String popupReport = new String("");
	private String listaCuentas = "";
	private String listaCuentasAux = "";

	private String cuentaParaMensualizadoReporte; // para el bean de plan de cuentas, que pueda setear las cuentas buscadas....


	public LibroMayorMensualizadoBean() {
		sucursalesFielSelectItem = new ArrayList();
		try {
			sucursalesFielAuxiliar = generalService.getSucursalFielService().getSucursales();
			sucursalesFielSelectItem.add(new SelectItem(new Long(0), "Todas"));
			Iterator iterDeSucursales = sucursalesFielAuxiliar.iterator();
			while (iterDeSucursales.hasNext()) {
				SucursalFiel su = (SucursalFiel) iterDeSucursales.next();
				sucursalesFielSelectItem.add(new SelectItem(su.getIdSucursal(), su.getNombre()));
			}
		} catch (SucursalFielException e1) {
			log.info("No existen en la base de datos sucursales de Fiel");
			e1.printStackTrace();
		}
		try {
		} catch (Exception e) {
			e.printStackTrace();
		}
		ejercicios = new ArrayList();
		ejerciciosSelectItem = new ArrayList();
		Filtro filtro = new Filtro();
		try {
			ejercicios = contabilidadService.getEjercicioService().getEjercicio(filtro);
			Iterator iterDeEjerciciosAuxiliares = ejercicios.iterator();
			while (iterDeEjerciciosAuxiliares.hasNext()) {
				Ejercicio ejer = (Ejercicio) iterDeEjerciciosAuxiliares.next();
				ejer.getIdEjercicio();
				ejer.getFechaCierre();
				ejer.getFechaInicio();
				ejer.getFechaPeriodo();
				ejer.getEstado();
				ejer.getSucursalFiel();
			}
		} catch (EjercicioException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	public String buscarLibroMayor() {
		error.borrar();
		log.info("Ejecutamos el metodo para mostrar libro mayor");

		return null;

	}


	public void borrar() {
		error.borrar();
		popupReport = new String("");
		modificarFechasEjerciciosFin = false;
		modificarFechasEjerciciosIni = false;
		tituloLargo = "Tarjeta Fiel - Libro Mayor Mensualizado";
		tituloCorto = "Libro Mayor";
		// borro las listas
		ejerciciosSelectItem = new ArrayList();
		cuentaABuscarEnLibro = "";
		fechaInicioBusqueda = new Date();
		fechaCierreBusqueda = new Date();
		ejerActual = null;
		cuentaParaMensualizadoReporte = "";
	}


	public String inicializar() {
		borrar();
		ejercicios = new ArrayList();
		ejerciciosSelectItem = new ArrayList();
		Filtro filtro = new Filtro();
		try {
			ejercicios = contabilidadService.getEjercicioService().getEjercicio(filtro);
			Iterator iterDeEjerciciosAuxiliares = ejercicios.iterator();
			while (iterDeEjerciciosAuxiliares.hasNext()) {
				Ejercicio ejer = (Ejercicio) iterDeEjerciciosAuxiliares.next();
				ejer.getIdEjercicio();
				ejer.getFechaCierre();
				ejer.getFechaInicio();
				ejer.getFechaPeriodo();
				ejer.getEstado();
				ejer.getSucursalFiel();
			}
		} catch (EjercicioException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Iterator i = ejercicios.iterator();
		ejerciciosSelectItem.add(new SelectItem(new Long(0), "Seleccione un ejercicio"));
		while (i.hasNext()) {
			Ejercicio ej = (Ejercicio) i.next();
			ejerciciosSelectItem.add(new SelectItem(new Long(ej.getIdEjercicio().intValue()), "Ejercicio: " + ej.getIdEjercicio()
					+ " | Fecha Inicio: " + ej.getFechaInicio() + " | Fecha Cierre: " + ej.getFechaCierre()));
			if (ej.getActual().compareTo("S") == 0) {
				idEjercicioSeleccionado = new Long(ej.getIdEjercicio().longValue());
				idEjercicioSeleccionadoItem = new HtmlSelectOneMenu();
				idEjercicioSeleccionadoItem.setValue(idEjercicioSeleccionado);
				ejerActual = ej;
			}
		}
		if (ejerActual != null) {

			fechaInicio = new Date(ejerActual.getFechaInicio().getTime());
			fechaCierre = new Date(ejerActual.getFechaCierre().getTime());
			fechaInicioBusqueda = fechaInicio;
			fechaCierreBusqueda = fechaCierre;
			modificarFechasEjerciciosFin = true;
			modificarFechasEjerciciosIni = true;
		}
		idSucSeleccionadaParaFiel = new Long(1);
		idSucursalDeFielSeleccionada = new HtmlSelectOneMenu();
		idSucursalDeFielSeleccionada.setValue(idSucSeleccionadaParaFiel);
		return "contabilidadLibroMayorMensualizado";
	}


	public boolean validar() {
		// TODO Auto-generated method stub
		return false;
	}


	public void listarEjercicios(ValueChangeEvent event) {
		idSucSeleccionadaParaFiel = (Long) idSucursalDeFielSeleccionada.getValue();
		ejerciciosSelectItem.clear();
		log.info("el id seleccionado es: " + idSucSeleccionadaParaFiel);
		Iterator i = ejercicios.iterator();
		Long comparador = new Long(0);
		ejerciciosSelectItem.add(new SelectItem(new Long(0), "Seleccione un ejercicio"));
		while (i.hasNext()) {
			Ejercicio ej = (Ejercicio) i.next();
			if (idSucSeleccionadaParaFiel.longValue() != comparador.longValue()) {
				if (ej.getSucursalFiel().longValue() == idSucSeleccionadaParaFiel.longValue()) {
					ejerciciosSelectItem.add(new SelectItem(new Long(ej.getIdEjercicio().intValue()), "Ejercicio: " + ej.getIdEjercicio()
							+ " | Fecha Inicio: " + ej.getFechaInicio() + " | Fecha Cierre: " + ej.getFechaCierre()));
				}
			} else {
				ejerciciosSelectItem.add(new SelectItem(new Long(ej.getIdEjercicio().intValue()), "Ejercicio: " + ej.getIdEjercicio()
						+ " | Fecha Inicio: " + ej.getFechaInicio() + " | Fecha Cierre: " + ej.getFechaCierre()));
			}
		}
		idEjercicioSeleccionado = new Long(0);
		idEjercicioSeleccionadoItem.setValue(new Long(0));
	}


	public String buscarCuentaPopup() {
		log.info("Ir a buscar una cuenta!!!");
		popupReport = "";
		PlanCuentaBean bean = (PlanCuentaBean) Session.getBean("PlanCuentaBean");
		bean.inicializaBusqueda(9);
		String path = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
		path += "/tarjetafiel/contabilidad/buscarPlanesDeCuenta.jsf";
		ejecutarJavaScript("popup('" + path + "',750,400), 'titlebar=no';");
		return null;
	}


	public void recargarYCerrarPopup(ActionEvent event) {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		String javaScriptText = "window.opener.recargar(); window.close();";
		ejecutarJavaScript(javaScriptText);
	}


	public String cancelar() {
		error.borrar();
		borrar();
		return "inicio";
	}


	public List getEjercicios() {
		return ejercicios;
	}


	public void setEjercicios(List ejercicios) {
		this.ejercicios = ejercicios;
	}


	public List getEjerciciosSelectItem() {
		return ejerciciosSelectItem;
	}


	public void setEjerciciosSelectItem(List ejerciciosSelectItem) {
		this.ejerciciosSelectItem = ejerciciosSelectItem;
	}


	public List getSucursalesFielAuxiliar() {
		return sucursalesFielAuxiliar;
	}


	public void setSucursalesFielAuxiliar(List sucursalesFielAuxiliar) {
		this.sucursalesFielAuxiliar = sucursalesFielAuxiliar;
	}


	public List getSucursalesFielSelectItem() {
		return sucursalesFielSelectItem;
	}


	public void setSucursalesFielSelectItem(List sucursalesFielSelectItem) {
		this.sucursalesFielSelectItem = sucursalesFielSelectItem;
	}


	public Long getIdSucSeleccionadaParaFiel() {
		return idSucSeleccionadaParaFiel;
	}


	public void setIdSucSeleccionadaParaFiel(Long idSucSeleccionadaParaFiel) {
		this.idSucSeleccionadaParaFiel = idSucSeleccionadaParaFiel;
	}


	public Long getIdEjercicioSeleccionado() {
		return idEjercicioSeleccionado;
	}


	public void setIdEjercicioSeleccionado(Long idEjercicioSeleccionado) {
		this.idEjercicioSeleccionado = idEjercicioSeleccionado;
	}


	public HtmlSelectOneMenu getIdEjercicioSeleccionadoItem() {
		return idEjercicioSeleccionadoItem;
	}


	public void setIdEjercicioSeleccionadoItem(
			HtmlSelectOneMenu idEjercicioSeleccionadoItem) {
		this.idEjercicioSeleccionadoItem = idEjercicioSeleccionadoItem;
	}


	public HtmlSelectOneMenu getIdSucursalDeFielSeleccionada() {
		return idSucursalDeFielSeleccionada;
	}


	public void setIdSucursalDeFielSeleccionada(
			HtmlSelectOneMenu idSucursalDeFielSeleccionada) {
		this.idSucursalDeFielSeleccionada = idSucursalDeFielSeleccionada;
	}


	public String getCuentaABuscarEnLibro() {
		return cuentaABuscarEnLibro;
	}


	public void setCuentaABuscarEnLibro(String cuentaABuscarEnLibro) {
		this.cuentaABuscarEnLibro = cuentaABuscarEnLibro;
		if (nombreCuenta != null) {
			this.cuentaABuscarEnLibro = nombreCuenta;
			nombreCuenta = null;
		} else {
			this.cuentaABuscarEnLibro = cuentaABuscarEnLibro;
		}
	}


	public boolean validarParametrosDeBusqueda() {
		idSucSeleccionadaParaFiel = (Long) idSucursalDeFielSeleccionada.getValue(); // se podria prescindir de esto por ahora
		// idEjercicioSeleccionado = (Long) idEjercicioSeleccionadoItem.getValue();
		idEjercicioSeleccionadoItem.setValue(idEjercicioSeleccionado);
		if (idEjercicioSeleccionado.equals(new Long(0))) {
			error.agregar(Error.CONT_NO_HAY_EJERCICIO_SELECCIONADO);
		}
		if (cuentaABuscarEnLibro == null || cuentaABuscarEnLibro.compareTo("") == 0) {
			error.agregar(Error.CONT_SELECCIONE_AL_MENOS_UNA_CUENTA_PARA_FILTRAR);
		}
		return (error.cantidad() == 0) ? true : false;
	}


	public void acomodarFechas(ValueChangeEvent e) {
		popupReport = "";
		idEjercicioSeleccionado = (Long) idEjercicioSeleccionadoItem.getValue();
		idEjercicioSeleccionadoItem.setValue(idEjercicioSeleccionado);
		Iterator iter = ejercicios.iterator();
		while (iter.hasNext()) {
			Ejercicio ej = (Ejercicio) iter.next();
			if (ej.getIdEjercicio().compareTo(new Integer(idEjercicioSeleccionado.intValue())) == 0) {
				ejerActual = ej;
				break;
			}
		}
		if (ejerActual != null) {
			log.info("la fechainicial es : " + ejerActual.getFechaInicio().toString());
			fechaInicioBusqueda = ejerActual.getFechaInicio();
			fechaCierreBusqueda = ejerActual.getFechaCierre();
			fechaInicio = ejerActual.getFechaInicio();
			fechaCierre = ejerActual.getFechaCierre();
			modificarFechasEjerciciosFin = true;
			modificarFechasEjerciciosIni = true;
		} else {
			log.info("el ejercicio es nulo");
		}
	}


	public Date getFechaCierre() {
		return fechaCierre;
	}


	public void setFechaCierre(Date fechaCierre) {
		this.fechaCierre = fechaCierre;
	}


	public Date getFechaInicio() {
		return fechaInicio;
	}


	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}


	public Long getNroDeCuentaBuscadaEnLibro() {
		return nroDeCuentaBuscadaEnLibro;
	}


	public void setNroDeCuentaBuscadaEnLibro(Long nroDeCuentaBuscadaEnLibro) {
		this.nroDeCuentaBuscadaEnLibro = nroDeCuentaBuscadaEnLibro;
	}


	public String getNombreCuenta() {
		return nombreCuenta;
	}


	public void setNombreCuenta(String nombreCuenta) {
		this.nombreCuenta = nombreCuenta;
	}


	public Date getFechaCierreBusqueda() {
		return fechaCierreBusqueda;
	}


	public void setFechaCierreBusqueda(Date fechaCierreBusqueda) {
		this.fechaCierreBusqueda = fechaCierreBusqueda;

	}


	public Date getFechaInicioBusqueda() {
		return fechaInicioBusqueda;
	}


	public void setFechaInicioBusqueda(Date fechaInicioBusqueda) {
		this.fechaInicioBusqueda = fechaInicioBusqueda;

	}


	public boolean validarFecha() {
		error.borrar();

		return (error.cantidad() == 0) ? true : false;
	}


	public String inicializarReporte() {
		borrar();
		ejercicios = new ArrayList();
		ejerciciosSelectItem = new ArrayList();
		Filtro filtro = new Filtro();
		try {
			ejercicios = contabilidadService.getEjercicioService().getEjercicio(filtro);
			Iterator iterDeEjerciciosAuxiliares = ejercicios.iterator();
			while (iterDeEjerciciosAuxiliares.hasNext()) {
				Ejercicio ejer = (Ejercicio) iterDeEjerciciosAuxiliares.next();
				ejer.getIdEjercicio();
				ejer.getFechaCierre();
				ejer.getFechaInicio();
				ejer.getFechaPeriodo();
				ejer.getEstado();
				ejer.getSucursalFiel();
			}
		} catch (EjercicioException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Iterator i = ejercicios.iterator();
		ejerciciosSelectItem.add(new SelectItem(new Long(0), "Seleccione un ejercicio"));
		while (i.hasNext()) {
			Ejercicio ej = (Ejercicio) i.next();
			ejerciciosSelectItem.add(new SelectItem(new Long(ej.getIdEjercicio().intValue()), "Ejercicio: " + ej.getIdEjercicio()
					+ " | Fecha Inicio: " + ej.getFechaInicio() + " | Fecha Cierre: " + ej.getFechaCierre()));
			if (ej.getActual().compareTo("S") == 0) {
				idEjercicioSeleccionado = new Long(ej.getIdEjercicio().longValue());
				idEjercicioSeleccionadoItem = new HtmlSelectOneMenu();
				idEjercicioSeleccionadoItem.setValue(idEjercicioSeleccionado);
				ejerActual = ej;
			}
		}
		if (ejerActual != null) {
			fechaInicioBusqueda = ejerActual.getFechaInicio();
			fechaCierreBusqueda = ejerActual.getFechaCierre();
			fechaInicio = ejerActual.getFechaInicio();
			fechaCierre = ejerActual.getFechaCierre();

			modificarFechasEjerciciosFin = false;
			modificarFechasEjerciciosIni = false;
		}
		idSucSeleccionadaParaFiel = new Long(1);
		idSucursalDeFielSeleccionada = new HtmlSelectOneMenu();
		idSucursalDeFielSeleccionada.setValue(idSucSeleccionadaParaFiel);
		return "reporteContabilidadLibroMayor";

	}


	public boolean validarDatosRequeridos() {
		if (listaCuentas == null || listaCuentas.compareTo("") == 0) {
			error.agregar("Ingrese los números de cuenta buscados, separados por coma.");
			return false;
		} else {
			String listaAux[] = listaCuentas.split(",");
			if (listaAux.length == 0) {
				error.agregar("Error en los numeros de cuenta. Una causa posible pueden ser dos comas seguidas.");
				return false;
			}
			listaCuentasAux = "";
			for (int i = 0; i < listaAux.length; i++) {
				listaCuentasAux += ", " + listaAux[i].trim();
				if (listaAux[i].trim().length() == 0) {
					error.agregar("Error en los numeros de cuenta. Una causa posible pueden ser dos comas seguidas.");
					return false;
				}
				try {
					int numCuenta = Integer.valueOf(listaAux[i].trim()).intValue();
				} catch (NumberFormatException e) {
					error.agregar("Los números de cuenta ingresados no son válidos.");
					return false;
				}
			}
			listaCuentasAux += ",";
		}

		return true;
	}


	public String generar(ActionEvent event) {
		error.borrar();
		popupReport = new String("");
		if (validarDatosRequeridos()) {
			HttpServletRequest request = Session.getRequest();

			Calendar fecha = Calendar.getInstance();
			fecha.setTime(fechaInicioBusqueda);
			if (ejerActual != null || validarFecha()) {
				Integer inte = new Integer(1);
				Format dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				String p1 = "?ejercicio=" + (Long) idEjercicioSeleccionadoItem.getValue();
				String p3 = "&fecha_uno=" + dateFormat.format(fechaInicioBusqueda);
				fecha.add(Calendar.MONTH, 1);
				String p4 = "&fecha_dos=" + dateFormat.format(fecha.getTime());
				fecha.add(Calendar.MONTH, 1);
				String p5 = "&fecha_tres=" + dateFormat.format(fecha.getTime());
				fecha.add(Calendar.MONTH, 1);
				String p6 = "&fecha_cuatro=" + dateFormat.format(fecha.getTime());
				fecha.add(Calendar.MONTH, 1);
				String p7 = "&fecha_cinco=" + dateFormat.format(fecha.getTime());
				fecha.add(Calendar.MONTH, 1);
				String p8 = "&fecha_seis=" + dateFormat.format(fecha.getTime());
				fecha.add(Calendar.MONTH, 1);
				String p9 = "&fecha_siete=" + dateFormat.format(fecha.getTime());
				fecha.add(Calendar.MONTH, 1);
				String p10 = "&fecha_ocho=" + dateFormat.format(fecha.getTime());
				fecha.add(Calendar.MONTH, 1);
				String p11 = "&fecha_nueve=" + dateFormat.format(fecha.getTime());
				fecha.add(Calendar.MONTH, 1);
				String p12 = "&fecha_diez=" + dateFormat.format(fecha.getTime());
				fecha.add(Calendar.MONTH, 1);
				String p13 = "&fecha_once=" + dateFormat.format(fecha.getTime());
				fecha.add(Calendar.MONTH, 1);
				String p14 = "&fecha_doce=" + dateFormat.format(fecha.getTime());
				fecha.add(Calendar.MONTH, 1);
				String p15 = "&fecha_trece=" + dateFormat.format(fecha.getTime());
				String p16 = "&lista_cuentas=" + listaCuentasAux;
				String p17 = "&id_sucursal=" + (Integer) inte;
				String p18 = "&URLImagen=" + Session.getHomePath() + "/img/fiel/logo_fiel.jpg";

				String page = request.getContextPath() + "/report/ContabilidadLibroMayorMensualizado.jrxml";

				popupReport = "popup('" + page + p1 + p3 + p4 + p5 + p6 + p7 + p8 + p9 + p10 + p11 + p12 + p13 + p14 + p15 + p16 + p17 + p18
						+ "',1000,600)";

				log.info(popupReport);
			} else {
				error.agregar("Error en el rango de fechas.");
				return null;
			}
		}
		return null;
	}


	public String generarAExcel(ActionEvent event) {
		error.borrar();
		popupReport = new String("");
		if (validarDatosRequeridos()) {
			HttpServletRequest request = Session.getRequest();

			Calendar fecha = Calendar.getInstance();
			fecha.setTime(fechaInicioBusqueda);
			if (ejerActual != null || validarFecha()) {
				Integer inte = new Integer(1);
				Format dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				String p1 = "?ejercicio=" + (Long) idEjercicioSeleccionadoItem.getValue();
				String p3 = "&fecha_uno=" + dateFormat.format(fechaInicioBusqueda);
				fecha.add(Calendar.MONTH, 1);
				String p4 = "&fecha_dos=" + dateFormat.format(fecha.getTime());
				fecha.add(Calendar.MONTH, 1);
				String p5 = "&fecha_tres=" + dateFormat.format(fecha.getTime());
				fecha.add(Calendar.MONTH, 1);
				String p6 = "&fecha_cuatro=" + dateFormat.format(fecha.getTime());
				fecha.add(Calendar.MONTH, 1);
				String p7 = "&fecha_cinco=" + dateFormat.format(fecha.getTime());
				fecha.add(Calendar.MONTH, 1);
				String p8 = "&fecha_seis=" + dateFormat.format(fecha.getTime());
				fecha.add(Calendar.MONTH, 1);
				String p9 = "&fecha_siete=" + dateFormat.format(fecha.getTime());
				fecha.add(Calendar.MONTH, 1);
				String p10 = "&fecha_ocho=" + dateFormat.format(fecha.getTime());
				fecha.add(Calendar.MONTH, 1);
				String p11 = "&fecha_nueve=" + dateFormat.format(fecha.getTime());
				fecha.add(Calendar.MONTH, 1);
				String p12 = "&fecha_diez=" + dateFormat.format(fecha.getTime());
				fecha.add(Calendar.MONTH, 1);
				String p13 = "&fecha_once=" + dateFormat.format(fecha.getTime());
				fecha.add(Calendar.MONTH, 1);
				String p14 = "&fecha_doce=" + dateFormat.format(fecha.getTime());
				fecha.add(Calendar.MONTH, 1);
				String p15 = "&fecha_trece=" + dateFormat.format(fecha.getTime());
				String p16 = "&lista_cuentas=" + listaCuentasAux;
				String p17 = "&id_sucursal=" + (Integer) inte;
				String p18 = "&URLImagen=" + Session.getHomePath() + "/img/fiel/logo_fiel.jpg";
				String p19 = "&AExcel=excel";
				String page = request.getContextPath() + "/report/ContabilidadLibroMayorMensualizadoExcel.jrxml";

				popupReport = "popup('" + page + p1 + p3 + p4 + p5 + p6 + p7 + p8 + p9 + p10 + p11 + p12 + p13 + p14 + p15 + p16 + p17 + p18 + p19
						+ "',1000,600)";

				log.info(popupReport);
			} else {
				error.agregar("Error en el rango de fechas.");
				return null;
			}
		}
		return null;
	}


	public String getListaCuentas() {
		return listaCuentas;
	}


	public void setListaCuentas(String listaCuentas) {
		this.listaCuentas = listaCuentas;
		if (cuentaParaMensualizadoReporte != null) {
			this.listaCuentas = cuentaParaMensualizadoReporte;
			cuentaParaMensualizadoReporte = null;
		}
	}


	public String getPopupReport() {
		return popupReport;
	}


	public void setPopupReport(String popupReport) {
		this.popupReport = popupReport;
	}


	public String getCuentaParaMensualizadoReporte() {
		return cuentaParaMensualizadoReporte;
	}


	public void setCuentaParaMensualizadoReporte(
			String cuentaParaMensualizadoReporte) {
		this.cuentaParaMensualizadoReporte = cuentaParaMensualizadoReporte;
	}

}
