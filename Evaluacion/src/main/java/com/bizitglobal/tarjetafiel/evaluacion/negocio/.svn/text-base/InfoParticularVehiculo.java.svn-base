package com.bizitglobal.tarjetafiel.evaluacion.negocio;

import com.bizitglobal.tarjetafiel.commons.interfaces.Negocio;
import com.bizitglobal.tarjetafiel.general.negocio.Vehiculo;

public class InfoParticularVehiculo implements Negocio {
	private Long idInfoPartVehic = new Long(0);
	private InformeParticular informeParticular;
	private Vehiculo vehiculo;

	public InfoParticularVehiculo() {
		this(null, new InformeParticular(), new Vehiculo());
	}

	public InfoParticularVehiculo(Long idInfoPartVehic) {
		this(idInfoPartVehic, new InformeParticular(), new Vehiculo());
	}

	public InfoParticularVehiculo(Long idInfoPartVehic, InformeParticular informeParticular, Vehiculo vehiculo) {
		super();
		this.idInfoPartVehic = idInfoPartVehic;
		this.informeParticular = informeParticular;
		this.vehiculo = vehiculo;
	}

	public Long getId() {
		return idInfoPartVehic;
	}
	
	public Long getIdInfoPartVehic() {
		return idInfoPartVehic;
	}
	
	public void setIdInfoPartVehic(Long idInfoPartVehic) {
		this.idInfoPartVehic = idInfoPartVehic;
	}

	public String getLabel() {
		return "Implementar propiedad getLabel";
	}
	
	public InformeParticular getInformeParticular() {
		return informeParticular;
	}
	
	public void setInformeParticular(InformeParticular informeParticular) {
		this.informeParticular = informeParticular;
	}

	public Vehiculo getVehiculo() {
		return vehiculo;
	}
	
	public void setVehiculo(Vehiculo vehiculo) {
		this.vehiculo = vehiculo;
	}
	
	public String toString() {
		
		return "Informe Particular Veiculo: " + 
				"id: " + idInfoPartVehic + 
				", informe Particular: " + informeParticular.toString() +
				", vehiculo: " + vehiculo.toString();
	}
}

