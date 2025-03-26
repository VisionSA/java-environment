/**
 * @id 4655
 * Class RenovacionPlasticosBean.
 * User José Casalis
 * Bizit Global - Año 2011
 */
package com.bizitglobal.webapp.faces.beans.transacciones;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import net.sf.jasperreports.engine.JRException;

import org.apache.log4j.Logger;

import com.bizitglobal.tarjetafiel.commons.util.ContextoProperties;
import com.bizitglobal.tarjetafiel.commons.util.Fecha;
import com.bizitglobal.tarjetafiel.general.exception.ParametroSistemaException;
import com.bizitglobal.tarjetafiel.general.negocio.ParametroSistema;
import com.bizitglobal.tarjetafiel.general.negocio.ParametroSistemaDetalle;
import com.bizitglobal.tarjetafiel.transacciones.exception.PlasticoLoteException;
import com.bizitglobal.tarjetafiel.transacciones.negocio.EstadoLote;
import com.bizitglobal.tarjetafiel.transacciones.negocio.PlasticoCliente;
import com.bizitglobal.tarjetafiel.transacciones.negocio.PlasticoLote;
import com.bizitglobal.tarjetafiel.transacciones.negocio.PlasticoLugar;
import com.bizitglobal.tarjetafiel.transacciones.negocio.PlasticoOperacion;
import com.bizitglobal.webapp.faces.beans.BaseBean;
import com.bizitglobal.webapp.faces.beans.util.Popup;
import com.bizitglobal.webapp.faces.util.PDFUtil;
import com.bizitglobal.webapp.faces.util.Session;


/**
 * @id 4655
 * @author José Casalis. Bizit Global - Año 2011
 */
@SuppressWarnings({"rawtypes","unchecked"})
public class RenovacionPlasticosBean extends BaseBean {

	private static final Logger log = Logger.getLogger(RenovacionPlasticosBean.class);

	private List plasticosRenovar;

	private String RENOVACION_PLASTICOS_CLIENTE = "renovacionPlasticosCliente";

	private Long cantidadTitulares = 0L;
	private Long cantidadAdicionales = 0L;
	private Long cantidadTotal = 0L;
	private Long cantidadErroneos = 0L;

	private Long tamanoLote = 0L;
	private Long plasticosTotal = 0L;

	DateFormat df = new SimpleDateFormat("yyyyMMdd");
	String directorioPDF;

	ParametroSistema mesesInactividad;


	/**
	 * Constructor for class RenovacionPlasticosBean
	 */
	public RenovacionPlasticosBean() {
		super();
		borrar();
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bizitglobal.webapp.faces.beans.BaseBean#borrar()
	 */
	@Override
	public void borrar() {
		tituloCorto = "Renovacion de Plasticos";
		tituloLargo = "TARJETA FIEL";

	}


	public String cancelar() {
		// borrar();
		ejecutarJavaScript("window.close();");
		return RENOVACION_PLASTICOS_CLIENTE;
	}


	public String irAContinuar() {
		limpiarMensajeExito();
		return RENOVACION_PLASTICOS_CLIENTE;
	}


	public String irASalir() {
		return cancelar();
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bizitglobal.webapp.faces.beans.BaseBean#validar()
	 */
	@Override
	public boolean validar() {
		// TODO Auto-generated method stub
		return false;
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see com.bizitglobal.webapp.faces.beans.BaseBean#inicializar()
	 */
	@Override
	public String inicializar() {
		borrar();
		borrarBaseBean();
		try {
			mesesInactividad = generalService.getParametroSistemaService().leerParametroSistema(11L);
		} catch (ParametroSistemaException e) {
			log.error("Error al leer el parametro de sistema de meses de inactividad");
			error.agregar("Error al leer el parametro de sistema: MESES DE INACIVIDAD");
			return RENOVACION_PLASTICOS_CLIENTE;
		}
		try {
			directorioPDF = ContextoProperties.catalinaHome + ContextoProperties.getProperty("directorioArchivos")
					+ ContextoProperties.getProperty("directorioEmbozos") + "/" + df.format(new Date()) + "/pdf/";
		} catch (IOException e) {
			log.error("Error al leer parametros para generar el directorio del pdf");
			error.agregar("Error al leer parametros para generar el directorio del pdf");
			return RENOVACION_PLASTICOS_CLIENTE;
		}

		limpiarMensajeExito();
		return obtenerPlasticos();
	}


	/**
	 * @id: 4655 Method: limpiarMensajeExito Description:
	 */
	private void limpiarMensajeExito() {
		plasticosTotal = 0L;
		popup.borrar();
	}


	/**
	 * @id: Method: obtenerPlasticos Description:
	 * @return
	 */
	private String obtenerPlasticos() {
		/*
		 * Leer plasticos a vencer de cuentas titulares que estén abiertas, en estado normal o mora, fecha de vencimiento al mes siguiente del actual
		 * y no estén en estados de pendiente para embozar, embozados, correo o rechazados. La lista debe contener cuentas adicionales también
		 */
		cantidadAdicionales = 0L;
		cantidadTitulares = 0L;
		tamanoLote = 0L;
		cantidadErroneos = 0L;

		/*
		 * IMPORTANTE: es de suma importancia mantener el orden de la consulta según ADIC ASC, CLI.C_ADICIONAL ya que este orden se usa en el agregado
		 * de los plásticos a los archivos para embozado, y es necesario para mantenerlos agrupados por cuentas
		 */
		plasticosRenovar = transaccionesService.getPlasticoClienteService().obtenerPlasticosRenovacion(
				Long.parseLong(((ParametroSistemaDetalle) mesesInactividad.getDetallesParametrosSistema().toArray()[0]).getValor()));

		Iterator<PlasticoCliente> plasticoIt = plasticosRenovar.iterator();
		while (plasticoIt.hasNext())
		{
			PlasticoCliente plastico = plasticoIt.next();
			if (plastico.getClienteTransaccion().getIdTitular() == null)
			{
				cantidadTitulares++;
				if (!plastico.getClienteTransaccion().validarDomicilio()) {
					cantidadErroneos++;
				}
			}
			else
			{
				cantidadAdicionales++;
			}
			recalcularTotalPlasticos();
		}
		return RENOVACION_PLASTICOS_CLIENTE;
	}


	/**
	 * @id: Method: recalcularTotalPlasticos Description:
	 */
	private void recalcularTotalPlasticos() {
		cantidadTotal = cantidadTitulares + cantidadAdicionales;
	}


	public String renovarAction()
	{
		limpiarMensajeExito();
		/*
		 * Un lote que agrupa todos los plásticos generados, para darle tratamiento conjunto .Tendrá el mismo número que su último plástico. .La fecha
		 * de vigencia desde será el mes siguiente al actual. .La fecha de vigencia hasta será la vigencia desde mas la cantidad de meses
		 * configurados. .El pin se genera aleatoriamente y se verifica que no exista un plástico igual.
		 */
		if (plasticosRenovar.isEmpty())
		{
			error.agregar("La lista de plasticos a renovar esta vacia");
			log.error("La lista de plásticos a renovar está vacía");
			return RENOVACION_PLASTICOS_CLIENTE;
		}

		/* Controla el tamaño del lote seleccionado */
		if (tamanoLote == null || tamanoLote <= 0)
		{
			tamanoLote = new Long(plasticosRenovar.size());
			log.warn("El tamaño del lote debe ser superior a 0, se establecerá en " + plasticosRenovar.size());
		}

		/* Lee parámetro de sistema de meses de validez de plásticos. */
		ParametroSistema mesesValidez;
		Long cantidadLotes = 1L;
		PlasticoLote plasticoLote = null;
		PlasticoLugar lugarGeneracion = null;
		PlasticoLugar lugarPendienteConfirmar = null;
		EstadoLote estadoLoteGenerar = new EstadoLote();
		estadoLoteGenerar.setIdPlastLoteEstado(4L);
		EstadoLote estadoLotePendEmbozar = new EstadoLote();
		estadoLotePendEmbozar.setIdPlastLoteEstado(1L);

		try {
			mesesValidez = generalService.getParametroSistemaService().leerParametroSistema(ParametroSistema.MESES_VALIDEZ_PLASTICO);

			Integer cantidadMesesValidez = Integer.parseInt(((ParametroSistemaDetalle) mesesValidez
					.getParametroSistemaDetalle(ParametroSistemaDetalle.MESES_VALIDEZ_PLASTICO)).getValor());
			// La validez desde es desde el siguiente mes
			Date validezDesde = Fecha.addMeses(new Date(), 1);
			// Setea la fecha en el primer dái del mes para que tenga validez el plástico desde esa fecha
			Calendar fecha = Calendar.getInstance();
			fecha.setTime(validezDesde);
			fecha.set(Calendar.DAY_OF_MONTH, fecha.getMinimum(Calendar.DAY_OF_MONTH));
			validezDesde = fecha.getTime();
			// La validez hasta es desde la validez desde más la cantidad de meses seteada por parámetros
			Date validezHasta = Fecha.addMeses(validezDesde, cantidadMesesValidez);
			// Setea la fecha hasta como el último día de la fecha dada
			fecha.setTime(validezHasta);
			fecha.set(Calendar.DAY_OF_MONTH, fecha.getActualMaximum(Calendar.DAY_OF_MONTH));
			validezHasta = fecha.getTime();

			plasticoLote = new PlasticoLote();
			plasticoLote.setFechaGeneracion(new Date());
			plasticoLote.setVigenciaDesde(validezDesde);
			plasticoLote.setVigenciaHasta(validezHasta);
			plasticoLote.setIdEstadoLote(estadoLoteGenerar);
			plasticoLote.setTipoLote(PlasticoLote.AUTOMATICO);

			Iterator<PlasticoCliente> plasticoIt = plasticosRenovar.iterator();
			Long cantidadGenerados = 0L;
			PlasticoCliente plastico;

			lugarGeneracion = transaccionesService.getPlasticoLugarService().get(15L);
			lugarPendienteConfirmar = transaccionesService.getPlasticoLugarService().get(9L);
			PlasticoOperacion operacion_renovacion = transaccionesService.getPlasticoOperacionService().obtenerPlasticoOperacion(
					PlasticoCliente.OP_RENOVACION);
			Long ultimoTitularAgregado = 0L;

			PlasticoCliente plasticoARenovar = null;

			while (plasticoIt.hasNext())
			{
				plasticoARenovar = plasticoIt.next();

				// Si el lote ya supera el máximo ingresado, se crea un lote nuevo

				if (cantidadGenerados >= tamanoLote && !(plasticoARenovar.getClienteTransaccion().getAdicional() > 0))
				{
					// Graba el lote
					transaccionesService.getPlasticoLoteService().guardarPlasticoLote(plasticoLote);
					generarArchivosEmbozo(plasticoLote);
					// Actualiza los datos del lote y sus plásticos
					actualizaLoteActual(estadoLotePendEmbozar, plasticoLote, lugarPendienteConfirmar);

					plasticoLote = new PlasticoLote();
					plasticoLote.setFechaGeneracion(new Date());
					plasticoLote.setVigenciaDesde(validezDesde);
					plasticoLote.setVigenciaHasta(validezHasta);
					plasticoLote.setIdEstadoLote(estadoLoteGenerar);
					plasticoLote.setTipoLote(PlasticoLote.AUTOMATICO);

					cantidadGenerados = 0L;
					cantidadLotes++;
				}

				

					plastico = transaccionesService.getPlasticoClienteService().crearRenovado(plasticoARenovar, validezDesde, validezHasta,
							plasticoLote, lugarGeneracion, Session.getOperador(), operacion_renovacion);
					// Agrega el plástico al set de lote para luego tener la informacion para generar archivos
					plasticoLote.getPlasticos().add(plastico);
					ultimoTitularAgregado = plastico.getClienteTransaccion().getIdTitular() == null ? plastico.getClienteTransaccion().getIdCliente()
							: ultimoTitularAgregado;
					// Aumenta la cantidad de plasticos generados en el lote actual
					cantidadGenerados++;
					plasticosTotal++;
				
				plastico = null;
			}
			if (plasticoLote.getPlasticos() == null || plasticoLote.getPlasticos().isEmpty()) {
				error.agregar("No existen plasticos para agregar al lote");
				recalcularTotalPlasticos();
				return RENOVACION_PLASTICOS_CLIENTE;
			}
			// Graba el lote
			transaccionesService.getPlasticoLoteService().guardarPlasticoLote(plasticoLote);
			generarArchivosEmbozo(plasticoLote);
			// Actualiza los datos del lote y sus plásticos
			actualizaLoteActual(estadoLotePendEmbozar, plasticoLote, lugarPendienteConfirmar);
			plasticoLote = null;
			estadoLoteGenerar = null;
			estadoLotePendEmbozar = null;
		} catch (ParametroSistemaException e) {
			log.error("Error al leer el parametro de sistema de meses de validez de plastico");
			error.agregar("Error al leer el parametro de sistema: MESES DE VALIDEZ DE PLASTICOS");
			return RENOVACION_PLASTICOS_CLIENTE;
		} catch (PlasticoLoteException e) {
			// En caso de ser un error en la generacion del archivo corta el proceso, de lo contrario, solo lo informa
			if (PlasticoLoteException.ERR_GEN_TXT.equals(e.getType())) {
				log.error(e.getMessage());
				error.agregar(e.getMessage());
				return RENOVACION_PLASTICOS_CLIENTE;
			} else {
				error.agregar(e.getMessage());
			}
		} catch (IOException e) {
			log.error(e.getMessage());
			error.agregar("Error al intentar generar el pdf de lotes");
			return RENOVACION_PLASTICOS_CLIENTE;
		} catch (JRException e) {
			log.error(e.getMessage());
			error.agregar("Error al intentar generar el pdf de plasticos");
			return RENOVACION_PLASTICOS_CLIENTE;
		} catch (Exception e) {
			log.error(e.getMessage());
			error.agregar("Error al leer la informacion de los plasticos");
			return RENOVACION_PLASTICOS_CLIENTE;
		}
		popup.setNombreIcono(Popup.ICONO_OK);
		popup.setMensaje("Se renovaron " + plasticosTotal + " plasticos en " + cantidadLotes + " lotes");
		popup.setMostrar(true);
		this.plasticosRenovar = null;
		cantidadAdicionales = 0L;
		cantidadTitulares = 0L;
		return obtenerPlasticos();
	}


	/**
	 * @id: Method: actualizaLoteActual Description: Genera el archivo txt y pdf para el embozado del lote y actualiza la ruta de los archivos del
	 *      lote.
	 * @param estadoLotePendEmbozar
	 * @param plasticoLote
	 * @param lugarPendEmbozo
	 * @param operacion_renovacion
	 * @throws Exception
	 * @throws PlasticoLoteException
	 */
	private void actualizaLoteActual(
			EstadoLote estadoLotePendEmbozar, PlasticoLote plasticoLote,
			PlasticoLugar lugarPendEmbozo) throws Exception,
			PlasticoLoteException {

		// Actualiza el lote y plasticos en la base
		Iterator<PlasticoCliente> plasIt = plasticoLote.getPlasticos().iterator();
		while (plasIt.hasNext()) {
			PlasticoCliente plaAct = plasIt.next();
			plaAct.setPlasticoLugar(lugarPendEmbozo);
			transaccionesService.getPlasticoClienteService().guardarHistorialPlastico(plaAct, Session.getOperador());
		}
		plasticoLote.setIdEstadoLote(estadoLotePendEmbozar);
		transaccionesService.getPlasticoLoteService().modificarPlasticoLote(plasticoLote);
		plasIt = null;
		System.gc();
	}


	private void generarArchivosEmbozo(PlasticoLote lote) throws Exception {
		transaccionesService.getPlasticoLoteService().generarArchivoEmbozadoPorLote(lote, Session.getOperador());
		lote.setArchivoAcuses(PDFUtil.generarPDFAcusesPlasticos(lote.getPlasticos(), lote.getIdPlastLote()));
		// unirPDFAcuses(lote);
	}


	public List getPlasticosRenovar() {
		return plasticosRenovar;
	}


	public void setPlasticosRenovar(List plasticosRenovar) {
		this.plasticosRenovar = plasticosRenovar;
	}


	public Long getCantidadTitulares() {
		return cantidadTitulares;
	}


	public void setCantidadTitulares(Long cantidadTitulares) {
		this.cantidadTitulares = cantidadTitulares;
		if (cantidadTitulares != null && cantidadAdicionales != null)
		{
			recalcularTotalPlasticos();
		}
		else
		{
			cantidadTotal = 0L;
		}
	}


	public Long getCantidadAdicionales() {
		return cantidadAdicionales;
	}


	public void setCantidadAdicionales(Long cantidadAdicionales) {
		this.cantidadAdicionales = cantidadAdicionales;
		if (cantidadTitulares != null && cantidadAdicionales != null)
		{
			recalcularTotalPlasticos();
		}
		cantidadTotal = 0L;
	}


	public Long getCantidadTotal() {
		return cantidadTotal;
	}


	public void setCantidadTotal(Long cantidadTotal) {
		this.cantidadTotal = cantidadTotal;
	}


	public Long getTamanoLote() {
		return tamanoLote;
	}


	public void setTamanoLote(Long tamanoLote) {
		this.tamanoLote = tamanoLote;
	}


	public Long getPlasticosTotal() {
		return plasticosTotal;
	}


	public void setPlasticosTotal(Long plasticosTotal) {
		this.plasticosTotal = plasticosTotal;
	}


	public Long getCantidadErroneos() {
		return cantidadErroneos;
	}


	public void setCantidadErroneos(Long cantidadErroneos) {
		this.cantidadErroneos = cantidadErroneos;
	}
}
