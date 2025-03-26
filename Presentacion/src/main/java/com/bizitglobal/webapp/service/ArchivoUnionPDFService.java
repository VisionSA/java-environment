package com.bizitglobal.webapp.service;

import com.bizitglobal.tarjetafiel.transacciones.negocio.DisponibleCliente;
import com.bizitglobal.tarjetafiel.transacciones.negocio.UltimoResumenCuenta;
import com.bizitglobal.tarjetafiel.transacciones.negocio.DetalleResumen;
import com.bizitglobal.tarjetafiel.transacciones.negocio.MovimientosPeriodoActual;
import com.bizitglobal.tarjetafiel.transacciones.negocio.ClienteApp;
import com.bizitglobal.tarjetafiel.transacciones.negocio.Promocion;
import com.bizitglobal.tarjetafiel.transacciones.negocio.ResumenPDFImage;
import com.bizitglobal.tarjetafiel.transacciones.negocio.Notificacion;

import java.util.List;


public interface ArchivoUnionPDFService {

	public byte[] getArchivoUnionPDF(long idLiqCliente, long idCliente)
			throws Exception;
	public byte[] getPDF(long idLiqCliente)
			throws Exception;
	public byte[] getArchivoEncryptedUnionPDF(String encryptedParameters)
			throws Exception;
	public Boolean registrarApp(String nomdoc, String nrotarjeta, String idregistracion, String nombreapellido, String mail, String resumenmail)
			throws Exception;
	public Boolean buscarRegistracionApp(String idregistracion) throws Exception;
	public DisponibleCliente buscarDiponibleApp(String idregistro) throws Exception;
	public UltimoResumenCuenta buscarUltimoResumenCuenta(String idregistro) throws Exception;
	public List<DetalleResumen> buscarDetalleResumenCuenta(String idregistro, String fecha) throws Exception;
	public List<MovimientosPeriodoActual> movimientosPeriodoActual(String idregistro, String fecha) throws Exception;
	public String bloqueoPastico(String idregistro, String nrodoc) throws Exception;
	public ClienteApp buscarClienteRegApp(String idregistro) throws Exception;
	public Boolean bajaRegistracionApp(String idregistracion) throws Exception;
	public Boolean inserirPromocion(Promocion promocion) throws Exception;
	public Promocion buscarPromocionesApp() throws Exception;
	public List<ResumenPDFImage> getResumenPDFImage(long idLiqCliente, long idCliente, String idregistro)
			throws Exception;
	public Boolean appResumenMail(String idregistro, String mail);
	public String buscarNotificacionApp(String registo, int tipoNotificacion) throws Exception;
	public List<Notificacion> buscarNotificacionesCliente(Long idCliente) throws Exception;
	public String buscarRecomiendaApp() throws Exception;
	public Boolean activarPlastico(String codCuenta, String codLote, String codOperacion, String documento, String apellido, String nombre);
	public Boolean activarPlasticoNuevo(String codCuenta, String codLote, String codOperacion, String documento, String apellido,
			String nombre, Long operador);
	public Boolean activarPlasticoFechaCarga(String codCuenta, String codLote, String codOperacion, String documento, String apellido,
			String nombre, Long operador, String parentesco, String fechaEntrega);
	public Boolean activarPlasticoTramite(String codCuenta, String codLote, String codOperacion, String documento, String apellido,
			String nombre, Long operador, String parentesco, String fechaEntrega,
			String lineaCredito, String observacion, String observacionTexto);
	
}
