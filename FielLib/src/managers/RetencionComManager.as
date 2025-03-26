package managers
{
	import com.util.block.ControlBlock;
	import com.util.components.alert.AlertError;
	import com.util.components.alert.AlertOk;
	
	import mx.collections.ArrayCollection;
	import mx.rpc.Fault;
	
	[Bindable]
	public class RetencionComManager
	{
		
		public var retencionesList:ArrayCollection;
		
		public function RetencionComManager()
		{
		}
		
		public function resultRetecionesList(arrayLiq:Array):void{
			ControlBlock.getInstance().remove();
			retencionesList = new ArrayCollection(arrayLiq);
			if(retencionesList.length == 0){
				AlertOk.show("No se encontraron retenciones, según el filtro seleccionado.");
			}
		}
		
		public function fault(fault:Fault):void{
			AlertError.show(fault.faultString);
			ControlBlock.getInstance().remove();
		}

	}
	
}