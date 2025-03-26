package commands {
	import com.adobe.cairngorm.business.ServiceLocator;
	import com.adobe.cairngorm.commands.ICommand;
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.tarjetafiel.caja.vo.Cobrador;
	import com.tarjetafiel.caja.vo.Partido;
	import com.util.block.ControlBlock;
	import com.util.components.alert.AlertError;
	
	import model.CobranzasModelLocator;
	
	import mx.controls.Alert;
	import mx.rpc.AsyncToken;
	import mx.rpc.IResponder;
	import mx.rpc.events.FaultEvent;
	import mx.rpc.remoting.RemoteObject;

	public class CambiarCobradorAFuturoCommand implements ICommand, IResponder {
		
		
		[Bindable]
		private var modelo : CobranzasModelLocator = CobranzasModelLocator.getInstance();
		
		private var cobrador : Cobrador = null;
		
		public function execute(event:CairngormEvent):void {
			
			cobrador = event.data as Cobrador;
			
			var partido : Partido = new Partido();
			partido.idPartido = modelo.partidoSeleccionado.idPartido;
			partido.idCobrador = cobrador.idCobrador; 
			
			ControlBlock.getInstance().add();
			var ro:RemoteObject = ServiceLocator.getInstance().getRemoteObject("partidoService");
			var pendingCall:AsyncToken = ro.asignarCobradorAPartido(partido);
			pendingCall.addResponder(this);
			
				
		}
		
		public function result(data:Object):void {
			ControlBlock.getInstance().remove();
			var msg : String = "El cobrador para futuras asignaciones\npara el partido " + modelo.partidoSeleccionado.descripcion + "\nse ha modificado con Exito";
			Alert.show(msg);
			modelo.partidoSeleccionado.cobrador = cobrador;			
		}
		
		public function fault(info:Object):void {			
			trace(FaultEvent(info).fault.faultString,FaultEvent(info).fault.faultDetail);
			AlertError.show(FaultEvent(info).fault.faultString);
			ControlBlock.getInstance().remove();
			
		}
		
	}
}