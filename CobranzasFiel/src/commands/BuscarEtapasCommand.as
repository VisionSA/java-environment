package commands {
	import com.adobe.cairngorm.business.ServiceLocator;
	import com.adobe.cairngorm.commands.ICommand;
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.tarjetafiel.caja.vo.Etapa;
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

	public class BuscarEtapasCommand implements ICommand, IResponder {
		
		[Bindable]
		private var modelo : CobranzasModelLocator = CobranzasModelLocator.getInstance();
				
		public function execute(event:CairngormEvent):void {						
			ControlBlock.getInstance().add();
			var ro:RemoteObject = ServiceLocator.getInstance().getRemoteObject("etapaService");
			var pendingCall:AsyncToken = ro.listarEtapas();
			pendingCall.addResponder(this)
		}
		
		public function result(data:Object):void {
			var resultado : Array = ResultEvent(data).result as Array;
			if (resultado != null){
				modelo.listaEtapas = new ArrayCollection(resultado);
			}
			
			
			/* El siguiente for se realiza para agregar los paneles de etapas a la vista, no se utiliza repeater ya que
			 * produjo problemas a la hora de completar.
			 */
			 
			modelo.versionSeleccionada.etapasVersion = new Array();
			
			for each(var e : Etapa in modelo.listaEtapas){
				
				var ev : EtapaVersion = new EtapaVersion();
				ev.etapa = e;
				ev.planVersion = modelo.versionSeleccionada;
				ev.accionesVersion = new Array();
				modelo.versionSeleccionada.etapasVersion.push(ev);
				
				
				var child : ViewPanelEtapa = new ViewPanelEtapa();
				child.etapaVersion = ev;							
				modelo.hBoxPaneles_nv.addChild(child);					
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