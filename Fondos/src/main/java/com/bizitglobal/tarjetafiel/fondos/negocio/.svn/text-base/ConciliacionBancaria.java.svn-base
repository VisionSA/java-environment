package com.bizitglobal.tarjetafiel.fondos.negocio;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.log4j.Logger;

import com.bizitglobal.tarjetafiel.fondos.exception.AcreditacionFondoException;

public class ConciliacionBancaria {

	private static Logger log = Logger.getLogger(ConciliacionBancaria.class);

	private ArchivoAcreditacion archivoAcreditacion;
	private List listaAcreditacionesRechazadas = new ArrayList();
	private List listaAcreditacionesAceptadas = new ArrayList();
	private List listaAcreditacionesNoConciliadas = new ArrayList();
	private int conciliados, noConciliadas;
	private int itemsProcesados;

	/**
	 * Actualiza como conciliadas las acreditacionesFondoDetalle del archivoAcreditacion, si encuentra conicidencias con la lista de cheques. 
	 * @param cheques: List de cheques almacenados en la base, filtrado segun las fechas que trae el archivo interbanking.
	 * @param archivoAcreditacion: archivoAcreditacion generado a partir del archivo de interbanking.
	 * @return: Un ArchivoAcreditacion con las acreditaciones actualizadas si se encontraron coincidencias con la lista de cheques. 
	 * @throws Exception
	 */
	public ArchivoAcreditacion conciliar(List cheques, ArchivoAcreditacion archivoAcreditacion)throws Exception {
		conciliados=0;
		noConciliadas=0;
		itemsProcesados=0;
		if(cheques!=null)
		{
			Iterator iterItems = archivoAcreditacion.getAcreditacionFondo().getAcreditacionesDetalleOrdenado().iterator();
			while (iterItems.hasNext()) {
				AcreditacionFondoDetalle acreditacionFondoDetalle = (AcreditacionFondoDetalle) iterItems.next();
				if (cheques.contains(acreditacionFondoDetalle)) {
					
					// Conciliar..
					acreditacionFondoDetalle.setConciliado("S");
					conciliados++;
					listaAcreditacionesAceptadas.add(acreditacionFondoDetalle);
	
				} else {
	
					acreditacionFondoDetalle.setConciliado("N");
					noConciliadas++;
					listaAcreditacionesRechazadas.add(acreditacionFondoDetalle);
				}
				
			}
			itemsProcesados = conciliados + noConciliadas;
			
			if (conciliados == archivoAcreditacion.getAcreditacionFondo().getAcreditacionesDetalleOrdenado().size()) {
				archivoAcreditacion.getAcreditacionFondo().setConciliado("S");
			}else{
				archivoAcreditacion.getAcreditacionFondo().setConciliado("N");
			}
			
			if (conciliados + noConciliadas != archivoAcreditacion.getAcreditacionFondo().getAcreditacionesDetalleOrdenado().size()) {
				log.info("NO COINCICIERON ITEMS TRATADOS DEL ARCHIVO DE ACREDITACIONES.");
				throw new AcreditacionFondoException("NO COINCICIERON ITEMS TRATADOS DEL ARCHIVO DE ACREDITACIONES.");
			}
			
			this.archivoAcreditacion = archivoAcreditacion;
			
		}
		
//		if(cheques.size()< archivoAcreditacion.getAcreditacionFondo().getAcreditacionesFondoDetalle().size())
//		{
//			log.info("NO SE ENCONTRARON ACREDITACIONES SUFICIENTES PARA CONCILIAR EN LA BASE DE DATOS.");
//			throw new AcreditacionFondoException("NO SE ENCONTRARON ACREDITACIONES SUFICIENTES PARA CONCILIAR EN LA BASE DE DATOS.");
//		}
		
		return archivoAcreditacion;
	}

	
	public ArchivoAcreditacion getArchivoAcreditacion() {
		return archivoAcreditacion;
	}

	public void setArchivoAcreditacion(ArchivoAcreditacion archivoAcreditacion) {
		this.archivoAcreditacion = archivoAcreditacion;
	}

	public List getListaAcreditacionesRechazadas() {
		return listaAcreditacionesRechazadas;
	}

	public void setListaAcreditacionesRechazadas(List listaAcreditacionesRechazadas) {
		this.listaAcreditacionesRechazadas = listaAcreditacionesRechazadas;
	}

	public List getListaAcreditacionesAceptadas() {
		return listaAcreditacionesAceptadas;
	}

	public void setListaAcreditacionesAceptadas(List listaAcreditacionesAceptadas) {
		this.listaAcreditacionesAceptadas = listaAcreditacionesAceptadas;
	}

	public List getListaAcreditacionesNoConciliadas() {
		return listaAcreditacionesNoConciliadas;
	}

	public void setListaAcreditacionesNoConciliadas(
			List listaAcreditacionesNoConciliadas) {
		this.listaAcreditacionesNoConciliadas = listaAcreditacionesNoConciliadas;
	}

	public int getConciliados() {
		return conciliados;
	}

	public void setConciliados(int conciliados) {
		this.conciliados = conciliados;
	}

	public int getNoConciliadas() {
		return noConciliadas;
	}

	public void setNoConciliadas(int noConciliadas) {
		this.noConciliadas = noConciliadas;
	}


	public int getItemsProcesados() {
		return itemsProcesados;
	}


	public void setItemsProcesados(int itemsProcesados) {
		this.itemsProcesados = itemsProcesados;
	}
	

}
