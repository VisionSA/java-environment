package com.bizitglobal.webapp.faces.beans.workflow.wrappers;

import com.bizitglobal.workflow.negocio.Flujo;


public class FlujoTabla {

	private Flujo flujo;
	private String descripcionFlujo;
	private String tituloTareaOrigen;
	private String tituloTareaDestino;


	public FlujoTabla() {

		descripcionFlujo = "";
		tituloTareaOrigen = "";
		tituloTareaDestino = "";
		flujo = new Flujo();

	}


	public String toString() {

		return "Titulo Tarea Origen: " + tituloTareaOrigen +
				", Titulo Tarea Destino: " + tituloTareaDestino +
				", Descripcion: " + descripcionFlujo +
				flujo.toString();
	}


	public String getDescripcionFlujo() {
		return descripcionFlujo;
	}


	public void setDescripcionFlujo(String descripcionFlujo) {
		this.descripcionFlujo = descripcionFlujo;
	}


	public Flujo getFlujo() {
		return flujo;
	}


	public void setFlujo(Flujo flujo) {
		this.flujo = flujo;
	}


	public String getTituloTareaDestino() {
		return tituloTareaDestino;
	}


	public void setTituloTareaDestino(String tituloTareaDestino) {
		this.tituloTareaDestino = tituloTareaDestino;
	}


	public String getTituloTareaOrigen() {
		return tituloTareaOrigen;
	}


	public void setTituloTareaOrigen(String tituloTareaOrigen) {
		this.tituloTareaOrigen = tituloTareaOrigen;
	}


	public void setIdFlujo(Long idFlujo) {
		flujo.setIdFlujo(idFlujo);
	}


	public Long getIdFlujo() {
		return flujo.getIdFlujo();
	}
}