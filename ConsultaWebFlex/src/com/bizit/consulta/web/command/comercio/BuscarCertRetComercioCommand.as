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
	
	public class BuscarCertRetComercioCommand implements ICommand, IResponder
	{
		
		[Bindable]
		private var modelo : FielWebModelLocator = FielWebModelLocator.getInstance();
		
		public function execute(event:CairngormEvent):void
		{
			var fechaDesde:Date = event.data as Date;
			var ro:RemoteObject = ServiceLocator.getInstance().getRemoteObject("comercioService");
			var pendingCall:AsyncToken = ro.listarRetencionesComercio(modelo.usuarioWeb.usuarioComercioWeb, fechaDesde);
			pendingCall.addResponder(this);
		}
		
		public function result(data:Object):void
		{
			modelo.listaRetComercio = ResultEvent(data).result as ArrayCollection;
			modelo.listaRetComercio.refresh();
			if (modelo.listaRetComercio.length > 0)
			{
				modelo.estadoBuscarCertRetComercio = ConstantesEstados.RESULTADOS_CERT_RET_COMERCIO_STATE;
				ManejadorPantallas.cerrarProgressBar();
			}
			else
			{
				ManejadorMensajes.mostrarMensajeInformacion("No se encontraron resultados que coincidan con el criterio de búsqueda");
				modelo.estadoBuscarCertRetComercio = ConstantesEstados.BUSCAR_CERT_RET_COMERCIO_STATE;
			}
		}
		
		public function fault(info:Object):void
		{
			ManejadorMensajes.mostrarMensajeErrorFaultEvent(FaultEvent(info), "Error consulta retenciones");
		}
	}
}