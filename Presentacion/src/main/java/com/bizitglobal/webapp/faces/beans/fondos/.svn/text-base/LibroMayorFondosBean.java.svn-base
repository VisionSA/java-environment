package com.bizitglobal.webapp.faces.beans.fondos;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.fondos.exception.LibroMayorExeption;
import com.bizitglobal.tarjetafiel.fondos.negocio.RenglonLibroMayor;
import com.bizitglobal.webapp.faces.beans.BaseBean;
import com.bizitglobal.webapp.faces.beans.contabilidad.PlanCuentaBean;
import com.bizitglobal.webapp.faces.util.Session;
import com.bizitglobal.webapp.faces.util.Validador;
import com.bizitglobal.webapp.faces.util.Error;


@SuppressWarnings({"rawtypes","unchecked"})
public class LibroMayorFondosBean extends BaseBean {
	private static final Logger log = Logger.getLogger(LibroMayorFondosBean.class);
	public static int renglon = 0;

	/**
	 * Este es un campo oculto para guardar los id de los elementos modificados.
	 */
	private LinkedList renglones;

	private String cuentaParaLibro; // es para el popup de cuentas...
	private String cuentaParaLibroReporte;
	private String nombreCuenta;
	private String cuentaABuscarEnLibro;
	private Date fechaInicioBusqueda;
	private Date fechaCierreBusqueda;

	private BigDecimal debe;
	private BigDecimal haber;
	private BigDecimal saldo;
	private BigDecimal saldoAnterior;
	private BigDecimal saldoPeriodo;
	private String cuentaVista;

	// Objetos necesarios para el filtro
	private Long sucursal;
	// private Long ejercicio;
	private Long nroDeCuentaBuscadaEnLibro;
	// Propiedad para ejecutar el popup del reporte.
	private String popupReport = new String("");
	private String listaCuentas = null;


	public LibroMayorFondosBean() {

	}


	public String buscarLibroMayor() {
		error.borrar();
		log.info("Ejecutamos el metodo para mostrar libro mayor");

		return null;

	}


	/*
	 * public void accederAVistaPopap(String sucursalVista, String ejercicioVisto, String fechaInicioVista, String fechaCierreVista, String
	 * cuentaVista, Long idSucu, Long idEje, Date ini, Date fin, Long cuenta) { this.sucursalVista = sucursalVista; this.ejercicioVisto =
	 * ejercicioVisto; this.fechaCierreVista = fechaCierreVista.substring(0,11); this.fechaInicioVista = fechaInicioVista.substring(0,11);
	 * this.cuentaVista = cuentaVista; this.saldoAnterior= new BigDecimal(0); this.sucursal = idSucu; this.ejercicio = idEje;
	 * this.nroDeCuentaBuscadaEnLibro = cuenta; fechaInicio = ini; fechaCierre = fin; fechaInicioBusqueda=ini; fechaCierreBusqueda=fin;
	 * cargarDatosCuentaParaPopap(idSucu,idEje,ini,fin,cuenta); String path =
	 * FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath(); path += "/tarjetafiel/contabilidad/popup/libroMayorPopup.jsf";
	 * ejecutarJavaScript("popup('" + path + "',900,600), 'titlebar=no';"); }
	 * 
	 * public void cargarDatosCuentaParaPopap(Long idSucu, Long idEje, Date ini, Date fin, Long cuenta) { haber = new BigDecimal(0); debe = new
	 * BigDecimal(0); renglones = new LinkedList(); try { List resul =
	 * contabilidadService.getRenglonLibroMayorService().getRenglonesLibroMayorFondos(ini, fin, cuenta, fechaInicio); Iterator ite = resul.iterator();
	 * while (ite.hasNext()) { RenglonLibroMayor ren = (RenglonLibroMayor)ite.next(); haber = new BigDecimal(haber.doubleValue()+
	 * ren.getHaber().doubleValue()); debe = new BigDecimal(debe.doubleValue()+ren.getDebe().doubleValue()); renglones.add(new
	 * WrapperDetalleCuenta(ren)); } haber = haber.setScale(2,BigDecimal.ROUND_HALF_DOWN); debe = debe.setScale(2,BigDecimal.ROUND_HALF_DOWN); saldo =
	 * debe.subtract(haber); saldo = saldo.setScale(2,BigDecimal.ROUND_HALF_DOWN);
	 * 
	 * } catch (AsientoDetalleException e) { // TODO Auto-generated catch block e.printStackTrace(); } catch (Exception e) { // TODO Auto-generated
	 * catch block e.printStackTrace(); } }
	 */

	public void borrar() {
		error.borrar();
		popupReport = new String("");
		fechaInicioBusqueda = new Date();
		fechaCierreBusqueda = new Date();
		tituloLargo = "Tarjeta Fiel - Libro Mayor";
		tituloCorto = "Libro Mayor";
		// borro las listas
		listaCuentas = null;
		cuentaABuscarEnLibro = "";
		debe = new BigDecimal(0);
		haber = new BigDecimal(0);
		saldo = new BigDecimal(0);
		renglones = new LinkedList();
		cuentaParaLibro = null;
		cuentaParaLibroReporte = null;
	}


	public void borrarMov() {
		error.borrar();
		popupReport = new String("");
		fechaInicioBusqueda = new Date();
		fechaCierreBusqueda = new Date();
		tituloLargo = "Tarjeta Fiel - Movimientos Caja";
		tituloCorto = "Movimientos Caja";

	}


	public String inicializar() {
		borrar();
		return "libroMayorFondos";
	}


	public String inicializarMovimientos() {
		borrarMov();
		return "fondosMovCaja";
	}


	public boolean validar() {
		// TODO Auto-generated method stub
		return false;
	}


	/*
	 * public String mostrarResumenCuenta() { error.borrar(); fechaInicio = new Date(); fechaCierre = new Date(); return null; }
	 */

	// public void listarEjercicios(ValueChangeEvent event) {
	// idSucSeleccionadaParaFiel = (Long) idSucursalDeFielSeleccionada.getValue();
	// ejerciciosSelectItem.clear();
	// log.info("el id seleccionado es: " + idSucSeleccionadaParaFiel);
	// Iterator i = ejercicios.iterator();
	// Long comparador = new Long(0);
	// ejerciciosSelectItem.add(new SelectItem(new Long(0),"Seleccione un ejercicio"));
	// while (i.hasNext()) {
	// Ejercicio ej = (Ejercicio) i.next();
	// if (idSucSeleccionadaParaFiel.longValue() != comparador.longValue()) {
	// if (ej.getSucursalFiel().longValue() == idSucSeleccionadaParaFiel.longValue()) {
	// ejerciciosSelectItem.add(new SelectItem(new Long(ej.getIdEjercicio().intValue()), "Ejercicio: "+ ej.getIdEjercicio() + " | Fecha Inicio: "+
	// ej.getFechaInicio() + " | Fecha Cierre: "+ ej.getFechaCierre()));
	// }
	// } else {
	// ejerciciosSelectItem.add(new SelectItem(new Long(ej.getIdEjercicio().intValue()), "Ejercicio: "+ ej.getIdEjercicio() + " | Fecha Inicio: "+
	// ej.getFechaInicio() + " | Fecha Cierre: "+ ej.getFechaCierre()));
	// }
	// }
	// idEjercicioSeleccionado = new Long(0);
	// idEjercicioSeleccionadoItem.setValue(new Long(0));
	// }

	/*
	 * public String buscarAsientos() {
	 * cargarDatosCuentaParaPopap(sucursal,ejercicio,fechaInicioBusqueda,fechaCierreBusqueda,nroDeCuentaBuscadaEnLibro); return null; }
	 */

	public String buscarCuentaPopup() {
		log.info("Ir a buscar una cuenta!!!");
		popupReport = "";
		PlanCuentaBean bean = (PlanCuentaBean) Session.getBean("PlanCuentaBean");
		bean.inicializaBusqueda(14);
		String path = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
		path += "/tarjetafiel/contabilidad/buscarPlanesDeCuenta.jsf";
		ejecutarJavaScript("popup('" + path + "',750,400), 'titlebar=no';");
		return null;
	}


	public String buscarCuentaPopupReporte() {
		popupReport = "";
		PlanCuentaBean bean = (PlanCuentaBean) Session.getBean("PlanCuentaBean");
		bean.inicializaBusqueda(13);
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


	public String getCuentaABuscarEnLibro() {
		return cuentaABuscarEnLibro;
	}


	public void setCuentaABuscarEnLibro(String cuentaABuscarEnLibro) {
		this.cuentaABuscarEnLibro = cuentaABuscarEnLibro;
		if (cuentaParaLibro != null) {
			this.cuentaABuscarEnLibro = cuentaParaLibro;
			cuentaParaLibro = null;
		} else {
			this.cuentaABuscarEnLibro = cuentaABuscarEnLibro;
		}
	}


	public boolean validarParametrosDeBusqueda() {
		validarFecha();
		if (cuentaABuscarEnLibro == null || cuentaABuscarEnLibro.compareTo("") == 0) {
			error.agregar("Ingrese el número de cuenta buscado.");
		}
		return (error.cantidad() == 0) ? true : false;
	}


	public String filtrarCuenta() {
		haber = new BigDecimal(0);
		debe = new BigDecimal(0);
		renglones = new LinkedList();
		if (validarParametrosDeBusqueda()) {
			try {
				/*
				 * GregorianCalendar cal= new GregorianCalendar(); cal.add(cal.DATE, -400); fechaInicio =cal.getTime();
				 */
				/*
				 * Calendar cal= Calendar.getInstance(); cal.setTime(fechaInicio);
				 */
				Filtro filtro = new Filtro();
				filtro.agregarCampoOperValor("estado", Filtro.IGUAL, "'C'");
				filtro.agregarCampoOperValor("fechaInicio", Filtro.MENOR, Filtro.getTO_DATE(fechaInicioBusqueda));
				filtro.agregarOrderBy("fechaInicio desc");
				// filtro.agregarCampoOperValor("",Filtro. , unValor);
				// List ejercicios = contabilidadService.getEjercicioService().getEjercicio(filtro);
				// Iterator iter= ejercicios.iterator();
				// while (iter.hasNext()) {
				// Ejercicio element = (Ejercicio) iter.next();
				// System.out.println( element.getIdEjercicio());
				// }
				// List resul = contabilidadService.getRenglonLibroMayorService().getRenglonesLibroMayorFondos( fechaInicioBusqueda,
				// fechaCierreBusqueda, new Long(cuentaABuscarEnLibro), null);
				List resul = fondosService.getLibroMayorFondosService().getRenglonesLibroMayorFondos(fechaInicioBusqueda, fechaCierreBusqueda,
						new Long(cuentaABuscarEnLibro), null);
				Iterator ite = resul.iterator();

				while (ite.hasNext()) {
					RenglonLibroMayor ren = (RenglonLibroMayor) ite.next();
					haber = new BigDecimal(haber.doubleValue() + ren.getHaber().doubleValue());
					debe = new BigDecimal(debe.doubleValue() + ren.getDebe().doubleValue());
					renglones.add(new WrapperDetalleCuenta(ren));
				}
				haber = haber.setScale(2, BigDecimal.ROUND_HALF_DOWN);
				debe = debe.setScale(2, BigDecimal.ROUND_HALF_DOWN);
				saldo = debe.subtract(haber);
				saldo = saldo.setScale(2, BigDecimal.ROUND_HALF_DOWN);
				saldoPeriodo = new BigDecimal(saldo.doubleValue() + getSaldoAnterior().doubleValue());

			} catch (LibroMayorExeption e) {
				// TODO Auto-generated catch block
				e.printStackTrace();

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
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

	public class WrapperDetalleCuenta {

		private RenglonLibroMayor renglonLibroMayor;
		private int nroRenglon;


		public WrapperDetalleCuenta(RenglonLibroMayor renglonLibroMayor) throws Exception {
			super();
			this.renglonLibroMayor = renglonLibroMayor;
			nroRenglon = ++renglon;

		}


		public int getNroRenglon() {
			return nroRenglon;
		}


		public void setNroRenglon(int nroRenglon) {
			this.nroRenglon = nroRenglon;
		}


		public RenglonLibroMayor getRenglonLibroMayor() {
			return renglonLibroMayor;
		}


		public void setRenglonLibroMayor(RenglonLibroMayor renglonLibroMayor) {
			this.renglonLibroMayor = renglonLibroMayor;
		}

	}


	public List getRenglones() {
		return renglones;
	}


	public void setRenglones(LinkedList renglones) {
		this.renglones = renglones;
	}


	public BigDecimal getDebe() {
		return debe;
	}


	public void setDebe(BigDecimal debe) {
		this.debe = debe;
	}


	public BigDecimal getHaber() {
		return haber;
	}


	public void setHaber(BigDecimal haber) {
		this.haber = haber;
	}


	public BigDecimal getSaldo() {
		return saldo;
	}


	public void setSaldo(BigDecimal saldo) {
		this.saldo = saldo;
	}


	public String getCuentaVista() {
		return cuentaVista;
	}


	public void setCuentaVista(String cuentaVista) {
		this.cuentaVista = cuentaVista;
	}


	public BigDecimal getSaldoAnterior() {
		RenglonLibroMayor aux = ((WrapperDetalleCuenta) ((LinkedList) renglones).getFirst()).getRenglonLibroMayor();
		return new BigDecimal(aux.getSaldoAcumulado().longValue() - (aux.getSaldo().longValue()));
	}


	public boolean validarFecha() {
		error.borrar();

		if (Validador.esNulo(getFechaInicioBusqueda()) || getFechaInicioBusqueda().equals(new Date(0))
				|| Validador.esNulo(getFechaCierreBusqueda()) || getFechaCierreBusqueda().equals(new Date(0))) {
			error.agregar(Error.COMPROBANTE_FECHA_REQUERIDA);
		} else {
			if (getFechaInicioBusqueda().after(getFechaCierreBusqueda())) {
				error.agregar("La fecha desde no puede ser mayor a la fecha hasta");
			}
		}
		return (error.cantidad() == 0) ? true : false;
	}


	public String inicializarReporte() {
		borrar();
		fechaInicioBusqueda = new Date();
		fechaCierreBusqueda = new Date();
		return "reporteFondosLibroMayor";

	}


	public boolean validarDatosRequeridos() {
		if (listaCuentas == null || listaCuentas.compareTo("") == 0) {
			error.agregar("Ingrese los números de cuenta buscados, separados por coma.");
			return false;
		}
		return true;
	}


	public String generar() {
		error.borrar();
		popupReport = new String("");
		HttpServletRequest request = Session.getRequest();
		if (validarFecha()) {
			Calendar cal = Calendar.getInstance();
			cal.setTime(fechaInicioBusqueda);
			Calendar cal2 = Calendar.getInstance();
			cal2.setTime(fechaCierreBusqueda);
			String p1 = "?fecha_desde=" + cal.get(Calendar.YEAR) + "-" + (cal.get(Calendar.MONTH) + 1) + "-" + cal.get(Calendar.DATE);
			String p2 = "&fecha_hasta=" + cal2.get(Calendar.YEAR) + "-" + (cal2.get(Calendar.MONTH) + 1) + "-" + cal2.get(Calendar.DATE);
			String p3 = "&lista_cuentas=" + listaCuentas;
			String p4 = "&URLImagen=" + Session.getHomePath() + "/img/fiel/logo_fiel.jpg";
			String page = request.getContextPath() + "/report/FondosLibroMayor.jrxml";
			popupReport = "popup('" + page + p1 + p2 + p3 + p4 + "',1000,600)";
			log.info(popupReport);
		} else {
			error.agregar("Error en el rango de fechas.");
			return null;
		}
		return null;
	}


	public String generarAExcel() {
		error.borrar();
		popupReport = new String("");
		HttpServletRequest request = Session.getRequest();
		if (validarFecha()) {
			BigDecimal saldo = new BigDecimal(25);
			Calendar cal = Calendar.getInstance();
			cal.setTime(fechaInicioBusqueda);
			Calendar cal2 = Calendar.getInstance();
			cal2.setTime(fechaCierreBusqueda);
			String p1 = "?fecha_desde=" + cal.get(Calendar.YEAR) + "-" + (cal.get(Calendar.MONTH) + 1) + "-" + cal.get(Calendar.DATE);
			String p2 = "&fecha_hasta=" + cal2.get(Calendar.YEAR) + "-" + (cal2.get(Calendar.MONTH) + 1) + "-" + cal2.get(Calendar.DATE);
			String p3 = "&lista_cuentas=" + listaCuentas;
			String p4 = "&URLImagen=" + Session.getHomePath() + "/img/fiel/logo_fiel.jpg";
			String p5 = "&AExcel=excel";
			String page = request.getContextPath() + "/report/FondosLibroMayorExcel.jrxml";
			popupReport = "popup('" + page + p1 + p2 + p3 + p4 + p5 + "',1000,600)";
			log.info(popupReport);
		} else {
			error.agregar("Error en el rango de fechas.");
			return null;
		}
		return null;
	}


	public String generarMovAExcel() {
		error.borrar();
		popupReport = new String("");
		HttpServletRequest request = Session.getRequest();
		if (validarFecha()) {

			Calendar cal = Calendar.getInstance();
			cal.setTime(fechaInicioBusqueda);
			Calendar cal2 = Calendar.getInstance();
			cal2.setTime(fechaCierreBusqueda);
			String p1 = "?fecha_desde=" + cal.get(Calendar.YEAR) + "-" + (cal.get(Calendar.MONTH) + 1) + "-" + cal.get(Calendar.DATE);
			String p2 = "&fecha_hasta=" + cal2.get(Calendar.YEAR) + "-" + (cal2.get(Calendar.MONTH) + 1) + "-" + cal2.get(Calendar.DATE);
			// String p3 = "&lista_cuentas="+listaCuentas;
			// String p4 = "&URLImagen="+Session.getHomePath()+"/img/fiel/logo_fiel.jpg";
			String p5 = "&AExcel=excel";
			String page = request.getContextPath() + "/report/FonMovCaja001exel.jrxml";
			popupReport = "popup('" + page + p1 + p2 + p5 + "',1000,600)";
			log.info(popupReport);
		} else {
			error.agregar("Error en el rango de fechas.");
			return null;
		}
		return null;
	}


	public String getListaCuentas() {
		return listaCuentas;
	}


	public void setListaCuentas(String listaCuentas) {
		this.listaCuentas = listaCuentas;
		if (cuentaParaLibroReporte != null) {
			this.listaCuentas = cuentaParaLibroReporte;
			cuentaParaLibroReporte = null;
		}
	}


	public String getPopupReport() {
		return popupReport;
	}


	public void setPopupReport(String popupReport) {
		this.popupReport = popupReport;
	}


	public String getCuentaParaLibro() {
		return cuentaParaLibro;
	}


	public void setCuentaParaLibro(String cuentaParaLibro) {
		this.cuentaParaLibro = cuentaParaLibro;
	}


	public String getCuentaParaLibroReporte() {
		return cuentaParaLibroReporte;
	}


	public void setCuentaParaLibroReporte(String cuentaParaLibroReporte) {
		this.cuentaParaLibroReporte = cuentaParaLibroReporte;
	}


	public BigDecimal getSaldoPeriodo() {
		return saldoPeriodo;
	}


	public void setSaldoPeriodo(BigDecimal saldoPeriodo) {
		this.saldoPeriodo = saldoPeriodo;
	}

}
