package business{
	import com.tarjetafiel.caja.vo.BancoPropio;
	import com.tarjetafiel.caja.vo.Operador;
	
	import mx.collections.ArrayCollection;
	
	[Bindable]
	[RemoteClass( alias="com.bizitglobal.tarjetafiel.fondos.negocio.ConciliacionFondoCabecera")]
	public class ConciliacionFondoCabecera{
		public function ConciliacionFondoCabecera(){
		}
		
		public var idCabeceraConciliacion:Number; //idGrupo
		public var comentario:String;
		public var fechaGeneracion:Date; //fecha de la conciliacion automatica o upload.
		public var fechaConfirmacion:Date;
		public var operadorConfirmo:Operador;
		public var conciliado:String; // [S | N | R:revertido]
		public var fechaReversion:Date;
		public var operadorReversion:Operador;		
		public var bancoPropio:BancoPropio;		
		public var conciliacionFondos:ArrayCollection;
		
		public var conciliacionFondosFlex:Array;
		public var seleccionado:Boolean; //Es un atributo solo visual, para saber si selecciono o no el item en la grilla.
		
/*@3918*/		//Atributos solo válidos para búsquedas
		public var numeroOperacion:String; //Número de la operación que concilia esta cabecera
		public var importeTotalConciliado:Number; //Importe total de la conciliación
/*@F3918*/		
		//Parametros
		public static const FECHA_CONCILIACION:int = 1;
		public static const FECHA_MOVIMIENTO:int = 2;
		public static const FECHA_EXTRACTO:int = 3;
		public static const SIN_FECHA:int = 0;
		public var tipoFecha:int;
		public var fechaDesde:Date;
		public var fechaHasta:Date;
		public var idBancoPropio:Number;
		 
		public var firstResult:int;
		public var maxResults:int;
	}
}