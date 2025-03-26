package com.bizitglobal.webapp.faces.beans.contabilidad;

import java.math.BigDecimal;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import javax.faces.component.html.HtmlSelectOneMenu;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.contabilidad.exception.AsientoDetalleException;
import com.bizitglobal.tarjetafiel.contabilidad.exception.EjercicioException;
import com.bizitglobal.tarjetafiel.contabilidad.negocio.Ejercicio;
import com.bizitglobal.tarjetafiel.contabilidad.negocio.RenglonLibroMayor;
import com.bizitglobal.tarjetafiel.general.exception.SucursalFielException;
import com.bizitglobal.tarjetafiel.general.negocio.SucursalFiel;
import com.bizitglobal.webapp.faces.beans.BaseBean;
import com.bizitglobal.webapp.faces.util.Error;
import com.bizitglobal.webapp.faces.util.Session;


@SuppressWarnings({"rawtypes","unchecked","unused"})
public class LibroMayorBean extends BaseBean {
	private static final Logger log = Logger.getLogger(LibroMayorBean.class);
	public static int renglon = 0;

	/**
	 * Este es un campo oculto para guardar los id de los elementos modificados.
	 */
	private List sucursalesFielAuxiliar; // todas las sucursales
	private List sucursalesFielSelectItem; // todas las sucursales como select
	private List ejercicios;
	private List ejerciciosSelectItem; // todos los ejercicios como select item
	private LinkedList renglones;

	private String cuentaParaLibro; // es para el popup de cuentas...
	private String cuentaParaLibroReporte;

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
	private Date fechaInicioAux;
	private Date fechaCierreAux;

	private BigDecimal debe;
	private BigDecimal haber;
	private BigDecimal saldo;
	private BigDecimal saldoAnterior;

	private String sucursalVista;
	private String ejercicioVisto;
	private String fechaInicioVista;
	private String fechaCierreVista;
	private String cuentaVista;

	// Objetos necesarios para el filtro
	private Long sucursal;
	private Long ejercicio;
	private Long nroDeCuentaBuscadaEnLibro;
	// Propiedad para ejecutar el popup del reporte.
	private String popupReport = new String("");
	private String listaCuentas = "";
	private String listaCuentasAux = "";
	private boolean banderaFechasInicio = false; // truco para lograr que cambie las fechas de los ejercicios.!!!
	private boolean banderaFechasCierre = false;


	public LibroMayorBean() {
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


	public void accederAVistaPopap(String sucursalVista, String ejercicioVisto, String fechaInicioVista, String fechaCierreVista, String cuentaVista,
			Long idSucu, Long idEje, Date ini, Date fin, Long cuenta) {
		this.sucursalVista = sucursalVista;
		this.ejercicioVisto = ejercicioVisto;
		this.fechaCierreVista = fechaCierreVista.substring(0, 11);
		this.fechaInicioVista = fechaInicioVista.substring(0, 11);
		this.cuentaVista = cuentaVista;
		this.saldoAnterior = new BigDecimal(0);
		this.sucursal = idSucu;
		this.ejercicio = idEje;
		this.nroDeCuentaBuscadaEnLibro = cuenta;
		fechaInicio = ini;
		fechaCierre = fin;
		fechaInicioBusqueda = ini;
		fechaCierreBusqueda = fin;
		cargarDatosCuentaParaPopap(idSucu, idEje, ini, fin, cuenta);
		String path = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
		path += "/tarjetafiel/contabilidad/popup/libroMayorPopup.jsf";
		ejecutarJavaScript("popup('" + path + "',900,600), 'titlebar=no';");
	}


	public void cargarDatosCuentaParaPopap(Long idSucu, Long idEje, Date ini, Date fin, Long cuenta) {
		haber = new BigDecimal(0);
		debe = new BigDecimal(0);
		renglones = new LinkedList();
		try {
			List resul = contabilidadService.getRenglonLibroMayorService().getRenglonesLibroMayor(idSucu, idEje, ini, fin, cuenta, fechaInicio);
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

		} catch (AsientoDetalleException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	public void borrar() {
		error.borrar();
		popupReport = new String("");
		fechaInicioAux = new Date();
		fechaCierreAux = new Date();
		modificarFechasEjerciciosFin = false;
		modificarFechasEjerciciosIni = false;
		tituloLargo = "Tarjeta Fiel - Libro Mayor";
		tituloCorto = "Libro Mayor";
		// borro las listas
		ejerciciosSelectItem = new ArrayList();
		cuentaABuscarEnLibro = "";
		debe = new BigDecimal(0);
		haber = new BigDecimal(0);
		saldo = new BigDecimal(0);
		renglones = new LinkedList();
		fechaInicioBusqueda = new Date();
		fechaCierreBusqueda = new Date();
		ejerActual = null;
		cuentaParaLibro = null;
		cuentaParaLibroReporte = null;
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
			fechaInicioAux = ejerActual.getFechaInicio();
			fechaCierreAux = ejerActual.getFechaCierre();
			fechaInicio = new Date(fechaInicioAux.getTime());
			fechaCierre = new Date(fechaCierreAux.getTime());
			fechaInicioBusqueda = fechaInicioAux;
			//fechaCierreBusqueda = fechaCierreBusqueda;
			modificarFechasEjerciciosFin = true;
			modificarFechasEjerciciosIni = true;
		}
		idSucSeleccionadaParaFiel = new Long(1);
		idSucursalDeFielSeleccionada = new HtmlSelectOneMenu();
		idSucursalDeFielSeleccionada.setValue(idSucSeleccionadaParaFiel);
		return "libroMayor";
	}


	public boolean validar() {
		// TODO Auto-generated method stub
		return false;
	}


	public String mostrarResumenCuenta() {
		error.borrar();
		fechaInicio = new Date();
		fechaCierre = new Date();
		return null;
	}


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

	public String buscarAsientos() {
		cargarDatosCuentaParaPopap(sucursal, ejercicio, fechaInicioBusqueda, fechaCierreBusqueda, nroDeCuentaBuscadaEnLibro);
		return null;
	}


	public String buscarCuentaPopup() {
		log.info("Ir a buscar una cuenta!!!");
		popupReport = "";
		PlanCuentaBean bean = (PlanCuentaBean) Session.getBean("PlanCuentaBean");
		bean.inicializaBusqueda(6);
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
		if (cuentaParaLibro != null) {
			this.cuentaABuscarEnLibro = cuentaParaLibro;
			cuentaParaLibro = null;
		} else {
			this.cuentaABuscarEnLibro = cuentaABuscarEnLibro;
		}
	}


	public boolean validarParametrosDeBusqueda() {
		idSucSeleccionadaParaFiel = (Long) idSucursalDeFielSeleccionada.getValue(); // se podria prescindir de esto por ahora
		idEjercicioSeleccionado = (Long) idEjercicioSeleccionadoItem.getValue();
		// if (idSucSeleccionadaParaFiel.equals(new Long(0))) {
		// error.agregar(Error.CONT_NO_HAY_SUCURSAL_SELECCIONADA);
		// }
		if (idEjercicioSeleccionado.equals(new Long(0))) {
			error.agregar(Error.CONT_NO_HAY_EJERCICIO_SELECCIONADO);
		}
		if (cuentaABuscarEnLibro == null || cuentaABuscarEnLibro.compareTo("") == 0) {
			error.agregar("Ingrese el número de cuenta buscado.");
		} else {
			String listaAux[] = cuentaABuscarEnLibro.split(",");
			if (listaAux.length == 0) {
				error.agregar("Error en el número de cuenta.");
			}
			listaCuentasAux = "";
			for (int i = 0; i < listaAux.length; i++) {
				listaCuentasAux += ", " + listaAux[i].trim();
				if (listaAux[i].trim().length() == 0) {
					error.agregar("Error en el número de cuenta.");
					i = listaAux.length;
				}
				try {
					int numCuenta = Integer.valueOf(listaAux[i].trim()).intValue();
				} catch (NumberFormatException e) {
					error.agregar("El número de cuenta ingresado no es válido.");
					i = listaAux.length;
				}
			}
			listaCuentasAux += ",";
		}
		return (error.cantidad() == 0) ? true : false;
	}


	public String filtrarCuenta() {
		haber = new BigDecimal(0);
		debe = new BigDecimal(0);
		renglones = new LinkedList();
		if (validarParametrosDeBusqueda()) {
			try {
				List resul = contabilidadService.getRenglonLibroMayorService().getRenglonesLibroMayor(idSucSeleccionadaParaFiel,
						idEjercicioSeleccionado, fechaInicioBusqueda, fechaCierreBusqueda, new Long(cuentaABuscarEnLibro), fechaInicio);
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

			} catch (AsientoDetalleException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}


	public void acomodarFechas(ValueChangeEvent e) {
		popupReport = "";
		idEjercicioSeleccionado = (Long) idEjercicioSeleccionadoItem.getValue();
		idEjercicioSeleccionadoItem.setValue(idEjercicioSeleccionado);
		Iterator iter = ejercicios.iterator();
		while (iter.hasNext()) {
			Ejercicio ej = (Ejercicio) iter.next();
			if (ej.getIdEjercicio().compareTo(new Integer(idEjercicioSeleccionado.intValue())) == 0) {
				log.info("Tenemos un ejercicio seleccionado");
				ejerActual = ej;
				break;
			}
		}
		if (ejerActual != null) {
			banderaFechasInicio = true;
			banderaFechasCierre = true;
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
		if (!banderaFechasCierre) {
			this.fechaCierreBusqueda = fechaCierreBusqueda;
		} else {
			banderaFechasCierre = false;
		}
	}


	public Date getFechaInicioBusqueda() {
		return fechaInicioBusqueda;
	}


	public void setFechaInicioBusqueda(Date fechaInicioBusqueda) {
		if (!banderaFechasInicio) {
			this.fechaInicioBusqueda = fechaInicioBusqueda;
		} else {
			banderaFechasInicio = false;
		}
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


	public String getEjercicioVisto() {
		return ejercicioVisto;
	}


	public void setEjercicioVisto(String ejercicioVisto) {
		this.ejercicioVisto = ejercicioVisto;
	}


	public String getFechaCierreVista() {
		return fechaCierreVista;
	}


	public void setFechaCierreVista(String fechaCierreVista) {
		this.fechaCierreVista = fechaCierreVista;
	}


	public String getFechaInicioVista() {
		return fechaInicioVista;
	}


	public void setFechaInicioVista(String fechaInicioVista) {
		this.fechaInicioVista = fechaInicioVista;
	}


	public String getSucursalVista() {
		return sucursalVista;
	}


	public void setSucursalVista(String sucursalVista) {
		this.sucursalVista = sucursalVista;
	}


	public BigDecimal getSaldoAnterior() {
		RenglonLibroMayor aux = ((WrapperDetalleCuenta) ((LinkedList) renglones).getFirst()).getRenglonLibroMayor();
		return new BigDecimal(aux.getSaldoAcumulado().longValue() - (aux.getSaldo().longValue()));
	}


	public boolean validarFecha() {
		error.borrar();
		//
		// if(Validador.esNulo(getFechaInicioBusqueda()) || getFechaInicioBusqueda().equals(new Date(0))
		// || Validador.esNulo(getFechaCierreBusqueda()) || getFechaCierreBusqueda().equals(new Date(0))){
		// error.agregar(Error.COMPROBANTE_FECHA_REQUERIDA);
		// }else {
		// if (getFechaInicioBusqueda().after(getFechaCierreBusqueda())) {
		// error.agregar("La fecha desde no puede ser mayor a la fecha hasta");
		// }
		// }
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
		return "reporteContabilidadLibroMayor";

	}


	public String prepararInforme() {
		modificarFechasEjerciciosFin = false;
		modificarFechasEjerciciosIni = false;

		return null;
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
			if (ejerActual != null || validarFecha()) {
				Integer inte = new Integer(1);
				Format dateFormat = new SimpleDateFormat("yyyy-MM-dd");

				String p1 = "?fecha_inicio=" + dateFormat.format(ejerActual.getFechaInicio());
				String p2 = "&fecha_desde=" + dateFormat.format(fechaInicioBusqueda);
				String p3 = "&fecha_hasta=" + dateFormat.format(fechaCierreBusqueda);
				String p4 = "&lista_cuentas=" + listaCuentasAux;
				String p5 = "&ejercicio=" + (Long) idEjercicioSeleccionadoItem.getValue();
				String p6 = "&id_sucursal=" + (Integer) inte;
				String p7 = "&URLImagen=" + Session.getHomePath() + "/img/fiel/logo_fiel.jpg";

				String page = request.getContextPath() + "/report/ContabilidadLibroMayor.jrxml";
				popupReport = "popup('" + page + p1 + p2 + p3 + p4 + p5 + p6 + p7 + "',1000,600)";

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
			if (ejerActual != null || validarFecha()) {
				Integer inte = new Integer(1);
				Format dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				String p1 = "?ejercicio=" + (Long) idEjercicioSeleccionadoItem.getValue();
				String p2 = "&fecha_inicio=" + dateFormat.format(ejerActual.getFechaInicio());
				String p3 = "&fecha_desde=" + dateFormat.format(fechaInicioBusqueda);
				String p4 = "&fecha_hasta=" + dateFormat.format(fechaCierreBusqueda);
				String p5 = "&lista_cuentas=" + listaCuentasAux;
				String p6 = "&id_sucursal=" + (Integer) inte;
				String p7 = "&URLImagen=" + Session.getHomePath() + "/img/fiel/logo_fiel.jpg";
				String p8 = "&AExcel=excel";
				String page = request.getContextPath() + "/report/ContabilidadLibroMayorExcel.jrxml";

				popupReport = "popup('" + page + p1 + p2 + p3 + p4 + p5 + p6 + p7 + p8 + "',1000,600)";

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

}
