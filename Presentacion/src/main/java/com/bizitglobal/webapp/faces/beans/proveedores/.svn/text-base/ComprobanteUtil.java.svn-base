/**
 * 
 */
package com.bizitglobal.webapp.faces.beans.proveedores;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import javax.faces.model.SelectItem;
import org.apache.log4j.Logger;

import com.bizitglobal.tarjetafiel.general.negocio.TipoComprobante;
import com.bizitglobal.tarjetafiel.impuestos.negocio.Categoria;
import com.bizitglobal.tarjetafiel.impuestos.negocio.Impuesto;
import com.bizitglobal.tarjetafiel.proveedores.negocio.AsientoContable;
import com.bizitglobal.tarjetafiel.proveedores.negocio.Comprobante;
import com.bizitglobal.tarjetafiel.proveedores.negocio.ComprobanteImputado;
import com.bizitglobal.tarjetafiel.proveedores.negocio.CuotaComprobante;
import com.bizitglobal.tarjetafiel.proveedores.negocio.ProvCompExclusion;
import com.bizitglobal.tarjetafiel.proveedores.negocio.ProvCompRetencion;
import com.bizitglobal.tarjetafiel.proveedores.negocio.Proveedor;
import com.bizitglobal.tarjetafiel.proveedores.negocio.ProveedorCategoria;
import com.bizitglobal.tarjetafiel.proveedores.negocio.ProveedorFormaPago;
import com.bizitglobal.tarjetafiel.proveedores.negocio.ProveedorImpuesto;
import com.bizitglobal.webapp.faces.beans.proveedores.ComprobanteBean.Cuota;
import com.bizitglobal.webapp.faces.beans.proveedores.OrdenPagoBean.FormaPagoOP;
import com.bizitglobal.webapp.faces.beans.proveedores.OrdenPagoBean.Imputacion;
import com.bizitglobal.webapp.faces.beans.proveedores.wrappers.ArchivoAdjunto;
import com.bizitglobal.webapp.faces.beans.proveedores.wrappers.RetencionUtil;
import com.bizitglobal.tarjetafiel.general.negocio.ParametroSistema;
import com.bizitglobal.tarjetafiel.general.negocio.ParametroSistemaDetalle;
import com.bizitglobal.webapp.faces.service.general.GeneralServiceFaces;
import com.bizitglobal.webapp.faces.service.proveedores.ProveedoresServiceFaces;
import com.bizitglobal.webapp.faces.util.AsientoCont;
import com.bizitglobal.webapp.faces.util.ImpConMonto;
import com.bizitglobal.webapp.faces.util.Util;
import com.bizitglobal.webapp.faces.util.Validador;


/**
 * @author Hernan
 * 
 */
@SuppressWarnings({"rawtypes","unchecked"})
public class ComprobanteUtil {

	private static final Logger log = Logger.getLogger(ComprobanteUtil.class);

	private List percepciones;
	private List impuestos;


	public ComprobanteUtil() {
		percepciones = new ArrayList();
		impuestos = new ArrayList();
	}


	/**
	 * @param tipoComprobantes
	 *            , Lista de tipos de comprobantes Convierte esta lista en una lista de items para poder mostrarla en los combos.
	 * @return la lista iterada con items.
	 */
	public static List getTipoCompItem(List tipoComps) {
		List result = new ArrayList();
		result.add(Util.primerSelectItem("Seleccione el Tipo"));
		Iterator iterTipoCompAux = tipoComps.iterator();
		TipoComprobante auxTipoComp = null;
		SelectItem item = null;

		while (iterTipoCompAux.hasNext()) {
			auxTipoComp = (TipoComprobante) iterTipoCompAux.next();
			log.info(auxTipoComp);
			if (!auxTipoComp.getDescripcionCorta().toString().equals("OP") &&
					!auxTipoComp.getDescripcionCorta().toString().equals("ROP")) {
				item = new SelectItem();
				item.setValue(auxTipoComp.getIdTipoComprobante());
				item.setLabel(auxTipoComp.getDescripcionLarga());
				result.add(item);
			}

		}
		return result;

	}


	public static List getCategoriaItem(List categorias) {
		List categoriaResul = new ArrayList();
		Iterator iterCategoria = categorias.iterator();
		Categoria auxCategoria = null;
		SelectItem item = null;

		while (iterCategoria.hasNext()) {
			auxCategoria = (Categoria) iterCategoria.next();
			item = new SelectItem();
			item.setValue(auxCategoria.getIdCategoria());
			item.setLabel(auxCategoria.getCodCategoria() + " " + auxCategoria.getDescripcion());
			categoriaResul.add(item);
		}
		return categoriaResul;

	}


	public void armarImpuestos(Proveedor proveedor) {
		try {
			if (!proveedor.getProvedorCategoria().isEmpty()) {
				ProveedorCategoria proCat = null;
				percepciones = new ArrayList();
				impuestos = new ArrayList();
				Iterator iterCategImp = proveedor.getProvedorCategoria().iterator();
				while (iterCategImp.hasNext()) {
					proCat = (ProveedorCategoria) iterCategImp.next();
					if (proCat.getActivo().equals("S")) {
						log.info("Categoria: " + proCat.getJurisdiccionActividad().getCategoria());
						if (!Validador.esNulo(proCat.getJurisdiccionActividad().getCategoria()) &&
								!proCat.getJurisdiccionActividad().getCategoria().getImpuestos().isEmpty()) {
							Iterator iterImp = proCat.getJurisdiccionActividad().getCategoria().getImpuestos().iterator();
							Impuesto imp = null;
							while (iterImp.hasNext()) {
								imp = (Impuesto) iterImp.next();
								log.info("Cargando Impuesto" + imp);
								if (imp.getPercepcion().equals(new Character('S'))) {
									percepciones.add(new ImpConMonto(imp));
								} else {
									impuestos.add(new ImpConMonto(imp));
								}
							}
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}


	public List getImpuestos() {
		return impuestos;
	}


	public List getPercepciones() {
		return percepciones;
	}


	/**
	 * Compone el set de impuestos a partir de las tablas cargadas
	 * 
	 * @param tablaImpuestos
	 * @param tablaPercepciones
	 * @return
	 */
	public static Set componerImpuestos(List tablaImpuestos, List tablaPercepciones, Comprobante comprobante) {
		Set impuestoSet = new HashSet();
		if (!tablaImpuestos.isEmpty()) {
			Iterator iterImp = tablaImpuestos.iterator();
			ImpConMonto impConMonto = null;
			while (iterImp.hasNext()) {
				impConMonto = (ImpConMonto) iterImp.next();
				if (impConMonto.getProvImpuesto() != null && !impConMonto.getMonto().equals(new BigDecimal(0.00).setScale(2))) {
					impConMonto.getProvImpuesto().setComprobante(comprobante);
					// impConMonto.setProvImpuesto(buscarImpuestoGuardado(impConMonto.getProvImpuesto(), comprobante));
					impuestoSet.add(impConMonto.getProvImpuesto());
				}
			}
		}
		if (!tablaPercepciones.isEmpty()) {
			Iterator iterImp = tablaPercepciones.iterator();
			ImpConMonto impConMonto = null;
			while (iterImp.hasNext()) {
				impConMonto = (ImpConMonto) iterImp.next();
				if (impConMonto.getProvImpuesto() != null && !impConMonto.getMonto().equals(new BigDecimal(0.00).setScale(2))) {
					impConMonto.getProvImpuesto().setComprobante(comprobante);
					// impConMonto.setProvImpuesto(buscarImpuestoGuardado(impConMonto.getProvImpuesto(), comprobante));
					impuestoSet.add(impConMonto.getProvImpuesto());
				}
			}
		}

		return impuestoSet;
	}


	private static ProveedorImpuesto buscarImpuestoGuardado(ProveedorImpuesto impuesto, Comprobante comprobante) {
		if (!comprobante.getProveedorImpuestos().isEmpty()) {
			Iterator impuesstosGuardados = comprobante.getProveedorImpuestos().iterator();
			ProveedorImpuesto impuestoGuardado;
			while (impuesstosGuardados.hasNext()) {
				impuestoGuardado = (ProveedorImpuesto) impuesstosGuardados.next();
				if (impuestoGuardado.getImpuesto().equals(impuesto.getImpuesto())) {
					impuesto = impuestoGuardado;
				}
			}
		}
		return impuesto;
	}


	/**
	 * @param tablaCuotas
	 * @return
	 */
	public static ProveedorFormaPago getFPSeleccionada(List formasPagoList) {
		if (!formasPagoList.isEmpty()) {
			Iterator iter = formasPagoList.iterator();
			while (iter.hasNext()) {
				FormaPagoOP formaPagoOP = (FormaPagoOP) iter.next();
				if (formaPagoOP.getSeleccionado()) {
					return formaPagoOP.getFormaPago();
				}
			}
		}
		return null;
	}


	/**
	 * @param tablaCuotas
	 * @return
	 */
	public static Set componerCuotaContable(List tablaCuotas, Comprobante comprobante) {
		Set cuotasSet = new HashSet();
		if (!tablaCuotas.isEmpty()) {
			Iterator iterCuotas = tablaCuotas.iterator();
			Cuota cuota = null;
			while (iterCuotas.hasNext()) {
				cuota = (Cuota) iterCuotas.next();
				cuota.getCuotaComprobante().setComprobante(comprobante);
				log.info("Cuota agregada: " + cuota);
				cuotasSet.add(cuota.getCuotaComprobante());
			}
		}
		return cuotasSet;
	}


	/**
	 * Compone el Set de asientos contables a partir de la tabla cargada.
	 * 
	 * @param tablaAsiento
	 * @return
	 */
	public static Set componerAsientos(List tablaAsiento, Comprobante comprobante) {
		Set asContSet = new HashSet();
		if (!tablaAsiento.isEmpty()) {
			Iterator iterAsientos = tablaAsiento.iterator();
			AsientoCont asientoCont = null;
			while (iterAsientos.hasNext()) {
				asientoCont = (AsientoCont) iterAsientos.next();
				AsientoContable asiento = asientoCont.getAsiento();
				asiento.setComprobante(comprobante);
				if (asiento.getImporteDebe() == null) {
					asiento.setImporteDebe(new BigDecimal(0));
				}
				if (asiento.getImporteHaber() == null) {
					asiento.setImporteHaber(new BigDecimal(0));
				}
				asContSet.add(asiento);
			}
		}
		return asContSet;
	}


	/**
	 * Arma el asiento que utiliza la grilla de la pagina.
	 * 
	 * @param nroImputa
	 * @param titulo
	 * @return
	 */
	public static AsientoCont getAsientoCont(Long nroImputa, String titulo) {
		AsientoCont asCont = new AsientoCont();
		AsientoContable asiento = new AsientoContable();
		asiento.setNroImputa(nroImputa);
		asCont.setAsiento(asiento);
		asCont.setTitulo(titulo);
		return asCont;
	}


	// public static List armarRetenciones(Proveedor proveedor){
	// List retenciones = new ArrayList();
	// try {
	// if (!proveedor.getProvedorCategoria().isEmpty()) {
	// ProveedorCategoria proCat = null;
	// Iterator iterCategImp = proveedor.getProvedorCategoria().iterator();
	// while (iterCategImp.hasNext()) {
	// proCat = (ProveedorCategoria) iterCategImp.next();
	// if (proCat.getActivo().equals("S")) {
	// if (!proCat.getCategoria().getRetenciones().isEmpty()) {
	// Iterator iterRet = proCat.getCategoria().getRetenciones().iterator();
	// Retencion ret = null;
	// while (iterRet.hasNext()) {
	// ret = (Retencion)iterRet.next();
	// log.info("Cargando Retencion:" + ret);
	// retenciones.add(ret);
	// }
	// }
	// }
	// }
	// }
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// return retenciones;
	//
	// }

	/*
	 * EN ESTE METODO DEBO CALCULAR EL MONTO A CADA RETENCION EN EL CASO DE QUE EL MONTO DE LA OP NO SUPERE EL TOTAL DE LAS RETENCIONES
	 */

	public static Set componerRetenciones(List tablaRetenciones, Comprobante comprobante, BigDecimal totalRetenible) {
		BigDecimal resto = new BigDecimal(totalRetenible.doubleValue());
		Set retencionesSet = new HashSet();
		Set asientoRetenciones = new HashSet();// limpio para empezar a cargar los asientos contables
		if (!tablaRetenciones.isEmpty()) {
			boolean tengoResto = true;
			Iterator iterRet = tablaRetenciones.iterator();
			while (iterRet.hasNext()) {
				RetencionUtil retencionUtil = (RetencionUtil) iterRet.next();
				BigDecimal montoRet = retencionUtil.getCalculoRetencion();
				if (retencionUtil.getRetencion().getIdRetencion() != null
						&& montoRet.compareTo(new BigDecimal(0)) != 0 && tengoResto) {
					ProvCompRetencion provCompRetencion = new ProvCompRetencion();
					provCompRetencion.setComprobante(comprobante);
					provCompRetencion.setRetencion(retencionUtil.getRetencion());
					// armo al asiento contable y lo agrego al comprobante

					// Voy restando las retenciones
					resto = resto.subtract(montoRet);
					if (resto.compareTo(new BigDecimal(0)) <= 0) {
						provCompRetencion.setMonto(resto.add(montoRet));
						tengoResto = false;
					} else {
						provCompRetencion.setMonto(montoRet);
					}

					provCompRetencion.setPorcAplicado(retencionUtil.getTramo().getPorcRetencion());
					if (retencionUtil.getExclusion().getIdExclusion() != null) {
						ProvCompExclusion provCompExclusion = new ProvCompExclusion();
						provCompExclusion.setExclusion(retencionUtil.getExclusion());
						provCompExclusion.setMonto(retencionUtil.getTotalExcluido());
						provCompExclusion.setPorcAplicado(retencionUtil.getExclusion().getPorcentaje());
						provCompExclusion.setRetencion(provCompRetencion);
						provCompRetencion.getExclusiones().add(provCompExclusion);
					}

					// creo el asientoContable del haber de cada retencion.
					AsientoContable asientoContable = new AsientoContable();
					asientoContable.setComprobante(comprobante);
					asientoContable.setNroImputa(Long.valueOf(retencionUtil.getRetencion().getCuentaContable()));
					asientoContable.setImporteDebe(new BigDecimal(0));
					asientoContable.setImporteHaber(provCompRetencion.getMonto());
					asientoContable.setLeyenda(retencionUtil.getRetencion().getDescripcion() + " - OP: ");

					// agrego el asientoContable al set de asientos contables
					asientoRetenciones.add(asientoContable);
					retencionesSet.add(provCompRetencion);
				}
			}
			if (!asientoRetenciones.isEmpty()) {
				// recupero el nro de cuenta de los proveedores para retenciones.
				Long nroCuentaProveedores = null;
				try {
					GeneralServiceFaces generalService = new GeneralServiceFaces();
					ParametroSistema paramNroCuentaProveedor = generalService.getParametroSistemaService().leerParametroSistema(
							ParametroSistema.NUMERO_CUENTA_PROVEEDORES_RETENCIONES);
					Iterator iter = paramNroCuentaProveedor.getDetallesParametrosSistema().iterator();
					while (iter.hasNext()) {
						ParametroSistemaDetalle det = (ParametroSistemaDetalle) iter.next();
						if (det.getIdParametroSistemaDetalle().equals(ParametroSistemaDetalle.NUMEROCUENTA_PROVEEDORES_PARA_RETENCIONES)) {
							nroCuentaProveedores = Long.valueOf(det.getValor());
							break;
						}
					}
				} catch (Exception e) {
					System.out.println("Error al buscar el numero de cuenta de los proveedores.");
				}
				// creo el asientoContable del debe.
				AsientoContable asientoContable = new AsientoContable();
				asientoContable.setComprobante(comprobante);
				asientoContable.setNroImputa(nroCuentaProveedores);
				asientoContable.setImporteDebe(comprobante.getTotalRetencion());
				asientoContable.setImporteHaber(new BigDecimal(0));
				asientoContable.setLeyenda("Retenciones - OP: ");
				asientoRetenciones.add(asientoContable);

				// asigno el set de asientosContables al comprobante.
				comprobante.setAsientos(asientoRetenciones);
			}
		}
		return retencionesSet;
	}


	// public static Set componerExclusiones(List tablaExclusiones, Comprobante comprobante, BigDecimal totalImpuestos){
	// Set exclusionesSet = new HashSet();
	// if (!tablaExclusiones.isEmpty()) {
	// Iterator iterExc = tablaExclusiones.iterator();
	// while (iterExc.hasNext()) {
	// Exclusion exclusion = (Exclusion) iterExc.next();
	// BigDecimal xCienExcl = new BigDecimal(exclusion.getPorcentaje().floatValue());
	// BigDecimal montoExclusiones = totalImpuestos.multiply(xCienExcl).divide(new BigDecimal(100));
	// ProvCompExclusion provCompExclusion = new ProvCompExclusion(comprobante, exclusion, exclusion.getPorcentaje(), montoExclusiones);
	// exclusionesSet.add(provCompExclusion);
	// }
	// }
	// return exclusionesSet;
	// }

	public static CuotaComprobante componerImputados(List tablaImputaciones, Comprobante comprobante, BigDecimal pagoACuenta) {
		CuotaComprobante cuotaOP;
		cuotaOP = new CuotaComprobante();
		cuotaOP.setComprobante(comprobante);
		cuotaOP.setActivo(new Character('S'));
		cuotaOP.setImporte(new Float(comprobante.getImporteTotal().floatValue()));
		cuotaOP.setFechaVencimiento(comprobante.getTimestamp());
		Set imputaSet;
		imputaSet = new HashSet();
		if (!tablaImputaciones.isEmpty()) {
			Iterator iterImp = tablaImputaciones.iterator();
			while (iterImp.hasNext()) {
				Imputacion imputacion = (Imputacion) iterImp.next();
				if (imputacion.getSeleccionado()) {
					ComprobanteImputado comprobanteImputado = new ComprobanteImputado();
					comprobanteImputado.setCuotaComprobanteD(imputacion.getCuota());
					comprobanteImputado.setCuotaComprobanteH(cuotaOP);
					comprobanteImputado.setFechaEmision(comprobante.getTimestamp());
					comprobanteImputado.setImporteCancela(new BigDecimal(imputacion.getMontoAImputar().floatValue()));
					imputaSet.add(comprobanteImputado);
				}
			}
		}
		cuotaOP.setCuotaComprobanteH(imputaSet);
		return cuotaOP;
	}


	public static void modificarCuota(List tablaImputaciones, Comprobante comprobante, BigDecimal pagoACuenta,
			ProveedoresServiceFaces proveedoresService) {
		Iterator iterCuotaSet = comprobante.getCuotaComprobantes().iterator();
		CuotaComprobante cuotaOP = (CuotaComprobante) iterCuotaSet.next();

		// if (!tablaImputaciones.isEmpty()) {
		// Iterator iterImp = tablaImputaciones.iterator();
		// while (iterImp.hasNext()) {
		// Imputacion imputacion = (Imputacion) iterImp.next();
		// if (imputacion.getSeleccionado()) {
		// ComprobanteImputado comprobanteImputado = new ComprobanteImputado();
		// comprobanteImputado.setCuotaComprobanteD(imputacion.getCuota());
		// comprobanteImputado.setCuotaComprobanteH(cuotaOP);
		// comprobanteImputado.setFechaEmision(comprobante.getTimestamp());
		// comprobanteImputado.setImporteCancela(new BigDecimal(imputacion.getMontoAImputar().floatValue()));
		// proveedoresService.getComprobanteImputadoDao().grabarComprobanteImputado(comprobanteImputado);
		// }
		// }
		// }

		if (!cuotaOP.getCuotaComprobanteH().isEmpty()) {
			Iterator iterImput = cuotaOP.getCuotaComprobanteH().iterator();
			while (iterImput.hasNext()) {
				ComprobanteImputado cImputado = (ComprobanteImputado) iterImput.next();
				proveedoresService.getComprobanteImputadoDao().borrarComprobanteImputado(cImputado);
			}
		}
		cuotaOP = componerImputados(tablaImputaciones, comprobante, pagoACuenta);
		// if (cuotaOP.getImporte().compareTo(new Float(pagoACuenta.floatValue())) != 0) {
		// cuotaOP.setImporte(new Float(pagoACuenta.floatValue()));
		// try {
		// proveedoresService.getCuotaComprobanteDao().actualizarCuotaComprobante(cuotaOP);
		// log.info("Pago a cuenta actualizado.");
		// } catch (Exception e) {
		// e.printStackTrace();
		// }
		// }

	}


	public static List eliminarArchivoAdjunto(List list, Long id) {

		list.remove(buscarArchivo(list, id));

		if (list == null || list.isEmpty() || list.size() == 0)
			list = new ArrayList();

		return list;
	}


	public static ArchivoAdjunto buscarArchivo(List list, Long id) {
		ArchivoAdjunto result = null;

		if (!list.isEmpty()) {
			Iterator iterator = list.iterator();
			while (iterator.hasNext()) {
				ArchivoAdjunto element = (ArchivoAdjunto) iterator.next();

				if (element.getIdArchivoAdjunto().equals(id)) {
					result = element;
				}
			}
		}

		return result;
	}
}
