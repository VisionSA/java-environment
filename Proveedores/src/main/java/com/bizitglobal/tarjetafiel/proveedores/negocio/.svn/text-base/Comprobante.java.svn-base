package com.bizitglobal.tarjetafiel.proveedores.negocio;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.hibernate.util.Cloneable;

import com.bizitglobal.tarjetafiel.operador.negocio.Operador;
import com.bizitglobal.tarjetafiel.proveedores.exception.DocumentoAdjuntoDuplicateException;
import com.bizitglobal.tarjetafiel.proveedores.negocio.Proveedor;
import com.bizitglobal.tarjetafiel.fondos.negocio.Movimiento;
import com.bizitglobal.tarjetafiel.general.negocio.TipoComprobante;

public class Comprobante {
	private Long idComprobante;
	private Integer nroCorto;
	private Integer nroLargo;
	private Proveedor provPedidoPor;
	private Timestamp fechaEmision;
	private Timestamp fechaContable;
	private String observacion;
	private BigDecimal montoGrabado;
	private BigDecimal montoNoGrabado;
	private BigDecimal importeNeto;
	private BigDecimal totalImpuestos;
	private BigDecimal importeTotal;
	private Character contabilizado;
	private Timestamp timestamp;
	private TipoComprobante tipoComprobante;
	private Integer signo;
	private BigDecimal totalExclusion;
	private BigDecimal totalRetencion;
	private Timestamp fechaPagado;
	private Set proveedorImpuestos = new HashSet();
	private Set cuotaComprobantes = new HashSet();
	private Set asientos = new HashSet();
//	private Set exclusiones = new HashSet();
	private Set retenciones = new HashSet();
	private String conceptoAsiento;
	private Comprobante compRevertido = null;
	private Proveedor proveedor = null;
	private Operador operador;
	private Character enFondos;
	private ProveedorFormaPago formaPago;
	private Set docAdjuntos;
	private Movimiento movimiento;
	
	public Comprobante() {
		this(null,null,null,null,null,null,null,null,null,null,null,null,
				null,null,null,null,null,null,null,null,null,null,null, new Character('N'),null);
	}



	public Comprobante(Long idComprobante, Integer nroCorto, Integer nroLargo, Proveedor provPedidoPor,
				Timestamp fechaEmision, Timestamp fechaContable, String observacion, BigDecimal montoGrabado,
				BigDecimal montoNoGrabado, BigDecimal importeNeto, BigDecimal totalImpuestos, BigDecimal totalExclusion,
				BigDecimal importeTotal, Character contabilizado, Timestamp timestamp, Integer signo, BigDecimal totalRetencion,
				TipoComprobante tipoComprobante, String conceptoAsiento, Proveedor proveedor, Operador operador,
				Timestamp fechaPagado, Comprobante compRevertido, Character enFondos, ProveedorFormaPago formaPago) {
		super();
		this.idComprobante = idComprobante;
		this.nroCorto = nroCorto;
		this.nroLargo = nroLargo;
		this.provPedidoPor = provPedidoPor;
		this.fechaEmision = fechaEmision;
		this.fechaContable = fechaContable;
		this.observacion = observacion;
		this.montoGrabado = montoGrabado;
		this.montoNoGrabado = montoNoGrabado;
		this.importeNeto = importeNeto;
		this.totalImpuestos = totalImpuestos;
		this.importeTotal = importeTotal;
		this.contabilizado = contabilizado;
		this.timestamp = timestamp;
		this.tipoComprobante = tipoComprobante;
		this.conceptoAsiento = conceptoAsiento;
		this.proveedor = proveedor;
		this.operador = operador;
		this.signo = signo;
		this.totalExclusion = totalExclusion;
		this.totalRetencion = totalRetencion;
		this.fechaPagado = fechaPagado;
		this.compRevertido = compRevertido;
		this.enFondos = enFondos;
		this.formaPago = formaPago;
	}

	
	/*
	* El metodo controla que el archivo exista, agrega la url donde se
	* ubica al set de docAdjuntos
     */
    public boolean agregarDocumentoAdjunto(DocumentoAdjunto docA) throws DocumentoAdjuntoDuplicateException {
        if (docAdjuntos==null) docAdjuntos = new HashSet();
        Iterator i = docAdjuntos.iterator();
        while (i.hasNext()) {
        	DocumentoAdjunto docAdjun = (DocumentoAdjunto)i.next();
        	if (docAdjun.getUrl().compareTo(docA.getUrl())==0) {
        		throw new DocumentoAdjuntoDuplicateException(null, null);
        	}
        }
        docA.setComprobante(this);
        docA.setId(null);
        docAdjuntos.add(docA);
    	return true;	
    }
    
    public boolean eliminarDocumentoAdjunto(DocumentoAdjunto docA) {
    	if (!docAdjuntos.isEmpty()) {
    		Iterator i = docAdjuntos.iterator();
            while (i.hasNext()) {
            	DocumentoAdjunto docAdjun = (DocumentoAdjunto)i.next();
            	if (docAdjun.getUrl().compareTo(docA.getUrl())==0) {
            		docAdjuntos.remove(docAdjun);
            		return true;
            	}
            }
    	}
    	return false;
    }
	
	
	public Character getEnFondos() {
		return enFondos;
	}

	public void setEnFondos(Character enFondos) {
		this.enFondos = enFondos;
	}

	public Set getAsientos() {
		return asientos;
	}

	public void setAsientos(Set asientos) {
		this.asientos = asientos;
	}

	public Set getCuotaComprobantes() {
		return cuotaComprobantes;
	}

	public void setCuotaComprobantes(Set cuotaComprobantes) {
		this.cuotaComprobantes = cuotaComprobantes;
	}

	public Timestamp getFechaContable() {
		return fechaContable;
	}

	public void setFechaContable(Timestamp fechaContable) {
		this.fechaContable = fechaContable;
	}

	public Timestamp getFechaEmision() {
		return fechaEmision;
	}

	public void setFechaEmision(Timestamp fechaEmision) {
		this.fechaEmision = fechaEmision;
	}

	public Long getIdComprobante() {
		return idComprobante;
	}

	public void setIdComprobante(Long idComprobante) {
		this.idComprobante = idComprobante;
	}

	public BigDecimal getImporteNeto() {
		return importeNeto;
	}

	public void setImporteNeto(BigDecimal importeNeto) {
		this.importeNeto = importeNeto;
	}

	public BigDecimal getImporteTotal() {
		return importeTotal;
	}

	public void setImporteTotal(BigDecimal importeTotal) {
		this.importeTotal = importeTotal;
	}

	public Set getProveedorImpuestos() {
		return proveedorImpuestos;
	}

	public void setProveedorImpuestos(Set proveedorImpuestos) {
		this.proveedorImpuestos = proveedorImpuestos;
	}

	public BigDecimal getMontoGrabado() {
		return montoGrabado;
	}

	public void setMontoGrabado(BigDecimal montoGrabado) {
		this.montoGrabado = montoGrabado;
	}

	public BigDecimal getMontoNoGrabado() {
		return montoNoGrabado;
	}

	public void setMontoNoGrabado(BigDecimal montoNoGrabado) {
		this.montoNoGrabado = montoNoGrabado;
	}

	public Integer getNroCorto() {
		return nroCorto;
	}

	public void setNroCorto(Integer nroCorto) {
		this.nroCorto = nroCorto;
	}

	public Integer getNroLargo() {
		return nroLargo;
	}

	public void setNroLargo(Integer nroLargo) {
		this.nroLargo = nroLargo;
	}

	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	public Proveedor getProveedor() {
		return proveedor;
	}

	public void setProveedor(Proveedor proveedor) {
		this.proveedor = proveedor;
	}

	public Timestamp getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}

	public TipoComprobante getTipoComprobante() {
		return tipoComprobante;
	}

	public void setTipoComprobante(TipoComprobante tipoComprobante) {
		this.tipoComprobante = tipoComprobante;
	}

	public BigDecimal getTotalImpuestos() {
		return totalImpuestos;
	}

	public void setTotalImpuestos(BigDecimal totalImpuestos) {
		this.totalImpuestos = totalImpuestos;
	}
			
	public Character getContabilizado() {
		return contabilizado;
	}

	public void setContabilizado(Character contabilizado) {
		this.contabilizado = contabilizado;
	}

	public Proveedor getProvPedidoPor() {
		return provPedidoPor;
	}

	public void setProvPedidoPor(Proveedor provPedidoPor) {
		this.provPedidoPor = provPedidoPor;
	}
	
	public String getConceptoAsiento() {
		return conceptoAsiento;
	}

	public void setConceptoAsiento(String conceptoAsiento) {
		this.conceptoAsiento = conceptoAsiento;
	}
	
//	public Set getExclusiones() {
//		return exclusiones;
//	}
//
//	public void setExclusiones(Set exclusiones) {
//		this.exclusiones = exclusiones;
//	}

	public Set getRetenciones() {
		return retenciones;
	}

	public void setRetenciones(Set retenciones) {
		this.retenciones = retenciones;
	}

	public void setOperador(Operador operador) {
		this.operador = operador;
	}

	public Operador getOperador() {
		return operador;
	}

	public Integer getSigno() {
		return signo;
	}

	public void setSigno(Integer signo) {
		this.signo = signo;
	}

	public BigDecimal getTotalExclusion() {
		return totalExclusion;
	}

	public void setTotalExclusion(BigDecimal totalExclusion) {
		this.totalExclusion = totalExclusion;
	}

	public BigDecimal getTotalRetencion() {
		return totalRetencion;
	}

	public void setTotalRetencion(BigDecimal totalRetencion) {
		this.totalRetencion = totalRetencion;
	}
	
	public Comprobante getCompRevertido() {
		return compRevertido;
	}

	public void setCompRevertido(Comprobante compRevertido) {
		this.compRevertido = compRevertido;
	}

	public Timestamp getFechaPagado() {
		return fechaPagado;
	}

	public void setFechaPagado(Timestamp fechaPagado) {
		this.fechaPagado = fechaPagado;
	}

	public Set getDocAdjuntos() {
		return docAdjuntos;
	}

	public void setDocAdjuntos(Set docAdjuntos) {
		this.docAdjuntos = docAdjuntos;
	}

	public ProveedorFormaPago getFormaPago() {
		return formaPago;
	}

	public void setFormaPago(ProveedorFormaPago formaPago) {
		this.formaPago = formaPago;
	}

	public Movimiento getMovimiento() {
		return movimiento;
	}

	public void setMovimiento(Movimiento movimiento) {
		this.movimiento = movimiento;
	}

	public String tiString() {
		return "Id:"+idComprobante+"|Cuit:"+proveedor.getCuit()+"Importe Total:"+importeTotal;
	}
	
	public boolean equals(Object unComprobante) {
		boolean result = false;
		if(unComprobante instanceof Comprobante) {
			Comprobante aux = (Comprobante)unComprobante;
			if(aux.getIdComprobante().equals(idComprobante)) {
				result = true;
			}
		}
		
		return result;
	}
	
	public Comprobante copia() {
        Comprobante result = new Comprobante();
        
    	result.setIdComprobante(idComprobante);
    	result.setNroCorto(nroCorto);
    	result.setNroLargo(nroLargo);
    	result.setProvPedidoPor(provPedidoPor);
    	result.setFechaEmision(fechaEmision);
    	result.setFechaContable(fechaContable);
    	result.setObservacion(observacion);
    	result.setMontoGrabado(montoGrabado);
    	result.setMontoNoGrabado(montoNoGrabado);
    	result.setImporteNeto(importeNeto);
    	result.setTotalImpuestos(totalImpuestos);
    	result.setImporteTotal(importeTotal);
    	result.setContabilizado(contabilizado);
    	result.setTimestamp(timestamp);
    	result.setTipoComprobante(tipoComprobante);
    	result.setSigno(signo);
    	result.setTotalExclusion(totalExclusion);
    	result.setTotalRetencion(totalRetencion);
    	result.setFechaPagado(fechaPagado);
    	result.setProveedorImpuestos(new HashSet());
    	result.setCuotaComprobantes(new HashSet());
    	result.setAsientos(new HashSet());
//    	result.setExclusiones(new HashSet());
    	result.setRetenciones(new HashSet());
    	result.setConceptoAsiento(conceptoAsiento);
    	result.setCompRevertido(compRevertido);
    	result.setProveedor(proveedor);
    	result.setOperador(operador);        
        result.setEnFondos(enFondos);
        result.setFormaPago(formaPago);
        
        return result;
    }



}
