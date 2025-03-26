package com.bizitglobal.webapp.faces.beans.general;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.apache.log4j.Logger;
import org.apache.myfaces.renderkit.html.util.AddResource;
import org.apache.myfaces.renderkit.html.util.AddResourceFactory;

import com.bizitglobal.tarjetafiel.evaluacion.negocio.PromotorTelefono;
import com.bizitglobal.tarjetafiel.evaluacion.negocio.Telefonos;
import com.bizitglobal.tarjetafiel.evaluacion.negocio.VerificadorTelefono;
import com.bizitglobal.tarjetafiel.general.negocio.SucTelefono;
import com.bizitglobal.tarjetafiel.general.negocio.Telefono;
import com.bizitglobal.tarjetafiel.general.negocio.TipoTelefono;
import com.bizitglobal.tarjetafiel.proveedores.negocio.ProveedorTelefono;
import com.bizitglobal.webapp.faces.beans.BaseBean;
import com.bizitglobal.webapp.faces.beans.evaluacion.IndividuoEvaluacionBean;
import com.bizitglobal.webapp.faces.beans.evaluacion.ModificacionEstructuraCuentaBean;
import com.bizitglobal.webapp.faces.beans.evaluacion.PromotorEvaluacionBean;
import com.bizitglobal.webapp.faces.beans.evaluacion.VerificadorEvaluacionBean;
import com.bizitglobal.webapp.faces.beans.proveedores.ProveedorBean;
import com.bizitglobal.webapp.faces.beans.transacciones.ColaboradorBean;
import com.bizitglobal.webapp.faces.beans.transacciones.popup.IndividuoPopupBean;
import com.bizitglobal.webapp.faces.util.Error;
import com.bizitglobal.webapp.faces.util.Session;
import com.bizitglobal.webapp.faces.beans.general.EmpresaBean;



@SuppressWarnings({"rawtypes","unchecked"})
public class TelefonoBean extends BaseBean {
	private static final Logger log = Logger.getLogger(IndividuoEvaluacionBean.class);

	private Telefono telefono;

	private List listTipoTel;
	private List aux;
	private String numeroInicial; // por si cancela las modificaciones,

	private String selectRadioButton;

	// Objetos para inicializar desde distintos origenes
	private int origen = 0;
	public static final int PROVEEDOR = 1;
	public static final int TITULAR = 2;
	public static final int ADICIONAL = 3;
	public static final int GARANTE = 4;
	public static final int VERIFICADOR = 5;
	public static final int PROMOTOR = 6;
	public static final int SUC_EMPRESA = 7;
	public static final int COLABORADOR = 8;
	// public static final int CODCOMERCIO = 9;
	public static final int INDIVIDUO_POPUP = 10;
	public static final int MODIFICACION_ESTRUCTURA_CUENTA = 11;


	public TelefonoBean() {
		super();
	}


	public void borrar() {
		borrarBaseBean();
		error.borrar();

		listTipoTel = new ArrayList();

		selectRadioButton = "";

		telefono = new Telefono();
	}


	public String inicializar() {

		return null;
	}


	public String inicializar(int origen, Telefono telefono) {
		log.info("Ejecutando ==> inicializar()");
		borrar();
		if (telefono.getNroTlefono() != null) {
			numeroInicial = telefono.getNroTlefono();
		}
		this.origen = origen;
		this.telefono = telefono;
		aux = generalService.getTipoTelefonoDao().listarTodos();
		listTipoTel = TelefonoUtil.cargarListaTelefonos(aux);

		if (this.telefono.getEsCelular() != null && !this.telefono.getEsCelular().equals("") && this.telefono.getEsCelular().equals(new String("S")))
			selectRadioButton = "1";

		if (this.telefono.getEsFax() != null && !this.telefono.getEsFax().equals("") && this.telefono.getEsFax().equals(new String("S")))
			selectRadioButton = "2";

		if (this.telefono.getEsCelular() != null && !this.telefono.getEsCelular().equals("") && !this.telefono.getEsCelular().equals(new String("S"))
				&& this.telefono.getEsCelular() != null && !this.telefono.getEsCelular().equals("")
				&& !this.telefono.getEsCelular().equals(new String("S")))
			selectRadioButton = "3";

		return null;
	}


	public boolean validar() {
		error.borrar();
		if (telefono.getCodArea() == null || telefono.getCodArea().equals("")) {
			error.agregar(Error.EVA_COD_AREA_TEL_REQUERIO);
		}
		if (telefono.getNroTlefono() == null || telefono.getNroTlefono().equals("")) {
			error.agregar(Error.EVA_NRO_TEL_REQUERIO);
		}
		if (telefono.getTipo().getIdTipoTelefono() == null || telefono.getTipo().getIdTipoTelefono().equals(new Long(0))) {
			error.agregar(Error.EVA_TIPO_TELEFONO_REQUERIDO);
		}

		switch (origen) {
		case PROVEEDOR:

			break;

		case TITULAR:

			break;

		case ADICIONAL:

			break;

		case GARANTE:

			break;

		case VERIFICADOR:

			break;

		case PROMOTOR:

			break;

		case SUC_EMPRESA:

			break;
		case COLABORADOR:

			break;
		// case CODCOMERCIO:
		case INDIVIDUO_POPUP:

			break;
		}
		return (error.cantidad() == 0) ? true : false;
	}


	public String agregarTelefono() {
		if (validar()) {
			switch (origen) {
			case PROVEEDOR:
				log.info("Ejecutando ==> agregarTelefono() a Proveedor");
				ProveedorBean proveedor = (ProveedorBean) Session.getBean("ProveedorBean");

				if (telefono.getIdTelefono() == null) {
					telefono.setIdTelefono(new Long(telefono.hashCode()));
					ProveedorTelefono telefonos = new ProveedorTelefono();
					telefonos.setTelefono(telefono);
					proveedor.getListaTelefonos().add(telefonos);
				}
				cargarDescripcionTipoTelefono();

				break;

			case TITULAR:
				log.info("Ejecutando ==> agregarTelefono() a titular");
				IndividuoEvaluacionBean bean = (IndividuoEvaluacionBean) Session.getBean("IndividuoEvaluacionBean");

				if (telefono.getIdTelefono() == null) {
					telefono.setIdTelefono(new Long(telefono.hashCode()));
					Telefonos telefonos = new Telefonos();
					telefonos.setTelefono(telefono);
					bean.getListTelefono().add(telefonos);
				}
				cargarDescripcionTipoTelefono();
				bean.setFocoHidden("lstestadoCivil");
				log.info("size: " + bean.getListTelefono().size());
				break;

			case ADICIONAL:
				log.info("Ejecutando ==> agregarTelefono().adicional");
				IndividuoEvaluacionBean beanAdicional = (IndividuoEvaluacionBean) Session.getBean("IndividuoEvaluacionBean");
				if (telefono.getIdTelefono() == null) {
					telefono.setIdTelefono(new Long(telefono.hashCode()));
					Telefonos telefonos = new Telefonos();
					telefonos.setTelefono(telefono);
					beanAdicional.getListTelefono().add(telefonos);
				}
				cargarDescripcionTipoTelefono();
				beanAdicional.setFocoHidden("lstestadoCivil");
				log.info("size: " + beanAdicional.getListTelefono().size());
				break;

			case GARANTE:
				log.info("Ejecutando ==> agregarTelefono().garante");
				IndividuoEvaluacionBean beanGarante = (IndividuoEvaluacionBean) Session.getBean("IndividuoEvaluacionBean");
				if (telefono.getIdTelefono() == null) {
					telefono.setIdTelefono(new Long(telefono.hashCode()));
					Telefonos telefonos = new Telefonos();
					telefonos.setTelefono(telefono);
					beanGarante.getListTelefono().add(telefonos);
				}
				cargarDescripcionTipoTelefono();
				beanGarante.setFocoHidden("lstestadoCivil");
				log.info("size: " + beanGarante.getListTelefono().size());
				break;

			case VERIFICADOR:
				log.info("Ejecutando ==> agregarTelefono().verificador");
				VerificadorEvaluacionBean beanVerif = (VerificadorEvaluacionBean) Session.getBean("VerificadorEvaluacionBean");
				cargarDescripcionTipoTelefono();
				if (telefono.getIdTelefono() == null) {
					VerificadorTelefono verificadorTelefono = new VerificadorTelefono();
					verificadorTelefono.setVerificador(beanVerif.getVerificador());
					verificadorTelefono.setTelefono(telefono);
					verificadorTelefono.setIdVerifTelefono(new Long(telefono.hashCode()));
					beanVerif.getListTelefonos().add(verificadorTelefono);
				}
				break;
			case PROMOTOR:
				log.info("Ejecutando ==> agregarTelefono().promotor");
				PromotorEvaluacionBean beans = (PromotorEvaluacionBean) Session.getBean("PromotorEvaluacionBean");
				cargarDescripcionTipoTelefono();
				if (telefono.getIdTelefono() == null) {
					telefono.setIdTelefono(new Long(telefono.hashCode()));
					PromotorTelefono promotorTelefono = new PromotorTelefono();
					promotorTelefono.setPromotor(beans.getPromotor());
					promotorTelefono.setTelefono(telefono);
					promotorTelefono.setIdPromotorTelefono(new Long(telefono.hashCode()));
					beans.getListTelefono().add(promotorTelefono);
				}
				break;
			case SUC_EMPRESA:
				log.info("Ejecutando ==> agregarTelefono() a suc Empresa");
				EmpresaBean beanEmpresa = (EmpresaBean) Session.getBean("EmpresaBean");
				cargarDescripcionTipoTelefono();
				if (telefono.getIdTelefono() == null) {

					SucTelefono telAux = new SucTelefono();
					telAux.setIdSucTelefono(null);
					telAux.setSucEmpresa(beanEmpresa.getSucursalEmpresa().getSucEmpresa());
					// Para evitar que se dupliquen telefonos, al agregar y editar sin guardar.
					telefono.setIdTelefono(new Long(-1));
					telAux.setTelefono(telefono);

					// telefono.setIdTelefono(new Long(telAux.hashCode()));
					EmpresaBean.TelefonoWrapper telefonoWrapper = beanEmpresa.new TelefonoWrapper(telAux);
					beanEmpresa.getSucursalEmpresa().getTelefonos().add(telefonoWrapper);

				}
				log.info("size: " + beanEmpresa.getSucursalEmpresa().getTelefonos().size());
				break;
			case COLABORADOR:
				log.info("Ejecutando ==> agregarTelefono() a colaborador");
				ColaboradorBean beanColabo = (ColaboradorBean) Session.getBean("ColaboradorBean");
				if (telefono.getIdTelefono() == null) {
					telefono.setIdTelefono(new Long(telefono.hashCode()));
					Telefonos telefonos = new Telefonos();
					telefonos.setTelefono(telefono);
					beanColabo.getListTelefono().add(telefonos);
				}
				cargarDescripcionTipoTelefono();
				log.info("size: " + beanColabo.getListTelefono().size());
				break;
			// case CODCOMERCIO:
			// log.info("Ejecutando ==> agregarTelefono() a CodComercio");
			// CodComercioBean beanCodComercio = (CodComercioBean)Session.getBean("CodComercioBean");
			//
			// if(telefono.getIdTelefono() == null){
			// telefono.setIdTelefono(new Long(telefono.hashCode()));
			// Telefonos telefonos = new Telefonos();
			// telefonos.setTelefono(telefono);
			// beanCodComercio.getListTelefono().add(telefonos);
			// }
			// cargarDescripcionTipoTelefono();
			// log.info("size: " + beanCodComercio.getListTelefono().size());
			// break;
			case INDIVIDUO_POPUP:
				log.info("Ejecutando ==> agregarTelefono() a IndividuoPopup");
				IndividuoPopupBean individuoPopupBean = (IndividuoPopupBean) Session.getBean("IndividuoPopupBean");

				if (telefono.getIdTelefono() == null) {
					telefono.setIdTelefono(new Long(telefono.hashCode()));
					Telefonos telefonos = new Telefonos();
					telefonos.setTelefono(telefono);
					individuoPopupBean.getListTelefono().add(telefonos);
				}
				cargarDescripcionTipoTelefono();
				log.info("size: " + individuoPopupBean.getListTelefono().size());
				break;

			case MODIFICACION_ESTRUCTURA_CUENTA:
				log.info("Ejecutando ==> agregarTelefono() en Workflow Modificacion Cuenta");
				ModificacionEstructuraCuentaBean beanMod = (ModificacionEstructuraCuentaBean) Session.getBean("ModificacionEstructuraCuentaBean");

				if (telefono.getIdTelefono() == null) {
					telefono.setIdTelefono(new Long(telefono.hashCode()));
					Telefonos telefonos = new Telefonos();
					telefonos.setTelefono(telefono);
					beanMod.getListTelefono().add(telefonos);
				}
				cargarDescripcionTipoTelefono();
				beanMod.setFocoHidden("lstestadoCivil");
				log.info("size: " + beanMod.getListTelefono().size());

				break;
			}
		}
		return null;
	}


	private void cargarDescripcionTipoTelefono() {
		if (selectRadioButton != null && !selectRadioButton.equals("")) {
			switch (Integer.parseInt(selectRadioButton)) {

			case 1:
				telefono.setEsCelular(new String("S"));
				telefono.setEsFax(new String("N"));
				break;

			case 2:
				telefono.setEsCelular(new String("N"));
				telefono.setEsFax(new String("S"));
				break;

			case 3:
				telefono.setEsCelular(new String("N"));
				telefono.setEsFax(new String("N"));
				break;
			}

		} else {
			telefono.setEsCelular(new String("N"));
			telefono.setEsFax(new String("N"));
		}
		Iterator iterator = aux.iterator();
		while (iterator.hasNext()) {
			TipoTelefono element = (TipoTelefono) iterator.next();
			if (element.getIdTipoTelefono().equals(telefono.getTipo().getIdTipoTelefono())) {
				telefono.setTipo(element);
			}

		}
	}


	public void recargarYCerrarPopup(ActionEvent event) {
		log.info("Ejecutando ==> recargarYCerrarPopup");
		if (validar()) {
			FacesContext facesContext = FacesContext.getCurrentInstance();
			String javaScriptText = "window.opener.recargar();window.close();";
			AddResource addResource = AddResourceFactory.getInstance(facesContext);
			addResource.addInlineScriptAtPosition(facesContext, AddResource.HEADER_BEGIN, javaScriptText);
		}
	}


	public String cancelar() {
		log.info("Ejecutando ==> cancelar()");
		telefono.setNroTlefono(numeroInicial);
		borrar();
		return null;
	}


	public Long getIdTipoTel() {
		return telefono.getTipo().getIdTipoTelefono();
	}


	public void setIdTipoTel(Long idTipoTel) {
		telefono.getTipo().setIdTipoTelefono(idTipoTel);
	}


	public List getListTipoTel() {
		return listTipoTel;
	}


	public void setListTipoTel(List listTipoTel) {
		this.listTipoTel = listTipoTel;
	}


	public String getSelectRadioButton() {
		return selectRadioButton;
	}


	public void setSelectRadioButton(String selectRadioButton) {
		this.selectRadioButton = selectRadioButton;
	}


	public int getOrigen() {
		return origen;
	}


	public void setOrigen(int origen) {
		this.origen = origen;
	}


	public Telefono getTelefono() {
		return telefono;
	}


	public void setTelefono(Telefono telefono) {
		this.telefono = telefono;
	}
}
