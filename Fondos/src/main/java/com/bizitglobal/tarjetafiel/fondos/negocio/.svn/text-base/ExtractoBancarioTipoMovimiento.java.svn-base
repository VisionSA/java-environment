package com.bizitglobal.tarjetafiel.fondos.negocio;

import com.bizitglobal.tarjetafiel.commons.interfaces.Negocio;
import com.bizitglobal.tarjetafiel.general.negocio.Banco;

public class ExtractoBancarioTipoMovimiento implements Negocio{
	
	private Long idTipoMovimiento;
	private String codigoOperacion;
	private Banco banco;
	private Character tipoMovimiento;
/*@I3918*/	private String descripcion;/*@F3918*/
	
//	C_ID_TIPO_MOV
//	C_CODIGO_OPERACION
//	C_ID_BANCO
//	C_TIPO_MOV
	
	public ExtractoBancarioTipoMovimiento()
	{
		
	}

/*@I3918*/	public ExtractoBancarioTipoMovimiento(Banco banco, String codigoOperacion,
			Long idTipoMovimiento, Character tipoMovimiento, String descripcion) {/*@F3918*/
		super();
		this.banco = banco;
		this.codigoOperacion = codigoOperacion;
		this.idTipoMovimiento = idTipoMovimiento;
		this.tipoMovimiento = tipoMovimiento;
/*@I3918*/		this.descripcion = descripcion;/*@F3918*/
	}

	public Long getIdTipoMovimiento() {
		return idTipoMovimiento;
	}


	public void setIdTipoMovimiento(Long idTipoMovimiento) {
		this.idTipoMovimiento = idTipoMovimiento;
	}


	public String getCodigoOperacion() {
		return codigoOperacion;
	}


	public void setCodigoOperacion(String codigoOperacion) {
		this.codigoOperacion = codigoOperacion;
	}


	public Banco getBanco() {
		return banco;
	}


	public void setBanco(Banco banco) {
		this.banco = banco;
	}


	public Character getTipoMovimiento() {
		return tipoMovimiento;
	}


	public void setTipoMovimiento(Character tipoMovimiento) {
		this.tipoMovimiento = tipoMovimiento;
	}

	@Override
	public Long getId() {
		return idTipoMovimiento;
	}

	@Override
	public String getLabel() {
		return codigoOperacion;
	}

/*@I3918*/	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
/*@F3918*/
}
