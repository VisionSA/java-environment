package com.bizitglobal.webapp.faces.beans.workflow;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.faces.component.html.HtmlSelectOneMenu;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

import org.apache.log4j.Logger;
import org.apache.myfaces.renderkit.html.util.AddResource;
import org.apache.myfaces.renderkit.html.util.AddResourceFactory;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.webapp.faces.beans.BaseBean;
import com.bizitglobal.webapp.faces.beans.util.ScrollBean;
import com.bizitglobal.webapp.faces.util.Error;
import com.bizitglobal.webapp.faces.util.Session;
import com.bizitglobal.webapp.faces.util.Util;
import com.bizitglobal.webapp.faces.util.Validador;
import com.bizitglobal.workflow.exception.IniParametroException;
import com.bizitglobal.workflow.exception.ProcesoAtributoException;
import com.bizitglobal.workflow.exception.TipoAtributoException;
import com.bizitglobal.workflow.negocio.Columna;
import com.bizitglobal.workflow.negocio.IniParametro;
import com.bizitglobal.workflow.negocio.Proceso;
import com.bizitglobal.workflow.negocio.ProcesoAtributo;
import com.bizitglobal.workflow.negocio.TipoAtributo;
import com.bizitglobal.workflow.service.IniParametroService;


@SuppressWarnings({"rawtypes","unchecked"})
public class AtributoBean extends BaseBean {
	private static final Logger log = Logger.getLogger(AtributoBean.class);
	private ProcesoAtributo atributo;

	// Listas para la presentacion(HtmlSelectItems).
	private List tipoAtributos;

	HtmlSelectOneMenu tiposHtml = new HtmlSelectOneMenu();

	// Lista de atributos para el proceso.
	private List atributos;

	// Indica si nos encontramos en una alta o modificacion.
	private boolean esParametro;
	private boolean boolLongitud;

	// Objetos para las verificaciones
	private String msjValor;
	private String msjGraba;
	private String msjLista;
	private String msjValida;

	// Indica desde donde toma el valor el parametro.
	private boolean esBuscaValor;


	public AtributoBean() {
		super();
		// Nombre de Titulos para la presentacion.
		tituloLargo = "Tarjeta Fiel - Administracion de tramites";
		tituloCorto = "Alta de atributos";
		tipoAtributos = cargarListaTipoAtributo();
		atributos = new ArrayList();
		borrar();

	}


	public HtmlSelectOneMenu getTiposHtml() {
		return tiposHtml;
	}


	public void setTiposHtml(HtmlSelectOneMenu tiposHtml) {
		this.tiposHtml = tiposHtml;
	}


	public String getMsjValida() {
		return msjValida;
	}


	public void setMsjValida(String msjValida) {
		this.msjValida = msjValida;
	}


	public String getMsjGraba() {
		return msjGraba;
	}


	public void setMsjGraba(String msjGraba) {
		this.msjGraba = msjGraba;
	}


	public String getMsjLista() {
		return msjLista;
	}


	public void setMsjLista(String msjLista) {
		this.msjLista = msjLista;
	}


	public String getMsjValor() {
		return msjValor;
	}


	public void setMsjValor(String msjValor) {
		this.msjValor = msjValor;
	}


	public ProcesoAtributo getAtributo() {
		return atributo;
	}


	public void setAtributo(ProcesoAtributo atributo) {
		this.atributo = atributo;
	}


	public Proceso getProceso() {
		return atributo.getProceso();
	}


	public void setProceso(Proceso proceso) {
		atributo.setProceso(proceso);
		;
	}


	public List getTipoAtributos() {
		return tipoAtributos;
	}


	public void setTipoAtributos(List tipoAtributos) {
		this.tipoAtributos = tipoAtributos;
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


	public List getAtributos() {
		return atributos;
	}


	public void setAtributos(List atributos) {
		this.atributos = atributos;
	}


	public Long getTipoASeleccionado() {
		return atributo.getTipoAtributo().getIdTipoAtributo();
	}


	public void setTipoASeleccionado(Long tipoASeleccionado) {
		atributo.getTipoAtributo().setIdTipoAtributo(tipoASeleccionado);
	}


	public boolean getEsParametro() {
		return esParametro;
	}


	public void setEsParametro(boolean esParametro) {
		this.esParametro = esParametro;
	}


	public boolean getBoolLongitud() {
		return boolLongitud;
	}


	public void setBoolLongitud(boolean boolLongitud) {
		this.boolLongitud = boolLongitud;
	}


	public boolean isEsBuscaValor() {
		return esBuscaValor;
	}


	public void setEsBuscaValor(boolean esBuscaValor) {
		this.esBuscaValor = esBuscaValor;
	}


	public void borrar() {
		error.borrar();
		atributo = new ProcesoAtributo();
		// Carga al objeto atributo los objetos relacionados.
		atributo = new ProcesoAtributo();
		atributo.setColumna(new Columna());
		atributo.setProceso(new Proceso());
		atributo.setTipoAtributo(new TipoAtributo());

		atributo.setSqlValor("SELECT [Nombre campo] AS VALOR\nFROM [Nombre tabla]\nWHERE [Nombre campo identificador]\n	= @{Nombre atributo}");
		atributo.setSqlGraba("UPDATE [Nombre tabla]\nSET [Nombre campo] = VALOR\nWHERE [Nombre campo identificador]\n	= @{Nombre atributo}");
		atributo.setSqlLista("SELECT [Nombre campo id] AS ID, [Nombre campo] AS VALOR \nFROM [Nombre tabla]");
		atributo.setSqlValida("SELECT [Nombre campo] AS RESPUESTA FROM [Nombre tabla] \nWHERE [Nombre campo a validar]\n = VALOR");
		alta = true;
		esParametro = false;
		boolLongitud = true;
		tiposHtml = new HtmlSelectOneMenu();

		msjValor = "";
		msjGraba = "";
		msjLista = "";
		msjValida = "";
	}


	public String inicializar() {
		// tipoAtributos = cargarListaTipoAtributo();
		atributos = new ArrayList();
		borrar();
		return "";
	}


	public void cambioTipo(ValueChangeEvent event) {
		Long valorSeleccionado = new Long(tiposHtml.getValue().toString());
		switch (valorSeleccionado.intValue()) {
		case 1:
		case 2:
			atributo.setLongitud(new Integer(0));
			boolLongitud = false;
			break;
		case 3:
			atributo.setLongitud(new Integer(0));
			boolLongitud = false;
			break;
		case 4:
			atributo.setLongitud(new Integer(0));
			boolLongitud = false;
			break;
		case 5:
			atributo.setLongitud(new Integer(0));
			boolLongitud = false;
			break;
		case 6:
			atributo.setLongitud(new Integer(5));
			boolLongitud = true;
			break;
		case 7:
			atributo.setLongitud(new Integer(3594));
			boolLongitud = true;
			break;
		case 8:
			atributo.setLongitud(new Integer(11));
			boolLongitud = true;
			break;
		case 9:
			atributo.setLongitud(new Integer(0));
			boolLongitud = false;
			break;
		default:
			atributo.setLongitud(new Integer(0));
			boolLongitud = false;
			break;
		}
	}


	public String verificarSQLValor() {
		String query = atributo.getSqlValor().toUpperCase();
		msjValor = verificarBase(query);
		if (msjValor == null) {
			if (query.indexOf("SELECT") < 0) {
				msjValor = Error.ATRIBUTO_QUERY_SINTAX_SELECT;
				return "";
			}
			if (query.indexOf("AS") < 0) {
				msjValor = Error.ATRIBUTO_QUERY_SINTAX_AS;
				return "";
			}
			if (query.indexOf("FROM") < 0) {
				msjValor = Error.ATRIBUTO_QUERY_SINTAX_FROM;
				return "";
			}
			if (query.indexOf("WHERE") < 0) {
				msjValor = Error.ATRIBUTO_QUERY_SINTAX_WHERE;
				return "";
			}
			msjValor = "PASO!!!";
		}
		return "";
	}


	public String verificarSQLGraba() {
		String query = atributo.getSqlGraba().toUpperCase();
		msjGraba = verificarBase(query);
		if (msjGraba == null) {
			if (query.indexOf("UPDATE") < 0) {
				msjGraba = Error.ATRIBUTO_QUERY_SINTAX_UPDATE;
				return "";
			}
			if (query.indexOf("SET") < 0) {
				msjGraba = Error.ATRIBUTO_QUERY_SINTAX_SET;
				return "";
			}
			if (query.indexOf("WHERE") < 0) {
				msjGraba = Error.ATRIBUTO_QUERY_SINTAX_WHERE;
				return "";
			}
			msjGraba = "PASO!!!";
		}
		return "";
	}


	public String verificarSQLLista() {
		String query = atributo.getSqlLista().toUpperCase();
		msjLista = verificarBase(query);
		if (msjLista == null) {
			if (query.indexOf("ID") < 0) {
				msjLista = Error.ATRIBUTO_QUERY_SINTAX_ID;
				return "";
			}
			if (query.indexOf("SELECT") < 0) {
				msjLista = Error.ATRIBUTO_QUERY_SINTAX_SELECT;
				return "";
			}
			if (query.indexOf("AS") < 0) {
				msjLista = Error.ATRIBUTO_QUERY_SINTAX_AS;
				return "";
			}
			if (query.indexOf("FROM") < 0) {
				msjLista = Error.ATRIBUTO_QUERY_SINTAX_FROM;
				return "";
			}
			msjLista = "PASO!!!";
		}
		return "";
	}


	public String verificarSQLValida() {
		String query = atributo.getSqlValida().toUpperCase();
		msjValida = verificarBase(query);
		if (msjValida == null) {
			if (query.indexOf("SELECT") < 0) {
				msjValida = Error.ATRIBUTO_QUERY_SINTAX_SELECT;
				return "";
			}
			if (query.indexOf("RESPUESTA") < 0) {
				msjValida = Error.ATRIBUTO_QUERY_SINTAX_RESPUESTA;
				return "";
			}
			if (query.indexOf("FROM") < 0) {
				msjValida = Error.ATRIBUTO_QUERY_SINTAX_FROM;
				return "";
			}
			if (query.indexOf("WHERE") < 0) {
				msjValida = Error.ATRIBUTO_QUERY_SINTAX_WHERE;
				return "";
			}
			msjValida = "PASO!!!";
		}
		return "";
	}


	private String verificarBase(String query) {
		if (query.indexOf("[") > 0 || query.indexOf("]") > 0) {
			return Error.ATRIBUTO_QUERY_SINTAX_CORCHETE;
		}
		if (query.indexOf("VALOR") < 0) {
			return Error.ATRIBUTO_QUERY_SINTAX_VALOR;
		}
		return null;
	}


	public String editar(ProcesoAtributo atributo) {
		borrar();
		this.atributo = atributo;
		try {
			List list = workflowService.getIniParametroService().listarIniParametro(new Filtro(
					IniParametro.ID_PROC_ATRIBUTO, Filtro.IGUAL, atributo.getIdProcesoAtributo()));
			if (!list.isEmpty()) {
				IniParametro iniParam = (IniParametro) list.get(0);
				esParametro = true;
				esBuscaValor = iniParam.isBuscaValor();
			}
		} catch (IniParametroException e1) {
			e1.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		tiposHtml.setValue(this.atributo.getTipoAtributo().getIdTipoAtributo());
		alta = false;
		switch (atributo.getTipoAtributo().getIdTipoAtributo().intValue()) {
		case 1:
		case 2:
			boolLongitud = false;
			break;
		case 3:
			boolLongitud = false;
			break;
		case 4:
			boolLongitud = false;
			break;
		case 5:
			boolLongitud = false;
			break;
		case 6:
			boolLongitud = true;
			break;
		case 7:
			boolLongitud = true;
			break;
		case 8:
			boolLongitud = true;
			break;
		case 9:
			boolLongitud = false;
			break;
		default:
			boolLongitud = false;
			break;
		}
		return "";
	}


	public boolean validar() {
		error.borrar();

		if (Validador.esNulo(atributo.getNombre()) || atributo.getNombre().equals("")) {
			error.agregar(Error.ATRIBUTO_NOMBRE_REQUERIDO);
		}

		if (Validador.esNulo(atributo.getDescripcion()) || atributo.getDescripcion().equals("")) {
			error.agregar(Error.ATRIBUTO_DESCRIPCION_REQUERIDO);
		}

		if (atributo.getTipoAtributo().getIdTipoAtributo().equals(new Long(0))) {
			error.agregar(Error.ATRIBUTO_TIPO_REQUERIDO);
		}

		if (Validador.esNulo(atributo.getLongitud()) || atributo.getLongitud().equals(new Integer(0))) {
			error.agregar(Error.ATRIBUTO_LONGITUD_REQUERIDO);
		}

		if ((Validador.esNulo(atributo.getSqlGraba()) || atributo.getSqlGraba().equals("")) &&
				(Validador.esNulo(atributo.getSqlLista()) || atributo.getSqlLista().equals("")) &&
				(Validador.esNulo(atributo.getSqlValor()) || atributo.getSqlValor().equals(""))) {
			if (!atributo.getLocal()) {
				error.agregar(Error.ATRIBUTO_SQL_REQUERIDO);
			}
		}

		if (esParametro && esBuscaValor && !atributo.getLocal()) {
			if ((Validador.esNulo(atributo.getSqlValor()) || atributo.getSqlValor().equals("")))
				error.agregar(Error.ATRIBUTO_SQL_REQUERIDO);
		}

		switch (atributo.getTipoAtributo().getIdTipoAtributo().intValue()) {

		// case 1: case 2:
		// break;

		case 3:
		case 4:
			if (atributo.getValorDefecto().equals("")) {
				error.agregar("En el caso de tipos numericos se debe colocar un valor por defecto.");
			}
			break;

		// case 5:
		// break;
		//
		case 6:
			if (!atributo.getValorDefecto().equals("true") && !atributo.getValorDefecto().equals("false")) {
				error.agregar("En el caso de tipos logicos se debe colocar un valor por defecto. (\"true\"/\"false\")");
			}
			break;

		// case 7: valorFecha = new Date(atributo.getValorDefecto());
		// break;
		}

		return (error.cantidad() == 0) ? true : false;
	}


	public String grabarAtributo() {
		try {
			if (validar()) {
				ProcesoBean procesoBean = (ProcesoBean) Session.getBean("ProcesoBean");
				if (atributo.getLocal()) {
					atributo.setValida(false);
					atributo.setValor(false);
					atributo.setLista(false);
					atributo.setGraba(false);
				}
				if (alta) {
					log.info("Grabando el atributo ->" + atributo);
					workflowService.getProcesoAtributoService().grabarProcesoAtributo(atributo);
					procesoBean.getProceso().getAtributos().add(atributo);
				} else {
					log.info("Actualizando el atributo ->" + atributo);
					workflowService.getProcesoAtributoService().actualizarProcesoAtributo(atributo);
					procesoBean.getProceso().getAtributos().remove(atributo);
					procesoBean.getProceso().getAtributos().add(atributo);
				}
				try {
					IniParametroService parametroService = workflowService.getIniParametroService();
					IniParametro parametro = new IniParametro();
					if (alta)
					{
						if (esParametro) {// Si es seleccionado como parametro lo grabo
							parametro = new IniParametro();
							parametro.setIdProcAtributo(atributo.getIdProcesoAtributo());
							parametro.setIdProceso(atributo.getProceso().getIdProceso());
							parametro.setNombre(atributo.getNombre());
							parametro.setBuscaValor(esBuscaValor && !atributo.getLocal());
							parametroService.grabarIniParametro(parametro);
							procesoBean.getProceso().getParametros().add(parametro);
							log.info("Grabando el parametro ->" + parametro);
						}
					}
					else {
						// Siempre borro los parametros
						List paramList = parametroService.listarIniParametro(new Filtro(IniParametro.ID_PROC_ATRIBUTO, Filtro.IGUAL, atributo
								.getIdProcesoAtributo()));
						if (!paramList.isEmpty()) {
							parametro = (IniParametro) paramList.get(0);
							parametro.setBuscaValor(esBuscaValor && !atributo.getLocal());
							parametro.setNombre(atributo.getNombre());
							parametroService.actualizarIniParametro(parametro);
							// parametroService.borrarIniParametro(parametro.getIdIniParametro());
							// procesoBean.getProceso().getParametros().remove(parametro);
							// log.info("Borrando el parametro ->" + parametro);
						}
					}
				} catch (IniParametroException e1) {
					e1.printStackTrace();
				} catch (Exception e) {
					e.printStackTrace();
				}
				borrar();
			} else {
				ScrollBean scrollBean = (ScrollBean) Session.getBean("ScrollBean");
				scrollBean.borrar();
			}
		} catch (ProcesoAtributoException e1) {
			e1.printStackTrace();
		} catch (Exception e3) {
			log.info("No se pudo grabar el atributo");
			e3.printStackTrace();
		}

		return "";
	}


	public void recargarYCerrarPopup(ActionEvent event) {
		if (validar()) {
			FacesContext facesContext = FacesContext.getCurrentInstance();
			String javaScriptText = "window.opener.recargar();window.close();";
			AddResource addResource = AddResourceFactory.getInstance(facesContext);
			addResource.addInlineScriptAtPosition(facesContext, AddResource.HEADER_BEGIN, javaScriptText);
		}
	}


	public String cancelar() {
		borrar();
		return null;
	}


	public List cargarListaTipoAtributo() {
		List result = new ArrayList();
		result.add(Util.primerSelectItem("Seleccione"));
		List tipoAtributoList = null;

		try {
			tipoAtributoList = workflowService.getTipoAtributoService().listarTipoAtributo(new Filtro());
		} catch (TipoAtributoException e) {
			e.printStackTrace();
			return null;
		}

		if (!tipoAtributoList.isEmpty()) {
			Iterator iterTipos = tipoAtributoList.iterator();
			while (iterTipos.hasNext()) {
				SelectItem item = new SelectItem();
				TipoAtributo aux = (TipoAtributo) iterTipos.next();
				item.setValue(aux.getIdTipoAtributo());
				item.setLabel(aux.getDescripcion());
				result.add(item);
			}
		}

		return result;
	}

}
