package com.bizitglobal.webapp.faces.beans.contabilidad;

import java.util.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

import org.apache.log4j.Logger;

import com.bizitglobal.tarjetafiel.contabilidad.exception.AsientoClienteException;
import com.bizitglobal.tarjetafiel.contabilidad.exception.AsientoComercioException;
import com.bizitglobal.tarjetafiel.contabilidad.exception.AsientoDetalleException;
import com.bizitglobal.tarjetafiel.contabilidad.exception.AsientoProveedorException;
import com.bizitglobal.tarjetafiel.contabilidad.exception.LoteDetalleException;
import com.bizitglobal.tarjetafiel.contabilidad.exception.LoteException;
import com.bizitglobal.tarjetafiel.contabilidad.exception.PlanCuentaDosException;
import com.bizitglobal.tarjetafiel.contabilidad.negocio.AsientoCliente;
import com.bizitglobal.tarjetafiel.contabilidad.negocio.AsientoClienteDetalle;
import com.bizitglobal.tarjetafiel.contabilidad.negocio.AsientoComercio;
import com.bizitglobal.tarjetafiel.contabilidad.negocio.AsientoComercioDetalle;
import com.bizitglobal.tarjetafiel.contabilidad.negocio.AsientoProveedor;
import com.bizitglobal.tarjetafiel.contabilidad.negocio.AsientoProveedorDetalle;
import com.bizitglobal.tarjetafiel.contabilidad.negocio.Importable;
import com.bizitglobal.tarjetafiel.contabilidad.negocio.Lote;
import com.bizitglobal.tarjetafiel.contabilidad.negocio.LoteDetalle;
import com.bizitglobal.tarjetafiel.contabilidad.negocio.PlanCuentaDos;
import com.bizitglobal.webapp.faces.beans.BaseBean;
import com.bizitglobal.webapp.faces.beans.util.PaginaDeRegistros;
import com.bizitglobal.webapp.faces.util.Session;


@SuppressWarnings({"rawtypes","unchecked","unused"})
public class ImportacionAsientosBean extends BaseBean {
	private static final Logger log = Logger.getLogger(ImportacionAsientosBean.class);
	public static int renglon = 0;
	public static String ORIGEN_PROVEEDOR = "Proveedores";

	private int cantidadAImportar;

	private int origen;
	private List renglones;
	private String titulo;
	private Long idEmpresa;
	private Long idEjercicio;
	private Date inicioEjercicio;
	private Date finEjercicio;
	private PaginaDeRegistros paginador;
	private boolean todos;
	private List objetosAImportar;


	public ImportacionAsientosBean() {
	}


	public void accederAVistaPopap() {
		cargarDatosCuentaParaPopap();
		String path = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
		path += "/tarjetafiel/contabilidad/popup/importacionPopup.jsf";
		ejecutarJavaScript("popup('" + path + "',900,600), 'titlebar=no';");
	}


	public void cargarDatosCuentaParaPopap() {
		renglones = new ArrayList();
		try {
			if (this.origen == 0) {
				List resul = contabilidadService.getAsientoProveedorService().getAsientoProveedorImportables(inicioEjercicio, finEjercicio);
				//paginador = new PaginaDeRegistros(30, resul);
				Iterator i = resul.iterator();//paginador.getPrimeraPagina().iterator();
				while (i.hasNext()) {
					Importable a = (Importable) i.next();
					WrapperDetalleImportacion w = new WrapperDetalleImportacion(a);
					renglones.add(w);
				}
			}

			if (this.origen == 1) {
				List resul = contabilidadService.getAsientoClienteService().getAsientoClienteImportables(inicioEjercicio, finEjercicio);
				paginador = new PaginaDeRegistros(30, resul);
				Iterator i = paginador.getPrimeraPagina().iterator();
				while (i.hasNext()) {
					Importable a = (Importable) i.next();
					WrapperDetalleImportacion w = new WrapperDetalleImportacion(a);
					renglones.add(w);
				}
			}

			if (this.origen == 2) {
				List resul = contabilidadService.getAsientoComercioService().getAsientoComercioImportables(inicioEjercicio, finEjercicio);
				paginador = new PaginaDeRegistros(30, resul);
				Iterator i = paginador.getPrimeraPagina().iterator();
				while (i.hasNext()) {
					Importable a = (Importable) i.next();
					WrapperDetalleImportacion w = new WrapperDetalleImportacion(a);
					renglones.add(w);
				}
			}

		} catch (AsientoDetalleException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
	}


	public boolean getHayAsientos() {
		return renglones.isEmpty();
	}


	public String primerRegistroAsiento() {
		renglones.clear();
		Iterator i = paginador.getPrimeraPagina().iterator();
		while (i.hasNext()) {
			Importable a = (Importable) i.next();
			WrapperDetalleImportacion w;
			try {
				w = new WrapperDetalleImportacion(a);
				renglones.add(w);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}


	public String ultimoRegistroAsiento() {
		renglones.clear();
		Iterator i = paginador.getUltimaPagina().iterator();
		while (i.hasNext()) {
			Importable a = (Importable) i.next();
			WrapperDetalleImportacion w;
			try {
				w = new WrapperDetalleImportacion(a);
				renglones.add(w);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}


	public String siguienteRegistroAsiento() {
		renglones.clear();
		Iterator i = paginador.getPaginaSiguiente().iterator();
		while (i.hasNext()) {
			Importable a = (Importable) i.next();
			WrapperDetalleImportacion w;
			try {
				w = new WrapperDetalleImportacion(a);
				renglones.add(w);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}


	public String anteriorRegistroAsiento() {
		renglones.clear();
		Iterator i = paginador.getPaginaAnterior().iterator();
		while (i.hasNext()) {
			Importable a = (Importable) i.next();
			WrapperDetalleImportacion w;
			try {
				w = new WrapperDetalleImportacion(a);
				renglones.add(w);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}


	public void cambiarPagina(ValueChangeEvent e) {
		paginaDeAsiento();
	}


	public String paginaDeAsiento() {
		renglones.clear();
		Iterator i = paginador.getPagina(
				((Long) paginador.getPagSeleccionada().getValue()).intValue()).iterator();
		while (i.hasNext()) {
			Importable a = (Importable) i.next();
			WrapperDetalleImportacion w;
			try {
				w = new WrapperDetalleImportacion(a);
				renglones.add(w);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}


	public void borrar() {
		error.borrar();
		idEjercicio = new Long(0);
		idEmpresa = new Long(0);
		titulo = "";
		paginador = new PaginaDeRegistros();
		renglones = new ArrayList();
		cantidadAImportar = 0;
		objetosAImportar = new ArrayList();
	}


	public String inicializar(Long idEmpresa, Long idEjercicio, int origen, Date inicioEjercicio, Date finEjercicio) {
		borrar();
		this.idEmpresa = idEmpresa;
		this.idEjercicio = idEjercicio;
		this.origen = origen;
		this.inicioEjercicio = inicioEjercicio;
		this.finEjercicio = finEjercicio;
		accederAVistaPopap();
		return null;
	}
	
	public int inicializarSegundoPlano(Long idEmpresa, Long idEjercicio, int origen, Date inicioEjercicio, Date finEjercicio) {
		borrar();
		this.idEmpresa = idEmpresa;
		this.idEjercicio = idEjercicio;
		this.origen = origen;
		this.inicioEjercicio = inicioEjercicio;
		this.finEjercicio = finEjercicio;
		cargarDatosCuentaParaPopap();
		Iterator iterRe = renglones.iterator();
		while (iterRe.hasNext()) {
			WrapperDetalleImportacion impo = (WrapperDetalleImportacion) iterRe.next();
			impo.setSeleccionado(true);
		}
		return importar();
	}


	public boolean validar() {
		// TODO Auto-generated method stub
		return false;
	}


	public String mostrarResumenCuenta() {
		error.borrar();
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
		FacesContext facesContext = FacesContext.getCurrentInstance();
		String javaScriptText = "window.opener.recargar(); window.close();";
		ejecutarJavaScript(javaScriptText);
		return null;
	}

	public class WrapperDetalleImportacion {

		private Importable importable;
		private int nroRenglon;
		private boolean seleccionado;


		public WrapperDetalleImportacion(Importable importable) throws Exception {
			super();
			this.importable = importable;
			nroRenglon = ++renglon;
			seleccionado = false;
		}


		public int getNroRenglon() {
			return nroRenglon;
		}


		public void setNroRenglon(int nroRenglon) {
			this.nroRenglon = nroRenglon;
		}


		public Importable getImportable() {
			return importable;
		}


		public void setImportable(Importable importable) {
			this.importable = importable;
		}


		public boolean isSeleccionado() {
			return seleccionado;
		}


		public void setSeleccionado(boolean seleccionado) {
			this.seleccionado = seleccionado;
		}

	}


	public int importar() {
		cantidadAImportar = 0;
		Iterator iterRe = renglones.iterator();
		while (iterRe.hasNext()) {
			WrapperDetalleImportacion impo = (WrapperDetalleImportacion) iterRe.next();
			if (impo.isSeleccionado()) {
				cantidadAImportar++;
			}
		}
		ContexOrigen contexOrigen = new ContexOrigen();
		switch (origen) {
		case 0:
			contexOrigen.setOrigenImportable(new OrigenImportacionProveedores());
			break;
		case 1:
			contexOrigen.setOrigenImportable(new OrigenImportacionClientes());
			break;
		case 2:
			contexOrigen.setOrigenImportable(new OrigenImportacionComercio());
			break;
		default:
			break;
		}

		int cuantosVan = 0;
		try {
			AsientosBean be = (AsientosBean) Session.getBean("AsientosBean");
			// PROCESO DE IMPORTACION!!!!
			// //Primero: crear Objeto Lote con los datos de WrapperImportableAsiento que se encuentren seleccionados
			objetosAImportar = new ArrayList();
			
			Iterator iterRenglones = renglones.iterator();
			while (iterRenglones.hasNext()) {
				WrapperDetalleImportacion importable = (WrapperDetalleImportacion) iterRenglones.next();
				if (importable.isSeleccionado()) {
					cuantosVan++;
					Lote lo = crearLote(importable.getImportable());
					List listaDetalles = contexOrigen.recuperarDetalleLotes(lo, importable.getImportable());
					contabilidadService.getLoteService().grabarLote(lo);
					Iterator iteDet = listaDetalles.iterator();
					while (iteDet.hasNext()) {
						LoteDetalle loDet = (LoteDetalle) iteDet.next();
						System.out.println(loDet.getImporte() + loDet.getLeyenda() + loDet.getFechaCarga() + loDet.getNumeroImputa());
						contabilidadService.getLoteDetalleService().grabar(loDet);
					}
					// contabilidadService.getLoteService().impactarOrigenContab(lo.getIdAsiento(), importable.getImportable().getIdObjetoOrigen(),
					// importable.getImportable().getIdNroComprobante());
					// contabilidadService.getAsientoProveedorService().actualizarAsientoProveedor((AsientoProveedor)importable.getImportable());
					contexOrigen.impactarOrigenContab(lo.getIdAsiento(), importable.getImportable().getIdObjetoOrigen(), importable.getImportable()
							.getIdNroComprobante());
					contexOrigen.actualizarAsiento(importable);
				}
			}
			// cerrar la ventana
			FacesContext facesContext = FacesContext.getCurrentInstance();
			String javaScriptText = "window.close();";
			ejecutarJavaScript(javaScriptText);
		} catch (LoteException e) {
			e.printStackTrace();
		} catch (LoteDetalleException e2) {
			e2.printStackTrace();
		}

		return cuantosVan;
	}


	public Lote crearLote(Importable importable) {
		Long id = new Long(contabilidadService.getLoteService().getLastIdDeLotes().intValue());
		String concepto = "";
		if (importable.getConcepto() != null)
			concepto = importable.getConcepto();
		Lote lot = new Lote(Integer.valueOf(idEmpresa.intValue()), Integer.valueOf(idEjercicio.intValue()), id, concepto, new Integer(2),
				importable.getFechaContab(), new Date(Calendar.getInstance().getTimeInMillis()), new Timestamp(Calendar.getInstance()
						.getTimeInMillis()), importable.getOperador());
		return lot;
	}


	public List getRenglones() {
		return renglones;
	}


	public void setRenglones(List renglones) {
		this.renglones = renglones;
	}


	public String inicializar() {
		// TODO Auto-generated method stub
		return null;
	}


	public String getTitulo() {
		String cad = "";
		switch (origen) {
		case 0:
			cad = "Proveedores";
			break;
		}
		return "Importar Asientos de " + cad;
	}


	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}


	public PaginaDeRegistros getPaginador() {
		return paginador;
	}


	public void setPaginador(PaginaDeRegistros paginador) {
		this.paginador = paginador;
	}

	public class OrigenImportacionProveedores implements OrigenImportable {

		public List recuperarDetallesDelLote(Lote lot, Importable importable) {
			List listaDetalles = new ArrayList();

			try {
				List details = contabilidadService.getAsientoProveedorService()
						.getDetallesAsientoProveedorImportado(importable.getIdNroComprobante());
				int i = 0;
				Iterator it = details.iterator();
				while (it.hasNext()) {
					AsientoProveedorDetalle asi = (AsientoProveedorDetalle) it.next();
					// recupero el centro de costo
					i++;
					PlanCuentaDos plan = contabilidadService.getPlanCuentaDosService().leerPlanCuenta(asi.getNumeroImputa());
					Integer reng = new Integer(i);

					LoteDetalle loteDet = new LoteDetalle(Integer.valueOf(idEmpresa.intValue()), Integer.valueOf(idEjercicio.intValue()), reng,
							Integer.valueOf(lot.getIdAsiento().intValue()), asi.getNumeroImputa(), asi.getSigno(), asi.getImporte(),
							asi.getLeyenda(), lot.getFechaContab(), new Date(Calendar.getInstance().getTimeInMillis()), new Timestamp(Calendar
									.getInstance().getTimeInMillis()), lot.getOperador());
					listaDetalles.add(loteDet);

				}
			} catch (AsientoProveedorException e) {
				e.printStackTrace();
			} catch (PlanCuentaDosException e) {
				e.printStackTrace();
			}
			return listaDetalles;
		}


		public void actualizarAsiento(WrapperDetalleImportacion importable) {
			try {
				contabilidadService.getAsientoProveedorService().actualizarAsientoProveedor((AsientoProveedor) importable.getImportable());
			} catch (AsientoProveedorException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}


		public void impactarOrigenContab(Long idAsiento, Long idObjetoOrigen, Long idNroComprobante) {
			contabilidadService.getLoteService().impactarOrigenContab(idAsiento, idObjetoOrigen, idNroComprobante);

		}

	}

	public class OrigenImportacionClientes implements OrigenImportable {

		public List recuperarDetallesDelLote(Lote lot, Importable importable) {
			List listaDetalles = new ArrayList();

			try {
				List details = contabilidadService.getAsientoClienteService().getDetallesAsientoClienteImportado(importable.getIdNroComprobante());
				int i = 0;
				Iterator it = details.iterator();
				while (it.hasNext()) {
					AsientoClienteDetalle asi = (AsientoClienteDetalle) it.next();
					// recupero el centro de costo
					i++;
					PlanCuentaDos plan = contabilidadService.getPlanCuentaDosService().leerPlanCuenta(asi.getNumeroImputa());
					PlanCuentaDos plan2 = contabilidadService.getPlanCuentaDosService().leerPlanCuenta(asi.getNumeroImputaHaber());
					Integer reng = new Integer(i);

					LoteDetalle loteDet = new LoteDetalle(Integer.valueOf(idEmpresa.intValue()), Integer.valueOf(idEjercicio.intValue()),
							new Integer(1), Integer.valueOf(lot.getIdAsiento().intValue()), asi.getNumeroImputa(), "C", asi.getImporte(),
							lot.getConcepto(), lot.getFechaContab(), new Date(Calendar.getInstance().getTimeInMillis()), new Timestamp(Calendar
									.getInstance().getTimeInMillis()), lot.getOperador());
					LoteDetalle loteDet2 = new LoteDetalle(Integer.valueOf(idEmpresa.intValue()), Integer.valueOf(idEjercicio.intValue()),
							new Integer(2), Integer.valueOf(lot.getIdAsiento().intValue()), asi.getNumeroImputaHaber(), "D", asi.getImporte(),
							lot.getConcepto(), lot.getFechaContab(), new Date(Calendar.getInstance().getTimeInMillis()), new Timestamp(Calendar
									.getInstance().getTimeInMillis()), lot.getOperador());

					listaDetalles.add(loteDet);
					listaDetalles.add(loteDet2);
				}
			} catch (AsientoClienteException e) {
				e.printStackTrace();
			} catch (PlanCuentaDosException e) {
				e.printStackTrace();
			}
			return listaDetalles;
		}


		public void actualizarAsiento(WrapperDetalleImportacion importable) {
			try {
				contabilidadService.getAsientoClienteService().actualizarAsientoCliente((AsientoCliente) importable.getImportable());
			} catch (AsientoClienteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}


		public void impactarOrigenContab(Long idAsiento, Long idObjetoOrigen, Long idNroComprobante) {
			// TODO Auto-generated method stub

		}

	}

	public class OrigenImportacionComercio implements OrigenImportable {

		public List recuperarDetallesDelLote(Lote lot, Importable importable) {
			List listaDetalles = new ArrayList();

			try {
				List details = contabilidadService.getAsientoComercioService().getDetallesAsientoComercioImportado(importable.getIdNroComprobante());
				Iterator it = details.iterator();
				while (it.hasNext()) {
					AsientoComercioDetalle asi = (AsientoComercioDetalle) it.next();
					// recupero el centro de costo

					LoteDetalle loteDet = new LoteDetalle(Integer.valueOf(idEmpresa.intValue()), Integer.valueOf(idEjercicio.intValue()),
							new Integer(1), Integer.valueOf(lot.getIdAsiento().intValue()), asi.getNumeroImputa(), "C", asi.getImporte(),
							lot.getConcepto(), lot.getFechaContab(), new Date(Calendar.getInstance().getTimeInMillis()), new Timestamp(Calendar
									.getInstance().getTimeInMillis()), lot.getOperador());
					LoteDetalle loteDet2 = new LoteDetalle(Integer.valueOf(idEmpresa.intValue()), Integer.valueOf(idEjercicio.intValue()),
							new Integer(2), Integer.valueOf(lot.getIdAsiento().intValue()), asi.getNumeroImputaHaber(), "D", asi.getImporte(),
							lot.getConcepto(), lot.getFechaContab(), new Date(Calendar.getInstance().getTimeInMillis()), new Timestamp(Calendar
									.getInstance().getTimeInMillis()), lot.getOperador());

					listaDetalles.add(loteDet);
					listaDetalles.add(loteDet2);
				}
			} catch (AsientoComercioException e) {
				e.printStackTrace();
			}
			return listaDetalles;
		}


		public void actualizarAsiento(WrapperDetalleImportacion importable) {
			try {
				contabilidadService.getAsientoComercioService().actualizarAsientoComercio((AsientoComercio) importable.getImportable());
			} catch (AsientoComercioException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}


		public void impactarOrigenContab(Long idAsiento, Long idObjetoOrigen, Long idNroComprobante) {
			// TODO Auto-generated method stub

		}

	}


	public boolean isTodos() {
		return todos;
	}


	public void setTodos(boolean todos) {
		this.todos = todos;
	}

}
