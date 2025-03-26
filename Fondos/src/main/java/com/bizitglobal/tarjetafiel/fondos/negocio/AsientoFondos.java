package com.bizitglobal.tarjetafiel.fondos.negocio;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

import org.apache.log4j.Logger;

import com.bizitglobal.tarjetafiel.commons.interfaces.Negocio;
import com.bizitglobal.tarjetafiel.operador.negocio.Operador;

public class AsientoFondos  implements Negocio, Cloneable {
	private Long idAsiento;
	private String concepto = "";
	private Date fecha;
	private Operador operador;
	private String hora;
	private Character cotabilizado = new Character('N');
	private Date fechaContabilizado;
	private AsientoFondos asientoRev;
	private Set asientosItems;
	private SimpleDateFormat format = new SimpleDateFormat("HH:mm");
	
	private static Logger logger = Logger.getLogger(AsientoFondos.class);
	
//	T_VIS_FON_ASIENTOS
//	C_ID_ASIENTO                   NOT NULL NUMBER(10)                                                                                                                                                                                    
//	C_CONCEPTO                              NVARCHAR2(40)                                                                                                                                                                                 
//	C_FECHA                                 DATE                                                                                                                                                                                          
//	C_ID_OPERADOR                           NUMBER                                                                                                                                                                                        
//	C_HORA                                  CHAR(5)                                                                                                                                                                                       
//	C_CONTABILIZADO                         CHAR(1)                                                                                                                                                                                       
//	C_FECHA_CONTABILIZADO                   TIMESTAMP(0)                                                                                                                                                                                  

	
	public Set getAsientosItems() {
		return asientosItems;
	}

	public void setAsientosItems(Set asientosItems) {
		this.asientosItems = asientosItems;
	}

	public AsientoFondos() {
	}

	public AsientoFondos(Long id) {
		idAsiento = id;
	}

	public Long getId() {
		return idAsiento;
	}

	public String getLabel() {
		return concepto;
	}
	
	public Long getIdAsiento() {
		return idAsiento;
	}

	public void setIdAsiento(Long idAsiento) {
		this.idAsiento = idAsiento;
	}

	public String getConcepto() {
		return concepto;
	}

	public void setConcepto(String concepto) {
		this.concepto = concepto;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
		this.hora = format.format(fecha);
	}

	public Operador getOperador() {
		return operador;
	}

	public void setOperador(Operador operador) {
		this.operador = operador;
	}

	public String getHora() {
		return hora;
	}

	public void setHora(String hora) {
		this.hora = hora;
	}

	public Character getCotabilizado() {
		return cotabilizado;
	}

	public void setCotabilizado(Character cotabilizado) {
		this.cotabilizado = cotabilizado;
	}

	public Date getFechaContabilizado() {
		return fechaContabilizado;
	}

	public void setFechaContabilizado(Date fechaContabilizado) {
		this.fechaContabilizado = fechaContabilizado;
	}
	
	public AsientoFondos getAsientoRev() {
		return asientoRev;
	}

	public void setAsientoRev(AsientoFondos asientoRev) {
		this.asientoRev = asientoRev;
	}

	public boolean equals(Object obj) {
		boolean result = false;
		if(obj instanceof AsientoFondos) {
			AsientoFondos aux = (AsientoFondos)obj;
			if(aux.getId().equals(idAsiento)) {
				result = true;
			}
		}
		return result;
	}
	
	public String toString() {
		return "Id:"+idAsiento+"|Concepto:" + concepto;
	}
	
	public AsientoFondos getClon(){
		try {
			return (AsientoFondos)super.clone();
		} catch (CloneNotSupportedException e) {
			logger.error(e,e);
			return null;
		}
	}
}

