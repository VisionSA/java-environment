package com.bizitglobal.tarjetafiel.fondos.negocio;

import java.util.List;

import com.bizitglobal.tarjetafiel.commons.interfaces.Negocio;
import com.bizitglobal.tarjetafiel.contabilidad.negocio.PlanCuentaDos;
import com.bizitglobal.tarjetafiel.general.negocio.FormaPago;

public class CajaMP  implements Negocio {
	private Long idCajaMP;
	private Character habilitado = new Character('N');
	private FormaPago formaPago;
	private Caja caja;
	private Long idPlanCuenta;
	private Double importeRetiro;
	
	/**
	 * Este lista es para ser utilizada desde flex 
	 */
	private List<Cheque> descargaChequesList;
	private List<Cheque> 	chequesEnCajaList;
	
	private PlanCuentaDos planCuentaDos; 
	
//	T_VIS_FON_CAJAS_MP
//	C_ID_CAJAMP                    NOT NULL NUMBER(10)                                                                                                                                                                                    
//	C_HABILITADO                            CHAR(1)                                                                                                                                                                                       
//	C_ID_FORMA_PAGO                         NUMBER(10)                                                                                                                                                                                    
//	C_ID_CAJA                               NUMBER(10)        
//	C_ID_PLAN_CUENTA						NUMBER(10)  
	
	public CajaMP() {
	}

	public CajaMP(Long id) {
		idCajaMP = id;
	}
	
	public CajaMP(FormaPago formaPago) {
		this.formaPago = new FormaPago();
		this.formaPago.setDescripcion(formaPago.getDescripcion());
		this.formaPago.setIdFormaPago(formaPago.getIdFormaPago());
	}

	public Long getId() {
		return idCajaMP;
	}

	public String getLabel() {
		return null;
	}
	
	public Long getIdCajaMP() {
		return idCajaMP;
	}
 
	public void setIdCajaMP(Long idCajaMP) {
		this.idCajaMP = idCajaMP;
	}

	public Character getHabilitado() {
		return habilitado;
	}

	public void setHabilitado(Character habilitado) {
		this.habilitado = habilitado;
	}

	public FormaPago getFormaPago() {
		return formaPago;
	}

	public void setFormaPago(FormaPago formaPago) {
		this.formaPago = formaPago;
	}

	public Caja getCaja() {
		return caja;
	}

	public void setCaja(Caja caja) {
		this.caja = caja;
	}

	public Long getIdPlanCuenta() {
		return idPlanCuenta;
	}

	public void setIdPlanCuenta(Long idPlanCuenta) {
		this.idPlanCuenta = idPlanCuenta;
	}

	public PlanCuentaDos getPlanCuentaDos() {
		return planCuentaDos;
	}
	
	public void setPlanCuentaDos(PlanCuentaDos planCuentaDos) {
		this.planCuentaDos = planCuentaDos;
	}
	
	public boolean equals(Object obj) {
		boolean result = false;
		if(obj instanceof CajaMP) {
			CajaMP aux = (CajaMP)obj;
			if(aux.getId().equals(idCajaMP)) {
				result = true;
			}
		}
		return result;
	}
	
	public Double getImporteRetiro() {
		return importeRetiro;
	}
	
	public void setImporteRetiro(Double importeRetiro) {
		this.importeRetiro = importeRetiro;
	}
//	public String toString() {
//		return "Id:"+idCaja+"|Descripcion:"+descripcion;
//	}
	
	public List<Cheque> getDescargaChequesList() {
		return descargaChequesList;
	}
	
	public void setDescargaChequesList(List<Cheque> descargaChequesList) {
		this.descargaChequesList = descargaChequesList;
	}
	
	public List<Cheque> getChequesEnCajaList() {
		return chequesEnCajaList;
	}
	
	public void setChequesEnCajaList(List<Cheque> chequesEnCajaList) {
		this.chequesEnCajaList = chequesEnCajaList;
	}

}

