package com.bizit.consulta.web.command.comercio
{
	import com.adobe.cairngorm.business.ServiceLocator;
	import com.adobe.cairngorm.commands.ICommand;
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.bizit.consulta.web.model.FielWebModelLocator;
	import com.bizit.consulta.web.utils.ConstantesEstados;
	import com.bizit.consulta.web.utils.ManejadorMensajes;
	
	import flash.utils.ByteArray;
	
	import mx.rpc.AsyncToken;
	import mx.rpc.IResponder;
	import mx.rpc.events.FaultEvent;
	import mx.rpc.events.ResultEvent;
	import mx.rpc.remoting.RemoteObject;
	
	public class GuardarArchivoTxtCommand implements IResponder, ICommand
	{
		
		[Bindable]
		private var modelo : FielWebModelLocator = FielWebModelLocator.getInstance();
		
		public function execute(event:CairngormEvent):void
		{
			var liq:int = int(event.data);
			var ro:RemoteObject = ServiceLocator.getInstance().getRemoteObject("comercioService");
			var pendingCall:AsyncToken = ro.getArchivoLiquidacion(liq, modelo.webServiceUrl);
			pendingCall.addResponder(this);
		}
		
		public function result(data:Object):void
		{
			modelo.byteArrayTxt = ResultEvent(data).result as ByteArray;
			modelo.estadoBuscarLiqComercio = ConstantesEstados.GUARDAR_TXT_STATE;
			modelo.estadoBuscarLiqComercio = ConstantesEstados.RESULTADOS_LIQ_COMERCIO_STATE;
		}
		
		public function fault(info:Object):void
		{
			ManejadorMensajes.mostrarMensajeErrorFaultEvent(FaultEvent(info), "Error al cargar archivo txt");
		}
	}
}