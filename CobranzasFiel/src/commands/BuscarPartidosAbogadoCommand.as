package commands
{
	import com.adobe.cairngorm.business.ServiceLocator;
	import com.adobe.cairngorm.commands.ICommand;
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.tarjetafiel.caja.vo.Abogado;
	import com.tarjetafiel.caja.vo.Partido;
	import com.util.block.ControlBlock;
	import com.util.components.alert.AlertError;
	
	import model.CobranzasModelLocator;
	
	import mx.collections.ArrayCollection;
	import mx.rpc.AsyncToken;
	import mx.rpc.IResponder;
	import mx.rpc.events.FaultEvent;
	import mx.rpc.events.ResultEvent;
	import mx.rpc.remoting.RemoteObject;

	public class BuscarPartidosAbogadoCommand implements ICommand, IResponder {
		
		[Bindable]
		private var modelo : CobranzasModelLocator = CobranzasModelLocator.getInstance();
		
		public function execute(event:CairngormEvent):void {
			
			ControlBlock.getInstance().add();
			var ro:RemoteObject = ServiceLocator.getInstance().getRemoteObject("partidoService");
			var pendingCall:AsyncToken = ro.listarPartidosXProvincia(event.data);
			pendingCall.addResponder(this);
			
		}
		
		public function result(data:Object):void {
			var resultado : Array = ResultEvent(data).result as Array;
			
			var lista : Array = new Array();
			for each (var p : Partido in resultado){
				var partido : Partido = new Partido();
				partido.abogado = this.buscarAbogado(modelo.listaAbogados,p.idAbogado);
				partido.idPartido = p.idPartido;
				partido.idAbogado = p.idAbogado;
				partido.descripcion = p.descripcion;
				partido.provincia = p.provincia;
				lista.push(partido);
			}
			
			modelo.listaPartidos = new ArrayCollection(lista);
			
			ControlBlock.getInstance().remove();
		}
		
		public function fault(info:Object):void {
			trace(FaultEvent(info).fault.faultString,FaultEvent(info).fault.faultDetail);
			AlertError.show(FaultEvent(info).fault.faultString);
			ControlBlock.getInstance().remove();
		}
		
		private function buscarAbogado(lista : ArrayCollection, idAbogado : Number):Abogado{
			
			var resultado : Abogado = null;
			
			for each (var a : Abogado in lista){
				if (a.idColaborador == idAbogado){
					resultado = new Abogado();
					resultado.nombres = a.nombres;
					resultado.apellido = a.apellido;
					resultado.idColaborador = idAbogado;
					break;
				}
			}
			
			return resultado;
		}
		
	}
}