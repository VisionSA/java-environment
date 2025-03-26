package views.categorias.comercio.models
{
	import com.tarjetafiel.caja.vo.CodComercio;
	import com.tarjetafiel.caja.vo.Empresa;
	import com.tarjetafiel.caja.vo.util.Filtro;
	import com.util.block.ControlBlock;
	import com.util.components.alert.AlertOk;
	import com.util.paginacion.Paginador;
	
	import events.ComercioEvent;
	
	import flash.events.Event;
	import flash.events.EventDispatcher;
	import flash.events.IEventDispatcher;
	
	import managers.ComercioManager;
	
	import mx.collections.ArrayCollection;
	import mx.controls.Alert;

	[Bindable]
	public class ListaComercioModel extends EventDispatcher
	{
		
		public var dispatcher:IEventDispatcher;
		
		private var _paginador:Paginador;
		
		private var _comerciosList:ArrayCollection;
		
		private var _empresaList:ArrayCollection;
		
		private var _comercio:CodComercio;
		
		private var _empresa:Empresa;
		
		private var filtro:Filtro;
		
		public var buscarEmpresa:Boolean = true;
		public var tipoBusqueda:String;
		
		public function ListaComercioModel()
		{
		}
		
		public function buscarComercio(buscarPor:String,parametro:String,paginador:Paginador):void{
			_paginador = paginador;
			filtro = new Filtro();
			/*filtro.campos.push(buscarPor);
			filtro.operadores.push(Filtro.LIKE_DER);
			filtro.valores.push(parametro);*/
			var ordenarPorfechaCarga:String = "sucEmpresa.empresa.cuit";
			
			if(tipoBusqueda == ComercioManager.BUSQ_POR_COD_COMERCIO)
			{
				filtro.funcion = "Where UPPER(" + buscarPor + ") = " + parametro +
					" ORDER BY " + buscarPorDescripcion + ") LIKE '%" + parametro + "%'";
								
			} else
			{
				var buscarPorCuil:String = "sucEmpresa.empresa.cuit";
				var buscarPorRazonSocial:String = "sucEmpresa.empresa.razonSocial";
				 var buscarPorDescripcion:String = "sucEmpresa.descripcion";
				filtro.funcion = "Where UPPER(" + buscarPorCuil + ") LIKE '%" + parametro + "%' or " +
					" UPPER(" + buscarPorRazonSocial + ") LIKE '%" + parametro + "%' or " +
					" UPPER(" + buscarPorDescripcion + ") LIKE '%" + parametro + "%'";
		    }	
			
				
			var evt:ComercioEvent = new ComercioEvent(buscarEmpresa == true ? ComercioEvent.BUSCAR_EMPRESAS : ComercioEvent.BUSCAR_COMERCIOS);
			evt.paginador = _paginador;
			evt.filtro = filtro;
			dispatcher.dispatchEvent(evt);			
			ControlBlock.getInstance().add();
		}
		public function buscarComercioPorEmpresa(buscarPor:String,parametro:String,paginador:Paginador):void{
			_paginador = paginador;
			filtro = new Filtro();
			filtro.funcion = "Where UPPER(" + buscarPor + ") LIKE '" + parametro + "'";		
			var evt:ComercioEvent = new ComercioEvent(ComercioEvent.BUSCAR_COMERCIOS);
			evt.paginador = _paginador;
			evt.filtro = filtro;
			dispatcher.dispatchEvent(evt);			
			ControlBlock.getInstance().add();
		}
		
		public function paginar(paginador:Paginador):void{
			_paginador = paginador;
			var evt:ComercioEvent = new ComercioEvent(buscarEmpresa == true ? ComercioEvent.BUSCAR_EMPRESAS : ComercioEvent.BUSCAR_COMERCIOS);
			evt.paginador = _paginador;
			evt.filtro = filtro;
			dispatcher.dispatchEvent(evt);			
			ControlBlock.getInstance().add();
		}
		
		public function set comerciosList(list:ArrayCollection):void{
			_comerciosList = list;
			dispatchEvent(new Event("changedComercioList"));
			//Si las busqueda es por CodigoPosnet no muestro el PopUp para seleccionar.
			
			Alert.show("comerciosList");
			
			if(tipoBusqueda == ComercioManager.BUSQ_POR_COD_COMERCIO || _comerciosList.length==1)
			{
				comercio = _comerciosList.getItemAt(0) as CodComercio;
			}
			else
			{
				dispatcher.dispatchEvent(new Event("loadPopUpEvent"));	
			}
			
		}
		
		[Bindable (event="changedComercioList")]
		public function get comerciosList():ArrayCollection{			
			return _comerciosList;
		}
		
		public function set empresaList(list:ArrayCollection):void{
			_empresaList = list;
			dispatchEvent(new Event("changedEmpresaList"));
			dispatcher.dispatchEvent(new Event("loadPopUpEvent"));
			
			
		}
		
		[Bindable (event="changedEmpresaList")]
		public function get empresaList():ArrayCollection{
			return _empresaList;
		}
		
		public function set paginador(paginador:Paginador):void{
			_paginador = paginador;
			if(buscarEmpresa){
				this.empresaList = new ArrayCollection(paginador.result);
			} else {
				this.comerciosList = new ArrayCollection(paginador.result);	
			}
		}
		
		public function get paginador():Paginador{
			return _paginador;
		}
		
		[Bindable (event="changedCodComercio")]
		public function get comercio():CodComercio{
			return _comercio;
		} 
		
		public function set comercio(cod:CodComercio):void{
			if(cod){
				_comercio = cod;
				dispatchEvent(new Event("changedCodComercio"));
				dispatcher.dispatchEvent(new Event("changedEmpresa"));
			}
		}
		
		[Bindable (event="changedEmpresa")]
		public function get empresa():Empresa{
			return _empresa;
		} 
		
		public function set empresa(cod:Empresa):void{
			if(cod){
				_empresa = cod;
				dispatchEvent(new Event("changedEmpresa"));
				dispatcher.dispatchEvent(new Event("changedEmpresa"));
			}
		}


	}
}