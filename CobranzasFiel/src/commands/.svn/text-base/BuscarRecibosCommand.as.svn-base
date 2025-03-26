package commands
{
	import com.adobe.cairngorm.business.ServiceLocator;
	import com.adobe.cairngorm.commands.ICommand;
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.tarjetafiel.caja.vo.Cobrador;
	import com.util.block.ControlBlock;
	import com.util.components.alert.AlertError;
	
	import model.CobranzasModelLocator;
	
	import mx.collections.ArrayCollection;
	import mx.controls.Alert;
	import mx.rpc.AsyncToken;
	import mx.rpc.IResponder;
	import mx.rpc.events.FaultEvent;
	import mx.rpc.events.ResultEvent;
	import mx.rpc.remoting.RemoteObject;
	import mx.utils.ObjectUtil;
	import mx.utils.StringUtil;

	public class BuscarRecibosCommand implements ICommand, IResponder {
		
		[Bindable]
		private var modelo : CobranzasModelLocator = CobranzasModelLocator.getInstance();
		
		private var cobrador : Cobrador;
		
		public function execute(event:CairngormEvent):void {
			ControlBlock.getInstance().add();
			
			cobrador = (event.data as Cobrador);			
			
			var ro:RemoteObject = ServiceLocator.getInstance().getRemoteObject("reciboService");
			var pendingCall:AsyncToken = ro.getRangosReciboByIDCobrador(cobrador.idCobrador);
			pendingCall.addResponder(this);	
		}
		
		public function result(data:Object):void {
			var resultado : Array = ResultEvent(data).result as Array;
			
			modelo.rangoReciboSeleccionado = null;
			modelo.listaDetalleRecibos = null;
			modelo.detalleReciboSeleccionado = null;
			
			
			// Seteo el cobrador seleccionado
			modelo.cobrador = cobrador;
			
			if (resultado != null){
				modelo.listaRecibos = new ArrayCollection(resultado);
				modelo.tituloRango = StringUtil.substitute("Rango de recibos para {0}, {1}",cobrador.nombre,cobrador.apellido);
			}
			ControlBlock.getInstance().remove();
		}
		
		public function fault(info:Object):void {
			trace(FaultEvent(info).fault.faultString,FaultEvent(info).fault.faultDetail);
			AlertError.show(FaultEvent(info).fault.faultString);
			ControlBlock.getInstance().remove();
		}
		
	}
}