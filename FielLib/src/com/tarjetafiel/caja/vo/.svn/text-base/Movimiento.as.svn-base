package com.tarjetafiel.caja.vo
{
	[Bindable]
 	[RemoteClass( alias="com.bizitglobal.tarjetafiel.fondos.negocio.Movimiento")]
	public class Movimiento
	{
			
		public var idMovimiento:Number;
		public var signo:Number;
		public var fecha:Date;
		public var operador:Operador;
		public var importe:Number;
		public var estado:String;
		public var concepto:ConceptoGen;		
		public var caja:Caja;		
		public var cajaApertura:CajaApertura;
		public var movimientosMP:Array;
		public var tipo:String; 
		public var ticket:String;	
		

		public function setDatosPrincipales(cajaApertura:CajaApertura,operador:Operador,movsMP:Array):void{
			this.cajaApertura = cajaApertura;
			this.caja = cajaApertura.caja;
			this.operador = operador;
			this.movimientosMP = movsMP;
			for each (var m : MovimientoMP in movsMP){
				m.movimiento = this;				
			}
		}
		
	}
}