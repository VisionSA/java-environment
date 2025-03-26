package model {
	
	import com.adobe.cairngorm.model.IModelLocator;
	
	[Bindable]
	public class PaginacionSearchModelLocator implements IModelLocator {
	
	    private static var _instanceCabeceraConciliacion:SearchModelLocator = null;
	    private static var _instanceReversion:SearchModelLocator = null;
/*@I3918*/		private static var _instanceReporteConciliacion:SearchModelLocator = null;
/*@F3918*/		
		public function PaginacionSearchModelLocator() {
			super();
		}
		
		/**
	     * Implementation of <code>ModelLocator</code>.
	     */
	    public static function getInstanceCabecera():SearchModelLocator { 
	    	if (_instanceCabeceraConciliacion == null)  { 
	    		_instanceCabeceraConciliacion = new SearchModelLocator(); 
	        } 
	            
	        return _instanceCabeceraConciliacion; 
	    } 
	    public static function getInstanceReversion():SearchModelLocator { 
	    	if (_instanceReversion == null)  { 
	    		_instanceReversion = new SearchModelLocator(); 
	        } 
	            
	        return _instanceReversion; 
	    }
		
		/**@id: 3918
		 * 
		 * */
		public static function getInstanceReporteConciliacion():SearchModelLocator
		{
			if (_instanceReporteConciliacion == null)  { 
				_instanceReporteConciliacion = new SearchModelLocator(); 
			} 
			
			return _instanceReporteConciliacion;
		}
	
	}
		
}


// Utility class to deny access to the constructor
class SingletonEnforcer {}