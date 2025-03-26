package com.bizitglobal.webapp.faces.beans.proveedores;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.faces.model.SelectItem;
import org.apache.log4j.Logger;

import com.bizitglobal.tarjetafiel.general.negocio.Domicilio;
import com.bizitglobal.tarjetafiel.proveedores.negocio.Comprobante;
import com.bizitglobal.tarjetafiel.proveedores.negocio.ComprobanteImputado;
import com.bizitglobal.tarjetafiel.proveedores.negocio.CuotaComprobante;
import com.bizitglobal.webapp.faces.util.Convertidores;
import com.bizitglobal.webapp.faces.util.CuotasImputables;
import com.bizitglobal.webapp.faces.util.Imputacion;
import com.bizitglobal.webapp.faces.util.Util;


@SuppressWarnings({"rawtypes","unchecked"})
public class ImputacionUtil {
	private static final Logger log = Logger.getLogger(ImputacionUtil.class);


	public static String getDomicilio(List domiciliosList) {
		String result = null;

		if (!domiciliosList.isEmpty()) {
			Iterator domicilios = domiciliosList.iterator();
			while (domicilios.hasNext()) {
				Domicilio domicilio = (Domicilio) domicilios.next();
			}
		}

		return result;
	}


	public static Object[] cargarCuotasImputables(List comprobantes) {
		// Arreglo de resultados.
		// result[0] tendrá los objetos para las tablas.
		// result[1] tendrá los objetos para las listas.
		// result[2] tendrá los objetos para las imputaciones leidas desde la base de datos.
		Object[] result = { new ArrayList(), new ArrayList(), new ArrayList() };

		if (!comprobantes.isEmpty()) {
			Iterator iter = comprobantes.iterator();
			while (iter.hasNext()) {
				Comprobante comprobante = (Comprobante) iter.next();
				List cuotasList = Convertidores.setToList(comprobante.getCuotaComprobantes());
				if (!cuotasList.isEmpty()) {
					Iterator iter2 = cuotasList.iterator();
					int contador = 1;
					while (iter2.hasNext()) {
						CuotaComprobante cuota = (CuotaComprobante) iter2.next();
						CuotasImputables cuotaImp = new CuotasImputables();
						List cuotasImputadas = Convertidores.setToList(cuota.getCuotaComprobanteD());
						float sumaImp = 0;
						if (!cuotasImputadas.isEmpty()) {
							log.info("Si hay cuotas de comprobantes -> " + cuotasImputadas.size());
							Iterator iter3 = cuotasImputadas.iterator();
							while (iter3.hasNext()) {
								ComprobanteImputado cImp = (ComprobanteImputado) iter3.next();
								sumaImp += cImp.getImporteCancela().floatValue();
							}
						} else {
							cuotasImputadas = Convertidores.setToList(cuota.getCuotaComprobanteH());
							if (!cuotasImputadas.isEmpty()) {
								log.info("Si hay cuota de orden de pago -> " + cuotasImputadas.size());
								Iterator iter3 = cuotasImputadas.iterator();
								while (iter3.hasNext()) {
									ComprobanteImputado cImp = (ComprobanteImputado) iter3.next();
									sumaImp += cImp.getImporteCancela().floatValue();
								}
							}
							cuotaImp.setCuenta(new Float(comprobante.getImporteTotal().floatValue() - sumaImp));
						}

						// Cargarmos los datos para mostrar en la página.

						String nroCorto = Util.completar(comprobante.getNroCorto().toString(), 4);
						String nroLargo = Util.completar(comprobante.getNroLargo().toString(), 8);
						String numeroCuota = nroCorto + "-" + nroLargo;
						String descripcion = comprobante.getTipoComprobante().getDescripcionCorta();

						cuotaImp.setTipo(descripcion + "/" + numeroCuota + "/" + contador);
						cuotaImp.setCuota(cuota);
						// cuotaImp.setNumero(numeroCuota); // No es necesario.
						cuotaImp.setImputado(new Float(sumaImp));
						cuotaImp.setResto(new Float(cuota.getImporte().floatValue() - sumaImp));
						cuotaImp.setFechaEmision(comprobante.getFechaEmision());

						// Agregamos los SelectItems para las listas desplegables.
						SelectItem item = new SelectItem();
						item.setValue(cuota.getIdCuotaComprobante());
						item.setLabel(cuotaImp.getTipo());
						((List) result[1]).add(item);

						// // Agregamos la lista de imputaciones.
						// List imputaciones = Convertidores.setToList(cuota.getCuotaComprobanteD());
						// if(!imputaciones.isEmpty()) {
						// Iterator imp = imputaciones.iterator();
						// while(imp.hasNext()) {
						// ComprobanteImputado comp = (ComprobanteImputado)imp.next();
						// Imputacion imputacion = new Imputacion();
						// imputacion.setIdImputacion(comp.getIdComprobanteImputado());
						// imputacion.setComprobante(comp.getCuotaComprobanteD().getIdCuotaComprobante());
						// imputacion.setOrden(comp.getCuotaComprobanteH().getIdCuotaComprobante());
						// imputacion.setMonto(new Float(comp.getImporteCancela().floatValue()));
						// ((List)result[2]).add(imputacion);
						// }
						// }

						((List) result[0]).add(cuotaImp);
						contador++;
					}
				}
			}
		}

		return result;
	}


	// public static List generarImputaciones(List listaOrdenesPago, List listaComprobantes, List listaImputaciones) {
	// List result = new ArrayList();
	// if(!listaImputaciones.isEmpty()) {
	// Iterator iter = listaImputaciones.iterator();
	// while(iter.hasNext()) {
	// Imputacion imp = (Imputacion)iter.next();
	// imp.setCuotasImpComprobante(listaComprobantes);
	// imp.setListaOrdenes(listaOrdenesPago);
	// result.add(imp);
	// }
	// } else {
	// result.add(new Imputacion(listaOrdenesPago,listaComprobantes));
	// }
	//
	// return result;
	// }

	public static List generarImputacionesListado(List imputaciones) {
		List result = new ArrayList();

		if (!imputaciones.isEmpty()) {
			Iterator iter = imputaciones.iterator();
			while (iter.hasNext()) {
				ComprobanteImputado aux = (ComprobanteImputado) iter.next();
				Imputacion nuevo = new Imputacion();

				String descC = aux.getCuotaComprobanteD().getComprobante().getTipoComprobante().getDescripcionCorta();
				String nroCC = Util.completar(aux.getCuotaComprobanteD().getComprobante().getNroCorto().toString(), 4);
				String nroLC = Util.completar(aux.getCuotaComprobanteD().getComprobante().getNroLargo().toString(), 8);

				String descO = aux.getCuotaComprobanteH().getComprobante().getTipoComprobante().getDescripcionCorta();
				String nroCO = Util.completar(aux.getCuotaComprobanteH().getComprobante().getNroCorto().toString(), 4);
				String nroLO = Util.completar(aux.getCuotaComprobanteH().getComprobante().getNroLargo().toString(), 8);

				String comp = descC + "/" + nroCC + "-" + nroLC;
				String ord = descO + "/" + nroCO + "-" + nroLO;

				String cuit = aux.getCuotaComprobanteH().getComprobante().getProveedor().getCuit().toString();
				String nombre = aux.getCuotaComprobanteH().getComprobante().getProveedor().getRazonSocial();

				nuevo.setComprobanteTipo(comp);
				nuevo.setOrdenTipo(ord);
				nuevo.setIdImputacion(aux.getIdComprobanteImputado());
				nuevo.setMonto(new Float(aux.getImporteCancela().floatValue()));
				nuevo.setFechaEmision(aux.getFechaEmision());
				nuevo.setNroCuit(cuit);
				nuevo.setRazonSocial(nombre);

				result.add(nuevo);
			}
		}

		return result;
	}

}
