package commands {
	import com.adobe.cairngorm.commands.ICommand;
	import com.adobe.cairngorm.control.CairngormEvent;
	
	import model.CobranzasModelLocator;
	
	import mx.rpc.IResponder;

	public class SeleccionarListaAccionPanelCommand implements ICommand, IResponder {
		
		[Bindable]
		private var modelo : CobranzasModelLocator = CobranzasModelLocator.getInstance();
		
		public function execute(event:CairngormEvent):void {			
			
			modelo.accionesDG = event.data.accionesDG;
			modelo.etapaVersionSeleccionada = event.data.etapaVersion;
			
		}
		
		public function result(data:Object):void {
		}
		
		public function fault(info:Object):void {
		}
		
	}
}