package com.bizitglobal.webapp.faces.beans.proveedores;

import java.io.Serializable;
import java.sql.Timestamp;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.faces.context.FacesContext;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.log4j.Logger;
import org.apache.myfaces.custom.schedule.HtmlSchedule;
import org.apache.myfaces.custom.schedule.ScheduleMouseEvent;
import org.apache.myfaces.custom.schedule.model.DefaultScheduleEntry;
import org.apache.myfaces.custom.schedule.model.SimpleScheduleModel;
import org.apache.myfaces.renderkit.html.util.AddResource;
import org.apache.myfaces.renderkit.html.util.AddResourceFactory;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.general.negocio.ModalidadPago;
import com.bizitglobal.tarjetafiel.proveedores.exception.ProveedorException;
import com.bizitglobal.tarjetafiel.proveedores.negocio.Proveedor;
import com.bizitglobal.tarjetafiel.proveedores.negocio.ProveedorCuotaComprobante;
import com.bizitglobal.webapp.faces.beans.error.ErrorBean;
import com.bizitglobal.webapp.faces.beans.proveedores.wrappers.ProveedorCalendar;
import com.bizitglobal.webapp.faces.service.proveedores.ProveedoresServiceFaces;
import com.bizitglobal.webapp.faces.util.Session;


@SuppressWarnings({"rawtypes","unchecked"})
public class BindingScheduleExampleHandler extends ScheduleExampleHandler implements Serializable {
	private static final long serialVersionUID = 763734566918182549L;
	private static final Logger log = Logger.getLogger(BindingScheduleExampleHandler.class);
	private static final ProveedoresServiceFaces service = new ProveedoresServiceFaces();
	private HtmlSchedule schedule;
	private String mouseActionText;
	private Calendar fecha;
	private String[] meses = { "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre",
			"Diciembre" };
	private String mes;
	private int numeroMes;
	private Map dias = new HashMap();
	private Map codigoDias = new HashMap();
	private Map proveedorCalendarioMap = new HashMap();
	private Map diaPagoProveedoresMap = new HashMap();
	private String proveedorSeleccionado;
	private String idProveedorSeleccionado;
	private String cuitProveedorSeleccionado;

	// Litado de todos los proveedores del sistema
	List proveedores = new ArrayList();

	// Listas para los proveedores calendario y el popup.

	private List listaProveedoresPorFecha = null;
	private List listaCuotasPorProveedor = null;

	// Indican que tablas serán mostradas segun el link que se clicke.
	private boolean mostrarFNI = false;
	private boolean mostrarFI = false;
	private boolean mostrarOP = false;

	// Fecha que se clickea, para mostrar en el title del popup.
	private String fechaClikeada = null;

	// Almacenamos la ultima fecha clikeada para actualizar.
	private Date ultimaFechaClikeada;

	// Indica si se debera o no mostrar la pagina de imputaciones.
	private boolean mostrarImputaciones;

	// Ruta donde se deberá redirigir.
	private String rutaRedireccion = "/tarjetafiel/proveedores/calendario/popup/calendarioPopup.jsf";


	public BindingScheduleExampleHandler() {

		super();
		schedule = new HtmlSchedule();
		mes = null;
		fecha = new GregorianCalendar();
		fecha.setTime(new Date());
		numeroMes = fecha.get(Calendar.MONTH);
		mostrarImputaciones = false;

	}


	public String getMouseActionText() {
		return mouseActionText;
	}


	public HtmlSchedule getSchedule() {
		return schedule;
	}


	public void setSchedule(HtmlSchedule schedule) {
		this.schedule = schedule;
	}


	public Date getFecha() {
		return fecha.getTime();
	}


	public void setFecha(Date fecha) {
		this.fecha.setTime(fecha);
	}


	public String getMes() {
		return meses[numeroMes];
	}


	public void setMes(String mes) {
		this.mes = mes;
	}


	public List getListaProveedoresPorFecha() {
		return listaProveedoresPorFecha;
	}


	public void setListaProveedoresPorFecha(List listaProveedoresPorFecha) {
		this.listaProveedoresPorFecha = listaProveedoresPorFecha;
	}


	public boolean getMostrarFI() {
		return mostrarFI;
	}


	public void setMostrarFI(boolean mostrarFI) {
		this.mostrarFI = mostrarFI;
	}


	public boolean getMostrarFNI() {
		return mostrarFNI;
	}


	public void setMostrarFNI(boolean mostrarFNI) {
		this.mostrarFNI = mostrarFNI;
	}


	public boolean getMostrarOP() {
		return mostrarOP;
	}


	public void setMostrarOP(boolean mostrarOP) {
		this.mostrarOP = mostrarOP;
	}


	public List getListaCuotasPorProveedor() {
		return listaCuotasPorProveedor;
	}


	public void setListaCuotasPorProveedor(List listaCuotasPorProveedor) {
		this.listaCuotasPorProveedor = listaCuotasPorProveedor;
	}


	public String getFechaClikeada() {
		return fechaClikeada;
	}


	public void setFechaClikeada(String fechaClikeada) {
		this.fechaClikeada = fechaClikeada;
	}


	public boolean isMostrarImputaciones() {
		return mostrarImputaciones;
	}


	public void setMostrarImputaciones(boolean mostrarImputaciones) {
		this.mostrarImputaciones = mostrarImputaciones;
	}


	public String getLastClickedDate() {
		if (getSchedule() == null
				|| getSchedule().getLastClickedDateAndTime() == null)
			return "no date/time clicked";
		return getSchedule().getLastClickedDateAndTime().toString();
	}


	public String scheduleAction() {
		log.info("The schedule was clicked");
		log.info("selected entry: " + schedule.getModel().getSelectedEntry());
		return "success";
	}


	public void scheduleClicked(ScheduleMouseEvent event) {
		StringBuffer buffer = new StringBuffer();
		switch (event.getEventType()) {
		case ScheduleMouseEvent.SCHEDULE_BODY_CLICKED:
			inicializarPopup(event);
			break;
		case ScheduleMouseEvent.SCHEDULE_HEADER_CLICKED:
			inicializarPopup(event);
			break;
		case ScheduleMouseEvent.SCHEDULE_ENTRY_CLICKED:
			break;
		default:
			buffer.append("No se registro ningún evento");
		}
		mouseActionText = buffer.toString();
	}


	public void refresh() {

		// Inicializamos el modelo nuevamente.
		setModel(new SimpleScheduleModel());
		getModel().setMode(3);
		getModel().setSelectedDate(fecha.getTime());

		// Obtenemos la fecha actual desde el modelo.
		Calendar fechaActual = new GregorianCalendar();
		fechaActual.setTime(getModel().getSelectedDate());

		// Calculamos al fecha base, osea el primer dia del cual deberiamos empezar a iterar.
		Calendar fechaBase = GregorianCalendar.getInstance();
		fechaBase.set(fechaActual.get(Calendar.YEAR), fechaActual.get(Calendar.MONTH), 1);

		List detallesComprobantesList = service.getProveedorCuotaComprobantesDao().listarCuotaComprobante(
				new Filtro().getTO_DATE(new Timestamp(fechaBase.getTime().getTime())));
		List detallesOrdenesPagoList = service.getProveedorCuotaComprobantesDao().listarCuotaOrdenPago(
				new Filtro().getTO_DATE(new Timestamp(fechaBase.getTime().getTime())));

		// map que contendra un objeto proveedorCalendar cuya clave es el idProveedor
		proveedorCalendarioMap = new HashMap();
		boolean band = true;

		Long idProveedorComparador = new Long(-1);
		Long idprovrAux = null;
		float sumaImp = 0;
		float sumaOP = 0;
		int indiceDesde = 0;
		int indiceHasta = 0;
		String diaPago = "";

		// recorremos los detalles de comprobantes( que estan ordenados por idProveedor) , vamos sumarizando los detalles finalmente guardamos la
		// sumatoria,
		// una lista con los detalles correspondientes a la sumatoria y los datos del proveddor
		Iterator detallesComprobantes = detallesComprobantesList.iterator();
		while (detallesComprobantes.hasNext()) {
			ProveedorCuotaComprobante element = (ProveedorCuotaComprobante) detallesComprobantes.next();
			if (band) {
				// esto es para obtener en primera instancia algo con q comparar
				idProveedorComparador = element.getIdProveedor();
				band = false;
			}
			if (element.getIdProveedor().longValue() == idProveedorComparador.longValue()) {
				sumaImp += element.getImporte().floatValue() - element.getSumaImp().floatValue();
				idprovrAux = element.getIdProveedor();
				indiceHasta++;
			}
			else {
				Proveedor proveedor = null;
				// listaProveedorCalendario.add(new
				// ProveedorCalendar(null,idprovrAux,null,null,sumaImp,0,detallesComprobantesList.subList(indiceDesde,indiceHasta),null));
				try {
					Filtro filtro = new Filtro();
					filtro.agregarCampoOperValor("idProveedor", Filtro.IGUAL, idprovrAux.toString());
					List proveedorList = service.getProveedorService().getProveedores(filtro);
					if (proveedorList != null) {
						proveedor = (Proveedor) proveedorList.get(0);
						ModalidadPago modalidad = proveedor.getModalidadPago();
						diaPago = modalidad.getEsDiaCalendario();
						if (modalidad.getNroDia().intValue() < 10) {
							diaPago = diaPago + "0" + modalidad.getNroDia().toString();
						}
						else
							diaPago = diaPago + modalidad.getNroDia().toString();
						if (modalidad.getNombreDia() == null)
							diaPago = diaPago + "0";
						else
							diaPago = diaPago + (String) codigoDias.get(modalidad.getNombreDia());

					}

				} catch (ProveedorException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				proveedorCalendarioMap.put(idprovrAux, new ProveedorCalendar(null, idprovrAux, proveedor.getRazonSocial(), proveedor.getAlias(),
						diaPago, sumaImp, 0, detallesComprobantesList.subList(indiceDesde, indiceHasta), null));
				sumaImp = element.getImporte().floatValue() - element.getSumaImp().floatValue();
				indiceDesde = indiceHasta;
				indiceHasta++;
			}
			idProveedorComparador = element.getIdProveedor();
		}
		// agrego a la lista el que falta
		Proveedor proveedor = null;
		if (idprovrAux != null) {
			try {
				Filtro filtro = new Filtro();
				filtro.agregarCampoOperValor("idProveedor", Filtro.IGUAL, idprovrAux.toString());
				List proveedorList = service.getProveedorService().getProveedores(filtro);
				if (proveedorList != null) {
					proveedor = (Proveedor) proveedorList.get(0);
					ModalidadPago modalidad = proveedor.getModalidadPago();
					diaPago = modalidad.getEsDiaCalendario();
					if (modalidad.getNroDia().intValue() < 10) {
						diaPago = diaPago + "0" + modalidad.getNroDia().toString();
					}
					else
						diaPago = diaPago + modalidad.getNroDia().toString();
					if (modalidad.getNombreDia() == null)
						diaPago = diaPago + "0";
					else
						diaPago = diaPago + (String) codigoDias.get(modalidad.getNombreDia());

				}

			} catch (ProveedorException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			proveedorCalendarioMap.put(idprovrAux, new ProveedorCalendar(null, idprovrAux, proveedor.getRazonSocial(), proveedor.getAlias(), diaPago,
					sumaImp, 0, detallesComprobantesList.subList(indiceDesde, indiceHasta), null));
		}

		// repetimos lo anterior pero ahora para ordenes de pago
		indiceDesde = 0;
		indiceHasta = 0;
		diaPago = "";
		Iterator detallesOrdenesPagos = detallesOrdenesPagoList.iterator();
		while (detallesOrdenesPagos.hasNext()) {
			ProveedorCuotaComprobante element = (ProveedorCuotaComprobante) detallesOrdenesPagos.next();
			if (band) {
				idProveedorComparador = element.getIdProveedor();
				band = false;
			}
			if (element.getIdProveedor().longValue() == idProveedorComparador.longValue()) {
				sumaOP += element.getImporte().floatValue() - element.getSumaImpOpFacImputadas().floatValue();
				idprovrAux = element.getIdProveedor();
				indiceHasta++;
			}
			else {
				proveedor = null;
				if (proveedorCalendarioMap.get(idprovrAux) == null) {
					try {
						Filtro filtro = new Filtro();
						filtro.agregarCampoOperValor("idProveedor", Filtro.IGUAL, idprovrAux.toString());
						List proveedorList = service.getProveedorService().getProveedores(filtro);
						if (proveedorList != null) {
							proveedor = (Proveedor) proveedorList.get(0);
							ModalidadPago modalidad = proveedor.getModalidadPago();
							diaPago = modalidad.getEsDiaCalendario();
							if (modalidad.getNroDia().intValue() < 10) {
								diaPago = diaPago + "0" + modalidad.getNroDia().toString();
							}
							else
								diaPago = diaPago + modalidad.getNroDia().toString();
							if (modalidad.getNombreDia() == null)
								diaPago = diaPago + "0";
							else
								diaPago = diaPago + (String) codigoDias.get(modalidad.getNombreDia());
						}
					} catch (ProveedorException e) {
						e.printStackTrace();
					}
					proveedorCalendarioMap.put(idprovrAux, new ProveedorCalendar(null, idprovrAux, proveedor.getRazonSocial(), proveedor.getAlias(),
							diaPago, 0, sumaOP, null, detallesOrdenesPagoList.subList(indiceDesde, indiceHasta)));
				}
				else {
					ProveedorCalendar prov = (ProveedorCalendar) proveedorCalendarioMap.get(idprovrAux);
					prov.setOpTotal(sumaOP);
					prov.setOp(detallesOrdenesPagoList.subList(indiceDesde, indiceHasta));
					proveedorCalendarioMap.put(idprovrAux, prov);
				}
				sumaOP = element.getImporte().floatValue() - element.getSumaImpOpFacImputadas().floatValue();
				indiceDesde = indiceHasta;
				indiceHasta++;
			}
			idProveedorComparador = element.getIdProveedor();
		}// fin while
			// agrego a la lista el que falta

		proveedor = null;
		// listaProveedorCalendario.add(new
		// ProveedorCalendar(null,idprovrAux,null,null,sumaImp,0,detallesComprobantesList.subList(indiceDesde,indiceHasta),null));
		if (idprovrAux != null) {
			if (proveedorCalendarioMap.get(idprovrAux) == null) {
				try {
					Filtro filtro = new Filtro();
					filtro.agregarCampoOperValor("idProveedor", Filtro.IGUAL, idprovrAux.toString());
					List proveedorList = service.getProveedorService().getProveedores(filtro);
					if (proveedorList != null) {
						proveedor = (Proveedor) proveedorList.get(0);
						ModalidadPago modalidad = proveedor.getModalidadPago();
						diaPago = modalidad.getEsDiaCalendario();
						if (modalidad.getNroDia().intValue() < 10) {
							diaPago = diaPago + "0" + modalidad.getNroDia().toString();
						}
						else
							diaPago = diaPago + modalidad.getNroDia().toString();
						if (modalidad.getNombreDia() == null)
							diaPago = diaPago + "0";
						else
							diaPago = diaPago + (String) codigoDias.get(modalidad.getNombreDia());
					}

				} catch (ProveedorException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				proveedorCalendarioMap.put(idprovrAux, new ProveedorCalendar(null, idprovrAux, proveedor.getRazonSocial(), proveedor.getAlias(),
						diaPago, 0, sumaOP, null, detallesOrdenesPagoList.subList(indiceDesde, indiceHasta)));
			}
			else {
				ProveedorCalendar prov = (ProveedorCalendar) proveedorCalendarioMap.get(idprovrAux);
				prov.setOpTotal(sumaOP);
				prov.setOp(detallesOrdenesPagoList.subList(indiceDesde, indiceHasta));
				proveedorCalendarioMap.put(idprovrAux, prov);
			}

		}

		Iterator iterador = proveedorCalendarioMap.entrySet().iterator();
		while (iterador.hasNext()) {
			Map.Entry e = (Map.Entry) iterador.next();
			// System.out.println(e.getKey() + " " + e.getValue());
			ProveedorCalendar element = (ProveedorCalendar) e.getValue();
			System.out
					.println("////////////////////////////////////////////////////////////////////////////////////////////////////////////////////");
			System.out.println("sumaTotalImp" + element.getFniTotal());
			System.out.println("DETALLES Imp****************** " + element.getIdProveedor().toString() + "proveedor" + element.getRazonSocial()
					+ "dia Pago" + element.getDiaPago());
			if (element.getFni() != null) {
				Iterator iter3 = element.getFni().iterator();
				while (iter3.hasNext()) {
					ProveedorCuotaComprobante element2 = (ProveedorCuotaComprobante) iter3.next();
					System.out.println("importe: " + element2.getImporte() + "sumaImp: " + element2.getSumaImp());
				}
			}

			System.out.println("sumaTotalOP" + element.getOpTotal());
			System.out.println("DETALLES OP****************** " + element.getIdProveedor().toString() + "proveedor" + element.getRazonSocial()
					+ "dia Pago" + element.getDiaPago());
			if (element.getOp() != null) {
				Iterator iter = element.getOp().iterator();
				while (iter.hasNext()) {
					ProveedorCuotaComprobante element2 = (ProveedorCuotaComprobante) iter.next();
					System.out.println("importe: " + element2.getImporte() + "sumaOP: " + element2.getSumaImpOpFacImputadas());
				}
			}

			System.out
					.println("////////////////////////////////////////////////////////////////////////////////////////////////////////////////////");
		}

		// ///////////////////ahora con el map anterior hacemos otro map cuya clave sera el dia de pago y valor una lista d e proveedores que hay que
		// pagarles ese dia

		diaPagoProveedoresMap = new HashMap();
		List temp = new ArrayList();
		Iterator itera = proveedorCalendarioMap.entrySet().iterator();
		while (itera.hasNext()) {
			Map.Entry e = (Map.Entry) itera.next();
			ProveedorCalendar element = (ProveedorCalendar) e.getValue();
			if (diaPagoProveedoresMap.get(element.getDiaPago()) == null) {
				// temp.clear();
				// temp.add(element);
				temp = new ArrayList();
				temp.add(element);
				diaPagoProveedoresMap.put(element.getDiaPago(), temp);
			} else {
				temp = ((List) diaPagoProveedoresMap.get(element.getDiaPago()));
				temp.add(element);
				diaPagoProveedoresMap.put(element.getDiaPago(), temp);
			}
		}

		proveedorCalendarioMap = null;

		String key1 = "";
		String key2 = "";
		List proveedoresCalendarList = new ArrayList();
		while (getModel().containsDate(fechaBase.getTime())) {
			int diaMes = fechaBase.get(Calendar.DATE);
			int diaDeSemana = fechaBase.get(Calendar.DAY_OF_WEEK_IN_MONTH);
			key1 = "S";
			if (diaMes < 10)
				key1 = key1 + "0" + String.valueOf(diaMes) + "0";
			else
				key1 = key1 + String.valueOf(diaMes) + "0";
			key2 = "N" + String.valueOf(diaDeSemana) + codigoDias.get(getDia(fechaBase));
			if (diaPagoProveedoresMap.get(key1) != null || diaPagoProveedoresMap.get(key2) != null) {
				if (diaPagoProveedoresMap.get(key1) != null)
					proveedoresCalendarList = (List) diaPagoProveedoresMap.get(key1);
				else
					proveedoresCalendarList = (List) diaPagoProveedoresMap.get(key2);
				if (proveedoresCalendarList != null) {
					Iterator iterator = proveedoresCalendarList.iterator();
					while (iterator.hasNext()) {
						ProveedorCalendar element = (ProveedorCalendar) iterator.next();
						DefaultScheduleEntry entry = new DefaultScheduleEntry();
						entry.setId(RandomStringUtils.randomNumeric(32));
						entry.setStartTime(fechaBase.getTime());
						entry.setEndTime(fechaBase.getTime());
						entry.setAllDay(true);
						entry.setTitle(element.getRazonSocial());
						entry.setDescription("FNI -> ($" + element.getFniText() + ") \n  OP  -> ($" + element.getOpText() + ")");
						getModel().addEntry(entry);
						getModel().refresh();

					}
				}
			}

			fechaBase.add(Calendar.DATE, 1);
		}

	}


	public String avanzar() {
		fecha.add(Calendar.MONTH, 1);
		numeroMes = fecha.get(Calendar.MONTH);
		getModel().setSelectedDate(fecha.getTime());
		refresh();
		return null;
	}


	public String retroceder() {
		fecha.add(Calendar.MONTH, -1);
		numeroMes = fecha.get(Calendar.MONTH);
		getModel().setSelectedDate(fecha.getTime());
		refresh();
		return null;
	}


	private String getDia(Calendar fecha) {
		String result = "";
		result += (fecha.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) ? "Domingo" : "";
		result += (fecha.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY) ? "Lunes" : "";
		result += (fecha.get(Calendar.DAY_OF_WEEK) == Calendar.TUESDAY) ? "Martes" : "";
		result += (fecha.get(Calendar.DAY_OF_WEEK) == Calendar.WEDNESDAY) ? "Miercoles" : "";
		result += (fecha.get(Calendar.DAY_OF_WEEK) == Calendar.THURSDAY) ? "Jueves" : "";
		result += (fecha.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY) ? "Viernes" : "";
		result += (fecha.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) ? "Sabado" : "";
		return result;
	}


	private void abrirPopupPorFecha() {
		String script = "popupCalendar('popup/calendarioPopup.jsf','',980,600);";
		FacesContext facesContext = FacesContext.getCurrentInstance();
		AddResource addResource = AddResourceFactory.getInstance(facesContext);
		addResource.addInlineScriptAtPosition(facesContext, AddResource.HEADER_BEGIN, script);

		// Borra el bean de imputaciones por si se entro anteriormente.
		ImputacionBean bean = (ImputacionBean) Session.getBean("ImputacionBean");
		bean.borrar();
	}


	public List cargarListaPorFecha(String key1, String key2) {

		if (diaPagoProveedoresMap.get(key1) != null)
			return (List) diaPagoProveedoresMap.get(key1);
		else
			return (List) diaPagoProveedoresMap.get(key2);

	}


	public String habilitarFNI() {
		log.info("Entrando a HabilitarFNI!!!");
		String fechaProveedorC = (Session.getRequestParameter("fechaProveedorCFNI")).toString();
		idProveedorSeleccionado = (Session.getRequestParameter("idProveedorFNI")).toString();
		proveedorSeleccionado = (Session.getRequestParameter("proveedorFNI")).toString();
		mostrarFNI = true;
		mostrarOP = false;
		Filtro filtro = new Filtro();
		filtro.agregarCampoOperValor("idProveedor", Filtro.IGUAL, idProveedorSeleccionado);
		try {
			List proveedor = service.getProveedorService().getProveedores(filtro);
			cuitProveedorSeleccionado = ((Proveedor) proveedor.get(0)).getCuit().toString();
			inicializarImputaciones(cuitProveedorSeleccionado);

			proveedor = null;
		} catch (ProveedorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		listaCuotasPorProveedor = cuotasPorProveedor(fechaProveedorC, 1, idProveedorSeleccionado);

		// Session.redirect(this.rutaRedireccion);
		return null;
	}


	public String habilitarOP() {
		log.info("Entrando a HabilitarOP!!!");
		String fechaProveedorC = (Session.getRequestParameter("fechaProveedorCOP")).toString();
		idProveedorSeleccionado = (Session.getRequestParameter("idProveedorOP")).toString();
		proveedorSeleccionado = (Session.getRequestParameter("proveedorOP")).toString();
		mostrarFNI = false;
		mostrarOP = true;
		Filtro filtro = new Filtro();
		filtro.agregarCampoOperValor("idProveedor", Filtro.IGUAL, idProveedorSeleccionado);
		try {
			List proveedor = service.getProveedorService().getProveedores(filtro);
			String cuit = ((Proveedor) proveedor.get(0)).getCuit().toString();
			inicializarImputaciones(cuit);
			proveedor = null;
		} catch (ProveedorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		listaCuotasPorProveedor = cuotasPorProveedor(fechaProveedorC, 3, idProveedorSeleccionado);

		// Session.redirect(this.rutaRedireccion);
		return null;
	}


	public void inicializarImputaciones(String cuit) {
		log.info("CUIT->" + cuit);
		ImputacionBean bean = (ImputacionBean) Session.getBean("ImputacionBean");
		bean.borrar();
		bean.setRutaRedireccion(this.rutaRedireccion);
		bean.setCuit(cuit);
		bean.validarCuit(null); // un validar con null???
	}


	public List cuotasPorProveedor(String fecha, int tipo, String idProv) {
		List result = new ArrayList();
		// tipo=1 -> fni, otro -> op

		if (diaPagoProveedoresMap.get(fecha) != null) {
			List lista = (List) diaPagoProveedoresMap.get(fecha);
			Iterator iter = lista.iterator();
			boolean encontrado = false;
			while (iter.hasNext() && !encontrado) {
				ProveedorCalendar element = (ProveedorCalendar) iter.next();
				if (element.getIdProveedor().toString().equals(idProv))
					encontrado = true;
				if (tipo == 1)
					result = element.getFni();

				else
					result = element.getOp();

			}

		}
		return result;
	}


	/*
	 * public void inicializarPopup(ScheduleMouseEvent event) { Date fechaClickeda = event.getClickedDate(); Format dateFormatUno = new
	 * SimpleDateFormat("dd/MM/yyyy"); Format dateFormatDos = new SimpleDateFormat("ddMMyyyy");
	 * 
	 * this.fechaClikeada = dateFormatUno.format(fechaClickeda).toString(); this.ultimaFechaClikeada = event.getClickedDate();
	 * 
	 * listaProveedoresPorFecha = cargarListaPorFecha(dateFormatDos.format(fechaClickeda));
	 * 
	 * if(!listaProveedoresPorFecha.isEmpty()) { mostrarFNI = false; mostrarFI = false; mostrarOP = false; mostrarImputaciones = false;
	 * abrirPopupPorFecha(); } }
	 */

	public void inicializarPopup(ScheduleMouseEvent event) {
		String key1 = "";
		String key2 = "";

		Date fechaClickeda = event.getClickedDate();
		Format dateFormatUno = new SimpleDateFormat("dd/MM/yyyy");
		this.fechaClikeada = dateFormatUno.format(fechaClickeda).toString();
		GregorianCalendar fecha = new GregorianCalendar();

		fecha.setTime(fechaClickeda);
		int diaMes = fecha.get(Calendar.DATE);
		int diaDeSemana = fecha.get(Calendar.DAY_OF_WEEK_IN_MONTH);
		key1 = "S";
		if (diaMes < 10)
			key1 = key1 + "0" + String.valueOf(diaMes) + "0";
		else
			key1 = key1 + String.valueOf(diaMes) + "0";
		key2 = "N" + String.valueOf(diaDeSemana) + codigoDias.get(getDia(fecha));
		listaProveedoresPorFecha = cargarListaPorFecha(key1, key2);

		if (!listaProveedoresPorFecha.isEmpty()) {
			mostrarFNI = false;
			mostrarFI = false;
			mostrarOP = false;
			mostrarImputaciones = false;
			abrirPopupPorFecha();
		}
	}


	public String verImputaciones() {
		mostrarImputaciones = true;
		Session.redirect(this.rutaRedireccion);
		return null;
	}


	public String ocultarImputaciones() {
		mostrarImputaciones = false;
		Session.redirect(this.rutaRedireccion);
		return null;
	}


	public String cerrarPopupImputaciones() {
		log.info("ENTRANDO A cerrarPopupImputaciones!!!");
		ImputacionBean bean = (ImputacionBean) Session.getBean("ImputacionBean");
		bean.getPopup().setMostrar(false);
		return null;
	}


	public String grabarImputacion() {
		ImputacionBean bean = (ImputacionBean) Session.getBean("ImputacionBean");
		ErrorBean errorBean = (ErrorBean) Session.getBean("ErrorBean");
		bean.setRutaRedireccion(this.rutaRedireccion);
		bean.grabarImputado();
		refresh();

		Format dateFormatDos = new SimpleDateFormat("ddMMyyyy");
		// listaProveedoresPorFecha = cargarListaPorFecha(dateFormatDos.format(this.ultimaFechaClikeada));

		if (errorBean.cantidad() == 0) {
			mostrarImputaciones = false;
			mostrarFNI = false;
			mostrarFI = false;
			mostrarOP = false;
		}

		return null;
	}


	public String irACalendarioPagos() {
		try {
			proveedores.clear();
			// Obtenemos la lista de proveedores de la base de datos.
			proveedores = service.getProveedorService().getProveedores(new Filtro());
		} catch (ProveedorException e) {
			e.printStackTrace();
		}
		dias.put("0", "-");
		dias.put("1", "Lunes");
		dias.put("2", "Martes");
		dias.put("3", "Miercoles");
		dias.put("4", "Jueves");
		dias.put("5", "Viernes");
		dias.put("6", "Sabado");
		dias.put("7", "Domingo");
		codigoDias.put("-", "0");
		codigoDias.put("Lunes", "1");
		codigoDias.put("Martes", "2");
		codigoDias.put("Miercoles", "3");
		codigoDias.put("Jueves", "4");
		codigoDias.put("Viernes", "5");
		codigoDias.put("Sabado", "6");
		codigoDias.put("Domingo", "7");
		refresh();
		return "calendarioDePagos";
	}


	public String getCuitProveedorSeleccionado() {
		return cuitProveedorSeleccionado;
	}


	public void setCuitProveedorSeleccionado(String cuitProveedorSeleccionado) {
		this.cuitProveedorSeleccionado = cuitProveedorSeleccionado;
	}


	public String getIdProveedorSeleccionado() {
		return idProveedorSeleccionado;
	}


	public void setIdProveedorSeleccionado(String idProveedorSeleccionado) {
		this.idProveedorSeleccionado = idProveedorSeleccionado;
	}


	public String getProveedorSeleccionado() {
		return proveedorSeleccionado;
	}


	public void setProveedorSeleccionado(String proveedorSeleccionado) {
		this.proveedorSeleccionado = proveedorSeleccionado;
	}

}
