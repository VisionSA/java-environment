package com.bizitglobal.webapp.faces.beans.transacciones;

import java.util.List;

import org.apache.log4j.Logger;

import com.bizitglobal.webapp.faces.beans.BaseBean;

import  com.bizitglobal.tarjetafiel.transacciones.negocio.ClienteBCRA;

public class ConsultaBCRABean extends BaseBean{
	private static final Logger log = Logger.getLogger(ConsultaBCRABean.class);
	
	Long input;
	String radioInput;
	
	List<ClienteBCRA> clienteBCRA;
	String nombreTitular;
	String cuil;
	
	boolean panelCliente;
	boolean error;
	String msjError;

	@Override
	public void borrar() {
		input = null;
		radioInput = null;
		nombreTitular = null;
		cuil = null;
		clienteBCRA = null;
		panelCliente = false;
		error = false;
		msjError = null;
	}

	@Override
	public boolean validar() {
		return false;
	}

	@Override
	public String inicializar() {
		return "consultaBCRA";
	}
	
	
	
	
	public String consultaBCRA(){
		nombreTitular = null;
		cuil = null;
		clienteBCRA = null;
		panelCliente = false;
		error = false;
		msjError = null;
		
		log.info("DNI/CUIL: " + input);
		log.info("Opcion: " + radioInput);
		clienteBCRA = transaccionesService.getTransaccionService().consultaBCRA(input, radioInput);
		
		if(clienteBCRA!=null){
			nombreTitular = clienteBCRA.get(0).getNombre();
			cuil = clienteBCRA.get(0).getCuil();
			panelCliente = true;
		}
		else{
			error = true;
			msjError = "No se obtuvieron datos.";
		}

		return "consultaBCRA";
	}
	
	
	

	public Long getInput() {
		return input;
	}

	public void setInput(Long input) {
		this.input = input;
	}

	public String getRadioInput() {
		return radioInput;
	}

	public void setRadioInput(String radioInput) {
		this.radioInput = radioInput;
	}

	public List<ClienteBCRA> getClienteBCRA() {
		return clienteBCRA;
	}

	public void setClienteBCRA(List<ClienteBCRA> clienteBCRA) {
		this.clienteBCRA = clienteBCRA;
	}

	public String getNombreTitular() {
		return nombreTitular;
	}

	public void setNombreTitular(String nombreTitular) {
		this.nombreTitular = nombreTitular;
	}

	public String getCuil() {
		return cuil;
	}

	public void setCuil(String cuil) {
		this.cuil = cuil;
	}

	public boolean isPanelCliente() {
		return panelCliente;
	}

	public void setPanelCliente(boolean panelCliente) {
		this.panelCliente = panelCliente;
	}

	public boolean isError() {
		return error;
	}

	public void setError(boolean error) {
		this.error = error;
	}

	public String getMsjError() {
		return msjError;
	}

	public void setMsjError(String msjError) {
		this.msjError = msjError;
	}
	
	

}
