package commands {
	import com.adobe.cairngorm.commands.ICommand;
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.tarjetafiel.caja.vo.AccionVersion;
	
	import model.CobranzasModelLocator;
	
	import mx.controls.Alert;
	import mx.rpc.IResponder;
	import mx.utils.ObjectUtil;

	public class SeleccionarAccionDisponibleCommand implements ICommand, IResponder {
		
		
		[Bindable]
		private var modelo : CobranzasModelLocator = CobranzasModelLocator.getInstance();
		
		public function execute(event:CairngormEvent):void {
			
			modelo.accionAAgregarSeleccionada = new AccionVersion();
			modelo.accionAAgregarSeleccionada.accion = event.data;
			modelo.accionAAgregarSeleccionada.dias = 0;
					
			
		}
		
		public function result(data:Object):void {
		}
		
		public function fault(info:Object):void	{
		}
		
	}
}