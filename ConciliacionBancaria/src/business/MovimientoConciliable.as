package business
{
	import com.tarjetafiel.caja.vo.Banco;
	
	[Bindable]
	[RemoteClass( alias="com.bizitglobal.tarjetafiel.fondos.negocio.MovimientoConciliable")]
	public class MovimientoConciliable extends BaseConciliacion	{
		public var idChequeHistorial:Number;
		public var importe:Number;
		public var numero:String; 
		public var fecha:Date;      //Asiento
		public var signo:String; //D:debito C:credito.
		public var descripcion:String; // leyenda asiento item
		public var numeroCuenta:String; //
		public var tipo:String;     //cheque
		public var beneficiario:String;//cheque
		public var banco:Banco;			
		public var idBanco:Number;
		public var idAsiento:Number;
		
		//Parametros
		public var idPlanCuentas:Number;
		public var fechaDesde:Date;
		public var fechaHasta:Date;
		
		override public function get importeConSigno():Number{
			if (signo == 'D'){
				return importe * (-1);
			}
			return importe;
		}
		
		override public function get debe():String{
			if (signo == 'C'){
				return formatNumber.format(importe);
			}else{
				return "";
			}
		}
		
		override public function get haber():String{
			if (signo == 'D'){
				return formatNumber.format(importe);
			}else{
				return "";
			}
		}
		
		override public function get getTipo():String{
			return "F";
		}
		
		
		override public function get fechaItem():Date{
			return fecha;
		}
		override public function get nroIdentifica():String{
			return numero;
		}
		
		
	}
	
}
