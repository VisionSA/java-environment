package com.tarjetafiel.proveedorconexion.delegate
{
	import com.adobe.cairngorm.business.ServiceLocator;
	import com.tarjetafiel.caja.business.BaseDelegate;
	import com.tarjetafiel.caja.vo.Abogado;
	import com.tarjetafiel.caja.vo.CajaApertura;
	import com.tarjetafiel.caja.vo.MovimientoClientesDTO;
	import com.tarjetafiel.caja.vo.Operador;
	import com.tarjetafiel.caja.vo.SumadorMediosPago;
	
	import mx.collections.ArrayCollection;
	import mx.rpc.AsyncToken;
	import mx.rpc.IResponder;
	import mx.rpc.remoting.RemoteObject;
	
	public class PagoDelegate extends BaseDelegate
	{
		public function PagoDelegate(responder:IResponder)
		{
			super(responder);
		}
		
		public function efectuarPagoJudicial(cliMovs:ArrayCollection,totales:SumadorMediosPago,abogado:Abogado):void{
			var ro:RemoteObject = ServiceLocator.getInstance().getRemoteObject("pagosCliente");
			var ask:AsyncToken = ro.registrarPagoJudicialCliente(cliMovs.source,totales,abogado);
			ask.addResponder(responder);
		}
		
		
		public function registrarPagoJudicial(movClis:MovimientoClientesDTO,totales:SumadorMediosPago,abogado:Abogado):void{
			var ro:RemoteObject = ServiceLocator.getInstance().getRemoteObject("pagosCliente");
			var ask:AsyncToken = ro.registrarPagoJudicialCliente(movClis,totales,abogado);
			ask.addResponder(responder);
		}
		
	}
}