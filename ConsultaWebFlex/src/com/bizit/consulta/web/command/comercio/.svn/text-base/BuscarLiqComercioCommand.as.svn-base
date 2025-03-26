package com.bizit.consulta.web.command.comercio
{
	import com.adobe.cairngorm.business.ServiceLocator;
	import com.adobe.cairngorm.commands.ICommand;
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.bizit.consulta.web.model.FielWebModelLocator;
	import com.bizit.consulta.web.utils.ConstantesEstados;
	import com.bizit.consulta.web.utils.ManejadorMensajes;
	import com.bizit.consulta.web.utils.ManejadorPantallas;
	
	import mx.collections.ArrayCollection;
	import mx.rpc.AsyncToken;
	import mx.rpc.IResponder;
	import mx.rpc.events.FaultEvent;
	import mx.rpc.events.ResultEvent;
	import mx.rpc.remoting.RemoteObject;
	
	public class BuscarLiqComercioCommand implements ICommand, IResponder
	{
		
		[Bindable]
		private var modelo : FielWebModelLocator = FielWebModelLocator.getInstance();
		
		public function execute(event:CairngormEvent):void
		{
			var fechaDesde:Date = event.data as Date;
			var ro:RemoteObject = ServiceLocator.getInstance().getRemoteObject("comercioService");
			var pendingCall:AsyncToken = ro.listarLiqComercios(modelo.usuarioWeb.usuarioComercioWeb, fechaDesde);
			pendingCall.addResponder(this);
		}
		
		public function result(data:Object):void
		{
			modelo.listaLiqComercios =  ResultEvent(data).result as ArrayCollection;
			modelo.listaLiqComercios.refresh();
			if (modelo.listaLiqComercios.length > 0)
			{
				modelo.estadoBuscarLiqComercio = ConstantesEstados.RESULTADOS_LIQ_COMERCIO_STATE;
				ManejadorPantallas.cerrarProgressBar();
			}
			else
			{
				ManejadorMensajes.mostrarMensajeInformacion("No se encontraron resultados que coincidan con el criterio de búsqueda");
				modelo.estadoBuscarLiqComercio = ConstantesEstados.BUSCAR_LIQ_COMERCIO_STATE;
			}
		}
		
		public function fault(info:Object):void
		{
			ManejadorMensajes.mostrarMensajeErrorFaultEvent(FaultEvent(info), "Error en Consulta Liquidaciones");
		}
	}
}