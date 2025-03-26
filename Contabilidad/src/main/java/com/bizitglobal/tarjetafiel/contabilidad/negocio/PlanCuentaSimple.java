package com.bizitglobal.tarjetafiel.contabilidad.negocio;

import com.bizitglobal.tarjetafiel.commons.interfaces.Negocio;
/** Clase que representa una version simplificada de plan de cuentas
 * creada a fin de mejorar la perfomance reduciendo la cantidad de memoria 
 * utilizada por las instancias de la clase PlanCuentaDos
*/
public class PlanCuentaSimple  implements Negocio{
	
    private Long	idPlanCuenta;   // DECIMAL(10,0) NOT NULL,
    private Long    idPadre;        // DECIMAL(10,0),
    private String titulo ;         // VARCHAR(40),
    private String estado;
   


	public PlanCuentaSimple() {
		this(null,null,null,null);
	}

	public PlanCuentaSimple(Long idPlanCuenta, Long idPadre, String titulo,String estado) {
		super();
		this.idPlanCuenta = idPlanCuenta;
		this.idPadre = idPadre;
		this.titulo = titulo;
		this.estado=  estado;
	}
	
	
	public PlanCuentaSimple(PlanCuentaSimple planCuenta){
		this.idPlanCuenta = null;//new Long(planCuenta.getIdPlanCuenta());
		this.idPadre = new Long(planCuenta.getIdPadre().longValue());
		this.titulo = new String(planCuenta.getTitulo());
		this.estado = new String(planCuenta.getEstado());
	}
	
	

	public Long getId() {
		return idPlanCuenta;
	}

    public String getLabel() {
		return titulo;
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
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}



	
	
	
}
