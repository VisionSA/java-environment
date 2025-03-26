package views.categorias.comercio.models
{
	import com.tarjetafiel.caja.vo.CodComercio;
	import com.tarjetafiel.caja.vo.RetencionComercio;
	import com.tarjetafiel.caja.vo.Empresa;
	import com.tarjetafiel.caja.vo.util.Filtro;
	import com.util.block.ControlBlock;
	import com.util.components.alert.AlertWarning;
	
	import events.RetencionComEvent;
	
	import flash.events.Event;
	import flash.events.EventDispatcher;
	import flash.events.IEventDispatcher;
	
	import mx.collections.ArrayCollection;
	
	[Bindable]
	public class RetencionComercioModel extends EventDispatcher
	{
		public var dispatcher:IEventDispatcher;
		public var comercio:CodComercio;
		public var fechaDesde:Date;
		private var _retencionesList:ArrayCollection;
		public var empresa:Empresa;
		public var buscarEmpresa:Boolean;
		
		public function RetencionComercioModel()
		{
		}
		
		public function buscarRetenciones():void{
			
			if(retencionesList){
				retencionesList.removeAll();
				dispatcher.dispatchEvent(new Event("removeItemDataGrig"));
			}
						
			if(!empresa && !comercio){
				AlertWarning.show("No hay una empresa o comercio seleccionado");
				return;
			}
			
			if(!buscarEmpresa && !comercio){
				AlertWarning.show("Debe buscar un comercio para realizar la busqueda de retenciones.");
				return;
			}
			
			var evt:RetencionComEvent = new RetencionComEvent(RetencionComEvent.BUSCAR_RETENCIONES_COMERCIO);
			if(buscarEmpresa){
				AlertWarning.show("Debe buscar un comercio para realizar la busqueda de retenciones.");
				return;
			} else {
				evt.fechaDesde = fechaDesde;
				evt.idCodComercio = comercio.idCodComercio;	
			}
			
			dispatcher.dispatchEvent(evt);
			ControlBlock.getInstance().add();
		}
		
		public function set retencionesList(array:ArrayCollection):void{
			_retencionesList = array;
			
			dispatchEvent(new Event("changedLiquidacionesList"));
			/*
			if(dispatcher)
				dispatcher.dispatchEvent(new Event("refreshDataGrig"));
				*/
		}
		
		[Bindable (event="changedLiquidacionesList")]
		public function get retencionesList():ArrayCollection{
			return _retencionesList;
		}
		
		public function resetValues():void{
			if(dispatcher)
				dispatcher.dispatchEvent(new Event("removeItemDataGrig"));
		}

	}
}