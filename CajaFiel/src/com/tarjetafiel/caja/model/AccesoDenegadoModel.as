package com.tarjetafiel.caja.model
{
	import com.tarjetafiel.caja.view.AccesoDenegado;
	
	import mx.core.Application;
	import mx.managers.PopUpManager;
	
	public class AccesoDenegadoModel
	{
		[Bindable]public var motivo:String = "";		
		public function AccesoDenegadoModel()
		{
		}
		
		public function addPopUpAccesoDenegado(mot:String=""):void{
			motivo = mot;
			var acc:AccesoDenegado = new AccesoDenegado();
			PopUpManager.addPopUp(acc,Application.application.contGlobal,true);
			PopUpManager.centerPopUp(acc);
			Application.application.visible = false;			
		}
		
		public function addPopUpCajaCerrada():void{			
			motivo = "Operador " + ModelLocator.getInstance().operadorModel.operador.apellido + ", " 
			+ ModelLocator.getInstance().operadorModel.operador.nombre
			+"\nLa caja ha sido cerrada con éxito.";
			var acc:AccesoDenegado = new AccesoDenegado();			
			PopUpManager.addPopUp(acc,Application.application.contGlobal,true);
			PopUpManager.centerPopUp(acc);
			acc.txtDeneagado.text = "Caja Cerrada";
			Application.application.visible = false;
		}

	}
}