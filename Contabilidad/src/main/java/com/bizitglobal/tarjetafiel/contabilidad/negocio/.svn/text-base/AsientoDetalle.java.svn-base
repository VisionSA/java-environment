package com.bizitglobal.tarjetafiel.contabilidad.negocio;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.bizitglobal.tarjetafiel.contabilidad.negocio.LoteDetalle.Id;

public class AsientoDetalle {
	
	private AsientoDetalle.Id id;
//	private Integer idEmpresa;
//	private Integer idEjercicio;
//	private Integer renglon;
	private Long idPrincipal;
//	private Long idAsiento;
	private Long numeroImputa;
	private String signo;
	private double importe;
	private String leyenda;
	private Date fechaContab;
	private Date fechaCarga;
	private Timestamp horaCarga;
	private String operador;
	
	public final static String ASIENTO_DETALLE = "t_cont_asientos_d";
	public final static String ID_EMPRESA = "c_empresa";
	public final static String ID_EJERCICIO = "c_ejercicio";
	public final static String RENGLON = "c_renglon";
	public final static String ID_ASIENTO = "c_asiento";
	public final static String NUMERO_IMPUTA = "c_numero_imputa";
	public final static String SIGNO = "c_signo";
	public final static String IMPORTE = "c_importe";
	public final static String LEYENDA = "c_leyenda";
	public final static String FECHA_CONTAB = "c_fecha_contab";
	public final static String FECHA_CARGA = "c_fecha_carga";
	public final static String OPERADOR = "c_operador";
	
    public AsientoDetalle() {
    	this.id = new Id();
    }
    
    
    public AsientoDetalle(Integer idEmpresa, Integer idEjercicio, Integer renglon, Integer idAsiento) {
    	Id id = new Id(idEmpresa, idEjercicio, renglon, idAsiento);
    	this.id = id;
    }
    
    public AsientoDetalle(Integer idEmpresa, Integer idEjercicio, Integer renglon, Integer idAsiento, Long numeroImputa, String signo, double importe,
    	String leyenda, Date fechaContab, Date fechaCarga, Timestamp horaCarga, String operador) {
    	this.id = new Id(idEmpresa, idEjercicio, renglon, idAsiento);
        this.numeroImputa = numeroImputa;
        this.signo = signo;
        this.importe = importe;
        this.leyenda = leyenda;
        this.fechaContab = fechaContab;
        this.fechaCarga = fechaCarga;
        this.horaCarga = horaCarga;
        this.operador = operador;
    }

	public String getLeyenda() {
		return leyenda;
	}

	public void setLeyenda(String leyenda) {
		this.leyenda = leyenda.trim();
	}

	public Date getFechaCarga() {
		return fechaCarga;
	}

	public void setFechaCarga(Date fechaCarga) {
		this.fechaCarga = fechaCarga;
	}

	public Date getFechaContab() {
		return fechaContab;
	}

	public void setFechaContab(Date fechaContab) {
		this.fechaContab = fechaContab;
	}

	public Timestamp getHoraCarga() {
		return horaCarga;
	}

	public void setHoraCarga(Timestamp horaCarga) {
		this.horaCarga = horaCarga;
	}

//	public Long getIdAsiento() {
//		return idAsiento;
//	}
//
//	public void setIdAsiento(Long idAsiento) {
//		this.idAsiento = idAsiento;
//	}

	public String getOperador() {
		return operador;
	}

	public void setOperador(String operador) {
		this.operador = operador;
	}

	public double getImporte() {
		return importe;
	}

	public void setImporte(double importe) {
		this.importe = importe;
	}

	public Long getNumeroImputa() {
		return numeroImputa;
	}

	public void setNumeroImputa(Long numeroImputa) {
		this.numeroImputa = numeroImputa;
	}

	public String getSigno() {
		return signo;
	}

	public void setSigno(String signo) {
		this.signo = signo;
	}

	public Long getIdPrincipal() {
		return idPrincipal;
	}

	public void setIdPrincipal(Long idPrincipal) {
		this.idPrincipal = idPrincipal;
	}

	public AsientoDetalle.Id getId() {
		return id;
	}

	public void setId(AsientoDetalle.Id id) {
		this.id = id;
	}

	
     public static class Id implements Serializable {

		
		
		/**
	 * 
	 */
	    private static final long serialVersionUID = -5811996012232928663L;
		   protected Integer idEmpresa;
		   protected Integer idEjercicio;
		   protected Integer renglon;
		   protected Integer idAsiento;
		
		
		public Id() {
			super();
			// TODO Auto-generated constructor stub
		}
		
		public Id(Integer idEmpresa, Integer idEjercicio, Integer renglon, Integer idAsiento) {
			this.idEmpresa = idEmpresa;
			this.idEjercicio = idEjercicio;
			this.renglon = renglon;
			this.idAsiento = idAsiento;
		}
        

		public boolean equals(Object id) {
			if (this == id) {
				return true;
			}
			if (id instanceof AsientoDetalle.Id == false)
				return false;
			AsientoDetalle.Id rhs = (AsientoDetalle.Id) id;
			return new EqualsBuilder()
							.append(idEmpresa, rhs.idEmpresa)
							.append(idEjercicio, rhs.idEjercicio)
							.append(renglon, rhs.renglon)
							.isEquals();
		}
		
		public int hashCode() {
			return new HashCodeBuilder(17, 37)
							.append(idEmpresa)
							.append(idEjercicio)
							.append(renglon)
							.toHashCode();
		}
		
		public String toString() {
			return new ToStringBuilder(this)
					.append("empresa", idEmpresa)
					.append("Ejercicio", idEjercicio)
					.append("Renglon ", renglon)
					.toString();
		}

		public Integer getIdEjercicio() {
			return idEjercicio;
		}

		public void setIdEjercicio(Integer idEjercicio) {
			this.idEjercicio = idEjercicio;
		}

		public Integer getIdEmpresa() {
			return idEmpresa;
		}

		public void setIdEmpresa(Integer idEmpresa) {
			this.idEmpresa = idEmpresa;
		}

		public Integer getRenglon() {
			return renglon;
		}

		public void setRenglon(Integer renglon) {
			this.renglon = renglon;
		}

		public Integer getIdAsiento() {
			return idAsiento;
		}

		public void setIdAsiento(Integer idAsiento) {
			this.idAsiento = idAsiento;
		}
	}


//	public Integer getIdEjercicio() {
//		return idEjercicio;
//	}
//
//	public void setIdEjercicio(Integer idEjercicio) {
//		this.idEjercicio = idEjercicio;
//	}
//
//	public Integer getIdEmpresa() {
//		return idEmpresa;
//	}
//
//	public void setIdEmpresa(Integer idEmpresa) {
//		this.idEmpresa = idEmpresa;
//	}
//
//	public Integer getRenglon() {
//		return renglon;
//	}
//
//	public void setRenglon(Integer renglon) {
//		this.renglon = renglon;
//	}
//	
}
