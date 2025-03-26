package com.bizitglobal.webapp.faces.beans.transacciones;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.faces.model.SelectItem;

import com.bizitglobal.tarjetafiel.commons.filtros.Filtro;
import com.bizitglobal.tarjetafiel.transacciones.exception.ClienteTransaccionException;
import com.bizitglobal.tarjetafiel.transacciones.exception.LineaCredComposException;
import com.bizitglobal.tarjetafiel.transacciones.exception.LineaCredHistoricoException;
import com.bizitglobal.tarjetafiel.transacciones.negocio.ClienteTransaccion;
import com.bizitglobal.tarjetafiel.transacciones.negocio.LineaCredCompos;
import com.bizitglobal.tarjetafiel.transacciones.negocio.LineaCredHistorico;
import com.bizitglobal.webapp.faces.beans.BaseBean;
import com.bizitglobal.webapp.faces.beans.util.Popup;
import com.bizitglobal.webapp.faces.beans.util.ScrollBean;
import com.bizitglobal.webapp.faces.util.Session;


@SuppressWarnings({"rawtypes","unchecked"})
public class LineaCreditoClienteBean extends BaseBean {

	private ClienteTransaccion cliente;
	private ClienteTransaccion clienteAux;
	private List clienteList;
	private List listaCliente;;
	private List lineaCredHistorico;
	private List lineaCredCompos;
	private LineaCredCompos lineaCred;
	private boolean clienteCargado;
	private boolean tieneLineaCredito;
	private boolean titular;
	private boolean adicional;
	private boolean cambiarLimite;
	private boolean buscar;
	/* @F5224 */private boolean continuar;
	private List cantidadDias = new ArrayList();
	private Long monto;
	private Long idSeleccionada;


	public LineaCreditoClienteBean() {
		super();
		borrar();
	}


	@Override
	public void borrar() {
		// TODO Auto-generated method stub
		super.borrarBaseBean();
		popup.borrar();
		error.borrar();
		tituloLargo = "TARJETA FIEL";
		tituloCorto = "Línea de Crédito Temporal";
		setClienteList(new ArrayList());
		clienteCargado = false;
		setCliente(new ClienteTransaccion());
		listaCliente = new ArrayList();
		monto = new Long(0);
		titular = false;
		adicional = false;
		cambiarLimite = false;
		buscar = true;
		tieneLineaCredito = false;
		cantidadDias.clear();
		cantidadDias.add(new SelectItem(new Long(1), "1"));
		cantidadDias.add(new SelectItem(new Long(2), "2"));
	}


	@Override
	public String inicializar() {
		borrarBaseBean();
		borrar();
		if (Session.getBean("ScrollBean") != null) {
			ScrollBean bean = (ScrollBean) Session.getBean("ScrollBean");
			bean.setHiddenScrollY(new Integer(0));
		}
		return "lineaCreditoCliente";
	}


	@Override
	public boolean validar() {
		// TODO Auto-generated method stub
		Iterator iter = listaCliente.iterator();

		while (iter.hasNext()) {
			WrappersLineaCreditoTemporal cli = (WrappersLineaCreditoTemporal) iter.next();
			if (cli.getCondicion().equals("Adicional")) {
				if (cli.cliente.getHabilitadoConsumo().equals("H")) {
					setCambiarLimite(true);
				} else {
					error.agregar("El Cliente se encuentra Desabilitado para Consumo");
				}
			} else {
				if (cli.titular.getHabilitadoConsumo().equals("H")) {
					setCambiarLimite(true);
				} else {
					error.agregar("El Cliente se encuentra Desabilitado para Consumo");
				}
			}
		}

		if (getLineaCredCompos() != null && getLineaCredCompos().size() > 0) {
			List listLineacred = new ArrayList();
			listLineacred.addAll(getLineaCredCompos());
			Iterator iterador = listLineacred.iterator();
			while (iterador.hasNext()) {
				setLineaCred((LineaCredCompos) iterador.next());

				if (getLineaCred().getTipo().equals("TEMPORAL")) {
					tieneLineaCredito = true;
				} else {
					tieneLineaCredito = false;
					getLineaCredCompos().remove(getLineaCred());
				}
			}
		}
		return false;
	}


	public void buscarCliente() {
		error.borrar();
		titular = false;
		adicional = false;
		cambiarLimite = false;
		tieneLineaCredito = false;
		List clientes = new ArrayList();
		try {
			Filtro filtro = new Filtro();

			if (cliente.getIndividuo().getNroDocumento() != null && cliente.getIndividuo().getNroDocumento().compareTo("") != 0) {
				filtro.agregarCampoOperValor("individuo.nroDocumento", Filtro.IGUAL, cliente.getIndividuo().getNroDocumento());
			}
			if (cliente.getIndividuo().getCuil() != null && cliente.getIndividuo().getCuil().compareTo("") != 0) {
				filtro.agregarCampoOperValor("individuo.cuil", Filtro.IGUAL, cliente.getIndividuo().getCuil());
			}
			if (cliente.getIndividuo().getApellido() != null && cliente.getIndividuo().getApellido().compareTo("") != 0) {
				filtro.agregarCampoOperValor("individuo.apellido", Filtro.LIKE, cliente.getIndividuo().getApellido());
			}
			if (cliente.getIndividuo().getNombres() != null && cliente.getIndividuo().getNombres().compareTo("") != 0) {
				filtro.agregarCampoOperValor("individuo.nombres", Filtro.LIKE, cliente.getIndividuo().getNombres());
			}
			if (cliente.getIdCliente() != null && !cliente.getIdCliente().equals(new Long(0))) {
				filtro.agregarCampoOperValor("idCliente", Filtro.IGUAL, cliente.getIdCliente());
			}
			if (!filtro.estaVacio()) {
				buscar = false;
				clienteList = transaccionesService.getClienteTransaccionService().getCliente(filtro);

				if (getClienteList() != null && getClienteList().size() > 0) {
					setClienteCargado(true);
				} else {
					setClienteCargado(false);
					setBuscar(true);
				}
			} else {
				buscar = true;
				error.agregar("Debe ingresar algún filtro para realizar la búsqueda");
			}

		} catch (ClienteTransaccionException e) {
			e.printStackTrace();
		}

	}


	public void editarLimiteCredito() {
		clienteCargado = false;
		listaCliente = new ArrayList();

		Long idCliente = new Long(Session.getRequestParameter("idCliente"));
		try {
			clienteAux = transaccionesService.getClienteTransaccionService().leerCliente(idCliente);

			/* @I5224 */if (null == clienteAux.getIndividuo().getDomicilio())
			{
				continuar = true;
				popup.setNombreIcono(Popup.ICONO_FALLA);
				popup.setMensaje("El cliente " + clienteAux.getIdCliente() + " no tiene asignado un domicilio. Presione Continuar o Salir ");
				popup.setMostrar(true);
				/* @F5224 */}

			Filtro filtro = new Filtro();
			// Veo si es Titular o Adicional
			if (clienteAux.getIdTitular() != null) {
				// Es Adicional
				titular = false;
				adicional = true;
				// Obtengo el titular del Adicional
				ClienteTransaccion titular = transaccionesService.getClienteTransaccionService().leerCliente(clienteAux.getIdTitular());
				if (titular != null) {
					listaCliente.add(new WrappersLineaCreditoTemporal(clienteAux, titular));
					filtro.agregarCampoOperValor("cliente.idCliente", Filtro.IGUAL, titular.getIdCliente());
					filtrar(filtro);
				} else {
					error.agregar("No se ha encontrado al Titular");
				}
			} else {
				titular = true;
				adicional = false;
				listaCliente.add(new WrappersLineaCreditoTemporal(clienteAux));
				filtro.agregarCampoOperValor("cliente.idCliente", Filtro.IGUAL, clienteAux.getIdCliente());
				filtrar(filtro);
			}
			validar();
		} catch (ClienteTransaccionException e) {
			e.printStackTrace();
		}
		Session.redirect("/tarjetafiel/transacciones/lineaCreditoCliente.jsf");
	}


	/*
	 * Busco si el titular ya tenia una linea de credito vigente
	 */
	private void filtrar(Filtro filtro) {
		try {
			filtro.agregarCampoOperValor("tipo", Filtro.LIKEXS, "TEMPORAL");
			setLineaCredCompos(transaccionesService.getLineaCredComposService().getLineaCerdCompos(filtro));
		} catch (LineaCredComposException e) {
			e.printStackTrace();
		}
	}


	public void aceptarLimite() {
		LineaCredCompos lineaCredCompos = new LineaCredCompos();
		int dia = 0;
		/* I5374 */try {
			Iterator iter = listaCliente.iterator();
			while (iter.hasNext()) {
				Calendar calendar = Calendar.getInstance();
				dia = (calendar.get(Calendar.DAY_OF_MONTH) + idSeleccionada.intValue());
				calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), dia);

				WrappersLineaCreditoTemporal cli = (WrappersLineaCreditoTemporal) iter.next();

				lineaCredCompos.setCliente(cli.getTitular());
				lineaCredCompos.setImporte(monto);
				lineaCredCompos.setTipo("TEMPORAL");
				lineaCredCompos.setFechaDesde(new Timestamp(new Date().getTime()));
				lineaCredCompos.setFechaHasta(new Timestamp(new Date().getTime()));
				lineaCredCompos.setFechaUltModif(new Timestamp(new Date().getTime()));
				/* F5374 */lineaCredCompos.setOperador(Session.getOperador());

				if (tieneLineaCredito == true) {
					// Tenia Linea credito Temporal
					LineaCredHistorico lineaCredHist = new LineaCredHistorico();
					lineaCredHist.setCliente(cli.getTitular());
					Iterator iter2 = getLineaCredCompos().iterator();
					while (iter2.hasNext()) {
						// Recupero la linea de Credito anterior para grabarla en el
						// Historico
						LineaCredCompos lineaCom = (LineaCredCompos) iter2.next();

						/* F5374 */lineaCredHist.setOperador(lineaCom.getOperador());
						lineaCredHist.setFechaDesde(lineaCom.getFechaDesde());
						lineaCredHist.setFechaHasta(lineaCom.getFechaHasta());
						lineaCredHist.setFechaUltModif(new Timestamp(new Date().getTime()));
						lineaCredHist.setImporte(lineaCom.getImporte());
						lineaCredHist.setTipo(lineaCom.getTipo());
						try {
							// borro la Anterior
							transaccionesService.getLineaCredComposService().borrarLineaCerdCompos(lineaCom);
							// inserto la Nueva
							if (monto != null && monto != 0) {
								transaccionesService.getLineaCredComposService().grabarLineaCredCompos(lineaCredCompos);
							}
						} catch (LineaCredComposException e) {
							e.printStackTrace();
						}
						try {
							// Grabo en el Historico
							transaccionesService.getLineaCredHistoricoService().grabarLineaCredHistorico(lineaCredHist);
						} catch (LineaCredHistoricoException e) {
							e.printStackTrace();
						}

						try {
							// resto el limite Temporal anterior
							cli.getTitular().setLimiteCredito(
									cli.getTitular().getLimiteCredito().subtract(
											BigDecimal.valueOf(lineaCom.getImporte())));
							// sumo el nuevo limite Temporal
							cli.getTitular().setLimiteCredito(
									cli.getTitular().getLimiteCredito().add(BigDecimal.valueOf(monto)));
							transaccionesService.getClienteTransaccionService().actualizarCliente(cli.getTitular());
						} catch (ClienteTransaccionException e) {
							e.printStackTrace();
						}
						if (monto != null && monto != 0) {
							continuar = false;
							/* @F5224 */popup.setPopup(popup.ICONO_OK,
									"El Proceso termino exitosamente. El nuevo limite de Credito es de: "
											+ cli.getTitular().getLimiteCredito() + " Presione Salir");
						} else {
							continuar = false;
							/* @F5224 */popup.setPopup(popup.ICONO_OK,
									"El Proceso termino exitosamente. Se elimino la linea de Credito." + " Presione Salir");
						}
					}
				} else {
					if (monto != null && monto != 0) {
						// No Linea credito Temporal
						try {
							transaccionesService.getLineaCredComposService()
									.grabarLineaCredCompos(lineaCredCompos);
						} catch (LineaCredComposException e) {
							e.printStackTrace();
						}
						try {
							cli.getTitular().setLimiteCredito(
									cli.getTitular().getLimiteCredito().add(BigDecimal.valueOf(monto)));
							transaccionesService.getClienteTransaccionService().actualizarCliente(cli.getTitular());
						} catch (ClienteTransaccionException e) {
							e.printStackTrace();
						}
						continuar = false;
						/* @F5224 */popup.setPopup(popup.ICONO_OK,
								"El Proceso termino exitosamente. El nuevo limite de Credito es de: "
										+ cli.getTitular().getLimiteCredito() + " Presione Salir");
					} else {
						error.agregar("Inserte un monto");
					}
				}
			}
			/* F5374 */} catch (Exception e) {
			e.printStackTrace();
			/* F5374 */}
	}


	/**
	 * @id: 5224 Method: irAContinuar Description: Boton continuar de la ventana Popup
	 * @return
	 */
	public String irAContinuar() {
		popup.borrar();
		return "";
	}


	public void volverBuscar() {
		clienteCargado = false;
		titular = false;
		adicional = false;
		cambiarLimite = false;
		buscar = true;
		tieneLineaCredito = false;
		monto = new Long(0);
		cliente = new ClienteTransaccion();
		cantidadDias = new ArrayList();
		cantidadDias.clear();
		cantidadDias.add(new SelectItem(new Long(1), "1"));
		cantidadDias.add(new SelectItem(new Long(2), "2"));
		popup.borrar();

	}


	private void setClienteAux(ClienteTransaccion clienteAux) {
		this.clienteAux = clienteAux;
	}


	private ClienteTransaccion getClienteAux() {
		return clienteAux;
	}


	public void setLineaCredCompos(List lineaCredCompos) {
		this.lineaCredCompos = lineaCredCompos;
	}


	public List getLineaCredCompos() {
		return lineaCredCompos;
	}


	public void setLineaCredHistorico(List lineaCredHistorico) {
		this.lineaCredHistorico = lineaCredHistorico;
	}


	public List getLineaCredHistorico() {
		return lineaCredHistorico;
	}


	public void setClienteCargado(boolean clienteCargado) {
		this.clienteCargado = clienteCargado;
	}


	public boolean isClienteCargado() {
		return clienteCargado;
	}


	public void setCliente(ClienteTransaccion cliente) {
		this.cliente = cliente;
	}


	public ClienteTransaccion getCliente() {
		return cliente;
	}


	public void setClienteList(List clienteList) {
		this.clienteList = clienteList;
	}


	public List getClienteList() {
		return clienteList;
	}


	public void setListaCliente(List listaCliente) {
		this.listaCliente = listaCliente;
	}


	public List getListaCliente() {
		return listaCliente;
	}


	public void setTitular(boolean titular) {
		this.titular = titular;
	}


	public boolean isTitular() {
		return titular;
	}


	public void setAdicional(boolean adicional) {
		this.adicional = adicional;
	}


	public boolean isAdicional() {
		return adicional;
	}


	public void setCambiarLimite(boolean cambiarLimite) {
		this.cambiarLimite = cambiarLimite;
	}


	public boolean isCambiarLimite() {
		return cambiarLimite;
	}


	public void setMonto(Long monto) {
		this.monto = monto;
	}


	public Long getMonto() {
		return monto;
	}


	public void setBuscar(boolean buscar) {
		this.buscar = buscar;
	}


	public boolean isBuscar() {
		return buscar;
	}


	public void setLineaCred(LineaCredCompos lineaCred) {
		this.lineaCred = lineaCred;
	}


	public LineaCredCompos getLineaCred() {
		return lineaCred;
	}


	public void setTieneLineaCredito(boolean tieneLineaCredito) {
		this.tieneLineaCredito = tieneLineaCredito;
	}


	public boolean isTieneLineaCredito() {
		return tieneLineaCredito;
	}


	public void setCantidadDias(List cantidadDias) {
		this.cantidadDias = cantidadDias;
	}


	public List getCantidadDias() {
		return cantidadDias;
	}


	public void setIdSeleccionada(Long idSeleccionada) {
		this.idSeleccionada = idSeleccionada;
	}


	public Long getIdSeleccionada() {
		return idSeleccionada;
	}

	public class WrappersLineaCreditoTemporal {

		private ClienteTransaccion cliente;
		private ClienteTransaccion titular;
		private BigDecimal disponible;
		private String nombreCliente;
		private String nombreTitular;
		private String condicion;


		/**
		 * Para armar la lista del Titular
		 * */
		public WrappersLineaCreditoTemporal(ClienteTransaccion titular) {
			super();
			this.titular = titular;
			this.disponible = this.titular.getLimiteCredito().subtract(
					this.titular.getSaldoLinea());
			this.nombreTitular = this.titular.getIndividuo().getApellido()
					.toUpperCase()
					+ ", "
					+ this.titular.getIndividuo().getNombres().toUpperCase();
			this.condicion = "Titular";
		}


		/**
		 * Para armar la lista del Adicional
		 * */
		public WrappersLineaCreditoTemporal(ClienteTransaccion cliente,
				ClienteTransaccion titular) {
			super();
			this.setCliente(cliente);
			this.titular = titular;
			this.disponible = this.titular.getLimiteCredito().subtract(
					this.titular.getSaldoLinea());
			this.setNombreCliente(this.cliente.getIndividuo().getApellido()
					.toUpperCase()
					+ ", "
					+ this.cliente.getIndividuo().getNombres().toUpperCase());
			this.setNombreTitular(this.titular.getIndividuo().getApellido()
					.toUpperCase()
					+ ", "
					+ this.titular.getIndividuo().getNombres().toUpperCase());
			this.condicion = "Adicional";

		}


		public WrappersLineaCreditoTemporal() {
			this.setCliente(new ClienteTransaccion());
			this.setTitular(new ClienteTransaccion());
		}


		public void setCliente(ClienteTransaccion cliente) {
			this.cliente = cliente;
		}


		public ClienteTransaccion getCliente() {
			return cliente;
		}


		public void setDisponible(BigDecimal disponible) {
			this.disponible = disponible;
		}


		public BigDecimal getDisponible() {
			return disponible;
		}


		public void setCondicion(String condicion) {
			this.condicion = condicion;
		}


		public String getCondicion() {
			return condicion;
		}


		public void setTitular(ClienteTransaccion titular) {
			this.titular = titular;
		}


		public ClienteTransaccion getTitular() {
			return titular;
		}


		public void setNombreCliente(String nombreCliente) {
			this.nombreCliente = nombreCliente;
		}


		public String getNombreCliente() {
			return nombreCliente;
		}


		public void setNombreTitular(String nombreTitular) {
			this.nombreTitular = nombreTitular;
		}


		public String getNombreTitular() {
			return nombreTitular;
		}

	}


	public boolean isContinuar() {
		return continuar;
	}


	public void setContinuar(boolean continuar) {
		this.continuar = continuar;
	}

}
