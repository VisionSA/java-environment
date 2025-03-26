package com.bizitglobal.tarjetafiel.proveedores.negocio;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import com.bizitglobal.tarjetafiel.general.negocio.ModalidadPago;
import com.bizitglobal.tarjetafiel.general.negocio.Moneda;
import com.bizitglobal.tarjetafiel.general.negocio.SucursalFiel;
import com.bizitglobal.tarjetafiel.operador.negocio.Operador;
import com.bizitglobal.tarjetafiel.impuestos.negocio.Actividad;
import com.bizitglobal.tarjetafiel.impuestos.negocio.Jurisdiccion;
import com.bizitglobal.tarjetafiel.impuestos.negocio.JurisdiccionActividad;

public class Proveedor {
	// Identificador del proveedor.
	private Long idProveedor;
	
	// Cuit para el proveedor.
	private Long cuit;
	
	// Razon social del proveedor.
	private String razonSocial;
	
	// Nombre de fantasia del proveedor.
	private String nombreFantasia;
	
	// Nombre alias para el proveedor.
	private String alias;
	
	// Inscripcion en dgr.
	private String inscripcionDgr;
	
	// Limite de credito para el proveedor.
	private BigDecimal limiteCredito;
	
	// Si integta una sociedad o no.
	private Character integranteSoc;
	
	// Si es empleador o no.
	private Character empleador;
	
	// Si es fecha factura o cuenta corriente.
	private Character fechaFactura;
	
	// Indica la fecha y ahora de modificacion/creacion del proveedor.
	private Timestamp timestamp;

	// Si esta activo o no.
	private Character activo;
	
	// Grupo al cual pertence el proveedor.
	private Grupo grupo = null;
	
	// Lista de domicilios que posee un proveedor.
	private Set domicilios;
	
	// Sucursal donde se registra el proveedor.
	private SucursalFiel sucursalFiel = null;
	
	// Moneda por defecto del proveedor.
	private Moneda moneda = null;
	
	// Modalidad seleccionada para el proveedor que determina la forma de pago.
	private ModalidadPago modalidadPago = null;
	
	// Jurisdiccion para verificar el nro de DGR.
	private Jurisdiccion jurisdiccion;
	
	// Relaciones con otros proveedores del sistema.
	private Set relaciones = new HashSet();
	
	// Lista de emails para el proveedor, al menos debe ser uno.
	private Set emails = new HashSet();
	
	// Lista de comprobantes asociados al proveedor.
	private Set comprobantes = new HashSet();
	
//	 Lista de categorias de impuestos asociados al proveedor.
	private Set provedorCategoria = new HashSet();
	
	// Lista de telefonos que posee un proveedor, al menos deber tener uno.
	private Set telefonos = new HashSet();
	
	// Lista de tipos de vencimiento.
	private Set tiposVtos = new HashSet();
	
	// Lista de clientes que se relacionan con el proveedor.
	private Set clieRelaciones = new HashSet();
	
	// Operador que modifica/crea el proveedor.
	private Operador operador;
	
	// Formas de pago para el proveedor.
	private Set formasDePago = new HashSet();
	
	public Proveedor() {
		this(null,null,null,null,null,null,null,null,null,null,null,null,null,
				null,null,null,null,null,null,null,null,null,null,null,null,null);
	}
	
	
	public Proveedor(Long idProveedor, Long cuit, Integer digitoVerificadorCuit,
			String razonSocial, String nombreFantasia, String inscripcionDgr, 
			String condIva, BigDecimal limiteCredito, String codIva, 
			Character integranteSoc, Character empleador, Timestamp timestamp, 
			Grupo grupo, Set domicilios, SucursalFiel sucursalFiel, Moneda moneda, 
			ModalidadPago modalidadPago, Set relaciones, Jurisdiccion jurisdiccion,
			Set emails, Set comprobantes, Set telefonos, Set tiposVtos, 
			Set clieRelaciones, Operador operador, String alias) {
		super();
		this.idProveedor = idProveedor;
		this.cuit = cuit;
		this.razonSocial = razonSocial;
		this.nombreFantasia = nombreFantasia;
		this.inscripcionDgr = inscripcionDgr;
		this.limiteCredito = limiteCredito;
		this.integranteSoc = new Character('N');
		this.empleador = new Character('N');
		this.timestamp = timestamp;
		this.activo = new Character('S');
		this.grupo = grupo;
		this.sucursalFiel = sucursalFiel;
		this.moneda = moneda;
		this.modalidadPago = modalidadPago;
		this.operador = operador;
		this.alias = alias;
		this.jurisdiccion = jurisdiccion;
	}

	public Character getActivo() {
		return activo;
	}

	public void setActivo(Character activo) {
		this.activo = activo;
	}

	public Set getClieRelaciones() {
		return clieRelaciones;
	}
	
	public void setClieRelaciones(Set clieRelaciones) {
		this.clieRelaciones = clieRelaciones;
	}
	
	public Set getComprobantes() {
		return comprobantes;
	}
	
	public void setComprobantes(Set comprobantes) {
		this.comprobantes = comprobantes;
	}
	
	public Long getCuit() {
		return cuit;
	}
	
	public void setCuit(Long cuit) {
		this.cuit = cuit;
	}
	
	public Set getDomicilios() {
		return domicilios;
	}
	
	public void setDomicilios(Set domicilios) {
		this.domicilios = domicilios;
	}
	
	public Set getEmails() {
		return emails;
	}
	
	public void setEmails(Set emails) {
		this.emails = emails;
	}
	
	public Grupo getGrupo() {
		return grupo;
	}
	
	public void setGrupo(Grupo grupo) {
		this.grupo = grupo;
	}
	
	public Long getIdProveedor() {
		return idProveedor;
	}
	
	public void setIdProveedor(Long idProveedor) {
		this.idProveedor = idProveedor;
	}
	
	public String getInscripcionDgr() {
		return inscripcionDgr;
	}
	
	public void setInscripcionDgr(String inscripcionDgr) {
		this.inscripcionDgr = inscripcionDgr;
	}
	
	public BigDecimal getLimiteCredito() {
		return limiteCredito;
	}
	
	public void setLimiteCredito(BigDecimal limiteCredito) {
		this.limiteCredito = limiteCredito;
	}
	
	public ModalidadPago getModalidadPago() {
		return modalidadPago;
	}
	
	public void setModalidadPago(ModalidadPago modalidadPago) {
		this.modalidadPago = modalidadPago;
	}
	
	public Moneda getMoneda() {
		return moneda;
	}
	
	public void setMoneda(Moneda moneda) {
		this.moneda = moneda;
	}
	
	public String getNombreFantasia() {
		return nombreFantasia;
	}
	
	public void setNombreFantasia(String nombreFantasia) {
		this.nombreFantasia = nombreFantasia;
	}
	
	public Operador getOperador() {
		return operador;
	}
	
	public void setOperador(Operador operador) {
		this.operador = operador;
	}
	
	public String getRazonSocial() {
		return razonSocial;
	}
	
	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}
	
	public Set getRelaciones() {
		return relaciones;
	}
	
	public void setRelaciones(Set relaciones) {
		this.relaciones = relaciones;
	}
	
	public Jurisdiccion getJurisdiccion() {
		return jurisdiccion;
	}


	public void setJurisdiccion(Jurisdiccion jurisdiccion) {
		this.jurisdiccion = jurisdiccion;
	}


	public SucursalFiel getSucursalFiel() {
		return sucursalFiel;
	}
	
	public void setSucursalFiel(SucursalFiel sucursalFiel) {
		this.sucursalFiel = sucursalFiel;
	}
	
	public Set getTelefonos() {
		return telefonos;
	}
	
	public void setTelefonos(Set telefonos) {
		this.telefonos = telefonos;
	}
	
	public Timestamp getTimestamp() {
		return timestamp;
	}
	
	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}
		
	public Set getProvedorCategoria() {
		return provedorCategoria;
	}

	public void setProvedorCategoria(Set provedorCategoria) {
		this.provedorCategoria = provedorCategoria;
	}

	public Set getTiposVtos() {
		return tiposVtos;
	}
	
	public void setTiposVtos(Set tiposVtos) {
		this.tiposVtos = tiposVtos;
	}

	public Character getEmpleador() {
		return empleador;
	}

	public void setEmpleador(Character empleador) {
		this.empleador = empleador;
	}

	public Character getIntegranteSoc() {
		return integranteSoc;
	}

	public void setIntegranteSoc(Character integranteSoc) {
		this.integranteSoc = integranteSoc;
	}
	
	public Set getFormasDePago() {
		return formasDePago;
	}

	public void setFormasDePago(Set formasDePago) {
		this.formasDePago = formasDePago;
	}
	
	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}
	
	public Character getFechaFactura() {
		return fechaFactura;
	}


	public void setFechaFactura(Character fechaFactura) {
		this.fechaFactura = fechaFactura;
	}	
	
	public String toString() {
		return "Id: " + idProveedor + "Cuit:"+cuit+"Razon Social:"+razonSocial;
	}
	
	public boolean equals(Object unProveedor) {
		boolean result = false;
		if(unProveedor instanceof Proveedor) {
			Proveedor aux = (Proveedor)unProveedor;
			if(aux.getIdProveedor().equals(idProveedor)) {
				result = true;
			}
		}
		return result;
	}

}
