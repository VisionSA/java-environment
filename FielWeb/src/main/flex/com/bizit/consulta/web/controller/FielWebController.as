package com.bizit.consulta.web.controller {
	import com.adobe.cairngorm.control.FrontController;
	import com.bizit.consulta.web.command.permisoWeb.PermisoWebListAllCommand;
	import com.bizit.consulta.web.event.PermisoWebEvent;
	
	public class FielWebController extends FrontController {
		
		
		public function FielWebController() {
			this.inicializar();
			
		}
		
		private function inicializar():void{
			addCommand(PermisoWebEvent.LIST_ALL,PermisoWebListAllCommand);	
		}
		
	}
}