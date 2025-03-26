package com.tarjetafiel.caja.model
{
	import com.tarjetafiel.caja.vo.Impresora;
	import com.util.modules.ManagerModules;
	import com.util.modules.TitleWindowCustom;
	
	import flash.events.Event;
	import flash.events.EventDispatcher;
	
	import mx.collections.ArrayCollection;
	
	[Bindable]
	public class ImpresorasModel extends EventDispatcher
	{
		private var _impresorasList:ArrayCollection = new ArrayCollection();
		
		private var _impresoraDefault:Impresora;
		
		public var textImpresoraPredeterminada:String = "No hay ninguna impresora predeterminada";
		
		public function ImpresorasModel()
		{
		}
		
		public function set impresorasList(list:ArrayCollection):void{
			_impresorasList = list;			
			dispatchEvent(new Event("changedListImpresoras"));
		}
		
		[Bindable (event="changedListImpresoras")]
		public function get impresorasList():ArrayCollection{
			return _impresorasList;
		}
		
		public function set impresoraDefault(impresora:Impresora):void{
			if(impresora){
				_impresoraDefault = impresora;
				textImpresoraPredeterminada = impresora.descripcion + " - " + impresora.path + " - " + impresora.nombre;
				dispatchEvent(new Event("impresoraDefault"));
			}			 
		}
		
		[Bindable (event="impresoraDefault")]
		public function get impresoraDefault():Impresora{
			return _impresoraDefault;
		}
		
		public function showListaImpresoras():void{
			if(ManagerModules.ventanasAbiertas.array["com/tarjetafiel/caja/view/caja/ImpresorasView.swf"] != null) {
				ManagerModules.ventanasAbiertas.showVentana("com/tarjetafiel/caja/view/caja/ImpresorasView.swf", null);
			} else {
				ManagerModules.getInstance().loadModule(new TitleWindowCustom()
				,"com/tarjetafiel/caja/view/caja/ImpresorasView.swf","Lista de Imrpesoras");
			}
				
		}

	}
}