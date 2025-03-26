package com.tarjetafiel.caja.model
{
	import com.tarjetafiel.caja.event.CajaArqueoEvent;
	import com.tarjetafiel.caja.event.DescargaValoresEvent;
	import com.tarjetafiel.caja.view.clientes.ConsultaLiquidacionesClienteView;
	import com.util.modules.ManagerModules;
	import com.util.modules.TitleWindowCustom;
	
	import flexmdi.containers.MDICanvas;
	import flexmdi.containers.MDIWindow;
	
	import mx.events.MenuEvent;
	import mx.core.Application;
	import flash.display.DisplayObject;
	
	/*import com.tarjetafiel.caja.view.caja.ImpresorasView;
	import mx.managers.PopUpManager;*/
	
	
	[Bindable]
	public class NavegacionModel
	{
		
		/*private var popUpImpresoras:ImpresorasView = new ImpresorasView();*/
		
		public function clickMenu(evt:MenuEvent, mdiCanvas:MDICanvas=null):void{
			
			/*if(evt.item.@modulo != "impresoras") {
				showListaImpresoras();
			}*/
			
			if(evt.item.@modulo != "") {
					
				if(evt.item.@contenedor == "TitleWindow"){
					
					if(evt.item.@id == "cierreProvisorio" || evt.item.@id == "cierreDefinitivo"){
						ModelLocator.getInstance().arqueoCajaModel.mostrarDiferencia = false;
						ModelLocator.getInstance().arqueoCajaModel.dispatchEvent(new CajaArqueoEvent(CajaArqueoEvent.NUEVO_ARQUEO));
					}
					
					if(evt.item.@id == "descargaValores"){
						ModelLocator.getInstance().descargaValoresModel.dispatchEvent(new DescargaValoresEvent(DescargaValoresEvent.NUEVO_RETIRO));
					}
						
					
					if(ManagerModules.ventanasAbiertas.array[evt.item.@modulo] != null) {
						ManagerModules.ventanasAbiertas.showVentana(evt.item.@modulo, null);					
					}else {					
						ManagerModules.getInstance().loadModule(new TitleWindowCustom(),evt.item.@modulo,evt.item.@titulo);
					}
				} else {									
					
					if(mdiCanvas){
						if(ManagerModules.ventanasAbiertas.array[evt.item.@modulo] != null) {
							ManagerModules.ventanasAbiertas.showVentana(evt.item.@modulo, mdiCanvas);					
						}else {											
							ManagerModules.getInstance().loadModule(mdiCanvas,evt.item.@modulo,evt.item.@titulo);							
						}
					}
				}
						
			}
		}
		
		

	}
}