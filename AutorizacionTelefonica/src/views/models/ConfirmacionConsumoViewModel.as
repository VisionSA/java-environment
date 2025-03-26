package views.models
{
	import com.tarjetafiel.caja.vo.ClienteTransaccion;
	import com.tarjetafiel.caja.vo.CodComercio;
	import com.tarjetafiel.caja.vo.Cuota;
	
	import events.ConfirmacionConsumoEvent;
	
	import flash.events.IEventDispatcher;
	
	import mx.collections.ArrayCollection;
	
	[Bindable]
	public class ConfirmacionConsumoViewModel
	{
		public var dispatcher:IEventDispatcher;
		
		private var _cliente:ClienteTransaccion;
		
		private var _arrayCuotas:ArrayCollection;
		
		private var _comercio:CodComercio;
		
		public var nombreCompleto:String;
		
		private var  _cuotaSeleccionada:Cuota;
		
		public var importeCuota:Number=new Number(0);
	 	
		private  var _nroPlastico : String; 
		
		private var _cantidadCuotaSeleccionada :  Number;
		
		public var importe :Number = 0;
		
	    private var _nroCupon:String; 
	    
	
	
	
		public function ConfirmacionConsumoViewModel()
		{ 
		}
		
	
		public function set cliente(cli:ClienteTransaccion):void{
			_cliente = cli;
			if(_cliente)
		    	nombreCompleto= cli.individuo.apellido+","+cli.individuo.nombres;
		}
		
		public function get cliente():ClienteTransaccion{
			return _cliente;
		}
		
		public function set comercio(comercio:CodComercio):void{
			_comercio = comercio;			
		}
		
		public function get comercio():CodComercio{
			return _comercio;
		}
		
		
		public function set arrayCuotas(array:ArrayCollection):void{
			_arrayCuotas = array;
		}
		
		public function get arrayCuotas():ArrayCollection{
			return _arrayCuotas;
		}
		

		public function confirmarConsumo(monto:String,codComercio:String,nroTarjeta:String,cuota:String,nroCupon:String):void{
			var evt:ConfirmacionConsumoEvent = new ConfirmacionConsumoEvent(ConfirmacionConsumoEvent.CONFIRMAR_CONSUMO);
			evt.monto =  monto;
			evt.codComercio= codComercio;
			evt.nroTarjeta= nroTarjeta ;
			evt.cuota =  cuota;
			evt.nroCupon =  nroCupon;
			 if( _comercio.presentaCupones == "S")
	 			   evt.estadoConc = "C"; 
	 			 else  evt.estadoConc = "P";
			dispatcher.dispatchEvent(evt);
			importeCuota = 0;
		}
					
			
        public function set nroPlastico(nro:String):void{
			_nroPlastico = nro;
		}
		
		public function get nroPlastico():String{
			return _nroPlastico;
		}
		
		public function get nroCupon():String{
			return _nroCupon;
		}
		
	    public function set nroCupon(nro:String):void{
			_nroCupon = nro;
		}

		
		
		
		public function calcularImporte(cantCuotas:Number):void{
			 for each ( var element:Cuota in arrayCuotas)
                {   if(element.cantidadCuota ==cantCuotas ){
					    importe =  element.interes+ element.capital;
					  break; 
                    }
				 }
		}
      }	


}