package com.bizitglobal.webapp.faces.beans.contabilidad;

import java.sql.Timestamp;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.faces.component.html.HtmlSelectOneMenu;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

import org.apache.log4j.Logger;
import org.apache.myfaces.renderkit.html.util.AddResource;
import org.apache.myfaces.renderkit.html.util.AddResourceFactory;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.contabilidad.exception.AsientoDetalleException;
import com.bizitglobal.tarjetafiel.contabilidad.exception.AsientoException;
import com.bizitglobal.tarjetafiel.contabilidad.exception.CentroCostoAsientoException;
import com.bizitglobal.tarjetafiel.contabilidad.exception.CentroCostosException;
import com.bizitglobal.tarjetafiel.contabilidad.exception.EjercicioException;
import com.bizitglobal.tarjetafiel.contabilidad.exception.LoteDetalleException;
import com.bizitglobal.tarjetafiel.contabilidad.exception.LoteException;
import com.bizitglobal.tarjetafiel.contabilidad.exception.OrigenException;
import com.bizitglobal.tarjetafiel.contabilidad.exception.PlanCuentaDosException;
import com.bizitglobal.tarjetafiel.contabilidad.negocio.Asiento;
import com.bizitglobal.tarjetafiel.contabilidad.negocio.AsientoDetalle;
import com.bizitglobal.tarjetafiel.contabilidad.negocio.CentroCostoAsiento;
import com.bizitglobal.tarjetafiel.contabilidad.negocio.CentroCostos;
import com.bizitglobal.tarjetafiel.contabilidad.negocio.DocAdjunto;
import com.bizitglobal.tarjetafiel.contabilidad.negocio.Ejercicio;
import com.bizitglobal.tarjetafiel.contabilidad.negocio.Lote;
import com.bizitglobal.tarjetafiel.contabilidad.negocio.LoteDetalle;
import com.bizitglobal.tarjetafiel.contabilidad.negocio.Origen;
import com.bizitglobal.tarjetafiel.contabilidad.negocio.PlanCuentaDos;
import com.bizitglobal.tarjetafiel.general.exception.SucursalFielException;
import com.bizitglobal.tarjetafiel.general.negocio.SucursalFiel;
import com.bizitglobal.tarjetafiel.operador.negocio.Operador;
import com.bizitglobal.webapp.faces.beans.BaseBean;
import com.bizitglobal.webapp.faces.beans.general.DocumentoAdjuntoBean;
import com.bizitglobal.webapp.faces.beans.util.LeedoraDeArrays;
import com.bizitglobal.webapp.faces.beans.util.PaginaDeRegistros;
import com.bizitglobal.webapp.faces.util.Error;
import com.bizitglobal.webapp.faces.util.Session;
import com.bizitglobal.webapp.faces.util.Validador;


public class AsientosBean extends BaseBean {
	private static final Logger log = Logger.getLogger(AsientosBean.class);

	private static int numeroAsientoTabla = 0;
	private static int numeroAsientoDetalleTabla = 0;
	private static int numeroLoteRenglonTabla = 0;
	private static int numeroLoteDetalleRenglonTabla = 0;
	private boolean campoEditableEnAsiento; // para el disabled d elos campos // editables.
	private boolean enLote; // para ver lotes o ejercicioactual.
	private Asiento asientoDetallado;
	private Lote loteDetallado;
	PopupAltaAsiento popupAltaAsiento;
	PlanCuentaDos cuentaNuevaParaDetalle;

	/**
	 * Este es un campo oculto para guardar los id de los elementos modificados.
	 */
	private String listaDeModicadosEnString;
	private String listaDeEliminadosEnString;
	private WrapperLoteDetalle wrapLot;
	// un booleano para migrar lotes a asientos;
	private boolean todos;
	// objetos para mostrar el balanceo de los detalles de asientos y lotes.
	private String balanceAsientoHaber;
	private String balanceAsientoDebe;
	private String balanceLoteHaber;
	private String balanceLoteDebe;
	private List cuentasDisponibles;
	private List idDeCuentasDisponibles; // guardo los id de las cuentas que soportan centros de Costos.
	private List centroCostosDisponibles;
	private List origenes;
	private List origenesItem; // como items
	private List sucursalesFielAuxiliar; // todas las sucursales
	private List sucursalesFielSelectItem; // todas las sucursales como select // item
	private List ejercicios; // todos los ejercicios
	private List ejerciciosSelectItem; // todos los ejercicios como select
										// item;
	private List asientos; // aqui los wrappers para asientos
	private List asientosDetalles; // aqui los wrappers para los asientos // Detalles
	private List lotes; // aqui wrappers para lotes.
	private List lotesDetalles; // aqui lotes en detalles, adentro de wrappers
	private List listaCentroDeCostos; // aqui la lista de centros de Costos
										// existentes en forma de Select item
	private List listaCentroDeCostosAux; // aqui la lista de centros de
											// Costos existentes
	private List lotesAuxi; // para poder agregar registros de lote a los leidos
							// de la base de datos sin releerla y poder
							// paginarlos.
	// buscadores de cuentas en el filtro;
	private String cuentaParaAsi;
	private String cuentaParaLot;
	// un objeto para evitar el heap Space de la virtual machine
	PaginaDeRegistros paginador;
	PaginaDeRegistros paginadorDeLotes;
	// objetos para trabajar con el select item de sucursales
	private Long idSucSeleccionadaParaFiel;
	private HtmlSelectOneMenu idSucursalDeFielSeleccionada;
	// objetos para trabajar con el select item de ejercicios
	private Long idEjercicioSeleccionado;
	private HtmlSelectOneMenu idEjercicioSeleccionadoItem;
	// objetos para trabajar con el select item de importacion
	private Long idOrigenSeleccionadoParaImportar;
	private HtmlSelectOneMenu origenSeleccionadoParaImportar;
	Ejercicio ejerActual;
	// objetos para el filtro de asientos, como el numero de asiento y la fecha
	// en que se hace
	private String nroAsiento;
	private Date fechaAsiento;
	private Long idCentroCostoSeleccionado;
	private HtmlSelectOneMenu centroCostoSeleccionado;
	private Long idCentroCostoSeleccionadoLote;
	private HtmlSelectOneMenu centroCostoSeleccionadoLote;
	private Long idOrigenSeleccionado;
	private HtmlSelectOneMenu origenSeleccionado;
	private Long idOrigenSeleccionadoLote;
	private HtmlSelectOneMenu origenSeleccionadoLote;
	private Long nroDeCuentaBuscadaEnAsiento;
	private String cuentaABuscarEnAsiento;
	private String conceptoABuscarEnAsiento;
	private Long nroDeCuentaBuscadaEnLote;
	private String cuentaABuscarEnLote;
	private String conceptoABuscarEnLote;
	private Date fechaInicioEjercicio;
	private Date fechaFinEjercicio;
	private Date fechaInicioAsiento;
	private Date fechaCierreAsiento;
	private Date fechaInicioLote;
	private Date fechaCierreLote;
	/* @F4933 */private Date fechaInicioImportar;
	/* @F4933 */private Date fechaCierreImportar;
	private boolean modificarFechasEjercicios = false;
	private String numeroImputaAAgregar;

	private String idAsientoABuscarEnAsiento;
	private String idAsientoABuscarEnLote;
	private String importeAsiento;
	private String importeLote;
	private String aproximadoHastaAsi;
	private String aproximadoHastaLot;
	private boolean importeSoloEnDebeAsi;
	private boolean importeSoloEnHaberAsi;
	private boolean importeSoloEnDebeLot;
	private boolean importeSoloEnHaberLot;

	private boolean agregarL = false;
	private boolean aperturaPermitida = true; // si el asiento de apertura ya esta hecho en el ejercicio no deja realizarlo
	private boolean cierrePermitido = true; // idem, pero con el cierre del ejercicio.

	private String listaCuentas;


	public AsientosBean() {
		sucursalesFielSelectItem = new ArrayList();
		asientos = new ArrayList();
		lotes = new ArrayList();
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
		try {
			Filtro fil = new Filtro();
			cuentasDisponibles = contabilidadService.getPlanCuentaDosService().getPlanCuenta(new Filtro("uso", Filtro.LIKE, "I"));
			idDeCuentasDisponibles = new ArrayList();
			Iterator it = cuentasDisponibles.iterator();
			while (it.hasNext()) {
				PlanCuentaDos cuen = (PlanCuentaDos) it.next();
				if (cuen.getCentroCosto().compareTo("S") == 0) {
					idDeCuentasDisponibles.add(cuen.getIdPlanCuenta());
				}
			}
			centroCostosDisponibles = contabilidadService.getCentroCostosService().getCentroCostos(fil);
		} catch (PlanCuentaDosException e) {
			log.info("imposible leer las cuentas existentes");
			e.printStackTrace();
		} catch (CentroCostosException e2) {
			log.info("imposible leer los Centros de costos existentes");
			e2.printStackTrace();
		}
		try {
			listaCentroDeCostos = new ArrayList();
			listaCentroDeCostosAux = contabilidadService.getCentroCostosService().getCentroCostos(new Filtro());
			Iterator iter = listaCentroDeCostosAux.iterator();
			listaCentroDeCostos.add(new SelectItem(new Long(0), ""));
			while (iter.hasNext()) {
				CentroCostos cen = (CentroCostos) iter.next();
				listaCentroDeCostos.add(new SelectItem(cen.getIdCentroCostos(), cen.getDescripcion()));
			}
		} catch (CentroCostosException e) {
			e.printStackTrace();
		}
	}


	public void borrar() {
		error.borrar();
		idSucSeleccionadaParaFiel = new Long(0);
		idSucursalDeFielSeleccionada = new HtmlSelectOneMenu();
		idSucursalDeFielSeleccionada.setValue(new Long(0));
		idEjercicioSeleccionado = new Long(0);
		idEjercicioSeleccionadoItem = new HtmlSelectOneMenu();
		idEjercicioSeleccionadoItem.setValue(new Long(0));
		tituloLargo = "Tarjeta Fiel - Asientos y Lotes";
		tituloCorto = "Listado de Asientos y Lotes";
		// borro las listas
		ejerciciosSelectItem = new ArrayList();
		asientos = new ArrayList();
		asientosDetalles = new ArrayList();
		lotes = new ArrayList();
		lotesDetalles = new ArrayList();
		paginador = new PaginaDeRegistros();
		listaDeModicadosEnString = "";
		listaDeEliminadosEnString = "";
		fechaCierreAsiento = new Date();
		fechaCierreLote = new Date();

		Calendar fecha = Calendar.getInstance();
		fechaCierreImportar = fecha.getTime();
		// fecha.add(Calendar.DAY_OF_MONTH, -1);
		fecha.add(Calendar.MONTH, -1);
		fechaInicioImportar = fecha.getTime();

		fechaInicioAsiento = new Date();
		fechaInicioLote = new Date();
		idCentroCostoSeleccionado = new Long(0);
		centroCostoSeleccionado = new HtmlSelectOneMenu();
		centroCostoSeleccionado.setValue(new Long(0));
		idCentroCostoSeleccionadoLote = new Long(0);
		centroCostoSeleccionadoLote = new HtmlSelectOneMenu();
		centroCostoSeleccionadoLote.setValue(new Long(0));
		idOrigenSeleccionado = new Long(0);
		origenSeleccionado = new HtmlSelectOneMenu();
		origenSeleccionado.setValue(new Long(0));
		idOrigenSeleccionadoLote = new Long(0);
		origenSeleccionadoLote = new HtmlSelectOneMenu();
		origenSeleccionadoLote.setValue(new Long(0));
		idOrigenSeleccionadoParaImportar = new Long(0);
		origenSeleccionadoParaImportar = new HtmlSelectOneMenu();
		origenSeleccionadoParaImportar.setValue(new Long(0));
		cuentaABuscarEnAsiento = "";
		conceptoABuscarEnAsiento = "";
		cuentaABuscarEnLote = "";
		conceptoABuscarEnLote = "";
		idAsientoABuscarEnAsiento = "";
		idAsientoABuscarEnLote = "";
		ejerActual = null;
		cuentaParaAsi = null;
		cuentaParaLot = null;
	}


	public String verLibroMayor() {
		Long idAsiento = new Long(Session.getRequestParameter("idAsientoALibro"));
		AsientoDetalle asien = new AsientoDetalle();
		WrapperAsientoDetalle asienD = null;
		Iterator iter = asientosDetalles.iterator();
		while (iter.hasNext()) {
			WrapperAsientoDetalle asienDeta = (WrapperAsientoDetalle) iter.next();
			if (asienDeta.getIdAsienDetal() == idAsiento.intValue()) {
				asien = asienDeta.getAsientoDetalle();
				asienD = asienDeta;
				break;
			}
		}
		LibroMayorBean bean = (LibroMayorBean) Session.getBean("LibroMayorBean");
		String sucursal = "";
		Iterator i = sucursalesFielAuxiliar.iterator();
		while (i.hasNext()) {
			SucursalFiel s = (SucursalFiel) i.next();
			if (s.getIdSucursal().intValue() == asien.getId().getIdEmpresa().intValue()) {
				sucursal = s.getNombre();
				break;
			}
		}
		String ejerci = "";
		Date fecIni = null;
		Date fecFin = null;
		Iterator e = ejercicios.iterator();
		while (e.hasNext()) {
			Ejercicio ej = (Ejercicio) e.next();
			if (ej.getIdEjercicio().intValue() == asien.getId().getIdEjercicio().intValue()) {
				ejerci = ej.getIdEjercicio().toString();
				fecIni = ej.getFechaInicio();
				fecFin = ej.getFechaCierre();
				break;
			}
		}

		bean.accederAVistaPopap(sucursal, ejerci, fecIni.toString(), fecFin.toString(), asienD.getDenominacion(), new Long(asienD.getAsientoDetalle()
				.getId().getIdEmpresa().intValue()),
				new Long(asienD.getAsientoDetalle().getId().getIdEjercicio().intValue()), fecIni, fecFin, asien.getNumeroImputa());
		return null;
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
		ejerciciosSelectItem.add(new SelectItem(new Long(0), "Seleccione un ejercicio"));
		Iterator i = ejercicios.iterator();
		while (i.hasNext()) {
			Ejercicio ej = (Ejercicio) i.next();
			ejerciciosSelectItem.add(new SelectItem(new Long(ej.getIdEjercicio().intValue()), "Ejercicio: " + ej.getIdEjercicio()
					+ " | Fecha Inicio: " + ej.getFechaInicio() + " | Fecha Cierre: " + ej.getFechaCierre()));
		}
		origenesItem = new ArrayList();
		origenesItem.add(new SelectItem(new Long(0), "Seleccione origen"));
		origenesItem.add(new SelectItem(new Long(1), "Proveedores"));
		origenesItem.add(new SelectItem(new Long(2), "Clientes/Comercios"));
		origenesItem.add(new SelectItem(new Long(3), "Fondos"));
		
		/*
		try {
			origenes = contabilidadService.getOrigenService().getOrigen(new Filtro());
			Iterator orig = origenesItem.iterator();
			while (orig.hasNext()) {
				Origen ori = (Origen) orig.next();
				origenesItem.add(new SelectItem(ori.getIdOrigen(), ori.getDescripcion()));
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		
		idSucSeleccionadaParaFiel = new Long(1);
		idSucursalDeFielSeleccionada.setValue(idSucSeleccionadaParaFiel);
		listarEjercicios();
		// Aqui conformamos la lista de codigos de cuenta con su titulo.
		try {
			List cuentas = contabilidadService.getPlanCuentaDosService().getPlanCuenta(new Filtro("uso", Filtro.LIKE, "I"));
			Iterator cuentasExistentes = cuentas.iterator();
			listaCuentas = "";
			while (cuentasExistentes.hasNext()) {
				PlanCuentaDos planCuenta = (PlanCuentaDos) cuentasExistentes.next();
				listaCuentas += planCuenta.getIdPlanCuenta() + ":" + planCuenta.getTitulo() + ":" + planCuenta.getCentroCosto() + ":";
			}
		} catch (PlanCuentaDosException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// aca se deberia traer el ejercicio por defecto
		return "lstAsientos";
	}


	public boolean validar() {
		// TODO Auto-generated method stub
		return false;
	}


	public String limpiarCamposAsientos() {
		error.borrar();
		cuentaABuscarEnAsiento = "";
		idCentroCostoSeleccionado = new Long(0);
		centroCostoSeleccionado.setValue(new Long(0));
		conceptoABuscarEnAsiento = "";
		idAsientoABuscarEnAsiento = "";
		fechaInicioAsiento = fechaInicioEjercicio;
		fechaCierreAsiento = fechaFinEjercicio;
		idOrigenSeleccionado = new Long(0);
		origenSeleccionado.setValue(new Long(0));
		importeAsiento = "";
		importeSoloEnHaberAsi = false;
		importeSoloEnDebeAsi = false;
		aproximadoHastaAsi = "";
		return null;
	}


	public String filtrarAsientos() {
		error.borrar();

		if (validarFiltrosAsiento()) {
			modificarFechasEjercicios = false;
			String consulta = "";
			boolean yaAgregeWhere = false;
			Filtro filtro = new Filtro();
			List asientosAuxi = new ArrayList();
			asientos.clear();
			idSucSeleccionadaParaFiel = (Long) idSucursalDeFielSeleccionada.getValue();
			idEjercicioSeleccionado = (Long) idEjercicioSeleccionadoItem.getValue();
			idOrigenSeleccionado = (Long) origenSeleccionado.getValue();
			idCentroCostoSeleccionado = (Long) centroCostoSeleccionado.getValue();
			if (idSucSeleccionadaParaFiel.longValue() != 0) {
				if (!yaAgregeWhere) {
					consulta += " WHERE ";
					yaAgregeWhere = true;
				} else {
					consulta += " AND ";
				}
				consulta = consulta + "c.c_empresa = " + idSucSeleccionadaParaFiel;
			}
			if (!yaAgregeWhere) {
				consulta += " WHERE ";
				yaAgregeWhere = true;
			} else {
				consulta += " AND ";
			}
			consulta = consulta + "c.c_ejercicio = " + idEjercicioSeleccionado;
			if (conceptoABuscarEnAsiento != null && conceptoABuscarEnAsiento.compareTo("") != 0) {
				if (!yaAgregeWhere) {
					consulta += " WHERE ";
					yaAgregeWhere = true;
				} else {
					consulta += " AND ";
				}
				consulta = consulta + "c.c_concepto LIKE " + "'" + conceptoABuscarEnAsiento + "%'";
			}
			if (idAsientoABuscarEnAsiento != null && idAsientoABuscarEnAsiento.compareTo("") != 0) {
				if (!yaAgregeWhere) {
					consulta += " WHERE ";
					yaAgregeWhere = true;
				} else {
					consulta += " AND ";
				}
				consulta = consulta + "c.c_asiento = " + new Long(idAsientoABuscarEnAsiento);
			}
			// if (idOrigenSeleccionado != null && !idOrigenSeleccionado.equals(new Long(0))) {
			// if (!yaAgregeWhere) {
			// consulta += " WHERE ";
			// yaAgregeWhere = true;
			// } else {
			// consulta += " AND ";
			// }
			// consulta = consulta + "c.c_origen = " + idOrigenSeleccionado;
			// }
			if (fechaInicioAsiento != null) {
				if (!yaAgregeWhere) {
					consulta += " WHERE ";
					yaAgregeWhere = true;
				} else {
					consulta += " AND ";
				}
				consulta = consulta + "c.c_fecha_contab >= " + Filtro.getTO_DATE(new Timestamp(fechaInicioAsiento.getTime()));
			}
			if (fechaCierreAsiento != null) {
				if (!yaAgregeWhere) {
					consulta += " WHERE ";
					yaAgregeWhere = true;
				} else {
					consulta += " AND ";
				}
				consulta = consulta + "c.c_fecha_contab <= " + Filtro.getTO_DATE(new Timestamp(fechaCierreAsiento.getTime()));
			}

			if (idCentroCostoSeleccionado != null && idCentroCostoSeleccionado.longValue() != 0) {
				if (!yaAgregeWhere) {
					consulta += " WHERE ";
					yaAgregeWhere = true;
				} else {
					consulta += " AND ";
				}
				consulta = consulta + " c.c_asiento IN (Select f.c_id_asiento from t_vis_cont_centro_costo_asiento f WHERE c_id_centro_costo = "
						+ idCentroCostoSeleccionado + ")";
			}
			if (cuentaABuscarEnAsiento != null && cuentaABuscarEnAsiento.compareTo("") != 0) {
				if (!yaAgregeWhere) {
					consulta += " WHERE ";
					yaAgregeWhere = true;
				} else {
					consulta += " AND ";
				}
				consulta = consulta + " c.c_asiento IN (Select f.c_asiento from t_cont_asientos_d f WHERE c_empresa = " + idSucSeleccionadaParaFiel
						+ " AND c_ejercicio = " + idEjercicioSeleccionado + " AND c_numero_imputa IN (" + cuentaABuscarEnAsiento + "))";
			}
			consulta += " Group by c.c_asiento, c.c_empresa, c.c_ejercicio, c.c_concepto, c.c_tipo_asiento, c.c_fecha_contab, c.c_hora_carga, c.c_fecha_carga ";
			if (importeAsiento != null && importeAsiento.compareTo("") != 0) {
				try {
					// el having para buscar el importe pedido
					int monto = Integer.valueOf(importeAsiento).intValue();
					int aprox = 0;
					if (aproximadoHastaAsi != null & aproximadoHastaAsi.compareTo("") != 0) {
						aprox = Integer.valueOf(aproximadoHastaAsi).intValue();
					}
					if (importeSoloEnDebeAsi || importeSoloEnHaberAsi) {
						if (importeSoloEnDebeAsi) {
							consulta += " Having SUM(DECODE(d.c_signo,'C',1,'D',-1)*d.c_importe) between " + (-monto - aprox) + " and "
									+ (-monto + aprox);
						} else {
							consulta += " Having SUM(DECODE(d.c_signo,'C',1,'D',-1)*d.c_importe) between " + (monto - aprox) + " and "
									+ (monto + aprox);
						}
					} else {
						consulta += " Having ABS(SUM(DECODE(d.c_signo,'C',1,'D',-1)*d.c_importe)) between " + (monto - aprox) + " and "
								+ (monto + aprox);
					}
				} catch (NumberFormatException e) {
					error.agregar("Los montos ingresados en los filtros no son correctos.");
				}

			}
			filtro.setConsultaAMano(consulta);

			try {
				asientosAuxi = contabilidadService.getAsientoService().getAsientoConsultaManual(filtro);
				paginador = new PaginaDeRegistros(50, asientosAuxi);
				Iterator i = paginador.getPrimeraPagina().iterator();
				while (i.hasNext()) {
					Asiento a = (Asiento) i.next();
					WrapperAsientoCabecera w = new WrapperAsientoCabecera(a);
					asientos.add(w);
				}
			} catch (AsientoException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}


	public String filtrarLotes() {
		error.borrar();
		if (validarFiltrosLote()) {
			String consulta = "";
			modificarFechasEjercicios = false;
			boolean yaAgregeWhere = false;
			Filtro filtro = new Filtro();
			List lotesAuxi = new ArrayList();
			lotes.clear();
			idSucSeleccionadaParaFiel = (Long) idSucursalDeFielSeleccionada.getValue();
			idEjercicioSeleccionado = (Long) idEjercicioSeleccionadoItem.getValue();
			idOrigenSeleccionadoLote = (Long) origenSeleccionadoLote.getValue();
			idCentroCostoSeleccionadoLote = (Long) centroCostoSeleccionadoLote.getValue();
			if (idSucSeleccionadaParaFiel.longValue() != 0) {
				if (!yaAgregeWhere) {
					consulta += " WHERE ";
					yaAgregeWhere = true;
				} else {
					consulta += " AND ";
				}
				consulta = consulta + "c.c_empresa = " + idSucSeleccionadaParaFiel;
			}
			if (!yaAgregeWhere) {
				consulta += " WHERE ";
				yaAgregeWhere = true;
			} else {
				consulta += " AND ";
			}
			consulta = consulta + "c.c_ejercicio = " + idEjercicioSeleccionado;
			if (conceptoABuscarEnLote != null && conceptoABuscarEnLote.compareTo("") != 0) {
				if (!yaAgregeWhere) {
					consulta += " WHERE ";
					yaAgregeWhere = true;
				} else {
					consulta += " AND ";
				}
				consulta = consulta + "c.c_concepto LIKE " + "'" + conceptoABuscarEnLote + "%'";
			}
			if (idAsientoABuscarEnLote != null && idAsientoABuscarEnLote.compareTo("") != 0) {
				if (!yaAgregeWhere) {
					consulta += " WHERE ";
					yaAgregeWhere = true;
				} else {
					consulta += " AND ";
				}
				consulta = consulta + "c.c_asiento = " + new Long(idAsientoABuscarEnLote);
			}
			if (idOrigenSeleccionadoLote != null && !idOrigenSeleccionadoLote.equals(new Long(0))) {
				if (!yaAgregeWhere) {
					consulta += " WHERE ";
					yaAgregeWhere = true;
				} else {
					consulta += " AND ";
				}
				consulta = consulta + "c.c_origen = " + idOrigenSeleccionadoLote;
			}
			if (fechaInicioLote != null) {
				if (!yaAgregeWhere) {
					consulta += " WHERE ";
					yaAgregeWhere = true;
				} else {
					consulta += " AND ";
				}
				consulta = consulta + "c.c_fecha_contab >= " + Filtro.getTO_DATE(new Timestamp(fechaInicioLote.getTime()));
			}
			if (fechaCierreLote != null) {
				if (!yaAgregeWhere) {
					consulta += " WHERE ";
					yaAgregeWhere = true;
				} else {
					consulta += " AND ";
				}
				consulta = consulta + "c.c_fecha_contab <= " + Filtro.getTO_DATE(new Timestamp(fechaCierreLote.getTime()));
			}

			if (idCentroCostoSeleccionadoLote != null && idCentroCostoSeleccionadoLote.longValue() != 0) {
				if (!yaAgregeWhere) {
					consulta += " WHERE ";
					yaAgregeWhere = true;
				} else {
					consulta += " AND ";
				}
				consulta = consulta + " c.c_asiento IN (Select f.c_id_asiento from t_vis_cont_centro_costo_asiento f WHERE c_id_centro_costo = "
						+ idCentroCostoSeleccionadoLote + ")";

			}
			if (cuentaABuscarEnLote != null && cuentaABuscarEnLote.compareTo("") != 0) {
				if (!yaAgregeWhere) {
					consulta += " WHERE ";
					yaAgregeWhere = true;
				} else {
					consulta += " AND ";
				}
				consulta = consulta + " c.c_asiento IN (Select f.c_asiento from t_cont_lote_d f WHERE c_empresa = " + idSucSeleccionadaParaFiel
						+ " AND c_ejercicio = " + idEjercicioSeleccionado + " AND c_numero_imputa IN (" + cuentaABuscarEnLote + "))";
			}
			consulta += " Group by c.c_asiento, c.c_empresa, c.c_ejercicio, c.c_concepto, c.c_tipo_asiento, c.c_fecha_contab, c.c_hora_carga, c.c_fecha_carga ";
			if (importeLote != null && importeLote.compareTo("") != 0) {
				try {
					// el having para buscar el importe pedido
					int monto = Integer.valueOf(importeLote).intValue();
					int aprox = 0;
					if (aproximadoHastaLot != null & aproximadoHastaLot.compareTo("") != 0) {
						aprox = Integer.valueOf(aproximadoHastaLot).intValue();
					}
					if (importeSoloEnDebeLot || importeSoloEnHaberLot) {
						if (importeSoloEnDebeLot) {
							consulta += " Having SUM(DECODE(d.c_signo,'C',1,'D',-1)*d.c_importe) between " + (-monto - aprox) + " and "
									+ (-monto + aprox);
						} else {
							consulta += " Having SUM(DECODE(d.c_signo,'C',1,'D',-1)*d.c_importe) between " + (monto - aprox) + " and "
									+ (monto + aprox);
						}
					} else {
						consulta += " Having ABS(SUM(DECODE(d.c_signo,'C',1,'D',-1)*d.c_importe)) between " + (monto - aprox) + " and "
								+ (monto + aprox);
					}
				} catch (NumberFormatException e) {
					error.agregar("Los montos ingresados en los filtros no son correctos.");
				}

			}
			filtro.setConsultaAMano(consulta);

			try {
				lotesAuxi = contabilidadService.getLoteService().getLoteConsultaManual(filtro);
				paginadorDeLotes = new PaginaDeRegistros(50, lotesAuxi);
				Iterator i = paginadorDeLotes.getPrimeraPagina().iterator();
				while (i.hasNext()) {
					Lote a = (Lote) i.next();
					WrapperLoteCabecera w = new WrapperLoteCabecera(a);
					lotes.add(w);
				}
			} catch (LoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}


	/**
	 * @id: 4933 Method: validarFecha Description: Valida la fecha desde y hasta
	 * @return
	 */
	public boolean validarFecha() {
		error.borrar();

		if (Validador.esNulo(fechaInicioImportar) || fechaInicioImportar.equals(new Date(0))
				|| Validador.esNulo(fechaCierreImportar) || fechaCierreImportar.equals(new Date(0))) {
			error.agregar(Error.COMPROBANTE_FECHA_REQUERIDA);
		} else {
			if (fechaInicioImportar.after(fechaCierreImportar)) {
				error.agregar("La fecha desde no puede ser mayor a la fecha hasta");
			}
		}
		return (error.cantidad() == 0) ? true : false;
	}


	public String realizarImportacion() {
		error.borrar();
		Format dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		String pFechaDesde = dateFormat.format(fechaInicioImportar);
		String pFechaHasta = dateFormat.format(fechaCierreImportar);
		Long pIdOperador = Session.getOperador().getCodigo();
		Long numeroEjercicio = idEjercicioSeleccionado;
		if (ejerActual.getEstado().compareTo("C") != 0) {
			if (validarFecha()) {
				if (Long.valueOf(origenSeleccionadoParaImportar.getValue().toString()).longValue() == 0) {
					error.agregar(Error.CONT_ORIGEN_REQUERIDO_PARA_IMPORTAR);
				} else {
					ImportacionAsientosBean bean = null;
					
					switch (Long.valueOf(origenSeleccionadoParaImportar.getValue().toString()).intValue()) {
						case 1:
							bean = (ImportacionAsientosBean) Session.getBean("ImportacionAsientosBean");
							// bean.inicializar(new Long(1), ejerActual.getId(), 0, ejerActual.getFechaInicio(), ejerActual.getFechaCierre());
							int cantidadImportados = bean.inicializarSegundoPlano(new Long(1), ejerActual.getId(), 0, fechaInicioImportar, fechaCierreImportar);
							error.agregar("Importación Finalizada. Cantidad de registros: " + cantidadImportados);
							break;
							
						case 2:
							String sql = "call sp_genera_asientos('" + pFechaDesde +"', '" + pFechaHasta + "', " + numeroEjercicio + ")";
							try{
								generalService.getGenericDao().ejecutarQuery(sql, null);
								error.agregar("Importación de Lote a Contabilidad Terminado");
							}
							catch(Exception e){
								log.info("Falló la ejecucion del sp");
								e.printStackTrace();
							}
							break;
						case 3:
							// traspaso de lote a contabilidad llamando a un SP
							try {
								Long registrosFondos = contabilidadService.getPasajeService().
								pasajeDeFondosAContabilidad(pIdOperador, pFechaDesde, pFechaHasta, numeroEjercicio);
								error.agregar("Importación de Lote a Contabilidad Terminado. Cantidad de registros: " + registrosFondos);
							} catch (Exception e) {
								error.agregar(e.getMessage());
								log.info(e.getMessage());
								e.printStackTrace();
							}
							break;

					default:
						break;
					}
				}
			}
		} else {
			error.agregar("No es posible importar asientos de otros módulos si el ejercicio esta cerrado.");
		}
		return null;
	}


	public boolean validarFiltrosAsiento() {
		error.borrar();
		if (fechaInicioAsiento.before(fechaInicioEjercicio) || fechaCierreAsiento.after(fechaFinEjercicio)) {
			error.agregar(Error.CONT_FILTRO_FECHAS_INCORRECTO);
		}
		return (error.cantidad() == 0) ? true : false;
	}


	public boolean validarFiltrosLote() {
		error.borrar();
		if (fechaInicioLote.before(fechaInicioEjercicio) || fechaCierreLote.after(fechaFinEjercicio)) {
			error.agregar(Error.CONT_FILTRO_FECHAS_INCORRECTO);
		}
		return (error.cantidad() == 0) ? true : false;
	}


	public String limpiarCamposLotes() {
		error.borrar();
		cuentaABuscarEnLote = "";
		idCentroCostoSeleccionadoLote = new Long(0);
		centroCostoSeleccionadoLote.setValue(new Long(0));
		conceptoABuscarEnLote = "";
		idAsientoABuscarEnLote = "";
		fechaInicioLote = fechaInicioEjercicio;
		fechaCierreLote = fechaFinEjercicio;
		idOrigenSeleccionadoLote = new Long(0);
		origenSeleccionadoLote.setValue(new Long(0));
		importeLote = "";
		importeSoloEnHaberLot = false;
		importeSoloEnDebeLot = false;
		aproximadoHastaLot = "";
		return null;
	}


	// Esto Es para cuando se habilite mas de una sucursal, que cuando seleccione cambie los ejercicios que hay en ella
	// /**
	// * Una vez seleccionada la sucursal rellena el select item de ejercicios
	// * disponibles eligiendolos de la lista de ejercicios existentes
	// */
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

	/**
	 * Una vez seleccionada la sucursal rellena el select item de ejercicios disponibles eligiendolos de la lista de ejercicios existentes
	 */
	public void listarEjercicios() {
		idSucSeleccionadaParaFiel = (Long) idSucursalDeFielSeleccionada.getValue();
		ejerciciosSelectItem.clear();
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
		listarAsientos();
	}


	public void listarAsientosYLotes() {
		idSucSeleccionadaParaFiel = (Long) idSucursalDeFielSeleccionada.getValue();
		idEjercicioSeleccionado = (Long) idEjercicioSeleccionadoItem.getValue();
		try {
			ejerActual = contabilidadService.getEjercicioService().leerEjercicio((Integer.valueOf(idEjercicioSeleccionado.intValue())));
			if (ejerActual != null) {

				fechaInicioEjercicio = ejerActual.getFechaInicio();
				fechaFinEjercicio = ejerActual.getFechaCierre();
				fechaInicioAsiento = new Date(fechaInicioEjercicio.getTime());
				fechaCierreAsiento = new Date(fechaFinEjercicio.getTime());
				fechaInicioLote = fechaInicioEjercicio;
				fechaCierreLote = fechaFinEjercicio;
			} else {
				log.info("el ejercicio es nulo");
			}
		} catch (EjercicioException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Filtro filtro = new Filtro();
		List asientosAuxi = new ArrayList();
		List lotesAuxi = new ArrayList();
		asientos.clear();
		lotes.clear();
		if (idSucSeleccionadaParaFiel.longValue() != 0) {
			filtro.agregarCampoOperValor("idEmpresa", Filtro.IGUAL, idSucSeleccionadaParaFiel);
		}
		filtro.agregarCampoOperValor("idEjercicio", Filtro.IGUAL, idEjercicioSeleccionado);
		try {
			asientosAuxi = contabilidadService.getAsientoService().getAsiento(filtro);
			paginador = new PaginaDeRegistros(50, asientosAuxi);
			Iterator i = paginador.getPrimeraPagina().iterator();
			while (i.hasNext()) {
				Asiento a = (Asiento) i.next();
				WrapperAsientoCabecera w = new WrapperAsientoCabecera(a);
				asientos.add(w);
			}
		} catch (AsientoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			lotesAuxi = contabilidadService.getLoteService().getLote(filtro);
			paginadorDeLotes = new PaginaDeRegistros(50, lotesAuxi);
			Iterator iLo = paginadorDeLotes.getPrimeraPagina().iterator();
			while (iLo.hasNext()) {
				Lote lo = (Lote) iLo.next();
				WrapperLoteCabecera w = new WrapperLoteCabecera(lo);
				lotes.add(w);
			}

		} catch (LoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	public void listarAsientos() {
		modificarFechasEjercicios = true;
		if (asientos != null)
			asientos = new ArrayList();
		if (lotes != null)
			lotes = new ArrayList();

		// debemos saber la sucursal y el ejercicio
		idSucSeleccionadaParaFiel = (Long) idSucursalDeFielSeleccionada.getValue();
		Iterator iter = ejercicios.iterator();
		while (iter.hasNext()) {
			Ejercicio ejer = (Ejercicio) iter.next();
			try {
				if (ejer.getActual().compareTo("S") == 0) {
					idEjercicioSeleccionado = new Long(ejer.getIdEjercicio().intValue());
					idEjercicioSeleccionadoItem.setValue(idEjercicioSeleccionado);

					break;
				}
			} catch (NullPointerException e) {
				// El ejercicio no fue cargado con un estado S o N. Deberia controlarse.
			}
		}
		try {
			ejerActual = contabilidadService.getEjercicioService().leerEjercicio((Integer.valueOf(idEjercicioSeleccionado.intValue())));
			if (ejerActual != null) {
				controlarCierreApertura();
				fechaInicioEjercicio = ejerActual.getFechaInicio();
				fechaFinEjercicio = ejerActual.getFechaCierre();
				fechaInicioAsiento = new Date(fechaInicioEjercicio.getTime());
				fechaCierreAsiento = new Date(fechaFinEjercicio.getTime());
				fechaInicioLote = fechaInicioEjercicio;
				fechaCierreLote = fechaFinEjercicio;
			} else {
			}
		} catch (EjercicioException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}


	public void listarAsientos(ValueChangeEvent event) {
		modificarFechasEjercicios = true;
		if (asientos != null)
			asientos = new ArrayList();
		if (lotes != null)
			lotes = new ArrayList();

		// debemos saber la sucursal y el ejercicio
		idSucSeleccionadaParaFiel = (Long) idSucursalDeFielSeleccionada.getValue();
		idEjercicioSeleccionado = (Long) idEjercicioSeleccionadoItem.getValue();
		try {
			ejerActual = contabilidadService.getEjercicioService().leerEjercicio((Integer.valueOf(idEjercicioSeleccionado.intValue())));
			if (ejerActual != null) {
				fechaInicioEjercicio = ejerActual.getFechaInicio();
				fechaFinEjercicio = ejerActual.getFechaCierre();
				fechaInicioAsiento = new Date(fechaInicioEjercicio.getTime());
				fechaCierreAsiento = new Date(fechaFinEjercicio.getTime());
				fechaInicioLote = fechaInicioEjercicio;
				fechaCierreLote = fechaFinEjercicio;
			} else {
				log.info("el ejercicio es nulo");
			}
		} catch (EjercicioException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		controlarCierreApertura();

	}


	public void controlarCierreApertura() {
		Filtro filtro = new Filtro();
		aperturaPermitida = true;
		cierrePermitido = true;
		filtro.agregarCampoOperValor("idEmpresa", Filtro.IGUAL, idSucSeleccionadaParaFiel);
		filtro.agregarCampoOperValor("idEjercicio", Filtro.IGUAL, idEjercicioSeleccionado);
		filtro.agregarCampoOperValor("idTipoAsiento", Filtro.IGUAL, new Integer(1));

		try {
			Long cantAsi = contabilidadService.getAsientoService().contarAsientos(filtro);
			Long cantLote = contabilidadService.getLoteService().contarLotes(filtro);
			if (!cantAsi.equals(0L) || !cantLote.equals(0L))
				aperturaPermitida = false;
		} catch (AsientoException e) {
			e.printStackTrace();
		} catch (LoteException e) {
			e.printStackTrace();
		}
		filtro.reset();
		filtro.agregarCampoOperValor("idEmpresa", Filtro.IGUAL, idSucSeleccionadaParaFiel);
		filtro.agregarCampoOperValor("idEjercicio", Filtro.IGUAL, idEjercicioSeleccionado);
		filtro.agregarCampoOperValor("idTipoAsiento", Filtro.IGUAL, new Integer(4));

		try {
			Long cantAsi = contabilidadService.getAsientoService().contarAsientos(filtro);
			Long cantLote = contabilidadService.getLoteService().contarLotes(filtro);
			if (!cantAsi.equals(0L) || !cantLote.equals(0L))
				cierrePermitido = false;
		} catch (AsientoException e) {
			e.printStackTrace();
		} catch (LoteException e) {
			e.printStackTrace();
		}
		filtro.reset();
		filtro.agregarCampoOperValor("idEmpresa", Filtro.IGUAL, idSucSeleccionadaParaFiel);
		filtro.agregarCampoOperValor("idEjercicio", Filtro.IGUAL, idEjercicioSeleccionado);
		filtro.agregarCampoOperValor("idTipoAsiento", Filtro.IGUAL, new Integer(5));

		try {
			Long cantAsi = contabilidadService.getAsientoService().contarAsientos(filtro);
			Long cantLote = contabilidadService.getLoteService().contarLotes(filtro);
			if (!cantAsi.equals(0L) || !cantLote.equals(0L))
				cierrePermitido = false;
		} catch (AsientoException e) {
			e.printStackTrace();
		} catch (LoteException e) {
			e.printStackTrace();
		}
	}


	public String buscarCuentaPopup() {
		PlanCuentaBean bean = (PlanCuentaBean) Session.getBean("PlanCuentaBean");
		bean.inicializaBusqueda(3);
		String path = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
		path += "/tarjetafiel/contabilidad/buscarPlanesDeCuenta.jsf";
		ejecutarJavaScript("popup('" + path + "',750,400), 'titlebar=no';");
		return null;
	}


	public String buscarCuentaPopupLote() {
		PlanCuentaBean bean = (PlanCuentaBean) Session.getBean("PlanCuentaBean");
		bean.inicializaBusqueda(4);
		String path = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
		path += "/tarjetafiel/contabilidad/buscarPlanesDeCuenta.jsf";
		ejecutarJavaScript("popup('" + path + "',750,400), 'titlebar=no';");
		return null;
	}


	public String primerRegistroAsiento() {
		asientos.clear();
		Iterator i = paginador.getPrimeraPagina().iterator();
		while (i.hasNext()) {
			Asiento a = (Asiento) i.next();
			WrapperAsientoCabecera w = new WrapperAsientoCabecera(a);
			asientos.add(w);
		}
		return null;
	}


	public String ultimoRegistroAsiento() {
		asientos.clear();
		Iterator i = paginador.getUltimaPagina().iterator();
		while (i.hasNext()) {
			Asiento a = (Asiento) i.next();
			WrapperAsientoCabecera w = new WrapperAsientoCabecera(a);
			asientos.add(w);
		}
		return null;
	}


	public String siguienteRegistroAsiento() {
		asientos.clear();
		Iterator i = paginador.getPaginaSiguiente().iterator();
		while (i.hasNext()) {
			Asiento a = (Asiento) i.next();
			WrapperAsientoCabecera w = new WrapperAsientoCabecera(a);
			asientos.add(w);
		}
		return null;
	}


	public String anteriorRegistroAsiento() {
		asientos.clear();
		Iterator i = paginador.getPaginaAnterior().iterator();
		while (i.hasNext()) {
			Asiento a = (Asiento) i.next();
			WrapperAsientoCabecera w = new WrapperAsientoCabecera(a);
			asientos.add(w);
		}
		return null;
	}


	public String primerRegistroLote() {
		lotes.clear();
		Iterator i = paginadorDeLotes.getPrimeraPagina().iterator();
		while (i.hasNext()) {
			Lote a = (Lote) i.next();
			WrapperLoteCabecera w = new WrapperLoteCabecera(a);
			lotes.add(w);
		}
		return null;
	}


	public String ultimoRegistroLote() {
		lotes.clear();
		Iterator i = paginadorDeLotes.getUltimaPagina().iterator();
		while (i.hasNext()) {
			Lote a = (Lote) i.next();
			WrapperLoteCabecera w = new WrapperLoteCabecera(a);
			lotes.add(w);
		}
		return null;
	}


	public String siguienteRegistroLote() {
		lotes.clear();
		Iterator i = paginadorDeLotes.getPaginaSiguiente().iterator();
		while (i.hasNext()) {
			Lote a = (Lote) i.next();
			WrapperLoteCabecera w = new WrapperLoteCabecera(a);
			lotes.add(w);
		}
		return null;
	}


	public String anteriorRegistroLote() {
		lotes.clear();
		Iterator i = paginadorDeLotes.getPaginaAnterior().iterator();
		while (i.hasNext()) {
			Lote a = (Lote) i.next();
			WrapperLoteCabecera w = new WrapperLoteCabecera(a);
			lotes.add(w);
		}
		return null;
	}


	public void cambiarPagina(ValueChangeEvent e) {
		paginaDeAsiento();
	}


	public void cambiarPaginaLote(ValueChangeEvent e) {
		paginaDeLote();
	}


	public String paginaDeLote() {
		lotes.clear();
		Iterator i = paginadorDeLotes.getPagina(
				((Long) paginadorDeLotes.getPagSeleccionada().getValue()).intValue()).iterator();
		while (i.hasNext()) {
			Lote a = (Lote) i.next();
			WrapperLoteCabecera w = new WrapperLoteCabecera(a);
			lotes.add(w);
		}
		return null;
	}


	public String paginaDeAsiento() {
		asientos.clear();
		Iterator i = paginador.getPagina(
				((Long) paginador.getPagSeleccionada().getValue()).intValue()).iterator();
		while (i.hasNext()) {
			Asiento a = (Asiento) i.next();
			WrapperAsientoCabecera w = new WrapperAsientoCabecera(a);
			asientos.add(w);
		}
		return null;
	}


	public String setTrueEnLote() {
		enLote = true;
		return null;
	}


	public String setFalseEnLote() {
		enLote = false;
		return null;
	}


	public String agregarNuevoDetalle() {
		cuentaNuevaParaDetalle = null;
		LoteDetalle loteDetalle = new LoteDetalle();
		loteDetalle.setNumeroImputa(new Long(numeroImputaAAgregar));
		loteDetalle.setIdPrincipal(loteDetallado.getIdAsiento());
		wrapLot = new WrapperLoteDetalle(loteDetalle);
		wrapLot.setSoyNuevo(true);
		if (wrapLot.getDenominacion().equals("")) {
			PlanCuentaBean bean = (PlanCuentaBean) Session.getBean("PlanCuentaBean");
			bean.inicializaBusqueda(PlanCuentaBean.DETALLE_DE_LOTE);
			String path = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
			path += "/tarjetafiel/contabilidad/buscarPlanesDeCuenta.jsf";
			ejecutarJavaScript("popup('" + path + "',800,600), 'titlebar=no';");
		} else {
			lotesDetalles.add(wrapLot);
		}
		return null;
	}


	public String realizarMigracion() {
		error.borrar();
		if (ejerActual.getEstado().compareTo("C") != 0) {
			int asientosListados = lotes.size();
			Iterator iter = lotes.iterator();
			while (iter.hasNext()) {
				WrapperLoteCabecera lot = (WrapperLoteCabecera) iter.next();
				if (lot.estado) {
					iter.remove();
					if (lot.getLote().getFechaContab().compareTo(ejerActual.getFechaInicio()) >= 0
							&& lot.getLote().getFechaContab().compareTo(ejerActual.getFechaCierre()) <= 0) {
						lot.migrar();
					} else {
						error.agregar("El lote " + lot.getLote().getIdAsiento() + " se encuentra fuera de la fechas válidas del ejercicio");
					}
				}
			}
			if (asientosListados > 0) {
				listarAsientosYLotes();
			}
		} else {
			error.agregar("No se pueden asentar lotes en un ejercicio ya cerrado.");
		}
		return null;
	}


	public void recargarYCerrarPopup(ActionEvent event) {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		String javaScriptText = "window.opener.recargar(); window.close();";
		ejecutarJavaScript(javaScriptText);
	}


	public String cancelarDetalles() {
		habilitarCargaPopup();
		return null;
	}


	public String calcularBalanceoLote() {
		double totalHaber = 0;
		double totalDebe = 0;
		Iterator lo = lotesDetalles.iterator();
		while (lo.hasNext()) {
			WrapperLoteDetalle asi = (WrapperLoteDetalle) lo.next();
			if (asi.getDebe().compareTo("") != 0) {
				totalDebe += (Double.valueOf(asi.getDebe())).doubleValue();
			}
			if (asi.getHaber().compareTo("") != 0) {
				totalHaber += (Double.valueOf(asi.getHaber())).doubleValue();
			}
		}
		balanceLoteDebe = (new Double(totalDebe)).toString();
		balanceLoteHaber = (new Double(totalHaber)).toString();
		return null;
	}


	public String calcularBalanceoAsiento() {
		if (!asientosDetalles.isEmpty()) {
			double totalHaber = 0;
			double totalDebe = 0;
			Iterator as = asientosDetalles.iterator();
			while (as.hasNext()) {
				WrapperAsientoDetalle asi = (WrapperAsientoDetalle) as.next();
				if (asi.getDebe().compareTo("") != 0) {
					totalDebe += (Double.valueOf(asi.getDebe())).doubleValue();
				}
				if (asi.getHaber().compareTo("") != 0) {
					totalHaber += (Double.valueOf(asi.getHaber())).doubleValue();
				}
			}
			balanceAsientoDebe = (new Double(totalDebe)).toString();
			balanceAsientoHaber = (new Double(totalHaber)).toString();
		}

		return null;
	}


	public String agregarLote() {
		error.borrar();
		if (ejerActual.getEstado().compareTo("C") != 0) {
			agregarL = true;
			asientosDetalles.clear();
			if (Long.valueOf(idSucursalDeFielSeleccionada.getValue().toString()).longValue() == 0
					|| Long.valueOf(idEjercicioSeleccionadoItem.getValue().toString()).longValue() == 0) {
				error.agregar(Error.CONT_PARA_AGREGAR_LOTE_SELECCION_EJERCICIOYEMPRESA);
			} else {
				lotesDetalles = new ArrayList();
				for (int i = 0; i < 100; i++) {
					WrapperLoteDetalle wrapDet = new WrapperLoteDetalle();
					wrapDet.setLoteDetalle(new LoteDetalle());
					// wrapDet.setSoyNuevo(true);
					wrapDet.setDenominacion("");
					lotesDetalles.add(wrapDet);
				}
				popupAltaAsiento = new PopupAltaAsiento(idEjercicioSeleccionado, idSucSeleccionadaParaFiel);
				String path = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
				path += "/tarjetafiel/contabilidad/altaAsientoPopup.jsf";
				ejecutarJavaScript("popup('" + path + "',900,900), 'titlebar=no';");
			}
			return null;
		} else {
			error.agregar("No se le pueden agregar lotes a un ejercicio cerrado.");
			return null;
		}
	}


	public boolean getHayAsientosDetalles() {
		return asientosDetalles.isEmpty();
	}


	public boolean getHayLotesDetalles() {
		return lotesDetalles.isEmpty();
	}


	/**
	 * lista los renglones de lote
	 */
	public String verDetallesLote() {
		error.borrar();
		agregarL = true;
		asientosDetalles.clear();
		int numeroMaximoCuentas = 0;
		enLote = true;
		lotesDetalles = new ArrayList();
		Long idLoteElegido = new Long(Session.getRequestParameter("idLoteAVer"));
		Iterator iter = lotes.iterator();
		while (iter.hasNext()) {
			WrapperLoteCabecera asienCabe = (WrapperLoteCabecera) iter.next();
			if (asienCabe.getIdLote() == idLoteElegido.intValue()) {
				loteDetallado = asienCabe.getLote();
				break;
			}
		}
		Filtro filtro = new Filtro();
		filtro.setConsultaAMano("Where id.idAsiento = " + loteDetallado.getIdAsiento() + " and id.idEjercicio = " + loteDetallado.getIdEjercicio()
				+ " and id.idEmpresa = " + loteDetallado.getIdEmpresa());
		try {
			List lot = contabilidadService.getLoteDetalleService().getLoteDetalleConsultaManual(filtro);
			Iterator it = lot.iterator();
			while (it.hasNext()) {
				LoteDetalle lotDeta = (LoteDetalle) it.next();
				WrapperLoteDetalle wraLotDeta = new WrapperLoteDetalle(lotDeta);
				numeroMaximoCuentas++;
				lotesDetalles.add(wraLotDeta);
			}
			calcularBalanceoLote();
		} catch (LoteDetalleException e) {
			e.printStackTrace();
		}
		if (Long.valueOf(idSucursalDeFielSeleccionada.getValue().toString()).longValue() == 0
				|| Long.valueOf(idEjercicioSeleccionadoItem.getValue().toString()).longValue() == 0) {
			error.agregar(Error.CONT_PARA_AGREGAR_LOTE_SELECCION_EJERCICIOYEMPRESA);
		} else {
			for (int i = numeroMaximoCuentas; i < 100; i++) {
				WrapperLoteDetalle wrapDet = new WrapperLoteDetalle();
				wrapDet.setLoteDetalle(new LoteDetalle());
				wrapDet.setDenominacion("");
				lotesDetalles.add(wrapDet);
			}
			popupAltaAsiento = new PopupAltaAsiento(loteDetallado);
			String path = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
			path += "/tarjetafiel/contabilidad/altaAsientoPopup.jsf";
			ejecutarJavaScript("popup('" + path + "',900,900), 'titlebar=no';");
		}
		return null;
	}


	public boolean getHayAsientos() {
		return asientos.isEmpty();
	}


	/**
	 * lista los renglones de Asientos
	 */
	public String verDetallesAsiento() {
		error.borrar();
		agregarL = false;
		lotesDetalles.clear();
		int numeroMaximoCuentas = 0;
		enLote = false;
		asientosDetalles = new ArrayList();
		Long idAsientoElegido = new Long(Session.getRequestParameter("idAsientoAVer"));
		Iterator iter = asientos.iterator();
		while (iter.hasNext()) {
			WrapperAsientoCabecera asienCabe = (WrapperAsientoCabecera) iter.next();
			if (asienCabe.getIdAsien() == idAsientoElegido.intValue()) {
				asientoDetallado = asienCabe.getAsiento();
				break;
			}
		}
		Filtro filtro = new Filtro();
		filtro.setConsultaAMano("Where id.idAsiento = " + asientoDetallado.getIdAsiento() + " and id.idEjercicio = "
				+ asientoDetallado.getIdEjercicio() + " and id.idEmpresa = " + asientoDetallado.getIdEmpresa());
		try {
			List asi = contabilidadService.getAsientoDetalleService().getAsientoDetalleConsultaManual(filtro);
			Iterator it = asi.iterator();
			while (it.hasNext()) {
				AsientoDetalle asiDeta = (AsientoDetalle) it.next();
				numeroMaximoCuentas++;
				WrapperAsientoDetalle wraAsiDeta = new WrapperAsientoDetalle(asiDeta);
				asientosDetalles.add(wraAsiDeta);
			}
			calcularBalanceoAsiento();
		} catch (AsientoDetalleException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (Long.valueOf(idSucursalDeFielSeleccionada.getValue().toString()).longValue() == 0
				|| Long.valueOf(idEjercicioSeleccionadoItem.getValue().toString()).longValue() == 0) {
			error.agregar(Error.CONT_PARA_AGREGAR_LOTE_SELECCION_EJERCICIOYEMPRESA);
		} else {
			for (int i = numeroMaximoCuentas; i < 100; i++) {
				WrapperAsientoDetalle wrapDet = new WrapperAsientoDetalle();
				wrapDet.setAsientoDetalle(new AsientoDetalle());
				wrapDet.setDenominacion("");
				asientosDetalles.add(wrapDet);
			}
			popupAltaAsiento = new PopupAltaAsiento(asientoDetallado);
			String path = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
			path += "/tarjetafiel/contabilidad/altaAsientoPopup.jsf";
			ejecutarJavaScript("popup('" + path + "',900,900), 'titlebar=no';");
		}
		return null;
	}


	public String habilitarCargaPopup() {
		return null;
	}


	public String cancelar() {
		error.borrar();
		borrar();
		return "inicio";
	}


	public static int getNumeroAsientoTabla() {
		return numeroAsientoTabla;
	}


	public static void setNumeroAsientoTabla(int numeroAsientoTabla) {
		AsientosBean.numeroAsientoTabla = numeroAsientoTabla;
	}


	public static int getNumeroLoteDetalleRenglonTabla() {
		return numeroLoteDetalleRenglonTabla;
	}


	public static void setNumeroLoteDetalleRenglonTabla(
			int numeroLoteDetalleRenglonTabla) {
		AsientosBean.numeroLoteDetalleRenglonTabla = numeroLoteDetalleRenglonTabla;
	}


	public boolean isCampoEditableEnAsiento() {
		return campoEditableEnAsiento;
	}


	public void setCampoEditableEnAsiento(boolean campoEditableEnAsiento) {
		this.campoEditableEnAsiento = campoEditableEnAsiento;
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


	public boolean isEnLote() {
		return enLote;
	}


	public void setEnLote(boolean enLote) {
		this.enLote = enLote;
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


	public static int getNumeroAsientoDetalleTabla() {
		return numeroAsientoDetalleTabla;
	}


	public static void setNumeroAsientoDetalleTabla(
			int numeroAsientoDetalleTabla) {
		AsientosBean.numeroAsientoDetalleTabla = numeroAsientoDetalleTabla;
	}


	public static int getNumeroLoteRenglonTabla() {
		return numeroLoteRenglonTabla;
	}


	public static void setNumeroLoteRenglonTabla(int numeroLoteRenglonTabla) {
		AsientosBean.numeroLoteRenglonTabla = numeroLoteRenglonTabla;
	}

	public class WrapperAsientoCabecera {

		public Asiento asiento;

		public int idAsien;

		public boolean origen;


		public String rastrearDocumentoOrigen() {
			// rastreoDocumentoOrigen
			return null;
		}


		public String mostrarDocAdjuntos() {
			DocumentoAdjuntoBean bean = (DocumentoAdjuntoBean) Session.getBean("DocumentoAdjuntoBean");
			bean.inicializar(DocumentoAdjuntoBean.CONTABILIDAD_ASIENTOS, this.asiento);
			String path = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
			path += "/tarjetafiel/general/varios/documentoAdjuntoPopup.jsf";
			ejecutarJavaScript("popup('" + path + "',900,900), 'titlebar=no';");
			return null;
		}


		public WrapperAsientoCabecera(Asiento asiento) {
			this.asiento = asiento;
			idAsien = ++numeroAsientoTabla;
			// if (asiento.getIdNroComprobante() != null && asiento.getIdNroComprobante().longValue()>0) {
			// origen = true;
			// } else {
			// origen = false;
			// }
		}


		public String eliminarAsiento() {
			error.borrar();
			Long idAsientoElegido = new Long(idAsien);
			Iterator iter = asientos.iterator();
			while (iter.hasNext()) {
				WrapperAsientoCabecera asienCabe = (WrapperAsientoCabecera) iter.next();
				if (asienCabe.getIdAsien() == idAsientoElegido.intValue()) {
					asientoDetallado = asienCabe.getAsiento();
					break;
				}
			}

			try {
				contabilidadService.getAsientoDetalleService().borrarTodosLosDetallesDelAsiento(
						new Long(asientoDetallado.getIdEjercicio().intValue()), new Long(asientoDetallado.getIdEmpresa().intValue()),
						asientoDetallado.getIdAsiento());
				contabilidadService.getAsientoService().borrarAsiento(asientoDetallado.getIdAsiento());
				listarAsientosYLotes();
			} catch (AsientoException e) {
				log.info("NO se pudo borrar el asiento");
				e.printStackTrace();
			}
			return null;
		}


		public Asiento getAsiento() {
			return asiento;
		}


		public void setAsiento(Asiento asiento) {
			this.asiento = asiento;
		}


		public int getIdAsien() {
			return idAsien;
		}


		public void setIdAsien(int idAsien) {
			this.idAsien = idAsien;
		}


		public boolean isOrigen() {
			return origen;
		}


		public void setOrigen(boolean origen) {
			this.origen = origen;
		}

	}

	public class WrapperAsientoDetalle {

		private AsientoDetalle asientoDetalle;

		private int idAsienDetal;

		private String denominacion;

		private String debe = "";

		private String haber = "";

		private String centroCosto = "";

		private Long idCentroCostoSeleccionado;

		private boolean soyNuevo = false;

		private boolean seBorra = false;

		private List listaCentroCosto;


		public WrapperAsientoDetalle() {
			soyNuevo = true;
			idAsienDetal = ++numeroAsientoDetalleTabla;
			crearListaCentroCostos();
		}


		public void crearListaCentroCostos() {
			listaCentroCosto = new ArrayList();
			Iterator iter = centroCostosDisponibles.iterator();
			while (iter.hasNext()) {
				CentroCostos cenCos = (CentroCostos) iter.next();
				WrapperCenCosAsiento aux = new WrapperCenCosAsiento(cenCos, new Long(0), null);
				listaCentroCosto.add(aux);
			}
		}


		public void llenarListaCentroCostos() {
			listaCentroCosto = new ArrayList();
			List listaYaAgregados = new ArrayList(); // en esta lista guardo los id de los centro de costos que tienene algun importe distinto de
														// cero, para no crearlos de nuevo ya que los leo de la base.
			Filtro filtro = new Filtro();
			filtro.agregarCampoOperValor("idAsiento", Filtro.IGUAL, new Long(asientoDetalle.getId().getIdAsiento().intValue()));
			filtro.agregarCampoOperValor("idRenglon", Filtro.IGUAL, new Long(asientoDetalle.getId().getRenglon().intValue()));
			// filtro.agregarCampoOperValor("isLote", Filtro.LIKE, "N");
			log.info(filtro.getSQL());
			try {
				List listaCCA = contabilidadService.getCentroCostoAsientoService().getCentroCostoAsiento(filtro);
				if (!listaCCA.isEmpty()) {
					Iterator iter = listaCCA.iterator();
					while (iter.hasNext()) {
						CentroCostoAsiento cca = (CentroCostoAsiento) iter.next();
						cca.getCentroCostos().getDescripcion();
						listaYaAgregados.add(cca.getCentroCostos().getIdCentroCostos());
						WrapperCenCosAsiento aux = new WrapperCenCosAsiento(cca.getCentroCostos(), cca.getIdImporte(), cca.getIdCentroCostoAsiento());
						listaCentroCosto.add(aux);
					}
				}
			} catch (CentroCostoAsientoException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			Iterator iter = centroCostosDisponibles.iterator();
			while (iter.hasNext()) {
				CentroCostos cenCos = (CentroCostos) iter.next();
				if (!listaYaAgregados.contains(cenCos.getIdCentroCostos())) {
					WrapperCenCosAsiento aux = new WrapperCenCosAsiento(cenCos, new Long(0), null);
					listaCentroCosto.add(aux);
				}
			}
		}


		public WrapperAsientoDetalle(AsientoDetalle asientoDetalle) {
			this.asientoDetalle = asientoDetalle;
			idAsienDetal = ++numeroAsientoDetalleTabla;
			Iterator it = cuentasDisponibles.iterator();
			soyNuevo = false;
			while (it.hasNext()) {
				PlanCuentaDos plan = (PlanCuentaDos) it.next();
				if (plan.getIdPlanCuenta().equals(asientoDetalle.getNumeroImputa())) {
					denominacion = plan.getTitulo();
					break;
				}
			}
			if (asientoDetalle.getSigno() != null) {
				if (asientoDetalle.getSigno().compareTo("D") == 0) {
					debe = String.valueOf(asientoDetalle.getImporte());
				} else {
					haber = String.valueOf(asientoDetalle.getImporte());
				}
			}
			llenarListaCentroCostos();
		}


		public AsientoDetalle getAsientoDetalle() {
			return asientoDetalle;
		}


		public void setAsientoDetalle(AsientoDetalle asientoDetalle) {
			this.asientoDetalle = asientoDetalle;
		}


		public int getIdAsienDetal() {
			return idAsienDetal;
		}


		public void setIdAsienDetal(int idAsienDetal) {
			this.idAsienDetal = idAsienDetal;
		}


		public String getDenominacion() {
			return denominacion;
		}


		public void setDenominacion(String denominacion) {
			this.denominacion = denominacion;
		}


		public String getDebe() {
			return debe;
		}


		public void setDebe(String debe) {
			this.debe = debe;
		}


		public String getHaber() {
			return haber;
		}


		public void setHaber(String haber) {
			this.haber = haber;
		}


		public String getCentroCosto() {
			return centroCosto;
		}


		public void setCentroCosto(String centroCosto) {
			this.centroCosto = centroCosto;
		}


		public Long getIdCentroCostoSeleccionado() {
			return idCentroCostoSeleccionado;
		}


		public void setIdCentroCostoSeleccionado(Long idCentroCostoSeleccionado) {
			this.idCentroCostoSeleccionado = idCentroCostoSeleccionado;
		}


		public List getListaCentroCosto() {
			return listaCentroCosto;
		}


		public void setListaCentroCosto(List listaCentroCosto) {
			this.listaCentroCosto = listaCentroCosto;
		}


		public boolean isSeBorra() {
			return seBorra;
		}


		public void setSeBorra(boolean seBorra) {
			this.seBorra = seBorra;
		}

	}

	public class WrapperLoteCabecera {

		private Lote lote;

		private int idLote;

		private boolean estado;

		private boolean origen;


		public WrapperLoteCabecera(Lote lote) {
			this.lote = lote;
			idLote = ++numeroLoteRenglonTabla;
			estado = false;
			// if (lote.getIdOrigen() != null) {
			// origen = true;
			// } else {
			// origen = false;
			// }
		}


		public String mostrarDocAdjuntos() {
			DocumentoAdjuntoBean bean = (DocumentoAdjuntoBean) Session.getBean("DocumentoAdjuntoBean");
			bean.inicializar(DocumentoAdjuntoBean.CONTABILIDAD_LOTES, this.lote);
			String path = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
			path += "/tarjetafiel/general/varios/documentoAdjuntoPopup.jsf";
			ejecutarJavaScript("popup('" + path + "',900,900), 'titlebar=no';");
			return null;
		}


		public String eliminarLote() {
			error.borrar();
			lotesDetalles.clear();
			Long idLoteElegido = new Long(idLote);
			Iterator iter = lotes.iterator();
			while (iter.hasNext()) {
				WrapperLoteCabecera asienCabe = (WrapperLoteCabecera) iter.next();
				if (asienCabe.getIdLote() == idLoteElegido.intValue()) {
					loteDetallado = asienCabe.getLote();
					break;
				}
			}
			try {
				contabilidadService.getLoteDetalleService().borrarTodosLosDetallesDelLote(new Long(loteDetallado.getIdEjercicio().intValue()),
						new Long(loteDetallado.getIdEmpresa().intValue()), loteDetallado.getIdAsiento());
				contabilidadService.getLoteService().borrarLote(loteDetallado.getIdAsiento());
				listarAsientosYLotes();
			} catch (LoteException e) {
				log.info("No se pudo borrar el lote");
				e.printStackTrace();
			}
			return null;
		}


		public String rastrearDocumentoOrigen() {
			// rastreoDocumentoOrigen
			return null;
		}


		public Lote getLote() {
			return lote;
		}


		public void setLote(Lote lote) {
			this.lote = lote;
		}


		public int getIdLote() {
			return idLote;
		}


		public void setIdLote(int idLote) {
			this.idLote = idLote;
		}


		public String migrar() {
			// long debe =
			// contabilidadService.getLoteDetalleService().getSumaDelTotal(Long.valueOf(this.getLote().getIdEjercicio().longValue()),Long.valueOf(this.getLote().getIdEmpresa().longValue()),this.getLote().getIdAsiento(),
			// "D");
			//
			// long haber =
			// contabilidadService.getLoteDetalleService().getSumaDelTotal(Long.valueOf(this.getLote().getIdEjercicio().longValue()),Long.valueOf(this.getLote().getIdEmpresa().longValue()),this.getLote().getIdAsiento(),
			// "C");

			// if (debe - haber == 0) {
			long debeMenosHaber = contabilidadService.getLoteDetalleService().getSumaDelTotal(
					Long.valueOf(this.getLote().getIdEjercicio().longValue()), Long.valueOf(this.getLote().getIdEmpresa().longValue()),
					this.getLote().getIdAsiento());
			if (debeMenosHaber == 0) {
				try {
					contabilidadService.getLoteService().asentarLote(Long.valueOf(this.getLote().getIdEjercicio().longValue()),
							Long.valueOf(this.getLote().getIdEmpresa().longValue()), this.getLote().getIdAsiento());
				} catch (LoteException e) {
					log.info("Error en el pasaje de lote a asiento");
					e.printStackTrace();
				}
			} else {
				error.agregar("El lote " + this.getLote().getIdAsiento() + " no tiene su debe y haber balanceado.");
			}
			return null;
		}


		public boolean isEstado() {
			return estado;
		}


		public void setEstado(boolean estado) {
			this.estado = estado;
		}


		public boolean isOrigen() {
			return origen;
		}


		public void setOrigen(boolean origen) {
			this.origen = origen;
		}

	}


	public String eliminarDetalleLote() {
		// para borrar detalles
		return null;
	}

	public class WrapperCenCosAsiento {

		private Long idCentroCostoAsiento;
		private CentroCostos centro;
		private String descripcion;
		private Long importe;


		public WrapperCenCosAsiento(CentroCostos centro, Long importe, Long idCentroCostoAsiento) {
			this.centro = centro;
			this.importe = importe;
			this.idCentroCostoAsiento = idCentroCostoAsiento;
		}


		public CentroCostos getCentro() {
			return centro;
		}


		public void setCentro(CentroCostos centro) {
			this.centro = centro;
		}


		public Long getImporte() {
			return importe;
		}


		public void setImporte(Long importe) {
			this.importe = importe;
		}


		public String getDescripcion() {
			return centro.getDescripcion();
		}


		public void setDescripcion(String descripcion) {
			this.descripcion = descripcion;
		}


		public Long getIdCentroCostoAsiento() {
			return idCentroCostoAsiento;
		}


		public void setIdCentroCostoAsiento(Long idCentroCostoAsiento) {
			this.idCentroCostoAsiento = idCentroCostoAsiento;
		}

	}

	public class WrapperLoteDetalle {

		private LoteDetalle loteDetalle;

		private int idLoteDetal;

		private String denominacion = "";

		private String debe = "";

		private String haber = "";

		private String centroCosto = "";

		private Long idCentroCostoSeleccionado;

		private boolean meEditaron = false;

		private boolean soyNuevo = false;

		private boolean seBorra = false;

		private List listaCentroCosto;


		public WrapperLoteDetalle() {
			soyNuevo = true;
			idLoteDetal = ++numeroLoteDetalleRenglonTabla;
			crearListaCentroCostos();
		}


		public void crearListaCentroCostos() {
			listaCentroCosto = new ArrayList();
			Iterator iter = centroCostosDisponibles.iterator();
			while (iter.hasNext()) {
				CentroCostos cenCos = (CentroCostos) iter.next();
				WrapperCenCosAsiento aux = new WrapperCenCosAsiento(cenCos, new Long(0), null);
				listaCentroCosto.add(aux);
			}
		}


		public void llenarListaCentroCostos() {
			listaCentroCosto = new ArrayList();
			List listaYaAgregados = new ArrayList(); // en esta lista guardo los id de los centro de costos que tienene algun importe distinto de
														// cero, para no crearlos de nuevo ya que los leo de la base.
			Filtro filtro = new Filtro();
			filtro.agregarCampoOperValor("idAsiento", Filtro.IGUAL, new Long(loteDetalle.getId().getIdAsiento().intValue()));
			filtro.agregarCampoOperValor("idRenglon", Filtro.IGUAL, new Long(loteDetalle.getId().getRenglon().intValue()));
			// filtro.agregarCampoOperValor("isLote", Filtro.LIKE, "S");
			log.info(filtro.getSQL());
			try {
				List listaCCA = contabilidadService.getCentroCostoAsientoService().getCentroCostoAsiento(filtro);
				log.info("Tratamos de recuperar las cuentas del asiento");
				log.info("Tenemos : " + listaCCA.size());
				if (!listaCCA.isEmpty()) {
					Iterator iter = listaCCA.iterator();
					while (iter.hasNext()) {
						CentroCostoAsiento cca = (CentroCostoAsiento) iter.next();
						cca.getCentroCostos().getDescripcion();
						listaYaAgregados.add(cca.getCentroCostos().getIdCentroCostos());
						WrapperCenCosAsiento aux = new WrapperCenCosAsiento(cca.getCentroCostos(), cca.getIdImporte(), cca.getIdCentroCostoAsiento());
						listaCentroCosto.add(aux);
					}
				}
			} catch (CentroCostoAsientoException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			Iterator iter = centroCostosDisponibles.iterator();
			while (iter.hasNext()) {
				CentroCostos cenCos = (CentroCostos) iter.next();
				if (!listaYaAgregados.contains(cenCos.getIdCentroCostos())) {
					WrapperCenCosAsiento aux = new WrapperCenCosAsiento(cenCos, new Long(0), null);
					listaCentroCosto.add(aux);
				}
			}
		}


		public WrapperLoteDetalle(LoteDetalle loteDetalle) {
			this.loteDetalle = loteDetalle;
			idLoteDetal = ++numeroLoteDetalleRenglonTabla;
			Iterator it = cuentasDisponibles.iterator();
			soyNuevo = false;
			while (it.hasNext()) {
				PlanCuentaDos plan = (PlanCuentaDos) it.next();
				if (plan.getIdPlanCuenta().equals(loteDetalle.getNumeroImputa())) {
					denominacion = plan.getTitulo();
					break;
				}
			}
			if (loteDetalle.getSigno() != null) {
				if (loteDetalle.getSigno().compareTo("D") == 0) {
					debe = String.valueOf(loteDetalle.getImporte());
				} else {
					haber = String.valueOf(loteDetalle.getImporte());
				}
			}
			llenarListaCentroCostos();
		}


		public void agregarAListaModicados() {
			log.info("Agrego un objeto a la lista de modificados si es que no esta");
		}


		public LoteDetalle getLoteDetalle() {
			return loteDetalle;
		}


		public void setLoteDetalle(LoteDetalle loteDetalle) {
			this.loteDetalle = loteDetalle;
		}


		public int getIdLoteDetal() {
			return idLoteDetal;
		}


		public void setIdLoteDetal(int idLoteDetal) {
			this.idLoteDetal = idLoteDetal;
		}


		public String getDenominacion() {
			return denominacion;
		}


		public void setDenominacion(String denominacion) {
			this.denominacion = denominacion;
		}


		public String getDebe() {
			return debe;
		}


		public void setDebe(String debe) {
			this.debe = debe;
		}


		public String getHaber() {
			return haber;
		}


		public void setHaber(String haber) {
			this.haber = haber;
		}


		public String getCentroCosto() {
			return centroCosto;
		}


		public void setCentroCosto(String centroCosto) {
			this.centroCosto = centroCosto;
		}


		public Long getIdCentroCostoSeleccionado() {
			return idCentroCostoSeleccionado;
		}


		public void setIdCentroCostoSeleccionado(Long idCentroCostoSeleccionado) {
			this.idCentroCostoSeleccionado = idCentroCostoSeleccionado;
		}


		public void setMeEditaron(boolean meEditaron) {
			this.meEditaron = meEditaron;
		}


		public boolean isSoyNuevo() {
			return soyNuevo;
		}


		public void setSoyNuevo(boolean soyNuevo) {
			this.soyNuevo = soyNuevo;
		}


		public List getListaCentroCosto() {
			return listaCentroCosto;
		}


		public void setListaCentroCosto(List listaCentroCosto) {
			this.listaCentroCosto = listaCentroCosto;
		}


		public boolean isSeBorra() {
			return seBorra;
		}


		public void setSeBorra(boolean seBorra) {
			this.seBorra = seBorra;
		}

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


	public List getAsientos() {
		return asientos;
	}


	public void setAsientos(List asientos) {
		this.asientos = asientos;
	}


	public List getAsientosDetalles() {
		return asientosDetalles;
	}


	public void setAsientosDetalles(List asientosDetalles) {
		this.asientosDetalles = asientosDetalles;
	}


	public Date getFechaAsiento() {
		return fechaAsiento;
	}


	public void setFechaAsiento(Date fechaAsiento) {
		this.fechaAsiento = fechaAsiento;
	}


	public List getLotes() {
		return lotes;
	}


	public void setLotes(List lotes) {
		this.lotes = lotes;
	}


	public List getLotesDetalles() {
		return lotesDetalles;
	}


	public void setLotesDetalles(List lotesDetalles) {
		this.lotesDetalles = lotesDetalles;
	}


	public String getNroAsiento() {
		return nroAsiento;
	}


	public void setNroAsiento(String nroAsiento) {
		this.nroAsiento = nroAsiento;
	}


	public PaginaDeRegistros getPaginador() {
		return paginador;
	}


	public void setPaginador(PaginaDeRegistros paginador) {
		this.paginador = paginador;
	}


	public PaginaDeRegistros getPaginadorDeLotes() {
		return paginadorDeLotes;
	}


	public void setPaginadorDeLotes(PaginaDeRegistros paginadorDeLotes) {
		this.paginadorDeLotes = paginadorDeLotes;
	}

	public class PopupAltaAsiento {

		private Lote lote;
		private Asiento asiento;
		private Long idOrigenSeleccionado;
		private Long idTipoAsientoSeleccionado;
		private Long nroComprobante;
		private HtmlSelectOneMenu menuOrigen;
		private HtmlSelectOneMenu tipoAsiento;
		private List tiposDeAsientos;
		private List origenes;
		private List tiposDeAsientosItem;
		private List origenesItem;
		private String empresa;
		private String ejercicio;
		private String fechaContab;
		private Date fechaContable;
		private Date fechaActual;
		private boolean asientoDeApertura = false;
		private boolean asientoDeCierre = false;
		private boolean isEdicion;
		private String concepto;
		private List docAdjuntos;


		public PopupAltaAsiento(Long idEjercicioSeleccionado, Long idEmpresaSeleccionada) {
			isEdicion = false;
			docAdjuntos = new ArrayList();
			if (!idEjercicioSeleccionado.equals(new Long(0))) {
				borrar();
				lote = new Lote();
				Iterator ejerci = ejercicios.iterator();
				while (ejerci.hasNext()) {
					Ejercicio ej = (Ejercicio) ejerci.next();
					if (ej.getIdEjercicio().longValue() == Long.valueOf(idEjercicioSeleccionadoItem.getValue().toString()).longValue()) {
						String diaInicio = ej.getFechaInicio().toString().substring(8, 10);
						String mesInicio = ej.getFechaInicio().toString().substring(5, 7);
						String anioInicio = ej.getFechaInicio().toString().substring(0, 4);
						String diaFin = ej.getFechaCierre().toString().substring(8, 10);
						String mesFin = ej.getFechaCierre().toString().substring(5, 7);
						String anioFin = ej.getFechaCierre().toString().substring(0, 4);
						ejercicio = ej.getIdEjercicio().toString() + " Inicia: " + diaInicio + "/" + mesInicio + "/" + anioInicio + " Finaliza: "
								+ diaFin + "/" + mesFin + "/" + anioFin;
						lote.setFechaContab(ej.getFechaPeriodo());
						fechaContab = ej.getFechaPeriodo().toString();
						break;
					}
				}
				Iterator it = sucursalesFielAuxiliar.iterator();
				while (it.hasNext()) {
					SucursalFiel su = (SucursalFiel) it.next();
					if (su.getIdSucursal().equals(idSucursalDeFielSeleccionada.getValue())) {
						empresa = su.getNombre();
						break;
					}
				}

				fechaActual = new Date(Calendar.getInstance().getTimeInMillis());
				fechaContable = new Date(Calendar.getInstance().getTimeInMillis());
				asientoDeApertura = false;
				asientoDeCierre = false;
				controlarCierreApertura();
			}
		}


		public PopupAltaAsiento(Lote lote) {
			borrar();
			isEdicion = true;
			this.lote = lote;
			fechaActual = lote.getFechaCarga();
			this.fechaContable = new Date(lote.getFechaContab().getTime()); // esto modifique
			asientoDeApertura = false;
			asientoDeCierre = false;
			concepto = lote.getConcepto();
			Iterator ejerci = ejercicios.iterator();
			while (ejerci.hasNext()) {
				Ejercicio ej = (Ejercicio) ejerci.next();
				if (ej.getIdEjercicio().longValue() == Long.valueOf(idEjercicioSeleccionadoItem.getValue().toString()).longValue()) {
					String diaInicio = ej.getFechaInicio().toString().substring(8, 10);
					String mesInicio = ej.getFechaInicio().toString().substring(5, 7);
					String anioInicio = ej.getFechaInicio().toString().substring(0, 4);
					String diaFin = ej.getFechaCierre().toString().substring(8, 10);
					String mesFin = ej.getFechaCierre().toString().substring(5, 7);
					String anioFin = ej.getFechaCierre().toString().substring(0, 4);
					ejercicio = ej.getIdEjercicio().toString() + " Inicia: " + diaInicio + "/" + mesInicio + "/" + anioInicio + " Finaliza: "
							+ diaFin + "/" + mesFin + "/" + anioFin;
					// lo siguiente daba el bug corregido
					// lote.setFechaContab(ej.getFechaPeriodo());
					fechaContab = ej.getFechaPeriodo().toString();
					break;
				}
			}
			controlarCierreApertura();
			try {
				if (lote.getIdTipoAsiento().equals(new Integer(1))) {
					asientoDeApertura = true;
					aperturaPermitida = true;
					cierrePermitido = false;
				}
				if (lote.getIdTipoAsiento().equals(new Integer(4)) || asiento.getIdTipoAsiento().equals(new Integer(5))) {
					asientoDeCierre = true;
					cierrePermitido = true;
					aperturaPermitida = false;
				}
			} catch (NullPointerException e) {
				// era un lote nuevo por lo tanto no tenia tipo asiento
			}
		}


		public PopupAltaAsiento(Asiento asiento) {
			borrar();
			isEdicion = true;
			this.asiento = asiento;
			fechaActual = asiento.getFechaCarga();
			this.fechaContable = asiento.getFechaContab();
			asientoDeApertura = false;
			asientoDeCierre = false;
			concepto = asiento.getConcepto();
			Iterator ejerci = ejercicios.iterator();
			while (ejerci.hasNext()) {
				Ejercicio ej = (Ejercicio) ejerci.next();
				if (ej.getIdEjercicio().longValue() == Long.valueOf(idEjercicioSeleccionadoItem.getValue().toString()).longValue()) {
					String diaInicio = ej.getFechaInicio().toString().substring(8, 10);
					String mesInicio = ej.getFechaInicio().toString().substring(5, 7);
					String anioInicio = ej.getFechaInicio().toString().substring(0, 4);
					String diaFin = ej.getFechaCierre().toString().substring(8, 10);
					String mesFin = ej.getFechaCierre().toString().substring(5, 7);
					String anioFin = ej.getFechaCierre().toString().substring(0, 4);
					ejercicio = ej.getIdEjercicio().toString() + " Inicia: " + diaInicio + "/" + mesInicio + "/" + anioInicio + " Finaliza: "
							+ diaFin + "/" + mesFin + "/" + anioFin;
					System.out.println("antes del supueto problema" + fechaContab);
					// asiento.setFechaContab(ej.getFechaPeriodo());
					System.out.println("antes del supueto problema" + fechaContab);
					fechaContab = ej.getFechaPeriodo().toString();
					System.out.println("despues del supueto problema" + fechaContab);
					break;
				}
			}
			controlarCierreApertura();
			try {
				if (asiento.getIdTipoAsiento().equals(new Integer(1))) {
					asientoDeApertura = true;
					aperturaPermitida = true;
					cierrePermitido = false;
				}
				if (asiento.getIdTipoAsiento().equals(new Integer(4)) || asiento.getIdTipoAsiento().equals(new Integer(5))) {
					asientoDeCierre = true;
					cierrePermitido = true;
					aperturaPermitida = false;
				}
			} catch (NullPointerException e) {
				// no tenida tipo aplicado
			}
		}


		public void borrar() {
			error.borrar();
			concepto = "";
			docAdjuntos = new ArrayList();
			idOrigenSeleccionado = new Long(0);
			idTipoAsientoSeleccionado = new Long(2);
			nroComprobante = new Long(0);
			menuOrigen = new HtmlSelectOneMenu();
			tipoAsiento = new HtmlSelectOneMenu();
			menuOrigen.setValue(new Long(0));
			tipoAsiento.setValue(new Long(2));
		}


		public String mostrarDocAdjuntosDesdePopup(ActionEvent event) {

			DocumentoAdjuntoBean bean = (DocumentoAdjuntoBean) Session.getBean("DocumentoAdjuntoBean");
			if (this.lote == null) {
				bean.inicializar(DocumentoAdjuntoBean.CONTABILIDAD_POPUP, this.asiento);
			} else {
				bean.inicializar(DocumentoAdjuntoBean.CONTABILIDAD_POPUP, this.lote);
			}
			String path = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
			path += "/tarjetafiel/general/varios/documentoAdjuntoPopup.jsf";
			ejecutarJavaScript("popup('" + path + "',900,900), 'titlebar=no';");
			return null;
		}


		public boolean validar() {
			error.borrar();
			// if (idOrigenSeleccionado.equals(new Long(0))) {
			// error.agregar(Error.CONT_ORIGEN_NO_SELECCIONADO);
			// }
			// if (idTipoAsientoSeleccionado.equals(new Long(0))) {
			// error.agregar(Error.CONT_TIPOASIENTO_NO_SELECCIONADO);
			// }
			if (fechaContable == null || fechaContable.before(ejerActual.getFechaPeriodo())) {
				String dia = ejerActual.getFechaPeriodo().toString().substring(8, 10);
				String mes = ejerActual.getFechaPeriodo().toString().substring(5, 7);
				String anio = ejerActual.getFechaPeriodo().toString().substring(0, 4);
				error.agregar("Debe especificar una fecha contable posterior a " + dia + "/" + mes + "/" + anio);
			}
			if (fechaContable == null || fechaContable.after(ejerActual.getFechaCierre())) {
				error.agregar("Debe especificar una fecha contable anterior a " + ejerActual.getFechaCierre().toString().substring(0, 11));
			}
			if (concepto.compareTo("") == 0) {
				error.agregar(Error.CONT_CONCEPTO_NO_INGRESADO);
			}
			// armo una lista con los modificados para ver si no han borrado alguna cuenta
			LeedoraDeArrays lector = new LeedoraDeArrays(listaDeModicadosEnString);
			log.info("La lista de eliminados es " + listaDeModicadosEnString);
			List lisModif = new ArrayList();
			while (lector.isHaySiguiente()) {
				Integer numero = Integer.valueOf(lector.next());
				lisModif.add(numero);
			}

			if (!agregarL) {
				Iterator borrados = asientosDetalles.iterator();
				boolean esPrimero = true;
				while (borrados.hasNext()) {
					WrapperAsientoDetalle w = (WrapperAsientoDetalle) borrados.next();
					if (w.seBorra) {
						if (esPrimero) {
							listaDeEliminadosEnString += w.getIdAsienDetal();
							esPrimero = false;
						} else {
							listaDeEliminadosEnString += ", " + w.getIdAsienDetal();
						}
					}
				}

				// en este procesito controlo si el asiento ha quedado balanceado, si no, no dejo guardarlo ya que es un asiento...
				// primero armo una coleccion con el id de los borrados.
				LeedoraDeArrays lect = new LeedoraDeArrays(listaDeEliminadosEnString);
				int registrosIncompletos = 0;
				List lisElim = new ArrayList();
				while (lect.isHaySiguiente()) {
					Integer numero = Integer.valueOf(lect.next());
					lisElim.add(numero);
				}
				double debeModificado = 0; // aqui sumaremos todos los debes de los elementos que no fueron eliminados.
				double haberModificado = 0; // idem.....
				boolean agregueErrorCuentaInexistente = false;
				Iterator iterDeControl = asientosDetalles.iterator();
				while (iterDeControl.hasNext()) {
					WrapperAsientoDetalle asi = (WrapperAsientoDetalle) iterDeControl.next();
					if (asi.getAsientoDetalle().getNumeroImputa() == null || asi.getAsientoDetalle().getNumeroImputa().compareTo(new Long(0)) == 0) {
						if (lisModif.contains(Integer.valueOf(asi.getIdAsienDetal()))) {
							if (!agregueErrorCuentaInexistente) {
								asi.getAsientoDetalle().setNumeroImputa(new Long(0));
								error.agregar("Asigne un número de cuenta válido a aquellas que se encuentran en cero.");
								agregueErrorCuentaInexistente = true;
							}
						} else {
							break;
						}
					}
					if (!lisElim.contains(new Integer(asi.getIdAsienDetal()))) {
						try {
							if (asi.getDebe() != null && asi.getDebe().compareTo("") != 0) {
								debeModificado += Double.valueOf(asi.getDebe()).doubleValue();
							} else {
								haberModificado += Double.valueOf(asi.getHaber()).doubleValue();
							}
						} catch (NumberFormatException e) {
							registrosIncompletos++;
						}
					}
				}
				if (registrosIncompletos != 0)
					error.agregar("Hay " + registrosIncompletos + " cuentas sin importe asignado.");
				// si no han quedado balanceados, no lo guardo y se informa el error.
				if (debeModificado != haberModificado) {
					error.agregar("No es posible modificar un Asiento si se lo desbalancea.");
				}
			} else {
				Iterator borrados = lotesDetalles.iterator();
				boolean esPrimero = true;
				while (borrados.hasNext()) {
					WrapperLoteDetalle w = (WrapperLoteDetalle) borrados.next();
					if (w.seBorra) {
						if (esPrimero) {
							listaDeEliminadosEnString += w.getIdLoteDetal();
							esPrimero = false;
						} else {
							listaDeEliminadosEnString += ", " + w.getIdLoteDetal();
						}
					}
				}
				// debo controlar que no borren numeros en haber y debe...
				LeedoraDeArrays lect = new LeedoraDeArrays(listaDeEliminadosEnString);
				log.info("La lista de eliminados es " + listaDeEliminadosEnString);
				int registrosIncompletos = 0;
				List lisElim = new ArrayList();
				while (lect.isHaySiguiente()) {
					Integer numero = Integer.valueOf(lect.next());
					lisElim.add(numero);
				}
				double debeModificado = 0; // aqui sumaremos todos los debes de los elementos que no fueron eliminados.
				double haberModificado = 0; // idem.....
				boolean agregueErrorCuentaInexistente = false;
				Iterator iterDeControl = lotesDetalles.iterator();
				while (iterDeControl.hasNext()) {
					WrapperLoteDetalle asi = (WrapperLoteDetalle) iterDeControl.next();
					if (asi.getLoteDetalle().getNumeroImputa() == null || asi.getLoteDetalle().getNumeroImputa().compareTo(new Long(0)) == 0) {
						if (lisModif.contains(Integer.valueOf(asi.getIdLoteDetal()))) {
							if (!agregueErrorCuentaInexistente) {
								asi.getLoteDetalle().setNumeroImputa(new Long(0));
								error.agregar("Asigne un número de cuenta válido a aquellas que se encuentran en cero.");
								agregueErrorCuentaInexistente = true;
							}
						} else {
							break;
						}
					}
					if (!lisElim.contains(new Integer(asi.getIdLoteDetal()))) {
						try {
							if (asi.getDebe() != null && asi.getDebe().compareTo("") != 0) {
								debeModificado += Double.valueOf(asi.getDebe()).doubleValue();
							} else {
								haberModificado += Double.valueOf(asi.getHaber()).doubleValue();
							}
						} catch (NumberFormatException e) {
							log.info("aumentare el campo de registros incompletos...");
							registrosIncompletos++;
						}
					}
				}
				if (registrosIncompletos != 0)
					error.agregar("Hay " + registrosIncompletos + " cuentas sin importe asignado.");
			}
			return (error.cantidad() == 0) ? true : false;
		}


		public void recargarYCerrarPopup(ActionEvent event) {
			error.borrar();
			listaDeEliminadosEnString = "";
			if (ejerActual.getEstado().compareTo("C") != 0) {
				if (validar() && isEdicion) {
					grabar();
					FacesContext facesContext = FacesContext.getCurrentInstance();
					String javaScriptText = "window.opener.recargar();window.close();";
					AddResource addResource = AddResourceFactory.getInstance(facesContext);
					addResource.addInlineScriptAtPosition(facesContext, AddResource.HEADER_BEGIN, javaScriptText);
				} else {
					if (validar()) {
						grabar();
						FacesContext facesContext = FacesContext.getCurrentInstance();
						String javaScriptText = "window.opener.recargar();";
						AddResource addResource = AddResourceFactory.getInstance(facesContext);
						addResource.addInlineScriptAtPosition(facesContext, AddResource.HEADER_BEGIN, javaScriptText);
					}
				}
			} else {
				error.agregar("No se puede guardar cambios en un ejercicio cerrado.");
			}
		}


		public String grabar() {
			error.borrar();
			// if (validar()) {
			if (agregarL) {
				// La lista de borrados fue iniciada en recargarYCerrarPopup
				log.info(listaDeModicadosEnString);
				if (isEdicion) {
					lote.setConcepto(concepto);
					lote.setFechaContab(this.getFechaContable());
					Operador operador = new Operador();
					operador = Session.getOperador();
					// Aca la logica de seteo de tipo de ejercicio si es de cierre o apertura.
					if (asientoDeApertura || asientoDeCierre) {
						if (asientoDeApertura) {
							lote.setIdTipoAsiento(new Integer(1));
							aperturaPermitida = false;
						}
						if (asientoDeCierre) {
							lote.setIdTipoAsiento(new Integer(4));
							cierrePermitido = false;
						}
					} else {
						lote.setIdTipoAsiento(Integer.valueOf(idTipoAsientoSeleccionado.intValue())); // aca setea el tipo de asiento, que esta en
																										// default en general.
					}
					lote.setHoraCarga(new Timestamp(Calendar.getInstance().getTimeInMillis()));
					lote.setOperador(operador.getId().toString());
					try {
						contabilidadService.getLoteService().actualizarLote(lote);

						// lo que sigue es por lo de anexar documentos adjuntos en la parte de popup
						if (this.docAdjuntos.size() > 0) {
							Iterator iter = this.docAdjuntos.iterator();
							while (iter.hasNext()) {
								DocAdjunto docs = (DocAdjunto) iter.next();
								docs.setIdAsiento(lote.getIdAsiento());
								contabilidadService.getDocAdjuntoDao().grabar(docs);
							}
						}

						// listarAsientosYLotes();
					} catch (LoteException e) {
						error.agregar("No se ha podido actualizar el lote.");
						log.info("No se pudo actualizar el lote");
						e.printStackTrace();
					}
				} else {
					lote.setIdEjercicio(Integer.valueOf(idEjercicioSeleccionado.toString()));
					lote.setIdEmpresa(Integer.valueOf(idSucSeleccionadaParaFiel.toString()));
					// if (nroComprobante == null || (nroComprobante != null && nroComprobante.equals(new Long(0)))) {
					// lote.setIdNroComprobante(null);
					// }
					// lote.setIdOrigen(new Long(3)); //Aca esta seteando el lote de contabilidad
					lote.setConcepto(concepto);
					lote.setFechaCarga(fechaActual);
					lote.setFechaContab(this.fechaContable);
					Operador operador = new Operador();
					operador = Session.getOperador();
					// Aca la logica de seteo de tipo de ejercicio si es de cierre o apertura.
					if (asientoDeApertura || asientoDeCierre) {
						if (asientoDeApertura) {
							lote.setIdTipoAsiento(new Integer(1));
							aperturaPermitida = false;
						}
						if (asientoDeCierre) {
							lote.setIdTipoAsiento(new Integer(4));
							cierrePermitido = false;
						}
					} else {
						lote.setIdTipoAsiento(Integer.valueOf(idTipoAsientoSeleccionado.intValue())); // aca setea el tipo de asiento, que esta en
																										// default en general.
					}
					lote.setHoraCarga(new Timestamp(Calendar.getInstance().getTimeInMillis()));
					lote.setOperador(operador.getId().toString());
					Long num = contabilidadService.getLoteService().getLastIdDeLotes();
					Long aGrabar = new Long(num.intValue());
					lote.setIdAsiento(aGrabar);
					log.info("ejercicio = " + lote.getIdEjercicio() + "; empresa = " + lote.getIdEmpresa() + "; asiento = " + lote.getIdAsiento());
					lote.setIdAsiento(aGrabar);
					try {
						contabilidadService.getLoteService().grabarLote(lote);
						// lo que sigue es por lo de anexar documentos adjuntos en la parte de popup
						if (docAdjuntos.size() > 0) {
							Iterator iter = docAdjuntos.iterator();
							while (iter.hasNext()) {
								DocAdjunto docs = (DocAdjunto) iter.next();
								docs.setIdAsiento(lote.getIdAsiento());
								docs.setIdEmpresa(lote.getIdEmpresa());
								docs.setIdEjercicio(lote.getIdEjercicio());
								contabilidadService.getDocAdjuntoDao().grabar(docs);
							}
						}

						// listarAsientosYLotes();
					} catch (LoteException e) {
						error.agregar("No se ha podido crear el lote.");
						log.info("No se pudo crear el lote");
						e.printStackTrace();
					}
				}
				grabarCambiosLotesDetalles();
				if (!isEdicion) {
					lotesDetalles = new ArrayList();
					for (int i = 0; i < 100; i++) {
						WrapperLoteDetalle wrapDet = new WrapperLoteDetalle();
						wrapDet.setLoteDetalle(new LoteDetalle());
						// wrapDet.setSoyNuevo(true);
						wrapDet.setDenominacion("");
						lotesDetalles.add(wrapDet);
					}
					popupAltaAsiento = new PopupAltaAsiento(idEjercicioSeleccionado, idSucSeleccionadaParaFiel);
				}

			} else {
				// La lista de borrados esta iniciada en recargarYCerrarPopup

				log.info(listaDeModicadosEnString);
				if (isEdicion) {
					asiento.setConcepto(concepto);
					asiento.setFechaContab(this.getFechaContable());
					Operador operador = new Operador();
					operador = Session.getOperador();
					// Aca la logica de seteo de tipo de ejercicio si es de cierre o apertura.
					if (asientoDeApertura || asientoDeCierre) {
						if (asientoDeApertura) {
							asiento.setIdTipoAsiento(new Integer(1));
							aperturaPermitida = false;
						}
						if (asientoDeCierre) {
							asiento.setIdTipoAsiento(new Integer(4));
							cierrePermitido = false;
						}
					} else {
						asiento.setIdTipoAsiento(Integer.valueOf(idTipoAsientoSeleccionado.intValue())); // aca setea el tipo de asiento, que esta en
																											// default en general.
					}
					asiento.setHoraCarga(new Timestamp(Calendar.getInstance().getTimeInMillis()));
					asiento.setOperador(operador.getId().toString());
					try {
						contabilidadService.getAsientoService().actualizarAsiento(asiento);

						// lo que sigue es por lo de anexar documentos adjuntos en la parte de popup
						if (docAdjuntos.size() > 0) {
							Iterator iter = docAdjuntos.iterator();
							while (iter.hasNext()) {
								DocAdjunto docs = (DocAdjunto) iter.next();
								docs.setIdAsiento(asiento.getIdAsiento());
								contabilidadService.getDocAdjuntoDao().grabar(docs);
							}
						}

						// listarAsientosYLotes();
					} catch (AsientoException e) {
						error.agregar("No se ha podido actualizar el Asiento.");
						log.info("No se pudo actualizar el asiento");
						e.printStackTrace();
					}
				}
				grabarCambiosAsientosDetalles();
			}

			// }
			return null;
		}


		public String grabarCambiosAsientosDetalles() {
			log.info("la lista tiene estos objetos ");
			log.info(listaDeModicadosEnString);
			log.info("y la lista a borrar es: " + listaDeEliminadosEnString);

			LeedoraDeArrays lectora = new LeedoraDeArrays(listaDeModicadosEnString);
			lectora.quitarCadena(listaDeEliminadosEnString);
			while (lectora.isHaySiguiente()) {
				AsientoDetalle asientoEdit = null;
				WrapperAsientoDetalle asientoDetalleEdit = null;
				int numero = Integer.valueOf(lectora.next()).intValue();
				Iterator it = asientosDetalles.iterator();
				while (it.hasNext()) {
					WrapperAsientoDetalle asi = (WrapperAsientoDetalle) it.next();
					if (asi.getIdAsienDetal() == numero) {
						asientoEdit = asi.getAsientoDetalle();
						asientoDetalleEdit = asi;
						// loteEdit.setCentroCosto(lot.getCentroCosto()); // Aca cambiar toda la logica a setear el set de centrros de costo
						if (asi.getDebe() != null && asi.getDebe().compareTo("") != 0) {
							asientoEdit.setImporte(Double.valueOf(asi.getDebe()).doubleValue());
							asientoEdit.setSigno("D");
						} else {
							asientoEdit.setImporte(Double.valueOf(asi.getHaber()).doubleValue());
							asientoEdit.setSigno("C");
						}
						break;
					}
				}
				try {
					if (asientoDetalleEdit != null) {
						if (asientoDetalleEdit.soyNuevo) {
							// Nunca debe entrar en este sector.
							log.info("Dando de alta un asiento nuevo!!. (Controlar proceso)");
							Long numeroDeRenglon = contabilidadService.getAsientoDetalleService().getLastIdDeRenglon(
									new Long(asiento.getIdEjercicio().intValue()), new Long(asiento.getIdEmpresa().intValue()),
									new Long(asiento.getIdAsiento().intValue()));
							Integer rengl = new Integer(numeroDeRenglon.intValue() + 1);
							AsientoDetalle asientoDetalleAgregar = asientoEdit;
							asientoDetalleAgregar.setId((new AsientoDetalle(asiento.getIdEmpresa(), asiento.getIdEjercicio(), rengl, new Integer(
									asiento.getIdAsiento().intValue()))).getId());
							asientoDetalleAgregar.setOperador(Session.getOperador().getId().toString());
							asientoDetalleAgregar.setImporte(asientoEdit.getImporte());
							asientoDetalleAgregar.setSigno(asientoEdit.getSigno());
							asientoDetalleAgregar.setFechaContab(fechaContable);
							asientoDetalleAgregar.setFechaCarga(fechaActual);
							contabilidadService.getAsientoDetalleService().grabar(asientoDetalleAgregar);
						} else {
							log.info("Actualizando el asiento identificado como " + asientoDetalleEdit.getIdAsienDetal());
							asientoEdit.setFechaContab(fechaContable);
							contabilidadService.getAsientoDetalleService().actualizar(asientoEdit);
						}

						// Aca la logica para grabar los objetos relacionales, los CentroCostoAsiento

						if (!asientoDetalleEdit.getListaCentroCosto().isEmpty()
								&& idDeCuentasDisponibles.contains(asientoDetalleEdit.getAsientoDetalle().getNumeroImputa())) {

							Iterator ite = asientoDetalleEdit.getListaCentroCosto().iterator();
							while (ite.hasNext()) {
								WrapperCenCosAsiento cenCosAsi = (WrapperCenCosAsiento) ite.next();
								if (cenCosAsi.getIdCentroCostoAsiento() != null) {
									try {
										CentroCostoAsiento cca = contabilidadService.getCentroCostoAsientoService().leerCentroCostoAsiento(
												cenCosAsi.getIdCentroCostoAsiento());
										cca.setIdImporte(cenCosAsi.getImporte());
										Iterator iterCuen = cuentasDisponibles.iterator();
										PlanCuentaDos cuentaAGrabar = null;
										while (iterCuen.hasNext()) {
											PlanCuentaDos plan = (PlanCuentaDos) iterCuen.next();
											if (plan.getIdPlanCuenta().compareTo(asientoEdit.getNumeroImputa()) == 0) {
												cuentaAGrabar = plan;
												break;
											}
										}
										cca.setPlanCuentaDos(cuentaAGrabar);
										if (!cca.getIdImporte().equals(new Long(0))) {
											contabilidadService.getCentroCostoAsientoService().actualizarCentroCostoAsiento(cca);
										} else {
											contabilidadService.getCentroCostoAsientoService().borrarCentroCostoAsiento(cca);
										}
									} catch (CentroCostoAsientoException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
								} else {
									if (cenCosAsi.getImporte().compareTo(new Long(0)) != 0) {
										CentroCostoAsiento cca = new CentroCostoAsiento();
										cca.setIdImporte(cenCosAsi.getImporte());
										Iterator iterCuen = cuentasDisponibles.iterator();
										PlanCuentaDos cuentaAGrabar = null;
										while (iterCuen.hasNext()) {
											PlanCuentaDos plan = (PlanCuentaDos) iterCuen.next();
											if (plan.getIdPlanCuenta().compareTo(asientoEdit.getNumeroImputa()) == 0) {
												cuentaAGrabar = plan;
												break;
											}
										}
										cca.setPlanCuentaDos(cuentaAGrabar);
										cca.setCentroCostos(cenCosAsi.getCentro());
										cca.setIdCentroCostoAsiento(null);
										cca.setIdAsiento(new Long(asientoEdit.getId().getIdAsiento().intValue()));
										cca.setIdRenglon(new Long(asientoEdit.getId().getRenglon().intValue()));
										try {
											log.info("Se Grabara 1 CentroCostoAsiento");
											contabilidadService.getCentroCostoAsientoService().grabarCentroCostoAsiento(cca);
										} catch (CentroCostoAsientoException e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										}
									}
								}

							}
						}

					}
				} catch (AsientoDetalleException e2) {
					log.info("Errores al querer actualizar asientosDetalles.");
					e2.printStackTrace();
				}
			}
			// Ahora debemos borrar los de la lista de eliminados.
			if (listaDeEliminadosEnString.compareTo("") != 0) {
				LeedoraDeArrays lectoraElim = new LeedoraDeArrays(listaDeEliminadosEnString);
				while (lectoraElim.isHaySiguiente()) {
					int numero = Integer.valueOf(lectoraElim.next()).intValue();
					Iterator it = asientosDetalles.iterator();
					while (it.hasNext()) {
						WrapperAsientoDetalle asi = (WrapperAsientoDetalle) it.next();
						if (asi.getIdAsienDetal() == numero) {
							if (!asi.soyNuevo) {
								try {
									Iterator listaDeCentros = asi.getListaCentroCosto().iterator();
									while (listaDeCentros.hasNext()) {
										WrapperCenCosAsiento cen = (WrapperCenCosAsiento) listaDeCentros.next();
										if (!cen.getImporte().equals(new Long(0))) {
											contabilidadService.getCentroCostoAsientoService().borrarCentroCostoAsiento(
													cen.getCentro().getIdCentroCostos());
										}
									}
									contabilidadService.getAsientoDetalleService().borrar(
											new Long(asi.getAsientoDetalle().getId().getIdEjercicio().intValue()),
											new Long(asi.getAsientoDetalle().getId().getIdEmpresa().intValue()),
											new Long(asi.getAsientoDetalle().getId().getIdAsiento().intValue()),
											new Long(asi.getAsientoDetalle().getId().getRenglon().intValue()));

								} catch (AsientoDetalleException e) {
									log.info("Error al borrar uno de los detalles.");
									e.printStackTrace();
								} catch (CentroCostoAsientoException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								break;
							}
						}
					}
				}
			}
			return null;
		}


		public String grabarCambiosLotesDetalles() {

			log.info("la lista tiene estos objetos ");
			log.info(listaDeModicadosEnString);
			log.info("y la lista a borrar es: " + listaDeEliminadosEnString);
			List listaAGrabar = new ArrayList();

			LeedoraDeArrays lectora = new LeedoraDeArrays(listaDeModicadosEnString);
			lectora.quitarCadena(listaDeEliminadosEnString);
			while (lectora.isHaySiguiente()) {
				LoteDetalle loteEdit = null;
				WrapperLoteDetalle loteDetalleEdit = null;
				int numero = Integer.valueOf(lectora.next()).intValue();
				Iterator it = lotesDetalles.iterator();
				while (it.hasNext()) {
					WrapperLoteDetalle lot = (WrapperLoteDetalle) it.next();
					if (lot.getIdLoteDetal() == numero) {
						loteEdit = lot.getLoteDetalle();
						loteDetalleEdit = lot;
						if (lot.getDebe() != null && lot.getDebe().compareTo("") != 0) {
							loteEdit.setImporte(Double.valueOf(lot.getDebe()).doubleValue());
							loteEdit.setSigno("D");
						} else {
							loteEdit.setImporte(Double.valueOf(lot.getHaber()).doubleValue());
							loteEdit.setSigno("C");
						}
						break;
					}
				}
				try {
					if (loteDetalleEdit != null) {
						if (loteDetalleEdit.soyNuevo) {
							Long numeroDeRenglon = contabilidadService.getLoteDetalleService().getLastIdDeRenglon(
									new Long(lote.getIdEjercicio().intValue()), new Long(lote.getIdEmpresa().intValue()),
									new Long(lote.getIdAsiento().intValue()));
							Integer rengl = new Integer(numeroDeRenglon.intValue() + 1);
							LoteDetalle loteDetalleAgregar = loteEdit;
							loteDetalleAgregar.setId((new LoteDetalle(lote.getIdEmpresa(), lote.getIdEjercicio(), rengl, new Integer(lote
									.getIdAsiento().intValue()))).getId());
							loteDetalleAgregar.setOperador(Session.getOperador().getId().toString());
							loteDetalleAgregar.setImporte(loteEdit.getImporte());
							loteDetalleAgregar.setSigno(loteEdit.getSigno());
							loteDetalleAgregar.setFechaContab(fechaContable);
							loteDetalleAgregar.setFechaCarga(fechaActual);

							contabilidadService.getLoteDetalleService().grabar(loteDetalleAgregar);
						} else {
							loteEdit.setFechaContab(fechaContable);
							contabilidadService.getLoteDetalleService().actualizar(loteEdit);
						}

						// Aca la logica para grabar los objetos relacionales, los CentroCostoAsiento

						if (!loteDetalleEdit.getListaCentroCosto().isEmpty()
								&& idDeCuentasDisponibles.contains(loteDetalleEdit.getLoteDetalle().getNumeroImputa())) {

							Iterator ite = loteDetalleEdit.getListaCentroCosto().iterator();
							while (ite.hasNext()) {
								WrapperCenCosAsiento cenCosAsi = (WrapperCenCosAsiento) ite.next();
								if (cenCosAsi.getIdCentroCostoAsiento() != null) {
									try {
										CentroCostoAsiento cca = contabilidadService.getCentroCostoAsientoService().leerCentroCostoAsiento(
												cenCosAsi.getIdCentroCostoAsiento());
										cca.setIdImporte(cenCosAsi.getImporte());
										Iterator iterCuen = cuentasDisponibles.iterator();
										PlanCuentaDos cuentaAGrabar = null;
										while (iterCuen.hasNext()) {
											PlanCuentaDos plan = (PlanCuentaDos) iterCuen.next();
											if (plan.getIdPlanCuenta().compareTo(loteEdit.getNumeroImputa()) == 0) {
												cuentaAGrabar = plan;
												break;
											}
										}
										cca.setPlanCuentaDos(cuentaAGrabar);
										if (!cca.getIdImporte().equals(new Long(0))) {
											contabilidadService.getCentroCostoAsientoService().actualizarCentroCostoAsiento(cca);
										} else {
											contabilidadService.getCentroCostoAsientoService().borrarCentroCostoAsiento(cca);
										}
									} catch (CentroCostoAsientoException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
								} else {
									if (cenCosAsi.getImporte().compareTo(new Long(0)) != 0) {
										CentroCostoAsiento cca = new CentroCostoAsiento();
										cca.setIdImporte(cenCosAsi.getImporte());
										Iterator iterCuen = cuentasDisponibles.iterator();
										PlanCuentaDos cuentaAGrabar = null;
										while (iterCuen.hasNext()) {
											PlanCuentaDos plan = (PlanCuentaDos) iterCuen.next();
											if (plan.getIdPlanCuenta().compareTo(loteEdit.getNumeroImputa()) == 0) {
												cuentaAGrabar = plan;
												break;
											}
										}
										cca.setPlanCuentaDos(cuentaAGrabar);
										cca.setCentroCostos(cenCosAsi.getCentro());
										cca.setIdCentroCostoAsiento(null);
										cca.setIdAsiento(new Long(loteEdit.getId().getIdAsiento().intValue()));
										cca.setIdRenglon(new Long(loteEdit.getId().getRenglon().intValue()));
										cca.setIsLote("S");
										try {
											contabilidadService.getCentroCostoAsientoService().grabarCentroCostoAsiento(cca);
										} catch (CentroCostoAsientoException e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										}
									}
								}

							}
						}

					}
				} catch (LoteDetalleException e) {
					log.info("Errores al querer actualizar lotesDetalles.");
					e.printStackTrace();
				}
			}

			// Ahora debemos borrar los de la lista de eliminados.
			if (listaDeEliminadosEnString.compareTo("") != 0) {
				LeedoraDeArrays lectoraElim = new LeedoraDeArrays(listaDeEliminadosEnString);
				while (lectoraElim.isHaySiguiente()) {
					int numero = Integer.valueOf(lectoraElim.next()).intValue();
					Iterator it = lotesDetalles.iterator();
					while (it.hasNext()) {
						WrapperLoteDetalle lot = (WrapperLoteDetalle) it.next();
						if (lot.getIdLoteDetal() == numero) {
							if (!lot.soyNuevo) {
								try {
									Iterator listaDeCentros = lot.getListaCentroCosto().iterator();
									while (listaDeCentros.hasNext()) {
										WrapperCenCosAsiento cen = (WrapperCenCosAsiento) listaDeCentros.next();
										if (!cen.getImporte().equals(new Long(0))) {
											log.info("borraremos el centro de id " + cen.getCentro().getIdCentroCostos());
											contabilidadService.getCentroCostoAsientoService()
													.borrarCentroCostoAsiento(cen.getIdCentroCostoAsiento());
										}
									}
									contabilidadService.getLoteDetalleService().borrar(
											new Long(lot.getLoteDetalle().getId().getIdEjercicio().intValue()),
											new Long(lot.getLoteDetalle().getId().getIdEmpresa().intValue()),
											new Long(lot.getLoteDetalle().getId().getIdAsiento().intValue()),
											new Long(lot.getLoteDetalle().getId().getRenglon().intValue()));

								} catch (LoteDetalleException e) {
									log.info("Error al borrar uno de los detalles.");
									e.printStackTrace();
								} catch (CentroCostoAsientoException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								break;
							}
						}
					}
				}
			}
			return null;
		}


		public String cancelar() {
			return null;
		}


		public Long getIdOrigenSeleccionado() {
			return idOrigenSeleccionado;
		}


		public void setIdOrigenSeleccionado(Long idOrigenSeleccionado) {
			this.idOrigenSeleccionado = idOrigenSeleccionado;
		}


		public Long getIdTipoAsientoSeleccionado() {
			return idTipoAsientoSeleccionado;
		}


		public void setIdTipoAsientoSeleccionado(Long idTipoAsientoSeleccionado) {
			this.idTipoAsientoSeleccionado = idTipoAsientoSeleccionado;
		}


		public Lote getLote() {
			return lote;
		}


		public void setLote(Lote lote) {
			this.lote = lote;
		}


		public Long getNroComprobante() {
			return nroComprobante;
		}


		public void setNroComprobante(Long nroComprobante) {
			this.nroComprobante = nroComprobante;
		}


		public List getOrigenesItem() {
			return origenesItem;
		}


		public void setOrigenesItem(List origenesItem) {
			this.origenesItem = origenesItem;
		}


		public List getTiposDeAsientosItem() {
			return tiposDeAsientosItem;
		}


		public void setTiposDeAsientosItem(List tiposDeAsientosItem) {
			this.tiposDeAsientosItem = tiposDeAsientosItem;
		}


		public HtmlSelectOneMenu getMenuOrigen() {
			return menuOrigen;
		}


		public void setMenuOrigen(HtmlSelectOneMenu menuOrigen) {
			this.menuOrigen = menuOrigen;
		}


		public HtmlSelectOneMenu getTipoAsiento() {
			return tipoAsiento;
		}


		public void setTipoAsiento(HtmlSelectOneMenu tipoAsiento) {
			this.tipoAsiento = tipoAsiento;
		}


		public String getEjercicio() {
			return ejercicio;
		}


		public void setEjercicio(String ejercicio) {
			this.ejercicio = ejercicio;
		}


		public String getEmpresa() {
			return empresa;
		}


		public void setEmpresa(String empresa) {
			this.empresa = empresa;
		}


		public Date getFechaActual() {
			return fechaActual;
		}


		public void setFechaActual(Date fechaActual) {
			this.fechaActual = fechaActual;
		}


		public String getFechaContab() {
			return fechaContab;
		}


		public void setFechaContab(String fechaContab) {
			this.fechaContab = fechaContab;
		}


		public String getConcepto() {
			return concepto;
		}


		public void setConcepto(String concepto) {
			this.concepto = concepto.trim();
		}


		public Date getFechaContable() {
			return fechaContable;
		}


		public void setFechaContable(Date fechaContable) {
			this.fechaContable = fechaContable;
		}


		public boolean isAsientoDeApertura() {
			return asientoDeApertura;
		}


		public void setAsientoDeApertura(boolean asientoDeApertura) {
			this.asientoDeApertura = asientoDeApertura;
		}


		public boolean isAsientoDeCierre() {
			return asientoDeCierre;
		}


		public void setAsientoDeCierre(boolean asientoDeCierre) {
			this.asientoDeCierre = asientoDeCierre;
		}


		public Asiento getAsiento() {
			return asiento;
		}


		public void setAsiento(Asiento asiento) {
			this.asiento = asiento;
		}


		public List getDocAdjuntos() {
			return docAdjuntos;
		}


		public void setDocAdjuntos(List docAdjuntoss) {
			docAdjuntos = docAdjuntoss;
		}

	}

	public class ClaseADetallar {

		private Asiento asientoDetallado;

		private Lote loteDetallado;


		public ClaseADetallar(Asiento asientoDetallado) throws Exception {
			super();
			this.asientoDetallado = asientoDetallado;
		}


		public ClaseADetallar(Lote loteDetallado) throws Exception {
			super();
			this.loteDetallado = loteDetallado;
		}


		public Asiento getAsientoDetallado() {
			return asientoDetallado;
		}


		public void setAsientoDetallado(Asiento asientoDetallado) {
			this.asientoDetallado = asientoDetallado;
		}


		public Lote getLoteDetallado() {
			return loteDetallado;
		}


		public void setLoteDetallado(Lote loteDetallado) {
			this.loteDetallado = loteDetallado;
		}

	}


	public Asiento getAsientoDetallado() {
		return asientoDetallado;
	}


	public void setAsientoDetallado(Asiento asientoDetallado) {
		this.asientoDetallado = asientoDetallado;
	}


	public Lote getLoteDetallado() {
		return loteDetallado;
	}


	public void setLoteDetallado(Lote loteDetallado) {
		this.loteDetallado = loteDetallado;
	}


	public String getBalanceAsientoDebe() {
		return balanceAsientoDebe;
	}


	public void setBalanceAsientoDebe(String balanceAsientoDebe) {
		this.balanceAsientoDebe = balanceAsientoDebe;
	}


	public String getBalanceAsientoHaber() {
		return balanceAsientoHaber;
	}


	public void setBalanceAsientoHaber(String balanceAsientoHaber) {
		this.balanceAsientoHaber = balanceAsientoHaber;
	}


	public String getBalanceLoteDebe() {
		return balanceLoteDebe;
	}


	public void setBalanceLoteDebe(String balanceLoteDebe) {
		this.balanceLoteDebe = balanceLoteDebe;
	}


	public String getBalanceLoteHaber() {
		return balanceLoteHaber;
	}


	public void setBalanceLoteHaber(String balanceLoteHaber) {
		this.balanceLoteHaber = balanceLoteHaber;
	}


	public List getListaCentroDeCostos() {
		return listaCentroDeCostos;
	}


	public void setListaCentroDeCostos(List listaCentroDeCostos) {
		this.listaCentroDeCostos = listaCentroDeCostos;
	}


	public String getListaDeModicadosEnString() {
		return listaDeModicadosEnString;
	}


	public void setListaDeModicadosEnString(String listaDeModicadosEnString) {
		this.listaDeModicadosEnString = listaDeModicadosEnString;
	}


	public String getCuentaABuscarEnAsiento() {
		return cuentaABuscarEnAsiento;
	}


	public void setCuentaABuscarEnAsiento(String cuentaABuscarEnAsiento) {

		this.cuentaABuscarEnAsiento = cuentaABuscarEnAsiento;
		if (cuentaParaAsi != null) {
			this.cuentaABuscarEnAsiento = cuentaParaAsi;
			cuentaParaAsi = null;
		} else {
			this.cuentaABuscarEnAsiento = cuentaABuscarEnAsiento;
		}

	}


	public String getConceptoABuscarEnAsiento() {
		return conceptoABuscarEnAsiento;
	}


	public void setConceptoABuscarEnAsiento(String conceptoABuscarEnAsiento) {
		this.conceptoABuscarEnAsiento = conceptoABuscarEnAsiento;
	}


	public Date getFechaCierreAsiento() {
		return fechaCierreAsiento;
	}


	public void setFechaCierreAsiento(Date fechaCierreAsiento) {
		this.fechaCierreAsiento = fechaCierreAsiento;
		if (modificarFechasEjercicios) {
			if (fechaFinEjercicio != null) {
				this.fechaCierreAsiento = fechaFinEjercicio;
			} else {
				this.fechaCierreAsiento = fechaCierreAsiento;
			}
		}
	}


	public Date getFechaInicioAsiento() {
		return fechaInicioAsiento;
	}


	public void setFechaInicioAsiento(Date fechaInicioAsiento) {
		this.fechaInicioAsiento = fechaInicioAsiento;
		if (modificarFechasEjercicios) {
			if (fechaInicioEjercicio != null) {
				this.fechaInicioAsiento = fechaInicioEjercicio;
			} else {
				this.fechaInicioAsiento = fechaInicioAsiento;
			}
		}
	}


	public Date getFechaCierreLote() {
		return fechaCierreLote;
	}


	public void setFechaCierreLote(Date fechaCierreLote) {
		this.fechaCierreLote = fechaCierreLote;
		if (modificarFechasEjercicios) {
			if (fechaFinEjercicio != null) {
				this.fechaCierreLote = fechaFinEjercicio;
			} else {
				this.fechaCierreLote = fechaCierreLote;
			}
		}
	}


	public Date getFechaInicioLote() {
		return fechaInicioLote;
	}


	public void setFechaInicioLote(Date fechaInicioLote) {
		this.fechaInicioLote = fechaInicioLote;
		if (modificarFechasEjercicios) {
			if (fechaInicioEjercicio != null) {
				this.fechaInicioLote = fechaInicioEjercicio;
			} else {
				this.fechaInicioLote = fechaInicioLote;
			}
		}
	}


	public String getConceptoABuscarEnLote() {
		return conceptoABuscarEnLote;
	}


	public void setConceptoABuscarEnLote(String conceptoABuscarEnLote) {
		this.conceptoABuscarEnLote = conceptoABuscarEnLote;
	}


	public String getCuentaABuscarEnLote() {
		return cuentaABuscarEnLote;
	}


	public void setCuentaABuscarEnLote(String cuentaABuscarEnLote) {
		this.cuentaABuscarEnLote = cuentaABuscarEnLote;
		if (cuentaParaLot != null) {
			this.cuentaABuscarEnLote = cuentaParaLot;
			cuentaParaLot = null;
		} else {
			this.cuentaABuscarEnLote = cuentaABuscarEnLote;
		}
	}


	public Long getNroDeCuentaBuscadaEnAsiento() {
		return nroDeCuentaBuscadaEnAsiento;
	}


	public void setNroDeCuentaBuscadaEnAsiento(Long nroDeCuentaBuscadaEnAsiento) {
		this.nroDeCuentaBuscadaEnAsiento = nroDeCuentaBuscadaEnAsiento;
	}


	public Long getNroDeCuentaBuscadaEnLote() {
		return nroDeCuentaBuscadaEnLote;
	}


	public void setNroDeCuentaBuscadaEnLote(Long nroDeCuentaBuscadaEnLote) {
		this.nroDeCuentaBuscadaEnLote = nroDeCuentaBuscadaEnLote;
	}


	public boolean isTodos() {
		return todos;
	}


	public void setTodos(boolean todos) {
		this.todos = todos;
	}


	public PopupAltaAsiento getPopupAltaAsiento() {
		return popupAltaAsiento;
	}


	public void setPopupAltaAsiento(PopupAltaAsiento popupAltaAsiento) {
		this.popupAltaAsiento = popupAltaAsiento;
	}


	public PlanCuentaDos getCuentaNuevaParaDetalle() {
		return cuentaNuevaParaDetalle;
	}


	public void setCuentaNuevaParaDetalle(PlanCuentaDos cuentaNuevaParaDetalle) {
		this.cuentaNuevaParaDetalle = cuentaNuevaParaDetalle;
	}


	public WrapperLoteDetalle getWrapLot() {
		return wrapLot;
	}


	public void setWrapLot(WrapperLoteDetalle wrapLot) {
		this.wrapLot = wrapLot;
	}


	public HtmlSelectOneMenu getCentroCostoSeleccionado() {
		return centroCostoSeleccionado;
	}


	public void setCentroCostoSeleccionado(
			HtmlSelectOneMenu centroCostoSeleccionado) {
		this.centroCostoSeleccionado = centroCostoSeleccionado;
	}


	public Long getIdCentroCostoSeleccionado() {
		return idCentroCostoSeleccionado;
	}


	public void setIdCentroCostoSeleccionado(Long idCentroCostoSeleccionado) {
		this.idCentroCostoSeleccionado = idCentroCostoSeleccionado;
	}


	public List getCentroCostosDisponibles() {
		return centroCostosDisponibles;
	}


	public void setCentroCostosDisponibles(List centroCostosDisponibles) {
		this.centroCostosDisponibles = centroCostosDisponibles;
	}


	public List getOrigenes() {
		return origenes;
	}


	public void setOrigenes(List origenes) {
		this.origenes = origenes;
	}


	public List getOrigenesItem() {
		return origenesItem;
	}


	public void setOrigenesItem(List origenesItem) {
		this.origenesItem = origenesItem;
	}


	public Long getIdOrigenSeleccionado() {
		return idOrigenSeleccionado;
	}


	public void setIdOrigenSeleccionado(Long idOrigenSeleccionado) {
		this.idOrigenSeleccionado = idOrigenSeleccionado;
	}


	public HtmlSelectOneMenu getOrigenSeleccionado() {
		return origenSeleccionado;
	}


	public void setOrigenSeleccionado(HtmlSelectOneMenu origenSeleccionado) {
		this.origenSeleccionado = origenSeleccionado;
	}


	public Long getIdOrigenSeleccionadoParaImportar() {
		return idOrigenSeleccionadoParaImportar;
	}


	public void setIdOrigenSeleccionadoParaImportar(
			Long idOrigenSeleccionadoParaImportar) {
		this.idOrigenSeleccionadoParaImportar = idOrigenSeleccionadoParaImportar;
	}


	public HtmlSelectOneMenu getOrigenSeleccionadoParaImportar() {
		return origenSeleccionadoParaImportar;
	}


	public void setOrigenSeleccionadoParaImportar(
			HtmlSelectOneMenu origenSeleccionadoParaImportar) {
		this.origenSeleccionadoParaImportar = origenSeleccionadoParaImportar;
	}


	public String getCuentaParaAsi() {
		return cuentaParaAsi;
	}


	public void setCuentaParaAsi(String cuentaParaAsi) {
		this.cuentaParaAsi = cuentaParaAsi;
	}


	public String getCuentaParaLot() {
		return cuentaParaLot;
	}


	public void setCuentaParaLot(String cuentaParaLot) {
		this.cuentaParaLot = cuentaParaLot;
	}


	public HtmlSelectOneMenu getCentroCostoSeleccionadoLote() {
		return centroCostoSeleccionadoLote;
	}


	public void setCentroCostoSeleccionadoLote(HtmlSelectOneMenu centroCostoSeleccionadoLote) {
		this.centroCostoSeleccionadoLote = centroCostoSeleccionadoLote;
	}


	public Long getIdCentroCostoSeleccionadoLote() {
		return idCentroCostoSeleccionadoLote;
	}


	public void setIdCentroCostoSeleccionadoLote(Long idCentroCostoSeleccionadoLote) {
		this.idCentroCostoSeleccionadoLote = idCentroCostoSeleccionadoLote;
	}


	public Long getIdOrigenSeleccionadoLote() {
		return idOrigenSeleccionadoLote;
	}


	public void setIdOrigenSeleccionadoLote(Long idOrigenSeleccionadoLote) {
		this.idOrigenSeleccionadoLote = idOrigenSeleccionadoLote;
	}


	public HtmlSelectOneMenu getOrigenSeleccionadoLote() {
		return origenSeleccionadoLote;
	}


	public void setOrigenSeleccionadoLote(HtmlSelectOneMenu origenSeleccionadoLote) {
		this.origenSeleccionadoLote = origenSeleccionadoLote;
	}


	public boolean isModificarFechasEjercicios() {
		return modificarFechasEjercicios;
	}


	public void setModificarFechasEjercicios(boolean modificarFechasEjercicios) {
		this.modificarFechasEjercicios = modificarFechasEjercicios;
	}


	public String getNumeroImputaAAgregar() {
		return numeroImputaAAgregar;
	}


	public void setNumeroImputaAAgregar(String numeroImputaAAgregar) {
		this.numeroImputaAAgregar = numeroImputaAAgregar;
	}


	public String getListaCuentas() {
		return listaCuentas;
	}


	public void setListaCuentas(String listaCuentas) {
		this.listaCuentas = listaCuentas;
	}


	public String getAproximadoHastaAsi() {
		return aproximadoHastaAsi;
	}


	public void setAproximadoHastaAsi(String aproximadoHastaAsi) {
		this.aproximadoHastaAsi = aproximadoHastaAsi;
	}


	public String getAproximadoHastaLot() {
		return aproximadoHastaLot;
	}


	public void setAproximadoHastaLot(String aproximadoHastaLot) {
		this.aproximadoHastaLot = aproximadoHastaLot;
	}


	public String getImporteAsiento() {
		return importeAsiento;
	}


	public void setImporteAsiento(String importeAsiento) {
		this.importeAsiento = importeAsiento;
	}


	public String getImporteLote() {
		return importeLote;
	}


	public void setImporteLote(String importeLote) {
		this.importeLote = importeLote;
	}


	public boolean isImporteSoloEnDebeAsi() {
		return importeSoloEnDebeAsi;
	}


	public void setImporteSoloEnDebeAsi(boolean importeSoloEnDebeAsi) {
		this.importeSoloEnDebeAsi = importeSoloEnDebeAsi;
	}


	public boolean isImporteSoloEnDebeLot() {
		return importeSoloEnDebeLot;
	}


	public void setImporteSoloEnDebeLot(boolean importeSoloEnDebeLot) {
		this.importeSoloEnDebeLot = importeSoloEnDebeLot;
	}


	public boolean isImporteSoloEnHaberAsi() {
		return importeSoloEnHaberAsi;
	}


	public void setImporteSoloEnHaberAsi(boolean importeSoloEnHaberAsi) {
		this.importeSoloEnHaberAsi = importeSoloEnHaberAsi;
	}


	public boolean isImporteSoloEnHaberLot() {
		return importeSoloEnHaberLot;
	}


	public void setImporteSoloEnHaberLot(boolean importeSoloEnHaberLot) {
		this.importeSoloEnHaberLot = importeSoloEnHaberLot;
	}


	public boolean isAgregarL() {
		return agregarL;
	}


	public void setAgregarL(boolean agregarL) {
		this.agregarL = agregarL;
	}


	public boolean isAperturaPermitida() {
		return aperturaPermitida;
	}


	public void setAperturaPermitida(boolean aperturaPermitida) {
		this.aperturaPermitida = aperturaPermitida;
	}


	public boolean isCierrePermitido() {
		return cierrePermitido;
	}


	public void setCierrePermitido(boolean cierrePermitido) {
		this.cierrePermitido = cierrePermitido;
	}


	public String getListaDeEliminadosEnString() {
		return listaDeEliminadosEnString;
	}


	public void setListaDeEliminadosEnString(String listaDeEliminadosEnString) {
		this.listaDeEliminadosEnString = listaDeEliminadosEnString;
	}


	public String getIdAsientoABuscarEnAsiento() {
		return idAsientoABuscarEnAsiento;
	}


	public void setIdAsientoABuscarEnAsiento(String idAsientoABuscarEnAsiento) {
		this.idAsientoABuscarEnAsiento = idAsientoABuscarEnAsiento;
	}


	public String getIdAsientoABuscarEnLote() {
		return idAsientoABuscarEnLote;
	}


	public void setIdAsientoABuscarEnLote(String idAsientoABuscarEnLote) {
		this.idAsientoABuscarEnLote = idAsientoABuscarEnLote;
	}


	public List getIdDeCuentasDisponibles() {
		return idDeCuentasDisponibles;
	}


	public void setIdDeCuentasDisponibles(List idDeCuentasDisponibles) {
		this.idDeCuentasDisponibles = idDeCuentasDisponibles;
	}


	public Date getFechaInicioImportar() {
		return fechaInicioImportar;
	}


	public void setFechaInicioImportar(Date fechaInicioImportar) {
		this.fechaInicioImportar = fechaInicioImportar;
	}


	public Date getFechaCierreImportar() {
		return fechaCierreImportar;
	}


	public void setFechaCierreImportar(Date fechaCierreImportar) {
		this.fechaCierreImportar = fechaCierreImportar;
	}

}
