package models
{
	import com.tarjetafiel.caja.vo.Operador;
	import com.tarjetafiel.caja.vo.Rol;
	import com.util.block.ControlBlock;
	import com.util.components.alert.AlertError;
	
	import events.ApplicationEvent;
	import events.OperadorEvent;
	
	import flash.events.IEventDispatcher;
	
	import mx.core.Application;
	import mx.rpc.Fault;
	import mx.rpc.events.FaultEvent;
	import mx.rpc.events.ResultEvent;
	import mx.rpc.http.HTTPService;
	
	
	[Bindable]
	public class ApplicationModel
	{
		
		public var dispatcher:IEventDispatcher;
		
		public var motivo:String = "";
		
		private var httpConfig:HTTPService = new HTTPService();
		
		public var operador:Operador; 
		
		
		public function ApplicationModel()
		{
		}
		
		public function faultConsultaOperador(fault:Fault):void{				
			motivo = fault.faultString;		
			var evt:ApplicationEvent = new ApplicationEvent(ApplicationEvent.CAMBIAR_VISTA);
			evt.index = 1;
			dispatcher.dispatchEvent(evt);
		}
		
		public function buscarConfiguracionModo():void{
				httpConfig.useProxy = false;
				httpConfig.resultFormat = HTTPService.RESULT_FORMAT_XML;
				var url:String = Application.application.url;
				
				url = url.replace(".html","");
				url = url.replace(".swf","");
				url = url.split("?")[0];				
				var nameApp:String = Application.application.name.substr(0,Application.application.name.length-1);

				url = url.replace(nameApp,"");
											
				httpConfig.url = url + "config/caja-config.xml";	
				httpConfig.addEventListener(FaultEvent.FAULT,faultHttpConfig);
				httpConfig.addEventListener(ResultEvent.RESULT,resultHttpConfig);
				httpConfig.send();
		}
		
		private function faultHttpConfig(evt:FaultEvent):void{
				AlertError.show(evt.fault.faultDetail);
				httpConfig.removeEventListener(FaultEvent.FAULT,faultHttpConfig);
				httpConfig.removeEventListener(ResultEvent.RESULT,resultHttpConfig);
			}
			
		private function resultHttpConfig(event:ResultEvent):void{
								
				httpConfig.removeEventListener(FaultEvent.FAULT,faultHttpConfig);
				httpConfig.removeEventListener(ResultEvent.RESULT,resultHttpConfig);
				
				
				var modo:String = new XML(event.result).modo.toString();
								
				if(modo != "desarrollo"){
					if(Application.application.parameters.codigoOperador != null){					
						ControlBlock.getInstance().add();
						var evt:OperadorEvent = new OperadorEvent(OperadorEvent.BUSCAR_OPERADOR_LOGUEADO);
						evt.codigoOperador = Number(Application.application.parameters.codigoOperador);
						dispatcher.dispatchEvent(evt);						
					} else {						
						var even:ApplicationEvent = new ApplicationEvent(ApplicationEvent.CAMBIAR_VISTA);
						even.index = 1;
						dispatcher.dispatchEvent(even);
						motivo = "No hay un operador logueado";							
					}	
				} else {
					operador = new Operador();
					operador.codigo = 1212;
					operador.nombre = "";
					operador.apellido = "";
					operador.rol = new Rol();
					operador.rol.idRol = 2;
					dispatcher.dispatchEvent(new OperadorEvent(OperadorEvent.OPERADOR_LOGUEADO));
				}	
				
							
			}
			
		public function resultOperadorLogueado(result:Operador):void{
			operador = result;
			ControlBlock.getInstance().remove();
			dispatcher.dispatchEvent(new OperadorEvent(OperadorEvent.OPERADOR_LOGUEADO));
			
		}

	}
}