package com.bizitglobal.webapp.faces.beans;

import java.text.DateFormat;
import java.util.Date;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.myfaces.renderkit.html.util.AddResource;
import org.apache.myfaces.renderkit.html.util.AddResourceFactory;

import com.bizitglobal.webapp.faces.beans.error.ErrorBean;
import com.bizitglobal.webapp.faces.beans.util.Popup;
import com.bizitglobal.webapp.faces.beans.util.ScrollBean;
import com.bizitglobal.webapp.faces.service.cobranzas.CobranzasServiceFaces;
import com.bizitglobal.webapp.faces.service.contabilidad.ContabilidadServiceFaces;
import com.bizitglobal.webapp.faces.service.evaluacion.EvaluacionServiceFaces;
import com.bizitglobal.webapp.faces.service.fondos.FondosServiceFaces;
import com.bizitglobal.webapp.faces.service.general.GeneralServiceFaces;
import com.bizitglobal.webapp.faces.service.impuestos.ImpuestoServiceFaces;
import com.bizitglobal.webapp.faces.service.operador.ABMOperadorServiceFaces;
import com.bizitglobal.webapp.faces.service.proveedores.ProveedoresServiceFaces;
import com.bizitglobal.webapp.faces.service.transacciones.TransaccionesServiceFaces;
import com.bizitglobal.webapp.faces.service.workflow.WorkflowServiceFaces;
import com.bizitglobal.webapp.faces.util.Session;

import javax.faces.context.FacesContext;


/**
 * Clase de la que heredan todos los beans del sistema.
 * 
 * @author Daniel
 */
public abstract class BaseBean {
	private static final long serialVersionUID = 6390940830550036297L;
	private Logger log = Logger.getLogger(BaseBean.class);

	protected ErrorBean error;
	protected Popup popup;
	protected String tituloLargo;
	protected String tituloCorto;
	protected boolean alta;
	protected ScrollBean scroll;
	protected static ABMOperadorServiceFaces operadorService;
	protected static ProveedoresServiceFaces proveedoresService;
	protected static GeneralServiceFaces generalService;
	protected static ImpuestoServiceFaces impuestoService;
	protected static WorkflowServiceFaces workflowService;
	protected static EvaluacionServiceFaces evaluacionService;
	protected static ContabilidadServiceFaces contabilidadService;
	protected static TransaccionesServiceFaces transaccionesService;
	protected static FondosServiceFaces fondosService;
	protected static CobranzasServiceFaces cobranzasService;


	public BaseBean() {
		if (operadorService == null)
			operadorService = new ABMOperadorServiceFaces();
		if (proveedoresService == null)
			proveedoresService = new ProveedoresServiceFaces();
		if (generalService == null)
			generalService = new GeneralServiceFaces();
		if (impuestoService == null)
			impuestoService = new ImpuestoServiceFaces();
		if (workflowService == null)
			workflowService = new WorkflowServiceFaces();
		if (evaluacionService == null)
			evaluacionService = new EvaluacionServiceFaces();
		if (contabilidadService == null)
			contabilidadService = new ContabilidadServiceFaces();
		if (transaccionesService == null)
			transaccionesService = new TransaccionesServiceFaces();
		if (fondosService == null)
			fondosService = new FondosServiceFaces();
		if (cobranzasService == null)
			cobranzasService = new CobranzasServiceFaces();

		alta = true;
		error = (ErrorBean) Session.getBean("ErrorBean");
		if (error != null)
			error.borrar();
		scroll = (ScrollBean) Session.getBean("ScrollBean");
		if (scroll != null)
			scroll.borrar();
		popup = new Popup();
	}


	/**
	 * Obtiene el día y la hora corrientes.
	 * 
	 * @return El día y la hora corrientes.
	 */
	public String getDate() {
		return DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG).format(new Date());
	}


	/**
	 * Ejecuta una porción de codigo javascript.
	 * 
	 * @param event
	 */
	public void ejecutarJavaScript(String script) {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		AddResource addResource = AddResourceFactory.getInstance(facesContext);
		addResource.addInlineScriptAtPosition(facesContext, AddResource.HEADER_BEGIN, script);
	}


	public Popup getPopup() {
		return popup;
	}


	public void setPopup(Popup popup) {
		this.popup = popup;
	}


	public void borrarBaseBean() {
		error.borrar();
		popup.borrar();
		scroll.borrar();
	}


	/**
	 * Borra el bean del objeto que la implementa.
	 */
	public abstract void borrar();


	/**
	 * Valida el bean del objeto que la implementa.
	 * 
	 * @return true si es correcta la validacion, false caso contrario.
	 */
	public abstract boolean validar();


	/**
	 * Inicializa el bean del objeto que lo implementa.
	 */
	public abstract String inicializar();


	/**
	 * Inicializa el bean con un Map de parametros para la llamada desde el worflow.
	 */
	public void inicializarParametros(Map param) {

	}


	public String getTituloCorto() {
		return tituloCorto;
	}


	public void setTituloCorto(String tituloCorto) {
		this.tituloCorto = tituloCorto;
	}


	public String getTituloLargo() {
		return tituloLargo;
	}


	public void setTituloLargo(String tituloLargo) {
		this.tituloLargo = tituloLargo;
	}

}
