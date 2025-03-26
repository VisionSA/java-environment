package com.bizitglobal.tarjetafiel.proveedores.negocio;

import com.bizitglobal.tarjetafiel.commons.interfaces.Negocio;
import com.bizitglobal.tarjetafiel.general.negocio.Banco;
import com.bizitglobal.tarjetafiel.general.negocio.FormaPago;
import com.bizitglobal.tarjetafiel.general.negocio.SucursalFormaPago;
import com.bizitglobal.tarjetafiel.general.negocio.TipoCuentaBanco;

public class ProveedorFormaPago implements Negocio {
	private Long idProvFormaPago;
	private String esChequeCruzado;
	private String ordenCheque;
	private TipoCuentaBanco tipoCta;
	private String codCtaDeposito;
	private String cbu;
	// Se obtiene de la tabla t_plan_cuenta, representa un cuenta contable, la que se pasa a fondos, y el campo
	// c_numero_imputa en la tabla de asientos contables.
	private Long nroCuentaFondos;
	private String esActivo;
	private FormaPago formaPago;
	private Banco banco;
	private Proveedor proveedor;
	private String chequeNoAlaOrden;
	 
	
	public ProveedorFormaPago() {
		this(null,"N",null,null,null,null,null,"S",new FormaPago(),null,null,"S");
	}
	
	public ProveedorFormaPago(Long idProvFormaPago) {
		this(idProvFormaPago,"N",null,null,null,null,null,"S",new FormaPago(),null,null,"S");
	}
	
	public ProveedorFormaPago(Long idProvFormaPago, String esChequeCruzado, 
			String ordenCheque, TipoCuentaBanco tipoCta, String codCtaDeposito, 
			String cbu, Long nroCuentaFondos, String activo, FormaPago formaPago, 
			Banco banco, Proveedor proveedor,String chequeNoAlaOrden) {
		super();
		this.idProvFormaPago = idProvFormaPago;
		this.esChequeCruzado = esChequeCruzado;
		this.ordenCheque = ordenCheque;
		this.tipoCta = tipoCta;
		this.codCtaDeposito = codCtaDeposito;
		this.cbu = cbu;
		this.nroCuentaFondos = nroCuentaFondos;
		this.esActivo = activo;
		this.formaPago = formaPago;
		this.banco = banco;
		this.proveedor = proveedor;
		this.chequeNoAlaOrden= chequeNoAlaOrden;
	}

	public Long getId() {
		return idProvFormaPago;
	}
	
	public String getLabel() {
		return null;
	}
	
	public Banco getBanco() {
		return banco;
	}
	
	public void setBanco(Banco banco) {
		this.banco = banco;
	}
	
	public String getCbu() {
		return cbu;
	}
	
	public void setCbu(String cbu) {
		this.cbu = cbu;
	}
	
	public String getCodCtaDeposito() {
		return codCtaDeposito;
	}
	
	public void setCodCtaDeposito(String codCtaDeposito) {
		this.codCtaDeposito = codCtaDeposito;
	}
	
	public String getEsChequeCruzado() {
		return esChequeCruzado;
	}
	
	public void setEsChequeCruzado(String esChequeCruzado) {
		this.esChequeCruzado = esChequeCruzado;
	}
	
	public FormaPago getFormaPago() {
		return formaPago;
	}
	
	public void setFormaPago(FormaPago formaPago) {
		this.formaPago = formaPago;
	}
	
	public Long getIdProvFormaPago() {
		return idProvFormaPago;
	}
	
	public void setIdProvFormaPago(Long idProvFormaPago) {
		this.idProvFormaPago = idProvFormaPago;
	}
	
	public Long getNroCuentaFondos() {
		return nroCuentaFondos;
	}
	
	public void setNroCuentaFondos(Long nroCuentaFondos) {
		this.nroCuentaFondos = nroCuentaFondos;
	}
	
	public String getOrdenCheque() {
		return ordenCheque;
	}
	
	public void setOrdenCheque(String ordenCheque) {
		this.ordenCheque = ordenCheque;
	}
	
	public Proveedor getProveedor() {
		return proveedor;
	}
	
	public void setProveedor(Proveedor proveedor) {
		this.proveedor = proveedor;
	}
	
	public TipoCuentaBanco getTipoCta() {
		return tipoCta;
	}

	public void setTipoCta(TipoCuentaBanco tipoCta) {
		this.tipoCta = tipoCta;
	}
	
	public String getEsActivo() {
		return esActivo;
	}

	public void setEsActivo(String esActivo) {
		this.esActivo = esActivo;
	}

	public String toString() {
		return "Id:"+idProvFormaPago+"|Cbu:"+cbu+"|TipCta:"+tipoCta;
	}

	public boolean equals(Object unProveedorFormaPago) {
		boolean result = false;
		if(unProveedorFormaPago instanceof ProveedorFormaPago) {
			ProveedorFormaPago aux = (ProveedorFormaPago) unProveedorFormaPago;
			if(aux.getIdProvFormaPago().equals(idProvFormaPago)) {
				result = true;
			}
		}
		
		return result;
	}

	public String getChequeNoAlaOrden() {
		return chequeNoAlaOrden;
	}

	public void setChequeNoAlaOrden(String chequeNoAlaOrden) {
		this.chequeNoAlaOrden = chequeNoAlaOrden;
	}

}
