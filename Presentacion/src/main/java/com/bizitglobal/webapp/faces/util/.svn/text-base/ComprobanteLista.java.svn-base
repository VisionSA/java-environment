package com.bizitglobal.webapp.faces.util;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.bizitglobal.tarjetafiel.operador.negocio.Operador;
import com.bizitglobal.tarjetafiel.proveedores.negocio.Comprobante;
import com.bizitglobal.tarjetafiel.proveedores.negocio.Proveedor;
import com.bizitglobal.tarjetafiel.commons.util.Convertidores;
import com.bizitglobal.tarjetafiel.general.negocio.TipoComprobante;


public class ComprobanteLista {
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
	private Set exclusiones = new HashSet();
	private Set retenciones = new HashSet();
	private String conceptoAsiento;
	private Comprobante compRevertido = null;
	private Proveedor proveedor = null;
	private Operador operador;
	private Character enFondos;


	public ComprobanteLista() {
		this(null, null, null, null, null, null, null, null, null, null, null, null,
				null, null, null, null, null, null, null, null, null, null, null, null);
	}


	public ComprobanteLista(Long idComprobante, Integer nroCorto, Integer nroLargo, Proveedor provPedidoPor,
			Timestamp fechaEmision, Timestamp fechaContable, String observacion, BigDecimal montoGrabado,
			BigDecimal montoNoGrabado, BigDecimal importeNeto, BigDecimal totalImpuestos, BigDecimal totalExclusion,
			BigDecimal importeTotal, Character contabilizado, Timestamp timestamp, Integer signo, BigDecimal totalRetencion,
			TipoComprobante tipoComprobante, String conceptoAsiento, Proveedor proveedor, Operador operador,
			Timestamp fechaPagado, Comprobante compRevertido, Character enFondos) {
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
	}


	public ComprobanteLista(Comprobante c) {
		super();
		this.idComprobante = c.getIdComprobante();
		this.nroCorto = c.getNroCorto();
		this.nroLargo = c.getNroLargo();
		this.provPedidoPor = c.getProvPedidoPor();
		this.fechaEmision = c.getFechaEmision();
		this.fechaContable = c.getFechaContable();
		this.observacion = c.getObservacion();
		this.montoGrabado = c.getMontoGrabado();
		this.montoNoGrabado = c.getMontoNoGrabado();
		this.importeNeto = c.getImporteNeto();
		this.totalImpuestos = c.getTotalImpuestos();
		this.importeTotal = c.getImporteTotal();
		this.contabilizado = c.getContabilizado();
		this.timestamp = c.getTimestamp();
		this.tipoComprobante = c.getTipoComprobante();
		this.conceptoAsiento = c.getConceptoAsiento();
		this.proveedor = c.getProveedor();
		this.operador = c.getOperador();
		this.signo = c.getSigno();
		this.totalExclusion = c.getTotalExclusion();
		this.totalRetencion = c.getTotalRetencion();
		this.fechaPagado = c.getFechaPagado();
		this.compRevertido = c.getCompRevertido();
		this.enFondos = c.getEnFondos();
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


	public Date getFechaContable() {
		return fechaContable;
	}


	public void setFechaContable(Date fechaContable) {
		this.fechaContable = new Timestamp(fechaContable.getTime());
	}


	public String getFechaEmision() {
		Format dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		return dateFormat.format(fechaEmision);
	}


	public void setFechaEmision(String fechaEmision) {
		// this.fechaEmision = new Timestamp(new Date(fechaEmision).getTime());
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


	public String getNroCorto() {
		return Util.completar(nroCorto.toString(), 4);
	}


	public void setNroCorto(String nroCorto) {
		this.nroCorto = new Integer(nroCorto);
	}


	public String getNroLargo() {
		return Util.completar(nroLargo.toString(), 8);
	}


	public void setNroLargo(String nroLargo) {
		this.nroLargo = new Integer(nroLargo);
	}


	public String getNroEntero() {
		return getNroCorto() + "-" + getNroLargo();
	}


	public void setNroEntero(String nroEntero) {
		;
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


	public Set getExclusiones() {
		return exclusiones;
	}


	public void setExclusiones(Set exclusiones) {
		this.exclusiones = exclusiones;
	}


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


	public Character getEnFondos() {
		return enFondos;
	}


	public void setEnFindos(Character enFondos) {
		this.enFondos = enFondos;
	}


	public boolean getPasoFondos() {
		return Convertidores.getBoolean(enFondos.toString());
	}


	public String toString() {
		return "Id:" + idComprobante +
				"|Cuit: " + proveedor.getCuit() +
				"|Importe Total: " + importeTotal +
				"|Tipo de comp: " + tipoComprobante.getDescripcionLarga();
	}


	public boolean equals(Object unComprobante) {
		boolean result = false;
		if (unComprobante instanceof ComprobanteLista) {
			ComprobanteLista aux = (ComprobanteLista) unComprobante;
			if (aux.getIdComprobante().equals(idComprobante)) {
				result = true;
			}
		}

		return result;
	}
}
