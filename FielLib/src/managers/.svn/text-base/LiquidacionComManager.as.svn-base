package managers
{
	import com.util.block.ControlBlock;
	import com.util.components.alert.AlertError;
	import com.util.components.alert.AlertOk;
	
	import mx.collections.ArrayCollection;
	import mx.rpc.Fault;
	
	[Bindable]
	public class LiquidacionComManager
	{
		
		public var liquidacionesList:ArrayCollection;
		
		public function LiquidacionComManager()
		{
		}
		
		public function resultLiquidacionesList(arrayLiq:Array):void{
			ControlBlock.getInstance().remove();
			liquidacionesList = new ArrayCollection(arrayLiq);
			if(liquidacionesList.length == 0){
				AlertOk.show("No se encontraron liquidaciones, según el filtro seleccionado.");
			}
			
			
		}
		
		public function fault(fault:Fault):void{
			AlertError.show(fault.faultString);
			ControlBlock.getInstance().remove();
		}

	}
}