package commands {
	import com.adobe.cairngorm.business.ServiceLocator;
	import com.adobe.cairngorm.commands.ICommand;
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.tarjetafiel.caja.vo.Abogado;
	import com.tarjetafiel.caja.vo.Partido;
	import com.util.block.ControlBlock;
	import com.util.components.alert.AlertError;
	
	import model.CobranzasModelLocator;
	
	import mx.controls.Alert;
	import mx.rpc.AsyncToken;
	import mx.rpc.IResponder;
	import mx.rpc.events.FaultEvent;
	import mx.rpc.remoting.RemoteObject;

	public class CambiarAbogadoAFuturoCommand implements ICommand, IResponder {
		
		[Bindable]
		private var modelo : CobranzasModelLocator = CobranzasModelLocator.getInstance();
		
		private var abogado : Abogado = null;
		
		public function execute(event:CairngormEvent):void {
			
			abogado = event.data as Abogado;
			
			var partido : Partido = new Partido();
			partido.idPartido = modelo.partidoSeleccionado.idPartido;
			partido.idAbogado = abogado.idColaborador; 
			
			ControlBlock.getInstance().add();
			var ro:RemoteObject = ServiceLocator.getInstance().getRemoteObject("partidoService");
			var pendingCall:AsyncToken = ro.asignarAbogadoAPartido(partido);
			pendingCall.addResponder(this);
			
			
		}
		
		public function result(data:Object):void {data
			ControlBlock.getInstance().remove();
			var msg : String = "El cobrador para futuras asignaciones\npara el partido " + modelo.partidoSeleccionado.descripcion + "\nse ha modificado con Exito";
			Alert.show(msg);
			modelo.partidoSeleccionado.abogado = abogado;			
		
		}
		
		public function fault(info:Object):void{
			trace(FaultEvent(info).fault.faultString,FaultEvent(info).fault.faultDetail);
			AlertError.show(FaultEvent(info).fault.faultString);
			ControlBlock.getInstance().remove();			
		}
		
	}
}