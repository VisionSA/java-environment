package commands{
	import business.BaseConciliacion;
	import business.ConciliacionFondoCabecera;
	import business.MovimientoConciliable;
	
	import com.adobe.cairngorm.commands.ICommand;
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.util.block.ControlBlock;
	import com.util.components.alert.AlertError;
	
	import model.ConciliacionModelLocator;
	
	import mx.collections.ArrayCollection;
	import mx.rpc.IResponder;
	import mx.rpc.events.FaultEvent;

	public class ConciliacionFondoCommandArmarYGrabar implements IResponder, ICommand{
		
		private var modelo : ConciliacionModelLocator = ConciliacionModelLocator.getInstance();
		
		public function execute(event:CairngormEvent):void{
			ControlBlock.getInstance().add();
			if(modelo.cabeceraNueva == null){
				modelo.cabeceraNueva = new ConciliacionFondoCabecera();
				modelo.cabeceraNueva.fechaGeneracion = new Date();
				modelo.cabeceraNueva.conciliado = "N";
				modelo.cabeceraNueva.bancoPropio = modelo.bancoPropioSeleccionado;
				modelo.cabeceraNueva.conciliacionFondos = new ArrayCollection();
				modelo.concTempVisible = true;
			}
			if(!modelo.cabeceraNueva.conciliacionFondos.contains(event.data)){
				
				modelo.cabeceraNueva.conciliacionFondos.addItem(event.data);
				if(event.data is MovimientoConciliable)
				{
					modelo.listaMovimiento.removeItemAt(modelo.listaMovimiento.getItemIndex(event.data));
				}else
				{
					modelo.listaExtracto.removeItemAt(modelo.listaExtracto.getItemIndex(event.data));
				}
				
				var sumatoria:Number = new Number(0);
				for each (var base:BaseConciliacion in modelo.cabeceraNueva.conciliacionFondos){
/*@I3918*/					sumatoria = ((sumatoria * 100) + Math.round(base.importeConSigno*100))/100;
/*@F3918*/				}
				
				if(sumatoria == 0 || Math.abs(sumatoria) < 0.001){
					modelo.btnGrabarConcVisible = true;
				}else{
					modelo.btnGrabarConcVisible = false;
				}
			}
			
//			var ro:RemoteObject = ServiceLocator.getInstance().getRemoteObject("detalleExtractoService");
//			var pendingCall:AsyncToken = ro.getDetalleExtractosFlex(event.data);
//			pendingCall.addResponder(this);
			ControlBlock.getInstance().remove();
		}

		public function result(data:Object):void{
			ControlBlock.getInstance().remove();			
		}
		
		public function fault(info:Object):void{
			trace(FaultEvent(info).fault.faultString,FaultEvent(info).fault.faultDetail);
			AlertError.show(FaultEvent(info).fault.faultString);
			ControlBlock.getInstance().remove();
		}
		
		
	}
}