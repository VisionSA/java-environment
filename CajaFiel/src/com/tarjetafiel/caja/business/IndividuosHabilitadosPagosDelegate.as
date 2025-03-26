package com.tarjetafiel.caja.business
{
	import com.adobe.cairngorm.business.ServiceLocator;
	import com.tarjetafiel.caja.model.ModelLocator;
	
	import mx.rpc.AsyncToken;
	import mx.rpc.IResponder;
	import mx.rpc.remoting.RemoteObject;

	public class IndividuosHabilitadosPagosDelegate extends BaseDelegate
	{
		public function IndividuosHabilitadosPagosDelegate(responder:IResponder)
		{
			super(responder);
		}
		
		public function buscarHabilitadosEmpresa():void{
			var ro:RemoteObject = ServiceLocator.getInstance().getRemoteObject("individuoEvaluacionService");
			var ask:AsyncToken = ro.buscarIndividuosHabilitadosCuit(ModelLocator.getInstance().empresaModel.empresa);
			ask.addResponder(responder);
		}
		
	}
}