package com.bizitglobal.webapp.faces.beans.transacciones;

import com.bizitglobal.webapp.faces.beans.BaseBean;


public class TratarPlasticosDeLotes extends BaseBean {

	@Override
	public void borrar() {
		tituloCorto = "Tratamiento de plasticos incluidos en lotes";
		tituloLargo = "TARJETA FIEL";
	}


	@Override
	public boolean validar() {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public String inicializar() {
		// TODO Auto-generated method stub
		return null;
	}

}
