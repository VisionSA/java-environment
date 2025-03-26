package business{
	import mx.formatters.NumberFormatter;
	
	[Bindable]
	[RemoteClass( alias="com.bizitglobal.tarjetafiel.fondos.negocio.BaseConciliacion")]
	public class BaseConciliacion{
		public var descripcionGen:String;
		public var debCred:int;
		public var fechaGeneral:Date; //Asiento
		public var formatNumber:NumberFormatter = new NumberFormatter();
		
		public function BaseConciliacion()
		{
			formatNumber.precision=2;
		}
		public function get importeConSigno():Number{
			return 0;
		}
		
		public function get conciliadoBase():String{
			return "Nada";
		}
		
		public function get debe():String{
			return "debe";
		}
		
		public function get haber():String{
			return "haber";
		}
		
		public function get getTipo():String{
			return "Nada";
		}
		
		
		public function get fechaItem():Date{
			return null;
		}
		public function get nroIdentifica():String{
			return "0";
		}
		
	}
}