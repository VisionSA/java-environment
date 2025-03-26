package views.categorias.comercio.models
{
	
	import com.tarjetafiel.caja.vo.CodComercio;
	import com.tarjetafiel.caja.vo.Empresa;
	import com.tarjetafiel.caja.vo.util.Filtro;
	import com.util.block.ControlBlock;
	import com.util.components.alert.AlertWarning;
	import com.util.components.alert.AlertOk;
	import events.LiquidacionComEvent;
	
	import flash.events.Event;
	import flash.events.EventDispatcher;
	import flash.events.IEventDispatcher;
	
	import mx.collections.ArrayCollection;
	
	[Bindable]
	public class LiquidacionComModel extends EventDispatcher
	{
		
		public var dispatcher:IEventDispatcher;
		
		private var _liquidacionesList:ArrayCollection;
		
		public var empresa:Empresa;
		
		public var comercio:CodComercio;
		
		public var fechaDesde:Date;
		
		public var buscarEmpresa:Boolean;
		
		public var expand:Boolean = false;
		
		public var expandButtonEnabled:Boolean = false;
		
		public function LiquidacionComModel()
		{
		}
		
		public function buscarLiquidaciones():void{
			
			expandButtonEnabled = false;
			if(liquidacionesList){
				liquidacionesList.removeAll();
				dispatcher.dispatchEvent(new Event("removeItemDataGrig"));
			}
						
			if(!empresa && !comercio){
				AlertWarning.show("No hay una empresa o comercio seleccionado");
				return;
			}
			
			if(!buscarEmpresa && !comercio){
				AlertWarning.show("Debe buscar un comercio para realizar la busqueda de liquidaciones.");
				return;
			}
			
			var evt:LiquidacionComEvent = new LiquidacionComEvent(LiquidacionComEvent.BUSCAR_LIQUIDACIONES);
			var filtro:Filtro = new Filtro();
			filtro.campos.push("periodoDesde");
			filtro.operadores.push(Filtro.MAYOR_IGUAL);
			filtro.valores.push(Filtro.toDate(fechaDesde));
			if(buscarEmpresa){
				filtro.campos.push("liqComercio.codComercio.sucEmpresa.empresa.idEmpresa");
				filtro.operadores.push(Filtro.IGUAL);													
				filtro.valores.push(empresa.idEmpresa);
			} else {
				filtro.campos.push("liqComercio.codComercio.sucEmpresa.idSucEmpresa");
				filtro.operadores.push(Filtro.IGUAL);													
				filtro.valores.push(comercio.sucEmpresa.idSucEmpresa);	
			}
			
			evt.filtro = filtro;
			
			dispatcher.dispatchEvent(evt);
			
			ControlBlock.getInstance().add();
			
			
		}
		
		public function set liquidacionesList(array:ArrayCollection):void{
			_liquidacionesList = array;
			if(_liquidacionesList && _liquidacionesList.length > 0){
				expandButtonEnabled = true; 
			}
			dispatchEvent(new Event("changedLiquidacionesList"));
			if(dispatcher)
				dispatcher.dispatchEvent(new Event("refreshDataGrig"));
		}
		
		[Bindable (event="changedLiquidacionesList")]
		public function get liquidacionesList():ArrayCollection{
			return _liquidacionesList;
		}
		
		public function resetValues():void{
			if(dispatcher)
				dispatcher.dispatchEvent(new Event("removeItemDataGrig"));
		}

	}
}