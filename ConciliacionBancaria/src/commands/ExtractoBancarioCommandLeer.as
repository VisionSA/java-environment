package commands{
	import business.DetalleExtracto;
	
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

	public class ExtractoBancarioCommandLeer implements IResponder, ICommand{
		
		private var modelo : ConciliacionModelLocator = ConciliacionModelLocator.getInstance();

		public function execute(event:CairngormEvent):void{
			ControlBlock.getInstance().add();
			var ro:RemoteObject = ServiceLocator.getInstance().getRemoteObject("detalleExtractoService");
			var pendingCall:AsyncToken = ro.getDetalleExtractosNoConciliadosFlex(event.data);
			pendingCall.addResponder(this);
			
		}
		
		public function result(data:Object):void{
			var resultado : Array = ResultEvent(data).result as Array;
			if (resultado != null){
				modelo.listaExtracto = new ArrayCollection(resultado);
/*@I3918*/				modelo.habilitarFiltrosParaConcicliacion = !(modelo.listaMovimiento == null && modelo.listaExtracto == null);
/*@F3918*/				this.eliminarItemSeleccionadoDeListaExtracto();
			}
			ControlBlock.getInstance().remove();			
		}
		
		public function fault(info:Object):void{
			trace(FaultEvent(info).fault.faultString,FaultEvent(info).fault.faultDetail);
			AlertError.show(FaultEvent(info).fault.faultString);
			ControlBlock.getInstance().remove();
		}
		/**
		 * Este metodo se usar para eliminar de la listaExtracto un item que fue seleccionado para conciliar
		 * Ya que si se vuelve a buscar se carga nuevamente en la listaExtracto
		 * */
		private function eliminarItemSeleccionadoDeListaExtracto():void{
			//Elimina de listaExtracto los items que pudieran existir en la conciliacion temporal.				
			if(modelo.cabeceraNueva!=null)
			{
				for each (var obj:Object in modelo.cabeceraNueva.conciliacionFondos){
					if(obj is DetalleExtracto)
					{
						var detalle:DetalleExtracto = obj as DetalleExtracto;
						for each (var detalleBase:DetalleExtracto in modelo.listaExtracto){
							if(detalle.idDetalleExtracto == detalleBase.idDetalleExtracto)
							{
								modelo.listaExtracto.removeItemAt(modelo.listaExtracto.getItemIndex(detalleBase));
								break;
							}
						}
					}
				}
			}
		}
		
	}
}