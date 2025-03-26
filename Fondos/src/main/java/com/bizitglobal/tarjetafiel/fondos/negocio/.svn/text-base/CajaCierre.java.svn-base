package com.bizitglobal.tarjetafiel.fondos.negocio;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

import com.bizitglobal.tarjetafiel.commons.interfaces.Negocio;

public class CajaCierre  implements Negocio {
	
	public static final Character CIERRE_X = 'X'; 
	public static final Character CIERRE_Z = 'Z';
	
	private Long idCajaCierre;
	private CajaMP caja;
	private Character tipo;
	private Date fecha;
	private Double totalArqueo;
	private Double totalContable;
	private Double diferencia;	
	
	private Set<CajaArqueo> cajaArqueosList;
	
//	T_VIS_FON_CAJAS_CIERRES
//	C_ID_CAJACIERRE                NOT NULL NUMBER(10,2)                                                                                                                                                                                  
//	C_ID_CAJA                               NUMBER(10)                                                                                                                                                                                    
//	C_TIPO                                  CHAR(1)                                                                                                                                                                                       
//	C_FECHA                                 DATE                                                                                                                                                                                          
//	C_TOTAL_ARQUEO                          NUMBER(10,2)                                                                                                                                                                                  
//	C_TOTAL_CONTABLE                        NUMBER(10,2)                                                                                                                                                                                  
//	C_DIFERENCIA                            NUMBER(10,2)  
	
	public CajaCierre() {
	}

	public CajaCierre(Long id) {
		idCajaCierre = id;
	}

	public Long getId() {
		return idCajaCierre;
	}

	public String getLabel() {
		return null;
	}

	public Long getIdCajaCierre() {
		return idCajaCierre;
	}

	public void setIdCajaCierre(Long idCajaCierre) {
		this.idCajaCierre = idCajaCierre;
	}

	public CajaMP getCaja() {
		return caja;
	}

	public void setCaja(CajaMP caja) {
		this.caja = caja;
	}

	public Character getTipo() {
		return tipo;
	}

	public void setTipo(Character tipo) {
		this.tipo = tipo;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Double getTotalArqueo() {
		return totalArqueo;
	}

	public void setTotalArqueo(Double totalArqueo) {
		this.totalArqueo = totalArqueo;
	}

	public Double getTotalContable() {
		return totalContable;
	}

	public void setTotalContable(Double totalContable) {
		this.totalContable = totalContable;
	}

	public Double getDiferencia() {
		return diferencia;
	}

	public void setDiferencia(Double diferencia) {
		this.diferencia = diferencia;
	}

	public boolean equals(Object obj) {
		boolean result = false;
		if(obj instanceof CajaCierre) {
			CajaCierre aux = (CajaCierre)obj;
			if(aux.getId().equals(idCajaCierre)) {
				result = true;
			}
		}
		return result;
	}
	
	public Set<CajaArqueo> getCajaArqueosList() {
		return cajaArqueosList;
	}
	
	public void setCajaArqueosList(Set<CajaArqueo> cajaArqueosList) {
		this.cajaArqueosList = cajaArqueosList;
	}
	
//	public String toString() {
//		return "Id:"+idCaja+"|Descripcion:"+descripcion;
//	}
}

