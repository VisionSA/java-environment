package commands
{
	import com.adobe.cairngorm.business.ServiceLocator;
	import com.adobe.cairngorm.commands.ICommand;
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.tarjetafiel.caja.vo.Operador;
	import com.tarjetafiel.caja.vo.util.Filtro;
	import com.util.block.ControlBlock;
	import com.util.components.alert.AlertError;
	
	import events.ConciliacionFondoCabeceraEvent;
	import events.ConciliacionFondoEvent;
	
	import model.ConciliacionModelLocator;
	
	import mx.collections.ArrayCollection;
	import mx.controls.Alert;
	import mx.rpc.AsyncToken;
	import mx.rpc.IResponder;
	import mx.rpc.events.FaultEvent;
	import mx.rpc.events.ResultEvent;
	import mx.rpc.remoting.RemoteObject;

	public class ConciliacionFondoCommandRevertir implements ICommand, IResponder {
		
		private var modelo : ConciliacionModelLocator = ConciliacionModelLocator.getInstance();
		private var filter:Object;
		
		public function execute(event:CairngormEvent):void {
			var ev:ConciliacionFondoEvent = event as ConciliacionFondoEvent;
			var operadorReversion:Operador = new Operador();
			operadorReversion.codigo = modelo.codigoOperador;
			ControlBlock.getInstance().add();
			var ro:RemoteObject = ServiceLocator.getInstance().getRemoteObject("conciliacionFondoService");
			var pendingCall:AsyncToken = null;
			if(ev.type == ConciliacionFondoEvent.REVERTIR_CONCILIACION_CABECERA_EVENT)
			{
/*@I4304*/				pendingCall = ro.revertirConciliacionFondo((event.data as ArrayCollection).source, null, operadorReversion);//pongo null la fecha para que tome el servicio la fecha del servidor
			}else if(ev.type == ConciliacionFondoEvent.REVERTIR_TODOS_CONCILIACION_CABECERA_EVENT)
			{
				pendingCall = ro.revertirConciliacionFondoTodos(ev.filtro, null, operadorReversion);//pongo null la fecha para que tome el servicio la fecha del servidor
/*@F4304*/			}
			pendingCall.addResponder(this);
			this.filter = ev.filter;			
		}
		
		public function result(data:Object):void {
			ControlBlock.getInstance().remove();
			var resultado : String = ResultEvent(data).result as String;
			if (resultado == null){
				resultado = "Se revirtió la conciliación bancaria con éxito.";
			}
			Alert.show(resultado);
			if(filter != null){
				new ConciliacionFondoCabeceraEvent(ConciliacionFondoCabeceraEvent.TRAER_CONCILIADOS_FONDOS_CABECERA_EVENT, filter).dispatch();
			}
			modelo.listaConciliable.removeAll();
			modelo.refreshListRevertidos = true;
		}
		
		public function fault(info:Object):void {
			trace(FaultEvent(info).fault.faultString,FaultEvent(info).fault.faultDetail);
			AlertError.show(FaultEvent(info).fault.faultString);
			ControlBlock.getInstance().remove();
		}
		
	}
}