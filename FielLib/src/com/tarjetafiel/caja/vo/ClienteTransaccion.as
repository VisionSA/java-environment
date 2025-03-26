package com.tarjetafiel.caja.vo
{
	import mx.collections.ArrayCollection;
	

	[Bindable]
 	[RemoteClass( alias="com.bizitglobal.tarjetafiel.transacciones.negocio.ClienteTransaccion")]
	public class ClienteTransaccion implements Negocio
	{
		public var idCliente:Number;
		public var adicional:Number; //o si es titular, o el numero de adicionales que posea la tarjeta 
		public var ciclo:Number;		
		public var estadoCobranza:EstadoCobranza;
		public var estadoCliente:EstadoCliente;
		public var fechaEstadoClienteFlex:Date;
		public var fechaEstadoCobranzaFlex:Date;
		public var fechaHabiitadoConsumoFlex:Date;
		public var habilitadoConsumo:String;
		public var individuo:IndividuoEvaluacion;
		public var idOperadorCliente:Number;
		public var idOperadorCobranza:Number;
		public var idOperadorHabilitadoconsumo:Number;
		//public var sucursal:SucursalFiel;
		public var idTitular:Number; //en caso de ser Adicional este campo lleva el id del titular
		public var limiteCredito:Object;
		public var saldoLinea:Object;
		public var clienteConceptoSet:Array;
		public var ctaCteClienteSet:Array;
		public var plasticoClienteSet:ArrayCollection;
		public var transaccionSet:Array;
		public var nombreCliente:String;
		public var platicoClienteHabilitado:PlasticoCliente;
		public var nroSolicitud:String;
		public var fechaAlta:Date;
		
		public var idClienteViejo:Number;
		public var domicilioValido : Number;
		public var diaCierre : Number;
		public var tieneEventos : Boolean;
		public var nuevoTipoCliente:String;
		public var tipoCliente:String;
		
		public var apellidoConsulta:String;
		public var nombreConsulta:String;
		public var documentoConsulta:String;
		public var cuilConsulta:String;
		public var cuentaConsulta:Number;
		public var referenciaConsulta:String;
		public var domicilioConsulta:String;
		public var nuevoInfoCliente:String;
		public var nombreTitularConsulta:String;
		public var estadoClienteConsulta:EstadoCliente;
		public var domicilioResumen:String;
		
		public var domicilio:String;
		public var localidad:String;
		public var codPost:String;
		public var provincia:String;
		public var cobranzaClienteConsulta:EstadoCobranza;
		

		public function getId():Number{return idCliente;}
		public function getLabel():String{return "";}
		
	}
}