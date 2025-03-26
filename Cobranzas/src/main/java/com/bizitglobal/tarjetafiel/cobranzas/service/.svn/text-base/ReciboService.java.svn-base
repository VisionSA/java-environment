package com.bizitglobal.tarjetafiel.cobranzas.service;

import java.util.List;

import com.bizitglobal.tarjetafiel.cobranzas.exception.DataBaseException;
import com.bizitglobal.tarjetafiel.cobranzas.exception.ReciboException;
import com.bizitglobal.tarjetafiel.cobranzas.negocio.DTO.ReciboDTO;

public interface ReciboService {
	
	/**
	 * Obtiene un recibo de la base de datos
	 */
	public ReciboDTO getReciboByCodigo(Long codigo) throws DataBaseException, ReciboException;
	
	public List<ReciboDTO> getRangosReciboByIDCobrador(Long idCobrador) throws DataBaseException, ReciboException;
	
	public List<ReciboDTO> getRecibosByParam(ReciboDTO param) throws DataBaseException, ReciboException;
	
	public void anularReciboById(Long idRecibo) throws DataBaseException, ReciboException;
	
	public void insertarRangoRecibosByParam(ReciboDTO param) throws DataBaseException, ReciboException;
	
	
	
	
}
