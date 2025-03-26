package commands {
	import com.adobe.cairngorm.commands.ICommand;
	import com.adobe.cairngorm.control.CairngormEvent;
	
	import model.CobranzasModelLocator;
	
	import mx.controls.Alert;
	import mx.rpc.IResponder;

	public class SeleccionarDetalleReciboCommand implements ICommand, IResponder {
		
		[Bindable]
		private var modelo : CobranzasModelLocator = CobranzasModelLocator.getInstance();
		
		public function execute(event:CairngormEvent):void {
			
			modelo.detalleReciboSeleccionado = event.data;
			modelo.esReciboAnulable = modelo.detalleReciboSeleccionado.ctaCteCliente.toString()=="NaN"&&modelo.detalleReciboSeleccionado.esEstadoAnulado=='N'; 
			
		}
		
		public function result(data:Object):void {
		}
		
		public function fault(info:Object):void {
		}
		
	}
}