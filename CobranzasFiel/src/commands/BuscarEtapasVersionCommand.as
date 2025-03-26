package commands {
	import com.adobe.cairngorm.business.ServiceLocator;
	import com.adobe.cairngorm.commands.ICommand;
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.tarjetafiel.caja.vo.EtapaVersion;
	import com.util.block.ControlBlock;
	import com.util.components.alert.AlertError;
	
	import model.CobranzasModelLocator;
	
	import mx.collections.ArrayCollection;
	import mx.rpc.AsyncToken;
	import mx.rpc.IResponder;
	import mx.rpc.events.FaultEvent;
	import mx.rpc.events.ResultEvent;
	import mx.rpc.remoting.RemoteObject;
	
	import views.components.ViewPanelEtapa;

	public class BuscarEtapasVersionCommand implements ICommand, IResponder {
		
		[Bindable]
		private var modelo : CobranzasModelLocator = CobranzasModelLocator.getInstance();
		
		
		public function execute(event:CairngormEvent):void {
			ControlBlock.getInstance().add();
			var ro:RemoteObject = ServiceLocator.getInstance().getRemoteObject("etapaVersionService");
			var pendingCall:AsyncToken = ro.getEtapasVersionByFiltro(event.data);
			pendingCall.addResponder(this);			
		}
		
		public function result(data:Object):void {
			var resultado : Array = ResultEvent(data).result as Array;
			if (resultado != null){
				modelo.listaEtapasVersion = new ArrayCollection(resultado);
			}	
						
			
			/* El siguiente for se realiza para agregar los paneles de etapas a la vista, no se utiliza repeater ya que
			 * produjo problemas a la hora de completar.
			 */			
			for each(var ev : EtapaVersion in modelo.listaEtapasVersion){
				var child : ViewPanelEtapa = new ViewPanelEtapa();
				child.etapaVersion = ev;
				ev.accionesVersion.sort(modelo.ordenar);
				child.accionesDG = new ArrayCollection(ev.accionesVersion);				
				modelo.hBoxPaneles_vv.addChild(child);					
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