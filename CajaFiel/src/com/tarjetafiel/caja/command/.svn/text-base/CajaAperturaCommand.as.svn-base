package com.tarjetafiel.caja.command
{
	import com.adobe.cairngorm.commands.ICommand;
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.tarjetafiel.caja.business.CajaAperturaDelegate;
	import com.tarjetafiel.caja.event.BancoEvent;
	import com.tarjetafiel.caja.event.ChequeEstadoEvent;
	import com.tarjetafiel.caja.event.ConceptosEvent;
	import com.tarjetafiel.caja.event.FormaPagoCajaEvent;
	import com.tarjetafiel.caja.event.ImpresorasEvent;
	import com.tarjetafiel.caja.model.ModelLocator;
	import com.tarjetafiel.caja.view.AccesoDenegado;
	import com.tarjetafiel.caja.vo.CajaApertura;
	import com.util.block.ControlBlock;
	
	import mx.core.Application;
	import mx.managers.PopUpManager;
	import mx.rpc.IResponder;
	import mx.rpc.events.FaultEvent;
	import mx.rpc.events.ResultEvent;

	public class CajaAperturaCommand implements ICommand, IResponder
	{
		public function CajaAperturaCommand()
		{
		}

		public function execute(event:CairngormEvent):void
		{
			ControlBlock.getInstance().add();
			new CajaAperturaDelegate(this).buscarApertura();
		}
		
		public function result(data:Object):void
		{
			ModelLocator.getInstance().cajaModel.cajaApertura = ResultEvent(data).result as CajaApertura;						 	
			ModelLocator.getInstance().impresorasModel.impresoraDefault = ModelLocator.getInstance().cajaModel.caja.impresora;			
			new ConceptosEvent(ConceptosEvent.LIST_CONCEPTOS).dispatch();
			new BancoEvent(BancoEvent.BUSCAR_BANCOS).dispatch();							
			new BancoEvent(BancoEvent.BUSCAR_BANCOS_PROPIOS).dispatch();
			new FormaPagoCajaEvent(FormaPagoCajaEvent.BUSCAR_FORMA_PAGO_CAJA).dispatch();
			new ChequeEstadoEvent(ChequeEstadoEvent.BUSCAR_TODOS).dispatch();			
			new ImpresorasEvent(ImpresorasEvent.LISTAR).dispatch();		
			ControlBlock.getInstance().remove();
		}
		
		public function fault(info:Object):void
		{
			ControlBlock.getInstance().remove();						
			var acc:AccesoDenegado = new AccesoDenegado();
			PopUpManager.addPopUp(acc, Application.application.contGlobal,true);
			PopUpManager.centerPopUp(acc);
			Application.application.visible = false;
			ModelLocator.getInstance().accesoDenegadoModel.motivo = FaultEvent(info).fault.faultString;
		}
		
	}
}