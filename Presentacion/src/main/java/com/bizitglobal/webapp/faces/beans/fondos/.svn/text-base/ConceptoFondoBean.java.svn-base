package com.bizitglobal.webapp.faces.beans.fondos;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import javax.faces.component.html.HtmlSelectOneMenu;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

import org.apache.log4j.Logger;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.contabilidad.exception.PlanCuentaDosException;
import com.bizitglobal.tarjetafiel.contabilidad.negocio.PlanCuentaDos;
import com.bizitglobal.tarjetafiel.fondos.negocio.ClaseFondo;
import com.bizitglobal.tarjetafiel.general.exception.ConceptoGenException;
import com.bizitglobal.tarjetafiel.general.negocio.ConceptoDetalleGen;
import com.bizitglobal.tarjetafiel.general.negocio.ConceptoGen;
import com.bizitglobal.webapp.faces.beans.BaseBean;
import com.bizitglobal.webapp.faces.beans.util.ScrollBean;
import com.bizitglobal.webapp.faces.util.Session;


@SuppressWarnings({"rawtypes","unchecked"})
public class ConceptoFondoBean extends BaseBean {
	private static final Logger log = Logger.getLogger(ConceptoFondoBean.class);
	private static int contador = 0;

	private ConceptoGen concepto;

	private ClaseFondo clase = new ClaseFondo();
	private Long idClaseSeleccionada;
	private HtmlSelectOneMenu claseHtml;
	private Long tarSeleccionado;
	private List listaTar;
	private ConceptoDetalleGen detalleUnico;
	private List cuentaUnicaItems;
	private List cuentaCompartidaItems;

	private List signoItems;

	private String codigoFiltro;
	private String descripcionFiltro;
	private Long idConceptoHidden;
	// todo sobre la lista de detalles
	private List compartidasList;

	// definicion de un list del objeto base
	private List conceptoList;

	// Objetos Relacionados.
	private List cuentasDisponibles;
	private boolean verDetalles;

	private String focoHidden;

	private boolean bloquearSigno;


	public ConceptoFondoBean() {
		super();
		borrar();
		listaTar = new ArrayList();
		listaTar.add(new SelectItem(new Long(0), "-"));
		listaTar.add(new SelectItem(new Long(1), "CO"));
		listaTar.add(new SelectItem(new Long(2), "PR"));
		try {
			Filtro filtro = new Filtro("uso", Filtro.LIKE, "I");
			// filtro.agregarCampoOperValor("fondos", Filtro.LIKE, "S");
			filtro.agregarCampoOperValor("habilitada", Filtro.LIKE, "S");
			filtro.agregarOrderBy("idPlanCuenta");
			cuentasDisponibles = contabilidadService.getPlanCuentaDosService().getPlanCuenta(filtro);
		} catch (PlanCuentaDosException e) {
			e.printStackTrace();
		}
		signoItems = new ArrayList();
		signoItems.add(new SelectItem(new Integer(1), "Debe"));
		signoItems.add(new SelectItem(new Integer(-1), "Haber"));
	}


	public boolean isAlta() {
		return alta;
	}


	// public void setAlta(boolean alta) {
	// this.alta = alta;
	// }

	public List getListaTar() {
		return listaTar;
	}


	public void setListaTar(List listaTar) {
		this.listaTar = listaTar;
	}


	public ConceptoGen getConcepto() {
		return concepto;
	}


	public void setConcepto(ConceptoGen concepto) {
		this.concepto = concepto;
	}


	public ClaseFondo getClase() {
		return clase;
	}


	public void setClase(ClaseFondo clase) {
		this.clase = clase;
	}


	public List getCompartidasList() {
		return compartidasList;
	}


	public void setCompartidasList(List compartidasList) {
		this.compartidasList = compartidasList;
	}


	public Long getIdConceptoHidden() {
		return idConceptoHidden;
	}


	public void setIdConceptoHidden(Long idConceptoHidden) {
		this.idConceptoHidden = idConceptoHidden;
	}


	public HtmlSelectOneMenu getClaseHtml() {
		return claseHtml;
	}


	public void setClaseHtml(HtmlSelectOneMenu claseHtml) {
		this.claseHtml = claseHtml;
	}


	public Long getIdClaseSeleccionada() {
		return idClaseSeleccionada;
	}


	public void setIdClaseSeleccionada(Long idClaseSeleccionada) {
		this.idClaseSeleccionada = idClaseSeleccionada;
	}


	public List getConceptoList() {
		return conceptoList;
	}


	public void setConceptoList(List object) {
		this.conceptoList = object;
	}


	public String getFocoHidden() {
		return focoHidden;
	}


	public void setFocoHidden(String focoHidden) {
		this.focoHidden = focoHidden;
	}


	public List getCuentaUnicaItems() {
		return cuentaUnicaItems;
	}


	public void setCuentaUnicaItems(List cuentaUnicaItems) {
		this.cuentaUnicaItems = cuentaUnicaItems;
	}


	public List getSignoItems() {
		return signoItems;
	}


	public void setSignoItems(List signoItems) {
		this.signoItems = signoItems;
	}


	public ConceptoDetalleGen getDetalleUnico() {
		return detalleUnico;
	}


	public void setDetalleUnico(ConceptoDetalleGen detalleUnico) {
		this.detalleUnico = detalleUnico;
	}


	public List getCuentaCompartidaItems() {
		return cuentaCompartidaItems;
	}


	public void setCuentaCompartidaItems(List cuentaCompartidaItems) {
		this.cuentaCompartidaItems = cuentaCompartidaItems;
	}


	public boolean isVerDetalles() {
		return verDetalles;
	}


	public void setVerDetalles(boolean verDetalles) {
		this.verDetalles = verDetalles;
	}


	public boolean isBloquearSigno() {
		return bloquearSigno;
	}


	public void setBloquearSigno(boolean bloquearSigno) {
		this.bloquearSigno = bloquearSigno;
	}


	public String getIdSigno() {
		return detalleUnico.getSigno().toString();
	}


	public void setIdSigno(String idSigno) {
		detalleUnico.setSigno(new Integer(idSigno));
	}


	public String getDescripcionFiltro() {
		return descripcionFiltro;
	}


	public void setDescripcionFiltro(String descripcionFiltro) {
		this.descripcionFiltro = descripcionFiltro;
	}


	public String getCodigoFiltro() {
		return codigoFiltro;
	}


	public void setCodigoFiltro(String codigoFiltro) {
		this.codigoFiltro = codigoFiltro;
	}


	public Integer getSigno() {
		return detalleUnico.getSigno();
	}


	public void setSigno(Integer signo) {
		this.detalleUnico.setSigno(signo);
		this.clase.setSignoCuentaUnica(signo);
	}


	public Long getTarSeleccionado() {
		return tarSeleccionado;
	}


	public void setTarSeleccionado(Long tarSeleccionado) {
		this.tarSeleccionado = tarSeleccionado;
	}


	/************************************************************************
	 * ACCIONES DEL BEAN DE CONCEPTO
	 ************************************************************************/

	public void borrar() {
		borrarBaseBean();
		error.borrar();
		alta = true;
		detalleUnico = new ConceptoDetalleGen();
		detalleUnico.setOrden(new Integer(0));
		compartidasList = new ArrayList();
		tituloLargo = "TARJETAFIEL";
		tituloCorto = "Alta de concepto de Fondos";
		popup.borrar();
		verDetalles = false;
		idClaseSeleccionada = new Long(0);
		claseHtml = new HtmlSelectOneMenu();
		claseHtml.setValue(new Long(0));
		concepto = new ConceptoGen();
		concepto.setFondos("S");
		codigoFiltro = "";
		descripcionFiltro = "";
		conceptoList = new ArrayList();
		cuentaUnicaItems = new ArrayList();
		cuentaCompartidaItems = new ArrayList();
		bloquearSigno = true;
		tarSeleccionado = new Long(0);
	}


	public String inicializar() {
		borrar();
		if (Session.getBean("ScrollBean") != null)
		{
			ScrollBean bean = (ScrollBean) Session.getBean("ScrollBean");
			bean.setHiddenScrollY(new Integer(0));
		}
		return "amConceptoFondo";
	}


	public void cambioClase(ValueChangeEvent event) {
		Long idClaseSeleccionada = (Long) claseHtml.getValue();
		cuentaUnicaItems.clear();
		cuentaUnicaItems.add(new SelectItem(new Long(0), "Seleccione Cuenta Unica"));
		compartidasList.clear();
		cuentaCompartidaItems.clear();
		cuentaCompartidaItems.add(new SelectItem(new Long(0), "Seleccione Cuenta"));
		bloquearSigno = true;
		clase.setClase(idClaseSeleccionada.intValue());
		if (!idClaseSeleccionada.equals(new Long(0))) {
			verDetalles = true;
			// PARA LA CUENTA UNICA
			Iterator iter1 = clase.cuentasUnicas(cuentasDisponibles).iterator();
			while (iter1.hasNext()) {
				PlanCuentaDos aux = (PlanCuentaDos) iter1.next();
				cuentaUnicaItems.add(new SelectItem(aux.getIdPlanCuenta(), aux.getIdPlanCuenta() + " - " + aux.getTitulo()));
			}
			detalleUnico.setSigno(clase.getSignoCuentaUnica());
			// PARA LAS CUENTAS COMPARTIDAS
			Iterator iter2 = clase.cuentasCompartidas(cuentasDisponibles).iterator();
			while (iter2.hasNext()) {
				PlanCuentaDos aux = (PlanCuentaDos) iter2.next();
				cuentaCompartidaItems.add(new SelectItem(aux.getIdPlanCuenta(), aux.getIdPlanCuenta() + " - " + aux.getTitulo()));
			}
			if (clase.getSignoCuentaUnica().intValue() == 0)
				bloquearSigno = false;
		} else {
			verDetalles = false;
		}

	}


	public String listarConcepto() {
		conceptoList.clear();
		try {
			// TODO: Agregar el tema del filtrado
			Filtro filtro = new Filtro();
			if (codigoFiltro.compareTo("") != 0) {
				filtro.agregarCampoOperValor("codigoConcepto", Filtro.IGUAL, new Long(Long.parseLong(codigoFiltro) + 400));
			}
			if (descripcionFiltro.compareTo("") != 0) {
				filtro.agregarCampoOperValor("descripcion", Filtro.LIKE, descripcionFiltro);
			}
			filtro.agregarCampoOperValor("fondos", Filtro.LIKE, "S");
			Iterator iter = generalService.getConceptoGenService().getConcepto(filtro).iterator();
			while (iter.hasNext()) {
				ConceptoGen element = (ConceptoGen) iter.next();
				WrapperConceptoGen wConc = new WrapperConceptoGen(element);
				conceptoList.add(wConc);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		Session.redirect("/tarjetafiel/fondos/listarConceptoFondo.jsf");
		return null;
	}


	public String agregarDetalle() {
		log.info("Ejecutando ==> agregarDetalle()");
		compartidasList.add(new WrapperDetalleGen(new ConceptoDetalleGen()));
		return null;
	}


	public String grabar() {
		try {
			if (validar()) {
				if (concepto.getTipoConcepto() == null
						|| concepto.getTipoConcepto().getIdTipoConcepto() == null
						|| !concepto.getTipoConcepto().getIdTipoConcepto().equals(new Long(clase.getClase()))) {
					concepto.setTipoConcepto(generalService.getTipoConceptoGenService().leerTipoConceptoGen(new Long(clase.getClase())));
				}
				if (concepto.getConceptoDetalleSet() == null)
					concepto.setConceptoDetalleSet(new HashSet());
				else
					concepto.getConceptoDetalleSet().clear();

				switch (tarSeleccionado.intValue()) {
				case 1:
					concepto.setTarget("CO");
					break;
				case 2:
					concepto.setTarget("PR");
					break;
				default:
					concepto.setTarget(null);
					break;
				}

				detalleUnico.setConcepto(concepto);
				detalleUnico.setActivo("S");
				concepto.getConceptoDetalleSet().add(detalleUnico);
				Iterator iter = compartidasList.iterator();
				while (iter.hasNext()) {
					WrapperDetalleGen wap = (WrapperDetalleGen) iter.next();
					wap.getDetalle().setConcepto(concepto);
					wap.getDetalle().setActivo("S");
					wap.getDetalle().setSigno(new Integer(detalleUnico.getSigno().intValue() * -1));
					concepto.getConceptoDetalleSet().add(wap.getDetalle());
				}
				if (alta) {
					concepto.setSucursal(generalService.getSucursalFielService().leerSucursalFiel(new Long(1)));
					generalService.getConceptoGenService().grabarConcepto(concepto);
				} else {
					generalService.getConceptoGenService().actualizarConcepto(concepto);
				}
				popup.setPopup(popup.ICONO_OK, "El concepto de fondos ha sido almacenado exitosamente.");
			} else {
				ScrollBean scrollBean = (ScrollBean) Session.getBean("ScrollBean");
				scrollBean.borrar();
			}
		} catch (Exception e3) {
			popup.setPopup(popup.ICONO_FALLA, "Falla el alta del concepto.");
			e3.printStackTrace();
		}
		return "";
	}


	public String cancelar() {
		borrar();
		return irAListarConcepto();
	}


	public boolean validar() {
		error.borrar();
		if (concepto.getDescripcion() == null || concepto.getDescripcion().compareTo("") == 0)
			error.agregar("La Descripción del concepto es requerida");
		else {
			try {
				Filtro filtro = new Filtro("descripcion", Filtro.LIKEXS, concepto.getDescripcion().trim());
				filtro.agregarCampoOperValor("fondos", Filtro.LIKE, "S");
				if (!alta)
					filtro.agregarCampoOperValor("idConcepto", Filtro.DISTINTO, concepto.getIdConcepto());
				List listaConceptos = generalService.getConceptoGenService().getConcepto(filtro);
				if (listaConceptos.size() > 0)
					error.agregar("La descripción del concepto ingresado ya existe.");
			} catch (ConceptoGenException e) {
				e.printStackTrace();
			}
		}

		if (concepto.getCodigoConcepto() == null) {
			error.agregar("El codigo es un dato requerido");
		} else {
			// controlo que sea un un rango correcto y que no se repita.
			if (concepto.getCodigoConcepto().longValue() > 600 || concepto.getCodigoConcepto().longValue() < 400) {
				error.agregar("El código de concepto de fondos debe ser un número entre 0 y 200.");
			} else {
				if (alta) {
					try {
						List listaConceptos = generalService.getConceptoGenService().getConcepto(
								new Filtro("codigoConcepto", Filtro.IGUAL, concepto.getCodigoConcepto()));
						if (listaConceptos.size() > 0)
							error.agregar("El código de concepto ingresado ya existe.");
					} catch (ConceptoGenException e) {
						e.printStackTrace();
					}
				}
			}
		}

		if (clase.getClase() == 0) {
			error.agregar("Debe seleccionar una clase");
		} else {
			if (detalleUnico.getNombre() == null || detalleUnico.getNombre().compareTo("") == 0)
				error.agregar("La descripción de la cunta unica es requerida");

			if (detalleUnico.getCtacontable() == null || detalleUnico.getCtacontable().equals(new Long(0)))
				error.agregar("La cuenta en la cuenta unica es un dato requerido");

			if (clase.getSignoCuentaUnica().equals(new Integer(0)))
				error.agregar("Debe seleccionar si la cuenta unica va al debe o al haber");

			Iterator iter = compartidasList.iterator();
			while (iter.hasNext()) {
				WrapperDetalleGen wDetalleGen = (WrapperDetalleGen) iter.next();
				wDetalleGen.validar();
			}
		}

		return !error.hayErrores();
	}


	public String irANuevoConcepto() {
		return inicializar();
	}


	public String irAModificarConcepto() {
		alta = false;
		popup.borrar();
		tituloCorto = "Modificar concepto de fondos";
		return null;
	}


	public String irAListarConcepto() {
		borrar();
		tituloCorto = "Listado de conceptos de fondos";
		Session.redirect("/tarjetafiel/fondos/listarConceptoFondo.jsf");
		return null;
	}


	public String editarConcepto() {
		String result = null;
		borrar();
		alta = false;
		tituloCorto = "Modificar concepto de fondos";
		try {
			concepto = generalService.getConceptoGenService().leerConcepto(idConceptoHidden);
			if (concepto.getTarget() == null)
				tarSeleccionado = new Long(0);
			else if (concepto.getTarget().equals("CO"))
				tarSeleccionado = new Long(1);
			else if (concepto.getTarget().equals("PR"))
				tarSeleccionado = new Long(2);

			claseHtml.setValue(concepto.getTipoConcepto().getIdTipoConcepto());
			cambioClase(null);

			Iterator iter = concepto.getConceptoDetalleSet().iterator();
			while (iter.hasNext()) {
				ConceptoDetalleGen det = (ConceptoDetalleGen) iter.next();
				if (det.getOrden().intValue() == 1) {
					compartidasList.add(new WrapperDetalleGen(det));
				} else {
					detalleUnico = det;
				}
			}
			result = "amConceptoFondo";
		} catch (ConceptoGenException e1) {
			error.agregar("Ocurrio un error al intentar editar el concepto");
			e1.printStackTrace();
			Session.redirect("/tarjetafiel/fondos/listarConceptoFondo.jsf");
		} catch (Exception e) {
			error.agregar("Ocurrio un error al intentar editar el concepto");
			e.printStackTrace();
			Session.redirect("/tarjetafiel/fondos/listarConceptoFondo.jsf");
		}
		return result;
	}


	public String eliminarConcepto() {
		try {
			error.borrar();
			WrapperConceptoGen wrapperConcepto = (WrapperConceptoGen) conceptoList
					.get(conceptoList.indexOf(new WrapperConceptoGen(idConceptoHidden)));
			ConceptoGen concepto = wrapperConcepto.getConcepto();
			if (concepto.getEsFiel().equals("S"))
				error.agregar("No se pueden eliminar conceptos controlados por el sistema");

			if (!error.hayErrores()) {
				generalService.getConceptoGenService().borrarConcepto(idConceptoHidden);
				conceptoList.remove(new WrapperConceptoGen(idConceptoHidden));
			}
		} catch (ConceptoGenException e1) {
			error.agregar("Imposible borrar el concepto. Posee elementos asociados");
			e1.printStackTrace();
		} catch (Exception e) {
			error.agregar("Ocurrió un error al intentar borrar el concepto");
			e.printStackTrace();
		}
		return null;
	}

	public class WrapperConceptoGen {
		private ConceptoGen concepto;


		public WrapperConceptoGen(Long idConcepto) {
			this.concepto = new ConceptoGen(idConcepto);
		}


		public WrapperConceptoGen(ConceptoGen c) {
			this.concepto = c;
		}


		public ConceptoGen getConcepto() {
			return concepto;
		}


		public void setConcepto(ConceptoGen conceptoGen) {
			this.concepto = conceptoGen;
		}


		public boolean equals(Object obj) {
			if (obj instanceof WrapperConceptoGen) {
				WrapperConceptoGen aux = (WrapperConceptoGen) obj;
				if (aux.getConcepto().getIdConcepto().equals(concepto.getIdConcepto()))
					return true;
			}
			return false;
		}

	}

	/**
	 * @author hernan Este wrapper es la representacion de las cuentas compartidas en el concepto de fondos. A todas estas se les asigna el "orden" en
	 *         "1", ya que es lo que la identifica como compartida
	 */
	public class WrapperDetalleGen {
		private int indice;
		private ConceptoDetalleGen detalle;
		private final Integer orden = new Integer(1);


		public WrapperDetalleGen(ConceptoDetalleGen detalle) {
			this.indice = contador++;
			this.detalle = detalle;
			this.detalle.setOrden(orden); // seteo que la identifica como compartida.
		}


		public ConceptoDetalleGen getDetalle() {
			return detalle;
		}


		public void setDetalle(ConceptoDetalleGen detalle) {
			this.detalle = detalle;
		}


		public int getIndice() {
			return indice;
		}


		public void setIndice(int indice) {
			this.indice = indice;
		}


		public void validar() {
			if (detalle.getNombre() == null || detalle.getNombre().compareTo("") == 0)
				error.agregar("La descripción en las cuntas compartidas es requerida");

			if (detalle.getCtacontable() == null || detalle.getCtacontable().equals(new Long(0)))
				error.agregar("La cuenta en la cuentas compartidas es un dato requerido");
		}


		public String borrar() {
			compartidasList.remove(this);
			return "";
		}
	}
}
