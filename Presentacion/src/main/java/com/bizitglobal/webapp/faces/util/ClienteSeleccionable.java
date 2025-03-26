package com.bizitglobal.webapp.faces.util;

import com.bizitglobal.tarjetafiel.general.negocio.Cliente;


/**
 * Represanta una instancia de un cliente seleccionable, para los listados de clientes.
 * 
 * @author Daniel
 */
public class ClienteSeleccionable {
	private Cliente cliente;
	private boolean seleccionado;


	public ClienteSeleccionable() {
		this(null, false);
	}


	public ClienteSeleccionable(Cliente cliente, boolean seleccionado) {
		super();
		this.cliente = cliente;
		this.seleccionado = seleccionado;
	}


	public Cliente getCliente() {
		return cliente;
	}


	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}


	public boolean getSeleccionado() {
		return seleccionado;
	}


	public void setSeleccionado(boolean seleccionado) {
		this.seleccionado = seleccionado;
	}


	public String toString() {
		return "Cuil:" + cliente.getCuil() + "Seleccionado:" + (seleccionado ? "true" : "false");
	}


	public boolean equals(Object obj) {
		boolean result = false;
		if (obj instanceof ClienteSeleccionable) {
			ClienteSeleccionable aux = (ClienteSeleccionable) obj;
			if (aux.getCliente().getIdCliente().equals(cliente.getIdCliente())) {
				result = true;
			}
		}

		return result;
	}

}
