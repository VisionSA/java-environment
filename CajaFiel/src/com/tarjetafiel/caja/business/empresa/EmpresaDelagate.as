package com.tarjetafiel.caja.business.empresa
{
	import com.adobe.cairngorm.business.ServiceLocator;
	import com.tarjetafiel.caja.business.BaseDelegate;
	import com.tarjetafiel.caja.model.ModelLocator;
	import com.tarjetafiel.caja.vo.util.Filtro;
	import com.util.paginacion.Paginador;
	
	import mx.rpc.AsyncToken;
	import mx.rpc.IResponder;
	import mx.rpc.remoting.RemoteObject;

	public class EmpresaDelagate extends BaseDelegate
	{
		public function EmpresaDelagate(responder:IResponder)
		{
			super(responder);
		}
		
		public function buscar(filtro:Filtro=null,paginador:Paginador=null):void{
			var ro:RemoteObject = ServiceLocator.getInstance().getRemoteObject("empresa");
			var pendingCall:AsyncToken;
			if(paginador != null){
				pendingCall = ro.getEmpresaFlex(filtro,paginador);
			} else {
				var idEmpresa:Number = ModelLocator.getInstance().empresaModel.empresa.idEmpresa;
				pendingCall = ro.leerEmpresaFlex(idEmpresa);
			}
			pendingCall.addResponder(responder);
		}
		
	}
}