package business{
	import com.tarjetafiel.caja.vo.BancoPropio;
	
	
	
	[Bindable]
	[RemoteClass( alias="com.bizitglobal.tarjetafiel.fondos.negocio.DetalleExtracto")]
	public class DetalleExtracto extends BaseConciliacion{
		
		public var idDetalleExtracto:Number;
		public var registro:String;
		public var tipoRegistro:String;
		public var fechaProceso:Date;
		public var nroComprobante:String;
		public var importe:Number;
		public var nroCuentaCorto:String ;
		public var conciliado:String;
		public var extractoBancario:ExtractoBancario;
		public var concepto:String;
		public var bancoPropio:BancoPropio;
		public var fechaMovimiento:Date; // fecha del Asiento
		public var fechaValor:Date;
		public var signo:String;// "D": debito  "C":credito.
		public var codigoOperacion:String;
		public var descripcion:String;
		public var sucursalOrigen:String;
		public var codigoDepositante:String;
		public var nroCuenta:String;
		public var codigoOperacionBanco:String;
		
		//Parametros
		public var idBancoPropio:Number;
		public var fechaCorte:Date;
		
		override public function get importeConSigno():Number{
			if (signo == 'C'){
				return importe * (-1);
			}
			return importe;
		}
		
		override public function get conciliadoBase():String{
			return conciliado;
		}
		
		override public function get debe():String{
			if (signo == 'D'){
				return formatNumber.format(importe);
			}else{
				return "";
			}
		}
		
		override public function get haber():String{
			if (signo == 'C'){
				return formatNumber.format(importe);
			}else{
				return "";
			}
		}
		
		override public function get getTipo():String{
			return "E";
		}
		
		override public function get fechaItem():Date{
			return fechaMovimiento;
		}
		override public function get nroIdentifica():String{
			return nroComprobante;
		}
	}
}