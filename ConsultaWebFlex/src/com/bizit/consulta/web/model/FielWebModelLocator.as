package com.bizit.consulta.web.model {
	import com.adobe.cairngorm.model.ModelLocator;
	import com.bizit.consulta.web.entity.UsuarioComercioVO;
	import com.bizit.consulta.web.entity.UsuarioComercioWeb;
	import com.bizit.consulta.web.entity.UsuarioWeb;
	import com.bizit.consulta.web.event.LoginChangeStateEvent;
	import com.bizit.consulta.web.utils.ConstantesEstados;
	import com.bizit.consulta.web.utils.ManejadorPantallas;
	import com.bizit.consulta.web.view.customComponent.BlockBar;
	
	import flash.net.FileReference;
	import flash.utils.ByteArray;
	
	import mx.collections.ArrayCollection;
	import mx.modules.ModuleLoader;
	
	[Bindable]
	public class FielWebModelLocator implements ModelLocator{
		
		private static var _instance: FielWebModelLocator;
		
		/* Configuración Aplicación*/
		public var AMFChannelDefinition:String = "";
		public var webServiceUrl:String = "";
		
		/*Modulos de la aplicación*/
		public var mlAplicacion:ModuleLoader = null;
		public var mlComercio:ModuleLoader = null;
		
		//public var progressBar:ProgressBar = null;
		public var blockBar:BlockBar = null;
		
		public var listaPermisoUsuarios : ArrayCollection = null;
		
		public var usuarioWeb : UsuarioWeb = null;
		
		public var usuarioComercio : UsuarioComercioWeb = null;
		public var usuarioComVO : UsuarioComercioVO = new UsuarioComercioVO();		
		
		public static const PATH_MODULES : String = "com/bizit/consulta/web/view/module/";
		public static const PATH_COMERCIO_MODULES : String = PATH_MODULES+"comercio/";
		
		public var listaLiqComercios : ArrayCollection = new ArrayCollection();
		public var listaRetComercio : ArrayCollection = new ArrayCollection();
		
		public var liquidacionArchivoPdf:FileReference = null;
		public var byteArrayPdf:ByteArray = null;
		public var byteArrayTxt:ByteArray = null;
		public var idLiquidacionGenerarTxt:Number = 0;
		
		
		/* Variables para cambio de estado */
		public var estadoAplicacion : String = ConstantesEstados.LOGIN_APP_STATE;
		public var estadoLoginUsuario : String = LoginChangeStateEvent.LOGIN_STATE;
		public var estadoBuscarLiqComercio : String = ConstantesEstados.BUSCAR_LIQ_COMERCIO_STATE;
		public var estadoBuscarCertRetComercio : String = ConstantesEstados.BUSCAR_CERT_RET_COMERCIO_STATE;
		/* ************************ */
		
		public function FielWebModelLocator(enforcer:SingletonEnforcer) {
			if (enforcer == null){
				throw new Error("Solo se puede tener una instancia de CobranzasModelLocator");
			}  
		}
		
		public static function getInstance(): FielWebModelLocator {
			if (_instance == null){
				_instance =  new FielWebModelLocator(new SingletonEnforcer());
			}
			return _instance;
		}
		
		public function setURLChannelDefinition() : void {
			
			var url : String = ManejadorPantallas.getAplicacion().url;
			var pattern : RegExp = /\/ConsultaWebFlex.swf.*/;
			AMFChannelDefinition = url.replace(pattern,"")+"/messagebroker/amf";
			
		}
		
		public function setUrlWebServices(url:String):void
		{
			webServiceUrl = url;
		}
	}
	
}
// Utility class to deny access to the constructor
class SingletonEnforcer {}