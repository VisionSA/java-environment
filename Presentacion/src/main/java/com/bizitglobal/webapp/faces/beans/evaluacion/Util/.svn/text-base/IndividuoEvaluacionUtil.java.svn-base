package com.bizitglobal.webapp.faces.beans.evaluacion.Util;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.faces.model.SelectItem;

import org.apache.log4j.Logger;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.evaluacion.negocio.Educacion;
import com.bizitglobal.tarjetafiel.evaluacion.negocio.IndividuoDomicilio;
import com.bizitglobal.tarjetafiel.evaluacion.negocio.Telefonos;
import com.bizitglobal.tarjetafiel.evaluacion.negocio.Verificador;
import com.bizitglobal.tarjetafiel.general.dao.TipoDocumentoDao;
import com.bizitglobal.tarjetafiel.general.dao.TipoTelefonoDao;
import com.bizitglobal.tarjetafiel.general.negocio.Banco;
import com.bizitglobal.tarjetafiel.general.negocio.EstadoCivil;
import com.bizitglobal.tarjetafiel.general.negocio.Ocupacion;
import com.bizitglobal.tarjetafiel.general.negocio.Profesion;
import com.bizitglobal.tarjetafiel.general.negocio.SucEmpresa;
import com.bizitglobal.tarjetafiel.general.negocio.SucTelefono;
import com.bizitglobal.tarjetafiel.general.negocio.Telefono;
import com.bizitglobal.tarjetafiel.general.negocio.TipoCuentaBanco;
import com.bizitglobal.tarjetafiel.general.negocio.TipoDigital;
import com.bizitglobal.tarjetafiel.general.negocio.TipoDocumento;
import com.bizitglobal.tarjetafiel.general.negocio.TipoDomicilio;
import com.bizitglobal.tarjetafiel.general.negocio.TipoTelefono;
import com.bizitglobal.tarjetafiel.general.negocio.Vinculo;


@SuppressWarnings({"rawtypes","unchecked"})
public class IndividuoEvaluacionUtil {
	private static final Logger log = Logger.getLogger(IndividuoEvaluacionUtil.class);


	/*
	 * Este metodo se utiliza para traer todos los tipos de documento desde la base de datos y armar el selectitem.
	 */
	public static List cargarTipoDocumeno(TipoDocumentoDao tipoDocumentoDao) {
		List result = new ArrayList();
		result.add(new SelectItem(new Long(0), "Seleccionar Tipo Documento"));

		List tipoDoc = tipoDocumentoDao.listarTodos(new Filtro());

		if (!tipoDoc.isEmpty()) {

			Iterator iterator = tipoDoc.iterator();
			while (iterator.hasNext()) {
				SelectItem item = new SelectItem();
				TipoDocumento element = (TipoDocumento) iterator.next();

				item.setValue(element.getIdTipoDocumento());
				item.setLabel(element.getDescripcion());

				result.add(item);
			}
		}
		return result;
	}


	/*
	 * Este metodo carga de manera dura los tipos de sexos.
	 */
	public static List cargarSexo() {
		List result = new ArrayList();
		result.add(new SelectItem(new Long(0), "Seleccionar Sexo"));

		SelectItem item = new SelectItem();
		item.setValue(new Long(1));
		item.setLabel(new String("Femenino"));

		SelectItem itemUno = new SelectItem();
		itemUno.setValue(new Long(2));
		itemUno.setLabel(new String("Masculino"));

		result.add(item);
		result.add(itemUno);

		return result;
	}


	/*
	 * Este metodo se utiliza para traer todos los tipos de telefonos desde la base de datos y armar el selectitem.
	 */
	public static List cargarTipoTelefono(TipoTelefonoDao tipoTelefonoDao) {
		List result = new ArrayList();
		result.add(new SelectItem(new Long(0), "Seleccionar Tipo Teléfono"));

		List tipoTel = tipoTelefonoDao.listarTodos();
		if (!tipoTel.isEmpty()) {

			Iterator iterator = tipoTel.iterator();
			while (iterator.hasNext()) {
				TipoTelefono element = (TipoTelefono) iterator.next();
				SelectItem item = new SelectItem();

				item.setValue(new Integer(element.getIdTipoTelefono().intValue()));
				item.setLabel(element.getDescripcion());

				result.add(item);
			}
		}
		return result;
	}


	/*
	 * Este metodo se utiliza para traer todos los estados civiles desde la base de datos y armar el selectitem.
	 */
	public static List cargarEstadoCivil(List listEstadoCivil) {
		log.info("Ejecutando ==> cargarEstadoCivil()");
		List result = new ArrayList();
		SelectItem el = new SelectItem();
		el.setValue(new Long(0));
		el.setLabel("Seleccionar Estado Civil");
		result.add(el);

		if (!listEstadoCivil.isEmpty()) {
			Iterator iterator = listEstadoCivil.iterator();
			while (iterator.hasNext()) {
				EstadoCivil element = (EstadoCivil) iterator.next();
				SelectItem item = new SelectItem();

				item.setValue(element.getId());
				item.setLabel(element.getDescripcion());

				result.add(item);
			}
		}
		return result;
	}


	/*
	 * Este metodo se utiliza para traer todos los tipos de educación desde la base de datos y armar el selectitem.
	 */
	public static List cargarEducacion(List listAuxEducacion) {
		List result = new ArrayList();
		result.add(new SelectItem(new Long(0), "Seleccionar Educación"));

		if (!listAuxEducacion.isEmpty()) {
			Iterator iterator = listAuxEducacion.iterator();
			while (iterator.hasNext()) {
				Educacion element = (Educacion) iterator.next();
				SelectItem item = new SelectItem();

				item.setValue(element.getIdEducacion());
				item.setLabel(element.getDescripcion());

				result.add(item);
			}
		}
		return result;
	}


	/*
	 * Este metodo se utiliza para traer todos los tipos de profeciones desde la base de datos y armar el selectitem.
	 */
	public static List cargarProfesion(List listAuxProfesion) {
		List result = new ArrayList();
		result.add(new SelectItem(new Long(0), "Seleccionar Profesión"));

		if (!listAuxProfesion.isEmpty()) {
			Iterator iterator = listAuxProfesion.iterator();
			while (iterator.hasNext()) {
				Profesion element = (Profesion) iterator.next();
				SelectItem item = new SelectItem();

				item.setValue(element.getId());
				item.setLabel(element.getDescripcion());

				result.add(item);
			}
		}
		return result;
	}


	/*
	 * Este metodo se utiliza para traer todos los tipos de vinculos familiares desde la base de datos y armar el selectitem.
	 */
	public static List cargarVinculo(List listAuxVinculo) {
		List result = new ArrayList();
		result.add(new SelectItem(new Long(0), "Seleccionar Vinculación Familiar"));

		if (!listAuxVinculo.isEmpty()) {
			Iterator iterator = listAuxVinculo.iterator();
			while (iterator.hasNext()) {
				Vinculo element = (Vinculo) iterator.next();
				SelectItem item = new SelectItem();

				item.setValue(element.getId());
				item.setLabel(element.getDescripcion());

				result.add(item);
			}
		}
		return result;
	}


	/*
	 * Este meodo se utiliza para eliminar un domicilio desde la tabla de domicilios.
	 */
	public static List eliminarDomicilio(List listaDomicilios, Long idDomicilio) {
		log.info("Ejecutando ==> eliminarDomicilio()");
		listaDomicilios.remove(buscarDomicilio(listaDomicilios, idDomicilio));

		if (!listaDomicilios.isEmpty())
			listaDomicilios.remove(buscarDomicilio(listaDomicilios, idDomicilio));

		if (listaDomicilios.isEmpty()) {
			listaDomicilios = new ArrayList();
		}
		return listaDomicilios;
	}


	/*
	 * Este método se utiliza para eliminar un domicilio del set de domicilios.
	 */
	public static Set eliminarDomicilioDelSet(Set listaDomicilios, Long idDomicilio) {
		log.info("Ejecutando ==> eliminarDomicilioDelSet()");
		Iterator iterIndDom = listaDomicilios.iterator();
		while (iterIndDom.hasNext()) {
			IndividuoDomicilio indDomAux = (IndividuoDomicilio) iterIndDom.next();
			if (indDomAux.getDomicilio().getIdDomicilio().equals(idDomicilio)) {
				listaDomicilios.remove(indDomAux);
				break;
			}
		}
		return listaDomicilios;
	}


	/*
	 * Este metodo se utiliza para bucar un domicilio.
	 */
	public static IndividuoDomicilio buscarDomicilio(List listaDomicilios, Long idDomicilio) {
		IndividuoDomicilio result = new IndividuoDomicilio();
		if (!listaDomicilios.isEmpty()) {
			Iterator iterator = listaDomicilios.iterator();
			while (iterator.hasNext()) {
				IndividuoDomicilio element = (IndividuoDomicilio) iterator.next();
				if (element.getDomicilio().getIdDomicilio().equals(idDomicilio)) {
					result = element;
				}
			}
		}
		return result;
	}


	/*
	 * Este metodo se utiliza para eliminar un telefono de la lista.
	 */
	public static List eliminarTelefono(List listaTelefono, Long idTelefono) {
		Telefonos telefonos = null;
		Iterator listaTel = listaTelefono.iterator();
		while (listaTel.hasNext()) {
			Telefonos te = (Telefonos) listaTel.next();
			if (te.getTelefono().getIdTelefono().equals(idTelefono)) {
				telefonos = te;
				break;
			}
		}
		if (telefonos != null) {
			listaTelefono.remove(telefonos);
		}
		if (listaTelefono.isEmpty())
			listaTelefono = new ArrayList();

		return listaTelefono;
	}


	public static Set eliminarTelefonoSet(Set listaTelefonos, Long idTelefono) {
		Telefonos telefonos = null;
		Iterator listaTel = listaTelefonos.iterator();
		while (listaTel.hasNext()) {
			Telefonos te = (Telefonos) listaTel.next();
			if (te.getTelefono().getIdTelefono().equals(idTelefono)) {
				telefonos = te;
				break;
			}
		}
		if (telefonos != null) {
			listaTelefonos.remove(telefonos);

			if (listaTelefonos.isEmpty()) {
				listaTelefonos = new HashSet();
			}
		}

		return listaTelefonos;
	}


	/*
	 * Este metodo se utliza para cargar el selectitem de sucursales
	 */
	public static List cargarSucursales(List list) {
		log.info("Ejecutando ==> cargarSucursales(List list, Long id)");
		List result = new ArrayList();
		result.add(new SelectItem(new Long(0), "Seleccionar Sucursal"));

		if (!list.isEmpty()) {
			// log.info("La lista no vino vacia.");
			Iterator iterator = list.iterator();
			while (iterator.hasNext()) {
				SucEmpresa element = (SucEmpresa) iterator.next();
				SelectItem item = new SelectItem();

				item.setValue(element.getIdSucEmpresa());
				item.setLabel(element.getDescripcion());
				result.add(item);
			}
		}
		return result;
	}


	/*
	 * Este metodo se utiliza para buscar un objeto telefono
	 */
	public static Telefono buscarTelefono(List listaTelefono, Long idTelefono) {
		Telefono result = null;

		if (!listaTelefono.isEmpty()) {
			Iterator iterator = listaTelefono.iterator();
			while (iterator.hasNext()) {
				Telefonos element = (Telefonos) iterator.next();

				if (element.getTelefono().getIdTelefono().equals(idTelefono)) {
					result = element.getTelefono();
				}
			}
		}

		return result;
	}


	public static Telefonos buscarTelefonos(Set listaTelefonos, Long idTelefono) {
		Telefonos result = null;

		if (!listaTelefonos.isEmpty()) {
			Iterator iterator = listaTelefonos.iterator();
			while (iterator.hasNext()) {
				Telefonos element = (Telefonos) iterator.next();

				if (element.getTelefono().getIdTelefono().equals(idTelefono)) {
					result = element;
				}
			}
		}

		return result;
	}


	/*
	 * Este metodo se utiliza para cargar la lista de bancos
	 */
	public static List cargarBancos(List listBanco) {
		List result = new ArrayList();
		result.add(new SelectItem(new Long(0), "Seleccionar Banco"));
		if (!listBanco.isEmpty()) {
			Iterator iterator = listBanco.iterator();
			while (iterator.hasNext()) {
				Banco element = (Banco) iterator.next();
				SelectItem item = new SelectItem();

				item.setValue(element.getIdBanco());
				item.setLabel(element.getDescripcion());

				result.add(item);
			}
		}
		return result;
	}


	/*
	 * Este metodo se utiliza para cargar el selectitem de Tipos d
	 */
	public static List cargarTipoDomicilio(List listDomicilioPago) {
		List result = new ArrayList();
		result.add(new SelectItem(new Long(0), "Seleccionar Domicilio Pago"));
		if (!listDomicilioPago.isEmpty()) {
			Iterator iterator = listDomicilioPago.iterator();
			while (iterator.hasNext()) {
				TipoDomicilio element = (TipoDomicilio) iterator.next();
				SelectItem item = new SelectItem();

				item.setValue(element.getIdTipoDomicilio());
				item.setLabel(element.getDescripcion());

				result.add(item);
			}
		}
		return result;
	}


	/*
	 * Este metodo se utiliza para poder general allsta de item select de tipos digitales.
	 */
	public static List cargarTipoDigitales(List listTipoDigital) {
		List result = new ArrayList();
		result.add(new SelectItem(new Long(0), "Tipo Documento Digital"));

		if (!listTipoDigital.isEmpty()) {
			Iterator iterator = listTipoDigital.iterator();
			while (iterator.hasNext()) {
				TipoDigital element = (TipoDigital) iterator.next();
				SelectItem item = new SelectItem();

				item.setValue(element.getIdTipoDigital());
				item.setLabel(element.getDescripcion());

				result.add(item);
			}
		}
		return result;
	}


	/*
	 * Este metodo se utiliza para devolver el nro de telefono de una sucursal.
	 */
	public static String devolverTelefono(List list, Long idSucursal) {
		log.info("Ejecutando devolverTelefono()");
		String result = "";

		Iterator iter = list.iterator();
		while (iter.hasNext()) {
			SucTelefono sucTelefono = (SucTelefono) iter.next();

			if (sucTelefono.getSucEmpresa().getIdSucEmpresa().equals(idSucursal)) {

				result += sucTelefono.getTelefono().getNroTlefono();
			}
		}
		return result;
	}


	/*
	 * este metodo se utiliza para cargar los tipos de cuentas.
	 */
	public static List cargarTiposCuentas(List tipoCuenta) {
		List result = new ArrayList();
		result.add(new SelectItem(new Long(0), "Seleccionar Tipo Cuenta"));

		Iterator iterator = tipoCuenta.iterator();
		while (iterator.hasNext()) {
			TipoCuentaBanco element = (TipoCuentaBanco) iterator.next();

			result.add(new SelectItem(element.getIdCuentaBanco(), element.getDescripcion()));
		}
		return result;

	}


	/*
	 * Este metodo se utiliza para poder cargar la antiguedad del individuo en la empresa en la que trabaja.
	 */
	public static int devolverCantidadAnios(Date fecha) {
		Calendar hoy = Calendar.getInstance();
		Calendar fecNac = Calendar.getInstance();
		fecNac.setTime(fecha);

		int anioActual = hoy.get(Calendar.YEAR);
		int mesActual = hoy.get(Calendar.MONTH);
		int diaActual = hoy.get(Calendar.DAY_OF_MONTH);

		int anioFecNac = fecNac.get(Calendar.YEAR);
		int mesFecNac = fecNac.get(Calendar.MONTH);
		int diaFecNac = fecNac.get(Calendar.DAY_OF_MONTH);

		int edad = (anioActual - anioFecNac - 1) + (mesActual == mesFecNac ? (diaActual >= diaFecNac ? 1 : 0) : mesActual >= mesFecNac ? 1 : 0);

		return edad;

	}


	public static List cargarOcupacion(List listAuxOcupacion) {
		List result = new ArrayList();
		result.add(new SelectItem(new Long(0), "Seleccionar Ocupación"));

		if (!listAuxOcupacion.isEmpty()) {
			Iterator iterator = listAuxOcupacion.iterator();

			while (iterator.hasNext()) {
				Ocupacion element = (Ocupacion) iterator.next();

				result.add(new SelectItem(element.getIdOcupacion(), element.getDescripcion()));
			}
		}
		return result;
	}


	public static List cargarVerificadores(List lstVerificadores) {
		List result = new ArrayList();
		result.add(new SelectItem(new Long(0), "Seleccionar Verificador"));
		if (!lstVerificadores.isEmpty()) {
			Iterator iterator = lstVerificadores.iterator();
			while (iterator.hasNext()) {
				Verificador element = (Verificador) iterator.next();
				SelectItem item = new SelectItem(element.getIdVerificador(), element.getIdVerificador() + " - " + element.getApellido());
				result.add(item);
			}
		}
		return result;
	}
}
