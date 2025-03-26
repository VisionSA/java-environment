package commands
{
	import com.adobe.cairngorm.business.ServiceLocator;
	import com.adobe.cairngorm.commands.ICommand;
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.util.block.ControlBlock;
	import com.util.components.alert.AlertError;
	
	import events.ConciliacionFondoCabeceraEvent;
	
	import model.ConciliacionModelLocator;
	import model.PaginacionSearchModelLocator;
	
	import mx.collections.ArrayCollection;
	import mx.rpc.AsyncToken;
	import mx.rpc.IResponder;
	import mx.rpc.events.FaultEvent;
	import mx.rpc.events.ResultEvent;
	import mx.rpc.remoting.RemoteObject;

	public class ConciliacionFondoCabeceraCommandConsultar implements ICommand, IResponder {
		
		private var modelo : ConciliacionModelLocator = ConciliacionModelLocator.getInstance();
		private var evento:CairngormEvent;
		
		public function execute(event:CairngormEvent):void {
			ControlBlock.getInstance().add();
			var ro:RemoteObject = ServiceLocator.getInstance().getRemoteObject("conciliacionFondoCabeceraService");
			this.evento = event;
			var cab:Object = event.data;
			var pendingCall:AsyncToken = null;
			if(event.type == ConciliacionFondoCabeceraEvent.TRAER_CONCILIADOS_FONDOS_CABECERA_EVENT)
			{
/*@I3918*/				 pendingCall = ro.buscarConciliacionCabeceraPorFecha(cab.tipoFecha, cab.fechaDesde, cab.fechaHasta, cab.idBancoPropio, cab.conciliado,cab.firstResult,cab.maxResults,cab.numeroOperacion, cab.importeTotalConciliado);
/*@F3918*/			}else if(event.type == ConciliacionFondoCabeceraEvent.TRAER_NO_CONCILIADOS_FONDOS_CABECERA_EVENT)
			{
				var evt:ConciliacionFondoCabeceraEvent = event as ConciliacionFondoCabeceraEvent;
				pendingCall = ro.buscarConciliacionCabeceraPaginado(evt.filtro,cab.firstResult,cab.maxResults);
			}else if(event.type == ConciliacionFondoCabeceraEvent.BUSCAR_CANTIDAD_PAGINAS_CONCILIACION)
			{
				var evt:ConciliacionFondoCabeceraEvent = event as ConciliacionFondoCabeceraEvent;
				pendingCall = ro.cantidadRegistros(evt.filtro);
			}else if(event.type == ConciliacionFondoCabeceraEvent.BUSCAR_CANTIDAD_PAGINAS_REVERSION)
			{
				var evt:ConciliacionFondoCabeceraEvent = event as ConciliacionFondoCabeceraEvent;
/*@I3918*/				pendingCall = ro.cantidadRegistrosPorFecha(cab.tipoFecha, cab.fechaDesde, cab.fechaHasta, cab.idBancoPropio, cab.conciliado,cab.numeroOperacion, cab.importeTotalConciliado);
/*@F3918*/			}
			
			pendingCall.addResponder(this);
		}
		
		public function result(data:Object):void {
			if(this.evento.type == ConciliacionFondoCabeceraEvent.TRAER_NO_CONCILIADOS_FONDOS_CABECERA_EVENT)
			{
				var resultado : Array = ResultEvent(data).result as Array;
				if (resultado != null){
					modelo.listaCabeceraConciliable = new ArrayCollection(resultado);				
				}
			}else if(this.evento.type == ConciliacionFondoCabeceraEvent.TRAER_CONCILIADOS_FONDOS_CABECERA_EVENT){
				var resultado : Array = ResultEvent(data).result as Array;
				if (resultado != null){
					modelo.listaCabeceraReversion = new ArrayCollection(resultado);				
				}
			}else if(this.evento.type == ConciliacionFondoCabeceraEvent.BUSCAR_CANTIDAD_PAGINAS_CONCILIACION){
				PaginacionSearchModelLocator.getInstanceCabecera().cantPaginasValor(ResultEvent(data).result as Number);
			
			}else if(this.evento.type == ConciliacionFondoCabeceraEvent.BUSCAR_CANTIDAD_PAGINAS_REVERSION){
				PaginacionSearchModelLocator.getInstanceReversion().cantPaginasValor((ResultEvent(data).result as Number) /2);
				PaginacionSearchModelLocator.getInstanceReporteConciliacion().cantPaginasValor((ResultEvent(data).result as Number) /2);
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