package com.bizitglobal.webapp.faces.beans.proveedores;

import java.util.Iterator;
import java.util.List;
import javax.faces.component.html.HtmlSelectOneMenu;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;
import org.apache.log4j.Logger;
import org.apache.myfaces.renderkit.html.util.AddResource;
import org.apache.myfaces.renderkit.html.util.AddResourceFactory;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.commons.util.Convertidores;
import com.bizitglobal.tarjetafiel.contabilidad.negocio.PlanCuentaDos;
import com.bizitglobal.tarjetafiel.fondos.negocio.CajaMP;
import com.bizitglobal.tarjetafiel.general.exception.BancoException;
import com.bizitglobal.tarjetafiel.general.exception.FormaPagoException;
import com.bizitglobal.tarjetafiel.general.negocio.Banco;
import com.bizitglobal.tarjetafiel.proveedores.exception.CbuNoValidoException;
import com.bizitglobal.tarjetafiel.proveedores.negocio.CbuValido;
import com.bizitglobal.tarjetafiel.proveedores.negocio.ProveedorFormaPago;
import com.bizitglobal.tarjetafiel.transacciones.negocio.ComercioFormaPago;
import com.bizitglobal.webapp.faces.beans.BaseBean;
import com.bizitglobal.webapp.faces.beans.fondos.CajaBean;
import com.bizitglobal.webapp.faces.beans.transacciones.CodComercioBean;
import com.bizitglobal.webapp.faces.service.proveedores.ProveedoresServiceFaces;
import com.bizitglobal.webapp.faces.util.Error;
import com.bizitglobal.webapp.faces.util.Session;
import com.bizitglobal.webapp.faces.util.Util;
import com.bizitglobal.webapp.faces.util.Validador;


@SuppressWarnings({"rawtypes"})
public class FormaDePagoBean extends BaseBean {

	private static final Logger log = Logger.getLogger(FormaDePagoBean.class);
	private static int ORIGEN_CAJA = 3;
	private static final int COD_COMERCIO = 1;
	private static final int PROVEEDOR = 2;
	private ProveedoresServiceFaces service = new ProveedoresServiceFaces();
	private ProveedorFormaPago formaPago = new ProveedorFormaPago();
	private int contador = 1;

	// Aqui si el origen es otro que proveedor!!!!
	private boolean noSoyProveedor = false;
	private ComercioFormaPago comercioFormaPago = new ComercioFormaPago();
	private CajaMP cajaMP = new CajaMP();
	private int origen;

	private List listaDeFormasDePago;
	private List listaDeCuentasBancos;
	private List listaDeBancos;
	private Long bancoSeleccionado;
	private Long formaPagoSeleccionada;

	// Propiedad para la cuenta banco seleccionada.
	private Integer tipoCuentaBancoSeleccionada;

	private Integer acreditacionBancariaSeleccionada;
	private String cbu;

	// para las caja

	private String idPlanCuenta;
	private Long idCuentaFondosSeleccionada;
	private List cuentaFondosItems;

	// Propiedades que controlan la visibilidad de los componentes graficos.

	private boolean esAcreditacionBancaria = false;
	private boolean esCheque = false;
	private boolean esCaja = false;
	private Long nroCuentaFondos;
	private Long sucursalFiel;
	private boolean mostrarCuentasFondo;

	private boolean habilitada;

	HtmlSelectOneMenu fpOneMenuHtml = new HtmlSelectOneMenu();
	HtmlSelectOneMenu acreditaOneMenuHtml = new HtmlSelectOneMenu();


	// HtmlSelectOneMenu ctaFondosOneMenuHtml = new HtmlSelectOneMenu();

	// este se llama desde pantalla comercio
	public FormaDePagoBean() {
		this(new Long(1), null, null);
	}


	// este se llama desde pantalla comercio
	public FormaDePagoBean(Long formaPagoSeleccionada, Integer acreditacionBancariaSeleccionada,
			String cbu) {
		super();
		error.borrar(); // Borrar los errores que existen en el arreglo.
		// this.listaDeFormasDePago = FormaDePagoUtil.cargarListaFormasDePago(generalService.getFormaPagoDao());
		this.listaDeCuentasBancos = FormaDePagoUtil.cargarListaCuentasBancos(generalService.getTipoCuentaBancoDao());
		this.listaDeBancos = FormaDePagoUtil.cargarListaDeBancos(generalService.getBancoDao());
		this.acreditacionBancariaSeleccionada = acreditacionBancariaSeleccionada;
		this.cbu = cbu;

		// inicializar();
	}


	public HtmlSelectOneMenu getAcreditaOneMenuHtml() {
		return acreditaOneMenuHtml;
	}


	public void setAcreditaOneMenuHtml(HtmlSelectOneMenu acreditaOneMenuHtml) {
		this.acreditaOneMenuHtml = acreditaOneMenuHtml;
	}


	public HtmlSelectOneMenu getFpOneMenuHtml() {
		return fpOneMenuHtml;
	}


	public void setFpOneMenuHtml(HtmlSelectOneMenu fpOneMenuHtml) {
		this.fpOneMenuHtml = fpOneMenuHtml;
	}


	public Integer getAcreditacionBancariaSeleccionada() {
		return acreditacionBancariaSeleccionada;
	}


	public void setAcreditacionBancariaSeleccionada(
			Integer acreditacionBancariaSeleccionada) {
		this.acreditacionBancariaSeleccionada = acreditacionBancariaSeleccionada;
	}


	public Long getBancoSeleccionado() {
		return bancoSeleccionado;
	}


	public void setBancoSeleccionado(Long bancoSeleccionado) {
		this.bancoSeleccionado = bancoSeleccionado;
	}


	public String getCbu() {
		return cbu;
	}


	public void setCbu(String cbu) {
		this.cbu = cbu;
	}


	public ProveedorFormaPago getFormaPago() {
		return formaPago;
	}


	public void setFormaPago(ProveedorFormaPago formaPago) {
		this.formaPago = formaPago;
	}


	public Long getFormaPagoSeleccionada() {
		// return formaPago.getSucursalFormaPago().getFormaPago().getIdFormaPago();
		return formaPago.getFormaPago().getIdFormaPago();
	}


	public void setFormaPagoSeleccionada(Long formaPagoSeleccionada) {
		formaPago.getFormaPago().setIdFormaPago(formaPagoSeleccionada);
		// formaPago.getSucursalFormaPago().getFormaPago().setIdFormaPago(formaPagoSeleccionada);
	}


	public List getListaDeFormasDePago() {
		return listaDeFormasDePago;
	}


	public void setListaDeFormasDePago(List listaDeFormasDePago) {
		this.listaDeFormasDePago = listaDeFormasDePago;
	}


	public boolean getEsCheque() {
		return esCheque;
	}


	public void setEsCheque(boolean esCheque) {
		this.esCheque = esCheque;
	}


	public boolean getEsAcreditacionBancaria() {
		return esAcreditacionBancaria;
	}


	public void setEsAcreditacionBancaria(boolean esAcreditacionBancaria) {
		this.esAcreditacionBancaria = esAcreditacionBancaria;
	}


	public List getListaDeCuentasBancos() {
		return listaDeCuentasBancos;
	}


	public void setListaDeCuentasBancos(List listaDeCuentasBancos) {
		this.listaDeCuentasBancos = listaDeCuentasBancos;
	}


	public List getListaDeBancos() {
		return listaDeBancos;
	}


	public void setListaDeBancos(List listaDeBancos) {
		this.listaDeBancos = listaDeBancos;
	}


	public Integer getTipoCuentaBancoSeleccionada() {
		return tipoCuentaBancoSeleccionada;
	}


	public void setTipoCuentaBancoSeleccionada(Integer tipoCuentaBancoSeleccionada) {
		this.tipoCuentaBancoSeleccionada = tipoCuentaBancoSeleccionada;
	}


	/*
	 * Acciones del bean FormaDePagoBean.
	 */

	public String agregarFormaDePago() {
		if (validar()) {
			log.info("Ejecutando agregar forma de pago.");
			formaPago.setIdProvFormaPago(new Long(contador));
			if (noSoyProveedor && origen != ORIGEN_CAJA) {
				comercioFormaPago.setIdComercioFormapago(new Long(contador));
				// comercioFormaPago.getSucursalFormaPago().setFormaPago(new FormaPago());
				// comercioFormaPago.getSucursalFormaPago().getFormaPago().setDescripcion(FormaDePagoUtil.getFormaPagoDeLista(listaDeFormasDePago,
				// formaPago.getSucursalFormaPago().getFormaPago().getIdFormaPago()));
			}
			// formaPago.setFormaPago(new FormaPago());

			try {
				formaPago.setFormaPago(generalService.getFormaPagoService().leerFormaPago(getFormaPagoSeleccionada()));
				if (formaPago.getFormaPago().getIdFormaPago().equals(new Long(3))) {
					formaPago.setTipoCta(generalService.getTipoCuentaBancoDao().buscarTipoCuentaBanco(new Long(getTipoCuentaBancoSeleccionada())));
					formaPago.setBanco(generalService.getBancoService().leerBanco(bancoSeleccionado));
				} else {
					formaPago.setTipoCta(null);
					formaPago.setBanco(null);
				}
			} catch (FormaPagoException e1) {
				e1.printStackTrace();
			} catch (BancoException e) {
				e.printStackTrace();
			}

			if (!getFormaPagoSeleccionada().equals(new Long(2))) {
				formaPago.setOrdenCheque(null);
			}

			if (esAcreditacionBancaria) {
				formaPago.setBanco(generalService.getBancoDao().buscarBanco(bancoSeleccionado));
				formaPago.setCbu(cbu);
			}

			/*
			 * if (acreditacionBancariaSeleccionada.equals(new Integer(1))) {
			 * formaPago.setBanco(generalService.getBancoDao().buscarBanco(bancoSeleccionado));
			 * formaPago.setTipoCta(generalService.getTipoCuentaBancoDao().buscarTipoCuentaBanco( new Long(tipoCuentaBancoSeleccionada.longValue())));
			 * }
			 * 
			 * if (esCbu) { formaPago.setCbu(cbu); }
			 */

			// log.info("Id:"+formaPago.getIdProvFormaPago());
			// log.info("es cheque cruzado"+formaPago.getEsChequeCruzado());
			// log.info("Activo:"+formaPago.getEsActivo());
			// log.info("Nr Fondo:"+formaPago.getSucursalFormaPago().getNroCuentaFondos());
			// formaPago.setSucursalFormaPago(sucursalFormaPago);
			formaPago.setNroCuentaFondos(idCuentaFondosSeleccionada);
			if (!noSoyProveedor) {
				formaPago.setIdProvFormaPago(null);
				ProveedorBean bean = (ProveedorBean) Session.getBean("ProveedorBean");
				System.out.println(idCuentaFondosSeleccionada);
				bean.agregarFormaDePago(formaPago);
				log.info("agregar forma pago terminado...");
			}
			if (noSoyProveedor) {
				switch (origen) {
				case 1:
					comercioFormaPago.setActivo(formaPago.getEsActivo());
					comercioFormaPago.setBanco(formaPago.getBanco());
					comercioFormaPago.setCbu(formaPago.getCbu());
					comercioFormaPago.setCodCuentaDeposito(formaPago.getCodCtaDeposito());
					comercioFormaPago.setEsChequeCruzado(formaPago.getEsChequeCruzado());
					comercioFormaPago.setIdComercioFormapago(null);
					comercioFormaPago.setChequeNoAlaOrden(formaPago.getChequeNoAlaOrden());
					comercioFormaPago.setNroCuentaFondos(formaPago.getNroCuentaFondos());
					comercioFormaPago.setOrdenCheque(formaPago.getOrdenCheque());
					comercioFormaPago.setTipoCuentaBanco(formaPago.getTipoCta());
					CodComercioBean beanCodComer = (CodComercioBean) Session.getBean("CodComercioBean");
					comercioFormaPago.setCodComercio(beanCodComer.getCodComercio());
					comercioFormaPago.setFormaPago(formaPago.getFormaPago());
					// comercioFormaPago.setFormaPago(new FormaPago());
					// comercioFormaPago.getFormaPago().setDescripcion(FormaDePagoUtil.getFormaPagoDeLista(listaDeFormasDePago,
					// formaPago.getFormaPago().getIdFormaPago()));
					beanCodComer.agregarFormaDePago(comercioFormaPago);
					log.info("agregar forma pago terminado...");
					break;
				case 3:
					try {
						cajaMP.setFormaPago(formaPago.getFormaPago());
						// PlanCuentaDos cuenta=contabilidadService.getPlanCuentaDosService().leerPlanCuenta(new Long(idPlanCuentaABuscar));
						// cajaMP.setPlanCuentaDos(cuenta);
						// cajaMP.setIdPlanCuenta(new Long(idPlanCuentaABuscar));
						System.out.println(idCuentaFondosSeleccionada);
						// cajaMP.setIdPlanCuenta(idCuentaFondosSeleccionada);
						PlanCuentaDos planCuentaDos = new PlanCuentaDos();
						planCuentaDos.setIdPlanCuenta(idCuentaFondosSeleccionada);
						if (idCuentaFondosSeleccionada != null)
							cajaMP.setPlanCuentaDos(planCuentaDos);
						CajaBean cajaBean = (CajaBean) Session.getBean("CajaBean");
						cajaBean.agregarFormaDePago(cajaMP);
					} catch (NumberFormatException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					break;

				}
			}
			// inicializar();
			contador++;
		}

		return null;
	}


	public void borrar() {
		log.info("Se borra el bean de forma de pago");
		error.borrar();
		formaPago = new ProveedorFormaPago();
		if (noSoyProveedor) {
			if (origen == this.COD_COMERCIO) {
				comercioFormaPago = new ComercioFormaPago();
			}
			if (origen == this.ORIGEN_CAJA) {
				cajaMP = new CajaMP();
			}
		}
		formaPago.setNroCuentaFondos(null);
		formaPago.setOrdenCheque(null);
		formaPago.setEsChequeCruzado("S");
		formaPago.setChequeNoAlaOrden("S");

		bancoSeleccionado = new Long(0);
		tipoCuentaBancoSeleccionada = new Integer(0);
		formaPago.setCodCtaDeposito(null);
		cbu = null;
		acreditacionBancariaSeleccionada = new Integer(0);
		acreditaOneMenuHtml.setValue("0");
		blanquearBool();
	}


	public void selecFormaPago(ValueChangeEvent event) {
		borrar();
		Long valorSeleccionado = new Long(fpOneMenuHtml.getValue().toString());

		if (origen != ORIGEN_CAJA) {
			switch (valorSeleccionado.intValue()) {
			case 2:
				esCheque = true;
				break;

			case 3:
				esAcreditacionBancaria = true;
				formaPago.setBanco(new Banco());
				break;
			}
		} else {
			esCaja = true;
		}
		Util.limpiarLista(cuentaFondosItems);
		this.cuentaFondosItems = FormaDePagoUtil.cargarListaCuentasFondos(contabilidadService.getPlanCuentaDosDao(), valorSeleccionado);
		// preguntar cual es la fma pago por default en caja!!!!!!!!
		if (!cuentaFondosItems.isEmpty()) {
			idCuentaFondosSeleccionada = (new Long(1));
		}
	}


	/*
 * 
 * 
 */

	private void blanquearBool() {
		esAcreditacionBancaria = false;
		esCheque = false;
		esCaja = false;
		cbu = null;

	}


	public String volver() {
		borrar();
		return null;
	}


	public void recargarYCerrarPopup(ActionEvent event) {
		if (validar()) {
			FacesContext facesContext = FacesContext.getCurrentInstance();
			String javaScriptText = "window.opener.recargar();window.close();";
			AddResource addResource = AddResourceFactory.getInstance(facesContext);
			addResource.addInlineScriptAtPosition(facesContext, AddResource.HEADER_BEGIN, javaScriptText);
		}
	}


	public boolean validar() {
		error.borrar();

		if (origen != ORIGEN_CAJA) {

			switch (getFormaPagoSeleccionada().intValue()) {

			case 2:
				if (Validador.esNuloVacio(formaPago.getOrdenCheque())) {
					error.agregar(Error.FORMAPAGO_ORDEN_CHEQUE_REQUERIDO);
				}
				break;

			case 3:

				if (bancoSeleccionado.equals(new Long(0))) {
					error.agregar(Error.FORMAPAGO_BANCO_REQUERIDO);
				}
				if (Validador.esNuloVacio(cbu)) {
					error.agregar(Error.FORMAPAGO_CBU_REQUERIDO);
				} else {
					if (cbu.length() != 22) {
						error.agregar(Error.FORMAPAGO_CBU_INCOMPLETO);
					} else {
						try {
							CbuValido cbuValido = new CbuValido(cbu);
						} catch (CbuNoValidoException e) {
							error.agregar(Error.FORMAPAGO_CBU_INVALIDO);
						} catch (Exception e) {
							e.printStackTrace();
							error.agregar(Error.FORMAPAGO_CBU_INVALIDO);
						}
					}
				}

				break;
			}
		}

		return (error.cantidad() == 0) ? true : false;
	}


	public String inicializar(String razonSocial, int origen) {
		borrar();
		this.noSoyProveedor = true;
		this.origen = origen;
		formaPago.setOrdenCheque(razonSocial);
		cargarFormasDePago();

		Util.limpiarLista(cuentaFondosItems);
		if (origen != ORIGEN_CAJA) {
			fpOneMenuHtml.setValue("3");
			setFormaPagoSeleccionada(new Long(3));
			esAcreditacionBancaria = true;
			this.cuentaFondosItems = FormaDePagoUtil.cargarListaCuentasFondos(contabilidadService.getPlanCuentaDosDao(), new Long(3));
			// preguntar cual es la fma pago por default en caja!!!!!!!!
			if (!cuentaFondosItems.isEmpty()) {
				idCuentaFondosSeleccionada = (new Long(2));
			}
		}
		else {
			esCaja = true;
			this.listaDeFormasDePago = FormaDePagoUtil.cargarListaFormasDePago(generalService.getFormaPagoDao(), new Filtro());
			// preguntar cual es la fma pago por default en caja!!!!!!!!
			fpOneMenuHtml.setValue("1");
			setFormaPagoSeleccionada(new Long(1));
			this.cuentaFondosItems = FormaDePagoUtil.cargarListaCuentasFondos(contabilidadService.getPlanCuentaDosDao(), new Long(2));
			// preguntar cual es la fma pago por default en caja!!!!!!!!
			if (!cuentaFondosItems.isEmpty()) {
				idCuentaFondosSeleccionada = (new Long(1));
			}
		}
		return null;
	}


	public String inicializar(String razonSocial, String idSucFiel) {
		borrar();
		origen = PROVEEDOR;
		noSoyProveedor = false;
		esCheque = true;

		// inicializar();
		formaPago.setOrdenCheque(razonSocial);
		cargarFormasDePago();
		fpOneMenuHtml.setValue("2");
		setFormaPagoSeleccionada(new Long(2));
		Util.limpiarLista(cuentaFondosItems);
		this.cuentaFondosItems = FormaDePagoUtil.cargarListaCuentasFondos(contabilidadService.getPlanCuentaDosDao(), new Long(2));
		return null;
	}


	private void cargarFormasDePago() {
		Filtro filtro = new Filtro();
		filtro.agregarCampoOperValor("idFormaPago", Filtro.MENOR_IGUAL, "3");
		if (origen == COD_COMERCIO) {
			filtro.agregarCampoOperValor("idFormaPago", Filtro.DISTINTO, "1");
		}
		this.listaDeFormasDePago = FormaDePagoUtil.cargarListaFormasDePago(generalService.getFormaPagoDao(), filtro);
		Iterator iter = this.listaDeFormasDePago.iterator();
		/*
		 * String aux=null; boolean encontrado=false; while (iter.hasNext()) { SelectItem element = (SelectItem) iter.next(); aux=
		 * ((Long)element.getValue()).toString(); //if(element.getValue().equals("2")) // { fpOneMenuHtml.setValue("2"); //
		 * setFormaPagoSeleccionada(new Long(2)); // break; // }
		 * 
		 * if(element.getLabel().compareTo("Cheque")==0){ setFormaPagoSeleccionada((Long)element.getValue());
		 * fpOneMenuHtml.setValue(element.getValue()); encontrado=true; esCheque= true; break; }
		 * 
		 * } System.out.println("El aux es: " + aux); if(!encontrado ){//si el prov o comercio no acepta fma pago por defecto
		 * fpOneMenuHtml.setValue(aux); setFormaPagoSeleccionada(new Long(aux)); esCheque=false; } ///obtenemos el nro de cuenta
		 * setearSucursalFormaPago(getFormaPagoSeleccionada().toString());
		 */
	}


	public ComercioFormaPago getComercioFormaPago() {
		return comercioFormaPago;
	}


	public void setComercioFormaPago(ComercioFormaPago comercioFormaPago) {
		this.comercioFormaPago = comercioFormaPago;
	}


	public Long getNroCuentaFondos() {
		return nroCuentaFondos;
	}


	public void setNroCuentaFondos(Long nroCuentaFondos) {
		this.nroCuentaFondos = nroCuentaFondos;
	}


	public boolean isEsCaja() {
		return esCaja;
	}


	public void setEsCaja(boolean esCaja) {
		this.esCaja = esCaja;
	}


	public String getIdPlanCuenta() {
		return idPlanCuenta;
	}


	public void setIdPlanCuenta(String idPlanCuenta) {
		this.idPlanCuenta = idPlanCuenta;
	}


	public boolean isHabilitada() {
		if (cajaMP.getHabilitado() != null)
			return Convertidores.getBoolean(cajaMP.getHabilitado().toString());
		return false;
	}


	public void setHabilitada(boolean habilitada) {
		cajaMP.setHabilitado(Character.valueOf(Convertidores.getSorN(habilitada).charAt(0)));
	}


	public Long getIdCuentaFondosSeleccionada() {
		return idCuentaFondosSeleccionada;
	}


	public void setIdCuentaFondosSeleccionada(Long idCuentaFondosSeleccionada) {
		this.idCuentaFondosSeleccionada = idCuentaFondosSeleccionada;
	}


	public List getCuentaFondosItems() {
		return cuentaFondosItems;
	}


	public void setCuentaFondosItems(List cuentaFondosItems) {
		this.cuentaFondosItems = cuentaFondosItems;
	}


	public boolean isMostrarCuentasFondo() {
		return mostrarCuentasFondo;
	}


	public void setMostrarCuentasFondo(boolean mostrarCuentasFondo) {
		this.mostrarCuentasFondo = mostrarCuentasFondo;
	}


	@Override
	public String inicializar() {
		// TODO Auto-generated method stub
		return null;
	}

}
