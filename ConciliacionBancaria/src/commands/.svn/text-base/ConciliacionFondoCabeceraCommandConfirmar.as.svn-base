package commands{
	import com.adobe.cairngorm.business.ServiceLocator;
	import com.adobe.cairngorm.commands.ICommand;
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.tarjetafiel.caja.vo.Operador;
	import com.util.block.ControlBlock;
	import com.util.components.alert.AlertError;
	
	import events.ConciliacionFondoCabeceraEvent;
	
	import model.ConciliacionModelLocator;
	
	import mx.collections.ArrayCollection;
	import mx.controls.Alert;
	import mx.rpc.AsyncToken;
	import mx.rpc.IResponder;
	import mx.rpc.events.FaultEvent;
	import mx.rpc.remoting.RemoteObject;

	public class ConciliacionFondoCabeceraCommandConfirmar implements IResponder, ICommand{
		
		private var modelo : ConciliacionModelLocator = ConciliacionModelLocator.getInstance();
		private var evento:CairngormEvent;
		public function execute(event:CairngormEvent):void{
			
			evento = event;
			var operadorConfirmo:Operador = new Operador();
			operadorConfirmo.codigo = modelo.codigoOperador;
			var ro:RemoteObject = ServiceLocator.getInstance().getRemoteObject("conciliacionFondoCabeceraService");
			var pendingCall:AsyncToken = null;
			
			if(evento.type==ConciliacionFondoCabeceraEvent.CONFIRMAR_CONCILIACION_CABECERA_EVENT)
			{
	 			var listCabeceraConfirmar:Array = (event.data as ArrayCollection).source;
/*@I4304*/				pendingCall = ro.confirmarConciliacionFondoCabecera(listCabeceraConfirmar,null, operadorConfirmo);//pongo null la fecha para que tome el servicio la fecha del servidor
			}else if(evento.type==ConciliacionFondoCabeceraEvent.CONFIRMAR_TODOS_CONCILIACION_CABECERA_EVENT)
			{
				var eventConciliacion:ConciliacionFondoCabeceraEvent = event as ConciliacionFondoCabeceraEvent;
/*@F4304*/				pendingCall = ro.confirmarTodosConciliacionFondoCabecera(null, operadorConfirmo, eventConciliacion.filtro);//pongo null la fecha para que tome el servicio la fecha del servidor
			}
			pendingCall.addResponder(this);
		}
		
		public function result(data:Object):void{
			Alert.show("Conciliacion confirmada con Exito");
			modelo.cabeceraNueva = null;
			modelo.concTempVisible = false;
			modelo.btnGrabarConcVisible = false;

		}
		
		public function fault(info:Object):void{
			trace(FaultEvent(info).fault.faultString,FaultEvent(info).fault.faultDetail);
			AlertError.show(FaultEvent(info).fault.faultString);
			ControlBlock.getInstance().remove();
		}
		
	}
}