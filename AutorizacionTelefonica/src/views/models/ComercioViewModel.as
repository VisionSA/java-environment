package views.models
{
	import com.tarjetafiel.caja.vo.ClienteTransaccion;
	import com.tarjetafiel.caja.vo.CodComercio;
	import com.tarjetafiel.caja.vo.ListaPrecio;
	
	import events.ComercioEvent;
	
	import flash.events.Event;
	import flash.events.EventDispatcher;
	import flash.events.IEventDispatcher;
	
	[Bindable]
	public class ComercioViewModel extends EventDispatcher
	{
		public var dispatcher:IEventDispatcher
		private var _comercio:CodComercio;
		private var _cliente:ClienteTransaccion;
		public var direccion:String;
		public var mostrarPanelComercio:Boolean = false;
		private var _listaPrecioSelected:ListaPrecio;
		public var mostrarConsumo:Boolean = false;
		public var mostrarCupon:Boolean = false;
		public var mostrarMsjListaPrecioVacia:Boolean = false;
		private var _comercioSeleccionado:CodComercio;
		private var _nroCupon:String = "";  
		public var listaPrecioSize: Number;
		public var estadoConc:String="P";
     
     	public function ComercioViewModel()
		{
		}
		
		public function buscarPorCodComercio(codigoPosnet:String):void{
			var evt:ComercioEvent = new ComercioEvent(ComercioEvent.BUSCAR_POR_COD_COMERCIO);
			evt.codigoPosnet = codigoPosnet;
			dispatcher.dispatchEvent(evt);
		}
		
		public function set comercio(comer:CodComercio):void{
			listaPrecioSelected = null;
			_comercio = comer;
			  listaPrecioSize = comer.comercioListaPrecioSet.length
			if(_comercio){
				mostrarPanelComercio = true;
				 for each ( var element:ListaPrecio in comer.comercioListaPrecioSet)
                 {     listaPrecioSelected =  element;
                       mostrarConsumo=true;
					  break; 
        		 }
        		
	 			 mostrarCupon =  mostrarConsumo; 	
	 		}
		/*	 direccion= comer.sucEmpresa.domicilio.calleNombre+" "+comer.sucEmpresa.domicilio.calleNumero;
			if(comer.sucEmpresa.domicilio.piso) direccion.concat(" Piso:"+comer.sucEmpresa.domicilio.piso);
			if(comer.sucEmpresa.domicilio.piso) direccion.concat(" Dpto:"+ comer.sucEmpresa.domicilio.depto);
			direccion.concat(comer.sucEmpresa.domicilio.barrio.descripcion);*/
			if(comer.comercioListaPrecioSet.length !=0)
			     mostrarMsjListaPrecioVacia =false;
			else  mostrarMsjListaPrecioVacia =true;
			
		}
		
		
		public function get comercio():CodComercio{
			return _comercio;
		}
		
		public function set comercioSeleccionado(comer:CodComercio):void{
			_comercioSeleccionado = comer;
		}

		public function get comercioSeleccionado():CodComercio{
			return _comercioSeleccionado;
		}
       
 		public function set listaPrecioSelected(listaPrecioSelected:ListaPrecio):void{ 		
			_listaPrecioSelected = listaPrecioSelected;
			//_listaPrecioSelected		
			if(listaPrecioSelected){
			   mostrarConsumo = true;
			} 
			dispatcher.dispatchEvent(new Event(ComercioEvent.CHANGE_LISTA_PRECIO));
		}
		
		 		
		public function get listaPrecioSelected():ListaPrecio{
			return _listaPrecioSelected;
		}
		
		
        public function set cliente(cliente:ClienteTransaccion):void{
			_cliente = cliente;
		}
		
		public function get cliente():ClienteTransaccion{
			return _cliente;
		}
		
		
		
		public function get nroCupon():String{
			return _nroCupon;
		}
		
		 public function set nroCupon(nro:String):void{
			_nroCupon = nro;
		}
        
        
        public function inicializar():void{
			mostrarMsjListaPrecioVacia=false;
			mostrarPanelComercio=false;
			mostrarConsumo=  false;
			mostrarCupon=false;
		}
		
            
	}
}