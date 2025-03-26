package commands {
	import com.adobe.cairngorm.commands.ICommand;
	import com.adobe.cairngorm.control.CairngormEvent;
	
	import model.CobranzasModelLocator;
	
	import mx.rpc.IResponder;

	public class InicializarModuloPlanCommand implements ICommand, IResponder {

		private var modelo : CobranzasModelLocator = CobranzasModelLocator.getInstance();		
		
		public function execute(event:CairngormEvent):void {		
			
			modelo.listaPlanes  = null;
			modelo.planSeleccionado = null;
			modelo.listaVersiones = null;
			modelo.versionSeleccionada = null
			modelo.listaEtapasVersion = null;			
			modelo.esNuevaVersion = false;
			modelo.accionAAgregarSeleccionada = null;
			modelo.listaConceptosCabecera = null;
			modelo.etapaVersionSeleccionada = null;
		
			modelo.hBoxPaneles_vv = null;
			modelo.hBoxPaneles_nv = null;
			modelo.accionesDG = null;
			
			
		}
		
		public function result(data:Object):void {
		}
		
		public function fault(info:Object):void	{
		}
		
	}
}