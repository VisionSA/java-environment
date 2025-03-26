package com.bizitglobal.webapp.faces.beans.workflow;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.faces.context.FacesContext;

import org.apache.log4j.Logger;
import org.apache.myfaces.renderkit.html.util.AddResource;
import org.apache.myfaces.renderkit.html.util.AddResourceFactory;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.webapp.faces.beans.BaseBean;
import com.bizitglobal.webapp.faces.beans.util.ScrollBean;
import com.bizitglobal.webapp.faces.beans.workflow.wrappers.ArmarCondicion;
import com.bizitglobal.webapp.faces.beans.workflow.wrappers.FlujoTabla;
import com.bizitglobal.webapp.faces.util.Error;
import com.bizitglobal.webapp.faces.util.Session;
import com.bizitglobal.workflow.exception.AtributoProcesoTareaException;
import com.bizitglobal.workflow.exception.FlujoCondicionException;
import com.bizitglobal.workflow.exception.FlujoException;
import com.bizitglobal.workflow.exception.OperadorCondicionException;
import com.bizitglobal.workflow.exception.ProcesoAtributoException;
import com.bizitglobal.workflow.exception.TareaException;
import com.bizitglobal.workflow.exception.TareaProcesoException;
import com.bizitglobal.workflow.exception.TipoCondicionException;
import com.bizitglobal.workflow.negocio.AtributoProcesoTarea;
import com.bizitglobal.workflow.negocio.Flujo;
import com.bizitglobal.workflow.negocio.FlujoCondicion;
import com.bizitglobal.workflow.negocio.Tarea;
import com.bizitglobal.workflow.negocio.TareaProceso;
import com.bizitglobal.workflow.service.FlujoCondicionService;
import com.bizitglobal.workflow.service.ProcesoAtributoService;


@SuppressWarnings({"rawtypes","unchecked"})
public class FlujosBean extends BaseBean {

	private static final Logger log = Logger.getLogger(FlujosBean.class);

	// Lista de flujos, condiciones y tareas.
	private List flujosList;
	private List tareaFlujosList;
	private List tipoCondicionesList;
	private List tareaProcesoList;
	private List tareaSelect;
	private List atributosList;
	private List condicionList;
	private List tipoUnionList;
	private List flujosCondicionesList;
	private List parentesisInicioList;
	private List parentesisFinalList;
	private List auxCondicionList;
	private TareaProceso tareaProceso;
	private Flujo flujo;
	private boolean flujoRetorno;
	private Tarea tarea;
	private ArmarCondicion armarCondicion;

	// Nombre de Titulos para la presentacion.
	private String tituloLargo = "Administración de Tramites - Alta de Flujos y Condiciones";
	private String tituloCorto = "Alta de flujos";
	private String tituloTarea;
	private String tituloTareaDestino;
	private String descripcion;
	private Long idTareaSelect;
	private Long idFlujoAux;
	private Long idTipoCondicionesSelect;
	private Long id;
	private boolean opcionA;
	private boolean opcionB;
	private boolean opcionC;


	public FlujosBean() {
		super();
	}


	public void limpiarBuleanos() {
		opcionA = false;
		opcionB = false;
		opcionC = false;
	}


	public boolean getFlujoRetorno() {
		return flujoRetorno;
	}


	public void setFlujoRetorno(boolean flujoRetorno) {
		this.flujoRetorno = flujoRetorno;
	}


	public List getTipoCondicionesList() {
		return tipoCondicionesList;
	}


	public void setTipoCondicionesList(List tipoCondicionesList) {
		this.tipoCondicionesList = tipoCondicionesList;
	}


	public List getFlujosList() {
		return flujosList;
	}


	public void setFlujosList(List flujosList) {
		this.flujosList = flujosList;
	}


	public List getTareaProcesoList() {
		return tareaProcesoList;
	}


	public void setTareaProcesoList(List tareasList) {
		this.tareaProcesoList = tareasList;
	}


	public TareaProceso getTareaProceso() {
		return tareaProceso;
	}


	public void setTareaProceso(TareaProceso tareaProceso) {
		this.tareaProceso = tareaProceso;
	}


	public List getTareaSelect() {
		return tareaSelect;
	}


	public void setTareaSelect(List tareaSelect) {
		this.tareaSelect = tareaSelect;
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


	public Flujo getFlujo() {
		return flujo;
	}


	public void setFlujo(Flujo flujo) {
		this.flujo = flujo;
	}


	public String getTituloTarea() {
		return tituloTarea;
	}


	public void setTituloTarea(String titulotarea) {
		this.tituloTarea = titulotarea;
	}


	public Tarea getTarea() {
		return tarea;
	}


	public void setTarea(Tarea tarea) {
		this.tarea = tarea;
	}


	public Long getIdTareaSelect() {
		return idTareaSelect;
	}


	public void setIdTareaSelect(Long idTareaSelect) {
		this.idTareaSelect = idTareaSelect;
	}


	public String getDescripcion() {
		return flujo.getDescripcion();
	}


	public void setDescripcion(String descripcion) {
		flujo.setDescripcion(descripcion);
	}


	public List getTareaFlujosList() {
		return tareaFlujosList;
	}


	public void setTareaFlujosList(List tareaFlujosList) {
		this.tareaFlujosList = tareaFlujosList;
	}


	public List getAtributosList() {
		return atributosList;
	}


	public void setAtributosList(List atributosList) {
		this.atributosList = atributosList;
	}


	public List getTipoUnionList() {
		return tipoUnionList;
	}


	public void setTipoUnionList(List tipoUnionList) {
		this.tipoUnionList = tipoUnionList;
	}


	public List getCondicionList() {
		return condicionList;
	}


	public void setCondicionList(List condicionList) {
		this.condicionList = condicionList;
	}


	public Long getIdFlujoAux() {
		return idFlujoAux;
	}


	public void setIdFlujoAux(Long idFlujoAux) {
		this.idFlujoAux = idFlujoAux;
	}


	public List getFlujosCondicionesList() {
		return flujosCondicionesList;
	}


	public void setFlujosCondicionesList(List flujosCondicionesList) {
		this.flujosCondicionesList = flujosCondicionesList;
	}


	public List getParentesisFinalList() {
		return parentesisFinalList;
	}


	public void setParentesisFinalList(List parentesisFinalList) {
		this.parentesisFinalList = parentesisFinalList;
	}


	public List getParentesisInicioList() {
		return parentesisInicioList;
	}


	public void setParentesisInicioList(List parentesisInicioList) {
		this.parentesisInicioList = parentesisInicioList;
	}


	public List getAuxCondicionList() {
		return auxCondicionList;
	}


	public void setAuxCondicionList(List auxCondicionList) {
		this.auxCondicionList = auxCondicionList;
	}


	public ArmarCondicion getCondicionTabla() {
		return armarCondicion;
	}


	public void setCondicionTabla(ArmarCondicion armarCondicion) {
		this.armarCondicion = armarCondicion;
	}


	public Long getIdTipoCondicionesSelect() {
		return idTipoCondicionesSelect;
	}


	public void setIdTipoCondicionesSelect(Long idTipoCondicionesSelect) {
		this.idTipoCondicionesSelect = idTipoCondicionesSelect;
	}


	public boolean isOpcionA() {
		return opcionA;
	}


	public void setOpcionA(boolean opcionA) {
		this.opcionA = opcionA;
	}


	public boolean isOpcionB() {
		return opcionB;
	}


	public void setOpcionB(boolean opcionB) {
		this.opcionB = opcionB;
	}


	public boolean isOpcionC() {
		return opcionC;
	}


	public void setOpcionC(boolean opcionC) {
		this.opcionC = opcionC;
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bizitglobal.webapp.faces.beans.BaseBean#borrar()
	 */
	public void borrar() {
		error.borrar();
		super.borrarBaseBean();
		limpiarBuleanos();

		flujosList = new ArrayList();
		tareaFlujosList = new ArrayList();
		tipoCondicionesList = new ArrayList();
		tareaProcesoList = new ArrayList();
		atributosList = new ArrayList();
		tareaProceso = new TareaProceso();
		tipoUnionList = new ArrayList();
		condicionList = new ArrayList();
		flujosCondicionesList = new ArrayList();
		parentesisInicioList = new ArrayList();
		parentesisFinalList = new ArrayList();
		auxCondicionList = new ArrayList();
		tarea = null;
		flujo = new Flujo();
		descripcion = "";
		idTareaSelect = new Long(0);
		flujoRetorno = false;
		idTipoCondicionesSelect = new Long(0);
		idFlujoAux = new Long(0);
		id = new Long(0);
		tituloLargo = "Anexar Condiciones";
	}


	/*
	 * este metodo se utiliza para trabajar sobre el proceso sobre el cual se decea crear los flujos y condiciones.
	 */
	public String inicializar(TareaProceso tProceso) {
		borrar();
		tareaProceso = tProceso;

		// Aca filtramos todas las tareas procesos teniendo en cuenta el Id del proceso que traemos.
		try {
			Filtro filtro = new Filtro(TareaProceso.ID_PROCESO, Filtro.IGUAL, tareaProceso.getIdProceso());
			tareaProcesoList = workflowService.getTareaProcesoService().listarTareaProceso(filtro);

		} catch (TareaProcesoException e) {

			e.printStackTrace();
		}

		// Aca buscamo la tarea relacionada al proceso para optener su nombre.
		try {
			tarea = workflowService.getTareaService().leerTarea(tareaProceso.getIdTarea());
			/*
			 * No olvidarse de quitar + "Id Tarea Proceso: " + tProceso.getIdTareaProceso()de abajo
			 */
			tituloTarea = tarea.getTitulo();
		} catch (TareaException e) {

			e.printStackTrace();
		}
		// Aca combertimos las tareas en flujos tablas para luego poder utilizarlas en la tabla de flujos tablas.
		try {
			Filtro filtro = new Filtro(Flujo.ID_TAREA_PROC_ORIGEN, Filtro.IGUAL, tareaProceso.getIdTareaProceso());
			flujosList = workflowService.getFlujoService().listarFlujo(filtro);
			if (!flujosList.isEmpty()) {
				Iterator flujoIter = flujosList.iterator();

				while (flujoIter.hasNext()) {
					Flujo flujo = (Flujo) flujoIter.next();

					armarFlujosTarea(flujo);
				}
			}
		} catch (FlujoException e) {
			e.printStackTrace();
		}
		/*
		 * Se utiliza para traer todos los procesos atributos relacionados con el id del tarea_proceso origen utilizado en el flujo. Con este Id.
		 * creamos un filtro para obtener la listas de todos los atributos_procesos_tareas que esten relacionado con el Id aterior. Una vez optenida
		 * dicha lista se pasa por parametros a un metodo de la clase Flujos util, el cual nos devolvera todas las tareas relacionadas a la
		 * tarea_proceso_origen.
		 */

		try {
			ProcesoAtributoService procesoAtributoService = workflowService.getProcesoAtributoService();
			Filtro filtro = new Filtro(AtributoProcesoTarea.ID_TAREA_PROCESO, Filtro.IGUAL, tareaProceso.getIdTareaProceso());
			List atribProcTarList = workflowService.getAtributoProcesoTareaService().listarAtributoProcesoTarea(filtro);
			log.info("Tamaño Lista Atributo_Proceso_Tarea: " + atribProcTarList.size());
			if (!atribProcTarList.isEmpty()) {
				atributosList = FlujosUtil.traerAtributos(procesoAtributoService, atribProcTarList);
			}
		} catch (AtributoProcesoTareaException e) {
			e.printStackTrace();
		}

		try {
			tipoCondicionesList = FlujosUtil.cargarListaTiposCondiciones(workflowService.getTipoCondicionService().listarTipoCondicion(new Filtro()));
		} catch (TipoCondicionException e) {
			e.printStackTrace();
		}

		// Se utiliza para traer todas las uniones.
		try {
			tipoUnionList = workflowService.getTipoCondicionService().listarTipoUnion();
			List aux = workflowService.getOperadorCondicionService().listarOperadoresCondicion(new Filtro());
			log.info("Tamaño Lista Condiciones: " + aux.size());
			condicionList = FlujosUtil.cargarCondiciones(aux);
		} catch (TipoCondicionException e) {
			e.printStackTrace();
		} catch (OperadorCondicionException e) {
			e.printStackTrace();
		}

		/*
		 * Aca se arman las listas de los parentesis de inico y final de codicion.
		 */
		try {
			parentesisInicioList = FlujosUtil.traerParetesis(workflowService.getFlujoCondicionService().devolverParentesisInicial());
			parentesisFinalList = FlujosUtil.traerParetesis(workflowService.getFlujoCondicionService().devolverParentesisFinal());
		} catch (FlujoCondicionException e) {
			e.printStackTrace();
		}

		tareaSelect = FlujosUtil.cargarListaTareas(workflowService.getTareaService(), tareaProcesoList, tituloTarea);
		Session.redirect("/workflow/flujos/amFlujos.jsf");
		return null;
	}


	public String inicializar() {
		return null;
	}


	/*
	 * este metodo se utiliza para validar los datos antes de que se grabe un flujo.
	 */
	public boolean validar() {
		error.borrar();
		if (getIdTareaSelect().equals(new Long(0)) || getIdTareaSelect() == null) {
			error.agregar(Error.ATRIBUTO_TAREA_REQUERIDO);
		}

		try {
			Filtro filtro = new Filtro();
			filtro.agregarCampoOperValor(Flujo.ID_TAREA_PROC_ORIGEN, Filtro.IGUAL, tareaProceso.getIdTareaProceso());
			filtro.agregarCampoOperValor(Flujo.ID_TAREA_PROC_DESTINO, Filtro.IGUAL, getIdTareaSelect());

			List auxVerifList = workflowService.getFlujoService().listarFlujo(filtro);
			if (!auxVerifList.isEmpty()) {
				error.agregar(Error.FLUJO_DUPLICADO);
			}
		} catch (FlujoException e) {

			e.printStackTrace();
		}

		return (error.cantidad() == 0) ? true : false;
	}


	/*
	 * Este metodo se utiliza para grabar un flujo, se valida q el mismo no esta ya grabado.
	 */
	public String grabarFlujo() {
		log.info("Ejecutando --> grabarFlujoCondicion()");
		error.borrar();
		if (validar()) {
			try {
				flujo.setIdFlujo(null);
				flujo.setIdTareaProcesoDestin(getIdTareaSelect());
				flujo.setIdTareaProcesoOrigen(tareaProceso.getIdTareaProceso());
				flujo.setRetorno(flujoRetorno);
				workflowService.getFlujoService().grabarFlujo(flujo);
				armarFlujosTarea(flujo);
				idTareaSelect = new Long(0);
				flujoRetorno = false;
			} catch (FlujoException e) {
				e.printStackTrace();
			}
		}
		else {
			ScrollBean scrollBean = (ScrollBean) Session.getBean("ScrollBean");
			scrollBean.borrar();
		}
		// inicializar(tareaProceso);
		descripcion = "";
		String path = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
		path += "/workflow/popups/amFlujos.jsf";
		return null;
	}


	/*
	 * este metodo se utiliza para amar el datList de flujos dentro de la pagina de flujos.
	 */
	public void armarFlujosTarea(Flujo flujo) {
		Tarea auxTarea = new Tarea();
		TareaProceso auxTareaProceso = new TareaProceso();
		if (flujo != null) {
			try {
				FlujoTabla flujosTabla = new FlujoTabla();

				flujosTabla.setTituloTareaOrigen(tarea.getTitulo());

				auxTareaProceso = workflowService.getTareaProcesoService().leerTareaProceso(flujo.getIdTareaProcesoDestin());
				auxTarea = workflowService.getTareaService().leerTarea(auxTareaProceso.getIdTarea());
				flujosTabla.setTituloTareaDestino(auxTarea.getTitulo());

				flujosTabla.setDescripcionFlujo(flujo.getDescripcion());

				flujosTabla.setFlujo(flujo);
				tareaFlujosList.add(flujosTabla);

			} catch (TareaProcesoException e) {
				e.printStackTrace();
			} catch (TareaException e) {
				e.printStackTrace();
			}
		}
	}


	/*
	 * Este metodo se utiliza cuando se ingresa al popup de agragar condiciones aqui se levantan todas las condiciones que estan en la base de datos y
	 * estas estan listas para poder ser mofificadas al mismo tiempo que se pueden ir creando nuevas condiciones para un flujo.
	 */
	public String irACondiciones() {
		log.info("Ejecutando --> irACondiciones()");
		List aux = new ArrayList();
		idFlujoAux = new Long(Session.getRequestParameter("idFlujoTabla"));
		armarTituloDestino(idFlujoAux);
		auxCondicionList = new ArrayList();

		try {
			setIdTipoCondicionesSelect(new Long(0));
			Filtro filtro = new Filtro(FlujoCondicion.ID_FLUJO, Filtro.IGUAL, idFlujoAux);
			aux = workflowService.getFlujoCondicionService().listarFlujoCondicion(filtro);

			if (!aux.isEmpty()) {

				Iterator auxIterator = aux.iterator();
				while (auxIterator.hasNext()) {
					FlujoCondicion condicion = (FlujoCondicion) auxIterator.next();
					armarCondicion = new ArmarCondicion();

					armarCondicion.setTipoCondicion(workflowService.getTipoCondicionService().leerTipoCondicion(
							condicion.getTipoCondicion().getIdTipoCondicion()));
					armarCondicion.getFlujoCondicion().setFlujo(workflowService.getFlujoService().leerFlujo(condicion.getFlujo().getIdFlujo()));
					armarCondicion.getFlujoCondicion().setProcesoAtributo(
							workflowService.getProcesoAtributoService().leerProcesoAtributo(condicion.getProcesoAtributo().getIdProcesoAtributo()));

					log.info("id atributo dos: " + condicion.getProcesoAtributoDos().getIdProcesoAtributo());
					if (!condicion.getProcesoAtributoDos().getIdProcesoAtributo().equals(new Long(0))) {
						armarCondicion.getFlujoCondicion().setProcesoAtributoDos(
								workflowService.getProcesoAtributoService().leerProcesoAtributo(
										condicion.getProcesoAtributoDos().getIdProcesoAtributo()));
					}

					armarCondicion.setIdTipoCondicionesSelect(condicion.getTipoCondicion().getIdTipoCondicion());
					armarCondicion.setFlujoCondicion(condicion);
					id = new Long(auxCondicionList.size() + 1);
					armarCondicion.setId(id);
					auxCondicionList.add(armarCondicion);
				}
			}
		} catch (FlujoCondicionException e) {
			e.printStackTrace();
		} catch (TipoCondicionException e) {
			e.printStackTrace();
		} catch (FlujoException e) {
			e.printStackTrace();
		} catch (ProcesoAtributoException e) {
			e.printStackTrace();
		}

		String path = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
		path += "/workflow/popups/anexarCondicionesPopup.jsf";
		ejecutarJavaScript("popup('" + path + "',800,400), 'titlebar=no';");

		idTipoCondicionesSelect = new Long(0);
		return null;
	}


	private void armarTituloDestino(Long id) {
		if (!tareaFlujosList.isEmpty()) {
			Iterator iterator = tareaFlujosList.iterator();
			while (iterator.hasNext()) {
				FlujoTabla element = (FlujoTabla) iterator.next();
				if (element.getIdFlujo().equals(id))
					tituloTareaDestino = element.getTituloTareaDestino();
			}
		}
	}


	/*
	 * este metodo se utiliza para eliminar un flujo sin importar que este tenga condiciones relacionadas. las condiciones tambien son eliminadas para
	 * mantener la integridad referencial.
	 */
	public String eliminarFlujo() {
		log.info("Ejecutando --> Eliminar Flujo.");
		Long idFlujoTab = new Long(Session.getRequestParameter("idFlujoTabla"));
		FlujoCondicionService flujoCondicionService = workflowService.getFlujoCondicionService();

		try {
			log.info("id Flujo tabla seleccionado: " + idFlujoTab);
			Filtro filtro = new Filtro(Flujo.ID_FLUJO, Filtro.IGUAL, idFlujoTab);
			List auxCondcionesLis = flujoCondicionService.listarFlujoCondicion(filtro);

			if (!auxCondcionesLis.isEmpty()) {
				Iterator condicionIter = auxCondcionesLis.iterator();
				while (condicionIter.hasNext()) {
					FlujoCondicion auxFlujoCondicion = (FlujoCondicion) condicionIter.next();
					flujoCondicionService.borrarFlujoCondicion(auxFlujoCondicion.getIdFlujoCondicion());
				}
			}

			workflowService.getFlujoService().borrarFlujo(idFlujoTab);
			inicializar(tareaProceso);
		} catch (FlujoCondicionException e) {
			e.printStackTrace();
		} catch (FlujoException e) {
			e.printStackTrace();
		}
		return null;
	}


	/*
	 * este metodo se utiliza para agragar una nueva condicion a un flujo.
	 */
	public String agregarNuevaCondicion() {
		if (idTipoCondicionesSelect.equals(new Long(0))) {
			error.agregar(Error.TIPO_CONDICION_REQUERIDA);
		}
		else {
			try {
				armarCondicion = new ArmarCondicion();
				armarCondicion.setIdTipoCondicionesSelect(idTipoCondicionesSelect);
				armarCondicion.setTipoCondicion(workflowService.getTipoCondicionService().leerTipoCondicion(idTipoCondicionesSelect));
				auxCondicionList.add(armarCondicion);
				if (armarCondicion.getId() == null || armarCondicion.getId().equals(new Long(0))) {
					id = new Long(auxCondicionList.size() + 1);
					armarCondicion.setId(id);
					log.info("id wrappers: " + id);
				}
				ejecutarJavaScript("recargar();");
			} catch (TipoCondicionException e) {
				e.printStackTrace();
			}
		}
		idTipoCondicionesSelect = new Long(0);
		return null;
	}


	/*
	 * este metodo se utiliza para asegurarse que se ingresen todos los datos deceados y que o queen campos en blanco a al momento de querer grabar
	 * una condicion.
	 */
	public boolean validarCondicon() {
		error.borrar();
		int acumParenIni = 0;
		int acumParenFin = 0;
		Iterator listaIterator = auxCondicionList.iterator();
		while (listaIterator.hasNext()) {
			ArmarCondicion condicionTabla = (ArmarCondicion) listaIterator.next();
			acumParenIni += condicionTabla.getParentesisInicial().intValue();
			acumParenFin += condicionTabla.getParentesisFinal().intValue();
			/*
			 * aqui se verifica que se haya seleccionado un atributo de la lista de atributos.
			 */
			if (condicionTabla.getIdAtributoSelect().equals(new Long(0)))
				error.agregar(Error.ATRIBUTO_REQUERIDO);

			/*
			 * aqui verificamos que se haya ingresado un valor
			 */
			if (condicionTabla.getTipoCondicion().getIdTipoCondicion().equals(new Long(1))) {
				if (condicionTabla.getValor() == null || condicionTabla.getValor().equals(new String(""))) {
					error.agregar(Error.VALOR_POR_DEFECTO_REQUERIDA);
				}
			}
			/*
			 * aqui se verifica que se haya seleccionado un atributo en el segunda lista de atributos en el tipo condicion Atributo - Atributo.
			 */
			if (condicionTabla.getTipoCondicion().getIdTipoCondicion().equals(new Long(2))) {
				if (condicionTabla.getValorAtributoDos().equals(new Long(0))) {
					error.agregar(Error.ATRIBUTO_REQUERIDO);
				}
			}

			if (condicionTabla.getIdCondicionSelect() == null || condicionTabla.getIdCondicionSelect().equals(new Long(0)))
				error.agregar(Error.OPERADOR_CONDICION_REQUERIDA);

			if (condicionTabla.getOpcionAtributoAtributo() && condicionTabla.getIdAtributoDosSelect().equals(new Long(0)))
				error.agregar(Error.ATRIBUTO_DOS_REQUERIDO);
		}
		/*
		 * Este metodo valida la paridad de los parentesis.
		 */
		if (acumParenIni != acumParenFin) {
			error.agregar(Error.PARIDAD_PARENTESIS_REQUERIDA);
		}
		return (error.cantidad() == 0) ? true : false;
	}


	/*
	 * este metodo se utiliza borra antes de grabar.
	 */
	public void borrarCondiciones(Filtro filtro) {
		try {
			workflowService.getFlujoCondicionService().borrarTodos(filtro);
		} catch (FlujoCondicionException e) {
			e.printStackTrace();
		}
	}


	/*
	 * este metodo se utiliza para poder garabar los procesos.
	 */
	public String grabarCondicion() {
		if (!auxCondicionList.isEmpty()) {
			if (validarCondicon()) {
				Filtro filtro = new Filtro(FlujoCondicion.ID_FLUJO, Filtro.IGUAL, idFlujoAux);
				borrarCondiciones(filtro);

				Iterator condTabIter = auxCondicionList.iterator();
				while (condTabIter.hasNext()) {
					FlujoCondicion condicion = new FlujoCondicion();
					ArmarCondicion condicionTabla = (ArmarCondicion) condTabIter.next();

					try {
						/*
						 * aqui se verifica que el id del flujo no sea cero para asegurarnos de que se haya cargado el flujo cuando se selecciono.
						 */
						if (!idFlujoAux.equals(new Long(0))) {
							condicion.setFlujo(workflowService.getFlujoService().leerFlujo(idFlujoAux));
						}
						else {
							// Verificacion interna. Hace referencia al idFlujoAux.
							error.agregar(Error.FLUJO_ID_MAL_CARGADO);
						}
						condicion.setTipoCondicion(condicionTabla.getTipoCondicion());
						condicion.setTipoUnion(workflowService.getTipoCondicionService().traerTipoUnion(condicionTabla.getIdTipoUnionSelect()));
						log.info("Atributo Uno: " + condicion.getProcesoAtributo().getIdProcesoAtributo());
						condicion.setProcesoAtributo(workflowService.getProcesoAtributoService().leerProcesoAtributo(
								condicionTabla.getIdAtributoSelect()));
						if (condicionTabla.getIdCondicionSelect() != null && !condicionTabla.getIdCondicionSelect().equals(new Long(0))) {
							condicion.getOperadorCondicion().setIdOperadorCondicion(condicionTabla.getIdCondicionSelect());
							log.info("Id Condición Seleccionada: " + condicionTabla.getIdCondicionSelect());
						}

						if (condicionTabla.getTipoCondicion().getIdTipoCondicion().equals(new Long(1)))
							condicion.setValor(condicionTabla.getValor());

						if (condicionTabla.getTipoCondicion().getIdTipoCondicion().equals(new Long(2))) {
							log.info("Atributo Dos: " + condicion.getProcesoAtributoDos().getIdProcesoAtributo());
							condicion.setProcesoAtributoDos(workflowService.getProcesoAtributoService().leerProcesoAtributo(
									condicionTabla.getValorAtributoDos()));
						}

						if (condicionTabla.getOpcionAtributoAtributo())
							condicion.setProcesoAtributoDos(workflowService.getProcesoAtributoService().leerProcesoAtributo(
									condicionTabla.getIdAtributoDosSelect()));

						condicion.setParentesisInicio(condicionTabla.getParentesisInicial());
						condicion.setParentesisFinal(condicionTabla.getParentesisFinal());
						workflowService.getFlujoCondicionService().grabarFlujoCondicion(condicion);
						FacesContext facesContext = FacesContext.getCurrentInstance();
						String javaScriptText = "window.close();";
						AddResource addResource = AddResourceFactory.getInstance(facesContext);
						addResource.addInlineScriptAtPosition(facesContext, AddResource.HEADER_BEGIN, javaScriptText);
					} catch (TipoCondicionException e) {
						e.printStackTrace();
					} catch (ProcesoAtributoException e) {
						e.printStackTrace();
					} catch (FlujoException e) {
						e.printStackTrace();
					} catch (FlujoCondicionException e) {
						e.printStackTrace();
					}
				}
			}
		}
		else {
			Filtro filtro = new Filtro(FlujoCondicion.ID_FLUJO, Filtro.IGUAL, idFlujoAux);
			borrarCondiciones(filtro);

			FacesContext facesContext = FacesContext.getCurrentInstance();
			String javaScriptText = "window.close();";
			AddResource addResource = AddResourceFactory.getInstance(facesContext);
			addResource.addInlineScriptAtPosition(facesContext, AddResource.HEADER_BEGIN, javaScriptText);
		}

		return null;
	}


	public String volerAFlujo() {
		log.info("Tamañ lista condiciones antes: " + auxCondicionList.size());
		auxCondicionList = new ArrayList();
		log.info("Tamañ lista condiciones despues: " + auxCondicionList.size());
		idTipoCondicionesSelect = new Long(0);
		return null;
	}


	/*
	 * este metodo se utiliza para mostrar las condiciones asociadas a un flujo desde la pagina de flujos.
	 */
	public String mostrarCondicion() {
		flujosCondicionesList = new ArrayList();
		List resulFlujoCondicionList = new ArrayList();

		idFlujoAux = new Long(Session.getRequestParameter("idFlujoTabla"));
		// log.info("Valor IdFlujo Selccionado: " + idFlujoAux);
		try {
			Filtro filtro = new Filtro(FlujoCondicion.ID_FLUJO, Filtro.IGUAL, idFlujoAux);
			resulFlujoCondicionList = workflowService.getFlujoCondicionService().listarFlujoCondicion(filtro);

			if (!resulFlujoCondicionList.isEmpty()) {
				Iterator flujoCondicionIter = resulFlujoCondicionList.iterator();
				while (flujoCondicionIter.hasNext()) {
					FlujoCondicion condicion = (FlujoCondicion) flujoCondicionIter.next();
					condicion.setOperadorCondicion(workflowService.getOperadorCondicionService().leerOperadorCondicion(
							condicion.getOperadorCondicion().getIdOperadorCondicion()));
					armarCondicion = new ArmarCondicion();
					condicion.setTipoCondicion(workflowService.getTipoCondicionService().leerTipoCondicion(
							condicion.getTipoCondicion().getIdTipoCondicion()));
					armarCondicion.setTipoCondicion(condicion.getTipoCondicion());
					armarCondicion.setParentesisInicial(condicion.getParentesisInicio());
					condicion.setProcesoAtributo(workflowService.getProcesoAtributoService().leerProcesoAtributo(
							condicion.getProcesoAtributo().getIdProcesoAtributo()));
					armarCondicion.getFlujoCondicion().setProcesoAtributo(condicion.getProcesoAtributo());
					if (!condicion.getProcesoAtributoDos().getIdProcesoAtributo().equals(new Long(0))) {
						condicion.setProcesoAtributoDos(workflowService.getProcesoAtributoService().leerProcesoAtributo(
								condicion.getProcesoAtributoDos().getIdProcesoAtributo()));
						armarCondicion.getFlujoCondicion().setProcesoAtributoDos(condicion.getProcesoAtributoDos());
					}
					if (condicion.getValor() != null || condicion.getValor().equals(new String(""))) {
						armarCondicion.setValor(condicion.getValor());
					}
					armarCondicion.setParentesisFinal(condicion.getParentesisFinal());
					armarCondicion.setTipoUnion(condicion.getTipoUnion());
					armarCondicion.setFlujoCondicion(condicion);
					flujosCondicionesList.add(armarCondicion);
				}
			}
		} catch (FlujoCondicionException e) {
			e.printStackTrace();
		} catch (TipoCondicionException e) {
			e.printStackTrace();
		} catch (ProcesoAtributoException e) {
			e.printStackTrace();
		} catch (OperadorCondicionException e) {
			e.printStackTrace();
		}
		return null;
	}


	/*
	 * este metodo se utiliza dentro del popup para eliminar una condicion. su funcionamiento es el siguiente: se pueden ir creando todas las
	 * condiciones deceadas, esta se almacenan en un List, si decea puede eliminar la condicion lo q la elimina del List, recien quedas plasmados los
	 * cambio en la base al momento de precionar el boton grabar del popup
	 */
	public String eliminarCondicion() {
		log.info("Ejecutando --> eliminarCondicion()");

		Long idCondicion = new Long(Session.getRequestParameter("idCondicion"));

		log.info("Se va a eliminar la condicion con Id: " + idCondicion);

		ArmarCondicion condicion = buscarCondicion(idCondicion);
		if (condicion != null)
			auxCondicionList.remove(condicion);

		FacesContext facesContext = FacesContext.getCurrentInstance();
		String javaScriptText = "window.opener.recargar();";
		AddResource addResource = AddResourceFactory.getInstance(facesContext);
		addResource.addInlineScriptAtPosition(facesContext, AddResource.HEADER_BEGIN, javaScriptText);

		return null;
	}


	private ArmarCondicion buscarCondicion(Long idCondicion) {
		ArmarCondicion condicion = null;

		if (!auxCondicionList.isEmpty()) {
			Iterator iterator = auxCondicionList.iterator();
			while (iterator.hasNext()) {
				ArmarCondicion element = (ArmarCondicion) iterator.next();

				if (element.getId().equals(idCondicion))
					return element;
			}
		}
		return condicion;
	}


	public String cancelar() {
		return "amProcesos";
	}


	public String getTituloTareaDestino() {
		return tituloTareaDestino;
	}


	public void setTituloTareaDestino(String tituloTareaDestino) {
		this.tituloTareaDestino = tituloTareaDestino;
	}
}
