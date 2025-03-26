package commands {
	import com.adobe.cairngorm.commands.ICommand;
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.tarjetafiel.caja.vo.AccionVersion;
	
	import model.CobranzasModelLocator;
	
	import mx.rpc.IResponder;

	public class ModificarAccionCommand implements ICommand, IResponder {
		
		[Bindable]
		private var modelo : CobranzasModelLocator = CobranzasModelLocator.getInstance();
		
		public function execute(event:CairngormEvent):void {
			modelo.accionAAgregarSeleccionada.dias = (event.data as AccionVersion).dias;
			modelo.accionAAgregarSeleccionada.conceptoCabecera = (event.data as AccionVersion).conceptoCabecera;
			
			modelo.etapaVersionSeleccionada.accionesVersion.sort(modelo.ordenar);
			modelo.accionesDG.source = modelo.etapaVersionSeleccionada.accionesVersion;
			
		}
		
		public function result(data:Object):void {
		}
		
		public function fault(info:Object):void {
		}
		
	}
}