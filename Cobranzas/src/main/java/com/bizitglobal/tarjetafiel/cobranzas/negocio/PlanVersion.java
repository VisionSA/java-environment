package com.bizitglobal.tarjetafiel.cobranzas.negocio;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.bizitglobal.tarjetafiel.commons.util.SimpleList;

public  class PlanVersion implements Comparable  {
	
	public Integer idPlanVersion;
	public Plan plan;	
	public String descripcion;
	public String queryClientesQueAplican;
	public Date fechaDesde; // fecha que entro
	
	public Set<EtapaVersion>  etapasVersion;
	
	// Una SimpleList, mantiene la misma informacion de la lista etapasVersion, pero de manera ordenada en funcion de compareTo (ya que sus elementos son comparables)
	public SimpleList listaEtapasOrdenada;
	
	// Para que resulte sencillo saber que accion ejecutar a los x dias de iniciado el plan.
	//Esta lista guarda la cantidad de dias totales transcurridos desde el inicio del plan, y una lista de accionesVersion a ejecutar ese dia.
	public HashMap<Integer, List<AccionVersion>> accionesXDia;
	
	
	public PlanVersion() {
         this.etapasVersion = new HashSet<EtapaVersion>();
	}
	
		
	
	public PlanVersion(Integer idPlanVersion, String descripcion, Date fechaDesde,
			String queryClientesQueAplican) {
		this.descripcion = descripcion;
		this.fechaDesde = fechaDesde;
		this.idPlanVersion = idPlanVersion;
		this.queryClientesQueAplican = queryClientesQueAplican;
	}



	public PlanVersion(PlanVersion planVersion) {
		this.descripcion = planVersion.getDescripcion();
		this.fechaDesde = planVersion.getFechaDesde();
		this.queryClientesQueAplican = planVersion.getQueryClientesQueAplican();
		this.plan = planVersion.getPlan();
		this.etapasVersion = new HashSet<EtapaVersion>(); // INICIO UN HASH PARA LAS ETAPAS VERSION
		Iterator<EtapaVersion> iteEtaVer = planVersion.etapasVersion.iterator();
		while (iteEtaVer.hasNext()) { //PARA CADA ETAPAvERSION DEL OBJETO A CLONAR, CREO UNA NUEVA EN EL OBJETO NUEVO CLONADO
			EtapaVersion etaV = iteEtaVer.next();
			EtapaVersion nuevaEtapaVersion = new EtapaVersion();
			nuevaEtapaVersion.descripcion = etaV.getDescripcion();
			nuevaEtapaVersion.dias = etaV.getDias();
			nuevaEtapaVersion.etapa = etaV.getEtapa();
			nuevaEtapaVersion.planVersion = this;
			nuevaEtapaVersion.nombreEtapa = etaV.getNombreEtapa();
			nuevaEtapaVersion.accionesVersion = new HashSet<AccionVersion>();
			this.getEtapasVersion().add(nuevaEtapaVersion);
			Iterator<AccionVersion> iteAccVer = etaV.getAccionesVersion().iterator(); // para cada una de las acciones version, debo clonarlas.
			while (iteAccVer.hasNext()) {
				AccionVersion accV = iteAccVer.next();
				AccionVersion nuevaAccionVersion = new AccionVersion();
				nuevaAccionVersion.accion = accV.getAccion();
				nuevaAccionVersion.conceptoCabecera = accV.getConceptoCabecera();
				nuevaAccionVersion.dias = accV.getDias();
				nuevaAccionVersion.etapaVersion = nuevaEtapaVersion;
				nuevaEtapaVersion.getAccionesVersion().add(nuevaAccionVersion);
			}
		}
	}

	public PlanVersion(String descripcion, Date fechaDesde, Plan plan, Set<EtapaVersion> etapas) {
		super();
		this.descripcion = descripcion;
		this.fechaDesde = fechaDesde;
		this.plan = plan;
		this.etapasVersion = etapas;
		disponerListaEtapasOrdenada();
	}

	/**
	 * Ordena las etapas en funcion del Objeto etapa básico.
	 * */
	private void disponerListaEtapasOrdenada() {
		listaEtapasOrdenada = new SimpleList();
		Iterator<EtapaVersion> iterEtap = etapasVersion.iterator();
		while(iterEtap.hasNext()) {
			 EtapaVersion eta = iterEtap.next();
			 eta.disponerListaAccionesOrdenada();
		     listaEtapasOrdenada.addInOrder(eta);
		}
	}
	
	public void validarConsistenciaPlan() throws Exception {
		//recorre las etapas, verifica que la cantidad de dias definidos para cada etapa incluye las acciones.
		disponerListaEtapasOrdenada();
		List listaErrores = new ArrayList<String>();
		int dias = 0;
		listaEtapasOrdenada.startIterator();
		while (listaEtapasOrdenada.hasNext()) {
			EtapaVersion etaV = (EtapaVersion)listaEtapasOrdenada.next();
			dias += etaV.getDias().intValue();
			SimpleList sl = etaV.getListaAccionesOrdenada();
			sl.startIterator();
			while(sl.hasNext()) {
				AccionVersion acV = (AccionVersion)sl.next();
				if (acV.getDias()<0 || acV.getDias()>etaV.getDias()) listaErrores.add("La actividad " + acV.getAccion().getDescripcion() + " de la etapa " + etaV.getEtapa().getDescripcion() + " no presenta una correcta configuracion de dias.");
			}
		}
		if (descripcion == null || descripcion.compareTo("")==0) listaErrores.add("La versión no presenta una descripción correcta.");
		if (descripcion != null && descripcion.length()>20) listaErrores.add("La descripción de la versión no debe superar los 20 caracteres.");
		if (fechaDesde == null || (fechaDesde.before(new Date()) && idPlanVersion == null)) listaErrores.add("Debe especificar una fecha de vigencia. De ser una version nueva, debe ser un dia posterior al actual");
        if (queryClientesQueAplican == null || queryClientesQueAplican.compareTo("")==0) listaErrores.add("Debe especificar una filtro para clientes para la version");
		if (listaErrores.isEmpty()) return;
		String mensajeDeError = "";
		Iterator iterErro = listaErrores.iterator();
		while (iterErro.hasNext()) {
			mensajeDeError += iterErro.next() + "\n";
		}
		throw new Exception(mensajeDeError);
	}
	
	public void armarHashAccionesPlan() {
		// Se llama al siguiente metodo, que nos refresca nuestra simpleList, para contemplar el caso de que haya cambiado alguna etapa o accion.
		disponerListaEtapasOrdenada();
		int dias = 0;
		accionesXDia = new HashMap<Integer, List<AccionVersion>>();
		listaEtapasOrdenada.startIterator();
		while (listaEtapasOrdenada.hasNext()) {
			EtapaVersion etaV = (EtapaVersion)listaEtapasOrdenada.next();

			SimpleList sl = etaV.getListaAccionesOrdenada();
			sl.startIterator();
			while(sl.hasNext()) {
				AccionVersion acV = (AccionVersion)sl.next();
				Integer clave = new Integer(dias+acV.dias);
				List listaE = accionesXDia.get(clave);
				if (listaE == null) {
					// lo creo
					List<AccionVersion> lista = new ArrayList<AccionVersion>();
					lista.add(acV);
					accionesXDia.put(clave, lista);
				} else {
					// ya existe
					listaE.add(acV);
				}
				
			}
			dias += etaV.getDias().intValue();
		}
	}
	
	public Integer getIdPlanVersion() {
		return idPlanVersion;
	}

	public void setIdPlanVersion(Integer idPlanVersion) {
		this.idPlanVersion = idPlanVersion;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Date getFechaDesde() {
		return fechaDesde;
	}

	public void setFechaDesde(Date fechaDesde) {
		this.fechaDesde = fechaDesde;
	}

	public Plan getPlan() {
		return plan;
	}

	public void setPlan(Plan plan) {
		this.plan = plan;
	}

	public Set<EtapaVersion> getEtapasVersion() {
		return etapasVersion;
	}

	public void setEtapasVersion(Set<EtapaVersion> etapasVersion) {
		this.etapasVersion = etapasVersion;
	}

	public SimpleList getListaEtapasOrdenada() {
		return listaEtapasOrdenada;
	}

	public void setListaEtapasOrdenada(SimpleList listaEtapasOrdenada) {
		this.listaEtapasOrdenada = listaEtapasOrdenada;
	}

	public HashMap<Integer, List<AccionVersion>> getAccionesXDia() {
		return accionesXDia;
	}

	public void setAccionesXDia(HashMap<Integer, List<AccionVersion>> accionesXDia) {
		this.accionesXDia = accionesXDia;
	}

	public String getQueryClientesQueAplican() {
		return queryClientesQueAplican;
	}

	public void setQueryClientesQueAplican(String queryClientesQueAplican) {
		this.queryClientesQueAplican = queryClientesQueAplican;
	}

	@Override
	public int compareTo(Object o) {
		
		int resultado = 0;
		
		int fechas = this.getFechaDesde().compareTo(((PlanVersion)o).getFechaDesde());
		
		if (fechas==0){ // Si la version es del mismo día tomo la de mayor ID
			if (this.getIdPlanVersion()>((PlanVersion)o).getIdPlanVersion()){
				resultado = 1;
			}else {
				resultado = -1;
			}
		}else {
			resultado = fechas;
		}
		return resultado;
	}
  

}
