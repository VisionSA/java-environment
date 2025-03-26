package commands {
	import com.adobe.cairngorm.business.ServiceLocator;
	import com.adobe.cairngorm.commands.ICommand;
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.tarjetafiel.caja.vo.Recibo;
	import com.util.block.ControlBlock;
	import com.util.components.alert.AlertError;
	
	import model.CobranzasModelLocator;
	
	import mx.controls.Alert;
	import mx.rpc.AsyncToken;
	import mx.rpc.IResponder;
	import mx.rpc.events.FaultEvent;
	import mx.rpc.remoting.RemoteObject;
	import mx.utils.StringUtil;

	public class AddNuevoRangoReciboCommand implements ICommand, IResponder {
		
		[Bindable]
		private var modelo : CobranzasModelLocator = CobranzasModelLocator.getInstance();
		
		private var reciboIngresado : Recibo = null;
		
		public function execute(event:CairngormEvent):void {
			ControlBlock.getInstance().add();
			
			reciboIngresado = event.data;
			
			var ro:RemoteObject = ServiceLocator.getInstance().getRemoteObject("reciboService");
			var pendingCall:AsyncToken = ro.insertarRangoRecibosByParam(reciboIngresado);
			pendingCall.addResponder(this);	
			
		}
		
		public function result(data:Object):void {
			
			Alert.show(StringUtil.substitute("El rango de recibos desde {0} hasta {1}\n" + 
					"para el cobrador {2}, {3}\n" + 
					"fue ingresado con éxito",
					reciboIngresado.desde, 
					reciboIngresado.hasta,
					reciboIngresado.cobrador.apellido,
					reciboIngresado.cobrador.nombre));
					
			modelo.listaRecibos.addItem(reciboIngresado);
			ControlBlock.getInstance().remove();
		}
		
		public function fault(info:Object):void {
			trace(FaultEvent(info).fault.faultString,FaultEvent(info).fault.faultDetail);
			AlertError.show(FaultEvent(info).fault.faultString);
			ControlBlock.getInstance().remove();
		}
		
	}
}