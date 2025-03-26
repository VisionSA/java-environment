package commands {
	import com.adobe.cairngorm.commands.ICommand;
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.tarjetafiel.caja.vo.Cobrador;
	import com.util.block.ControlBlock;
	import com.util.components.alert.AlertError;
	
	import events.GenericTareasPendientesEvent;
	
	import model.CobranzasModelLocator;
	
	import mx.formatters.DateFormatter;
	import mx.rpc.AsyncToken;
	import mx.rpc.IResponder;
	import mx.rpc.events.FaultEvent;
	import mx.rpc.http.HTTPService;

	public class GenerarCobrosCobradoresCommand implements ICommand, IResponder {
		
		private var fechaDesde : Date = null;
		private var fechaHasta : Date = null;
		
		public function execute(event:CairngormEvent):void {
			
			var idCobrador : int = 0;
			
			fechaDesde = event.data.fechaDesde;
			fechaHasta = event.data.fechaHasta;
			
			if (event.data.cobrador != null){
		    	idCobrador = (event.data.cobrador as Cobrador).idCobrador; 
		    }
			
			
			ControlBlock.getInstance().add();
			var httpService:HTTPService = new HTTPService();
		    httpService.url = CobranzasModelLocator.URL_SERVLET;
		    httpService.method="POST";
		    
		    
		    var token:AsyncToken=httpService.send({peticion:3, 
		    										diaDesde:fechaDesde.date, 
		    										mesDesde:fechaDesde.month,
		    										anioDesde:fechaDesde.fullYear,
		    										diaHasta:fechaHasta.date, 
		    										mesHasta:fechaHasta.month,
		    										anioHasta:fechaHasta.fullYear,
		    										idCobrador:idCobrador});
		    token.addResponder(this);		
		}
		
		public function result(data:Object):void {
			var df : DateFormatter = new DateFormatter();
			df.formatString="DD-MM-YYYY";
			var url : String;
			url = "/archivos/cobrosDeCobradores/"+df.format(fechaHasta)+"/PLANILLA_COBROS_COBRADORES.pdf";
			new GenericTareasPendientesEvent(GenericTareasPendientesEvent.ABRIR_PDF_EVENT,url).dispatch();
			ControlBlock.getInstance().remove();
			
		}
		
		public function fault(info:Object):void {
			trace(FaultEvent(info).fault.faultString,FaultEvent(info).fault.faultDetail);
			AlertError.show(FaultEvent(info).fault.faultString);
			ControlBlock.getInstance().remove();
		}
		
	}
}