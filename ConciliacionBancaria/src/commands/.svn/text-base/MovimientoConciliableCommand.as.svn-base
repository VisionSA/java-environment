package commands
{
	import business.MovimientoConciliable;
	
	import com.adobe.cairngorm.business.ServiceLocator;
	import com.adobe.cairngorm.commands.ICommand;
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.util.block.ControlBlock;
	import com.util.components.alert.AlertError;
	
	import model.ConciliacionModelLocator;
	
	import mx.collections.ArrayCollection;
	import mx.rpc.AsyncToken;
	import mx.rpc.IResponder;
	import mx.rpc.events.FaultEvent;
	import mx.rpc.events.ResultEvent;
	import mx.rpc.remoting.RemoteObject;

	public class MovimientoConciliableCommand implements ICommand, IResponder {
		
		private var modelo : ConciliacionModelLocator = ConciliacionModelLocator.getInstance();
		
		public function execute(event:CairngormEvent):void {
			ControlBlock.getInstance().add();
			var obj : Object = event.data;
			var ro:RemoteObject = ServiceLocator.getInstance().getRemoteObject("movimientoConciliableService");
			var pendingCall:AsyncToken = ro.listarNoConciliados(obj.idPlanCuentas, obj.fechaDesde, obj.fechaHasta);
			pendingCall.addResponder(this);
		}
		
		public function result(data:Object):void {
			var resultado : Array = ResultEvent(data).result as Array;
			if (resultado != null){
				modelo.listaMovimiento = new ArrayCollection(resultado);
/*@I3918*/				modelo.habilitarFiltrosParaConcicliacion = !(modelo.listaMovimiento == null && modelo.listaExtracto == null);
/*@F3918*/				this.eliminarItemSeleccionadoDeListaMovimiento();
			}
			ControlBlock.getInstance().remove();	
			
		}
		
		public function fault(info:Object):void {
			trace(FaultEvent(info).fault.faultString,FaultEvent(info).fault.faultDetail);
			AlertError.show(FaultEvent(info).fault.faultString);
			ControlBlock.getInstance().remove();			
		}
		
		/**
		 * Este metodo se usar para eliminar de la listaMovimiento un item que fue seleccionado para conciliar
		 * Ya que si se vuelve a buscar se carga nuevamente en la listaMovimiento
		 * */
		private function eliminarItemSeleccionadoDeListaMovimiento():void{
			//Elimina de listaMovimiento los items que pudieran existir en la conciliacion temporal.				
			if(modelo.cabeceraNueva!=null)
			{
				for each (var obj:Object in modelo.cabeceraNueva.conciliacionFondos){
					if(obj is MovimientoConciliable)
					{
						var detalle:MovimientoConciliable = obj as MovimientoConciliable;
						for each (var detalleBase:MovimientoConciliable in modelo.listaMovimiento){
							if(detalle.idAsiento == detalleBase.idAsiento)
							{
								modelo.listaMovimiento.removeItemAt(modelo.listaMovimiento.getItemIndex(detalleBase));
								break;
							}
						}
					}
				}
			}
		}
		
	}
}