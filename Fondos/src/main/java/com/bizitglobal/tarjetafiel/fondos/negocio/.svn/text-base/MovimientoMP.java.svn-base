package com.bizitglobal.tarjetafiel.fondos.negocio;

import java.util.Set;

import org.apache.log4j.Logger;

import com.bizitglobal.tarjetafiel.commons.interfaces.Negocio;
import com.bizitglobal.tarjetafiel.general.negocio.FormaPago;


public class MovimientoMP  implements Negocio,Cloneable {
	private Long idMovimientoMP;
	private Double monto;
	private Movimiento movimiento;
	private FormaPago formaPago;
	private AsientoItem asientoItem;
	private Set chequeHistorial;
	/**
	 * Esta variable es solo para ser utilizada desde la caja (flex)
	 */
	private Cheque cheque;
	private static Logger logger = Logger.getLogger(MovimientoMP.class);
	
//	T_VIS_FON_MOVIMIENTOS_MP
//	C_ID_MOVIM_MP                  NOT NULL NUMBER(10)                                                                                                                                                                                    
//	C_MONTO                                 NUMBER(10,2)                                                                                                                                                                                  
//	C_ID_MOVIMIENTO                         NUMBER(10)                                                                                                                                                                                    
//	C_ID_FORMA_PAGO                         NUMBER(10)                                                                                                                                                                                    
//	C_ID_ASIENTO_ITEM                       NUMBER(10)        

	  public MovimientoMP(MovimientoMP movimientoMP) {
	    	if(movimientoMP.getIdMovimientoMP()!= null){
			 this.idMovimientoMP = new Long(movimientoMP.getIdMovimientoMP().longValue());
	    	}
			this.monto = movimientoMP.getMonto();
			this.movimiento = movimientoMP.getMovimiento();
			this.formaPago = movimientoMP.getFormaPago();
			this.asientoItem= movimientoMP.getAsientoItem();
	 }
	
	
	
	public MovimientoMP() {
	}

	public MovimientoMP(Long id) {
		idMovimientoMP = id;
	}

	public Long getId() {
		return idMovimientoMP;
	}

	public String getLabel() {
		return null;
	}
	
	public Long getIdMovimientoMP() {
		return idMovimientoMP;
	}

	public void setIdMovimientoMP(Long idMovimientoMP) {
		this.idMovimientoMP = idMovimientoMP;
	}

	public Double getMonto() {
		return monto;
	}

	public void setMonto(Double monto) {
		this.monto = monto;
	}

	public Movimiento getMovimiento() {
		return movimiento;
	}

	public void setMovimiento(Movimiento movimiento) {
		this.movimiento = movimiento;
	}

	public FormaPago getFormaPago() {
		return formaPago;
	}

	public void setFormaPago(FormaPago formaPago) {
		this.formaPago = formaPago;
	}

	public AsientoItem getAsientoItem() {
		return asientoItem;
	}

	public void setAsientoItem(AsientoItem asientoItem) {
		this.asientoItem = asientoItem;
	}

	public Set getChequeHistorial() {
		return chequeHistorial;
	}

	public void setChequeHistorial(Set chequeHistorial) {
		this.chequeHistorial = chequeHistorial;
	}

	public boolean equals(Object obj) {
		boolean result = false;
		if(obj instanceof MovimientoMP) {
			MovimientoMP aux = (MovimientoMP)obj;
			if(aux.getId().equals(idMovimientoMP)) {
				result = true;
			}
		}
		return result;
	}
	
	public Cheque getCheque() {
		return cheque;
	}
	
	public void setCheque(Cheque cheque) {
		this.cheque = cheque;
	}
	
	public MovimientoMP getClon(){
		try {
			return (MovimientoMP)super.clone();
		} catch (CloneNotSupportedException e) {
			logger.error(e,e);
			return null;
		}
	}
	
}

