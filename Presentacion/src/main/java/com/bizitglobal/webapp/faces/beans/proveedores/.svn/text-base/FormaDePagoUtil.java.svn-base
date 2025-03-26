package com.bizitglobal.webapp.faces.beans.proveedores;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.faces.model.SelectItem;

import org.apache.log4j.Logger;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.contabilidad.dao.PlanCuentaDosDao;
import com.bizitglobal.tarjetafiel.contabilidad.negocio.PlanCuentaDos;
import com.bizitglobal.tarjetafiel.general.dao.BancoDao;
import com.bizitglobal.tarjetafiel.general.dao.FormaPagoDao;
import com.bizitglobal.tarjetafiel.general.dao.SucursalFormaPagoDao;
import com.bizitglobal.tarjetafiel.general.dao.TipoCuentaBancoDao;
import com.bizitglobal.tarjetafiel.general.negocio.FormaPago;
import com.bizitglobal.tarjetafiel.general.negocio.SucursalFormaPago;
import com.bizitglobal.tarjetafiel.general.negocio.TipoCuentaBanco;
import com.bizitglobal.webapp.faces.util.Util;


@SuppressWarnings({"rawtypes","unchecked"})
public class FormaDePagoUtil {
	private static final Logger log = Logger.getLogger(FormaDePagoUtil.class);


	/*
	 * public static List cargarListaFormasDePago(FormaPagoDao formaPagoDao) { List result = new ArrayList(); Filtro filtro= new Filtro();
	 * 
	 * List formaPagoList = formaPagoDao.listarTodos(filtro) ; if(!formaPagoList.isEmpty()) { SelectItem item = new SelectItem(); Iterator formasPago
	 * = formaPagoList.iterator(); while(formasPago.hasNext()) { item = new SelectItem(); FormaPago aux = (FormaPago)formasPago.next();
	 * item.setValue(aux.getIdFormaPago()); item.setLabel(aux.getDescripcion()); result.add(item); } } return result; }
	 */

	public static List cargarListaFormasDePago(FormaPagoDao formaPagoDao, Filtro filtro) {
		List result = new ArrayList();
		List formaPagoList = formaPagoDao.listarTodos(filtro);
		if (!formaPagoList.isEmpty()) {
			SelectItem item = new SelectItem();
			Iterator formasPago = formaPagoList.iterator();
			while (formasPago.hasNext()) {
				item = new SelectItem();
				FormaPago aux = (FormaPago) formasPago.next();
				item.setValue(aux.getIdFormaPago());
				item.setLabel(aux.getDescripcion());
				result.add(item);
			}
		}
		return result;
	}


	public static List cargarListaFormasDePago(SucursalFormaPagoDao sucursalFormaPagoDao, String idSucFiel) {
		List result = new ArrayList();
		Filtro filtro = new Filtro();
		filtro.agregarCampoOperValor("sucursalFiel.idSucursal", Filtro.IGUAL, new Long(idSucFiel));
		List sucursalFormaPagoList = sucursalFormaPagoDao.listarTodos(filtro);
		if (!sucursalFormaPagoList.isEmpty()) {
			Iterator sucursalFormasPago = sucursalFormaPagoList.iterator();
			while (sucursalFormasPago.hasNext()) {
				SelectItem item = new SelectItem();
				SucursalFormaPago aux = (SucursalFormaPago) sucursalFormasPago.next();
				item.setValue(aux.getFormaPago().getIdFormaPago());
				item.setLabel(aux.getFormaPago().getDescripcion());
				result.add(item);
			}
		}
		return result;
	}


	public static List cargarListaCuentasFondos(PlanCuentaDosDao planCuentaDao, Long idFormaPago) {
		List result = new ArrayList();
		Filtro filtro = new Filtro();
		/* @I4294 */if (idFormaPago == 1L) {
			filtro.agregarCampoOperValor("formaPago.idFormaPago", Filtro.IN, "1,6,11");
		}
		else {
			filtro.agregarCampoOperValor("formaPago.idFormaPago", Filtro.IGUAL, idFormaPago);
			/* @F4294 */}
		filtro.agregarOrderBy("idPlanCuenta");
		List cuentasFondosList = planCuentaDao.listarTodos(filtro);
		if (!cuentasFondosList.isEmpty()) {
			Iterator cuentasIterator = cuentasFondosList.iterator();
			while (cuentasIterator.hasNext()) {
				SelectItem item = new SelectItem();
				PlanCuentaDos aux = (PlanCuentaDos) cuentasIterator.next();
				item.setValue(aux.getIdPlanCuenta());
				item.setLabel(aux.getIdPlanCuenta().toString() + "-" + aux.getTitulo());
				result.add(item);
			}
		}
		return result;
	}


	public static List cargarListaCuentasBancos(TipoCuentaBancoDao cuentaBancoDao) {
		List result = new ArrayList();
		List cuentaBancoList = cuentaBancoDao.listarTodos();
		if (!cuentaBancoList.isEmpty()) {
			Iterator cuentaBancos = cuentaBancoList.iterator();
			while (cuentaBancos.hasNext()) {
				SelectItem item = new SelectItem();
				TipoCuentaBanco aux = (TipoCuentaBanco) cuentaBancos.next();
				item.setValue(new Integer(aux.getIdCuentaBanco().intValue()));
				item.setLabel(aux.getDescripcion());
				result.add(item);
			}
		}

		return result;
	}


	public static List cargarListaDeBancos(BancoDao bancoDao) {
		List result = new ArrayList();
		result.add(new SelectItem(new Long(0), ("Seleccione Banco")));
		Filtro filtro = new Filtro();
		filtro.agregarOrderBy("idBanco");
		result.addAll(Util.cargarSelectItem(bancoDao.listarTodos(filtro)));
		return result;
	}


	public static String getFormaPagoDeLista(List formasDePago, Long id) {
		String result = null;
		if (!formasDePago.isEmpty()) {
			Iterator iter = formasDePago.iterator();
			while (iter.hasNext()) {
				SelectItem aux = (SelectItem) iter.next();
				if (new Long(aux.getValue().toString()).equals(id)) {
					result = aux.getLabel();
				}
			}
		}
		return result;
	}
	//
	// public static String getDescriBnanco(List bancos,Long id) {
	// String result = null;
	// if(!bancos.isEmpty()) {
	// Iterator iter = formasDePago.iterator();
	// while(iter.hasNext()) {
	// SelectItem aux = (SelectItem)iter.next();
	// if(id.equals(new Long(aux.getValue().toString()))) {
	// result = aux.getLabel();
	// }
	// }
	// }
	// return result;
	// }
}
