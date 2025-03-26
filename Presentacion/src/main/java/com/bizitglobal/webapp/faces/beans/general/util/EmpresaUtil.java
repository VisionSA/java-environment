package com.bizitglobal.webapp.faces.beans.general.util;

import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;

import com.bizitglobal.tarjetafiel.general.negocio.Domicilio;
import com.bizitglobal.webapp.faces.beans.general.EmpresaBean.Sucursal;
import com.bizitglobal.webapp.faces.beans.general.wrapper.EmailWrapper;
import com.bizitglobal.webapp.faces.beans.general.EmpresaBean.DomicilioWrapper;


@SuppressWarnings({"rawtypes"})
public class EmpresaUtil {
	private static final Logger log = Logger.getLogger(EmpresaUtil.class);


	/*
	 * Este metodo se utiliza para eliminar un telefono de la lista.
	 */
	/*
	 * public static List eliminarTelefono(List listaWrapperTelefono, int idTelefono) {
	 * 
	 * if(listaWrapperTelefono!=null&&!listaWrapperTelefono.isEmpty()) { Iterator iter = listaWrapperTelefono.iterator(); while(iter.hasNext()) {
	 * TelefonoWrapper aux = (TelefonoWrapper) iter.next(); if(aux.getCodigo()==idTelefono) { listaWrapperTelefono.remove(aux);
	 * log.info("TelefonoEliminado"); break; } } }
	 * 
	 * return listaWrapperTelefono; }
	 */
	/*
	 * Este metodo se utiliza para buscar un objeto telefono
	 */
	/*
	 * public static SucTelefono buscarSucTelefono(Set listaTelefonoWrapper, int idTelefono){ TelefonoWrapper result = null;
	 * 
	 * if(!listaTelefonoWrapper.isEmpty()){ Iterator iterator = listaTelefonoWrapper.iterator(); while (iterator.hasNext()) { TelefonoWrapper element
	 * = (TelefonoWrapper) iterator.next();
	 * 
	 * if(element.getCodigo()==idTelefono){ result = element; } } }
	 * 
	 * return result.getSucTelefono(); }
	 */

	public static List eliminarEmail(List listaWrapperEmail, int idEmail) {

		if (!listaWrapperEmail.isEmpty()) {
			Iterator iter = listaWrapperEmail.iterator();
			while (iter.hasNext()) {
				EmailWrapper auxi = (EmailWrapper) iter.next();
				if (auxi.getCodigo() == idEmail) {
					listaWrapperEmail.remove(auxi);
					break;
				}
			}
		}

		return listaWrapperEmail;
	}


	public static Sucursal buscarSucursal(List listaSucursales, int idSucursal) {
		Sucursal result = null;

		if (!listaSucursales.isEmpty()) {
			Iterator iterator = listaSucursales.iterator();
			while (iterator.hasNext()) {
				Sucursal element = (Sucursal) iterator.next();

				if (element.getIdSucursalTabla() == (idSucursal)) {
					result = element;
				}
			}
		}

		return result;
	}


	public static Domicilio buscarDomicilio(List listaDomicilios, int idDomicilio) {
		if (!listaDomicilios.isEmpty()) {
			Iterator iterator = listaDomicilios.iterator();
			while (iterator.hasNext()) {
				DomicilioWrapper element = (DomicilioWrapper) iterator.next();
				if (element.getCodigo() == idDomicilio) {
					return element.getDomicilio();
				}
			}
		}
		return null;
	}


	/*
	 * Este metodo se utiliza para eliminar un domicilio de la lista.
	 */
	public static List eliminarDomicilio(List listaWrapperDomicilio, int idDomicilio) {

		if (listaWrapperDomicilio != null && !listaWrapperDomicilio.isEmpty()) {
			Iterator iter = listaWrapperDomicilio.iterator();
			while (iter.hasNext()) {
				DomicilioWrapper aux = (DomicilioWrapper) iter.next();
				if (aux.getCodigo() == idDomicilio) {
					listaWrapperDomicilio.remove(aux);
					log.info("DomicilioEliminado");
					break;
				}
			}
		}

		return listaWrapperDomicilio;
	}
}
