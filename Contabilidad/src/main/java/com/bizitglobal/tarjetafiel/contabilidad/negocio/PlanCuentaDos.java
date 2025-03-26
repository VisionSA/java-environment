package com.bizitglobal.tarjetafiel.contabilidad.negocio;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

import com.bizitglobal.tarjetafiel.commons.interfaces.Negocio;
import com.bizitglobal.tarjetafiel.general.negocio.FormaPago;

public class PlanCuentaDos  implements Negocio{
	
    private Long	idPlanCuenta;   // DECIMAL(10,0) NOT NULL,
    private Long    idPadre;        // DECIMAL(10,0),
    private Long    seccion;         // DECIMAL(2,0),
    private String   numeroContable;   // DECIMAL(13,0),
    private String    estado;          // CHAR(1),
    private String titulo ;         // VARCHAR(40),
    private Long    cuenta ;         // DECIMAL(6,0),
    private String    habilitada ;     	// CHAR(1),
    private Long    operador;        	// /DECIMAL(3,0),
    private Timestamp fecha_carga;     	// DATE,
    private Date fecha_cargaFlex;     	// DATE,
    private String    contab;        // CHAR(1),
    private String fondos;        	// CHAR(1),
    private Long   tipoCuenta;   // DECIMAL(2,0),
    private Long moneda;        	// DECIMAL(1,0),
    private Long codBco;       // DECIMAL(3,0),
    private String codCtaBco;   	// CHAR(17),
    private Integer  signo ;        // SMALLINT,
    private BigDecimal  saldo;         // DECIMAL(10,2),
    private BigDecimal  importeMaximo;	// DECIMAL(10,2),
    private Long caja ;         //	DECIMAL(4,0) 
    private String uso;
    private String saldoHabitual;
    private String centroCosto;
    private String tipoDeCuenta;
    private String ajusteInflacion;
    private String flujoEfectivo;
    private Integer tipoFondos;
    private FormaPago formaPago;
    private int nivel;
    private Long idEstadoCheque;
    private Long idCaja;
	
	public String getAjusteInflacion() {
		return ajusteInflacion;
	}

	public void setAjusteInflacion(String ajusteInflacion) {
		this.ajusteInflacion = ajusteInflacion;
	}

	public String getFlujoEfectivo() {
		return flujoEfectivo;
	}

	public void setFlujoEfectivo(String flujoEfectivo) {
		this.flujoEfectivo = flujoEfectivo;
	}

	public String getTipoDeCuenta() {
		return tipoDeCuenta;
	}

	public void setTipoDeCuenta(String tipoDeCuenta) {
		this.tipoDeCuenta = tipoDeCuenta;
	}

	public PlanCuentaDos() {
		this(null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null);
	}	

	public PlanCuentaDos(Long id) {
		this();
		idPlanCuenta = id;
	}

	public PlanCuentaDos(Long idPlanCuenta, Long idPadre, Long seccion, String numeroContable, String estado,
			String titulo, Long cuenta, String habilitada, Long operador, Timestamp fecha_carga, Timestamp hora_carga,
			String contab, String fondos, Long tipoCuenta, Long moneda, Long codBco, String codCtaBco, Integer signo, 
			BigDecimal saldo, BigDecimal importeMaximo, Long caja,String uso,String saldoHabitual,String centroCosto,
			String tipoDeCuenta,String ajusteInflacion,String flujoEfectivo, Integer tipoFondos) {
		super();
		this.idPlanCuenta = idPlanCuenta;
		this.idPadre = idPadre;
		this.seccion = seccion;
		this.numeroContable = numeroContable;
		this.estado = estado;
		this.titulo = titulo;
		this.cuenta = cuenta;
		this.habilitada = habilitada;
		this.operador = operador;
		this.fecha_carga = fecha_carga;
		this.contab = contab;
		this.fondos = fondos;
		this.tipoCuenta = tipoCuenta;
		this.moneda = moneda;
		this.codBco = codBco;
		this.codCtaBco = codCtaBco;
		this.signo = signo;
		this.saldo = saldo;
		this.importeMaximo = importeMaximo;
		this.caja = caja;
		this.uso = uso;
		this.saldoHabitual=saldoHabitual;
		this.centroCosto=centroCosto;
		this.tipoDeCuenta=tipoDeCuenta;
		this.ajusteInflacion=ajusteInflacion;
		this.flujoEfectivo=flujoEfectivo;
		this.tipoFondos = tipoFondos;
		
	}
	
	public PlanCuentaDos(PlanCuentaDos planCuenta){
	
		
		this.idPlanCuenta = null;//new Long(planCuenta.getIdPlanCuenta());
		this.idPadre = new Long(planCuenta.getIdPadre().longValue());
		this.seccion = new Long(planCuenta.getIdPadre().longValue());
		this.numeroContable = new String(planCuenta.getNumeroContable());
		this.estado = new String(planCuenta.getEstado());
		this.titulo = new String(planCuenta.getTitulo());
		this.cuenta = planCuenta.getCuenta();
		this.habilitada = new String(planCuenta.getHabilitada());
		this.operador = planCuenta.getOperador();
		//this.fecha_carga = fecha_carga;
		this.contab = new String(planCuenta.getContab());
		this.fondos = new String(planCuenta.getFondos());
		this.tipoCuenta = planCuenta.getTipoCuenta();
		this.moneda = planCuenta.getMoneda();
		this.codBco = planCuenta.getCodBco();
		this.codCtaBco = planCuenta.getCodCtaBco();
		this.signo = planCuenta.getSigno();
		//this.saldo = 
		//this.importeMaximo = 
		this.caja = planCuenta.getCaja();
		this.uso = new String(planCuenta.getUso());
		this.saldoHabitual=new String(planCuenta.getSaldoHabitual());
	    this.centroCosto= planCuenta.getCentroCosto();
		this.tipoDeCuenta=new String(planCuenta.getTipoDeCuenta());
		this.ajusteInflacion= new String(planCuenta.getAjusteInflacion());
		this.flujoEfectivo=new String(planCuenta.getFlujoEfectivo());
		this.tipoFondos = new Integer(planCuenta.getTipoFondos());
		
	}
	
	

	public Long getId() {
		return idPlanCuenta;
	}

    public String getLabel() {
		return titulo;
	}
	
	public Long getCaja() {
		return caja;
	}
	public void setCaja(Long caja) {
		this.caja = caja;
	}
	public Long getCodBco() {
		return codBco;
	}
	public void setCodBco(Long codBco) {
		this.codBco = codBco;
	}
	public String getCodCtaBco() {
		return codCtaBco;
	}
	public void setCodCtaBco(String codCtaBco) {
		this.codCtaBco = codCtaBco;
	}
	public String getContab() {
		return contab;
	}
	public void setContab(String contab) {
		this.contab = contab;
	}
	public Long getCuenta() {
		return cuenta;
	}
	public void setCuenta(Long cuenta) {
		this.cuenta = cuenta;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public Timestamp getFecha_carga() {
		return fecha_carga;
	}
	public void setFecha_carga(Timestamp fecha_carga) {
		this.fecha_carga = fecha_carga;
	}
	public String getFondos() {
		return fondos;
	}
	public void setFondos(String fondos) {
		this.fondos = fondos;
	}
	public String getHabilitada() {
		return habilitada;
	}
	public void setHabilitada(String habilitada) {
		this.habilitada = habilitada;
	}
	public Long getIdPadre() {
		return idPadre;
	}
	public void setIdPadre(Long idPadre) {
		this.idPadre = idPadre;
	}
	public Long getIdPlanCuenta() {
		return idPlanCuenta;
	}
	public void setIdPlanCuenta(Long idPlanCuenta) {
		this.idPlanCuenta = idPlanCuenta;
	}
	public BigDecimal getImporteMaximo() {
		return importeMaximo;
	}
	public void setImporteMaximo(BigDecimal importeMaximo) {
		this.importeMaximo = importeMaximo;
	}
	public Long getMoneda() {
		return moneda;
	}
	public void setMoneda(Long moneda) {
		this.moneda = moneda;
	}
	public String getNumeroContable() {
		return numeroContable;
	}
	public void setNumeroContable(String numeroContable) {
		this.numeroContable = numeroContable;
	}
	public Long getOperador() {
		return operador;
	}
	public void setOperador(Long operador) {
		this.operador = operador;
	}
	public BigDecimal getSaldo() {
		return saldo;
	}
	public void setSaldo(BigDecimal saldo) {
		this.saldo = saldo;
	}
	public Long getSeccion() {
		return seccion;
	}
	public void setSeccion(Long seccion) {
		this.seccion = seccion;
	}
	public Integer getSigno() {
		return signo;
	}
	public void setSigno(Integer signo) {
		this.signo = signo;
	}
	public Long getTipoCuenta() {
		return tipoCuenta;
	}
	public void setTipoCuenta(Long tipoCuenta) {
		this.tipoCuenta = tipoCuenta;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getCentroCosto() {
		return centroCosto;
	}

	public void setCentroCosto(String centroCosto) {
		this.centroCosto = centroCosto;
	}

	public String getSaldoHabitual() {
		return saldoHabitual;
	}

	public void setSaldoHabitual(String saldoHabitual) {
		this.saldoHabitual = saldoHabitual;
	}

	public String getUso() {
		return uso;
	}

	public void setUso(String uso) {
		this.uso = uso;
	}

	public Integer getTipoFondos() {
		return tipoFondos;
	}

	public void setTipoFondos(Integer tipoFondos) {
		this.tipoFondos = tipoFondos;
	}

	public int getNivel() {
		return nivel;
	}

	public void setNivel(int nivel) {
		this.nivel = nivel;
	}

	public FormaPago getFormaPago() {
		return formaPago;
	}
	
	public void setFormaPago(FormaPago formaPago) {
		this.formaPago = formaPago;
	}
	
	public Date getFecha_cargaFlex() {
		if(fecha_carga !=null){
			fecha_cargaFlex = new Date(fecha_carga.getTime());
		}
		return fecha_cargaFlex;
	}
	
	public void setFecha_cargaFlex(Date fecha_cargaFlex) {		
		this.fecha_cargaFlex = fecha_cargaFlex;
		if(fecha_cargaFlex != null){
			fecha_carga = new Timestamp(fecha_cargaFlex.getTime());
		}
	}

	public boolean equals(Object obj) {
		if(obj instanceof PlanCuentaDos) {
			PlanCuentaDos aux = (PlanCuentaDos)obj;
			if(aux.getId().equals(idPlanCuenta)) 
				return true;
		}
		return false;
	}
	
	public Long getIdEstadoCheque() {
		return idEstadoCheque;
	}
	
	public void setIdEstadoCheque(Long idEstadoCheque) {
		this.idEstadoCheque = idEstadoCheque;
	}

	public Long getIdCaja() {
		return idCaja;
	}

	public void setIdCaja(Long idCaja) {
		this.idCaja = idCaja;
	}
}
