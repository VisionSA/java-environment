package com.bizitglobal.webapp.faces.beans.proveedores.wrappers;

import java.math.BigDecimal;
import java.util.Iterator;

import org.apache.log4j.Logger;

import com.bizitglobal.tarjetafiel.impuestos.negocio.Categoria;
import com.bizitglobal.tarjetafiel.impuestos.negocio.Exclusion;
import com.bizitglobal.tarjetafiel.impuestos.negocio.Retencion;
import com.bizitglobal.tarjetafiel.impuestos.negocio.TramosRetencion;



@SuppressWarnings({"rawtypes"})
public class RetencionUtil {
	private static final Logger log = Logger.getLogger(RetencionUtil.class);
	private Categoria categoria;
	private Retencion retencion;
	private Exclusion exclusion;
	private BigDecimal xCienTramo;
	private BigDecimal totalOP;
	private BigDecimal totalPagosMes;
	private BigDecimal totalRetencionesMes;
	private TramosRetencion tramo;
	private BigDecimal totalExcluido;
	private BigDecimal totalRetenido;
	private BigDecimal total;


	public RetencionUtil() {
		super();
		this.retencion = null;
		this.totalOP = new BigDecimal(0);
		this.totalPagosMes = new BigDecimal(0);
		this.totalRetencionesMes = new BigDecimal(0);
		this.totalRetencionesMes = new BigDecimal(0);
		this.total = new BigDecimal(0);
		this.xCienTramo = new BigDecimal(0);
		this.tramo = null;
	}


	public RetencionUtil(Categoria categoria, Retencion retencion, Exclusion exclusion, BigDecimal totalOP, BigDecimal totalPagosMes,
			BigDecimal totalRetencionesMes) {
		this();
		this.categoria = categoria;
		if (retencion == null) {
			this.retencion = new Retencion();
			this.retencion.setDescripcion("No se aplica");
			this.retencion.setCodigoRegimen(" - ");
		} else {
			this.retencion = retencion;
		}
		if (exclusion == null) {
			this.exclusion = new Exclusion();
			this.exclusion.setPorcentaje(new Float(0));
		} else {
			this.exclusion = exclusion;
		}
		this.totalOP = totalOP;
		System.out.println("Intentare retener sobre el monto total de la operacion de " + totalOP);
		this.totalPagosMes = totalPagosMes;
		this.totalRetencionesMes = totalRetencionesMes;
		this.totalExcluido = new BigDecimal(0);
		this.totalRetenido = new BigDecimal(0);
	}


	public BigDecimal getTotalRetenido() {
		return totalRetenido.setScale(2, BigDecimal.ROUND_HALF_DOWN);
	}


	public void setTotalRetenido(BigDecimal totalRetenido) {
		this.totalRetenido = totalRetenido;
	}


	public BigDecimal getTotalExcluido() {
		return totalExcluido.setScale(2, BigDecimal.ROUND_HALF_DOWN);
	}


	public void setTotalExcluido(BigDecimal totalExcluido) {
		this.totalExcluido = totalExcluido;
	}


	public Categoria getCategoria() {
		return categoria;
	}


	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}


	public Exclusion getExclusion() {
		return exclusion;
	}


	public void setExclusion(Exclusion exclusion) {
		this.exclusion = exclusion;
	}


	public Retencion getRetencion() {
		return retencion;
	}


	public void setRetencion(Retencion retencion) {
		this.retencion = retencion;
	}


	public BigDecimal getTotalPagosMes() {
		return totalPagosMes;
	}


	public void setTotalPagosMes(BigDecimal totalPagosMes) {
		this.totalPagosMes = totalPagosMes;
	}


	public BigDecimal getXCienTramo() {
		return xCienTramo;
	}


	public void setXCienTramo(BigDecimal cienTramo) {
		xCienTramo = cienTramo;
	}


	public BigDecimal getTotalOP() {
		return totalOP;
	}


	public void setTotalOP(BigDecimal totalOP) {
		this.totalOP = totalOP;
	}


	public TramosRetencion getTramo() {
		return tramo;
	}


	public void setTramo(TramosRetencion tramo) {
		this.tramo = tramo;
	}


	public BigDecimal getTotal() {
		return total.setScale(2, BigDecimal.ROUND_HALF_DOWN);
	}


	public void setTotal(BigDecimal total) {
		this.total = total;
	}


	public BigDecimal getCalculoRetencion() {
		if (retencion.getIdRetencion() == null) {
			System.out.println("Devolvere cero comoretencion ya que no existe");
			return new BigDecimal(0);
		} else {
			tramo = new TramosRetencion();
			total = new BigDecimal(0);

			if (new BigDecimal(0).compareTo(totalOP) < 0) {
				BigDecimal monto = new BigDecimal(totalOP.doubleValue());
				// me fijo si acumula pagos y lo guardo para sacar los calculos
				if (retencion.getAcumulaPagos().toString().equals("S")) {
					// le sumo el total de los paggos que se le efectuaron en el mes
					monto = monto.add(totalPagosMes);
				}

				if (retencion.getMinimoImponible().compareTo(monto) <= 0) {
					// Recorro los tramos para ver en que porcentaje se aplica
					Iterator tramosIter = retencion.getTramosRetenciones().iterator();
					while (tramosIter.hasNext()) {
						// me guardo el tramo que se aplica
						TramosRetencion tramoAux = (TramosRetencion) tramosIter.next();
						// log.info("Tramo encontrado: " + tramo);
						if (tramoAux.getMontoDesde().compareTo(monto) <= 0 && tramoAux.getMontoHasta().compareTo(monto) >= 0) {
							// log.info("Tramo aplicado: " + tramo);
							tramo = tramoAux;
							// // calculo lo que exede a el monto desde
							monto = monto.subtract(tramo.getMontoDesde());
							// cargo el porcentaje que se aplica
							xCienTramo = new BigDecimal(tramo.getPorcRetencion().floatValue());
							// calculo el % de retencion
							totalRetenido = monto.multiply(xCienTramo).divide(new BigDecimal(100));
							// guardo el monto total de retencion para mostrarlo
							// le sumo el monto minimo de la retencion
							totalRetenido = totalRetenido.add(tramo.getMontoMinimo());
							// Si acumulaba pagos le resto el total de retenciones practicadas en el mes
							if (retencion.getAcumulaPagos().toString().equals("S")) {
								totalRetenido = totalRetenido.subtract(totalRetencionesMes);
							}
							if (retencion.getMinimoRetencion().compareTo(totalRetenido) > 0) {
								total = new BigDecimal(0);
							} else {
								total = new BigDecimal(totalRetenido.doubleValue());
							}
							// controlo si tiene una exclusion y se la aplico
							if (exclusion.getIdExclusion() != null) {
								totalExcluido = total.multiply(new BigDecimal(exclusion.getPorcentaje().floatValue())).divide(new BigDecimal(100));
								total = total.subtract(totalExcluido);
							}
							total = total.setScale(2, BigDecimal.ROUND_HALF_DOWN);

							return total;
						}
					}
				}
			}

			return total;
		}
	}
}
