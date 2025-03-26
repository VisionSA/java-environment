package com.bizitglobal.webapp.service.impl;

import com.bizitglobal.tarjetafiel.transacciones.service.ArchivoCuponesService;
import com.bizitglobal.webapp.service.ArchivoLiqComercioService;


public class ArchivoLiqComercioServiceImpl implements ArchivoLiqComercioService {

	ArchivoCuponesService archivoCuponesService;


	public ArchivoCuponesService getArchivoCuponesService() {
		return archivoCuponesService;
	}


	public void setArchivoCuponesService(ArchivoCuponesService archivoCuponesService) {
		this.archivoCuponesService = archivoCuponesService;
	}


	@Override
	public byte[] getArchivoLiqComercio(Long idLiqComercio) throws Exception {
		return archivoCuponesService.generarArchivo(idLiqComercio).getBytes();
	}

}
