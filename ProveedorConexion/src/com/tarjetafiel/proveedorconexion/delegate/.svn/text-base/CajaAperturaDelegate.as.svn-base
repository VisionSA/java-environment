package com.tarjetafiel.proveedorconexion.delegate {
	import com.adobe.cairngorm.business.ServiceLocator;
	import com.tarjetafiel.caja.business.BaseDelegate;
	import com.tarjetafiel.caja.vo.CajaApertura;
	import com.tarjetafiel.caja.vo.CajaMP;
	import com.tarjetafiel.caja.vo.Cheque;
	import com.tarjetafiel.caja.vo.Impresora;
	import com.tarjetafiel.caja.vo.Operador;
	import com.tarjetafiel.caja.vo.util.Filtro;
	
	import mx.collections.ArrayCollection;
	import mx.rpc.AsyncToken;
	import mx.rpc.IResponder;
	import mx.rpc.remoting.RemoteObject;
	
	public class CajaAperturaDelegate extends BaseDelegate{
		
		public function CajaAperturaDelegate(responder:IResponder) {
			super(responder);
		}
		
		public function buscarAperturaCaja(operador:Operador):void{
			var ro:RemoteObject = ServiceLocator.getInstance().getRemoteObject("cajaApertura");			
			var at:AsyncToken = ro.getCajaAperturaFlex(operador,null);
			at.addResponder(responder);
		}
		
		public function buscarMediosPagoCaja(cajaApertura:CajaApertura):void{
			var ro:RemoteObject = ServiceLocator.getInstance().getRemoteObject("formaPagoCaja");
			var filtro:Filtro = new Filtro();
			filtro.campos.push("caja.idCaja");
			filtro.operadores.push(Filtro.IGUAL);
			filtro.valores.push(cajaApertura.caja.idCaja);
			filtro.campos.push("habilitado");
			filtro.operadores.push(Filtro.IGUAL);
			filtro.valores.push("'S'");
			var ask:AsyncToken = ro.listarFormaPagoCajaFlex(filtro);
			ask.addResponder(responder);
		}
		
		public function buscarFormaPagoValores():void{
			var ro:RemoteObject = ServiceLocator.getInstance().getRemoteObject("formaPagoValor");
			var ask:AsyncToken = ro.getFormaPagoValoresFlex(new Filtro());
			ask.addResponder(responder);
		}
		
		public function registrarDescargaValores(cajaMpList:ArrayCollection, cajaApertura:CajaApertura, operador:Operador, 
												 defaultPrinter:Impresora):void
		{			
			var ro:RemoteObject = ServiceLocator.getInstance().getRemoteObject("movimientoService");
			var ask:AsyncToken = ro.registrarDescargaValores(cajaMpList.toArray(),
				cajaApertura, operador, defaultPrinter);
			ask.addResponder(responder);
		}
		
		public function ejecutarCierre(cajaApertura:CajaApertura, cajaCierreList:ArrayCollection, chequesEnCajaList:ArrayCollection):void
		{
			trace("Caja " + cajaApertura.caja.idCaja);
			trace("Apertura " + cajaApertura.idCajaApertura);
			trace("Cierres " + cajaCierreList);
			
			/*var cajaApertura:CajaApertura = ModelLocator.getInstance().cajaModel.cajaApertura;
			var cajaCierres:Array = ModelLocator.getInstance().arqueoCajaModel.cajaCierreList.toArray();*/
			var ro:RemoteObject = ServiceLocator.getInstance().getRemoteObject("arqueoService");
			
			var ask:AsyncToken = 
				ro.procesarArqueo(cajaApertura,
					cajaCierreList.toArray(),
					cajaApertura.caja.impresora,
					chequesEnCajaList.toArray());
			
			ask.addResponder(responder);
		}
	}
	
}