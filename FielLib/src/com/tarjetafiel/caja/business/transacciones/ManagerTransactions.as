package com.tarjetafiel.caja.business.transacciones
{
	import com.util.block.ControlBlock;
	import com.util.components.alert.AlertError;
	import com.util.error.ManagerErrors;
	
	import flash.events.Event;
	import flash.events.IOErrorEvent;
	import flash.events.ProgressEvent;
	import flash.events.SecurityErrorEvent;
	import flash.net.Socket;
	
	import mx.rpc.events.FaultEvent;
	import mx.rpc.events.ResultEvent;
	import mx.rpc.http.HTTPService;
	
	public class ManagerTransactions
	{		
		
		private var sock:Socket;		
		private var response:String;	
		private var responder:IResponder;
		private var xmlConfig:XML;			
		private var token:String;
		private var httpServices:HTTPService;
		
		public function ManagerTransactions(){
			sock = new Socket();			
			sock.addEventListener(Event.CONNECT,conectado);
			sock.addEventListener(Event.CLOSE,cerrado);
			sock.addEventListener(Event.ACTIVATE,activado);
			sock.addEventListener(IOErrorEvent.IO_ERROR, error);
			sock.addEventListener(ProgressEvent.SOCKET_DATA, socketData);
			sock.addEventListener(SecurityErrorEvent.SECURITY_ERROR, securityError);
			
					
		}
		
		public function callTransaccion(posnetParser:PosnetParser, res:IResponder, tipo:int=1):void{
					
			PosnetParser.tipoUltimaTransaccion = tipo;
			responder = res;
			token = posnetParser.formatToken(tipo);
			trace("Lenght Token: " + token.length);
			trace("Token: " + token);												
			ControlBlock.getInstance().add();
			if(this.xmlConfig == null){
				httpServices = new HTTPService();
				httpServices.url = "config/trans-config.xml";
				httpServices.method = "GET"
				httpServices.resultFormat = HTTPService.RESULT_FORMAT_XML;
				httpServices.addEventListener(ResultEvent.RESULT, result);
				httpServices.addEventListener(FaultEvent.FAULT, fault);
				httpServices.send();
			} else {
				this.initTransaction();
			}
															
		}
		
		
		
		public function callTransaccionMensaje(res:IResponder, mensaje:String):void{
					
			this.responder = res;
			ControlBlock.getInstance().add();										
			token = mensaje;
			if(this.xmlConfig == null){
				httpServices = new HTTPService();
				httpServices.url = "config/trans-config.xml";
				httpServices.method = "GET"
				httpServices.resultFormat = HTTPService.RESULT_FORMAT_XML;
				httpServices.addEventListener(ResultEvent.RESULT, result);
				httpServices.addEventListener(FaultEvent.FAULT, fault);
				httpServices.send();
			} else {
				this.initTransaction();
			}
															
		}
		
		private function result(evt:ResultEvent):void{
			xmlConfig = XML(evt.result);			
			this.initTransaction();						
		}
		
		private function fault(evt:FaultEvent):void {
			ControlBlock.getInstance().remove();
			ManagerErrors.getInstance().addPopUpError(evt.fault.faultString, evt.fault.faultDetail);
		}
		
		private function initTransaction():void{
			response = "";
			var host:String =xmlConfig.ip.toString();
			var port:String = xmlConfig.port.toString();
			sock.connect(host,int(port));
																				
		}
		
		
			private function securityError(evt:SecurityErrorEvent):void {
				
				trace("ERROR SEGURIDAD..... "+evt.text);
				ControlBlock.getInstance().remove();
			}
			
			private function conectado(evt:Event):void {
				trace("CONECTADO.....");
				sock.writeUTFBytes(token);
				sock.flush();				
			}
			
			private function error(evt:IOErrorEvent):void {
				trace("ERROR..... "+evt.text);				
				ControlBlock.getInstance().remove();
				AlertError.show(evt.text);
			}
			
			private function activado(evt:Event):void {
				trace("ACTIVADO.....");
			}
			
			private function socketData(evt:ProgressEvent):void {				
				response += sock.readUTFBytes ( sock.bytesAvailable );
				trace("El Server responde : "+ response)				
			}					
			
			private function cerrado(evt:Event):void {
				trace("CERRADO.....");								
				trace(response);
				
				responder.responderPosnetParser(response);
				
				
			}
			
			public function responderConsulta(respuesta:String):void{
				ControlBlock.getInstance().add();
				sock.writeUTFBytes(respuesta);
				sock.flush();
			}
			
	}
}