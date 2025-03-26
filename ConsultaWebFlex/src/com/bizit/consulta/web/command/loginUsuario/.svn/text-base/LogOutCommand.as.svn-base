package com.bizit.consulta.web.command.loginUsuario
{
	import com.adobe.cairngorm.commands.ICommand;
	import com.adobe.cairngorm.control.CairngormEvent;
	import com.bizit.consulta.web.model.FielWebModelLocator;
	import com.bizit.consulta.web.utils.ConstantesEstados;
	import com.bizit.consulta.web.utils.ManejadorPantallas;
	import com.bizit.consulta.web.view.module.ViewConsultaComercioModule;
	
	import flash.system.System;
	
	import mx.modules.IModuleInfo;
	import mx.modules.Module;
	import mx.modules.ModuleLoader;
	import mx.modules.ModuleManager;
	import mx.rpc.IResponder;
	
	import spark.components.ButtonBar;
	import spark.components.Group;
	import spark.components.VGroup;
	
	public class LogOutCommand implements ICommand, IResponder {
		
		[Bindable]
		private var modelo : FielWebModelLocator = FielWebModelLocator.getInstance();
		
		public function execute(event:CairngormEvent):void {
			modelo.usuarioWeb = null;
			
			modelo.estadoAplicacion = ConstantesEstados.LOGIN_APP_STATE;
			
			if (modelo.mlComercio != null)
			{
				modelo.mlComercio.unloadModule();
				modelo.mlComercio.unloadModule();
				modelo.mlComercio.removeAllChildren();
				modelo.mlComercio.url = null;
				modelo.mlComercio.data = null;
				modelo.mlComercio.validateNow();
				modelo.mlComercio = null;
			}
			
			if (modelo.mlAplicacion != null)
			{
				modelo.mlAplicacion.unloadModule();
				modelo.mlAplicacion.removeAllChildren();
				modelo.mlAplicacion.url = null;
				modelo.mlAplicacion.data = null;
				modelo.mlAplicacion.validateNow();
				modelo.mlAplicacion = null;
			}
			
			ButtonBar(ManejadorPantallas.getAplicacion().btnBar).selectedIndex = -1;
			
			System.gc();
		}
		
		public function result(data:Object):void
		{
		}
		
		public function fault(info:Object):void
		{
		}
	}
}