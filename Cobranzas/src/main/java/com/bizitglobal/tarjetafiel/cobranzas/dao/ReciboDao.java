package com.bizitglobal.tarjetafiel.cobranzas.dao;

import java.util.List;

import com.bizitglobal.tarjetafiel.cobranzas.negocio.DTO.ReciboDTO;

public interface ReciboDao {
	
	public ReciboDTO getReciboByCodigo(Long codigo);
	
	public List<ReciboDTO> getRangosReciboByIDCobrador(Long idCobrador);
	
	public List<ReciboDTO> getRecibosByIDCobrador(Long idCobrador);
	
	public List<ReciboDTO> getRecibosByParam(ReciboDTO param);

	public void anularReciboById(Long idRecibo);

	public void insertarRangoRecibosByParam(ReciboDTO param);

}
