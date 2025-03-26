package com.bizitglobal.webapp.faces.beans.contabilidad;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

import org.apache.log4j.Logger;
import org.apache.myfaces.custom.tree2.HtmlTree;
import org.apache.myfaces.custom.tree2.TreeNode;
import org.apache.myfaces.custom.tree2.TreeNodeBase;
import org.apache.myfaces.renderkit.html.util.AddResource;
import org.apache.myfaces.renderkit.html.util.AddResourceFactory;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.contabilidad.exception.PlanCuentaDosDuplicateException;
import com.bizitglobal.tarjetafiel.contabilidad.exception.PlanCuentaDosException;
import com.bizitglobal.tarjetafiel.contabilidad.negocio.LoteDetalle;
import com.bizitglobal.tarjetafiel.contabilidad.negocio.PlanCuentaDos;
import com.bizitglobal.tarjetafiel.contabilidad.negocio.PlanCuentaSimple;
import com.bizitglobal.tarjetafiel.contabilidad.service.PlanCuentaDosService;
import com.bizitglobal.tarjetafiel.fondos.exception.ChequeEstadoException;
import com.bizitglobal.tarjetafiel.fondos.negocio.ChequeEstado;
import com.bizitglobal.tarjetafiel.general.exception.FormaPagoException;
import com.bizitglobal.tarjetafiel.general.negocio.FormaPago;
import com.bizitglobal.tarjetafiel.operador.negocio.Operador;
import com.bizitglobal.webapp.faces.beans.BaseBean;
import com.bizitglobal.webapp.faces.beans.fondos.CajaBean;
import com.bizitglobal.webapp.faces.beans.fondos.LibroMayorFondosBean;
import com.bizitglobal.webapp.faces.beans.proveedores.ComprobanteBean;
import com.bizitglobal.webapp.faces.beans.proveedores.FormaDePagoBean;
import com.bizitglobal.webapp.faces.beans.transacciones.ConceptoDetalleBean;
import com.bizitglobal.webapp.faces.beans.util.ScrollBean;
import com.bizitglobal.webapp.faces.util.AsientoCont;
import com.bizitglobal.webapp.faces.util.Error;
import com.bizitglobal.webapp.faces.util.PlanCuentaSeleccionable;
import com.bizitglobal.webapp.faces.util.Session;
import com.bizitglobal.webapp.faces.util.Util;


@SuppressWarnings({ "rawtypes", "unchecked", "unused" })
public class PlanCuentaBean extends BaseBean {
	private static final Logger log = Logger.getLogger(PlanCuentaDos.class);

	// Objetos para inicializar desde distintos origenes
	private int origen = 0;
	public static final int PLANCUENTA = 1;
	public static final int COMPROBANTE = 2;
	public static final int ASIENTO = 3;
	public static final int LOTE = 4;
	public static final int DETALLE_DE_LOTE = 5;
	public static final int LIBRO_MAYOR = 6;
	public static final int DETALLE_CONCEPTO_HABER = 7;
	public static final int DETALLE_CONCEPTO_DEBE = 8;
	public static final int LIBRO_MAYOR_MENSUALIZADO_REPORTE = 9;
	public static final int LIBRO_MAYOR_REPORTE = 10;
	public static final int CAJA = 11;
	public static final int CAJA_MP = 12;
	public static final int LIBRO_MAYOR_FONDOS_REPORTE = 13;
	public static final int LIBRO_MAYOR_FONDOS = 14;

	public static final Integer TIPO_FONDO_PREDETERMINADO = null;

	public static final int OTROS = 1;
	public static final int BANCOS = 2;
	public static final int CARTERA = 4;

	public static final String PROPIO = "P";
	public static final String TERCERO = "T";
	public static final String COMPARTIDO = "C";

	public static final String CHEQUE = "2";

	private PlanCuentaDos planCuenta;

	private String planSeleccionado;
	private String nroPlanSeleccionado;

	public static final String IDNODORAIZ = "-1";

	// Objetos Relacionados.
	PlanCuentaDos cuentaElegida = null; // este guarda una referencia a la cuenta elegida si es que la cuenta elegida es imputable.
	// PlanCuentaDos cuentaElegidaCajaMP = null; // este guarda una referencia a la cuenta elegida si es que la cuenta elegida es imputable.
	private boolean aCopiar = false;
	private TreeNode nodoClickeado;
	private String nodeClicked;
	private String idNodeClicked;
	private String nodoOrigen;
	private String idNodoOrigen;
	private String nroCuentaPadre;

	// booleano que nos indica si estamos realizando un alta de un nuevo plan cuenta.
	private boolean altaNuevoPlanCuenta;
	private boolean contabilidad;
	private boolean fondo;
	private boolean ajusteInflacion;
	private boolean flujoEfectivo;
	private boolean centroCosto;
	private boolean habilitada;
	private boolean desHabilitaControles;
	private boolean desHabilitaImputable;
	private boolean deshabilitaGuardar;
	private boolean accionDeshabilitada;

	private String tipoDeCuenta;
	private boolean mostrarTiposDeFondo;
	private Integer tipoDeFondo;
	private FormaPago formasDePago;
	private String uso;
	private String saldoHabitual;
	private Operador operador;
	private String nodoDestino;
	private TreeNode treeData;
	private boolean move;

	private List selectedItemsModulo;

	private List tiposDeFondoList;

	private List mediosPagoList;
	private List mediosPagoItem;
	private Long medioPagoSeleccionado;

	private List estadosPredList;
	private List estadosPredItem;
	private Long estadosPredSeleccionado;

	private boolean mostrarMediosPago;
	private boolean mostrarEstadosPredeterminados;
	HashMap hijosNodo;

	private List listaCentroDeCostos;
	private List listaCentroDeCostosAux;
	// private HtmlSelectOneMenu centroCostoSeleccionado;
	private Long idCentroCostoSeleccionado;

	// Para ser ocupados en la pantalla de búsqueda de planes de cuenta
	private String nroCuentaBuscada;
	private String tituloPlanCuentaBuscado;
	List planes;
	String metodoDeBusqueda = "";


	public PlanCuentaBean() {
		super();
		borrar();
	}


	// public void cargarListaDeCentrosDeCostosDisponibles() {
	// listaCentroDeCostos = new ArrayList();
	// try {
	// listaCentroDeCostosAux = contabilidadService.getCentroCostosService().getCentroCostos(new Filtro());
	// Iterator iter = listaCentroDeCostosAux.iterator();
	// while(iter.hasNext()) {
	// CentroCostos centro = (CentroCostos)iter.next();
	// listaCentroDeCostos.add(new SelectItem(centro.getIdCentroCostos(),centro.getDescripcion()));
	// }
	// // centroCostoSeleccionado.setValue(((SelectItem)listaCentroDeCostos.get(0)).getValue());
	// } catch (CentroCostosException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// }

	private void inicializarArbol() {
		// aca guardamos en un map que contendara una lista de hijos de cada nodo
		hijosNodo = new HashMap();
		List listaHijos = new ArrayList();
		try {
			List nodos = contabilidadService.getPlanCuentaDosService().getPlanCuentaSimple(new Filtro());
			Iterator it = nodos.iterator();
			PlanCuentaSimple p = new PlanCuentaSimple();
			while (it.hasNext()) {
				PlanCuentaSimple element = (PlanCuentaSimple) it.next();
				if (hijosNodo.get(element.getIdPadre()) == null) {
					listaHijos = new ArrayList();
					listaHijos.add(element);
					hijosNodo.put(element.getIdPadre(), listaHijos);
				}
				else
				{
					listaHijos = (List) hijosNodo.get(element.getIdPadre());
					listaHijos.add(element);
					hijosNodo.put(element.getIdPadre(), listaHijos);
				}
			}

			move = true;
			Filtro filtro = new Filtro();
			filtro.agregarCampoOperValor("idPadre", Filtro.IGUAL, new Long(PlanCuentaBean.IDNODORAIZ));
			List nodosRaizList;
			idNodeClicked = null;
			nodeClicked = null;
			treeData = null;

			log.info("Se intentará recuperar los nodos....");
			nodosRaizList = contabilidadService.getPlanCuentaDosService().getPlanCuentaSimple(filtro);
			Iterator iter = nodosRaizList.iterator();
			while (iter.hasNext()) {
				treeData = asignarHijo((PlanCuentaSimple) iter.next());
				log.info("y va un nodo mas....");

			}
			hijosNodo = null;
			System.gc();
		} catch (PlanCuentaDosException e) {
			e.printStackTrace();
		}
	}


	public TreeNode getTreeData() {
		return treeData;
	}


	/* ESTE METODO ES RECURSIVO PARA CREAR LOS NODOS */
	public TreeNode asignarHijo(PlanCuentaSimple padre) {
		log.info("Entramos al metodo asignar hijos");
		List hijosP = new ArrayList();
		// hijosP = getHijos(padre);
		hijosP = (List) hijosNodo.get(padre.getId());
		Iterator iter = hijosP.iterator();
		log.info("Los hijos de el nodo raiz son: " + hijosP.size());
		TreeNode nodePadre = new TreeNodeBase("nodo", padre.getTitulo(), String.valueOf(padre.getIdPlanCuenta()), false);
		while (iter.hasNext()) {
			PlanCuentaSimple hijo = (PlanCuentaSimple) iter.next();
			// System.out.println(hijo.getTitulo() + " Asignado a "+ padre.getTitulo());
			TreeNode nodeHijo = new TreeNodeBase("nodo", "", false);
			nodeHijo.setDescription(hijo.getTitulo());
			nodeHijo.setIdentifier(String.valueOf(hijo.getIdPlanCuenta()));
			nodePadre.getChildren().add(nodeHijo);
			asignarHijo(nodeHijo, hijo);
		}
		return nodePadre;
	}


	public TreeNode asignarHijo(TreeNode nodePadre, PlanCuentaSimple padre) {
		log.info("Entrando al método asignar hijos de algun nodo subordinado.");
		List hijosP = new ArrayList();
		hijosP = (List) hijosNodo.get(padre.getId());

		if (hijosP != null) {
			Iterator iter = hijosP.iterator();
			while (iter.hasNext()) {
				PlanCuentaSimple hijo = (PlanCuentaSimple) iter.next();

				// System.out.println(hijo.getTitulo() + " Asignado a " + padre.getTitulo());

				TreeNode nodeHijo = new TreeNodeBase("nodo", "", "", false);
				nodeHijo.setDescription(hijo.getTitulo());
				nodeHijo.setIdentifier(String.valueOf(hijo.getIdPlanCuenta()));
				nodePadre.getChildren().add(nodeHijo);
				asignarHijo(nodeHijo, hijo);
			}
		}
		return nodePadre;
	}


	public List getHijos(PlanCuentaSimple padre) {

		List aux = new ArrayList();
		List hijos = new ArrayList();

		Filtro filtro = new Filtro();
		filtro.agregarCampoOperValor("idPadre", Filtro.IGUAL, padre.getIdPlanCuenta());
		try {
			aux = contabilidadService.getPlanCuentaDosService().getPlanCuentaSimple(filtro);
			Iterator iter;
			iter = aux.iterator();
			while (iter.hasNext()) {
				PlanCuentaSimple planCuenta = (PlanCuentaSimple) iter.next();
				// if (planCuenta.getEstado().equals("H"))
				hijos.add(planCuenta);
			}
		} catch (PlanCuentaDosException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return hijos;
	}


	public List getHijos(Long idParent) {
		List aux = new ArrayList();
		List hijos = new ArrayList();

		Iterator it;
		Filtro filtro = new Filtro();
		filtro.agregarCampoOperValor("idPadre", Filtro.IGUAL, idParent);
		try {
			aux = contabilidadService.getPlanCuentaDosService().getPlanCuentaSimple(filtro);
			Iterator iter;
			iter = aux.iterator();
			while (iter.hasNext()) {
				PlanCuentaSimple planCuenta = (PlanCuentaSimple) iter.next();
				if (planCuenta.getEstado().equals("H"))
					// TODO crear uso en la bd y filtrar por uso
					// if(planCuenta.getUso()!=null && planCuenta.getUso().compareTo())
					hijos.add(planCuenta);
			}
		} catch (PlanCuentaDosException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return hijos;
	}


	/**
	 * muestra los datos asociados a cada nodo
	 * 
	 * @param p_event
	 * 
	 */
	public void processToogle(ActionEvent p_event) {

		UIComponent component = (UIComponent) p_event.getSource();
		while (!(component != null && component instanceof HtmlTree)) {
			component = component.getParent();
		}

		if (component != null) {
			HtmlTree tree = (HtmlTree) component;
			TreeNode node = tree.getNode();
			if (!aCopiar) {
				nodoClickeado = node;
				log.info("Hay un nodo clickeado: es el :" + nodoClickeado.getDescription());
				log.info("Tiene " + nodoClickeado.getChildCount() + " hijos");
				planSeleccionado = nodoClickeado.getDescription();
			}
			component = null;
			nodeClicked = node.getDescription();
			idNodeClicked = node.getIdentifier();
			borrarBaseBean();
			borrar();
			alta = false;
			llenarCampos();
			log.info("INFO DEL NODO " + node.getDescription());
			planSeleccionado = nodoClickeado.getDescription();
			if (!tree.isNodeExpanded()) {

			} else {
				// unloadChildren(node);
			}
		}
	}


	private void llenarCampos() {

		try {
			planCuenta = contabilidadService.getPlanCuentaDosService().leerPlanCuenta(new Long(idNodeClicked));
			planCuenta.setTitulo(planCuenta.getTitulo().trim());
			if (planCuenta != null) {
				if (planCuenta.getHabilitada() != null && planCuenta.getHabilitada().equals("S"))
					habilitada = true;
				else
					habilitada = false;
				if (planCuenta.getContab() != null && planCuenta.getContab().equals("S"))
					contabilidad = true;
				else
					contabilidad = false;

				if (planCuenta.getFondos() != null && planCuenta.getFondos().equals("S"))
				{
					this.fondo = true;
					this.mostrarTiposDeFondo = true;
					if (planCuenta.getTipoFondos() != null && planCuenta.getTipoFondos() != 0)
					{
						this.tipoDeFondo = planCuenta.getTipoFondos();
						llenarListaMediosPago();
						if (planCuenta.getFormaPago() != null)
						{
							this.medioPagoSeleccionado = planCuenta.getFormaPago().getIdFormaPago();
							if (planCuenta.getIdEstadoCheque() != null)
							{
								llenarListaEstadosPredeterminados();
								this.estadosPredSeleccionado = planCuenta.getIdEstadoCheque();
							}
							else
							{
								this.estadosPredSeleccionado = null;
							}
							this.mostrarMediosPago = true;
							if (tipoDeFondo != 1)
							{
								this.mostrarEstadosPredeterminados = true;
							}
							else
							{
								this.mostrarEstadosPredeterminados = false;
							}
						}
						else
						{
							this.medioPagoSeleccionado = null;
							this.mostrarMediosPago = true;
						}
					}
					else
					{
						this.tipoDeFondo = this.TIPO_FONDO_PREDETERMINADO;
						this.mostrarTiposDeFondo = false;
						this.medioPagoSeleccionado = null;
						this.mostrarMediosPago = false;
						this.estadosPredSeleccionado = null;
						this.mostrarEstadosPredeterminados = false;
					}
				}
				else
				{
					this.fondo = false;
					this.tipoDeFondo = this.TIPO_FONDO_PREDETERMINADO;
					this.mostrarTiposDeFondo = false;
					this.medioPagoSeleccionado = null;
					this.mostrarMediosPago = false;
					this.estadosPredSeleccionado = null;
					this.mostrarEstadosPredeterminados = false;
				}

				if (planCuenta.getTipoDeCuenta() != null && planCuenta.getTipoDeCuenta().equals("R"))
					tipoDeCuenta = "R";
				else
					tipoDeCuenta = "P";
				if (planCuenta.getAjusteInflacion() != null && planCuenta.getAjusteInflacion().equals("S"))
					ajusteInflacion = true;
				else
					ajusteInflacion = false;
				if (planCuenta.getFlujoEfectivo() != null && planCuenta.getFlujoEfectivo().equals("S"))
					flujoEfectivo = true;
				else
					flujoEfectivo = false;
				if (planCuenta.getUso() != null && planCuenta.getUso().equals("I"))
					uso = "I";
				else
					uso = "S";
				if (planCuenta.getSaldoHabitual() != null && planCuenta.getSaldoHabitual().equals("D"))
					saldoHabitual = "D";
				else
					saldoHabitual = "H";
				if (planCuenta.getCentroCosto() != null) {
					log.info("Buscando el centro de costo asignado por defecto");
					centroCosto = false;
					if (planCuenta.getCentroCosto().compareTo("S") == 0) {
						centroCosto = true;
					}
					// Iterator it = listaCentroDeCostosAux.iterator();
					// while (it.hasNext()) {
					// CentroCostos cen = (CentroCostos)it.next();
					// log.info("comparando... el centro del plan cuenta es: " + planCuenta.getCentroCosto());
					//
					// if (planCuenta.getCentroCosto().longValue()==cen.getIdCentroCostos().longValue()) {
					// log.info("CentroCostos encontrado");
					// idCentroCostoSeleccionado = cen.getIdCentroCostos();
					// centroCostoSeleccionado.setValue(idCentroCostoSeleccionado);
					// break;
					// }
					// }
				} else {
					centroCosto = false;
				}
			}
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (PlanCuentaDosException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}


	/**
	 * when tree node is expanded, this method will load its children
	 * 
	 * @param p_parentNode
	 */
	public void loadChildren(TreeNode p_parentNode) {
		List childList = p_parentNode.getChildren();

		// This is where you need to load the actual child nodes
		childList.add(new TreeNodeBase(p_parentNode.getType(), "folder"
				+ System.currentTimeMillis(), p_parentNode.getIdentifier() + 1,
				false));
	}


	public boolean getAlta() {
		return alta;
	}


	public void setAlta(boolean alta) {
		this.alta = alta;
	}


	public PlanCuentaDos getPlanCuenta() {
		return planCuenta;
	}


	public void setPlanCuenta(PlanCuentaDos planCuenta) {
		this.planCuenta = planCuenta;
	}


	/***************************************************************************
	 * ACCIONES DEL BEAN PLAN CUENTA
	 **************************************************************************/

	public String inicializar() {
		treeData = null;
		borrarBaseBean();
		borrar();
		if (Session.getBean("ScrollBean") != null) {
			ScrollBean bean = (ScrollBean) Session.getBean("ScrollBean");
			bean.setHiddenScrollY(new Integer(0));
		}
		// cargarListaDeCentrosDeCostosDisponiles();
		inicializarArbol();

		try {
			mediosPagoList = generalService.getFormaPagoService().getFormaPagos(new Filtro());
			mediosPagoItem = Util.cargarSelectItem(mediosPagoList);
		} catch (FormaPagoException e) {
			e.printStackTrace();
		}

		try {
			estadosPredList = fondosService.getChequeEstadoService().getChequeEstados(new Filtro());
			estadosPredItem = Util.cargarSelectItem(estadosPredList);
		} catch (ChequeEstadoException e) {
			e.printStackTrace();
		}
		tiposDeFondoList = new ArrayList();
		tiposDeFondoList.add(new SelectItem(1, "1 - Otros"));
		tiposDeFondoList.add(new SelectItem(2, "2 - Bancos"));
		tiposDeFondoList.add(new SelectItem(4, "4 - Cartera"));

		this.accionDeshabilitada = false;

		return "planCuenta";
	}


	public String grabar() {
		try {
			planCuenta.setTitulo(planCuenta.getTitulo().trim());
			planCuenta.setHabilitada(habilitada ? "S" : "N");
			planCuenta.setContab(contabilidad ? "S" : "N");
			// if (tipoDeCuenta.compareTo("R")==0)
			// planCuenta.setTipoDeCuenta("R");
			// else
			// planCuenta.setTipoDeCuenta("P");

			planCuenta.setTipoDeCuenta(tipoDeCuenta);
			if (this.fondo)
			{
				planCuenta.setFondos("S");
			}
			else
			{
				planCuenta.setFondos("N");
			}
			planCuenta.setTipoFondos(tipoDeFondo);
			if (this.medioPagoSeleccionado != null)
			{
				planCuenta.setFormaPago(new FormaPago(this.medioPagoSeleccionado));
			}
			else
			{
				planCuenta.setFormaPago(null);
			}
			planCuenta.setIdEstadoCheque(this.estadosPredSeleccionado);
			planCuenta.setAjusteInflacion(ajusteInflacion ? "S" : "N");
			planCuenta.setFlujoEfectivo(flujoEfectivo ? "S" : "N");
			// if (uso.compareTo("S")==0)
			// planCuenta.setUso("S");
			// else
			// planCuenta.setUso("I");
			planCuenta.setUso(uso);
			// if (saldoHabitual.compareTo("D")==0)
			// planCuenta.setSaldoHabitual("D");
			// else
			// planCuenta.setSaldoHabitual("H");
			planCuenta.setSaldoHabitual(saldoHabitual);
			planCuenta.setFecha_carga(new Timestamp(new Date().getTime()));
			planCuenta.setOperador(new Long(Session.getOperador().getId().longValue()));
			planCuenta.setEstado("H");
			Long centroCostosAAsignar = null;
			if (centroCosto) {
				log.info("grabaremos el plan cuenta seleccionado");
				planCuenta.setCentroCosto("S");
			} else {
				planCuenta.setCentroCosto("N");
			}

			/*
			 * if (planCuentaClicked.getCentroCosto().equals("S")) centroCosto=true; else centroCosto=false;
			 */
			/*
			 * if (planCuenta.getUso().equals("S")) contabilidad=false; else contabilidad=true;
			 */
			log.info(planCuenta.getTitulo());

			if (validar()) {
				// Inicio los servis que voy a usar
				PlanCuentaDosService planCuentaService = contabilidadService.getPlanCuentaDosService();
				if (alta || altaNuevoPlanCuenta) {
					// Grabo el nuevo objeto
					log.info("Grabo el plan de cuentas nuevo");
					planCuenta.setIdPadre(new Long(idNodeClicked));
					planCuentaService.grabarPlanCuenta(planCuenta);
					altaNuevoPlanCuenta = false;
					alta = false;
				} else {
					planCuentaService.actualizarPlanCuenta(planCuenta);
				}
				popup.setPopup(popup.ICONO_OK, "El plan Cuenta ha sido almacenado exitosamente.");
				inicializarArbol();
				// Deshabilita los controles solamente si se graba bien
				desHabilitaControles = !desHabilitaControles;
				accionDeshabilitada = false;
			} else {
				ScrollBean scrollBean = (ScrollBean) Session.getBean("ScrollBean");
				scrollBean.borrar();
			}
			// desHabilitaControles = !desHabilitaControles;
		} catch (PlanCuentaDosDuplicateException e1) {
			popup.setPopup(popup.ICONO_FALLA, "Falló el alta del Plan Cuenta.");
			e1.printStackTrace();
		} catch (PlanCuentaDosException e2) {
			popup.setPopup(popup.ICONO_FALLA,
					"Falló el alta del  Plan Cuenta.");
			e2.printStackTrace();
		} catch (Exception e3) {
			popup.setPopup(popup.ICONO_FALLA, "Falló el alta del  Plan Cuenta.");
			e3.printStackTrace();
		}
		return "";
	}


	public void borrar() {
		error.borrar();
		planes = new ArrayList();
		alta = true;
		altaNuevoPlanCuenta = false;
		nroCuentaPadre = "";
		tituloLargo = "TARJETA FIEL";
		tituloCorto = "Plan Cuentas";
		popup.borrar();
		planCuenta = new PlanCuentaDos();
		desHabilitaControles = true;
		desHabilitaImputable = true;
		centroCosto = false;
		// centroCostoSeleccionado = new HtmlSelectOneMenu();
		planSeleccionado = "";
	}


	public String cancelar() {
		borrar();
		accionDeshabilitada = false;
		return "inicio";
	}


	public void cancelar2() {
		borrar();
		llenarCampos();
		accionDeshabilitada = false;
	}


	public void habilitarSaldoHabitual(ValueChangeEvent event) {
		if (uso.equals("I")) {
			desHabilitaImputable = false;
		} else
			desHabilitaImputable = true;

	}


	public void setearAjusteInflacion(ValueChangeEvent event) {

		if (event.getNewValue().equals("R")) {
			ajusteInflacion = false;
		} else
			ajusteInflacion = true;

	}


	public void verSiEsDebeOHaber(ValueChangeEvent event) {
		if (event.getNewValue().equals("D")) {
			saldoHabitual = "D";
		} else
			saldoHabitual = "H";

	}


	public boolean validarArbolPrincipal() {
		error.borrar();
		log.info("IDNODO" + idNodoOrigen);
		if (idNodoOrigen == null) {
			if (altaNuevoPlanCuenta) {
				error.agregar(Error.PLAN_CUENTA_PADRE_REQUERIDO);
			} else {
				error.agregar(Error.PLAN_CUENTA_REQUERIDO);
			}
		} else {
			PlanCuentaDos planCuentaOrigen;
			try {
				planCuentaOrigen = contabilidadService.getPlanCuentaDosService().leerPlanCuenta(new Long(idNodoOrigen));
				if (altaNuevoPlanCuenta)
					nroCuentaPadre = planCuentaOrigen.getTitulo();
				// PlanCuenta planCuentaDestino =
				// contabilidadService.getPlanCuentaService().leerPlanCuenta(new
				// Long(idNodeClicked));
				if (planCuentaOrigen.getIdPadre().longValue() == new Long(this.IDNODORAIZ).longValue()) {
					if (!altaNuevoPlanCuenta)
						error.agregar(Error.PLAN_CUENTA_NODO_RAIZ_INTOCABLE);
				}

				if (planCuentaOrigen.getUso() == null || planCuentaOrigen.getUso().equals("I") && (alta || altaNuevoPlanCuenta))
				{
					error.agregar(Error.PLAN_CUENTA_NO_SUMARIZADA);
				}

			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (PlanCuentaDosException e) {
				e.printStackTrace();
			}
		}

		return (error.cantidad() == 0) ? true : false;
	}


	public boolean validarArbolPopup() {
		error.borrar();
		if (idNodoOrigen != null) {

			if (idNodeClicked == null)
				error.agregar(Error.PLAN_CUENTA_DESTINO_REQUERIDO);
			else {
				if (idNodoOrigen == idNodeClicked)
					error.agregar(Error.PLAN_CUENTA_ORIGEN_DISTINTO_DESTINO);

				if (move) {
					PlanCuentaDos planCuentaOrigen;
					try {
						planCuentaOrigen = contabilidadService
								.getPlanCuentaDosService().leerPlanCuenta(
										new Long(idNodoOrigen));

						if (planCuentaOrigen.getIdPadre().longValue() == new Long(idNodeClicked).longValue())
							error.agregar(Error.PLAN_CUENTA_PADRE_NODO_ORIGEN_DISTINTO_PADRE_NODO_DESTINO);
					} catch (NumberFormatException e) {
						e.printStackTrace();
					} catch (PlanCuentaDosException e) {
						e.printStackTrace();
					}
				}
			}

		}

		return (error.cantidad() == 0) ? true : false;
	}


	/**
	 * @deprecated
	 */
	public void nuevoNodo() {
		alta = true;
		log.info("ORIGEN " + nodoOrigen);
		log.info("DESTINO " + nodeClicked);
		mostrarAmPlanCuentaPopup();
	}


	public void modificarPlanCuenta() {
		if (nodeClicked != null && nodeClicked != "")
		{
			desHabilitaControles = false;
			deshabilitaGuardar = false;
			accionDeshabilitada = true;
		}
	}


	public void nuevoPlanCuenta() {
		idNodoOrigen = idNodeClicked;
		nodoOrigen = nodeClicked;
		altaNuevoPlanCuenta = true;
		System.out.println(nodoClickeado.toString());
		Boolean esSumarizada = true;
		if (validarArbolPrincipal()) {
			accionDeshabilitada = true;
			String nodin = nroCuentaPadre;
			borrar();
			altaNuevoPlanCuenta = true;
			nroCuentaPadre = nodin;
			desHabilitaControles = false;
			contabilidad = false;
			fondo = false;
			mostrarTiposDeFondo = false;
			tipoDeFondo = TIPO_FONDO_PREDETERMINADO;
			mostrarMediosPago = false;
			medioPagoSeleccionado = null;
			mostrarEstadosPredeterminados = false;
			estadosPredSeleccionado = null;
			tipoDeCuenta = "P";
			ajusteInflacion = false;
			flujoEfectivo = false;
			habilitada = false;
			uso = "I";
			saldoHabitual = "D";
			centroCosto = false;
		} else {
			altaNuevoPlanCuenta = false;
			accionDeshabilitada = false;
		}
	}


	/**
	 * @deprecated
	 */
	public void modificarNodo() {
		alta = false;

		mostrarAmPlanCuentaPopup();

	}


	public void moverNodo() {
		idNodoOrigen = idNodeClicked;
		nodoOrigen = nodeClicked;
		if (validarArbolPrincipal()) {
			move = true;
			mostrarArbolNodoDestinoPopup();
			accionDeshabilitada = true;
		}
	}


	public void copiarNodo() {
		idNodoOrigen = idNodeClicked;
		nodoOrigen = nodeClicked;
		if (validarArbolPrincipal()) {
			move = false;
			aCopiar = true;
			mostrarArbolNodoDestinoPopup();
			accionDeshabilitada = false;
		}
	}


	/**
	 * @deprecated
	 */

	public String mostrarAmPlanCuentaPopup() {
		String path = FacesContext.getCurrentInstance().getExternalContext()
				.getRequestContextPath();
		path += "/tarjetafiel/contabilidad/amPlanCuentaPopup.jsf";
		ejecutarJavaScript("popup('" + path + "',750,400), 'titlebar=no';");
		// move= true;
		idNodoOrigen = idNodeClicked;
		nodoOrigen = nodeClicked;
		return null;
	}


	public String mostrarArbolNodoDestinoPopup() {
		String path = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
		path += "/tarjetafiel/contabilidad/arbolPopup.jsf";
		ejecutarJavaScript("popup('" + path + "',750,400), 'titlebar=no';");
		idNodeClicked = null;
		nodeClicked = null;
		return null;
	}


	public String agregarNodoDestinoPopup() {
		// idNodoOrigen=idNodeClicked;
		// String nodoOrigen=nodeClicked;
		try {

			String tituloOriginal = "";
			log.info("ORIGEN " + nodoOrigen);
			log.info("DESTINO " + nodeClicked);
			boolean hijosSinParentesis = true;
			if (validarArbolPopup()) {
				planCuenta = contabilidadService.getPlanCuentaDosService().leerPlanCuenta(new Long(idNodoOrigen));
				tituloOriginal = planCuenta.getTitulo();
				log.info("TITULO ORIGINAL" + tituloOriginal);
				// planCuenta.setIdPadre(new Long(idNodeClicked));
				// verifico si ya existe el nombre del nodo origen en el nodo destino
				Filtro filtro = new Filtro();
				filtro.agregarCampoOperValor("idPadre", Filtro.IGUAL, new Long(idNodeClicked));
				filtro.agregarfuncion("AND (obj.titulo like '" + nodoOrigen
						+ "(%' OR obj.titulo like '" + nodoOrigen + "')");
				log.info(filtro.getHQL());
				List hijos = contabilidadService.getPlanCuentaDosService().getPlanCuenta(filtro);
				if (!hijos.isEmpty()) {
					int max = 2;
					String nroCopia = "";
					Iterator iter = hijos.iterator();
					while (iter.hasNext()) {
						PlanCuentaDos hijo = (PlanCuentaDos) iter.next();
						if (hijo.getTitulo().trim().endsWith(")")) {
							hijosSinParentesis = false;
							// si no es de dos cifras
							if (!hijo.getTitulo().substring(hijo.getTitulo().length() - 4, hijo.getTitulo().length() - 3).equals("(")) {
								nroCopia = hijo.getTitulo().substring(hijo.getTitulo().length() - 2, hijo.getTitulo().length() - 1);
								if (Integer.parseInt(nroCopia) >= max)
									max = Integer.parseInt(hijo.getTitulo().substring(hijo.getTitulo().length() - 2, hijo.getTitulo().length() - 1));
							} else {
								nroCopia = hijo.getTitulo().substring(hijo.getTitulo().length() - 3, hijo.getTitulo().length() - 1);
								if (Integer.parseInt(nroCopia) >= max)
									max = Integer.parseInt(hijo.getTitulo().substring(hijo.getTitulo().length() - 3, hijo.getTitulo().length() - 1));
							}
						}
					}

					String sufijo = String.valueOf(max + 1) + ")";
					if (!nodoOrigen.trim().endsWith(")")) {
						if (hijosSinParentesis) {
							planCuenta.setTitulo(nodoOrigen + "(" + max + ")");

						}
						else {
							planCuenta.setTitulo(nodoOrigen + "(" + sufijo);
						}

					}

					else {
						if (max < 10)
							planCuenta.setTitulo(nodoOrigen.substring(0,
									nodoOrigen.length() - 2)
									+ sufijo);
						else
							planCuenta.setTitulo(nodoOrigen.substring(0,
									nodoOrigen.length() - 3)
									+ sufijo);

					}

					log.info("MAX: " + max + planCuenta.getTitulo());
				}

				planCuenta.setFecha_carga(new Timestamp(new Date().getTime()));
				planCuenta.setOperador(new Long(Session.getOperador().getId().longValue()));

				PlanCuentaDosService planCuentaService = contabilidadService.getPlanCuentaDosService();
				if (move) {
					planCuenta.setIdPadre(new Long(idNodeClicked));
					planCuentaService.actualizarPlanCuenta(planCuenta);
					inicializarArbol();
				} else {
					log.info("COPIANDO PLAN CUENTA!!!");
					PlanCuentaDos nuevo = new PlanCuentaDos();
					nuevo.setIdPlanCuenta(null);
					nuevo.setCaja(planCuenta.getCaja());
					nuevo.setCodBco(planCuenta.getCodBco());
					nuevo.setCodCtaBco(planCuenta.getCodCtaBco());
					nuevo.setContab(planCuenta.getContab());
					nuevo.setCuenta(planCuenta.getCuenta());
					nuevo.setEstado(planCuenta.getEstado());
					nuevo.setFecha_carga(planCuenta.getFecha_carga());
					nuevo.setFondos(planCuenta.getFondos());
					nuevo.setHabilitada(planCuenta.getHabilitada());
					nuevo.setIdPadre(new Long(idNodeClicked));
					nuevo.setImporteMaximo(planCuenta.getImporteMaximo());
					nuevo.setMoneda(planCuenta.getMoneda());
					nuevo.setNumeroContable(planCuenta.getNumeroContable());
					// /setear arriba operador
					nuevo.setOperador(planCuenta.getOperador());
					nuevo.setSaldo(planCuenta.getSaldo());
					nuevo.setSeccion(planCuenta.getSeccion());
					nuevo.setSigno(planCuenta.getSigno());
					nuevo.setTipoCuenta(planCuenta.getTipoCuenta());
					nuevo.setTipoDeCuenta(planCuenta.getTipoDeCuenta());
					nuevo.setAjusteInflacion(planCuenta.getAjusteInflacion());
					nuevo.setFlujoEfectivo(planCuenta.getFlujoEfectivo());
					nuevo.setUso(planCuenta.getUso());
					nuevo.setSaldoHabitual((planCuenta.getSaldoHabitual()));
					nuevo.setTitulo(planCuenta.getTitulo());
					planCuenta.setTitulo(tituloOriginal);
					planCuentaService.grabarPlanCuenta(nuevo);

				}
				// nodeClicked=null;
				// idNodeClicked=null;

			}
		} catch (PlanCuentaDosException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			if (!move) {
				log.info("voy a copiar los hijos");
				PlanCuentaDosService planService = contabilidadService.getPlanCuentaDosService();
				Filtro filt = new Filtro();
				filt.agregarCampoOperValor("titulo", Filtro.LIKEXS, planCuenta.getTitulo());
				filt.agregarCampoOperValor("idPadre", Filtro.IGUAL, new Long(idNodeClicked));
				List listaPlanes = planService.getPlanCuenta(filt);
				PlanCuentaDos nodoOri = (PlanCuentaDos) listaPlanes.get(0);
				nodoOri.getIdPlanCuenta();

				copiarDescendencia(nodoOri, nodoOri.getIdPlanCuenta(), nodoClickeado);
				inicializarArbol();
			}
		} catch (PlanCuentaDosException e) {
			e.printStackTrace();
		}
		aCopiar = false;
		return null;
	}


	public void copiarDescendencia(PlanCuentaDos nodoPadre, Long idDelNuevoPadre, TreeNode nodoAux) throws PlanCuentaDosException {
		log.info("**************************************************************");
		log.info("el id con que fue grabado el nodo padre es : " + nodoPadre.getIdPlanCuenta());
		log.info("el nodoCliceado es: " + nodoClickeado.getDescription());
		log.info("y tiene hijos: " + nodoClickeado.getChildCount());
		if (!nodoAux.getChildren().isEmpty()) {
			int i = 0;
			log.info("El nodo posee hijos");
			Iterator iterDePlanes = nodoAux.getChildren().iterator();
			while (iterDePlanes.hasNext()) {
				TreeNode nodoAuxiliarHijo = (TreeNode) iterDePlanes.next();
				log.info("Copiaremos el nodo numero: " + i++);
				log.info("El id del nodo aux es: " + nodoAuxiliarHijo.getIdentifier());
				planCuenta = contabilidadService.getPlanCuentaDosService().leerPlanCuenta(new Long(nodoAuxiliarHijo.getIdentifier()));
				PlanCuentaDos nuevo = new PlanCuentaDos();
				nuevo.setIdPlanCuenta(null);
				nuevo.setCaja(planCuenta.getCaja());
				nuevo.setCodBco(planCuenta.getCodBco());
				nuevo.setCodCtaBco(planCuenta.getCodCtaBco());
				nuevo.setContab(planCuenta.getContab());
				nuevo.setCuenta(planCuenta.getCuenta());
				nuevo.setEstado(planCuenta.getEstado());
				nuevo.setFecha_carga(planCuenta.getFecha_carga());
				nuevo.setFondos(planCuenta.getFondos());
				nuevo.setHabilitada(planCuenta.getHabilitada());
				nuevo.setIdPadre(idDelNuevoPadre);
				nuevo.setImporteMaximo(planCuenta.getImporteMaximo());
				nuevo.setMoneda(planCuenta.getMoneda());
				nuevo.setNumeroContable(planCuenta.getNumeroContable());
				// /setear arriba operador
				nuevo.setOperador(planCuenta.getOperador());
				nuevo.setSaldo(planCuenta.getSaldo());
				nuevo.setSeccion(planCuenta.getSeccion());
				nuevo.setSigno(planCuenta.getSigno());
				nuevo.setTipoCuenta(planCuenta.getTipoCuenta());
				nuevo.setTipoDeCuenta(planCuenta.getTipoDeCuenta());
				nuevo.setAjusteInflacion(planCuenta.getAjusteInflacion());
				nuevo.setFlujoEfectivo(planCuenta.getFlujoEfectivo());
				nuevo.setUso(planCuenta.getUso());
				nuevo.setSaldoHabitual((planCuenta.getSaldoHabitual()));
				nuevo.setTitulo(planCuenta.getTitulo());
				contabilidadService.getPlanCuentaDosService().grabarPlanCuenta(nuevo);

				Filtro filt = new Filtro();
				filt.agregarCampoOperValor("titulo", Filtro.LIKEXS, planCuenta.getTitulo());
				filt.agregarCampoOperValor("idPadre", Filtro.IGUAL, nuevo.getIdPadre());
				List listaPlanes = contabilidadService.getPlanCuentaDosService().getPlanCuenta(filt);
				PlanCuentaDos nodoOri = (PlanCuentaDos) listaPlanes.get(0);
				copiarDescendencia(nodoOri, nodoOri.getIdPlanCuenta(), nodoAuxiliarHijo);
			}
		}
	}


	public void recargarYCerrarPopup(ActionEvent event) {
		if (validarSoloImputables()) {
			FacesContext facesContext = FacesContext.getCurrentInstance();
			String javaScriptText = "window.opener.recargar(); window.close();";
			AddResource addResource = AddResourceFactory.getInstance(facesContext);
			addResource.addInlineScriptAtPosition(facesContext, AddResource.HEADER_BEGIN, javaScriptText);
			// inicializarArbol2();
		}
	}


	public void borrarPlanCuenta() {
		try {
			idNodoOrigen = idNodeClicked;
			nodoOrigen = nodeClicked;
			if (validarArbolPrincipal()) {
				planCuenta = contabilidadService.getPlanCuentaDosService().leerPlanCuenta(new Long(idNodeClicked));
				planCuenta.setEstado("D");
				planCuenta.setFecha_carga(new Timestamp(new Date().getTime()));
				planCuenta.setOperador(new Long(Session.getOperador().getId().longValue()));
				PlanCuentaDosService planCuentaService = contabilidadService.getPlanCuentaDosService();
				planCuentaService.borrarPlanCuenta(planCuenta);
				inicializarArbol();

				limpiarCampos();

			}

		} catch (PlanCuentaDosException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}


	private void limpiarCampos() {
		// Limpia todos los campos mostrados
		planCuenta = new PlanCuentaDos();
		tipoDeCuenta = "";
		contabilidad = false;
		fondo = false;
		tipoDeFondo = TIPO_FONDO_PREDETERMINADO;
		mostrarTiposDeFondo = false;
		medioPagoSeleccionado = null;
		mostrarMediosPago = false;
		estadosPredSeleccionado = null;
		mostrarEstadosPredeterminados = false;
		ajusteInflacion = false;
		flujoEfectivo = false;
		habilitada = false;
		uso = "";
		saldoHabitual = "";
		centroCosto = false;
	}


	// por si el borrado es recursivo
	private void borrarPlanCuenta(TreeNode nodo) {
		List hijos = nodo.getChildren();
		Iterator iter = hijos.iterator();
		if (!hijos.isEmpty()) {
			// /borro primero los hijos
			while (iter.hasNext()) {
				TreeNode node = (TreeNode) iter.next();
				// if(node.isLeaf()){
				try {
					PlanCuentaDosService planCuentaService = contabilidadService
							.getPlanCuentaDosService();
					planCuentaService.actualizarPlanCuenta(planCuenta);
				} catch (PlanCuentaDosException e) {
					e.printStackTrace();
				}
			}
		}

	}


	public TreeNode getNodo() {
		List hijos = treeData.getChildren();
		Iterator iter = hijos.iterator();
		TreeNode nodo = null;
		if (!hijos.isEmpty()) {
			while (iter.hasNext()) {
				nodo = (TreeNode) iter.next();
				if (nodo.getIdentifier().equals(idNodeClicked)) {
					break;
				}
			}
		}
		return nodo;
	}


	public String cancelarPopup() {
		// borrar();
		return null;
	}


	public List getSelectedItemsModulo() {
		return selectedItemsModulo;
	}


	public void setSelectedItemsModulo(List selectedItemsModulo) {
		this.selectedItemsModulo = selectedItemsModulo;
	}


	public String getNodeClicked() {
		log.info(nodeClicked);
		return nodeClicked;
	}


	public void setNodeClicked(String nodeClicked) {
		this.nodeClicked = nodeClicked;
		log.info(nodeClicked);
	}


	public String getNodoDestino() {
		return nodoDestino;
	}


	public void setNodoDestino(String nodoDestino) {
		this.nodoDestino = nodoDestino;
	}


	public String reDirect() {
		return "";
	}


	public String getIdNodeClicked() {
		return idNodeClicked;
	}


	public void setIdNodeClicked(String idNodeClicked) {
		this.idNodeClicked = idNodeClicked;
	}


	public String getNodoOrigen() {
		return nodoOrigen;
	}


	public void setNodoOrigen(String nodoOrigen) {
		this.nodoOrigen = nodoOrigen;
	}


	public boolean isAjusteInflacion() {
		return ajusteInflacion;
	}


	public void setAjusteInflacion(boolean ajusteInflacion) {
		this.ajusteInflacion = ajusteInflacion;
	}


	public boolean isCentroCosto() {
		return centroCosto;
	}


	public void setCentroCosto(boolean centroCosto) {
		this.centroCosto = centroCosto;
	}


	public boolean isContabilidad() {
		return contabilidad;
	}


	public void setContabilidad(boolean contabilidad) {
		this.contabilidad = contabilidad;
	}


	public boolean isFlujoEfectivo() {
		return flujoEfectivo;
	}


	public void setFlujoEfectivo(boolean flujoEfectivo) {
		this.flujoEfectivo = flujoEfectivo;
	}


	public boolean isFondo() {
		return fondo;
	}


	public void setFondo(boolean fondo) {
		this.fondo = fondo;
	}


	public boolean isHabilitada() {
		return habilitada;
	}


	public void setHabilitada(boolean habilitada) {
		this.habilitada = habilitada;
	}


	public boolean isDesHabilitaControles() {
		return desHabilitaControles;
	}


	public void setDesHabilitaControles(boolean desHabilitaControles) {
		this.desHabilitaControles = desHabilitaControles;
	}


	public boolean isDesHabilitaImputable() {
		return desHabilitaImputable;
	}


	public void setHabilitaImputable(boolean desHabilitaImputable) {
		this.desHabilitaImputable = desHabilitaImputable;
	}


	public String getTipoDeCuenta() {
		return tipoDeCuenta;
	}


	public void setTipoDeCuenta(String tipoDeCuenta) {
		this.tipoDeCuenta = tipoDeCuenta;
	}


	public String getSaldoHabitual() {
		return saldoHabitual;
	}


	public void setSaldoHabitual(String saldoHabitual) {
		this.saldoHabitual = saldoHabitual;
	}


	public String getUso() {
		return uso;
	}


	public void setUso(String uso) {
		this.uso = uso;
	}


	public boolean isDeshabilitaGuardar() {
		return deshabilitaGuardar;
	}


	public void setDeshabilitaGuardar(boolean deshabilitaGuardar) {
		this.deshabilitaGuardar = deshabilitaGuardar;
	}


	public boolean validarCarga() {
		error.borrar();
		if (origen == 3 || origen == 4) {
			// nothing
		} else {
			if (planCuenta.getTitulo() == null || planCuenta.getTitulo().equals(""))
				error.agregar(Error.PLAN_CUENTA_TITULO_REQUERIDO);
		}
		return (error.cantidad() == 0) ? true : false;
	}


	public boolean validar() {
		error.borrar();
		if (origen == 3 || origen == 4) {
			// nothing
		} else {
			if (planCuenta.getTitulo() == null || planCuenta.getTitulo().equals(""))
				error.agregar(Error.PLAN_CUENTA_TITULO_REQUERIDO);
			boolean repetido = false;
			try {
				List hijos = getHijos(new Long(idNodeClicked));
				Iterator iter = hijos.iterator();
				while (iter.hasNext()) {
					PlanCuentaSimple hijo = (PlanCuentaSimple) iter.next();
					if (planCuenta.getTitulo().equals(hijo.getTitulo())) {
						repetido = true;
						break;
					}
				}
			} catch (NumberFormatException e) {
				List hijos = getHijos(planCuenta.getIdPlanCuenta());
				Iterator iter = hijos.iterator();
				repetido = false;
				while (iter.hasNext()) {
					PlanCuentaDos hijo = (PlanCuentaDos) iter.next();
					if (planCuenta.getTitulo().equals(hijo.getTitulo())) {
						repetido = true;
						break;

					}
				}
			}
			if (repetido) {
				error.agregar(Error.PLAN_CUENTA_TITULO_REPETIDO);
			}

			// Chequea si selecciona al menos un módulo
			if (!this.contabilidad && !this.fondo)
			{
				error.agregar(Error.PLAN_CUENTA_MODULO_NO_SELECCIONADO);
			}

			// Chequea que al seleccionar fondo se seleccione al menos un tipo de fondo
			if (this.fondo && this.tipoDeFondo == null)
			{
				error.agregar(Error.PLAN_CUENTA_TIPO_DE_FONDO_NO_SELECCIONADO);
			}

		}
		return (error.cantidad() == 0) ? true : false;
	}


	public boolean validarSoloImputables() {
		error.borrar();
		cuentaElegida = null;
		// cuentaElegidaCajaMP=null;
		if (metodoDeBusqueda.compareTo("T") == 0) {
			if (!planes.isEmpty()) {
				Iterator iter = planes.iterator();
				while (iter.hasNext()) {
					PlanCuentaSeleccionable aux = (PlanCuentaSeleccionable) iter.next();
					if (aux.getSeleccionado()) {
						cuentaElegida = aux.getPlanCuenta();
						// cuentaElegidaCajaMP = aux.getPlanCuenta();
					}
				}
			}
		} else {
			if (nodoClickeado != null) {
				try {
					planCuenta = contabilidadService.getPlanCuentaDosService().leerPlanCuenta(new Long(nodoClickeado.getIdentifier()));
					cuentaElegida = planCuenta;
					// cuentaElegidaCajaMP = planCuenta;
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (PlanCuentaDosException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		if (cuentaElegida == null)
			error.agregar(Error.PLAN_CUENTA_NO_SELECCIONADO);
		if (cuentaElegida != null && cuentaElegida.getUso().compareTo("S") == 0) {
			error.agregar(Error.PLAN_CUENTA_SOLO_IMPUTABLES);
		}

		return (error.cantidad() == 0) ? true : false;
	}


	public boolean isAltaNuevoPlanCuenta() {
		return altaNuevoPlanCuenta;
	}


	public void setAltaNuevoPlanCuenta(boolean altaNuevoPlanCuenta) {
		this.altaNuevoPlanCuenta = altaNuevoPlanCuenta;
	}


	public String getNroCuentaPadre() {
		return nroCuentaPadre;
	}


	public void setNroCuentaPadre(String nroCuentaPadre) {
		this.nroCuentaPadre = nroCuentaPadre;
	}


	public TreeNode getNodoClickeado() {
		return nodoClickeado;
	}


	public void setNodoClickeado(TreeNode nodoClickeado) {
		this.nodoClickeado = nodoClickeado;
	}


	public boolean isACopiar() {
		return aCopiar;
	}


	public void setACopiar(boolean copiar) {
		aCopiar = copiar;
	}


	public String getMetodoDeBusqueda() {
		return metodoDeBusqueda;
	}


	public void setMetodoDeBusqueda(String metodoDeBusqueda) {
		this.metodoDeBusqueda = metodoDeBusqueda;
	}


	public String getNroCuentaBuscada() {
		return nroCuentaBuscada;
	}


	public void setNroCuentaBuscada(String nroCuentaBuscada) {
		this.nroCuentaBuscada = nroCuentaBuscada;
	}


	public List getPlanes() {
		return planes;
	}


	public void setPlanes(List planes) {
		this.planes = planes;
	}


	public String getTituloPlanCuentaBuscado() {
		return tituloPlanCuentaBuscado;
	}


	public void setTituloPlanCuentaBuscado(String tituloPlanCuentaBuscado) {
		this.tituloPlanCuentaBuscado = tituloPlanCuentaBuscado;
	}


	// ********************************
	// Sección de métodos para la pantalla de búsqueda de planes de Cuenta

	public void inicializaBusqueda(int origen) {
		metodoDeBusqueda = "T";
		this.origen = origen;
	}


	// para borrar los parametros de busqueda
	public String resetearParametrosDeBusqueda() {
		nroCuentaBuscada = "";
		tituloPlanCuentaBuscado = "";
		planes = new ArrayList();
		return null;
	}


	public String buscarPlanes() {
		String path = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
		path += "/tarjetafiel/contabilidad/buscarPlanesDeCuenta.jsf";
		ejecutarJavaScript("popup('" + path + "',750,400), 'titlebar=no';");
		inicializarArbol();
		metodoDeBusqueda = "T";
		idNodeClicked = null;
		nodeClicked = null;
		return null;
	}


	public void cambiarMetodoDeBusqueda(ValueChangeEvent event) {
		log.info("El método de busqueda venia siendo : " + event.getNewValue());
		if (event.getNewValue().equals("T")) {
			metodoDeBusqueda = "T";
		} else
			metodoDeBusqueda = "A";
		inicializarArbol();
	}


	public boolean isBusquedaDeArbol() {
		if (metodoDeBusqueda.compareTo("A") == 0) {
			return true;
		} else {
			return false;
		}
	}


	public String aceptarFiltroPlan() {
		error.borrar();
		if (validarSoloImputables()) {
			switch (origen) {
			case COMPROBANTE:
				ComprobanteBean bean = (ComprobanteBean) Session.getBean("ComprobanteBean");
				AsientoCont asC = new AsientoCont(cuentaElegida);
				asC.getAsiento().setLeyenda(bean.getConceptoAsiento());
				bean.getTablaDeAsientos().add(asC);
				break;
			case ASIENTO:
			case LOTE:
				AsientosBean beanAsi = (AsientosBean) Session.getBean("AsientosBean");
				planCuenta = cuentaElegida;
				log.info("Cargando el plan en el bean.");
				if (origen == ASIENTO) {
					String var = "";
					if (beanAsi.getCuentaParaAsi() == null)
						beanAsi.setCuentaParaAsi("");
					if (beanAsi.getCuentaParaAsi().compareTo("") != 0)
						var = ", ";
					beanAsi.setCuentaParaAsi(beanAsi.getCuentaParaAsi() + var + planCuenta.getIdPlanCuenta());
				} else {
					String var = "";
					if (beanAsi.getCuentaParaLot() == null)
						beanAsi.setCuentaParaLot("");
					if (beanAsi.getCuentaParaLot() != null && beanAsi.getCuentaParaLot().compareTo("") != 0)
						var = ", ";
					beanAsi.setCuentaParaLot(beanAsi.getCuentaParaLot() + var + planCuenta.getIdPlanCuenta());
				}
				break;
			case DETALLE_DE_LOTE:
				log.info("El origen es un detalle de lote");
				AsientosBean bea = (AsientosBean) Session.getBean("AsientosBean");
				if (metodoDeBusqueda.compareTo("T") == 0) {
					if (!planes.isEmpty()) {
						Iterator iter = planes.iterator();
						while (iter.hasNext()) {
							PlanCuentaSeleccionable aux = (PlanCuentaSeleccionable) iter.next();
							if (aux.getSeleccionado()) {

								break;
							}

						}
					}
				} else {
					if (nodoClickeado != null) {
						try {
							planCuenta = contabilidadService.getPlanCuentaDosService().leerPlanCuenta(new Long(nodoClickeado.getIdentifier()));
							log.info("Cargando el plan en el bean.");
							bea.setCuentaNuevaParaDetalle(planCuenta);
							LoteDetalle loteAgregado = new LoteDetalle();
							loteAgregado.setNumeroImputa(planCuenta.getIdPlanCuenta());
							loteAgregado.setLeyenda(planCuenta.getTitulo());
							if (planCuenta.getSigno() != null) {
								if (planCuenta.getSigno().intValue() == 1) {
									loteAgregado.setSigno("C");
								} else {
									loteAgregado.setSigno("D");
								}
							}
							bea.getWrapLot().setSoyNuevo(true);
							bea.getWrapLot().setDebe("");
							bea.getWrapLot().setHaber("");
							bea.getLotesDetalles().add(bea.getWrapLot());
						} catch (NumberFormatException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (PlanCuentaDosException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
				break;
			case LIBRO_MAYOR:
				LibroMayorBean beanLibro = (LibroMayorBean) Session.getBean("LibroMayorBean");
				planCuenta = cuentaElegida;
				String var = "";
				if (beanLibro.getCuentaParaLibro() == null)
					beanLibro.setCuentaParaLibro(new String());
				if (beanLibro.getCuentaParaLibro().compareTo("") != 0)
					var = ", ";
				beanLibro.setCuentaParaLibro(beanLibro.getCuentaParaLibro() + var + planCuenta.getIdPlanCuenta());
				break;
			case LIBRO_MAYOR_REPORTE:
				LibroMayorBean beanLibroMayorRep = (LibroMayorBean) Session.getBean("LibroMayorBean");
				planCuenta = cuentaElegida;
				log.info("Cargando el plan en el bean.");
				String varDos = "";
				if (beanLibroMayorRep.getCuentaParaLibroReporte() == null)
					beanLibroMayorRep.setCuentaParaLibroReporte("");
				if (beanLibroMayorRep.getCuentaParaLibroReporte().compareTo("") != 0)
					varDos = ", ";
				beanLibroMayorRep.setCuentaParaLibroReporte(beanLibroMayorRep.getCuentaParaLibroReporte() + varDos + planCuenta.getIdPlanCuenta());
				break;
			case LIBRO_MAYOR_MENSUALIZADO_REPORTE:
				LibroMayorMensualizadoBean beanLibroMayorMensualizadoRep = (LibroMayorMensualizadoBean) Session.getBean("LibroMayorMensualizadoBean");
				planCuenta = cuentaElegida;
				String varTres = "";
				if (beanLibroMayorMensualizadoRep.getCuentaParaMensualizadoReporte() == null)
					beanLibroMayorMensualizadoRep.setCuentaParaMensualizadoReporte("");
				if (beanLibroMayorMensualizadoRep.getCuentaParaMensualizadoReporte().compareTo("") != 0)
					varTres = ", ";
				beanLibroMayorMensualizadoRep.setCuentaParaMensualizadoReporte(beanLibroMayorMensualizadoRep.getCuentaParaMensualizadoReporte()
						+ varTres + planCuenta.getIdPlanCuenta());
				break;
			case DETALLE_CONCEPTO_HABER:
			case DETALLE_CONCEPTO_DEBE:
				ConceptoDetalleBean beanDet = (ConceptoDetalleBean) Session.getBean("ConceptoDetalleBean");
				planCuenta = cuentaElegida;
				if (origen == DETALLE_CONCEPTO_HABER) {
					beanDet.setCtacontablehaber(planCuenta.getIdPlanCuenta() + " - " + planCuenta.getTitulo());
					beanDet.setCuentaHaber(planCuenta.getIdPlanCuenta());
					beanDet.setHaber(planCuenta.getIdPlanCuenta() + " - " + planCuenta.getTitulo());
				} else {
					beanDet.setCtacontabledebe(planCuenta.getIdPlanCuenta() + " - " + planCuenta.getTitulo());
					beanDet.setCuentaDebe(planCuenta.getIdPlanCuenta());
					beanDet.setDebe(planCuenta.getIdPlanCuenta() + " - " + planCuenta.getTitulo());
				}
				break;
			case CAJA:
				CajaBean beanCaja = (CajaBean) Session.getBean("CajaBean");
				// planCuenta = cuentaElegida;

				if (beanCaja.getIdPlanCuenta() == null)
					beanCaja.setIdPlanCuenta(new String());
				beanCaja.setIdPlanCuenta(cuentaElegida.getIdPlanCuenta().toString());
				break;
			case CAJA_MP:
				FormaDePagoBean formaPagoBean = (FormaDePagoBean) Session.getBean("FormaDePagoBean");
				// planCuenta = cuentaElegidaCajaMP;
				if (formaPagoBean.getIdPlanCuenta() == null)
					formaPagoBean.setIdPlanCuenta(new String());
				formaPagoBean.setIdPlanCuenta(cuentaElegida.getIdPlanCuenta().toString());
				break;
			case LIBRO_MAYOR_FONDOS_REPORTE:
				LibroMayorFondosBean beanLibroMayorFondosRep = (LibroMayorFondosBean) Session.getBean("LibroMayorFondosBean");
				planCuenta = cuentaElegida;
				log.info("Cargando el plan en el bean.");
				String varCuatro = "";
				if (beanLibroMayorFondosRep.getCuentaParaLibroReporte() == null)
					beanLibroMayorFondosRep.setCuentaParaLibroReporte("");
				if (beanLibroMayorFondosRep.getCuentaParaLibroReporte().compareTo("") != 0)
					varDos = ", ";
				beanLibroMayorFondosRep.setCuentaParaLibroReporte(beanLibroMayorFondosRep.getCuentaParaLibroReporte() + varCuatro
						+ planCuenta.getIdPlanCuenta());
				break;
			case LIBRO_MAYOR_FONDOS:
				LibroMayorFondosBean beanLibroFondos = (LibroMayorFondosBean) Session.getBean("LibroMayorFondosBean");
				planCuenta = cuentaElegida;
				String varCinco = "";
				if (beanLibroFondos.getCuentaParaLibro() == null)
					beanLibroFondos.setCuentaParaLibro(new String());
				if (beanLibroFondos.getCuentaParaLibro().compareTo("") != 0)
					var = ", ";
				beanLibroFondos.setCuentaParaLibro(beanLibroFondos.getCuentaParaLibro() + varCinco + planCuenta.getIdPlanCuenta());
				break;

			}
		}
		resetearParametrosDeBusqueda();
		return null;
	}


	public String cancelarFiltroPlan() {
		resetearParametrosDeBusqueda();
		return null;
	}


	public String filtrarPlanes() {
		log.info("filtrando planes!!!");
		if (planes != null)
			planes.clear();
		else
			planes = new ArrayList();
		Filtro filtro = new Filtro();
		if (nroCuentaBuscada != null && nroCuentaBuscada != "") {
			filtro.agregarCampoOperValor("idPlanCuenta", Filtro.IGUAL, new Long(nroCuentaBuscada));
		}
		if (tituloPlanCuentaBuscado != null && tituloPlanCuentaBuscado != "") {
			filtro.agregarCampoOperValor("titulo", Filtro.LIKE, tituloPlanCuentaBuscado);
		}
		if (origen == CAJA || origen == CAJA_MP) {
			filtro.agregarCampoOperValor("fondos", Filtro.LIKE, "S");
			if (origen == CAJA_MP)
				filtro.agregarCampoOperValor("uso", Filtro.LIKE, "I");
		}

		log.info("Filtro -> " + filtro.getHQL());

		try {
			List planesSeleccionables = contabilidadService.getPlanCuentaDosService().getPlanCuenta(filtro);
			if (!planesSeleccionables.isEmpty()) {
				Iterator iter = planesSeleccionables.iterator();
				while (iter.hasNext()) {
					PlanCuentaDos aux = (PlanCuentaDos) iter.next();
					planes.add(new PlanCuentaSeleccionable(aux, false));
				}
			}
		} catch (PlanCuentaDosException e) {
			e.printStackTrace();
		}
		// if (tituloPlanCuentaBuscado!=null&&tituloPlanCuentaBuscado!="") {
		// List hijosNodoRaiz = treeData.getChildren();
		// Iterator hijosDe = hijosNodoRaiz.iterator();
		// while (hijosDe.hasNext()) {
		// TreeNode nodoHijoDe = (TreeNode)hijosDe.next();
		//
		// }
		// } else {
		//
		// }
		return null;
	}


	public void cambiaEstadoTiposFondo(ValueChangeEvent event)
	{
		if (event.getNewValue().equals(true)) {
			log.info("Pone mostrarTiposDeFon en true");
			mostrarTiposDeFondo = true;
			medioPagoSeleccionado = new Long(1);
			mostrarMediosPago = true;
		} else
			mostrarTiposDeFondo = false;
		tipoDeFondo = this.TIPO_FONDO_PREDETERMINADO;
		// Oculta el combo de medios de pago
		mostrarMediosPago = false;
		medioPagoSeleccionado = null;
		mostrarEstadosPredeterminados = false;
		estadosPredSeleccionado = null;
		log.info("Pone mostrarTiposDeFon en false");
	}


	public void obtenerMediosDePago(ValueChangeEvent event)
	{
		String medioSeleccionado;
		if (event.getNewValue() != null)
		{
			medioSeleccionado = event.getNewValue().toString();
		}
		else
		{
			medioSeleccionado = "";
		}
		if (!medioSeleccionado.equals(""))
		{
			// Setea el tipo de fondo seleccionado
			this.tipoDeFondo = (Integer) event.getNewValue();
			SelectItem tempSelectItem = (SelectItem) this.mediosPagoItem.get(0);
			this.medioPagoSeleccionado = (Long) tempSelectItem.getValue();
			// Busca los medios de pago aplicables al tipo de fondo seleccionado
			llenarListaMediosPago();
			mostrarMediosPago = true;
			// Selecciona el item 0 por defecto
			llenarListaEstadosPredeterminados();
		}
		else
		{
			this.tipoDeFondo = this.TIPO_FONDO_PREDETERMINADO;
			this.medioPagoSeleccionado = null;
			this.estadosPredSeleccionado = null;
			mostrarMediosPago = false;
		}
	}


	private void llenarListaEstadosPredeterminados() {
		// Obtiene los estados posibles del medio de pago
		obtenerEstadosPredeterminados(medioPagoSeleccionado.toString());
	}


	private void llenarListaMediosPago() {
		List tempMediosPagosList = new ArrayList();
		Iterator tmpIterator = this.mediosPagoList.iterator();

		while (tmpIterator.hasNext())
		{
			FormaPago tmpFormaPago = (FormaPago) tmpIterator.next();
			if (tmpFormaPago.getClaveTiposFondo() != null &&
					tmpFormaPago.getClaveTiposFondo().contains(this.tipoDeFondo.toString()))
			{
				tempMediosPagosList.add(tmpFormaPago);
			}

		}
		this.mediosPagoItem = Util.cargarSelectItem(tempMediosPagosList);
	}


	/**
	 * Acorde al tipo de fondo y al medio seleccionado selecciona los tipos de estado asociado a cada medio de pago
	 * 
	 * @param medioSeleccionado
	 */
	private void obtenerEstadosPredeterminados(String medioSeleccionado)
	{
		/*
		 * Cuando el medio de pago seleccionado es otros no tiene posibilidad de seleciconar un estado, de lo contrario puede seleccionar un estado de
		 * acuerdo al medio de pago
		 */
		if (this.tipoDeFondo == this.OTROS)
		{
			this.mostrarEstadosPredeterminados = false;
			this.estadosPredSeleccionado = null;
		}
		else
		{
			List tempEstadoList = new ArrayList();

			if (this.tipoDeFondo == this.BANCOS)
			{
				// Cheque
				if (medioSeleccionado.equals(this.CHEQUE))
				{
					agregarEstado(this.PROPIO, estadosPredList, tempEstadoList);
					agregarEstado(this.COMPARTIDO, estadosPredList, tempEstadoList);
				}
				// Otros
				else
				{
					agregarEstado(this.PROPIO, estadosPredList, tempEstadoList);
				}
			}
			else if (this.tipoDeFondo == this.CARTERA)
			{
				// Solamente cheques
				agregarEstado(this.TERCERO, estadosPredList, tempEstadoList);
				agregarEstado(this.COMPARTIDO, estadosPredList, tempEstadoList);
			}
			this.mostrarEstadosPredeterminados = true;
			this.estadosPredItem = Util.cargarSelectItem(tempEstadoList);
			// Setea el primer estado como el seleccionado
			SelectItem tempEstadoSeleccionado = (SelectItem) estadosPredItem.get(0);
			// Setea el estado de pago seleccionado
			this.estadosPredSeleccionado = (Long) tempEstadoSeleccionado.getValue();
		}
	}


	/**
	 * Busca el tipo de estado en origen y si existe y coincide lo agrega a destino
	 * 
	 * @param tipo
	 * @param origen
	 * @param destino
	 */
	private void agregarEstado(String tipo, List origen, List destino)
	{
		Iterator estadoIt = origen.iterator();
		while (estadoIt.hasNext())
		{
			ChequeEstado tempEstado = (ChequeEstado) estadoIt.next();
			Character estado = tempEstado.getTipo();
			if (estado == tipo.charAt(0))
			{
				destino.add(tempEstado);
			}
		}
	}


	/**
	 * Al cambiar el medio de pago lo actualiza y obtiene los estados disponible para el medio de pago seleccionado
	 * 
	 * @param event
	 */
	public void actualizarEstadosPredeterminados(ValueChangeEvent event)
	{
		if (event.getNewValue() != null)
		{
			this.medioPagoSeleccionado = (Long) event.getNewValue();
			obtenerEstadosPredeterminados(event.getNewValue().toString());
		}
	}


	public void verSiNodoContiene(TreeNode nodoOriginario) {

	}


	public boolean getIsBusquedaDeArbol() {
		return isBusquedaDeArbol();
	}


	public List getListaCentroDeCostos() {
		return listaCentroDeCostos;
	}


	public void setListaCentroDeCostos(List listaCentroDeCostos) {
		this.listaCentroDeCostos = listaCentroDeCostos;
	}


	public Long getIdCentroCostoSeleccionado() {
		return idCentroCostoSeleccionado;
	}


	public void setIdCentroCostoSeleccionado(Long idCentroCostoSeleccionado) {
		this.idCentroCostoSeleccionado = idCentroCostoSeleccionado;
	}


	public String getNroPlanSeleccionado() {
		return nroPlanSeleccionado;
	}


	public void setNroPlanSeleccionado(String nroPlanSeleccionado) {
		this.nroPlanSeleccionado = nroPlanSeleccionado;
	}


	public String getPlanSeleccionado() {
		return planSeleccionado;
	}


	public void setPlanSeleccionado(String planSeleccionado) {
		this.planSeleccionado = planSeleccionado;
	}


	public boolean isMostrarTiposDeFondo() {
		return mostrarTiposDeFondo;
	}


	public void setMostrarTiposDeFondo(boolean mostrarTiposDeFondo) {
		this.mostrarTiposDeFondo = mostrarTiposDeFondo;
	}


	public List getMediosPagoItem() {
		return mediosPagoItem;
	}


	public void setMediosPagoItem(List mediosPagoItem) {
		this.mediosPagoItem = mediosPagoItem;
	}


	public Long getMedioPagoSeleccionado() {
		return medioPagoSeleccionado;
	}


	public void setMedioPagoSeleccionado(Long medioPagoSeleccionado) {
		this.medioPagoSeleccionado = medioPagoSeleccionado;
	}


	public boolean isMostrarMediosPago() {
		return mostrarMediosPago;
	}


	public void setMostrarMediosPago(boolean mostrarMediosPago) {
		this.mostrarMediosPago = mostrarMediosPago;
	}


	public List getEstadosPredList() {
		return estadosPredList;
	}


	public void setEstadosPredList(List estadosPredList) {
		this.estadosPredList = estadosPredList;
	}


	public List getEstadosPredItem() {
		return estadosPredItem;
	}


	public void setEstadosPredItem(List estadosPredItem) {
		this.estadosPredItem = estadosPredItem;
	}


	public Long getEstadosPredSeleccionado() {
		return estadosPredSeleccionado;
	}


	public void setEstadosPredSeleccionado(Long estadosPredSeleccionado) {
		this.estadosPredSeleccionado = estadosPredSeleccionado;
	}


	public boolean isMostrarEstadosPredeterminados() {
		return mostrarEstadosPredeterminados;
	}


	public void setMostrarEstadosPredeterminados(
			boolean mostrarEstadosPredeterminados) {
		this.mostrarEstadosPredeterminados = mostrarEstadosPredeterminados;
	}


	public List getTiposDeFondoList() {
		return tiposDeFondoList;
	}


	public void setTiposDeFondoList(List tiposDeFondoList) {
		this.tiposDeFondoList = tiposDeFondoList;
	}


	public void setTipoDeFondo(Integer tipoDeFondo) {
		this.tipoDeFondo = tipoDeFondo;
	}


	public Integer getTipoDeFondo() {
		return tipoDeFondo;
	}


	public boolean isaccionDeshabilitada() {
		return accionDeshabilitada;
	}


	public void setaccionDeshabilitada(boolean accionDeshabilitada) {
		this.accionDeshabilitada = accionDeshabilitada;
	}

}
