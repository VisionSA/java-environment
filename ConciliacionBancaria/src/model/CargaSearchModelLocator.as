package model {
	
	import com.adobe.cairngorm.model.IModelLocator;
	
	import mx.collections.ArrayCollection;
	
	[Bindable]
	public class CargaSearchModelLocator implements IModelLocator {
	
		public var  data:ArrayCollection = new ArrayCollection();
    	//public var  listdata:ArrayCollection = new ArrayCollection();
    	private var cantRegistros:int = 0;
	    public var  cantPorPagina:int = 0;
	    public var  numPage:int = 0;
	    public var  nexPag:Boolean = true;
	    public var  paginas:ArrayCollection = new ArrayCollection();	    
	    private static var _instance:CargaSearchModelLocator = null;
		public var pagSel:int=0;
		
		public function CargaSearchModelLocator() {
			super();
		}
		
		/**
	     * Implementation of <code>ModelLocator</code>.
	     */
	    public static function getInstance():CargaSearchModelLocator { 
	    	if (_instance == null)  { 
	    		_instance = new CargaSearchModelLocator(); 
	        } 
	            
	        return _instance; 
	    } 
	
	    public function get cantPaginas():int {
			return cantRegistros;
		}
		
		public function set cantPaginas(o:int):void {
			cantRegistros = o;
		}	
		
		public function cantPaginasValor(o:int):void {	
			cantRegistros = o;	
			nexPag = true;
			if(cantPorPagina == 0){cantPorPagina = 10;}
			if(cantRegistros > cantPorPagina){
			   numPage= Math.ceil(cantRegistros/cantPorPagina);
			}else{numPage = 1;}  
			if(numPage > 0){
				paginas.removeAll();	
				for( var x:int = 0; x < numPage; x++){
					var pg:int = x + 1;			
					paginas.addItem({label: pg ,data: pg});
				}
			}
		}

	}
		
}


// Utility class to deny access to the constructor
class SingletonEnforcer {}