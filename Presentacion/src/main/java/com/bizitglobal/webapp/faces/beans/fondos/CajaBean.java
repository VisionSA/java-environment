package com.bizitglobal.webapp.faces.beans.fondos;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import org.apache.log4j.Logger;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.commons.util.Convertidores;
import com.bizitglobal.tarjetafiel.contabilidad.negocio.PlanCuentaDos;
import com.bizitglobal.tarjetafiel.fondos.exception.CajaDuplicateException;
import com.bizitglobal.tarjetafiel.fondos.exception.CajaException;
import com.bizitglobal.tarjetafiel.fondos.exception.CajaMPException;
import com.bizitglobal.tarjetafiel.fondos.negocio.Caja;
import com.bizitglobal.tarjetafiel.fondos.negocio.CajaMP;
import com.bizitglobal.tarjetafiel.fondos.negocio.Lugar;
import com.bizitglobal.tarjetafiel.fondos.service.CajaService;
import com.bizitglobal.tarjetafiel.general.exception.SucursalFielException;
import com.bizitglobal.tarjetafiel.general.negocio.Impresora;
import com.bizitglobal.tarjetafiel.general.negocio.SucursalFiel;
import com.bizitglobal.tarjetafiel.operador.negocio.Operador;
import com.bizitglobal.tarjetafiel.transacciones.exception.ColaboradorException;
import com.bizitglobal.tarjetafiel.transacciones.negocio.Colaborador;
import com.bizitglobal.webapp.faces.beans.BaseBean;
import com.bizitglobal.webapp.faces.beans.contabilidad.PlanCuentaBean;
import com.bizitglobal.webapp.faces.beans.proveedores.FormaDePagoBean;
import com.bizitglobal.webapp.faces.beans.util.ScrollBean;
import com.bizitglobal.webapp.faces.util.Error;
import com.bizitglobal.webapp.faces.util.Session;
import com.bizitglobal.webapp.faces.util.Util;


@SuppressWarnings({"rawtypes","unchecked"})
public class CajaBean extends BaseBean {
	private static final Logger log = Logger.getLogger(CajaBean.class);
	private Caja caja;
	private String nombreFiltro;
	private Long idCajaHidden;
	private String idCaja;
	private PlanCuentaDos planCuenta;
	private String idPlanCuentaABuscar;
	private String idPlanCuenta;
	private static int posicionFormaPago = 0;
	// definicion de un list del objeto base
	private List cajaList;
	private List unaCaja;
	// Listas para la presentacion(HtmlSelectItems).
	private List sucursalList = new ArrayList();
	private List sucursalItems = new ArrayList();
	private List impresoraList = new ArrayList();
	private List impresoraItems = new ArrayList();
	private List operadorList = new ArrayList();
	private List operadorItems = new ArrayList();
	private List tablaDeFormaDePago = new ArrayList();

	// Objetos Relacionados.
	private Long idSucursalSeleccionada;
	private Long idImpresoraSeleccionada;
	private Long idOperadorSeleccionado;

	private String focoHidden;
	private boolean habilitada;
	private boolean busquedaPorPopup;


	public CajaBean() {
		super();
		borrar();
		try {
			try {
				sucursalList = generalService.getSucursalFielService().getSucursales(new Filtro());
				impresoraList = generalService.getImpresoraService().getImpresora(new Filtro());
				// operadorList = operadorService.getOperadorService().getOperadores(new Filtro());
				operadorList = transaccionesService.getColaboradorService().getColaborador(new Filtro("funcion", Filtro.LIKE, "CAJERO"));
			} catch (SucursalFielException e1) {
				e1.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}


	public boolean getAlta() {
		return alta;
	}


	public void setAlta(boolean alta) {
		this.alta = alta;
	}


	public Caja getCaja() {
		return caja;
	}


	public void setCaja(Caja caja) {
		this.caja = caja;
	}


	public Long getIdCajaHidden() {
		return idCajaHidden;
	}


	public void setIdCajaHidden(Long idCajaHidden) {
		this.idCajaHidden = idCajaHidden;
	}


	public List getSucursalItems() {
		return sucursalItems;
	}


	public void setSucursalItems(List sucursalItems) {
		this.sucursalItems = sucursalItems;
	}


	public List getImpresoraItems() {
		return impresoraItems;
	}


	public void setImpresoraItems(List impresoraItems) {
		this.impresoraItems = impresoraItems;
	}


	public Long getIdSucursalSeleccionada() {
		return idSucursalSeleccionada;
	}


	public void setIdSucursalSeleccionada(Long idSucursalSeleccionada) {
		this.idSucursalSeleccionada = idSucursalSeleccionada;
	}


	public Long getIdImpresoraSeleccionada() {
		return idImpresoraSeleccionada;
	}


	public void setIdImpresoraSeleccionada(Long idImpresoraSeleccionada) {
		this.idImpresoraSeleccionada = idImpresoraSeleccionada;
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
	 * ACCIONES DEL BEAN DE CAJA
	 ************************************************************************/

	public String inicializar() {
		borrarBaseBean();
		borrar();
		if (Session.getBean("ScrollBean") != null)
		{
			ScrollBean bean = (ScrollBean) Session.getBean("ScrollBean");
			bean.setHiddenScrollY(new Integer(0));
		}

		cargarItems();
		tablaDeFormaDePago = new ArrayList();
		return "amCaja";
	}


	private void cargarItems() {

		if (sucursalItems.size() != sucursalList.size()) {
			sucursalItems = new ArrayList();
			sucursalItems.add(new SelectItem(new Long(0), "Seleccionar Sucursal"));
			sucursalItems.addAll(Util.cargarSelectItem(sucursalList));
		}
		if (alta) {
			if (impresoraItems.size() != impresoraList.size()) {
				impresoraItems = new ArrayList();
				impresoraItems.add(new SelectItem(new Long(0), "Seleccionar Impresora"));
				impresoraItems.addAll(Util.cargarSelectItem(impresoraList));
			}
			if (operadorItems.size() != operadorList.size()) {
				operadorItems = new ArrayList();
				operadorItems.add(new SelectItem(new Long(0), "Seleccionar Operador"));
				operadorItems.addAll(Util.cargarSelectItem(operadorList));
			}
		}
	}


	public String editarCaja() {
		String result = null;
		borrarBaseBean();
		borrar();
		alta = false;
		tituloCorto = "Modificar Caja";
		try {
			caja = fondosService.getCajaService().leerCaja(idCajaHidden);
			if (caja.getSucursal() != null)
				idSucursalSeleccionada = caja.getSucursal().getIdSucursal();
			if (caja.getImpresora() != null)
				idImpresoraSeleccionada = caja.getImpresora().getIdImpresora();
			if (caja.getOperadorDefault() != null) {
				Filtro filtro = new Filtro();
				filtro.agregarCampoOperValor("funcion", Filtro.LIKE, "CAJERO");
				filtro.agregarCampoOperValor("operador.codigo", Filtro.IGUAL, caja.getOperadorDefault().getCodigo());
				List colaboradores = transaccionesService.getColaboradorService().getColaborador(filtro);
				if (!colaboradores.isEmpty()) {
					idOperadorSeleccionado = ((Colaborador) colaboradores.get(0)).getIdColaborador();
				} else {
					error.agregar("El colaborador que fue seleccionado originalmente ya no cumple funciones de Cajero");
					idOperadorSeleccionado = new Long(0);

				}

				// Long idOperador = caja.getOperadorDefault().getCodigo()d()Impresora();

			}

			// idPlanCuentaABuscar= caja.getPlanCuenta().getIdPlanCuenta().toString();
			if (caja.getIdPlanCuenta() != null)
				idPlanCuentaABuscar = caja.getIdPlanCuenta().toString();

			Iterator iter = caja.getCajaMPSet().iterator();
			while (iter.hasNext()) {
				CajaMP element = (CajaMP) iter.next();
				tablaDeFormaDePago.add(new WrapperFormaPago(element, false));
			}

			// idOperadorSeleccionado = caja.getOperadorDefault().getCodigo();
			result = "amCaja";

		} catch (CajaException e1) {
			error.agregar("Ocurrio un error al intentar editar la caja");
			e1.printStackTrace();
			Session.redirect("/tarjetafiel/fondos/listarCaja.jsf");
		} catch (Exception e) {
			error.agregar("Ocurrio un error al intentar editar la caja");
			e.printStackTrace();
			Session.redirect("/tarjetafiel/fondos/listarCaja.jsf");
		}
		return result;
	}


	public String eliminarCaja() {
		try {
			fondosService.getCajaService().borrarCaja(idCajaHidden);
			cajaList.remove(new Caja(idCajaHidden));

		} catch (CajaException e1) {
			error.agregar("Imposible borrar la caja. Posee elemintos asociados");
			e1.printStackTrace();
		} catch (Exception e) {
			error.agregar("Ocurrio un error al intentar borrar la caja");
			e.printStackTrace();
		}
		Session.redirect("/tarjetafiel/fondos/listarCaja.jsf");
		return null;
	}


	public String grabar() {
		try {
			caja.setDescripcion(caja.getDescripcion().trim());
			// PlanCuentaDos cuenta=contabilidadService.getPlanCuentaDosService().leerPlanCuenta(new Long(idPlanCuentaABuscar));
			// caja.setPlanCuenta(cuenta);
			if (idPlanCuentaABuscar != null && idPlanCuentaABuscar.compareTo("") != 0)
				caja.setIdPlanCuenta(new Long(idPlanCuentaABuscar));
			CajaService cajaService = fondosService.getCajaService();
			Operador operador = Session.getOperador();
			caja.setOperador(operador);
			Date hoy = new Date();
			caja.setFechaModificacion(new Timestamp(hoy.getTime()));
			caja.setSucursal((SucursalFiel) Util.buscarElemento(sucursalList, new SucursalFiel(idSucursalSeleccionada)));
			caja.setImpresora((Impresora) Util.buscarElemento(impresoraList, new Impresora(idImpresoraSeleccionada)));
			Colaborador cajero = (Colaborador) Util.buscarElemento(operadorList, new Colaborador(idOperadorSeleccionado));
			if (cajero != null)
				caja.setOperadorDefault(cajero.getOperador());

			// System.out.println(cajero.getOperador());
			/*
			 * Iterator iter =tablaDeFormaDePago.iterator(); if(iter.hasNext()&& caja.getCajaMPSet()!=null && caja.getCajaMPSet().size()==0)
			 * caja.setCajaMPSet(new HashSet()); while (iter.hasNext()) { CajaMP element = ((WrapperFormaPago) iter.next()).getCajaMP();
			 * element.setCaja(caja); caja.getCajaMPSet().add(element); }
			 */

			// caja.setCajaMPSet(Convertidores.listToSet(tablaDeFormaDePago));
			// caja.setOperadorDefault(cajero.getOperador());
			if (validar()) {
				// if (caja.getCajaMPSet()==null && tablaDeFormaDePago.size()>0) caja.setCajaMPSet(new HashSet());
				Iterator iterFormaPago = tablaDeFormaDePago.iterator();
				if (alta) {
					Lugar lugar = new Lugar();
					lugar.setDescripcion(caja.getDescripcion());
					lugar.setCodigo(new Long(0));
					lugar.setTipo(new Character('F'));
					caja.setLugar(lugar);
					while (iterFormaPago.hasNext()) {
						CajaMP cajaMP = ((WrapperFormaPago) iterFormaPago.next()).getCajaMP();
						cajaMP.setCaja(caja);
						caja.getCajaMPSet().add(cajaMP);
					}
					cajaService.grabarCaja(caja);
				}
				else {

					while (iterFormaPago.hasNext()) {
						WrapperFormaPago wrap = (WrapperFormaPago) iterFormaPago.next();
						if (wrap.getCajaMP().getIdCajaMP() == null) {
							CajaMP cajaMP = wrap.getCajaMP();
							Long idCajaMp = cajaMP.getIdCajaMP();
							Iterator it = caja.getCajaMPSet().iterator();
							boolean encontrado = false;
							while (it.hasNext()) {
								encontrado = false;
								CajaMP element = (CajaMP) it.next();
								if (element.getIdCajaMP() == idCajaMp) {
									encontrado = true;
								}
							}
							if (!encontrado) {
								cajaMP.setCaja(caja);
								caja.getCajaMPSet().add(cajaMP);
							}
						}
					}
					cajaService.actualizarCaja(caja);
				}
				// idPaisSeleccionada = new Long(0);
				popup.setPopup(popup.ICONO_OK, "La Caja ha sido almacenada exitosamente.");
			}
			else {
				ScrollBean scrollBean = (ScrollBean) Session.getBean("ScrollBean");
				scrollBean.borrar();
			}
		} catch (CajaDuplicateException e1) {
			popup.setPopup(popup.ICONO_FALLA, "Fallo el alta de la Caja.");
			e1.printStackTrace();
		} catch (CajaException e2) {
			popup.setPopup(popup.ICONO_FALLA, "Fallo el alta de la Caja.");
			e2.printStackTrace();
		} catch (Exception e3) {
			popup.setPopup(popup.ICONO_FALLA, "Fallo el alta de la Caja.");
			e3.printStackTrace();
		}
		return "";
	}


	public void borrar() {
		error.borrar();
		alta = true;
		tituloLargo = "TARJETA FIEL";
		tituloCorto = "Alta de Caja";
		popup.borrar();
		planCuenta = new PlanCuentaDos();
		idCaja = "";
		/*
		 * try { unaCaja = fondosService.getCajaService().getCajas(new Filtro()); Iterator cajaIterator = unaCaja.iterator(); while
		 * (cajaIterator.hasNext()) { Caja caj = (Caja)cajaIterator.next(); caj.getSucursal(); caj.getOperadorDefault(); caj.getImpresora(); } } catch
		 * (CajaException e) { // TODO Auto-generated catch block e.printStackTrace(); }
		 */
		caja = new Caja();
		cajaList = new ArrayList();
		idImpresoraSeleccionada = new Long(0);
		idSucursalSeleccionada = new Long(0);
		idOperadorSeleccionado = new Long(0);
		habilitada = false;
		idPlanCuenta = "";
		idPlanCuentaABuscar = "";
		busquedaPorPopup = false;
		Util.limpiarLista(tablaDeFormaDePago);
	}


	public String cancelar() {
		borrar();
		return "inicio";
	}


	public boolean validar() {
		error.borrar();

		if (caja.getDescripcion() == null || (caja.getDescripcion() != null && caja.getDescripcion().equals("")))
			error.agregar(Error.FON_AMCAJAS_DESCRIPCION_REQUERIDA);

		if (idSucursalSeleccionada == null || idSucursalSeleccionada.equals(new Long(0)))
			error.agregar(Error.FON_AMCAJAS_SUCURSAL_REQUERIDA);
		if (idImpresoraSeleccionada == null || idImpresoraSeleccionada.equals(new Long(0)))
			error.agregar(Error.FON_AMCAJAS_IMPRESORA_REQUERIDA);
		if (idOperadorSeleccionado == null || idOperadorSeleccionado.equals(new Long(0)))
			error.agregar(Error.FON_AMCAJAS_OPERADOR_REQUERIDO);
		else {
			boolean repetido = false;
			for (int i = 0; i < tablaDeFormaDePago.size(); i++) {
				CajaMP mp = ((WrapperFormaPago) tablaDeFormaDePago.get(i)).getCajaMP();
				for (int j = i + 1; j < tablaDeFormaDePago.size(); j++) {
					CajaMP mp2 = ((WrapperFormaPago) tablaDeFormaDePago.get(j)).getCajaMP();
					if (mp.getFormaPago().getDescripcion().compareTo(mp2.getFormaPago().getDescripcion()) == 0) {
						repetido = true;
						break;
					}
				}
			}

			if (repetido)
				error.agregar(Error.FON_AMCAJAS_FORMA_PAGO_UNICA);

			Filtro filtro = new Filtro();
			filtro.agregarCampoOperValor("idColaborador", Filtro.IGUAL, idOperadorSeleccionado);
			try {

				List colaboradoes = transaccionesService.getColaboradorService().getColaborador(filtro);
				Long idOperador = ((Colaborador) colaboradoes.get(0)).getOperador().getCodigo();
				filtro = new Filtro();
				filtro.agregarCampoOperValor("operadorDefault.codigo", Filtro.IGUAL, idOperador);
				if (!alta) {
					filtro.agregarCampoOperValor("idCaja", Filtro.NOTIN, caja.getIdCaja());
				}
				List operadoresDefault = fondosService.getCajaService().getCajas(filtro);
				if (operadoresDefault != null && operadoresDefault.size() > 0) {
					error.agregar(Error.FON_AMCAJAS_OPERADOR_DEFAULT_UNICO);
				}
			} catch (CajaException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ColaboradorException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

		/*
		 * if(alta){ if(!unaCaja.isEmpty()){ Iterator iterator = unaCaja.iterator(); while (iterator.hasNext()) { Caja element = (Caja)
		 * iterator.next(); if (element.getDescripcion().compareTo(caja.getDescripcion())==0) {
		 * if(element.getSucursal().getIdSucursal().equals(idSucursalSeleccionada)&&!element.getIdCaja().equals(idCaja))
		 * error.agregar(Error.FON_AMCAJAS_CAJA_REQUERIDA); } } } } else { if(!unaCaja.isEmpty()){ Iterator iterator = unaCaja.iterator(); while
		 * (iterator.hasNext()) { Caja element = (Caja) iterator.next(); if
		 * (element.getDescripcion().compareTo(caja.getDescripcion())==0&&!element.getIdCaja().equals(idCajaHidden)) {
		 * if(element.getSucursal().getIdSucursal().equals(idSucursalSeleccionada)) error.agregar(Error.FON_AMCAJAS_CAJA_REQUERIDA); } } } }
		 */

		return (error.cantidad() == 0) ? true : false;
	}


	public String irANuevoCaja() {
		idSucursalSeleccionada = new Long(0);
		return inicializar();
	}


	public String irAModificarCaja() {
		alta = false;
		popup.borrar();
		tituloCorto = "Modificar Caja";

		return null;
	}


	public String irAListarCaja() {
		borrar();
		tituloCorto = "Listado de Cajas";
		cargarItems();
		Session.redirect("/tarjetafiel/fondos/listarCaja.jsf");
		return "";
	}


	public String buscarCuentaPopup() {
		log.info("Ir a buscar una cuenta!!!");
		busquedaPorPopup = true;
		// popupReport = "";
		PlanCuentaBean bean = (PlanCuentaBean) Session.getBean("PlanCuentaBean");
		bean.inicializaBusqueda(11);
		String path = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
		path += "/tarjetafiel/contabilidad/buscarPlanesDeCuenta.jsf";
		ejecutarJavaScript("popup('" + path + "',750,400), 'titlebar=no';");
		return null;
	}


	public String listarCaja() {
		cajaList = new ArrayList();
		try {
			// TODO: Agregar el tema del filtrado
			Filtro filtro = new Filtro();
			if (idCaja != null && !idCaja.equals(""))
				filtro.agregarCampoOperValor("idCaja", Filtro.IGUAL, new Long(idCaja));
			if (idSucursalSeleccionada != null && !idSucursalSeleccionada.equals(new Long(0)))
				filtro.agregarCampoOperValor("sucursal.idSucursal", Filtro.IGUAL, idSucursalSeleccionada);
			if (idImpresoraSeleccionada != null && !idImpresoraSeleccionada.equals(new Long(0)))
				filtro.agregarCampoOperValor("impresora.idImpresora", Filtro.IGUAL, idImpresoraSeleccionada);
			if (idOperadorSeleccionado != null && !idOperadorSeleccionado.equals(new Long(0)))
				filtro.agregarCampoOperValor("operador.codigo", Filtro.IGUAL, idOperadorSeleccionado);

			if (caja.getDescripcion() != null && !caja.getDescripcion().equals(""))
				filtro.agregarCampoOperValor("descripcion", Filtro.LIKE, caja.getDescripcion());

			cajaList = fondosService.getCajaService().getCajas(filtro);
			Iterator iter = cajaList.iterator();
			while (iter.hasNext())
			{
				Caja element = (Caja) iter.next();
				if (element.getSucursal() != null)
					element.getSucursal().getLabel();
				if (element.getImpresora() != null)
					element.getImpresora().getLabel();
				if (element.getOperadorDefault() != null)
					element.getOperadorDefault().getLabel();

			}
			// idProvincia = "";
			// idPaisSeleccionada = new Long(0);
			// provincia.setNombre("");
		} catch (Exception e) {
			e.printStackTrace();
		}
		Session.redirect("/tarjetafiel/fondos/listarCaja.jsf");
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


	public PlanCuentaDos getPlanCuenta() {
		return planCuenta;
	}


	public void setPlanCuenta(PlanCuentaDos planCuenta) {
		this.planCuenta = planCuenta;
	}


	public boolean isHabilitada() {
		if (caja.getHabilitada() != null)
			return Convertidores.getBoolean(caja.getHabilitada().toString());
		return false;
	}


	public void setHabilitada(boolean habilitada) {
		caja.setHabilitada(Character.valueOf(Convertidores.getSorN(habilitada).charAt(0)));
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


	public String getIdPlanCuenta() {
		return idPlanCuenta;
	}


	public void setIdPlanCuenta(String idPlanCuenta) {
		this.idPlanCuenta = idPlanCuenta;
	}


	public String getIdPlanCuentaABuscar() {
		return idPlanCuentaABuscar;
	}


	public void setIdPlanCuentaABuscar(String idPlanCuentaABuscar) {
		if (busquedaPorPopup) {
			this.idPlanCuentaABuscar = idPlanCuentaABuscar;
			if (idPlanCuenta != null) {
				this.idPlanCuentaABuscar = idPlanCuenta;
				idPlanCuenta = null;
			} else {
				this.idPlanCuentaABuscar = idPlanCuentaABuscar;
			}
		} else
			this.idPlanCuentaABuscar = idPlanCuentaABuscar;
	}


	public boolean isBusquedaPorPopup() {
		return busquedaPorPopup;
	}


	public void setBusquedaPorPopup(boolean busquedaPorPopup) {
		this.busquedaPorPopup = busquedaPorPopup;
	}


	public String eliminarFormaPago() {
		Long idFormaPago = new Long(Session.getRequestParameter("idFormapago"));
		Iterator iter = tablaDeFormaDePago.iterator();
		CajaMP formaPagoElim = null;
		while (iter.hasNext()) {
			WrapperFormaPago wrap = (WrapperFormaPago) iter.next();
			if (wrap.getIndice() == idFormaPago.intValue()) {
				// if (wrap.getCajaMP().getIdCajaMP()!=null) {
				formaPagoElim = wrap.getCajaMP();
				// }
				tablaDeFormaDePago.remove(wrap);
				break;
			}
		}
		if (formaPagoElim != null) {

			try {
				if (formaPagoElim.getIdCajaMP() != null) {
					fondosService.getCajaMPService().borrarCajaMP(formaPagoElim.getIdCajaMP());
					caja.getCajaMPSet().remove(formaPagoElim);
				}
			} catch (CajaMPException e1) {
				error.agregar("Imposible borrar el medio de pago. Posee elemintos asociados");
				e1.printStackTrace();
			} catch (Exception e) {
				error.agregar("Ocurrio un error al intentar borrar el medio de pago ");
				e.printStackTrace();
			}

		}
		return null;
	}


	public void agregarFormaDePago(CajaMP unaFormaDePago) {

		tablaDeFormaDePago.add(new WrapperFormaPago(unaFormaDePago, true));

	}


	public String irAgregarFormaDePago() {
		log.info("Ir a agrega nueva forma de pago!!!");
		FormaDePagoBean bean = (FormaDePagoBean) Session.getBean("FormaDePagoBean");
		bean.inicializar(null, 3);
		String path = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
		path += "/tarjetafiel/proveedores/popup/formaPago.jsf";
		ejecutarJavaScript("popup('" + path + "',700,400), 'titlebar=no';");
		return null;
	}

	public class WrapperFormaPago {
		private int indice;
		private CajaMP cajaMP;
		private boolean soyNuevo;


		/**
		 * 
		 * */
		public WrapperFormaPago(CajaMP cajaMP, boolean soyNuevo) {
			// if(this.cajaMP != null && cajaMP.getIdCajaMP()!=null)
			// this.cajaMP.setIdCajaMP(cajaMP.getIdCajaMP());
			this.cajaMP = new CajaMP(cajaMP.getFormaPago());
			this.cajaMP.setIdCajaMP(cajaMP.getIdCajaMP());
			this.cajaMP.setHabilitado(cajaMP.getHabilitado());
			this.cajaMP.setPlanCuentaDos(cajaMP.getPlanCuentaDos());
			this.soyNuevo = soyNuevo;
			this.indice = ++posicionFormaPago;
		}


		public int getIndice() {
			return indice;
		}


		public void setIndice(int indice) {
			this.indice = indice;
		}


		public boolean isSoyNuevo() {
			return soyNuevo;
		}


		public void setSoyNuevo(boolean soyNuevo) {
			this.soyNuevo = soyNuevo;
		}


		public CajaMP getCajaMP() {
			return cajaMP;
		}


		public void setCajaMP(CajaMP cajaMP) {
			this.cajaMP = cajaMP;
		}

	}

}
