package com.bizitglobal.webapp.faces.beans.transacciones;

import java.text.DecimalFormat;
import java.util.Calendar;

import com.bizitglobal.tarjetafiel.transacciones.exception.ClienteTransaccionException;
import com.bizitglobal.tarjetafiel.transacciones.exception.ConceptoException;
import com.bizitglobal.tarjetafiel.transacciones.negocio.ClienteTransaccion;
import com.bizitglobal.tarjetafiel.transacciones.negocio.Concepto;
import com.bizitglobal.webapp.faces.beans.BaseBean;
import com.bizitglobal.webapp.faces.util.Session;


/**
 * @id 4945
 * @author Hernan Guillen. Bizit Global - Año 2012
 */
public class NotaDebitoJudicialBean extends BaseBean {
	private DecimalFormat formateador = new DecimalFormat("#0.00;");
	private Long nroCuenta;
	private ClienteTransaccion cliente;
	private Double saldo;
	private Double monto;
	private boolean verDetalles;
	private Calendar cal = Calendar.getInstance();


	public NotaDebitoJudicialBean() {
		super();
		cal.add(Calendar.DATE, 1);
	}


	public boolean isVerDetalles() {
		return verDetalles;
	}


	public void setVerDetalles(boolean verDetalles) {
		this.verDetalles = verDetalles;
	}


	public Double getMonto() {
		return monto;
	}


	public void setMonto(Double monto) {
		this.monto = monto;
	}


	public String getSaldo() {
		return formateador.format(saldo);
	}


	public Long getNroCuenta() {
		return nroCuenta;
	}


	public void setNroCuenta(Long nroCuenta) {
		this.nroCuenta = nroCuenta;
	}


	public ClienteTransaccion getCliente() {
		return cliente;
	}


	public void setCliente(ClienteTransaccion cliente) {
		this.cliente = cliente;
	}


	@Override
	public void borrar() {
		borrarBaseBean();
		nroCuenta = null;
		cliente = null;
		saldo = null;
		monto = 0.0;
		verDetalles = false;
	}


	@Override
	public boolean validar() {
		error.borrar();
		boolean valido = true;
		if (monto == null || monto <= 0.0) {
			error.agregar("El monto debe ser mayor a 0");
			valido = false;
		}
		if (monto > saldo * -1) {
			error.agregar("El monto debe ser igual o inferior al saldo del cliente");
			valido = false;
		}
		return valido;
	}


	@Override
	public String inicializar() {
		borrar();
		tituloLargo = "CLIENTES";
		tituloCorto = "Nota de Débito Judicial Manual";
		return "notaDebitoJudicialManual";
	}


	public String buscar() {
		error.borrar();
		if (nroCuenta != null && !nroCuenta.equals(0L)) {
			try {
				cliente = transaccionesService.getClienteTransaccionService().leerCliente(nroCuenta);
				int idEstadoCobranza = cliente.getEstadoCobranza().getIdEstadoCobranza().intValue();
				if (idEstadoCobranza == 9 || idEstadoCobranza == 10) {
					cliente.getIndividuo().getNombres();
					leerSaldo();
					if (saldo < 0) {
						verDetalles = true;
					} else {
						error.agregar("Para poder realizar notas de débito el saldo del cliente debe ser negativo.");
					}
				} else {
					error.agregar("La cuenta solicitada no se encuentra en estado judicial. \nNo es posible realizarle notas de débito judicial.");
				}
			} catch (ClienteTransaccionException e) {
				error.agregar("Ocurrio un error al intentar leer el cliente.");
				e.printStackTrace();
			}
		} else {
			error.agregar("Debe ingresar un nro de cuenta valido.");
		}
		return null;
	}


	public void cancelar() {
		borrar();
	}


	private void leerSaldo() {
		try {
			saldo = transaccionesService.getCtaCteClienteService().getSaldoAnteriorComposicionSaldo(cal.getTime(), nroCuenta);
		} catch (Exception e) {
			error.agregar("Ocurrio un error al intentar consultar el saldo del cliente.");
			e.printStackTrace();
		}
	}


	public String crear() {
		try {
			if (validar()) {
				double capital = monto / 1.21;
				transaccionesService.getNotaDebitoSinIvaService().registrarNotaDebito(
						transaccionesService.getClienteTransaccionService().leerCliente(cliente.getIdCliente()),
						transaccionesService.getConceptoService().getConceptoPorCodigo(Long.parseLong(Concepto.CODIGO_ND_CON_IVA_JUDICIAL)),
						Session.getOperador(), capital);
				leerSaldo();
				popup.setPopup(popup.ICONO_OK, "La nota de débito se genero exitosamente.");
			}
		} catch (ConceptoException e) {
			error.agregar("Ocurrio un error al intentar leer el concepto.");
			e.printStackTrace();
		} catch (NumberFormatException e) {
			error.agregar("Ocurrio un error al intentar generar la nota de débito.");
			e.printStackTrace();
		} catch (ClienteTransaccionException e) {
			error.agregar("Ocurrio un error al intentar leer el cliente.");
			e.printStackTrace();
		} catch (Exception e) {
			error.agregar("Ocurrio un error al intentar grabar la nota de débito.");
			e.printStackTrace();
		}
		return null;
	}


	public String irANuevaNotaDebito() {
		return inicializar();
	}


	public String irASalir() {
		return "inicio";
	}

}
