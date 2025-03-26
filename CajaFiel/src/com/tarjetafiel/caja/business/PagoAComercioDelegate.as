package com.tarjetafiel.caja.business
{
	import com.adobe.cairngorm.business.ServiceLocator;
	import com.tarjetafiel.caja.model.EmpresaModel;
	import com.tarjetafiel.caja.model.ModelLocator;
	import com.tarjetafiel.caja.vo.CodComercio;
	import com.tarjetafiel.caja.vo.Empresa;
	import com.tarjetafiel.caja.vo.SucEmpresa;
	
	import mx.rpc.AsyncToken;
	import mx.rpc.IResponder;
	import mx.rpc.remoting.RemoteObject;

	public class PagoAComercioDelegate extends BaseDelegate
	{
		public function PagoAComercioDelegate(responder:IResponder)
		{
			super(responder);
		}
		
		/**
		 * El nombre de este metodo hace referencia a pagos pero en realidad lista las liquidaciones no entregadas
		 * este quilombo se debe al gran analisis realizado de antemano por el analista y el lider de proyecto
		 * Andaaaaaaa!!!! 
		 **/
		public function buscarPagos():void{
			
			
			var empresa:Empresa = ModelLocator.getInstance().empresaModel.empresa;			
			
			var ro:RemoteObject = ServiceLocator.getInstance().getRemoteObject("iliquidacionComercioService");
			var ask:AsyncToken = ro.getLiquidacionesNoEntregadasFlex(empresa);
			ask.addResponder(responder);
			
		}
		
		public function entregarTodosLosPagos():void{
			var ro:RemoteObject = ServiceLocator.getInstance().getRemoteObject("iliquidacionComercioService");
			var codComercio:CodComercio;
			if(ModelLocator.getInstance().empresaModel.empresa.tipoLiquidacion == EmpresaModel.LIQUIDACION_POR_COMERCIO){
				codComercio = SucEmpresa(ModelLocator.getInstance().empresaModel.empresa.sucEmpresas[0]).codComercio;
			}
			var pendingCall:AsyncToken = ro.entregarLiquidaciones(
			ModelLocator.getInstance().pagosAComercios.liquidacionesAEntregar.toArray(),
			ModelLocator.getInstance().cajaModel.caja,
			ModelLocator.getInstance().impresorasModel.impresoraDefault,
			ModelLocator.getInstance().operadorModel.operador,
			ModelLocator.getInstance().empresaModel.empresa,
			ModelLocator.getInstance().individuosHabilitadosPagosModel.individuo);
			pendingCall.addResponder(responder);			
		}
		
	}
}