package commands {
	import com.adobe.cairngorm.commands.ICommand;
	import com.adobe.cairngorm.control.CairngormEvent;
	
	import model.CobranzasModelLocator;
	
	import mx.rpc.IResponder;

	public class InicializarEditPlanModuloCommand implements ICommand, IResponder {
		
		
		private var modelo : CobranzasModelLocator = CobranzasModelLocator.getInstance();
		
		public function execute(event:CairngormEvent):void {
			modelo.listaVersiones = null;
			modelo.versionSeleccionada = null;
			modelo.planSeleccionado = null;			
		}
		
		public function result(data:Object):void {
		}
		
		public function fault(info:Object):void	{
		}
		
	}
}