package com.fiel.caja.judicial.command.arqueoCaja
{
	import com.adobe.cairngorm.commands.ICommand;
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.fiel.caja.judicial.business.ConstantesEstados;
	import com.fiel.caja.judicial.model.CajaJudicialModelLocator;
	import com.tarjetafiel.caja.vo.CajaCierre;
	import com.tarjetafiel.caja.vo.RespuestaImpresion;
	import com.tarjetafiel.proveedorconexion.delegate.CajaAperturaDelegate;
	
	import mx.rpc.IResponder;
	import mx.rpc.events.FaultEvent;
	import mx.rpc.events.ResultEvent;
	import mx.utils.StringUtil;
	
	import utils.ManejadorMensajes;
	import utils.ManejadorPantallas;
	import utils.constantes.ConstantesCierreCaja;
	
	public class EjecutarArqueoCajaCommand implements ICommand, IResponder 
	{
		[Bindable]
		private var modelo:CajaJudicialModelLocator = CajaJudicialModelLocator.getInstance();
		
		public function execute(event:CairngormEvent):void
		{
			ManejadorPantallas.mostrarProgressBar();
			new CajaAperturaDelegate(this).ejecutarCierre(modelo.cajaApertura, modelo.arrCajaCierre, modelo.arrChequesEnCaja);
		}
		
		public function result(data:Object):void
		{
			var respuesta:RespuestaImpresion = ResultEvent(data).result as RespuestaImpresion;
			
			if(modelo.tipoDeCierreCaja == ConstantesCierreCaja.ARQUEO_PROVISORIO)
			{				
				var cierres:Array = respuesta.target as Array;
				modelo.mostrarDiferencia = true;
				for each(var cierre:CajaCierre in cierres)
				{
					for each(var cierreActual:CajaCierre in modelo.arrCajaCierre)
					{
						if(cierre.caja.idCajaMP == cierreActual.caja.idCajaMP)
						{						
							cierreActual.totalContable = cierre.totalContable;
							cierreActual.diferencia = cierre.diferencia;
						}
						
					}
				}
				modelo.estadoActualVMenuCajas = ConstantesEstados.ESTADO_VMC_NUEVO_CIERRE_CAJA_X;
			}
			else 
			{
				modelo.estadoActualVMenuCajas = ConstantesEstados.ESTADO_VMC_CIERRA_CAJA_Z_COMPLETO;
			}
			
			if(respuesta.falloImpresion)
			{
				ManejadorMensajes.mostrarMensajeNotificacion(respuesta.mensaje);
			} 
			else 
			{
				modelo.ultimaRespuestaImpresion = RespuestaImpresion(data.result);
				ManejadorMensajes.mostrarMensajeInformacion(respuesta.mensaje);
			}
			ManejadorPantallas.cerrarProgressBar();
			
		}
		
		public function fault(info:Object):void
		{
			ManejadorPantallas.cerrarProgressBar();
			ManejadorMensajes.mostrarMensajeErrorFaultEvent(FaultEvent(info),
			StringUtil.substitute("Error al ejecutar el cierre {0}",modelo.tipoDeCierreCaja));
		}
	}
}