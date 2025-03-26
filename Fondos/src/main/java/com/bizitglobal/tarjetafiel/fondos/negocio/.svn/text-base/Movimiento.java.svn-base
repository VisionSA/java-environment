package com.bizitglobal.tarjetafiel.fondos.negocio;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;

import com.bizitglobal.tarjetafiel.commons.interfaces.Negocio;
import com.bizitglobal.tarjetafiel.general.negocio.ConceptoGen;
import com.bizitglobal.tarjetafiel.operador.negocio.Operador;

public class Movimiento  implements Negocio, Cloneable {
	private Long idMovimiento;
	private Integer signo;
	private Date fecha;
	private Date fechaAsiento;
	private Operador operador;
	private Double importe;
	private Character estado;
	private ConceptoGen concepto;
	private Caja caja;
	private CajaApertura cajaApertura;
	private Character tipo; 	
	private Set movimientosMP;
	private String ticket;
	private String codigoExterno;
	
	private static Logger logger = Logger.getLogger(Movimiento.class);
	
//	T_VIS_FON_MOVIMIENTOS
//	C_ID_MOVIMIENTO                NOT NULL NUMBER(10)                                                                                                                                                                                    
//	C_SIGNO                                 NUMBER(1)                                                                                                                                                                                       
//	C_FECHA                                 TIMESTAMP(0)           
//	C_FECHA_ASIENTO                         TIMESTAMP(0)
//	C_ID_OPERADOR                           NUMBER(10)                                                                                                                                                                                    
//	C_IMPORTE                               NUMBER(10,2)                                                                                                                                                                                  
//	C_ESTADO                                CHAR(1)                                                                                                                                                                                       
//	C_ID_CONCEPTO                           NUMBER(10)                                                                                                                                                                                    
//	C_ID_CAJA                               NUMBER(10)                                                                                                                                                                                    
//	C_ID_CAJAAPERTURA                       NUMBER(10)     
//	C_TICKET								CLOB
//  C_CODIGO_EXTERNO						VARCHAR2(20)

	public Movimiento() {
		movimientosMP = new HashSet();
	}
	
	public Movimiento(Long id) {
		this();
		idMovimiento = id;
	}

	public Long getId() {
		return idMovimiento;
	}

	public String getLabel() {
		return null;
	}
	
	public Long getIdMovimiento() {
		return idMovimiento;
	}

	public void setIdMovimiento(Long idMovimiento) {
		this.idMovimiento = idMovimiento;
	}

	public Integer getSigno() {
		return signo;
	}

	public void setSigno(Integer signo) {
		this.signo = signo;
	}

	public Date getFecha() {
		return fecha;
	}
	
	public String getFechaFormat() {
		Format dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		return dateFormat.format(fecha);
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
		if (fechaAsiento == null) fechaAsiento = fecha;
	}
	
	public String getFechaAsientoFormat() {
		Format dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		return dateFormat.format(fechaAsiento);
	}
	
	public Date getFechaAsiento() {
		return fechaAsiento;
	}

	public void setFechaAsiento(Date fechaAsiento) {
		this.fechaAsiento = fechaAsiento;
	}

	public Operador getOperador() {
		return operador;
	}

	public void setOperador(Operador operador) {
		this.operador = operador;
	}

	public Double getImporte() {
		return importe;
	}

	public void setImporte(Double importe) {
		this.importe = importe;
	}

	public Character getEstado() {
		return estado;
	}

	public void setEstado(Character estado) {
		this.estado = estado;
	}

	public ConceptoGen getConcepto() {
		return concepto;
	}

	public void setConcepto(ConceptoGen concepto) {
		this.concepto = concepto;
	}

	public Caja getCaja() {
		return caja;
	}

	public void setCaja(Caja caja) {
		this.caja = caja;
	}

	public CajaApertura getCajaApertura() {
		return cajaApertura;
	}

	public void setCajaApertura(CajaApertura cajaApertura) {
		this.cajaApertura = cajaApertura;
	}

	public boolean equals(Object obj) {
		boolean result = false;
		if(obj instanceof Movimiento) {
			Movimiento aux = (Movimiento)obj;
			if(aux.getId().equals(idMovimiento)) {
				result = true;
			}
		}
		return result;
	}

	public Set getMovimientosMP() {
		return movimientosMP;
	}
	
	public void setMovimientosMP(Set movimientosMP) {
		this.movimientosMP = movimientosMP;
	}
	
	
	/**
	 * @param lista de movientos posteriores al cierre de una caja en particular
	 * * @return  map clave=idfmaPago valor= sumatoria de pagos realizados con la clave del map  
	 */
	public Map movimientosPosterioresACierreCaja(List movimientosPosteriores){
		Iterator it =  movimientosPosteriores.iterator();
		Map movimientosMap = new HashMap();
		while (it.hasNext()) {
			Movimiento movimiento = (Movimiento) it.next();
			if(movimiento.getMovimientosMP()!= null){
			  Iterator  movMPIterator =  movimiento.getMovimientosMP().iterator();
			  while (movMPIterator.hasNext()) {
				MovimientoMP movimientoMP = (MovimientoMP) movMPIterator.next();
				// si no existe lo crea
				if(movimientosMap.get(movimientoMP.getFormaPago().getIdFormaPago())== null){
					  movimientosMap.put(movimientoMP.getFormaPago().getIdFormaPago(),
					   new Double(movimientoMP.getAsientoItem().getSigno().doubleValue()* movimientoMP.getMonto().doubleValue()));
				}else{
					// si no acumula
					 movimientosMap.put(movimientoMP.getFormaPago().getIdFormaPago(),
					  new Double(((Double)movimientosMap.get(movimientoMP.getFormaPago().getIdFormaPago())).doubleValue() + 
                       movimientoMP.getAsientoItem().getSigno().doubleValue()* movimientoMP.getMonto().doubleValue()));
				}
			}
			} 
		}
		return movimientosMap; 
	}
	
	public String getTicket() {
		return ticket;
	}
	
	public void setTicket(String ticket) {
		this.ticket = ticket;
	}

	public String getCodigoExterno() {
		return codigoExterno;
	}

	public void setCodigoExterno(String codigoExterno) {
		this.codigoExterno = codigoExterno;
	}
	
	public Movimiento getClon(){
		try {
			return (Movimiento)super.clone();
		} catch (CloneNotSupportedException e) {
			logger.error(e,e);
			return null;
		}
	}
	
}
