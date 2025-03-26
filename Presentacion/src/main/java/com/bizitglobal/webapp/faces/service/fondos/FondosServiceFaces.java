package com.bizitglobal.webapp.faces.service.fondos;

import org.apache.log4j.Logger;

import com.bizitglobal.tarjetafiel.fondos.service.AcreditacionFondoDetalleService;
import com.bizitglobal.tarjetafiel.fondos.service.AcreditacionFondoService;
import com.bizitglobal.tarjetafiel.fondos.service.AsientoFondosService;
import com.bizitglobal.tarjetafiel.fondos.service.BancoPropioService;
import com.bizitglobal.tarjetafiel.fondos.service.CajaAperturaService;
import com.bizitglobal.tarjetafiel.fondos.service.CajaMPService;
import com.bizitglobal.tarjetafiel.fondos.service.CajaService;
import com.bizitglobal.tarjetafiel.fondos.service.ChequeEstadoService;
import com.bizitglobal.tarjetafiel.fondos.service.ChequeHistorialService;
import com.bizitglobal.tarjetafiel.fondos.service.ChequeService;
import com.bizitglobal.tarjetafiel.fondos.service.ChequeraService;
import com.bizitglobal.tarjetafiel.fondos.service.LibroMayorFondosService;
import com.bizitglobal.tarjetafiel.fondos.service.LoteInterbankService;
import com.bizitglobal.tarjetafiel.fondos.service.MovimientoMPService;
import com.bizitglobal.tarjetafiel.fondos.service.MovimientoService;
import com.bizitglobal.webapp.faces.service.BaseService;


public class FondosServiceFaces extends BaseService {
	private static final Logger log = Logger.getLogger(FondosServiceFaces.class);
	private static final String ASIENTO_FONDOS_SERVICE_NAME = "asientoFondosService";
	private static final String CAJA_SERVICE_NAME = "cajaService";
	private static final String BANCO_PROPIO_SERVICE_NAME = "bancoPropioService";
	private static final String MOVIMIENTO_SERVICE_NAME = "movimientoService";
	private static final String CHEQUERA_SERVICE_NAME = "chequeraService";
	private static final String CAJA_APERTURA_SERVICE_NAME = "cajaAperturaService";
	private static final String CHEQUE_SERVICE_NAME = "chequeService";
	private static final String CHEQUE_ESTADO_NAME = "chequeEstadoService";
	private static final String CHEQUE_HISTORIAL_NAME = "chequeHistorialService";
	private static final String MOVIMIENTO_MP_SERVICE_NAME = "movimientoMPService";
	private static final String CAJA_MP_SERVICE_NAME = "cajaMPService";
	private static final String LOTE_INTERBANK_SERVICE_NAME = "loteInterbankService";
	private static final String LIBRO_MAYOR_FONDOS_SERVICE_NAME = "libroMayorFondosService";
	private static final String ACREDITACIONES_FONDOS_SERVICE_NAME = "acreditacionFondoService";
	private static final String ACREDITACIONES_FONDOS_DETALLE_SERVICE_NAME = "acreditacionFondoDetalleService";

	private AsientoFondosService asientoFondosService;
	private CajaService cajaService;
	private BancoPropioService bancoPropioService;
	private MovimientoService movimientoService;
	private ChequeraService chequeraService;
	private CajaAperturaService cajaAperturaService;
	private ChequeService chequeService;
	private ChequeEstadoService chequeEstadoService;
	private ChequeHistorialService chequeHistorialService;
	private MovimientoMPService movimientoMPService;
	private CajaMPService cajaMPService;
	private LoteInterbankService loteInterbankService;
	private LibroMayorFondosService libroMayorFondosService;
	private AcreditacionFondoService acreditacionFondoService;
	private AcreditacionFondoDetalleService acreditacionFondoDetalleService;


	public FondosServiceFaces() {
		super();
		log.info("Construyendo el service de Fondos!!!");
		this.asientoFondosService = (AsientoFondosService) this.lookupService(ASIENTO_FONDOS_SERVICE_NAME);
		this.cajaService = (CajaService) this.lookupService(CAJA_SERVICE_NAME);
		this.bancoPropioService = (BancoPropioService) this.lookupService(BANCO_PROPIO_SERVICE_NAME);
		this.movimientoService = (MovimientoService) this.lookupService(MOVIMIENTO_SERVICE_NAME);
		this.chequeraService = (ChequeraService) this.lookupService(CHEQUERA_SERVICE_NAME);
		this.cajaAperturaService = (CajaAperturaService) this.lookupService(CAJA_APERTURA_SERVICE_NAME);
		this.chequeService = (ChequeService) this.lookupService(CHEQUE_SERVICE_NAME);
		this.chequeEstadoService = (ChequeEstadoService) this.lookupService(CHEQUE_ESTADO_NAME);
		this.chequeHistorialService = (ChequeHistorialService) this.lookupService(CHEQUE_HISTORIAL_NAME);
		this.movimientoMPService = (MovimientoMPService) this.lookupService(MOVIMIENTO_MP_SERVICE_NAME);
		this.cajaMPService = (CajaMPService) this.lookupService(CAJA_MP_SERVICE_NAME);
		this.loteInterbankService = (LoteInterbankService) this.lookupService(LOTE_INTERBANK_SERVICE_NAME);
		this.libroMayorFondosService = (LibroMayorFondosService) this.lookupService(LIBRO_MAYOR_FONDOS_SERVICE_NAME);
		this.acreditacionFondoService = (AcreditacionFondoService) this.lookupService(ACREDITACIONES_FONDOS_SERVICE_NAME);
		this.acreditacionFondoDetalleService = (AcreditacionFondoDetalleService) this.lookupService(ACREDITACIONES_FONDOS_DETALLE_SERVICE_NAME);
	}


	public AcreditacionFondoDetalleService getAcreditacionFondoDetalleService() {
		return acreditacionFondoDetalleService;
	}


	public AcreditacionFondoService getAcreditacionFondoService() {
		return acreditacionFondoService;
	}


	public AsientoFondosService getAsientoFondosService() {
		return asientoFondosService;
	}


	public CajaService getCajaService() {
		return cajaService;
	}


	public BancoPropioService getBancoPropioService() {
		return bancoPropioService;
	}


	public ChequeraService getChequeraService() {
		return chequeraService;
	}


	public MovimientoService getMovimientoService() {
		return movimientoService;
	}


	public CajaAperturaService getCajaAperturaService() {
		return cajaAperturaService;
	}


	public ChequeService getChequeService() {
		return chequeService;
	}


	public ChequeEstadoService getChequeEstadoService() {
		return chequeEstadoService;
	}


	public ChequeHistorialService getChequeHistorialService() {
		return chequeHistorialService;
	}


	public MovimientoMPService getMovimientoMPService() {
		return movimientoMPService;
	}


	public CajaMPService getCajaMPService() {
		return cajaMPService;
	}


	public LoteInterbankService getLoteInterbankService() {
		return loteInterbankService;
	}


	public LibroMayorFondosService getLibroMayorFondosService() {
		return libroMayorFondosService;
	}


	public void setLibroMayorFondosService(
			LibroMayorFondosService libroMayorFondosService) {
		this.libroMayorFondosService = libroMayorFondosService;
	}

}
