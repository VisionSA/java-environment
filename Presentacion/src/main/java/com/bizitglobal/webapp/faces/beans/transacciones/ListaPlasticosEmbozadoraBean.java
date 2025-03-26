package com.bizitglobal.webapp.faces.beans.transacciones;

import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;

import com.bizitglobal.tarjetafiel.commons.util.Convertidores;
import com.bizitglobal.tarjetafiel.operador.negocio.Operador;
import com.bizitglobal.tarjetafiel.transacciones.exception.ClienteTransaccionException;
import com.bizitglobal.tarjetafiel.transacciones.exception.EstadoLoteException;
import com.bizitglobal.tarjetafiel.transacciones.exception.PlasticoClienteException;
import com.bizitglobal.tarjetafiel.transacciones.exception.PlasticoLoteException;
import com.bizitglobal.tarjetafiel.transacciones.negocio.ClienteTransaccion;
import com.bizitglobal.tarjetafiel.transacciones.negocio.EstadoLote;
import com.bizitglobal.tarjetafiel.transacciones.negocio.PlasticoCliente;
import com.bizitglobal.tarjetafiel.transacciones.negocio.PlasticoHistorial;
import com.bizitglobal.tarjetafiel.transacciones.negocio.PlasticoLote;
import com.bizitglobal.tarjetafiel.transacciones.negocio.PlasticoLugar;
import com.bizitglobal.tarjetafiel.transacciones.negocio.PlasticoOperacion;
import com.bizitglobal.webapp.faces.beans.BaseBean;
import com.bizitglobal.webapp.faces.beans.util.Popup;
import com.bizitglobal.webapp.faces.beans.util.ScrollBean;
import com.bizitglobal.webapp.faces.util.PDFUtil;
import com.bizitglobal.webapp.faces.util.Session;


public class ListaPlasticosEmbozadoraBean extends BaseBean {
	private static final Logger log = Logger.getLogger(ListaPlasticosEmbozadoraBean.class);
	private boolean limpiarSeleccion;
	private Operador operador;
	private List<PlasticoCliente> listaTarjetas;
	private List<WrapperCuenta> listaCuentas;
	private boolean mostrarBotonConfirmar;
	private FileWriter fw = null; // Objetos para manejar el archivo
	private boolean aborto = false;


	public List<WrapperCuenta> getListaCuentas() {
		return listaCuentas;
	}


	public void setListaCuentas(List<WrapperCuenta> listaCuentas) {
		this.listaCuentas = listaCuentas;
	}


	public boolean isMostrarBotonConfirmar() {
		return mostrarBotonConfirmar;
	}


	public void setMostrarBotonConfirmar(boolean mostrarBotonConfirmar) {
		this.mostrarBotonConfirmar = mostrarBotonConfirmar;
	}


	public boolean isAborto() {
		return aborto;
	}


	public void setAborto(boolean aborto) {
		this.aborto = aborto;
	}


	/************************************************************************
	 * ACCIONES DEL BEAN DE LISTAPLASTICOSEMBOZADORA
	 ************************************************************************/

	public String inicializar() {
		borrar();
		if (Session.getBean("ScrollBean") != null)
		{
			ScrollBean bean = (ScrollBean) Session.getBean("ScrollBean");
			bean.setHiddenScrollY(new Integer(0));
		}

		try {
			listaTarjetas = transaccionesService.getPlasticoClienteService().obtenerPlasticosAEmbozar();
			if (!listaTarjetas.isEmpty()) {
				WrapperCuenta ultimoWCuenta = null;
				for (PlasticoCliente p : listaTarjetas) {
					if (p.getOperacion() == null) {
						error.agregar("Error, debe asignarle un codigo de operacion al plastico con id " + p.getIdPlasticocliente()
								+ " para poder continuar");
						aborto = true;
					}
					else {
						if (ultimoWCuenta == null) {
							ultimoWCuenta = new WrapperCuenta(p);
							listaCuentas.add(ultimoWCuenta);
						} else {

							if (ultimoWCuenta.pertenece(p)) {
								ultimoWCuenta.agregarPlastico(p);
							} else {
								ultimoWCuenta = new WrapperCuenta(p);
								listaCuentas.add(ultimoWCuenta);
							}

						}
					}
				}
				if (aborto == true)
					listaCuentas = null;

			}

		} catch (PlasticoClienteException e) {
			log.info("ERROR, al intentar leer los plasticos pendientes de embozar");
			e.printStackTrace();

		}
		operador = Session.getOperador();// nombre del operador
		return "listarPlasticosEmbozadora";
	}


	private String getInfoPlasticoCliente(PlasticoCliente p) {
		String s = "";
		if (p.esPlasticoTitular())
			s += "Titular -  ";
		else
			s += "Adicional - ";
		s += p.getClienteTransaccion().getIndividuo().getApellido().toUpperCase() + ", "
				+ p.getClienteTransaccion().getIndividuo().getNombres().toUpperCase();
		s += " Nro:" + p.getNumero();
		return s;
	}


	public String seguirConfirmando() {
		popup.setMostrar(false);
		return null;
	}


	public String cancelar() {
		return "inicio";
	}


	public void borrar() {
		super.borrarBaseBean();
		error.borrar();
		tituloLargo = "TARJETA FIEL";
		tituloCorto = "Embozado de Tarjetas";
		limpiarSeleccion = false;
		listaCuentas = new ArrayList<WrapperCuenta>();
		listaTarjetas = null;
		mostrarBotonConfirmar = false;
		aborto = false;
	}


	// Botones
	/**
	 * @id: 4877 Method: procesarEmbozado Description:
	 * @return
	 */
	public String procesarEmbozado() {
		try {
			EstadoLote estadoLote = transaccionesService.getEstadoLoteService().obtenerEstadoLote(4L);
			PlasticoLote lote = new PlasticoLote(null, null, null, new Date(), null, null, null, null, null,null, estadoLote, PlasticoLote.MANUAL);

			PlasticoLugar plasticoLugar = transaccionesService.getPlasticoLugarService().get(9L);
			String directorioLote = null;
			for (WrapperCuenta w : this.listaCuentas) {
				if (w.isEmbozar()) {
					for (PlasticoCliente p : w.getPlasticosAsociados()) {
						p.setUltimamodifFlex(new Date());
						p.setOperador(this.operador);
						p.setPlasticoLote(lote);
						p.setPlasticoLugar(plasticoLugar);
						p.getPlasticoHistorialSet().add(new PlasticoHistorial(p));
						lote.getPlasticos().add(p);
					}
				}
			}

			transaccionesService.getPlasticoLoteService().guardarPlasticoLote(lote);

			transaccionesService.getPlasticoLoteService().generarArchivoEmbozadoPorLote(lote, this.operador);
			lote.setArchivoAcuses(PDFUtil.generarPDFAcusesPlasticos(lote.getPlasticos(), lote.getIdPlastLote()));
			
			lote.setEstadoLote(transaccionesService.getEstadoLoteService().obtenerEstadoLote(1L));
			transaccionesService.getPlasticoLoteService().modificarPlasticoLote(lote);
			mostrarBotonConfirmar = true;
			popup.setPopup(
					Popup.ICONO_OK,
					"Los embozos seleccionados fueron procesados con exito con nro de lote "
							+ Convertidores.completarIzquierda(lote.getIdPlastLote().toString(), new Character('0'), 6));

		} catch (EstadoLoteException e) {
			log.info("Error al obtener el estado del lote con id 4L o 1L");
			popup.setPopup(Popup.ICONO_FALLA, "Se produjo un error cuando se intentaba procesar los embozos de las cuentas seleccionadas(1)");
			e.printStackTrace();
		} catch (PlasticoLoteException e) {
			log.info("Error al intentar guardar el objeto lote en la tabla de lotes o error en la generacion del .tst");
			popup.setPopup(Popup.ICONO_FALLA, "Se produjo un error cuando se intentaba procesar los embozos de las cuentas seleccionadas(2)");
			e.printStackTrace();
		} catch (Exception e) {
			log.info("Error al generar los archivos pdf's de acuses");
			popup.setPopup(Popup.ICONO_FALLA, "Se produjo un error cuando se intentaba procesar los embozos de las cuentas seleccionadas(3)");
			e.printStackTrace();
		}
		return null;
	}


	@Override
	public boolean validar() {
		return true;
	}


	public String seleccionarTodos() {
		for (WrapperCuenta w : listaCuentas) {
			w.setEmbozar(!limpiarSeleccion);
		}
		limpiarSeleccion = !limpiarSeleccion;
		return null;
	}


	public String irContinuar() {
		return inicializar();
	}


	/**
	 * @id: 4877 Method: irEmbozarLote Description:
	 * @return Navegacion a embozado del lote
	 */
	public String irEmbozarLote() {

		return ((EmbozadoLotesPlasticosBean) Session.getBean("EmbozadoLotesPlasticosBean")).inicializar();
	}

	// CLASE INTERNA//
	public class WrapperCuenta {
		private Long idCuenta;
		private PlasticoOperacion operacion;
		private String nombreTitular;
		private Set<PlasticoCliente> plasticosAsociados;
		private List<String> infoPlasticosAsociados;
		private boolean embozar;


		public WrapperCuenta(PlasticoCliente p) {
			super();
			this.idCuenta = p.esPlasticoTitular() ? p.getClienteTransaccion().getIdCliente() : p.getClienteTransaccion().getIdTitular();
			this.operacion = p.getOperacion();

			try {
				ClienteTransaccion titular = transaccionesService.getClienteTransaccionService().leerCliente(idCuenta);
				this.nombreTitular = titular.getIndividuo().getApellido().toUpperCase() + ", " + titular.getIndividuo().getNombres().toUpperCase();

			} catch (ClienteTransaccionException e) {
				log.info("ERROR, al intentar leer el cliente titular de algun plastico pendiente de  embozar");
				e.printStackTrace();
			}

			this.plasticosAsociados = new HashSet<PlasticoCliente>();
			this.infoPlasticosAsociados = new ArrayList<String>();
			agregarPlastico(p);
			this.embozar = false;
		}


		private void agregarPlastico(PlasticoCliente p) {
			this.plasticosAsociados.add(p);
			this.infoPlasticosAsociados.add(getInfoPlasticoCliente(p));
		}


		private boolean pertenece(PlasticoCliente p) {
			Long idTitular = p.esPlasticoTitular() ? p.getClienteTransaccion().getIdCliente() : p.getClienteTransaccion().getIdTitular();
			return (this.idCuenta.equals(idTitular)) && this.operacion.getIdPlasticoOperacion().equals(p.getOperacion().getIdPlasticoOperacion());

		}


		public Long getIdCuenta() {
			return idCuenta;
		}


		public void setIdCuenta(Long idCuenta) {
			this.idCuenta = idCuenta;
		}


		public String getNombreTitular() {
			return nombreTitular;
		}


		public void setNombreTitular(String nombreTitular) {
			this.nombreTitular = nombreTitular;
		}


		public Set<PlasticoCliente> getPlasticosAsociados() {
			return plasticosAsociados;
		}


		public void setPlasticosAsociados(Set<PlasticoCliente> plasticosAsociados) {
			this.plasticosAsociados = plasticosAsociados;
		}


		public List<String> getInfoPlasticosAsociados() {
			return infoPlasticosAsociados;
		}


		public void setInfoPlasticosAsociados(List<String> infoPlasticosAsociados) {
			this.infoPlasticosAsociados = infoPlasticosAsociados;
		}


		public boolean isEmbozar() {
			return embozar;
		}


		public void setEmbozar(boolean embozar) {
			this.embozar = embozar;
		}


		public PlasticoOperacion getOperacion() {
			return operacion;
		}


		public void setOperacion(PlasticoOperacion operacion) {
			this.operacion = operacion;
		}

	}// FIN CLASE INTERNA

}// FIN BEAN
