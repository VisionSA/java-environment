package commands {
	import com.adobe.cairngorm.commands.ICommand;
	import com.adobe.cairngorm.control.CairngormEvent;
	
	import model.CobranzasModelLocator;
	
	import mx.controls.Alert;
	import mx.rpc.IResponder;
	import mx.utils.ObjectUtil;

	public class SeleccionarDetalleTareaCommand implements ICommand, IResponder	{
		
		[Bindable]
		private var modelo : CobranzasModelLocator = CobranzasModelLocator.getInstance();
		
		public function execute(event:CairngormEvent):void {
			
			modelo.detalleTareaSeleccionada = event.data;
						
		}
		
		public function result(data:Object):void {
		}
		
		public function fault(info:Object):void	{
		}
		
	}
}