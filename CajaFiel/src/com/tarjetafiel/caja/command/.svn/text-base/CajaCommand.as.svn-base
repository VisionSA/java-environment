package com.tarjetafiel.caja.command
{
	import com.adobe.cairngorm.commands.ICommand;
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.tarjetafiel.caja.business.CajaDelegate;
	import com.tarjetafiel.caja.event.BancoEvent;
	import com.tarjetafiel.caja.event.CajaEvent;
	import com.tarjetafiel.caja.event.ChequeEstadoEvent;
	import com.tarjetafiel.caja.event.ConceptosEvent;
	import com.tarjetafiel.caja.event.FormaPagoCajaEvent;
	import com.tarjetafiel.caja.event.ImpresorasEvent;
	import com.tarjetafiel.caja.model.ModelLocator;
	import com.tarjetafiel.caja.view.AccesoDenegado;
	import com.tarjetafiel.caja.vo.Caja;
	import com.util.block.ControlBlock;
	
	import mx.core.Application;
	import mx.managers.PopUpManager;
	import mx.rpc.IResponder;
	import mx.rpc.events.FaultEvent;
	import mx.rpc.events.ResultEvent;

	public class CajaCommand implements ICommand, IResponder
	{
		public function CajaCommand()
		{
		}

		public function execute(event:CairngormEvent):void
		{
			ControlBlock.getInstance().add();
			new CajaDelegate(this).buscarCaja();
		}
		
		public function result(data:Object):void
		{
			//Traigo los conceptos de fiel
			ControlBlock.getInstance().remove();
			ModelLocator.getInstance().cajaModel.caja = ResultEvent (data).result as Caja;			
			ModelLocator.getInstance().impresorasModel.impresoraDefault = ModelLocator.getInstance().cajaModel.caja.impresora;
			
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