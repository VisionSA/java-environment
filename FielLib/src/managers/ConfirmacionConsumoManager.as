package managers
{
	import com.tarjetafiel.caja.business.transacciones.ManagerTransactions;
	import com.tarjetafiel.caja.business.transacciones.PosnetParser;
	import com.util.block.ControlBlock;
	import com.util.components.alert.AlertError;
	
	import flash.events.Event;
	import flash.events.IEventDispatcher;
	
	import mx.controls.DateField;
	import mx.rpc.AsyncToken;
	import mx.rpc.Responder;
	import mx.rpc.events.FaultEvent;
	import mx.rpc.events.ResultEvent;
	import mx.rpc.http.HTTPService;
	import mx.rpc.remoting.RemoteObject;
	import mx.utils.StringUtil;
	
	[Bindable]
	public class ConfirmacionConsumoManager 
	{
		public var dispatcher:IEventDispatcher;
		
		public var managerTransaction:ManagerTransactions;
		
		public var posnetParser:PosnetParser;
		
		public var ro:RemoteObject = new RemoteObject();
		
		public var mensaje:String;
		
		public var codigo:String;
		
		public var color:String;
		
       	public var respuestaTransaccionador:String;
		
		
		public function ConfirmacionConsumoManager()
		{
			ro.destination = "GenericDestination";
			ro.source = "com.bizitglobal.tarjetafiel.transacciones.service.impl.AutorizacionTelefonicaService";
			
		}
		
		public function confirmarConsumo(monto:String,codComercio:String,nroTarjeta:String,cuota:String, nroCupon:String,estadoConc:String):void{
			posnetParser.tipoReg = PosnetParser.CONSUMO;
			if(monto.indexOf(".")==-1)
			     monto+="00";
			else  {
				var importe:String = monto.split(".")[0];
				var centavos:String = monto.split(".")[1];
				
				centavos = centavos.length == 1 ? centavos + "0" : centavos;
				
				monto = importe + centavos;
			}    
	     	posnetParser.importe = monto;  
	     	posnetParser.comercio= codComercio;
	     	posnetParser.tarjeta = nroTarjeta;
	     	posnetParser.cuotas =cuota;
	     	posnetParser.cupon = nroCupon;
	     	var fecha:String = DateField.dateToString(new Date(), "DD/MM/YY");
	     	fecha=fecha.substring(0,2)+ fecha.substring(3,5)+fecha.substring(6,8);
	        posnetParser.fecha=fecha; 
	     	posnetParser.conciliarTransaccion=  estadoConc; //si estado conc="C" es porq el comercio no presenta cupones=> concilia directamente 
	     	//ver el campo plan cuotas!!!!!!!
	     	posnetParser.planCuotas = "00";
	     	posnetParser.origen= "4"; 
	        //managerTransaction.callTransaccion(posnetParser,this,3);
	        this.enviarTransaccion();
		}
		
		private var responder:Responder = new Responder(responderPosnetParser,fault);
		
		public function fault( fault:Object ):void{
			AlertError.show(FaultEvent(fault).fault.faultDetail);	
			ControlBlock.getInstance().remove();				
		}
		
		public function responderPosnetParser(result:Object):void{		   
		   ControlBlock.getInstance().remove();
		   var respuesta : Object = ResultEvent(result).result;		  
		   var codigoRespuesta:String = respuesta["codigoRespuesta"];
		   var codigoAutorizacion:String = respuesta["codigoAutorizacion"];
		   var mensaje:String = respuesta["mensaje"];
		   //mensaje = respuesta.mensaje;
		   this.mensaje = mensaje;
		   if(codigoRespuesta == "00" ){
		   		codigo = StringUtil.trim(codigoAutorizacion);
		   		color = "#001D9C";
		   } else {		   		
		   		codigo = "-";
		   		color = "#FF0202";
		   }
		   
		   dispatcher.dispatchEvent(new Event("verCodigoAutorizacion"));
		   dispatcher.dispatchEvent(new Event("inicializar"));		   
		     
		   //Alert.show("Respuesta: "+ respuesta.mensajeRespuesta+ "\nCodigo de autorizacion: "+ respuesta.codigoAutorizacion);
		}
		
		private function enviarTransaccion():void{
			httpServices = new HTTPService();
			httpServices.url = "config/trans-config.xml";
			httpServices.method = "GET"
			httpServices.resultFormat = HTTPService.RESULT_FORMAT_XML;
			httpServices.addEventListener(ResultEvent.RESULT, result);
			httpServices.addEventListener(FaultEvent.FAULT, fault);
			httpServices.send();			
		}
		
		private var httpServices:HTTPService;
		private var xmlConfig:XML;
		
		private var host:String;
		private var port:String;
		
		private function result(evt:ResultEvent):void{
			xmlConfig = XML(evt.result);
			host = xmlConfig.ip.toString();
			port = xmlConfig.port.toString();
			var ask:AsyncToken = ro.enviarMensajeTransaccionadoAutTelefonica(posnetParser.formatToken(PosnetParser.CONSUMOS),host,int(port));
	        ask.addResponder(responder);
	        ControlBlock.getInstance().add();
		}
		
		
		public function responderErrorPosnetParser(result:Array):void{
		    
		}
		
		
		
		/*public function responderConsultaPosnetParser(result:Array):void{}
		public function responderOkPosnetParser(result:Array):void{}*/

	}
}