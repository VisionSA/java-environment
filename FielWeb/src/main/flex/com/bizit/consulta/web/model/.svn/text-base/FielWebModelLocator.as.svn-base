package com.bizit.consulta.web.model {
	import com.adobe.cairngorm.model.ModelLocator;
	
	import mx.collections.ArrayCollection;
	
	[Bindable]
	public class FielWebModelLocator implements ModelLocator {length
		
		private static var _instance: FielWebModelLocator;
		
		
		
		public var listaPermisoUsuarios : ArrayCollection = null;
		
		
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
	}
	
}
// Utility class to deny access to the constructor
class SingletonEnforcer {}