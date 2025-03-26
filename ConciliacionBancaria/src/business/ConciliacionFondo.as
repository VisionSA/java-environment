package business{
	
	[Bindable]
	[RemoteClass( alias="com.bizitglobal.tarjetafiel.fondos.negocio.ConciliacionFondo")]
	public class ConciliacionFondo{
		public static var TIPO_ID_FONDO:String="F";
		public static var TIPO_ID_EXTRACTO:String="E";
		
		public var idConciliacion:Number;
		public var conciliacionFondoCabecera:ConciliacionFondoCabecera;
		public var tipo:String;
		public var idRegistro:Number;
		public var importe:Number;
		public var signo:int;
		
		public var contenedor:BaseConciliacion;
		
	
		public function ConciliacionFondo(){
		}
	}
	
}
