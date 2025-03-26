package commands {
	import com.adobe.cairngorm.business.ServiceLocator;
	import com.adobe.cairngorm.commands.ICommand;
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.tarjetafiel.caja.vo.ConceptoCabecera;
	import com.util.components.alert.AlertError;
	
	import model.CobranzasModelLocator;
	
	import mx.collections.ArrayCollection;
	import mx.rpc.AsyncToken;
	import mx.rpc.IResponder;
	import mx.rpc.events.FaultEvent;
	import mx.rpc.events.ResultEvent;
	import mx.rpc.remoting.RemoteObject;

	public class BuscarConceptosCabecerasCommand implements ICommand, IResponder {
		
		[Bindable]
		private var modelo : CobranzasModelLocator = CobranzasModelLocator.getInstance();
		
		public function execute(event:CairngormEvent):void {
			var ro:RemoteObject = ServiceLocator.getInstance().getRemoteObject("conceptoService");
			var pendingCall:AsyncToken = ro.getCabecerasConcepto();
			pendingCall.addResponder(this);
		}
		
		public function result(data:Object):void {
			var resultado : Array = ResultEvent(data).result as Array;
			
			var conceptoNulo : ConceptoCabecera = new ConceptoCabecera();
			conceptoNulo.descripcion = "NO APLICA";
			conceptoNulo.idConcepto = Number(-1);
			var lista : Array = new Array();
			lista.push(conceptoNulo);			
			if (resultado != null){
				modelo.listaConceptosCabecera = new ArrayCollection(lista.concat(resultado));
			}
			
		}
		
		public function fault(info:Object):void {			
			trace(FaultEvent(info).fault.faultString,FaultEvent(info).fault.faultDetail);
			AlertError.show(FaultEvent(info).fault.faultString);
		}
		
	}
}