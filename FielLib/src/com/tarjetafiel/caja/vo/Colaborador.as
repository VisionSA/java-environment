package com.tarjetafiel.caja.vo {
		
	[Bindable]
 	[RemoteClass(alias="com.bizitglobal.tarjetafiel.transacciones.negocio.Colaborador")]
	public class Colaborador {
		
		public var idColaborador : uint;
		public var fechaAlta : Date;
		public var fechaBaja : Date;
		public var estado : String;
		public var nroLegajo : String;
		public var sucursal : SucursalFiel;
		public var individuo : IndividuoEvaluacion;
		public var cobrador : Cobrador;
		public var esAbogado : String;
		
	}
}