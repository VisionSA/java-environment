package com.tarjetafiel.caja.business
{
	import com.adobe.cairngorm.business.ServiceLocator;
	import com.tarjetafiel.caja.model.ModelLocator;
	import com.tarjetafiel.caja.vo.util.Filtro;
	
	import mx.rpc.AsyncToken;
	import mx.rpc.IResponder;
	import mx.rpc.remoting.RemoteObject;

	public class FormaPagoCajaDelegate extends BaseDelegate
	{
		public function FormaPagoCajaDelegate(responder:IResponder)
		{
			super(responder);
		}
		
		public function buscarFormaPagoCaja():void{
			var ro:RemoteObject = ServiceLocator.getInstance().getRemoteObject("formaPagoCaja");
			var filtro:Filtro = new Filtro();
			filtro.campos.push("caja.idCaja");
			filtro.operadores.push(Filtro.IGUAL);
			filtro.valores.push(ModelLocator.getInstance().cajaModel.caja.idCaja);
			filtro.campos.push("habilitado");
			filtro.operadores.push(Filtro.IGUAL);
			filtro.valores.push("'S'");
			var ask:AsyncToken = ro.listarFormaPagoCajaFlex(filtro);
			ask.addResponder(responder);
		}
		
	}
}