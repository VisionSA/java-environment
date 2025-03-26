package com.bizitglobal.webapp.faces.beans.transacciones.popup;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Iterator;
import java.util.Set;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

import org.apache.log4j.Logger;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.evaluacion.negocio.IndividuoEvaluacion;
import com.bizitglobal.tarjetafiel.transacciones.exception.ClienteConceptoException;
import com.bizitglobal.tarjetafiel.transacciones.exception.ClienteTransaccionException;
import com.bizitglobal.tarjetafiel.transacciones.exception.CodComercioException;
import com.bizitglobal.tarjetafiel.transacciones.exception.ComercioConceptoException;
import com.bizitglobal.tarjetafiel.transacciones.exception.OrigenConceptoException;
import com.bizitglobal.tarjetafiel.transacciones.exception.OrigenenException;
import com.bizitglobal.tarjetafiel.transacciones.negocio.ClienteConcepto;
import com.bizitglobal.tarjetafiel.transacciones.negocio.ClienteTransaccion;
import com.bizitglobal.tarjetafiel.transacciones.negocio.CodComercio;
import com.bizitglobal.tarjetafiel.transacciones.negocio.ComercioConcepto;
import com.bizitglobal.tarjetafiel.transacciones.negocio.Concepto;
import com.bizitglobal.tarjetafiel.transacciones.negocio.OrigenConcepto;
import com.bizitglobal.tarjetafiel.transacciones.negocio.Origenen;
import com.bizitglobal.webapp.faces.beans.BaseBean;
import com.bizitglobal.webapp.faces.util.Session;
import com.bizitglobal.webapp.faces.beans.util.PaginaDeRegistros;
import com.bizitglobal.webapp.faces.beans.util.ScrollBean;


@SuppressWarnings({"rawtypes","unchecked"})
public class ConceptosPopupBean extends BaseBean {
	private static final Logger log = Logger.getLogger(ConceptosPopupBean.class);
	//
	public static final String CLIENTES = "Clientes Relacionados";
	public static final String COMERCIOS = "Comercios Relacionados";
	public static final String ORIGENES = "Origenes Relacionados";
	//
	public static final String CLIENTES_AGREGAR = "Seleccione los clientes a agregar";
	public static final String COMERCIOS_AGREGAR = "Seleccione los comercios a agregar";
	public static final String ORIGENES_AGREGAR = "Seleccione los origenes a agregar";
	public static final int CLIENTE = 1;
	public static final int COMERCIO = 2;
	public static final int ORIGEN = 3;

	private int origen;
	private String titulo, mensaje, descripcionElemento; // descripcionElemento se utiliza para saber si elimino comercios o clientes u origenes.
	private String tituloAgregar, mensajeElementosDisponibles; // tituloAgregar el titulo del segundo panel ,mensaje para decir que no existen
																// elementos disponibles.
	private String tituloCampo1, tituloCampo2, tituloCampo3, tituloCampo4, tituloCampo5, tituloCampo6, tituloCampoBoleanoDos;
	private boolean verCampo1, verCampo2, verCampo3, verCampo4, verCampo5, verCampo6;
	private boolean verFiltroComercio, verFiltroCliente, verBotonGrabar;
	private boolean verCampoBoleano; // para mostrar o no una columna que me permita seleccionar con select boolean checkbox los elementos
	private List listaElementos;
	private List listaElementosDisponibles; // es una lista de los elementos que aun no forman parte de la tabla relacional, de manera que pueden ser
											// seleccionados para serlo y pasarian a la lista de Elementos.
	private boolean paraAgregar;

	private Concepto concepto; // para tener los datos basicos del concepto que estamos editando
	PaginaDeRegistros paginador;
	PaginaDeRegistros paginadorBis;
	private List listaElementosAuxiliar; // una lista que contiene los objeto base, pueden ser conceptoclientes o conceptos comercio o conceptos
											// origenes.
	private Set listaClientesRelacionados;
	private Set listaComerciosRelacionados;
	private Set listaOrigenesRelacionados;

	private String listaIdClientesYaAgregados, listaIdComerciosYaAgregados, listaIdOrigenesYaAgregados;

	private ClienteTransaccion clienteFiltro;
	private List estadoClienteItems;
	private List estadoCobranzaItems;
	private List estadoConsumoItems;
	private List estadoClienteFiltroItems;
	private List estadoCobranzaFiltroItems;
	private List estadoConsumoFiltroItems;
	private String estadoCobranza;
	private String consumoHabilitado;
	private boolean verFiltroClientes;


	public ConceptosPopupBean() {
		super();
		borrar();
		// clienteFiltro = new ClienteTransaccion();
		// clienteFiltro.setIndividuo(new IndividuoEvaluacion());
		// clienteFiltro.setEstadoCliente("NoValido");
		// clienteFiltro.setEstadoCobranza("NoValido");
		// clienteFiltro.setHabiliadoConsumo("NoValido");
		// estadoClienteItems = Util.cargarSelectItemMascara(ClienteTransaccion.estadosClienteStaticList);
		// estadoCobranzaItems = Util.cargarSelectItemMascara(ClienteTransaccion.estadoCobranzaStaticList);
		// estadoConsumoItems = Util.cargarSelectItemMascara(ClienteTransaccion.habilitadoConsumoStaticList);
		// estadoClienteFiltroItems = new ArrayList();
		// estadoClienteFiltroItems.add(new SelectItem("NoValido", "Seleccione un estado"));
		// estadoClienteFiltroItems.addAll(estadoClienteItems);
		// estadoCobranzaFiltroItems = new ArrayList();
		// estadoCobranzaFiltroItems.add(new SelectItem("NoValido", "Seleccione un estado"));
		// estadoCobranzaFiltroItems.addAll(estadoCobranzaItems);
		// estadoConsumoFiltroItems = new ArrayList();
		// estadoConsumoFiltroItems.add(new SelectItem("NoValido", "Seleccione un estado"));
		// estadoConsumoFiltroItems.addAll(estadoConsumoItems);
	}


	/************************************************************************
	 * ACCIONES DEL BEAN DE ConceptosPOPUP
	 ************************************************************************/

	public String inicializar() {
		borrarBaseBean();
		borrar();
		if (Session.getBean("ScrollBean") != null)
		{
			ScrollBean bean = (ScrollBean) Session.getBean("ScrollBean");
			bean.setHiddenScrollY(new Integer(0));
		}
		return null;
	}


	public String inicializar(int origen, Concepto concepto) {
		this.origen = origen;
		this.concepto = concepto;
		setearVariablesSegunOrigen();
		listarElementos();
		paraAgregar = false;
		String path = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
		path += "/tarjetafiel/transacciones/popup/conceptosPopup.jsf";
		ejecutarJavaScript("popup('" + path + "',900,700), 'titlebar=no';");
		return null;
	}


	public void setearVariablesSegunOrigen() {
		switch (origen) {
		case CLIENTE:
			descripcionElemento = "cliente";
			verCampo1 = true;
			verCampo2 = true;
			verCampo3 = true;
			verCampo4 = true;
			verCampo5 = true;
			verCampo6 = false;
			tituloCampo1 = "Cuit";
			tituloCampo2 = "Apellido";
			tituloCampo3 = "Estado Cliente";
			tituloCampo4 = "Estado Cobranza";
			tituloCampo5 = "Estado Consumo";
			tituloCampo6 = "";
			tituloCampoBoleanoDos = "";
			mensaje = "No existen Clientes relacionados a este Concepto";
			mensajeElementosDisponibles = "No existen clientes en condiciones de agregarse.";
			titulo = CLIENTES;
			tituloAgregar = CLIENTES_AGREGAR;
			verBotonGrabar = false;
			verFiltroClientes = true;
			verFiltroComercio = false;
			break;
		case COMERCIO:
			descripcionElemento = "comercio";
			verCampo1 = true;
			verCampo2 = true;
			verCampo3 = true;
			verCampo4 = true;
			verCampo5 = true;
			verCampo6 = false;
			tituloCampo1 = "Cuit";
			tituloCampo2 = "Razón Social";
			tituloCampo3 = "Sucursal";
			tituloCampo4 = "Código";
			tituloCampo5 = "Estado";
			tituloCampo6 = "";
			tituloCampoBoleanoDos = "";
			mensaje = "No existen Comercios relacionados a este Concepto";
			mensajeElementosDisponibles = "No existen comercios en condiciones de agregarse.";
			titulo = COMERCIOS;
			tituloAgregar = COMERCIOS_AGREGAR;
			verBotonGrabar = false;
			verFiltroClientes = false;
			verFiltroComercio = true;
			break;
		case ORIGEN:
			descripcionElemento = "origen";
			verCampo1 = true;
			verCampo2 = true;
			verCampo3 = false;
			verCampo4 = false;
			verCampo5 = false;
			verCampo6 = false;
			tituloCampo1 = "Descripción";
			tituloCampo2 = "Activo";
			tituloCampo3 = "Permite Habilitado";
			tituloCampo4 = "";
			tituloCampo5 = "";
			tituloCampo6 = "";
			tituloCampoBoleanoDos = "Genera Autorización";
			mensaje = "No existen Orígenes relacionados a este Concepto";
			mensajeElementosDisponibles = "No existen origenes en condiciones de agregarse.";
			titulo = ORIGENES;
			tituloAgregar = ORIGENES_AGREGAR;
			verBotonGrabar = true;
			verFiltroClientes = false;
			verFiltroComercio = false;
			break;
		}

	}


	public String listarElementos() {
		Filtro filtro = new Filtro();
		listaElementosAuxiliar = null;
		listaElementos = new ArrayList();
		listaClientesRelacionados = new HashSet();
		listaComerciosRelacionados = new HashSet();
		listaOrigenesRelacionados = new HashSet();
		// definir si entra o no a los filtros y armar el paginador
		switch (origen) {
		case CLIENTE:
			try {
				ClienteConcepto clienteConc;
				listaIdClientesYaAgregados = "";
				filtro.agregarCampoOperValor("concepto.idConcepto", Filtro.IGUAL, concepto.getIdConcepto());

				// if (clienteFiltro.getIndividuo().getCuil()!=null&&clienteFiltro.getIndividuo().getCuil().compareTo("")!= 0) {
				// filtro.agregarCampoOperValor("clienteTransaccion.individuo.cuil", Filtro.LIKE, clienteFiltro.getIndividuo().getCuil());
				// }
				// if (clienteFiltro.getIndividuo().getApellido()!=null&&clienteFiltro.getIndividuo().getApellido().compareTo("")!= 0) {
				// filtro.agregarCampoOperValor("clienteTransaccion.individuo.apellido", Filtro.LIKE, clienteFiltro.getIndividuo().getApellido());
				// }
				// if (clienteFiltro.getEstadoCliente().compareTo("NoValido")!=0) {
				// filtro.agregarCampoOperValor("clienteTransaccion.estadoCliente", Filtro.LIKEXS, clienteFiltro.getEstadoCliente());
				// }
				// if (clienteFiltro.getEstadoCobranza().compareTo("NoValido")!=0) {
				// filtro.agregarCampoOperValor("clienteTransaccion.estadoCobranza", Filtro.LIKEXS, clienteFiltro.getEstadoCobranza());
				// }
				// if (clienteFiltro.getHabiliadoConsumo().compareTo("NoValido")!=0) {
				// filtro.agregarCampoOperValor("clienteTransaccion.habiliadoConsumo", Filtro.LIKEXS, clienteFiltro.getHabiliadoConsumo());
				// }

				listaElementosAuxiliar = transaccionesService.getClienteConceptoService().getClienteConcepto(filtro);
				Iterator ite = listaElementosAuxiliar.iterator();
				while (ite.hasNext()) {
					clienteConc = (ClienteConcepto) ite.next();
					listaIdClientesYaAgregados += ", " + clienteConc.getCliente().getIdCliente();
					listaClientesRelacionados.add(clienteConc.getCliente());
					clienteConc.getCliente().getIndividuo().getCuil();
					clienteConc.getCliente().getIndividuo().getApellido();
				}
				if (listaIdClientesYaAgregados.length() > 0)
					listaIdClientesYaAgregados = listaIdClientesYaAgregados.substring(2);
			} catch (ClienteConceptoException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case COMERCIO:
			try {
				ComercioConcepto comercioConc;
				listaIdComerciosYaAgregados = "";
				filtro.agregarCampoOperValor("concepto.idConcepto", Filtro.IGUAL, concepto.getIdConcepto());
				listaElementosAuxiliar = transaccionesService.getComercioConceptoService().getComercioConcepto(filtro);
				Iterator ite = listaElementosAuxiliar.iterator();
				while (ite.hasNext()) {
					comercioConc = (ComercioConcepto) ite.next();
					listaIdComerciosYaAgregados += ", " + comercioConc.getCodComercio().getIdCodComercio();
					listaComerciosRelacionados.add(comercioConc.getCodComercio());
					comercioConc.getCodComercio().getSucEmpresa().getEmpresa().getCuit();
					comercioConc.getCodComercio().getSucEmpresa().getEmpresa().getRazonSocial();
					comercioConc.getCodComercio().getSucEmpresa().getDescripcion();
					comercioConc.getCodComercio().getCodigo();
					comercioConc.getCodComercio().getEstado();
				}
				if (listaIdComerciosYaAgregados.length() > 0)
					listaIdComerciosYaAgregados = listaIdComerciosYaAgregados.substring(2);
			} catch (ComercioConceptoException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case ORIGEN:
			try {
				OrigenConcepto origenConc;
				listaIdOrigenesYaAgregados = "";
				filtro.agregarCampoOperValor("concepto.idConcepto", Filtro.IGUAL, concepto.getIdConcepto());
				listaElementosAuxiliar = transaccionesService.getOrigenConceptoService().getOrigenConcepto(filtro);
				Iterator ite = listaElementosAuxiliar.iterator();
				while (ite.hasNext()) {
					origenConc = (OrigenConcepto) ite.next();
					listaIdOrigenesYaAgregados += ", " + origenConc.getOrigenen().getIdOrigenes();
					listaOrigenesRelacionados.add(origenConc.getOrigenen());
					origenConc.getOrigenen().getDescripcion();
					origenConc.getOrigenen().getActivo();
					origenConc.getGeneraCodAutorizacion();
				}
				if (listaIdOrigenesYaAgregados.length() > 0)
					listaIdOrigenesYaAgregados = listaIdOrigenesYaAgregados.substring(2);
			} catch (OrigenConceptoException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		}
		if (listaElementosAuxiliar != null) {
			if (!listaElementosAuxiliar.isEmpty()) {
				paginador = new PaginaDeRegistros(30, listaElementosAuxiliar);
				Iterator i = paginador.getPrimeraPagina().iterator();
				while (i.hasNext()) {
					Object a = i.next();
					WrapperElemento w = new WrapperElemento(a);
					listaElementos.add(w);
					// implementar bien
				}
			}
		}
		return null;
	}


	public String listarElementosDisponibles() {
		Filtro filtro = new Filtro();
		List listaElementosAuxiliarBis = null;
		listaElementosDisponibles = new ArrayList();
		// definir si entra o no a los filtros y armar el paginador
		switch (origen) {
		case CLIENTE:
			try {
				ClienteTransaccion cliente;
				if (listaIdClientesYaAgregados.length() > 0) {
					filtro.agregarCampoOperValor("idCliente", Filtro.NOTIN, listaIdClientesYaAgregados);
				}

				// if (clienteFiltro.getIndividuo().getCuil()!=null&&clienteFiltro.getIndividuo().getCuil().compareTo("")!= 0) {
				// filtro.agregarCampoOperValor("individuo.cuil", Filtro.LIKE, clienteFiltro.getIndividuo().getCuil());
				// }
				// if (clienteFiltro.getIndividuo().getApellido()!=null&&clienteFiltro.getIndividuo().getApellido().compareTo("")!= 0) {
				// filtro.agregarCampoOperValor("individuo.apellido", Filtro.LIKE, clienteFiltro.getIndividuo().getApellido());
				// }
				// if (clienteFiltro.getEstadoCliente().compareTo("NoValido")!=0) {
				// filtro.agregarCampoOperValor("estadoCliente", Filtro.LIKEXS, clienteFiltro.getEstadoCliente());
				// }
				// if (clienteFiltro.getEstadoCobranza().compareTo("NoValido")!=0) {
				// filtro.agregarCampoOperValor("estadoCobranza", Filtro.LIKEXS, clienteFiltro.getEstadoCobranza());
				// }
				// if (clienteFiltro.getHabiliadoConsumo().compareTo("NoValido")!=0) {
				// filtro.agregarCampoOperValor("habiliadoConsumo", Filtro.LIKEXS, clienteFiltro.getHabiliadoConsumo());
				// }

				listaElementosAuxiliarBis = transaccionesService.getClienteTransaccionService().getCliente(filtro);
				Iterator ite = listaElementosAuxiliarBis.iterator();
				while (ite.hasNext()) {
					cliente = (ClienteTransaccion) ite.next();
					cliente.getIndividuo().getCuil();
					cliente.getIndividuo().getApellido();
				}
				// log.info("los clientes que traeremos son "+ listaElementosAuxiliarBis.size());
				// listaElementosAuxiliarBis.removeAll(listaClientesRelacionados);
				// log.info("los clientes que traeremos son "+ listaElementosAuxiliarBis.size());

			} catch (ClienteTransaccionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case COMERCIO:
			try {
				CodComercio comercio;
				if (listaIdComerciosYaAgregados.length() > 0) {
					filtro.agregarCampoOperValor("idCodComercio", Filtro.NOTIN, listaIdComerciosYaAgregados);
				}
				listaElementosAuxiliarBis = transaccionesService.getCodComercioService().getCodComercio(filtro);
				Iterator ite = listaElementosAuxiliarBis.iterator();
				while (ite.hasNext()) {
					comercio = (CodComercio) ite.next();
					comercio.getSucEmpresa().getEmpresa().getCuit();
					comercio.getSucEmpresa().getEmpresa().getRazonSocial();
					comercio.getSucEmpresa().getDescripcion();
					comercio.getCodigo();
					comercio.getEstado();
				}
			} catch (CodComercioException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case ORIGEN:
			try {
				Origenen origen;
				if (listaIdOrigenesYaAgregados.length() > 0) {
					filtro.agregarCampoOperValor("idOrigenes", Filtro.NOTIN, listaIdOrigenesYaAgregados);
				}
				listaElementosAuxiliarBis = transaccionesService.getOrigenenService().getOrigenen(filtro);
				Iterator ite = listaElementosAuxiliarBis.iterator();
				while (ite.hasNext()) {
					origen = (Origenen) ite.next();
					origen.getDescripcion();
					origen.getActivo();
				}
			} catch (OrigenenException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		}
		if (!listaElementosAuxiliarBis.isEmpty()) {
			paginadorBis = new PaginaDeRegistros(30, listaElementosAuxiliarBis);
			Iterator i = paginadorBis.getPrimeraPagina().iterator();
			while (i.hasNext()) {
				Object a = i.next();
				WrapperElemento w = new WrapperElemento(a);
				listaElementosDisponibles.add(w);
			}
		}
		return null;
	}


	public String eliminarElemento() {
		// implementar como borrar los elementos en base al origen;
		Long idBuscado = new Long(Session.getRequestParameter("idElementoElim"));
		WrapperElemento elementoABorrar = null;
		switch (origen) {
		case CLIENTE:
			Iterator iter = listaElementos.iterator();
			while (iter.hasNext()) {
				WrapperElemento wraperElemento = (WrapperElemento) iter.next();
				if (wraperElemento.getCampoIdentificatorio().equals(idBuscado)) {
					try {
						transaccionesService.getClienteConceptoService().borrarClienteConcepto(wraperElemento.getCampoIdentificatorio());
						elementoABorrar = wraperElemento;
						log.info("Antes de borrar la cadena de id es: " + listaIdClientesYaAgregados);
						listaIdClientesYaAgregados = listaIdClientesYaAgregados.replaceAll(", "
								+ wraperElemento.getClienteConcepto().getCliente().getIdCliente().toString() + ",", ",");
						String cadena = wraperElemento.getClienteConcepto().getCliente().getIdCliente().toString();
						log.info("Buscare las cadenas:");
						log.info(", " + wraperElemento.getClienteConcepto().getCliente().getIdCliente().toString() + ", ");
						log.info("', " + cadena + "'");
						log.info("'" + cadena + ", '");
						log.info("''");
						if (listaIdClientesYaAgregados.endsWith(", " + cadena)) {
							listaIdClientesYaAgregados = listaIdClientesYaAgregados.substring(0,
									listaIdClientesYaAgregados.length() - (cadena.length() + 2));
						}
						if (listaIdClientesYaAgregados.startsWith(cadena + ", ")) {
							listaIdClientesYaAgregados = listaIdClientesYaAgregados.substring(cadena.length() + 2);
						}
						if (listaIdClientesYaAgregados.compareTo(cadena) == 0)
							listaIdClientesYaAgregados = "";
						Iterator iterObjetos = listaElementosAuxiliar.iterator();
						while (iterObjetos.hasNext()) {
							log.info("Camino a borrar");
							ClienteConcepto cliente = (ClienteConcepto) iterObjetos.next();
							if (cliente.getIdClientesconceptos().equals(elementoABorrar.getCampoIdentificatorio())) {
								boolean boo = listaElementosAuxiliar.remove(cliente);
								log.info("intento de borrado " + boo);
								break;
							}
						}
						log.info("Despues de borrar la cadena de id es: " + listaIdClientesYaAgregados);
						break;
					} catch (ClienteConceptoException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			break;
		case COMERCIO:
			Iterator iterComercio = listaElementos.iterator();
			while (iterComercio.hasNext()) {
				WrapperElemento wraperElemento = (WrapperElemento) iterComercio.next();
				if (wraperElemento.getCampoIdentificatorio().equals(idBuscado)) {
					try {
						transaccionesService.getComercioConceptoService().borrarComercioConcepto(wraperElemento.getCampoIdentificatorio());
						elementoABorrar = wraperElemento;
						log.info("Antes de borrar la cadena de id es: " + listaIdComerciosYaAgregados);
						listaIdComerciosYaAgregados = listaIdComerciosYaAgregados.replaceAll(", "
								+ wraperElemento.getComercioConcepto().getCodComercio().getIdCodComercio().toString() + ",", ",");
						String cadena = wraperElemento.getComercioConcepto().getCodComercio().getIdCodComercio().toString();
						if (listaIdComerciosYaAgregados.endsWith(", " + cadena)) {
							listaIdComerciosYaAgregados = listaIdComerciosYaAgregados.substring(0,
									listaIdComerciosYaAgregados.length() - (cadena.length() + 2));
						}
						if (listaIdComerciosYaAgregados.startsWith(cadena + ", ")) {
							listaIdComerciosYaAgregados = listaIdComerciosYaAgregados.substring(cadena.length() + 2);
						}
						if (listaIdComerciosYaAgregados.compareTo(cadena) == 0)
							listaIdComerciosYaAgregados = "";
						Iterator iterObjetos = listaElementosAuxiliar.iterator();
						while (iterObjetos.hasNext()) {
							log.info("Camino a borrar");
							ComercioConcepto comercio = (ComercioConcepto) iterObjetos.next();
							if (comercio.getIdComerciosconceptos().equals(elementoABorrar.getCampoIdentificatorio())) {
								boolean boo = listaElementosAuxiliar.remove(comercio);
								log.info("intento de borrado " + boo);
								break;
							}
						}
						log.info("Despues de borrar la cadena de id es: " + listaIdComerciosYaAgregados);
						break;
					} catch (ComercioConceptoException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			break;
		case ORIGEN:
			Iterator iterOrigen = listaElementos.iterator();
			while (iterOrigen.hasNext()) {
				WrapperElemento wraperElemento = (WrapperElemento) iterOrigen.next();
				if (wraperElemento.getCampoIdentificatorio().equals(idBuscado)) {
					try {
						transaccionesService.getOrigenConceptoService().borrarOrigenConcepto(wraperElemento.getCampoIdentificatorio());
						elementoABorrar = wraperElemento;
						log.info("Antes de borrar la cadena de id es: " + listaIdOrigenesYaAgregados);
						listaIdOrigenesYaAgregados = listaIdOrigenesYaAgregados.replaceAll(", "
								+ wraperElemento.getOrigenConcepto().getOrigenen().getIdOrigenes().toString() + ",", ",");
						String cadena = wraperElemento.getOrigenConcepto().getOrigenen().getIdOrigenes().toString();
						if (listaIdOrigenesYaAgregados.endsWith(", " + cadena)) {
							listaIdOrigenesYaAgregados = listaIdOrigenesYaAgregados.substring(0,
									listaIdOrigenesYaAgregados.length() - (cadena.length() + 2));
						}
						if (listaIdOrigenesYaAgregados.startsWith(cadena + ", ")) {
							listaIdOrigenesYaAgregados = listaIdOrigenesYaAgregados.substring(cadena.length() + 2);
						}
						if (listaIdOrigenesYaAgregados.compareTo(cadena) == 0)
							listaIdOrigenesYaAgregados = "";
						Iterator iterObjetos = listaElementosAuxiliar.iterator();
						while (iterObjetos.hasNext()) {
							log.info("Camino a borrar");
							OrigenConcepto origen = (OrigenConcepto) iterObjetos.next();
							if (origen.getIdOrigenconcepto().equals(elementoABorrar.getCampoIdentificatorio())) {
								listaElementosAuxiliar.remove(origen);
								break;
							}
						}
						log.info("Despues de borrar la cadena de id es: " + listaIdOrigenesYaAgregados);
						break;
					} catch (OrigenConceptoException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			break;
		}
		if (elementoABorrar != null) {
			listaElementos.clear();
			paginador = new PaginaDeRegistros(30, listaElementosAuxiliar);
			Iterator i = paginador.getPrimeraPagina().iterator();
			while (i.hasNext()) {
				Object a = i.next();
				WrapperElemento w = new WrapperElemento(a);
				listaElementos.add(w);
				// implementar bien
			}
		}
		return null;
	}


	public void recargarYCerrarPopup(ActionEvent event) {
		if (validar()) {
			String javaScriptText = "window.opener.recargar(); window.close();";
			ejecutarJavaScript(javaScriptText);
		}
	}


	public String grabarCambiosElementos() {
		// grabar Los cambios solo en origen, el resto no implementa el metodo
		Iterator iter = listaElementos.iterator();
		while (iter.hasNext()) {
			WrapperElemento ori = (WrapperElemento) iter.next();
			if (ori.isCampoBoleanoDos()) {
				ori.getOrigenConcepto().setGeneraCodAutorizacion("H");
			} else {
				ori.getOrigenConcepto().setGeneraCodAutorizacion("D");
			}
			try {
				transaccionesService.getOrigenConceptoService().actualizarOrigenConcepto(ori.getOrigenConcepto());
			} catch (OrigenConceptoException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}


	public String volver() {
		// metodo comun a todos los origenes. implementar que hacer antes de cerrar el popup
		return null;
	}


	public String agregarElemento() {
		// implementar correctamente para cada origen
		listarElementosDisponibles();
		paraAgregar = true;
		return null;
	}


	public String grabar() {
		if (validar()) {

		}
		return "";
	}


	// /*******************************
	// **** Metodos para el paginador
	public String primerRegistro() {
		listaElementos.clear();
		Iterator i = paginador.getPrimeraPagina().iterator();
		while (i.hasNext()) {
			Object a = i.next();
			WrapperElemento w = new WrapperElemento(a);
			listaElementos.add(w);
		}
		return null;
	}


	public String ultimoRegistro() {
		listaElementos.clear();
		Iterator i = paginador.getUltimaPagina().iterator();
		while (i.hasNext()) {
			Object a = i.next();
			WrapperElemento w = new WrapperElemento(a);
			listaElementos.add(w);
		}
		return null;
	}


	public String siguienteRegistro() {
		listaElementos.clear();
		Iterator i = paginador.getPaginaSiguiente().iterator();
		while (i.hasNext()) {
			Object a = i.next();
			WrapperElemento w = new WrapperElemento(a);
			listaElementos.add(w);
		}
		return null;
	}


	public String anteriorRegistro() {
		listaElementos.clear();
		Iterator i = paginador.getPaginaAnterior().iterator();
		while (i.hasNext()) {
			Object a = i.next();
			WrapperElemento w = new WrapperElemento(a);
			listaElementos.add(w);
		}
		return null;
	}


	public void cambiarPagina(ValueChangeEvent e) {
		paginaDeAsiento();
	}


	public String paginaDeAsiento() {
		listaElementos.clear();
		Iterator i = paginador.getPagina(
				((Long) paginador.getPagSeleccionada().getValue()).intValue()).iterator();
		while (i.hasNext()) {
			Object a = i.next();
			WrapperElemento w = new WrapperElemento(a);
			listaElementos.add(w);
		}
		return null;
	}


	// ***** fin metodos paginador
	// *******************************************

	// /*******************************
	// **** Metodos para el paginadorBIs
	public String primerRegistroBis() {
		listaElementosDisponibles.clear();
		Iterator i = paginadorBis.getPrimeraPagina().iterator();
		while (i.hasNext()) {
			Object a = i.next();
			WrapperElemento w = new WrapperElemento(a);
			listaElementosDisponibles.add(w);
		}
		return null;
	}


	public String ultimoRegistroBis() {
		listaElementosDisponibles.clear();
		Iterator i = paginadorBis.getUltimaPagina().iterator();
		while (i.hasNext()) {
			Object a = i.next();
			WrapperElemento w = new WrapperElemento(a);
			listaElementosDisponibles.add(w);
		}
		return null;
	}


	public String siguienteRegistroBis() {
		listaElementosDisponibles.clear();
		Iterator i = paginadorBis.getPaginaSiguiente().iterator();
		while (i.hasNext()) {
			Object a = i.next();
			WrapperElemento w = new WrapperElemento(a);
			listaElementosDisponibles.add(w);
		}
		return null;
	}


	public String anteriorRegistroBis() {
		listaElementosDisponibles.clear();
		Iterator i = paginadorBis.getPaginaAnterior().iterator();
		while (i.hasNext()) {
			Object a = i.next();
			WrapperElemento w = new WrapperElemento(a);
			listaElementosDisponibles.add(w);
		}
		return null;
	}


	public void cambiarPaginaBis(ValueChangeEvent e) {
		paginaDeAsientoBis();
	}


	public String paginaDeAsientoBis() {
		listaElementosDisponibles.clear();
		Iterator i = paginadorBis.getPagina(
				((Long) paginadorBis.getPagSeleccionada().getValue()).intValue()).iterator();
		while (i.hasNext()) {
			Object a = i.next();
			WrapperElemento w = new WrapperElemento(a);
			listaElementosDisponibles.add(w);
		}
		return null;
	}

	// ***** fin metodos paginadorBis
	// *******************************************

	// **************************************
	// ***** Wrapper Elemento

	public class WrapperElemento {

		private ClienteConcepto clienteConcepto;
		private ComercioConcepto comercioConcepto;
		private OrigenConcepto origenConcepto;
		private String campo1, campo2, campo3, campo4, campo5, campo6;
		private boolean campoBoleano, campoBoleanoDos;
		private Long campoIdentificatorio;


		public WrapperElemento(Object o) {
			switch (origen) {
			case CLIENTE:
				if (o instanceof ClienteConcepto) {
					clienteConcepto = (ClienteConcepto) o;

					// el primer get Estado Cliente, lo reemplaze por getHabiliadoConsumo..... hay que ver como arreglarlo despues.
					rellenarCamposIdentificatorios(clienteConcepto.getIdClientesconceptos(), clienteConcepto.getCliente().getIndividuo().getCuil(),
							clienteConcepto.getCliente().getIndividuo().getApellido(), clienteConcepto.getCliente().getHabilitadoConsumo(),
							clienteConcepto.getCliente().getEstadoCobranza().getDescripcion(), clienteConcepto.getCliente().getHabilitadoConsumo(),
							null, false, false);

				} else {
					clienteConcepto = new ClienteConcepto();
					clienteConcepto.setCliente((ClienteTransaccion) o);
					clienteConcepto.setConcepto(concepto);

					// el primer get Estado Cliente, lo reemplaze por getHabiliadoConsumo..... hay que ver como arreglarlo despues.
					rellenarCamposIdentificatorios(clienteConcepto.getIdClientesconceptos(), clienteConcepto.getCliente().getIndividuo().getCuil(),
							clienteConcepto.getCliente().getIndividuo().getApellido(), clienteConcepto.getCliente().getHabilitadoConsumo(),
							clienteConcepto.getCliente().getEstadoCobranza().getDescripcion(), clienteConcepto.getCliente().getHabilitadoConsumo(),
							null, true, false);

				}
				break;
			case COMERCIO:
				if (o instanceof ComercioConcepto) {
					comercioConcepto = (ComercioConcepto) o;
					rellenarCamposIdentificatorios(comercioConcepto.getIdComerciosconceptos(), comercioConcepto.getCodComercio().getSucEmpresa()
							.getEmpresa().getCuit().toString(), comercioConcepto.getCodComercio().getSucEmpresa().getEmpresa().getRazonSocial(),
							comercioConcepto.getCodComercio().getSucEmpresa().getDescripcion(), comercioConcepto.getCodComercio().getCodigo(),
							comercioConcepto.getCodComercio().getEstado(), null, false, false);
				} else {
					comercioConcepto = new ComercioConcepto();
					comercioConcepto.setCodComercio((CodComercio) o);
					comercioConcepto.setConcepto(concepto);
					rellenarCamposIdentificatorios(comercioConcepto.getIdComerciosconceptos(), comercioConcepto.getCodComercio().getSucEmpresa()
							.getEmpresa().getCuit().toString(), comercioConcepto.getCodComercio().getSucEmpresa().getEmpresa().getRazonSocial(),
							comercioConcepto.getCodComercio().getSucEmpresa().getDescripcion(), comercioConcepto.getCodComercio().getCodigo(),
							comercioConcepto.getCodComercio().getEstado(), null, true, false);
				}
				break;
			case ORIGEN:
				if (o instanceof OrigenConcepto) {
					origenConcepto = (OrigenConcepto) o;
					boolean queValor;
					try {
						if (origenConcepto.getGeneraCodAutorizacion().compareTo("H") == 0) {
							queValor = true;
						} else {
							queValor = false;
						}
					} catch (NullPointerException e) {
						queValor = false;
					}
					rellenarCamposIdentificatorios(origenConcepto.getIdOrigenconcepto(), origenConcepto.getOrigenen().getDescripcion(),
							origenConcepto.getOrigenen().getActivo(), null, null, null, null, false, queValor);
				} else {
					origenConcepto = new OrigenConcepto();
					origenConcepto.setOrigenen((Origenen) o);
					origenConcepto.setConcepto(concepto);
					rellenarCamposIdentificatorios(origenConcepto.getIdOrigenconcepto(), origenConcepto.getOrigenen().getDescripcion(),
							origenConcepto.getOrigenen().getActivo(), null, null, null, null, true, false);
				}
				break;
			}
		}


		public void rellenarCamposIdentificatorios(Long campoIdentificatorio, String campo1, String campo2, String campo3, String campo4,
				String campo5, String campo6, boolean campoBoleano, boolean campoBoleanoDos) {
			this.campoIdentificatorio = campoIdentificatorio;
			this.campo1 = campo1;
			this.campo2 = campo2;
			this.campo3 = campo3;
			this.campo4 = campo4;
			this.campo5 = campo5;
			this.campo6 = campo6;
			this.campoBoleano = campoBoleano;
			this.campoBoleanoDos = campoBoleanoDos;
		}


		public ClienteConcepto getClienteConcepto() {
			return clienteConcepto;
		}


		public ComercioConcepto getComercioConcepto() {
			return comercioConcepto;
		}


		public OrigenConcepto getOrigenConcepto() {
			return origenConcepto;
		}


		public String getCampo1() {
			return campo1;
		}


		public String getCampo2() {
			return campo2;
		}


		public String getCampo3() {
			return campo3;
		}


		public String getCampo4() {
			return campo4;
		}


		public String getCampo5() {
			return campo5;
		}


		public String getCampo6() {
			return campo6;
		}


		public Long getCampoIdentificatorio() {
			return campoIdentificatorio;
		}


		public boolean isCampoBoleano() {
			return campoBoleano;
		}


		public void setCampoBoleano(boolean campoBoleano) {
			this.campoBoleano = campoBoleano;
		}


		public boolean isCampoBoleanoDos() {
			return campoBoleanoDos;
		}


		public void setCampoBoleanoDos(boolean campoBoleanoDos) {
			this.campoBoleanoDos = campoBoleanoDos;
		}

	}


	// ************fin definicion Wrapper
	// *******************************

	public String cancelarAgregados() {
		paraAgregar = false;
		return null;
	}


	public String aceptarAgregados() {
		// todo el proceso de grabacion aqui;
		switch (origen) {
		case CLIENTE:
			Iterator iter = listaElementosDisponibles.iterator();
			while (iter.hasNext()) {
				WrapperElemento wrap = (WrapperElemento) iter.next();
				if (wrap.isCampoBoleano()) {
					try {
						transaccionesService.getClienteConceptoService().grabarClienteConcepto(wrap.clienteConcepto);
					} catch (ClienteConceptoException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

			}
			listarElementos();
			break;
		case COMERCIO:
			Iterator iterComercios = listaElementosDisponibles.iterator();
			while (iterComercios.hasNext()) {
				WrapperElemento wrap = (WrapperElemento) iterComercios.next();
				if (wrap.isCampoBoleano()) {
					try {
						transaccionesService.getComercioConceptoService().grabarComercioConcepto(wrap.comercioConcepto);
					} catch (ComercioConceptoException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

			}
			listarElementos();
			break;
		case ORIGEN:
			Iterator iterOrigen = listaElementosDisponibles.iterator();
			while (iterOrigen.hasNext()) {
				WrapperElemento wrap = (WrapperElemento) iterOrigen.next();
				if (wrap.isCampoBoleano()) {
					try {
						transaccionesService.getOrigenConceptoService().grabarOrigenConcepto(wrap.origenConcepto);
					} catch (OrigenConceptoException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

			}
			listarElementos();
			break;
		}
		paraAgregar = false;
		return null;
	}


	public void borrar() {
		error.borrar();
		clienteFiltro = new ClienteTransaccion();
		clienteFiltro.setIndividuo(new IndividuoEvaluacion());
		popup.borrar();
	}


	public String cancelar() {
		borrar();
		return "inicio";
	}


	public boolean validar() {
		error.borrar();

		return (error.cantidad() == 0) ? true : false;
	}


	public String getTitulo() {
		return titulo;
	}


	public boolean isVerBotonGrabar() {
		return verBotonGrabar;
	}


	public boolean isVerFiltroCliente() {
		return verFiltroCliente;
	}


	public boolean isVerFiltroComercio() {
		return verFiltroComercio;
	}


	public List getListaElementos() {
		return listaElementos;
	}


	public String getMensaje() {
		return mensaje;
	}


	public Concepto getConcepto() {
		return concepto;
	}


	public PaginaDeRegistros getPaginador() {
		return paginador;
	}


	public void setPaginador(PaginaDeRegistros paginador) {
		this.paginador = paginador;
	}


	public String getTituloCampo1() {
		return tituloCampo1;
	}


	public String getTituloCampo2() {
		return tituloCampo2;
	}


	public String getTituloCampo3() {
		return tituloCampo3;
	}


	public String getTituloCampo4() {
		return tituloCampo4;
	}


	public String getTituloCampo5() {
		return tituloCampo5;
	}


	public String getTituloCampo6() {
		return tituloCampo6;
	}


	public boolean isVerCampo1() {
		return verCampo1;
	}


	public boolean isVerCampo2() {
		return verCampo2;
	}


	public boolean isVerCampo3() {
		return verCampo3;
	}


	public boolean isVerCampo4() {
		return verCampo4;
	}


	public boolean isVerCampo5() {
		return verCampo5;
	}


	public boolean isVerCampo6() {
		return verCampo6;
	}


	public String getDescripcionElemento() {
		return descripcionElemento;
	}


	public List getListaElementosDisponibles() {
		return listaElementosDisponibles;
	}


	public boolean isParaAgregar() {
		return paraAgregar;
	}


	public String getTituloAgregar() {
		return tituloAgregar;
	}


	public PaginaDeRegistros getPaginadorBis() {
		return paginadorBis;
	}


	public String getMensajeElementosDisponibles() {
		return mensajeElementosDisponibles;
	}


	public boolean isVerCampoBoleano() {
		return paraAgregar;
	}


	public boolean isVerCampoBoleanoDos() {
		return (origen == ORIGEN && !paraAgregar);
	}


	public String getTituloCampoBoleanoDos() {
		return tituloCampoBoleanoDos;
	}


	public List getEstadoClienteFiltroItems() {
		return estadoClienteFiltroItems;
	}


	public void setEstadoClienteFiltroItems(List estadoClienteFiltroItems) {
		this.estadoClienteFiltroItems = estadoClienteFiltroItems;
	}


	public List getEstadoClienteItems() {
		return estadoClienteItems;
	}


	public void setEstadoClienteItems(List estadoClienteItems) {
		this.estadoClienteItems = estadoClienteItems;
	}


	public List getEstadoCobranzaFiltroItems() {
		return estadoCobranzaFiltroItems;
	}


	public void setEstadoCobranzaFiltroItems(List estadoCobranzaFiltroItems) {
		this.estadoCobranzaFiltroItems = estadoCobranzaFiltroItems;
	}


	public List getEstadoCobranzaItems() {
		return estadoCobranzaItems;
	}


	public void setEstadoCobranzaItems(List estadoCobranzaItems) {
		this.estadoCobranzaItems = estadoCobranzaItems;
	}


	public List getEstadoConsumoFiltroItems() {
		return estadoConsumoFiltroItems;
	}


	public void setEstadoConsumoFiltroItems(List estadoConsumoFiltroItems) {
		this.estadoConsumoFiltroItems = estadoConsumoFiltroItems;
	}


	public List getEstadoConsumoItems() {
		return estadoConsumoItems;
	}


	public void setEstadoConsumoItems(List estadoConsumoItems) {
		this.estadoConsumoItems = estadoConsumoItems;
	}


	public ClienteTransaccion getClienteFiltro() {
		return clienteFiltro;
	}


	public void setClienteFiltro(ClienteTransaccion clienteFiltro) {
		this.clienteFiltro = clienteFiltro;
	}


	public String getConsumoHabilitado() {
		return consumoHabilitado;
	}


	public void setConsumoHabilitado(String consumoHabilitado) {
		this.consumoHabilitado = consumoHabilitado;
	}


	public String getEstadoCobranza() {
		return estadoCobranza;
	}


	public void setEstadoCobranza(String estadoCobranza) {
		this.estadoCobranza = estadoCobranza;
	}


	public boolean isVerFiltroClientes() {
		return verFiltroClientes;
	}

}
