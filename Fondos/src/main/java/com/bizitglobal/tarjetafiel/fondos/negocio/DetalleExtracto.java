package com.bizitglobal.tarjetafiel.fondos.negocio;

import java.util.Date;
import java.util.Iterator;
import java.util.List;


public class DetalleExtracto extends BaseConciliacion implements Comparable {
	private Long idDetalleExtracto;
	private String registro;
	private Character tipoRegistro;
	private Date fechaProceso;
	private String nroComprobante;
	private Double importe;
	private String nroCuentaCorto;
	private Character conciliado;
	private ExtractoBancario extractoBancario;
	private String concepto;
	private BancoPropio bancoPropio;
	private Date fechaMovimiento;
	private Date fechaValor;
	private Character signo;// "D": debito  "C":credito.
	private String codigoOperacion;
	private String descripcion;
	private String sucursalOrigen;
	private String codigoDepositante;
	private String nroCuenta;
	private String codigoOperacionBanco;
	
	
	private Long codigoBanco;
	private Long fechaMovimientoCadena;

//	C_ID_DETALLE_EXTRACTO	NUMBER(10,0)
//	C_REGISTRO	VARCHAR2(150 BYTE)
//	C_TIPO_REGISTRO	CHAR(1 BYTE)
//	C_FECHA_PROCESO	DATE
//	C_NRO_COMPROBANTE	CHAR(12 BYTE)
//	C_IMPORTE	NUMBER(12,2)
//	C_NRO_CTA_CORTO	CHAR(2 BYTE)
//	C_CONCILIADO	CHAR(1 BYTE)
//	C_ID_EXTRACTO_BANCARIO	NUMBER(10,0)
//	C_CONCEPTO	VARCHAR2(30 BYTE)
//	C_ID_BANCO_PROPIO	NUMBER(10,0)
//	C_FECHA_MOVIMIENTO	DATE
//	C_FECHA_VALOR	DATE
//	C_SIGNO	CHAR(1 BYTE)
//	C_CODIGO_OPERACION	CHAR(3 BYTE)
//	C_DESCRIPCION	VARCHAR2(28 BYTE)
//	C_SUCURSAL_ORIGEN	CHAR(5 BYTE)
//	C_CODIGO_DEPOSITANTE	CHAR(8 BYTE)
//	C_NRO_CUENTA	CHAR(17 BYTE)
//	C_COD_OPERACION_BANCO	CHAR(5 BYTE)
	
	public DetalleExtracto() {
	}

	public DetalleExtracto(Long id) {
		idDetalleExtracto = id;
	}

	public Long getIdDetalleExtracto() {
		return idDetalleExtracto;
	}

	public void setIdDetalleExtracto(Long idDetalleExtracto) {
		this.idDetalleExtracto = idDetalleExtracto;
	}

	public String getRegistro() {
		return registro;
	}

	public void setRegistro(String registro) {
		this.registro = registro;
	}

	public Character getTipoRegistro() {
		return tipoRegistro;
	}

	public void setTipoRegistro(Character tipoRegistro) {
		this.tipoRegistro = tipoRegistro;
	}

	public Date getFechaProceso() {
		return fechaProceso;
	}

	public void setFechaProceso(Date fechaProceso) {
		this.fechaProceso = fechaProceso;
	}

	public String getNroComprobante() {
		return nroComprobante;
	}

	public void setNroComprobante(String nroComprobante) {
		this.nroComprobante = nroComprobante;
	}

	public Character getConciliado() {
		return conciliado;
	}

	public void setConciliado(Character conciliado) {
		this.conciliado = conciliado;
	}

	public ExtractoBancario getExtractoBancario() {
		return extractoBancario;
	}

	public void setExtractoBancario(ExtractoBancario extractoBancario) {
		this.extractoBancario = extractoBancario;
	}

	
	public Double getImporte() {
		return importe;
	}

	public void setImporte(Double importe) {
		this.importe = importe;
	}

	public String getNroCuentaCorto() {
		return nroCuentaCorto;
	}

	public void setNroCuentaCorto(String nroCuentaCorto) {
		this.nroCuentaCorto = nroCuentaCorto;
	}

	public String getConcepto() {
		return concepto;
	}

	public void setConcepto(String concepto) {
		this.concepto = concepto;
	}

	public BancoPropio getBancoPropio() {
		return bancoPropio;
	}

	public void setBancoPropio(BancoPropio bancoPropio) {
		this.bancoPropio = bancoPropio;
	}

	public Date getFechaMovimiento() {
		return fechaMovimiento;
	}

	public void setFechaMovimiento(Date fechaMovimiento) {
		this.fechaMovimiento = fechaMovimiento;
	}

	public Date getFechaValor() {
		return fechaValor;
	}

	public void setFechaValor(Date fechaValor) {
		this.fechaValor = fechaValor;
	}

	public Character getSigno() {
		return signo;
	}

	public void setSigno(Character signo) {
		this.signo = signo;
	}

	public String getCodigoOperacion() {
		return codigoOperacion;
	}

	public void setCodigoOperacion(String codigoOperacion) {
		this.codigoOperacion = codigoOperacion;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getSucursalOrigen() {
		return sucursalOrigen;
	}

	public void setSucursalOrigen(String sucursalOrigen) {
		this.sucursalOrigen = sucursalOrigen;
	}

	public String getCodigoDepositante() {
		return codigoDepositante;
	}

	public void setCodigoDepositante(String codigoDepositante) {
		this.codigoDepositante = codigoDepositante;
	}

	public String getNroCuenta() {
		return nroCuenta;
	}

	public void setNroCuenta(String nroCuenta) {
		this.nroCuenta = nroCuenta;
	}

	public String getCodigoOperacionBanco() {
		return codigoOperacionBanco;
	}

	public void setCodigoOperacionBanco(String codigoOperacionBanco) {
		this.codigoOperacionBanco = codigoOperacionBanco;
	}

	
	public Long getCodigoBanco() {
		return codigoBanco;
	}

	public void setCodigoBanco(Long codigoBanco) {
		this.codigoBanco = codigoBanco;
	}
	
	

	public Long getFechaMovimientoCadena() {
		return fechaMovimientoCadena;
	}

	public void setFechaMovimientoCadena(Long fechaMovimientoCadena) {
		this.fechaMovimientoCadena = fechaMovimientoCadena;
	}

	@Override
	public int compareTo(Object o) {
		DetalleExtracto detalle = (DetalleExtracto)o;
		Long result = this.getFechaMovimientoCadena() - detalle.getFechaMovimientoCadena(); 
		return result.intValue()== 0L ? -1 : result.intValue();
	}
	
	public boolean equals(Object obj) {
		boolean result = false;
		if(obj instanceof DetalleExtracto) {
			DetalleExtracto aux = (DetalleExtracto)obj;
			if(aux.getIdDetalleExtracto().equals(idDetalleExtracto)) {
				result = true;
			}
		}
		//Comparacion para filtrar solo los detalleExtracto que tiene un codigoOperacion conocido.(en t_vis_fon_tipo_mov_extracto)
		if(obj instanceof ExtractoBancarioTipoMovimiento) {
			ExtractoBancarioTipoMovimiento aux = (ExtractoBancarioTipoMovimiento)obj;
/*@I3918*/			if(aux.getCodigoOperacion().trim().equals(this.codigoOperacion.trim())) {
				/*Para Poder conciliar automáticamente las acreditaciones anteriores a feb 2011*/			
/*@F3918*/			//if(aux.getDescripcion().trim().equals(this.descripcion.trim())) {
				result = true;
			}
		}
		//Comparacion para la conciliacion automatica del detalleExtracto.
		if(obj instanceof ChequeHistorial) {
			ChequeHistorial aux = (ChequeHistorial)obj;
			//Comparar por: Cheque:c_numero = DetalleExtracto:nroComprobante  
			Long numeroCheque = new Long(aux.getCheque().getNumero().trim());
			Long numeroComprobante = new Long(this.nroComprobante.trim());
/*@I3918*/			Double importeCheque = new Double(aux.getCheque().getImporte());
			Integer histSigno = aux.getAsientoItem().getSigno();
			if(numeroCheque.equals(numeroComprobante) && importe.equals(importeCheque)
					&& histSigno.equals(getIntSigno())) {
/*@F3918*/				result = true;
			}
		}
		
		
		return result;
	}
	
	/**
	 * @id 3918
	 */
	private Integer getIntSigno()
	{
		return this.signo.equals('D') ?  -1 : 1;
	}
	
	/**
	 * Busca en un listado de ChuqueHistorial un detalleExtracto pasado como parametro.
	 * Usa el metodo equals() de DetalleExtracto para buscar.
	 * @param listChequeHistorial: Listado ChequeHistorial
	 * @param detalle: Un DetalleExtracto.
	 * @return Devuelve el DetalleExtracto encontrado en el listado o null.
	 */
	
	public static ChequeHistorial buscarDetalleExtracto(List listChequeHistorial, DetalleExtracto detalle)
	{
		
		Iterator iter = listChequeHistorial.iterator();
		while(iter.hasNext())
		{
			ChequeHistorial chequeHistorial = (ChequeHistorial)iter.next();
			if(detalle.equals(chequeHistorial)) {
				return chequeHistorial;
			}
		}
		
		return null;
	}
//	
//	public String toString() {
//		return "Tipo: "+tipoRegistro+"|Numero:"+numero+"|Beneficiario:"+beneficiario;
//	}

	@Override
	public String getDescripcionGen() {
		return descripcion;
	}

	@Override
	public int getDebCred() {
		if (signo.equals('D')) 
			return -1;
		else 
			return 1;
	}

	@Override
	public Date getFechaGeneral() {
		return fechaMovimiento;
	}
}

