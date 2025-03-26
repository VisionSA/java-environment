package com.bizitglobal.tarjetafiel.fondos.negocio;

import java.util.Set;

import org.apache.log4j.Logger;

import com.bizitglobal.tarjetafiel.commons.interfaces.Negocio;
import com.bizitglobal.tarjetafiel.contabilidad.negocio.PlanCuentaDos;

public class AsientoItem  implements Negocio,Cloneable {
	private Long idAsientoItem;
	private AsientoFondos asiento;
	private Integer signo;
	private String leyenda = "";
	private Double importe;
	private Long idPlanCuenta;
	private Set movimientoMPs;
	private Integer nroRenglon;
	
	private static Logger logger = Logger.getLogger(AsientoFondos.class);
	
	private PlanCuentaDos planCuenta;
	private Set chequeHistorial;

//	T_VIS_FON_ASIENTOS_ITEM
//	C_ID_ASIENTO_ITEM              NOT NULL NUMBER(10)                                                                                                                                                                                    
//	C_ID_ASIENTO                            NUMBER(10)                                                                                                                                                                                    
//	C_SIGNO                                 CHAR(1)                                                                                                                                                                                       
//	C_LEYENDA                               NVARCHAR2(50)                                                                                                                                                                                 
//	C_IMPORTE                               NUMBER(10,2)                                                                                                                                                                                  
//	C_ID_PLAN_CUENTA                        NUMBER(10)                                                                                                                                                                                    


	public AsientoItem() {
	}

	public AsientoItem(Long id) {
		idAsientoItem = id;
	}

	public Long getId() {
		return idAsientoItem;
	}
	
	public String getLabel() {
		return leyenda;
	}
	
	public Long getIdAsientoItem() {
		return idAsientoItem;
	}

	public void setIdAsientoItem(Long idAsientoItem) {
		this.idAsientoItem = idAsientoItem;
	}

	public AsientoFondos getAsiento() {
		return asiento;
	}

	public void setAsiento(AsientoFondos asiento) {
		this.asiento = asiento;
	}

	public Integer getSigno() {
		return signo;
	}

	public void setSigno(Integer signo) {
		this.signo = signo;
	}

	public String getLeyenda() {
		return leyenda;
	}

	public void setLeyenda(String leyenda) {
		this.leyenda = leyenda;
	}

	public Double getImporte() {
		return importe;
	}

	public void setImporte(Double importe) {
		this.importe = importe;
	}
	
	public Long getIdPlanCuenta() {
		return idPlanCuenta;
	}

	public void setIdPlanCuenta(Long idPlanCuenta) {
		this.idPlanCuenta = idPlanCuenta;
	}

	public PlanCuentaDos getPlanCuenta() {
		return planCuenta;
	}

	public void setPlanCuenta(PlanCuentaDos planCuenta) {
		this.planCuenta = planCuenta;
	}
	
	public Set getMovimientoMPs() {
		return movimientoMPs;
	}

	public void setMovimientoMPs(Set movimientoMPs) {
		this.movimientoMPs = movimientoMPs;
	}
	
	public Integer getNroRenglon() {
		return nroRenglon;
	}

	public void setNroRenglon(Integer nroRenglon) {
		this.nroRenglon = nroRenglon;
	}
	
	public Set getChequeHistorial() {
		return chequeHistorial;
	}

	public void setChequeHistorial(Set chequeHistorial) {
		this.chequeHistorial = chequeHistorial;
	}

	public boolean equals(Object obj) {
		boolean result = false;
		if(obj instanceof AsientoItem) {
			AsientoItem aux = (AsientoItem)obj;
			if(aux.getId().equals(idAsientoItem)) {
				result = true;
			}
		}
		return result;
	}
	
	public String toString() {
		return "Id:"+idAsientoItem+"|Leyenda:"+leyenda;
	}
	
	public AsientoItem getClon(){
		try {
			return (AsientoItem)super.clone();
		} catch (CloneNotSupportedException e) {
			logger.error(e,e);
			return null;
		}
	}
}

