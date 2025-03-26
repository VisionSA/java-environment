package com.bizitglobal.tarjetafiel.fondos.negocio;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import com.bizitglobal.tarjetafiel.commons.interfaces.Negocio;
import com.bizitglobal.tarjetafiel.general.negocio.Banco;
import com.bizitglobal.tarjetafiel.operador.negocio.Operador;

public class ExtractoBancario  implements Negocio {
	private Long idExtractoBancario;
	private String urlArchivo;
	private String nroCliente;
	private Date fechaProceso;
	private String nombreArchivo;
	private Integer cantRegistroHF;
	private Integer cantRegistroMF;
	private Banco banco;
	private String registro;
	private String registroF;
	
	private Set detallesExtracto;
	private Character conciliado;
	private Double importeTotal;
	private Operador operador;
	private Long idBanco;
	private String fechaProcesoCadena;
	private SortedSet detallesExtractoOrdenado;
	private Date timeStamp;
	
//	C_ID_EXTRACTO_BANCARIO	NUMBER(10,0)
//	C_URL_ARCHIVO	VARCHAR2(500 BYTE)
//	C_NRO_CLIENTE	CHAR(7 BYTE)
//	C_FECHA_PROCESO	DATE
//	C_NOMBRE_ARCHIVO	CHAR(6 BYTE)
//	C_CANT_REG_H_F	CHAR(6 BYTE)
//	C_CANT_REG_M_F	CHAR(6 BYTE)
//	C_ID_BANCO	NUMBER(10,0)
//	C_REGISTRO	VARCHAR2(80 BYTE)
//	C_REGISTRO_F	VARCHAR2(80 BYTE)        
	
	public ExtractoBancario() {
	}

	public ExtractoBancario(Long id) {
		idExtractoBancario = id;
	}

	public Long getId() {
		return idExtractoBancario;
	}

	public String getLabel() {
		return nombreArchivo = " - " + fechaProceso.toString();
	}
	
	public Long getIdExtractoBancario() {
		return idExtractoBancario;
	}

	public void setIdExtractoBancario(Long idExtractoBancario) {
		this.idExtractoBancario = idExtractoBancario;
	}

	public String getUrlArchivo() {
		return urlArchivo;
	}

	public void setUrlArchivo(String urlArchivo) {
		this.urlArchivo = urlArchivo;
	}

	public String getNroCliente() {
		return nroCliente;
	}

	public void setNroCliente(String nroCliente) {
		this.nroCliente = nroCliente;
	}

	public Date getFechaProceso() {
		return fechaProceso;
	}

	public void setFechaProceso(Date fechaProceso) {
		this.fechaProceso = fechaProceso;
	}

	public String getNombreArchivo() {
		return nombreArchivo;
	}

	public void setNombreArchivo(String nombreArchivo) {
		this.nombreArchivo = nombreArchivo;
	}

	public Integer getCantRegistroHF() {
		return cantRegistroHF;
	}

	public void setCantRegistroHF(Integer cantRegistroHF) {
		this.cantRegistroHF = cantRegistroHF;
	}

	public Integer getCantRegistroMF() {
		return cantRegistroMF;
	}

	public void setCantRegistroMF(Integer cantRegistroMF) {
		this.cantRegistroMF = cantRegistroMF;
	}

	public Banco getBanco() {
		return banco;
	}

	public void setBanco(Banco banco) {
		this.banco = banco;
	}

	public String getRegistro() {
		return registro;
	}

	public void setRegistro(String registro) {
		this.registro = registro;
	}

	public String getRegistroF() {
		return registroF;
	}

	public void setRegistroF(String registroF) {
		this.registroF = registroF;
	}

	public Set getDetallesExtracto() {
		this.detallesExtracto = this.detallesExtracto==null ? new HashSet() : this.detallesExtracto; 
		return this.detallesExtracto;
	}

	public void setDetallesExtracto(Set detallesExtracto) {
		this.detallesExtracto = detallesExtracto;
	}

	public Character getConciliado() {
		return conciliado;
	}

	public void setConciliado(Character conciliado) {
		this.conciliado = conciliado;
	}

	
	public Double getImporteTotal() {
		return importeTotal;
	}

	public void setImporteTotal(Double importeTotal) {
		this.importeTotal = importeTotal;
	}
	
	public Operador getOperador() {
		return operador;
	}

	public void setOperador(Operador operador) {
		this.operador = operador;
	}

	public Long getIdBanco() {
		return idBanco;
	}

	public void setIdBanco(Long idBanco) {
		this.idBanco = idBanco;
	}

	
	public String getFechaProcesoCadena() {
		return fechaProcesoCadena;
	}

	public void setFechaProcesoCadena(String fechaProcesoCadena) {
		this.fechaProcesoCadena = fechaProcesoCadena;
	}

	
	public SortedSet getDetallesExtractoOrdenado() {
		this.detallesExtractoOrdenado = this.detallesExtractoOrdenado==null ? new TreeSet() : this.detallesExtractoOrdenado;
		return detallesExtractoOrdenado;
	}

	public void setDetallesExtractoOrdenado(SortedSet detallesExtractoOrdenado) {
		this.detallesExtractoOrdenado = detallesExtractoOrdenado;
	}

	public Date getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(Date timeStamp) {
		this.timeStamp = timeStamp;
	}

	public boolean equals(Object obj) {
		boolean result = false;
		if(obj instanceof ExtractoBancario) {
			ExtractoBancario aux = (ExtractoBancario)obj;
			if(aux.getId().equals(idExtractoBancario)) {
				result = true;
			}
		}
		return result;
	}
	
//	public String toString() {
//		return "Tipo: "+tipo+"|Numero:"+numero+"|Beneficiario:"+beneficiario;
//	}

}

