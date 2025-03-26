package com.bizitglobal.tarjetafiel.fondos.negocio;

import com.bizitglobal.tarjetafiel.commons.interfaces.Negocio;
import com.bizitglobal.tarjetafiel.contabilidad.negocio.PlanCuentaDos;
import com.bizitglobal.tarjetafiel.general.negocio.Banco;
import com.bizitglobal.tarjetafiel.general.negocio.Moneda;
import com.bizitglobal.tarjetafiel.general.negocio.SucursalFiel;

public class BancoPropio  implements Negocio {
	private Long idBancoPropio;
	private String tipoCuenta = "";
	private String numeroCuenta;
	private Moneda moneda;
	private String cbu;
	private Long numeroSucursal;
	private Long plaza;
	private Character habilitado = new Character('N');
	private Banco banco;
	private SucursalFiel sucursal;
	private PlanCuentaDos planCuenta;
	
//	T_VIS_FON_BANCOS_PROPIOS
//	C_ID_BANCO_PROPIO              NOT NULL NUMBER(10)                                                                                                                                                                                    
//	C_TIPO_CUENTA                           CHAR(2)                                                                                                                                                                                       
//	C_NUMERO_CUENTA                         VARCHAR2(20)                                                                                                                                                                                    
//	C_MONEDA                                CHAR(1)                                                                                                                                                                                       
//	C_CBU                                   VARCHAR2(20)                                                                                                                                                                                    
//	C_NUMERO_SUCURSAL                       NUMBER(10)                                                                                                                                                                                    
//	C_PLAZA                                 NUMBER(10)                                                                                                                                                                                    
//	C_HABILITADO                            CHAR(1)                                                                                                                                                                                       
//	C_ID_BANCO                              NUMBER(10)                                                                                                                                                                                    
//	C_ID_SUCURSAL                           NUMBER(10)    
//	C_ID_PLAN_CUENTA			   NOT NULL NUMBER(10)

	public BancoPropio() {
	}

	public BancoPropio(Long id) {
		idBancoPropio = id;
	}

	public Long getId() {
		return idBancoPropio;
	}

	public String getLabel() {
		return banco.getDescripcion()+"|"+numeroCuenta+ "|Suc: "+numeroSucursal;
	}
	
	public Long getIdBancoPropio() {
		return idBancoPropio;
	}

	public void setIdBancoPropio(Long idBancoPropio) {
		this.idBancoPropio = idBancoPropio;
	}

	public String getTipoCuenta() {
		return tipoCuenta;
	}

	public void setTipoCuenta(String tipoCuenta) {
		this.tipoCuenta = tipoCuenta;
	}

	public String getNumeroCuenta() {
		return numeroCuenta;
	}

	public void setNumeroCuenta(String numeroCuenta) {
		this.numeroCuenta = numeroCuenta;
	}

	
	public Moneda getMoneda() {
		return moneda;
	}

	public void setMoneda(Moneda moneda) {
		this.moneda = moneda;
	}

	public String getCbu() {
		return cbu;
	}

	public void setCbu(String cbu) {
		this.cbu = cbu;
	}

	public Long getNumeroSucursal() {
		return numeroSucursal;
	}

	public void setNumeroSucursal(Long numeroSucursal) {
		this.numeroSucursal = numeroSucursal;
	}

	public Long getPlaza() {
		return plaza;
	}

	public void setPlaza(Long plaza) {
		this.plaza = plaza;
	}

	public Character getHabilitado() {
		return habilitado;
	}

	public void setHabilitado(Character habilitado) {
		this.habilitado = habilitado;
	}

	public Banco getBanco() {
		return banco;
	}

	public void setBanco(Banco banco) {
		this.banco = banco;
	}

	public SucursalFiel getSucursal() {
		return sucursal;
	}

	public void setSucursal(SucursalFiel sucursal) {
		this.sucursal = sucursal;
	}

	public PlanCuentaDos getPlanCuenta() {
		return planCuenta;
	}

	public void setPlanCuenta(PlanCuentaDos planCuenta) {
		this.planCuenta = planCuenta;
	}

	public boolean equals(Object obj) {
		boolean result = false;
		if(obj instanceof BancoPropio) {
			BancoPropio aux = (BancoPropio)obj;
			if(aux.getId().equals(idBancoPropio)) {
				result = true;
			}
		}
		return result;
	}
//	
//	public String toString() {
//		return "Id:"+idCaja+"|Descripcion:"+descripcion;
//	}

}

