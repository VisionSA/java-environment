package managers
{	
	import com.tarjetafiel.caja.vo.ClienteTransaccion;
	import com.tarjetafiel.caja.vo.ListaPrecio;
	import com.util.block.ControlBlock;
	import com.util.components.alert.AlertError;
	
	import events.SimuladorManagerEvent;
	
	import flash.events.IEventDispatcher;
	
	import mx.collections.ArrayCollection;
	import mx.rpc.Fault;
	
	public class SimuladorManager
	{
		public var dispatcher:IEventDispatcher;
		
		[Bindable]public var arrayCuotas:ArrayCollection = new ArrayCollection();
					
		
		public function SimuladorManager()
		{
			
			
		}
		
	  	public function resetArrayCuotas():void{
	  		arrayCuotas = new ArrayCollection();
	  	}
	  	
	  	public function calcularCuotas(monto:Number, lista:ListaPrecio, cliente:ClienteTransaccion):void{
			var evt:SimuladorManagerEvent = new SimuladorManagerEvent(SimuladorManagerEvent.CALCULAR_CUOTAS);
			evt.monto = monto;		
			evt.listaPrecio = lista;
			evt.cliente = cliente;
			this.dispatcher.dispatchEvent(evt);
		}
	  	
	  	
		public function resultCalcularCuotas(result:Array):void{
			ControlBlock.getInstance().remove();
			this.arrayCuotas = new ArrayCollection(result); 	
		}
		
		public function fault(fault:Fault):void{
			AlertError.show(fault.faultDetail);
		}

	}
}