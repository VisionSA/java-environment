package com.bizitglobal.webapp.faces.beans.fondos;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

import javax.faces.component.html.HtmlSelectOneMenu;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

import org.apache.log4j.Logger;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.commons.util.Convertidores;
import com.bizitglobal.tarjetafiel.fondos.exception.CajaAperturaException;
import com.bizitglobal.tarjetafiel.fondos.exception.CajaException;
import com.bizitglobal.tarjetafiel.fondos.exception.CajaMPException;
import com.bizitglobal.tarjetafiel.fondos.exception.MovimientoException;
import com.bizitglobal.tarjetafiel.fondos.exception.MovimientoMPException;
import com.bizitglobal.tarjetafiel.fondos.negocio.Caja;
import com.bizitglobal.tarjetafiel.fondos.negocio.CajaApertura;
import com.bizitglobal.tarjetafiel.fondos.negocio.CajaMP;
import com.bizitglobal.tarjetafiel.fondos.negocio.Movimiento;
import com.bizitglobal.tarjetafiel.fondos.negocio.MovimientoMP;
import com.bizitglobal.tarjetafiel.fondos.service.CajaAperturaService;
import com.bizitglobal.tarjetafiel.general.exception.ConceptoGenException;
import com.bizitglobal.tarjetafiel.general.negocio.ConceptoGen;
import com.bizitglobal.tarjetafiel.general.negocio.FormaPago;
import com.bizitglobal.tarjetafiel.operador.negocio.Operador;
import com.bizitglobal.tarjetafiel.transacciones.exception.ColaboradorException;
import com.bizitglobal.tarjetafiel.transacciones.negocio.Colaborador;
import com.bizitglobal.webapp.faces.beans.BaseBean;
import com.bizitglobal.webapp.faces.beans.util.ScrollBean;
import com.bizitglobal.webapp.faces.util.Error;
import com.bizitglobal.webapp.faces.util.Session;
import com.bizitglobal.webapp.faces.util.Util;


@SuppressWarnings({"rawtypes","unchecked"})
public class AperturaCajaBean extends BaseBean {
	private static final Logger log = Logger.getLogger(AperturaCajaBean.class);
	private CajaApertura cajaApertura;
	private Long idAperturaCajaHidden;
	private String idCaja;
	private static int posicionFormaPago = 0;

	// Listas para la presentacion(HtmlSelectItems).
	private List cajaList = new ArrayList();
	private List cajaItems = new ArrayList();
	private List operadorList = new ArrayList();
	private List operadorItems = new ArrayList();
	private List tablaDeFormaDePago;
	private Map valoresCierresAnterioresMap = new HashMap();
	// definicion de un list del objeto base
	private List aperturaCajaList;
	private List unaCajaApertura;

	// Objetos Relacionados.
	private Long idCajaSeleccionada;
	private Long idOperadorSeleccionado;
	private HtmlSelectOneMenu cajaHtml = new HtmlSelectOneMenu();
	private boolean mostrarPanel;
	private double saldoInicial; // este valor es solo para mostrar la cantidad q hay realmente en la caja
									// al abrir la caja lo que seteamos es cajaApertura.SaldoInicial= saldoFinalUltimoCierre
									// cuyo valor debe permanecer inalterable independiente de los movimientos entre el
									// cierre anterior y la nueva apertura
	private String focoHidden;
	private boolean mostrarCajas;
	private boolean mostrarDetalleApertura;
	private HashMap<Object, Movimiento> mapMovientosCierre;


	public AperturaCajaBean() {
		super();
		borrar();
	}


	private void setearValoresSegunCierresAnteriores(String cajasAbiertas) {
		// traemos los ultimos cierres de las cajas
		valoresCierresAnterioresMap = new HashMap();
		String cajasAperturasCerradas = "";
		try {
			cajasAperturasCerradas = fondosService.getCajaAperturaService().ultimoCierreCajas(alta, cajasAbiertas);

			if (cajasAperturasCerradas.compareTo("") != 0) {
				List cajasAperturasList;
				cajasAperturasList = fondosService.getCajaAperturaService().getCajaAperturas(
						new Filtro("idCajaApertura", Filtro.IN, cajasAperturasCerradas));
				Iterator it = cajasAperturasList.iterator();
				while (it.hasNext()) {
					CajaApertura element = (CajaApertura) it.next();
					cajaList.add(element.getCaja());
					valoresCierresAnterioresMap.put(element.getCaja().getIdCaja(), element);
				}
			}
		} catch (CajaAperturaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}


	private void setearValoresSegunAperturasAnteriores(String cajasAbiertas) {
		// traemos los ultimos cierres de las cajas
		valoresCierresAnterioresMap = new HashMap();
		String cajasAperturasAbiertas = "";
		try {
			// cajasAperturasAbiertas = fondosService.getCajaAperturaService().ultimoCierreCajas(alta,cajasAbiertas);
			cajasAperturasAbiertas = fondosService.getCajaAperturaService().ultimaAperturaCajas();
			if (cajasAperturasAbiertas.compareTo("") != 0) {
				List cajasAperturasList;
				cajasAperturasList = fondosService.getCajaAperturaService().getCajaAperturas(
						new Filtro("idCajaApertura", Filtro.IN, cajasAperturasAbiertas));
				Iterator it = cajasAperturasList.iterator();
				while (it.hasNext()) {
					CajaApertura element = (CajaApertura) it.next();
					element.getCaja().getIdCaja();
					element.getCaja().getDescripcion();
					WrapperCajaApertura wrap = new WrapperCajaApertura(element);
					cajaList.add(wrap);
					// valoresCierresAnterioresMap.put(element.getCaja().getIdCaja(),element);
				}
			}
		} catch (CajaAperturaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}


	public void cambiarCaja(ValueChangeEvent event) {
		idCajaSeleccionada = (Long) cajaHtml.getValue();
		if (idCajaSeleccionada.longValue() != 0) {
			mostrarPanel = true;
			Util.limpiarLista(tablaDeFormaDePago);
			System.out.println(idCajaSeleccionada);
			double difereciaTotalMovPosteriores = 0;

			List movimientos = new ArrayList();
			List mediosPagosCajaList = new ArrayList();
			HashMap tablaDeFormaDePagoMap = new HashMap();

			// cajaApertura.setSaldoInicial((Double)valoresCierresAnterioresMap.get(idCajaSeleccionada.toString()));
			// cajaApertura.setSaldoInicial(((CajaApertura)valoresCierresAnterioresMap.get(new Long(1))).getSaldoFinal());

			// buscamos los medio de pago asociados a la caja
			Filtro filter = new Filtro();
			// filter.agregarCampoOperValor("planCuentaDos.idPlanCuenta",Filtro.NOTNULL,"");
			filter.agregarCampoOperValor("caja.idCaja", Filtro.IGUAL, idCajaSeleccionada);

			try {
				mediosPagosCajaList = fondosService.getCajaMPService().getCajaMPs(filter);
				Iterator ite = mediosPagosCajaList.iterator();
				while (ite.hasNext()) {
					CajaMP object = (CajaMP) ite.next();
					// if(object.getPlanCuentaDos()!= null) //cargamos en el map solo los q tengan una cuenta asociada
					tablaDeFormaDePagoMap.put(object.getFormaPago().getIdFormaPago(), object.getFormaPago());
				}
			} catch (CajaMPException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			CajaApertura apertura = (CajaApertura) valoresCierresAnterioresMap.get(idCajaSeleccionada);
			if (apertura.getIdCajaApertura() != null) {
				try {

					Filtro filtro = new Filtro();
					filtro.agregarCampoOperValor("caja.idCaja", Filtro.IGUAL, idCajaSeleccionada);
					// filtro.agregarCampoOperValor("concepto.codigoConcepto",Filtro.IGUAL,new Long(401));
					filtro.agregarCampoOperValor("concepto.codigoConcepto", Filtro.IGUAL, 401);
					filtro.agregarCampoOperValor("cajaApertura.idCajaApertura", Filtro.IGUAL, apertura.getIdCajaApertura());
					movimientos = fondosService.getMovimientoService().getMovimientos(filtro);

					if (movimientos.size() > 0) { // si no entra aca es porq la caja no tuvo movientos(no ha sido abierta nunca)
						Movimiento movimiento = (Movimiento) movimientos.get(0);// segun el filtro aplicado deberia traer un unico movimiento de
																				// cierre
						mapMovientosCierre = new HashMap<Object, Movimiento>();
						for (Object mov : movimiento.getMovimientosMP()) {
							mapMovientosCierre.put(((MovimientoMP) mov).getFormaPago().getIdFormaPago(), movimiento);
						}
						tablaDeFormaDePago = Convertidores.setToList(movimiento.getMovimientosMP());
						cajaApertura.setSaldoInicial(movimiento.getImporte());
						// List listaMovimMPAgregados= new ArrayList();//aca agregamos los
						Iterator iterator = tablaDeFormaDePagoMap.keySet().iterator();
						while (iterator.hasNext()) {
							FormaPago object = (FormaPago) tablaDeFormaDePagoMap.get(iterator.next());
							if (mapMovientosCierre.get(object.getIdFormaPago()) == null) { // si no existe, lo agregamos
								MovimientoMP movimientoMP = new MovimientoMP();
								movimientoMP.setFormaPago(object);
								movimientoMP.setMonto(new Double(0));
								tablaDeFormaDePago.add(movimientoMP);
							}
						}

						// //// Se hablo en reunion que lo que sigue no es aplicable ya que no sepuede realizar NINGUN movimiento u operacion sobre la
						// caja estando cerrada
						/**
						 * // ahora puede que si bien la caja cerro con cierto monto, se realizaron //movimientos sobre esa caja estando cerrada, por
						 * ello para // determinar la cantidad exacta que hay en la caja hacemos lo siguiente: ///1) buscamos todos los movimientos
						 * que se hicieron en esa caja estando cerrada... Filtro filtro2 = new Filtro();
						 * filtro2.agregarCampoOperValor("caja.idCaja",Filtro.IGUAL,apertura.getCaja().getIdCaja());
						 * filtro2.agregarCampoOperValor("idMovimiento",Filtro.MAYOR,movimiento.getIdMovimiento());
						 * filtro2.agregarCampoComparacionNulo("cajaApertura.idCajaApertura",Filtro.NULL); List movimientosPosterioresCierre=
						 * fondosService.getMovimientoService().getMovimientos(filtro2); Map importeMovimientosPosteriores=
						 * movimiento.movimientosPosterioresACierreCaja(movimientosPosterioresCierre);
						 * 
						 * ///2) sacamos la diferencia entre lo q habia en caja al momento de cerrarla y de lo que entro y/o salio despues de cerrarla
						 * para cada una de las formas de pago /// que es en definitiva la cantidad real q hay en la caja.
						 * if(importeMovimientosPosteriores.isEmpty())//si no hubo movimientos despues del cierre se muestra como esta
						 * tablaDeFormaDePago= Convertidores.setToList(movimiento.getMovimientosMP()); else { Iterator it=
						 * movimiento.getMovimientosMP().iterator(); while (it.hasNext()) { MovimientoMP element = (MovimientoMP) it.next();
						 * if(importeMovimientosPosteriores.containsKey(element.getFormaPago().getIdFormaPago())){ // si seteo directamente el objeto
						 * leido por hibernate, despues me hace un update si cambio alguna propiedad, //como no queremos esto(ya que es inf solo para
						 * mostrar y los movimientos deben permanecer inalterables) creamos una var auxiliar MovimientoMP aux= new
						 * MovimientoMP(element); aux.setMonto(new
						 * Double(element.getMonto().doubleValue()+((Double)importeMovimientosPosteriores.get(element
						 * .getFormaPago().getIdFormaPago())).doubleValue())); tablaDeFormaDePago.add(aux);
						 * difereciaTotalMovPosteriores+=((Double)importeMovimientosPosteriores
						 * .get(element.getFormaPago().getIdFormaPago())).doubleValue(); } else tablaDeFormaDePago.add(element); }
						 * //cajaApertura.setSaldoInicial(new Double(apertura.getSaldoFinal().doubleValue()+difereciaTotalMovPosteriores));
						 * saldoInicial=apertura.getSaldoFinal().doubleValue()+difereciaTotalMovPosteriores; }
						 */
					}

				} catch (MovimientoException e) {
					e.printStackTrace();
				}
			}
			else {// la caja no fue abierta nunca ==> seteamos el valor inicial en 0
					// PREGUNTAR SI SE SETEA EN CERO O EN ALGUN VALOR INICIAL EN ESPECIAL!!!!!!!!
				saldoInicial = 0.0;
				cajaApertura.setSaldoInicial(new Double(0.0));
				Set set = tablaDeFormaDePagoMap.entrySet();
				Iterator iter = set.iterator();
				while (iter.hasNext()) {
					Map.Entry entry = (Map.Entry) iter.next();
					MovimientoMP movimientoMP = new MovimientoMP();
					movimientoMP.setFormaPago(((FormaPago) entry.getValue()));
					movimientoMP.setMonto(new Double(0));
					tablaDeFormaDePago.add(movimientoMP);
				}
				/*
				 * Iterator mpIterator= mediosPagosCajaList.iterator(); while (mpIterator.hasNext()) { CajaMP object = (CajaMP) mpIterator.next();
				 * MovimientoMP movimientoMP= new MovimientoMP(); movimientoMP.setFormaPago(object.getFormaPago()); movimientoMP.setMonto(new
				 * Double(0)); tablaDeFormaDePago.add(movimientoMP); }
				 */
			}
			// seteamos los cajeros disponbles
			try {
				Util.limpiarLista(operadorList);
				String cajerosAsignados = fondosService.getCajaAperturaService().cajerosAsignadosUltimaAperturaCajas();
				Filtro filtro = new Filtro("funcion", Filtro.LIKE, "CAJERO");
				if (cajerosAsignados.compareTo("") != 0)
					filtro.agregarCampoOperValor("operador.codigo", Filtro.NOTIN, cajerosAsignados);
				operadorList = transaccionesService.getColaboradorService().getColaborador(filtro);
				cargarCajeros();
				// seteamos el operador por defecto
				Caja cajaux = (Caja) Util.buscarElemento(cajaList, new Caja(idCajaSeleccionada));
				if (cajaux.getOperadorDefault() != null) {
					Filtro filtro2 = new Filtro();
					filtro2.agregarCampoOperValor("funcion", Filtro.LIKE, "CAJERO");
					filtro.agregarCampoOperValor("operador.codigo", Filtro.IGUAL, cajaux.getOperadorDefault().getCodigo());
					List colaboradores = transaccionesService.getColaboradorService().getColaborador(filtro);
					if (!colaboradores.isEmpty()) {
						idOperadorSeleccionado = ((Colaborador) colaboradores.get(0)).getIdColaborador();
					} else {
						error.agregar("El colaborador que fue seleccionado originalmente ya no cumple funciones de Cajero");
						idOperadorSeleccionado = new Long(0);
					}
				}

			} catch (ColaboradorException e) {
				e.printStackTrace();
			} catch (CajaAperturaException e) {
				e.printStackTrace();
			}
		} else
			mostrarPanel = false;
	}


	public String irAListarAperturaCaja() {
		borrar();
		tituloCorto = "Listado de Cajas abiertas";
		cargarItems();
		Session.redirect("/tarjetafiel/fondos/listarAperturaCaja.jsf");
		return "";
	}


	public String listarAperturaCaja() {
		alta = false;
		Util.limpiarLista(aperturaCajaList);
		Util.limpiarLista(cajaList);
		try {
			// TODO: Agregar el tema del filtrado

			// String idCajasAbiertas= fondosService.getCajaAperturaService().ultimaAperturaCajas();

			// setearValoresSegunCierresAnteriores(idCajasAbiertas);
			setearValoresSegunAperturasAnteriores(null);

			if (cajaList.isEmpty()) {
				mostrarCajas = false;
			}

			/*
			 * Filtro filtro = new Filtro(); filtro.agregarCampoOperValor("idCaja", Filtro.IN,idCajasAbiertas); //
			 * filtro.agregarCampoOperValor("hibilitada", Filtro.IGUAL, "S"); if(idCaja != null && !idCaja.equals(""))
			 * filtro.agregarCampoOperValor("idCaja", Filtro.IGUAL, new Long(idCaja)); cajaList = fondosService.getCajaService().getCajas(filtro); /*
			 * Iterator iter = cajaList.iterator(); while (iter.hasNext()) { CajaApertura element = (CajaApertura)iter.next();
			 * if(element.getCaja()!=null){ element.getCaja().getIdCaja(); element.getCaja().getDescripcion();
			 * 
			 * 
			 * }
			 */

			/*
			 * if(element.getImpresora()!=null) element.getImpresora().getLabel(); if(element.getOperadorDefault()!=null)
			 * element.getOperadorDefault().getLabel(); }
			 */

		} catch (Exception e) {
			e.printStackTrace();
		}
		Session.redirect("/tarjetafiel/fondos/listarAperturaCaja.jsf");
		return null;
	}


	public String mostrarDetAperturaCaja() {
		FacesContext context = FacesContext.getCurrentInstance();
		Map map = context.getExternalContext().getRequestParameterMap();
		String idCajaSeleccionada = (String) map.get("idCaja");
		mostrarPanel = true;
		Util.limpiarLista(tablaDeFormaDePago);
		System.out.println(idCajaSeleccionada);
		double difereciaTotalMovPosteriores = 0;
		List movimientos = new ArrayList();
		// cajaApertura.setSaldoInicial((Double)valoresCierresAnterioresMap.get(idCajaSeleccionada.toString()));
		// cajaApertura.setSaldoInicial(((CajaApertura)valoresCierresAnterioresMap.get(new Long(1))).getSaldoFinal());
		CajaApertura apertura = (CajaApertura) valoresCierresAnterioresMap.get(idCajaSeleccionada);
		if (apertura.getIdCajaApertura() != null) {
			try {
				Filtro filtro = new Filtro();
				filtro.agregarCampoOperValor("caja.idCaja", Filtro.IGUAL, idCajaSeleccionada);
				filtro.agregarCampoOperValor("concepto.descripcion", Filtro.LIKE, "Cierre");
				filtro.agregarCampoOperValor("concepto.tipoConcepto.descripcion", Filtro.LIKE, "Fondos");
				filtro.agregarCampoOperValor("cajaApertura.idCajaApertura", Filtro.IGUAL, apertura.getIdCajaApertura());
				movimientos = fondosService.getMovimientoService().getMovimientos(filtro);
				if (movimientos.size() > 0) { // si no entra aca es porq la caja no tuvo movientos(no ha sido abierta nunca)
					Movimiento movimiento = (Movimiento) movimientos.get(0);// segun el filtro aplicado deberia traer un unico movimiento de cierre
					// /el codigo abajo (que muestra info incluyendo movimientos estando la caja cerrada)
					// fue comentado porq se hablo en reunion q no hay q contamplar movimientos en la caja estando cerrada (no se pueden hacer mov en
					// una caja cerrada)

					/**
					 * // ahora puede que si bien la caja cerro con cierto monto, se realizaron //movimientos sobre esa caja estando cerrada, por ello
					 * para // determinar la cantidad exacta que hay en la caja hacemos lo siguiente: ///1) buscamos todos los movimientos que se
					 * hicieron en esa caja estando cerrada... Filtro filtro2 = new Filtro();
					 * filtro2.agregarCampoOperValor("caja.idCaja",Filtro.IGUAL,apertura.getCaja().getIdCaja());
					 * filtro2.agregarCampoOperValor("idMovimiento",Filtro.MAYOR,movimiento.getIdMovimiento());
					 * filtro2.agregarCampoComparacionNulo("cajaApertura.idCajaApertura",Filtro.NULL); List movimientosPosterioresCierre=
					 * fondosService.getMovimientoService().getMovimientos(filtro2); Map importeMovimientosPosteriores=
					 * movimiento.movimientosPosterioresACierreCaja(movimientosPosterioresCierre);
					 * 
					 * ///2) sacamos la diferencia entre lo q habia en caja al momento de cerrarla y de lo que entro y/o salio despues de cerrarla
					 * para cada una de las formas de pago /// que es en definitiva la cantidad real q hay en la caja.
					 * if(importeMovimientosPosteriores.isEmpty())//si no hubo movimientos despues del cierre se muestra como esta tablaDeFormaDePago=
					 * Convertidores.setToList(movimiento.getMovimientosMP()); else { Iterator it= movimiento.getMovimientosMP().iterator(); while
					 * (it.hasNext()) { MovimientoMP element = (MovimientoMP) it.next();
					 * if(importeMovimientosPosteriores.containsKey(element.getFormaPago().getIdFormaPago())){ // si seteo directamente el objeto
					 * leido por hibernate, despues me hace un update si cambio alguna propiedad, //como no queremos esto(ya que es inf solo para
					 * mostrar y los movimientos deben permanecer inalterables) creamos una var auxiliar MovimientoMP aux= new MovimientoMP(element);
					 * aux.setMonto(new
					 * Double(element.getMonto().doubleValue()+((Double)importeMovimientosPosteriores.get(element.getFormaPago().getIdFormaPago
					 * ())).doubleValue())); tablaDeFormaDePago.add(aux);
					 * difereciaTotalMovPosteriores+=((Double)importeMovimientosPosteriores.get(element
					 * .getFormaPago().getIdFormaPago())).doubleValue(); } else tablaDeFormaDePago.add(element); } //cajaApertura.setSaldoInicial(new
					 * Double(apertura.getSaldoFinal().doubleValue()+difereciaTotalMovPosteriores));
					 * saldoInicial=apertura.getSaldoFinal().doubleValue()+difereciaTotalMovPosteriores; }
					 */

					tablaDeFormaDePago = Convertidores.setToList(movimiento.getMovimientosMP());
					cajaApertura.setSaldoInicial(new Double(apertura.getSaldoFinal().doubleValue() + difereciaTotalMovPosteriores));

				} else
				{ // cajaApertura.setSaldoInicial(apertura.getSaldoFinal());
					cajaApertura.setSaldoInicial(new Double(0.0));
				}
			} catch (MovimientoException e) {
				e.printStackTrace();
			}
		}
		else {// la caja no fue abierta nunca ==> seteamos el valor inicial en 0
				// PREGUNTAR SI SE SETEA EN CERO O EN ALGUN VALOR INICIAL EN ESPECIAL!!!!!!!!
			cajaApertura.setSaldoInicial(new Double(0));

		}
		// seteamos los cajeros disponbles
		try {
			Util.limpiarLista(operadorList);
			String cajerosAsignados = fondosService.getCajaAperturaService().cajerosAsignadosUltimaAperturaCajas();
			Filtro filtro = new Filtro("funcion", Filtro.LIKE, "CAJERO");
			filtro.agregarCampoOperValor("operador.codigo", Filtro.NOTIN, cajerosAsignados);
			operadorList = transaccionesService.getColaboradorService().getColaborador(filtro);
			cargarCajeros();

		} catch (ColaboradorException e) {
			e.printStackTrace();
		} catch (CajaAperturaException e) {
			e.printStackTrace();
		}

		mostrarDetalleApertura = true;
		return null;

	}


	public boolean getAlta() {
		return alta;
	}


	public void setAlta(boolean alta) {
		this.alta = alta;
	}


	public CajaApertura getCajaApertura() {
		return cajaApertura;
	}


	public void setCajaApertura(CajaApertura cajaApertura) {
		this.cajaApertura = cajaApertura;
	}


	public List getCajaItems() {
		return cajaItems;
	}


	public void setCajaItems(List cajaItems) {
		this.cajaItems = cajaItems;
	}


	public Long getIdCajaSeleccionada() {
		return idCajaSeleccionada;
	}


	public void setIdCajaSeleccionada(Long idCajaSeleccionada) {
		this.idCajaSeleccionada = idCajaSeleccionada;
	}


	public List getCajaList() {
		return cajaList;
	}


	public void setCajaList(List object) {
		this.cajaList = object;
	}


	public String getFocoHidden() {
		return focoHidden;
	}


	public void setFocoHidden(String focoHidden) {
		this.focoHidden = focoHidden;
	}


	/************************************************************************
	 * ACCIONES DEL BEAN APERTURA DE CAJA
	 ************************************************************************/

	public String inicializar() {
		borrarBaseBean();
		borrar();
		if (Session.getBean("ScrollBean") != null)
		{
			ScrollBean bean = (ScrollBean) Session.getBean("ScrollBean");
			bean.setHiddenScrollY(new Integer(0));
		}
		try {
			setearValoresSegunCierresAnteriores(null);
			// puede que una caja nunca haya sido abierta tambien debemos recuperar esa cajas
			/*
			 * String cajasAperturasAbiertas = fondosService.getCajaAperturaService().ultimaAperturaCajas(); Filtro f = new Filtro();
			 * f.agregarCampoOperValor("idCaja",Filtro.NOTIN,idCajas); f.agregarCampoOperValor("idCaja",Filtro.NOTIN,cajasAperturasAbiertas);
			 * 
			 * List cajasSinAperturas= fondosService.getCajaService().getCajas(f); operadorList =
			 * transaccionesService.getColaboradorService().getColaborador(new Filtro("funcion",Filtro.LIKE,"CAJERO")); Iterator iter=
			 * cajasSinAperturas.iterator(); while (iter.hasNext()) { Caja element = (Caja) iter.next(); cajaList.add(element);
			 * valoresCierresAnterioresMap.put(element.getIdCaja(),new CajaApertura()); }
			 */
			String cajasVirgenes = "";
			cajasVirgenes = fondosService.getCajaAperturaService().getCajasSinAbrir();
			if (cajasVirgenes.compareTo("") != 0) {
				List restoCajas = fondosService.getCajaService().getCajas(new Filtro("idCaja", Filtro.IN, cajasVirgenes));
				Iterator iter = restoCajas.iterator();
				while (iter.hasNext()) {
					Caja element = (Caja) iter.next();
					cajaList.add(element);
					valoresCierresAnterioresMap.put(element.getIdCaja(), new CajaApertura());
				}
			}

			if (cajaList.isEmpty()) {
				mostrarCajas = false;
			}

		} catch (CajaAperturaException e1) {
			e1.printStackTrace();
		} catch (CajaException e) {
			e.printStackTrace();
		}

		/*
		 * try { Filtro f= new Filtro(); List cajas= fondosService.getCajaService().getCajas(new Filtro()); Util.limpiarLista(cajaList); Iterator
		 * cajasIterator= cajas.iterator(); while (cajasIterator.hasNext()) { Caja element = (Caja) cajasIterator.next(); List
		 * listaAperturas=Convertidores.setToList(element.getCajaAperturasSet()); ///si la caja no se abrio nunca la pongo en la lista de disponibles
		 * if(listaAperturas == null || listaAperturas.size()==0) { cajaList.add(element); valoresCierresAnterioresMap.put(element.getIdCaja(), new
		 * CajaApertura()); } else{ //si no busco la utltima apertura para determinar el estado actual de la caja, si no esta abierta agregarla al
		 * listado ////Collections.reverse(listaAperturas);esto no va porq ya viene ordenada por hibernate por idcajaApertura
		 * if(((CajaApertura)listaAperturas.get(0)).getFechaCierre()!=null) {cajaList.add(element);
		 * valoresCierresAnterioresMap.put(element.getIdCaja(),((CajaApertura)listaAperturas.get(0))); } }
		 * 
		 * } operadorList = transaccionesService.getColaboradorService().getColaborador(new Filtro("funcion",Filtro.LIKE,"CAJERO"));
		 * 
		 * } catch (CajaException e) { e.printStackTrace(); } catch (ColaboradorException e) { // TODO Auto-generated catch block e.printStackTrace();
		 * }
		 */

		cargarItems();
		tablaDeFormaDePago = new ArrayList();
		return "aperturaCaja";
	}


	private void cargarItems() {
		if (cajaItems.size() != cajaList.size()) {
			cajaItems = new ArrayList();
			cajaItems.add(new SelectItem(new Long(0), "Seleccionar Caja"));
			cajaItems.addAll(Util.cargarSelectItem(cajaList));
		}
	}


	private void cargarCajeros() {
		if (operadorItems.size() != operadorList.size()) {
			operadorItems = new ArrayList();
			operadorItems.add(new SelectItem(new Long(0), "Seleccionar Operador"));
			operadorItems.addAll(Util.cargarSelectItem(operadorList));
		}
	}


	public String grabar() throws CajaAperturaException {

		CajaAperturaService cajaAperturaService = fondosService.getCajaAperturaService();
		Operador operador = Session.getOperador();
		/*
		 * Date hoy= new Date(); SimpleDateFormat dateFormat= new SimpleDateFormat("dd/MM/yyyy HH:mm"); cajaApertura.setFechaApertura(new Date());
		 */
		// cajaApertura.setCaja((Caja)Util.buscarElemento(cajaList, new Caja(idCajaSeleccionada)));
		CajaApertura cajaAper = new CajaApertura();
		cajaAper.setCaja((Caja) Util.buscarElemento(cajaList, new Caja(idCajaSeleccionada)));
		cajaAper.setEstado(new Character('A'));
		cajaAper.setFechaApertura(new Date());
		cajaAper.setFechaCierre(null);
		// cajaAper.setOperador(operador);
		cajaAper.setSignoApertura(new Character('1'));
		cajaAper.setSaldoInicial(cajaApertura.getSaldoInicial());
		cajaAper.setSignoFinal(null);
		Colaborador cajero = null;
		try {
			cajero = (Colaborador) Util.buscarElemento(operadorList, new Colaborador(idOperadorSeleccionado));
		} catch (ColaboradorException e1) {

			e1.printStackTrace();
		}
		if (cajero != null)
			cajaAper.setOperador(cajero.getOperador());
		// cajaApertura.setOperador(cajero.getOperador());
		if (validar()) {
			try {
				cajaAperturaService.grabarCajaApertura(cajaAper);
				Movimiento movimiento = new Movimiento();
				movimiento.setCaja(cajaAper.getCaja());
				movimiento.setCajaApertura(cajaAper);
				List conceptoList;
				// /402 es el nro de concepto asignado para la apertura de caja
				conceptoList = generalService.getConceptoGenService().getConcepto(new Filtro("codigoConcepto", Filtro.IGUAL, "402"));
				ConceptoGen conceptoGen = (ConceptoGen) conceptoList.get(0);
				movimiento.setConcepto(conceptoGen);
				movimiento.setEstado(new Character('T'));
				movimiento.setFecha(new Date());
				movimiento.setImporte(cajaAper.getSaldoInicial());
				movimiento.setOperador(operador);
				movimiento.setSigno(new Integer(1));
				/*
				 * if(tablaDeFormaDePago!=null && tablaDeFormaDePago.size()>0){ Set movimientosMP= new HashSet(); Iterator fmasPagoIterator=
				 * tablaDeFormaDePago.iterator(); while (fmasPagoIterator.hasNext()) { MovimientoMP object = (MovimientoMP) fmasPagoIterator.next();
				 * movimientosMP.add(object); } movimiento.setMovimientosMP(movimientosMP); }
				 */
				fondosService.getMovimientoService().grabarMovimiento(movimiento);
				if (tablaDeFormaDePago != null) {
					Iterator fmasPagoIterator = tablaDeFormaDePago.iterator();
					while (fmasPagoIterator.hasNext()) {

						MovimientoMP movimientoMP = (MovimientoMP) fmasPagoIterator.next();
						if (mapMovientosCierre != null) {
							if (mapMovientosCierre.get(movimientoMP.getFormaPago().getIdFormaPago()) != null) {
								movimientoMP.setMovimiento(movimiento);
								fondosService.getMovimientoMPService().grabarMovimientoMP(movimientoMP);
							}
						}
					}

				}/*
				 * else{ //grabamos un movimiento mp en 0 para las cajas que nunca fueron abiertas y le // seteamos todos los medios de pago que
				 * acepta la caja en 0 Caja caja = (Caja)Util.buscarElemento(cajaList, new Caja(idCajaSeleccionada)); if(caja.getCajaMPSet()!=null){
				 * Iterator fmasPagoCajaIterator= caja.getCajaMPSet().iterator(); while (fmasPagoCajaIterator.hasNext()) { MovimientoMP movimientoMP=
				 * new MovimientoMP(); movimientoMP.setFormaPago(((CajaMP)fmasPagoCajaIterator.next()).getFormaPago()); movimientoMP.setMonto(new
				 * Double(0)); movimientoMP.setMovimiento(movimiento); fondosService.getMovimientoMPService().grabarMovimientoMP(movimientoMP); } } }
				 */
				popup.setPopup(popup.ICONO_OK, "La Caja ha sido abierta exitosamente.");
			} catch (ConceptoGenException e) {
				popup.setPopup(popup.ICONO_FALLA, "Falla en la apertura de la caja.");
				e.printStackTrace();
			} catch (MovimientoException e) {
				popup.setPopup(popup.ICONO_FALLA, "Falla en la apertura de la caja.");
				e.printStackTrace();
			} catch (MovimientoMPException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		return "";
	}


	public void borrar() {
		error.borrar();
		alta = true;
		tituloLargo = "TARJETA FIEL";
		tituloCorto = "Apertura de Caja";
		popup.borrar();
		idCaja = "";
		cajaApertura = new CajaApertura();
		idCajaSeleccionada = new Long(0);
		idOperadorSeleccionado = new Long(0);
		HtmlSelectOneMenu cajaHtml = new HtmlSelectOneMenu();
		mostrarPanel = false;
		tablaDeFormaDePago = new ArrayList();
		// valoresCierresAnterioresMap = new HashMap();
		Util.limpiarLista(cajaItems);
		Util.limpiarLista(cajaList);
		Util.limpiarLista(aperturaCajaList);
		mostrarCajas = true;
		mostrarDetalleApertura = false;
	}


	public String cancelar() {
		borrar();
		return "inicio";
	}


	public boolean validar() {
		error.borrar();
		/*
		 * if(((CajaApertura)valoresCierresAnterioresMap.get(new Long(1))).getEstado()== new Character('C')){ error.agregar(Error.) }
		 */

		if (idCajaSeleccionada.longValue() == 0) {
			error.agregar(Error.FON_APERTURACAJA_CAJA_REQUERIDA);
		}

		// ahora nos aseguramos que la caja este realmente cerrada para evitar problemas de concurrencia,
		// puede q otro usuario usuario la haya abierto antes en otra sesion.
		try {
			String cajasAperturasCerradas = fondosService.getCajaAperturaService().ultimoCierreCajas(alta, null);
			boolean valido = false;
			StringTokenizer tokens = new StringTokenizer(cajasAperturasCerradas, ",");
			while (tokens.hasMoreTokens()) {
				if (tokens.nextToken().compareTo(idCajaSeleccionada.toString()) == 0) {
					valido = true;
					break;
				}
			}
			if (!valido) {
				popup.setPopup(popup.ICONO_FALLA, "La caja ya ha sido abierta por otro usuario.");
			}

		} catch (CajaAperturaException e) {
			e.printStackTrace();
		}

		// por el mismo motivo anterior chequeamos el cajero seleccionado ya que un cajero
		// debe tener asignada una y solo una caja en un momento determninado

		try {
			String cajerosAsignados = fondosService.getCajaAperturaService().cajerosAsignadosUltimaAperturaCajas();
			boolean valido = true;
			StringTokenizer tokens = new StringTokenizer(cajerosAsignados, ",");
			while (tokens.hasMoreTokens()) {
				if (tokens.nextToken().compareTo(idOperadorSeleccionado.toString()) == 0) {
					valido = false;
					break;
				}
			}
			if (!valido)
				popup.setPopup(popup.ICONO_FALLA, "El cajero seleccionado ya fue asignado a otra caja");

		} catch (CajaAperturaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return (error.cantidad() == 0) ? true : false;
	}


	public String irANuevaApertura() {
		inicializar();
		popup.borrar();
		return null;
	}


	public List getTablaDeFormaDePago() {
		return tablaDeFormaDePago;
	}


	public void setTablaDeFormaDePago(List tablaDeFormaDePago) {
		this.tablaDeFormaDePago = tablaDeFormaDePago;
	}


	public String getIdCaja() {
		return idCaja;
	}


	public void setIdCaja(String idCaja) {
		this.idCaja = idCaja;
	}


	public List getOperadorItems() {
		return operadorItems;
	}


	public void setOperadorItems(List operadorItems) {
		this.operadorItems = operadorItems;
	}


	public void setIdOperadorSeleccionado(Long idOperadorSeleccionado) {
		this.idOperadorSeleccionado = idOperadorSeleccionado;
	}


	public Long getIdOperadorSeleccionado() {
		return idOperadorSeleccionado;
	}

	public class WrapperCajaApertura {
		private int indice;
		private CajaApertura cajaApertura;


		// private boolean soyNuevo;

		/**
		 * 
		 * */
		public WrapperCajaApertura(CajaApertura cajaApertura) {
			this.cajaApertura = cajaApertura;
			//
			this.indice = ++posicionFormaPago;
		}


		public int getIndice() {
			return indice;
		}


		public void setIndice(int indice) {
			this.indice = indice;
		}


		public String mostrarDetAperturaCaja() {
			FacesContext context = FacesContext.getCurrentInstance();
			Map map = context.getExternalContext().getRequestParameterMap();
			String idCajaSeleccionada = (String) map.get("idCaja");

			mostrarPanel = true;
			Util.limpiarLista(tablaDeFormaDePago);
			System.out.println(idCajaSeleccionada);
			double difereciaTotalMovPosteriores = 0;

			List movimientos = new ArrayList();
			// cajaApertura.setSaldoInicial((Double)valoresCierresAnterioresMap.get(idCajaSeleccionada.toString()));
			// cajaApertura.setSaldoInicial(((CajaApertura)valoresCierresAnterioresMap.get(new Long(1))).getSaldoFinal());
			// CajaApertura apertura= (CajaApertura)valoresCierresAnterioresMap.get(idCajaSeleccionada);
			CajaApertura apertura = cajaApertura;
			if (apertura.getIdCajaApertura() != null) {
				Filtro filtro = new Filtro();
				filtro.agregarCampoOperValor("caja.idCaja", Filtro.IGUAL, idCajaSeleccionada);
				filtro.agregarCampoOperValor("concepto.descripcion", Filtro.LIKE, "Cierre");
				filtro.agregarCampoOperValor("concepto.tipoConcepto.descripcion", Filtro.LIKE, "Fondos");
				filtro.agregarCampoOperValor("cajaApertura.idCajaApertura", Filtro.IGUAL, apertura.getIdCajaApertura());
				try {
					movimientos = fondosService.getMovimientoService().getMovimientos(filtro);
					if (movimientos.size() > 0) { // si no entra aca es porq la caja no tuvo movientos(no ha sido abierta nunca)
						Movimiento movimiento = (Movimiento) movimientos.get(0);// segun el filtro aplicado deberia traer un unico movimiento de
																				// cierre
						// ahora puede que si bien la caja cerro con cierto monto, se realizaron
						// movimientos sobre esa caja estando cerrada, por ello para
						// determinar la cantidad exacta que hay en la caja hacemos lo siguiente:
						// /1) buscamos todos los movimientos que se hicieron en esa caja estando cerrada...
						Filtro filtro2 = new Filtro();
						filtro2.agregarCampoOperValor("caja.idCaja", Filtro.IGUAL, apertura.getCaja().getIdCaja());
						filtro2.agregarCampoOperValor("idMovimiento", Filtro.MAYOR, movimiento.getIdMovimiento());
						filtro2.agregarCampoComparacionNulo("cajaApertura.idCajaApertura", Filtro.NULL);
						List movimientosPosterioresCierre = fondosService.getMovimientoService().getMovimientos(filtro2);
						Map importeMovimientosPosteriores = movimiento.movimientosPosterioresACierreCaja(movimientosPosterioresCierre);

						// /2) sacamos la diferencia entre lo q habia en caja al momento de cerrarla y de lo que entro y/o salio despues de cerrarla
						// para cada una de las formas de pago
						// / que es en definitiva la cantidad real q hay en la caja.
						if (importeMovimientosPosteriores.isEmpty())// si no hubo movimientos despues del cierre se muestra como esta
							tablaDeFormaDePago = Convertidores.setToList(movimiento.getMovimientosMP());
						else {
							Iterator it = movimiento.getMovimientosMP().iterator();
							while (it.hasNext()) {
								MovimientoMP element = (MovimientoMP) it.next();
								if (importeMovimientosPosteriores.containsKey(element.getFormaPago().getIdFormaPago())) {
									// si seteo directamente el objeto leido por hibernate, despues me hace un update si cambio alguna propiedad,
									// como no queremos esto(ya que es inf solo para mostrar y los movimientos deben permanecer inalterables) creamos
									// una var auxiliar
									MovimientoMP aux = new MovimientoMP(element);
									aux.setMonto(new Double(element.getMonto().doubleValue()
											+ ((Double) importeMovimientosPosteriores.get(element.getFormaPago().getIdFormaPago())).doubleValue()));
									tablaDeFormaDePago.add(aux);
									difereciaTotalMovPosteriores += ((Double) importeMovimientosPosteriores.get(element.getFormaPago()
											.getIdFormaPago())).doubleValue();
								}
								else
									tablaDeFormaDePago.add(element);
							}
							// cajaApertura.setSaldoInicial(new Double(apertura.getSaldoFinal().doubleValue()+difereciaTotalMovPosteriores));
							saldoInicial = apertura.getSaldoFinal().doubleValue() + difereciaTotalMovPosteriores;
						}
					} else
					{ // cajaApertura.setSaldoInicial(apertura.getSaldoFinal());
						if (apertura.getSaldoFinal() != null)
							saldoInicial = apertura.getSaldoFinal().doubleValue();
					}
					cajaApertura.setSaldoInicial(apertura.getSaldoFinal());// esto es lo que realmente se graba
				} catch (MovimientoException e) {
					e.printStackTrace();
				}
			}
			else {// la caja no fue abierta nunca ==> seteamos el valor inicial en 0
					// PREGUNTAR SI SE SETEA EN CERO O EN ALGUN VALOR INICIAL EN ESPECIAL!!!!!!!!
				saldoInicial = 0.0;
				cajaApertura.setSaldoInicial(new Double(0.0));

			}
			// seteamos los cajeros disponbles
			try {
				Util.limpiarLista(operadorList);
				String cajerosAsignados = fondosService.getCajaAperturaService().cajerosAsignadosUltimaAperturaCajas();
				Filtro filtro = new Filtro("funcion", Filtro.LIKE, "CAJERO");
				filtro.agregarCampoOperValor("operador.codigo", Filtro.NOTIN, cajerosAsignados);
				operadorList = transaccionesService.getColaboradorService().getColaborador(filtro);
				cargarCajeros();

			} catch (ColaboradorException e) {
				e.printStackTrace();
			} catch (CajaAperturaException e) {
				e.printStackTrace();
			}

			mostrarDetalleApertura = true;
			return null;

		}


		public CajaApertura getCajaApertura() {
			return cajaApertura;
		}


		public void setCajaApertura(CajaApertura cajaApertura) {
			this.cajaApertura = cajaApertura;
		}

	}


	public HtmlSelectOneMenu getCajaHtml() {
		return cajaHtml;
	}


	public void setCajaHtml(HtmlSelectOneMenu cajaHtml) {
		this.cajaHtml = cajaHtml;
	}


	public boolean isMostrarPanel() {
		return mostrarPanel;
	}


	public void setMostrarPanel(boolean mostrarPanel) {
		this.mostrarPanel = mostrarPanel;
	}


	public double getSaldoInicial() {
		return saldoInicial;
	}


	public void setSaldoInicial(double saldoInicial) {
		this.saldoInicial = saldoInicial;
	}


	public boolean isMostrarCajas() {
		return mostrarCajas;
	}


	public void setMostrarCajas(boolean mostrarCajas) {
		this.mostrarCajas = mostrarCajas;
	}


	public List getAperturaCajaList() {
		return aperturaCajaList;
	}


	public void setAperturaCajaList(List aperturaCajaList) {
		this.aperturaCajaList = aperturaCajaList;
	}


	public Long getIdAperturaCajaHidden() {
		return idAperturaCajaHidden;
	}


	public void setIdAperturaCajaHidden(Long idAperturaCajaHidden) {
		this.idAperturaCajaHidden = idAperturaCajaHidden;
	}


	public boolean isMostrarDetalleApertura() {
		return mostrarDetalleApertura;
	}


	public void setMostrarDetalleApertura(boolean mostrarDetalleApertura) {
		this.mostrarDetalleApertura = mostrarDetalleApertura;
	}

}
