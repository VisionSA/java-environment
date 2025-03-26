package com.bizitglobal.tarjetafiel.fondos.negocio;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.bizitglobal.tarjetafiel.commons.exception.NroBancarioNoValidoException;
import com.bizitglobal.tarjetafiel.commons.interfaces.Negocio;
import com.bizitglobal.tarjetafiel.commons.util.Convertidores;
import com.bizitglobal.tarjetafiel.commons.util.NroBancarioValido;
import com.bizitglobal.tarjetafiel.general.negocio.Banco;
import com.bizitglobal.tarjetafiel.general.negocio.TipoCuentaBanco;

public class Cheque  implements Negocio{

	private Long idCheque;
	private Character tipo;
	private String sucursalBanco;
	private String cuenta;
	private String numero = "";
	private String beneficiario = "";
	private Date fechaEmision;
	private Date fechaPago;
	private Character esCruzado = ' ';
	private Character noOrden = ' ';
	private Double importe;
	private String codRed;
	private Banco banco;
	private Character conciliado = 'N';
	private BancoPropio bancoPropio;
	private Character procesado = 'N';
	private String cbu;
	private TipoCuentaBanco tipoCuentaBanco;
	private Set historial = new HashSet();
	private Boolean imprimir= new Boolean(false);
	/**
	 * Cheque estado es para usar en flex no esta mapeada con hibernate
	 * y no existe el campo en  la tabla 
	 */
	private ChequeEstado chequeEstado;
	private String codigoPostal;
	
	private Short DV1;
	private Short DV2;
	private Short DV3;
	
	public final static Character EFECTIVO		= 'E';
	public final static Character PROPIO		= 'P';
	public final static Character TERCERO		= 'T';
	public final static Character ACREDITACION	= 'A';
	

//	T_VIS_FON_CHEQUES
//	C_ID_CHEQUE                    NOT NULL NUMBER(10)                                                                                                                                                                                    
//	C_TIPO                         NOT NULL CHAR(1)                                                                                                                                                                                       
//	C_SUCURSAL_BANCO                        NUMBER(10)                                                                                                                                                                                    
//	C_NUMERO                       NOT NULL VARCHAR2(20)                                                                                                                                                                                  
//	C_BENEFICIARIO                 NOT NULL NVARCHAR2(50)                                                                                                                                                                                 
//	C_FECHA_EMISION                NOT NULL DATE                                                                                                                                                                                          
//	C_FECHA_PAGO                   NOT NULL DATE                                                                                                                                                                                          
//	C_ESCRUZADO                    NOT NULL CHAR(1)                                                                                                                                                                                       
//	C_IMPORTE                      NOT NULL NUMBER(10,2)                                                                                                                                                                                  
//	C_COD_RED                               CHAR(5)                                                                                                                                                                                       
//	C_ID_BANCO                     NOT NULL NUMBER(10)                                                                                                                                                                                    
//	C_CONCILIADO                            CHAR(1)                                                                                                                                                                                       
//	C_ID_BANCO_PROPIO                       NUMBER(10)
//	C_PROCESADO                            CHAR(1)     
	
	public Cheque() {
	}

	public Cheque(Long id) {
		idCheque = id;
	}
	
	

	public Cheque(String beneficiario, ChequeEstado chequeEstado,
			String cuenta, Character esCruzado, Date fechaEmision,
			Date fechaPago, Long idCheque, Double importe, Character noOrden,
			String numero, String sucursalBanco, Character tipo) {
		super();
		this.beneficiario = beneficiario;
		this.chequeEstado = chequeEstado;
		this.cuenta = cuenta;
		this.esCruzado = esCruzado;
		this.fechaEmision = fechaEmision;
		this.fechaPago = fechaPago;
		this.idCheque = idCheque;
		this.importe = importe;
		this.noOrden = noOrden;
		this.numero = numero;
		this.sucursalBanco = sucursalBanco;
		this.tipo = tipo;
	}

	public Long getId() {
		return idCheque;
	}

	public String getLabel() {
		return beneficiario;
	}
	
	public Long getIdCheque() {
		return idCheque;
	}

	public void setIdCheque(Long idCheque) {
		this.idCheque = idCheque;
	}

	public Character getTipo() {
		return tipo;
	}

	public String getTipoFormat() {
		switch (tipo) {
		case 'A':
			return "Acreditación";
		case 'P':
			return "Cheque Propio";
		case 'T':
			return "Cheque Tercero";
		case 'D':
			return "Debito";
		default:
			return "Indefinido";
		}
	}
	public Boolean getImprimir(){
		return this.imprimir;
	}
	
	public void setImprimir(Boolean b){
		this.imprimir=b;
	}
	
	public void setTipo(Character tipo) {
		this.tipo = tipo;
	}

	public String getSucursalBanco() {
		return sucursalBanco;
	}

	public void setSucursalBanco(String sucursalBanco) {
		this.sucursalBanco = sucursalBanco;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		try {
			if (numero != null) {
				this.numero = Convertidores.completarIzquierda(numero,'0',8);
			}else {
				this.numero = ""; 
			}
		} catch (Exception e) {
			this.numero = numero;
			e.printStackTrace();
		}
	}

	public String getBeneficiario() {
		return beneficiario;
	}

	public void setBeneficiario(String beneficiario) {
		this.beneficiario = beneficiario;
	}

	public String getFechaEmisionFormat() {
		Format dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		return dateFormat.format(fechaEmision);
	}
	
	public Date getFechaEmision() {
		return fechaEmision;
	}

	public void setFechaEmision(Date fechaEmision) {
		this.fechaEmision = fechaEmision;
	}

	public String getFechaPagoFormat() {
		Format dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		return dateFormat.format(fechaPago);
	}
	
	public Date getFechaPago() {
		return fechaPago;
	}

	public void setFechaPago(Date fechaPago) {
		this.fechaPago = fechaPago;
	}

	public Character getEsCruzado() {
		return esCruzado;
	}

	public void setEsCruzado(Character esCruzado) {
		this.esCruzado = esCruzado;
	}

	public Double getImporte() {
		return importe;
	}

	public void setImporte(Double importe) {
		this.importe = importe;
	}

	public Banco getBanco() {
		return banco;
	}

	public void setBanco(Banco banco) {
		this.banco = banco;
	}

	public BancoPropio getBancoPropio() {
		return bancoPropio;
	}

	public void setBancoPropio(BancoPropio bancoPropio) {
		this.bancoPropio = bancoPropio;
	}

	public String getCodRed() {
		return codRed;
	}

	public void setCodRed(String codRed) {
		this.codRed = codRed;
	}

	public Character getConciliado() {
		return conciliado;
	}

	public void setConciliado(Character conciliado) {
		this.conciliado = conciliado;
	}

	public Set getHistorial() {
		return historial;
	}

	public void setHistorial(Set historial) {
		this.historial = historial;
	}

	public Character getProcesado() {
		return procesado;
	}

	public void setProcesado(Character procesado) {
		this.procesado = procesado;
	}

	public String getCodigoPostal() {
		return codigoPostal;
	}

	public void setCodigoPostal(String codigoPostal) {
		this.codigoPostal = codigoPostal;
	}
	
	public Character getNoOrden() {
		return noOrden;
	}

	public void setNoOrden(Character noOrden) {
		this.noOrden = noOrden;
	}

	public Short getDV1() {
		return DV1;
	}

	public void setDV1(Short dv1) {
		DV1 = dv1;
	}

	public Short getDV2() {
		return DV2;
	}

	public void setDV2(Short dv2) {
		DV2 = dv2;
	}

	public Short getDV3() {
		return DV3;
	}

	public void setDV3(Short dv3) {
		DV3 = dv3;
	}

	public boolean getCruzado(){
		return (esCruzado.equals('S'));
	}
	
	public void setCruzado(boolean cruzado){
		if (cruzado) 
			esCruzado = new Character('S');
		else
			esCruzado = new Character('N');
	}
	
	public boolean getNoALaOrden(){
		return (noOrden.equals('N'));
	}
	
	public void setNoALaOrden(boolean noALaOrden){
		if (noALaOrden) 
			noOrden = new Character('N');
		else
			noOrden= new Character('S');
	}
	
	public boolean equals(Object obj) {
		boolean result = false;
		if(obj instanceof Cheque) {
			Cheque aux = (Cheque)obj;
			if(aux.getId().equals(idCheque)) {
				result = true;
			}
		}
		return result;
	}
	
	public String toString() {
		return "Tipo: "+tipo+"|Numero:"+numero+"|Beneficiario:"+beneficiario;
	}
	
	public boolean validarBanco() throws NroBancarioNoValidoException{
		new NroBancarioValido(banco.getCodigo().toString() + sucursalBanco.toString() 
				+ codigoPostal.toString() + DV1.toString(), "Banco");
		return true;
	}
	
	public boolean validarNumero() throws NroBancarioNoValidoException{
		new NroBancarioValido(numero + DV2.toString(), "Cheque");
		return true;
	}
	
	public boolean validarCuenta() throws NroBancarioNoValidoException{
		new NroBancarioValido( getCuenta() + DV3.toString(), "Cuenta");
		return true;
	}
	
	public boolean validar() throws NroBancarioNoValidoException{
		try {
			validarBanco();
			validarNumero();
			validarCuenta();
		} catch (NullPointerException e) {
			throw new NroBancarioNoValidoException("Error al validar. Datos insuficientes.");
		}
		return true;
	}
	
	public ChequeEstado getChequeEstado() {
		return chequeEstado;
	}
	
	public void setChequeEstado(ChequeEstado chequeEstado) {
		this.chequeEstado = chequeEstado;
	}

	public String getCuenta() {
		return cuenta;
	}
	
	public void setCuenta(String cuenta) {
		this.cuenta = cuenta;
	}

	public String getCbu() {
		return cbu;
	}

	public void setCbu(String cbu) {
		this.cbu = cbu;
	}

	public TipoCuentaBanco getTipoCuentaBanco() {
		return tipoCuentaBanco;
	}

	public void setTipoCuentaBanco(TipoCuentaBanco tipoCuentaBanco) {
		this.tipoCuentaBanco = tipoCuentaBanco;
	}

}

