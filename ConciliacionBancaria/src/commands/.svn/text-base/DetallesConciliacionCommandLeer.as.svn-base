package commands{
	import business.ConciliacionFondo;
	
	import com.adobe.cairngorm.business.ServiceLocator;
	import com.adobe.cairngorm.commands.ICommand;
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.tarjetafiel.caja.vo.util.Filtro;
	import com.util.block.ControlBlock;
	import com.util.components.alert.AlertError;
	
	import model.ConciliacionModelLocator;
	
	import mx.collections.ArrayCollection;
	import mx.rpc.AsyncToken;
	import mx.rpc.IResponder;
	import mx.rpc.events.FaultEvent;
	import mx.rpc.events.ResultEvent;
	import mx.rpc.remoting.RemoteObject;

	public class DetallesConciliacionCommandLeer implements IResponder, ICommand{
		
		private var modelo : ConciliacionModelLocator = ConciliacionModelLocator.getInstance();
		
		public function execute(event:CairngormEvent):void{
			ControlBlock.getInstance().add();
			modelo.cabeceraSeleccionada = event.data;
			var filtro:Filtro = new Filtro();
			filtro.agregarCampoOperValor("conciliacionFondoCabecera.idCabeceraConciliacion",Filtro.IGUAL,modelo.cabeceraSeleccionada.idCabeceraConciliacion);
			var ro:RemoteObject = ServiceLocator.getInstance().getRemoteObject("conciliacionFondoService");
			var pendingCall:AsyncToken = ro.getConciliacionFondosFlex(filtro);
			pendingCall.addResponder(this);
		}
		
		public function result(data:Object):void{
			var resultado : Array = ResultEvent(data).result as Array;
			if (resultado != null){
				modelo.cabeceraSeleccionada.conciliacionFondos = new ArrayCollection();
				for each (var c:ConciliacionFondo in resultado){
					modelo.cabeceraSeleccionada.conciliacionFondos.addItem(c); 
				}
			}
			ControlBlock.getInstance().remove();
		}
		
		public function fault(info:Object):void{
			trace(FaultEvent(info).fault.faultString,FaultEvent(info).fault.faultDetail);
			AlertError.show(FaultEvent(info).fault.faultString);
			ControlBlock.getInstance().remove();	
		}
		
	}
}