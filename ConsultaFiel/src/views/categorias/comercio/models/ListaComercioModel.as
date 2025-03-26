package views.categorias.comercio.models
{
	import com.tarjetafiel.caja.vo.CodComercio;
	import com.tarjetafiel.caja.vo.Email;
	import com.tarjetafiel.caja.vo.Empresa;
	import com.tarjetafiel.caja.vo.Telefono;
	import com.tarjetafiel.caja.vo.util.Filtro;
	import com.util.block.ControlBlock;
	import com.util.components.alert.AlertOk;
	import com.util.paginacion.Paginador;
	
	import events.ComercioEvent;
	import events.ComercioManagerEvent;
	
	import flash.display.DisplayObject;
	import flash.events.Event;
	import flash.events.EventDispatcher;
	import flash.events.IEventDispatcher;
	
	import managers.ComercioManager;
	
	import mx.collections.ArrayCollection;
	import mx.controls.Alert;
	import mx.core.Application;
	import mx.managers.PopUpManager;
	
	import views.categorias.comercio.modules.DetalleInformacionComercioModule;
	import views.categorias.comercio.popup.AgregarMailComercioView;
	import views.categorias.comercio.popup.AgregarTelefonoComercioView;

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
		
		public var parametroRazonSocial:String;
		
		public var buscarEmpresa:Boolean = true;
		public var tipoBusqueda:String;
		public var detalleInformacionComercioModule:DetalleInformacionComercioModule = null;
		public var popUpAgregarMail:AgregarMailComercioView = new AgregarMailComercioView();
		public var popUpAgregarTelefono:AgregarTelefonoComercioView = new AgregarTelefonoComercioView();
		
		public var arrayTiposTelefono:ArrayCollection;
				
		public var telefonoSuc:ArrayCollection;
		public var emailSuc:ArrayCollection;

		public function ListaComercioModel()
		{
		}
		
		public function reporteComercio(cuit:String):void{
			var evt:ComercioEvent = new ComercioEvent(ComercioEvent.REPORTE_COMERCIO);
			evt.cuit = cuit;
			dispatcher.dispatchEvent(evt);			
			/*ControlBlock.getInstance().add();*/			
		}
		
		public function buscarComercio(buscarPor:String,parametro:String,paginador:Paginador):void{
			_paginador = paginador;
			filtro = new Filtro();
			/*filtro.campos.push(buscarPor);
			filtro.operadores.push(Filtro.LIKE_DER);
			filtro.valores.push(parametro);*/
			
			if(buscarPor == "codigoPosnet"){
				filtro.funcion = "Where UPPER(" + buscarPor + ") = '" + parametro + "'";
				var evt:ComercioEvent = new ComercioEvent(ComercioEvent.BUSCAR_COMERCIO);
				evt.paginador = _paginador;
				evt.filtro = filtro;
			}else{
				if(buscarPor == "sucEmpresa.empresa.cuit"){
					filtro.funcion = "Where UPPER(" + buscarPor + ") = '" + parametro + "'";
					var evt:ComercioEvent = new ComercioEvent(ComercioEvent.BUSCAR_COMERCIO);
					evt.paginador = _paginador;
					evt.filtro = filtro;
				}if(buscarPor == "sucEmpresa.empresa.razonSocial"){
					
					filtro.funcion = "Where UPPER(" + buscarPor + ") like '%" + parametro + "%'";
					var evt:ComercioEvent = new ComercioEvent(ComercioEvent.BUSCAR_COMERCIOS);
					evt.paginador = _paginador;
					evt.filtro = filtro;
				}else{
					filtro.funcion = "Where UPPER(" + buscarPor + ") LIKE '%" + parametro + "%'";
					var evt:ComercioEvent = new ComercioEvent(buscarEmpresa == true ? ComercioEvent.BUSCAR_EMPRESAS : ComercioEvent.BUSCAR_COMERCIOS);
					evt.paginador = _paginador;
					evt.filtro = filtro;
				}
			}			
			dispatcher.dispatchEvent(evt);			
			ControlBlock.getInstance().add();
		}
		
		public function buscarComercioPorEmpresa(buscarPor:String,parametro:String,paginador:Paginador):void{
			_paginador = paginador;
			filtro = new Filtro();
			filtro.funcion = "Where UPPER(" + buscarPor + ") = '" + parametro + "'";		
			var evt:ComercioEvent = new ComercioEvent(ComercioEvent.BUSCAR_COMERCIO);
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
			
			if((tipoBusqueda == ComercioManager.BUSQ_POR_COD_COMERCIO || tipoBusqueda == ComercioManager.BUSQ_POR_CUIL ) && _comerciosList.length==1)
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
				this.comerciosList = new ArrayCollection(paginador.result);
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
/*@I3945*/		/**
		 * @id 3945
		 * abre el popUp para agregar el mail
		 **/
		public function abrirPopUpAgregarMail():void{
			//llamo al popUp agregar mail
			popUpAgregarMail.listaComercioModel= this;
			PopUpManager.addPopUp(popUpAgregarMail,Application.application as  DisplayObject, true);
			PopUpManager.centerPopUp(popUpAgregarMail);	
		}  
		/**
		 * @id 3945
		 * lanza en evento para agregar el mail
		 **/
		public function agregarMail(mail:Email,idSucEmpresa:Number):void{
			//agrega el nuevo mail al individuo actual
			var evt:ComercioEvent;
			evt = new ComercioEvent(ComercioEvent.AGREGAR_EMAIL);
			evt.email = mail;
			evt.idSucEmpresa = idSucEmpresa;
			
			dispatcher.dispatchEvent(evt);
			
			ControlBlock.getInstance().add();
			return;
		}
		/**
		 * @id 3945
		 * abre el popUp para agregar el telefono
		 **/
		
		public function abrirPopUpAgregarTelefono():void{
			//llamo al popUp agregar Telefono
			popUpAgregarTelefono.listaComercioModel = this;
			popUpAgregarTelefono.initCombo();
			PopUpManager.addPopUp(popUpAgregarTelefono,Application.application as  DisplayObject, true);
			popUpAgregarTelefono.cleanData();
			PopUpManager.centerPopUp(popUpAgregarTelefono);
			popUpAgregarTelefono.setH(95);//height
		}  
		/**
		 * @id 3945
		 * lanza el evento para agregar el telefono
		 **/
		public function agregarTelefono(tel:Telefono,idSucEmpresa:Number):void{
			//agrega el nuevo mail al individuo actual
			var evt:ComercioEvent;
			evt = new ComercioEvent(ComercioEvent.AGREGAR_TELEFONO);
			evt.telefono = tel;
			evt.idSucEmpresa = idSucEmpresa;
			
			dispatcher.dispatchEvent(evt);
			
			ControlBlock.getInstance().add();
		}
		/**
		 * @id 3945
		 * lanza el evento para cargar el combo de TipoTelefono
		 **/
		public function cargarCmbTipoTel():void {
			if(this.arrayTiposTelefono == null || this.arrayTiposTelefono.length == 0)
			{
				var evt:ComercioEvent;
				evt = new ComercioEvent(ComercioEvent.CARGAR_CMB_TIPO_TEL);
				
				dispatcher.dispatchEvent(evt);
				
				ControlBlock.getInstance().add();
			}
		}
		/**
		 * @id 3945
		 * lanza el evento para cargar el combo de TipoTelefono
		 **/
		public function resultCargarCmbTipoTelefono(result:Array):void {
			if(result != null){
				this.arrayTiposTelefono = new ArrayCollection(result);
			}else{
				Alert.show("No se pudieron cargar los tipos de telefono, intente mas tarde");
			}
			ControlBlock.getInstance().remove();
		}
		
		/**
		 * @id 3945
		 * lanza el evento para recargar los datos de la pagina
		 **/
		public function recargarDatos():void {
			this.detalleInformacionComercioModule.refreshAllComponents();
		}
		
		public function limpiarDatos():void{
			this.detalleInformacionComercioModule.limpiarDatos();
			this.comercio = new CodComercio();
			
			this.empresa= new Empresa();
			this.telefonoSuc=null;
			this.emailSuc=null;
		}
/*@F3945*/

	}
}