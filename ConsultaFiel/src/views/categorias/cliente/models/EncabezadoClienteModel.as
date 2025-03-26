package views.categorias.cliente.models
{
	import com.tarjetafiel.caja.vo.ClienteTransaccion;
	import com.tarjetafiel.caja.vo.Email;
	import com.tarjetafiel.proveedorconexion.model.ModelLocatorGeneral;
	import com.util.block.ControlBlock;
	import com.util.paginacion.Paginador;
	
	import events.ClienteEvent;
	
	import flash.display.DisplayObject;
	import flash.events.Event;
	
	import managers.ClienteManager;
	
	import com.tarjetafiel.caja.vo.Email;
	import com.tarjetafiel.caja.vo.Telefono;
	
	import mx.collections.ArrayCollection;
	import mx.controls.Alert;
	import mx.core.Application;
	import mx.managers.PopUpManager;
	
	import views.categorias.cliente.popup.MostrarClientesView;
	import com.tarjetafiel.caja.vo.util.Filtro;
	
	import views.categorias.cliente.popup.AgregarMailView;
	import views.categorias.cliente.popup.AgregarTelefonoLaoralView;
	import views.categorias.cliente.popup.AgregarTelefonoView;
	import views.categorias.cliente.popup.ModificarMailView;
	import views.categorias.cliente.popup.ModificarTelefonoView;
	import views.categorias.cliente.popup.VerTelefonoLaboralView;

	[Bindable]
	public class EncabezadoClienteModel extends  BaseModel
	{
	    private var _cliente:ClienteTransaccion;
		private var _clienteEncontrado:ClienteTransaccion;
		private var _titular:ClienteTransaccion;
		private var _arrayClientes:ArrayCollection;
		[Bindable]	public var nombreCliente :  String =""; 
		public var popUpMostrarClientes:MostrarClientesView = new MostrarClientesView();
		private var _clienteSeleccionado:ClienteTransaccion;
		public var paginador:Paginador;	
		private var _tipoBusqueda:String;	
		private var _paramValue:String;
		[Bindable]	private var _arrayLineaTemporal:ArrayCollection;
		public var revistaBaja:String;
		public var envioResumen:String;
private var  _arrayMailTitularAdicional:ArrayCollection;
public var desbloqueoRepacta:String;


public var popUpAgregarMail:AgregarMailView = new AgregarMailView();
public var popUpModificarMail:ModificarMailView = new ModificarMailView();
public var popUpAgregarTelefono:AgregarTelefonoView = new AgregarTelefonoView();
public var popUpModificarTelefono:ModificarTelefonoView = new ModificarTelefonoView();
public var popUpModificarTelefonoLaboral:VerTelefonoLaboralView = new VerTelefonoLaboralView();
public var popUpAgregarTelefonoLaboral:AgregarTelefonoLaoralView = new AgregarTelefonoLaoralView();

public var arrayTiposTelefono:ArrayCollection;	


	public var varBloqueDesbloque:String;
	public var varBloqueDesbloqueApp:String;
	private var filtro:Filtro = new Filtro();
	[Bindable]public var informacionCliente:String;
		
		public function EncabezadoClienteModel()
		{
			
		} 
		/*[Bindable]
		public function set arrayTitularAdiscionales(arrayTitularAdiscionales:ArrayCollection):void{
		
			
			if(arrayTitularAdiscionales){
				
				
				
				_arrayMailTitularAdicional = new ArrayCollection();
				
				for each (var element:ClienteTransaccion in arrayTitularAdiscionales){
					for each (var mail:Email in element.individuo.mails){
												
						_arrayMailTitularAdicional.addItem(mail.email);
						
						_arrayMailTitularAdicional.refresh();
						
					}
				}
			}
		} 
		[Bindable]
		public function get arrayTitularAdiscionales():ArrayCollection{
			return _arrayMailTitularAdicional;
		}*/
		
	public function armarFiltro(nroDoc:String=null, nroCuenta:String=null, cuit:String=null, apellido:String=null,nombre:String=null,general:String=null):void{
			this.filtro.campos = new Array();
			this.filtro.operadores = new Array();
			this.filtro.valores = new Array();
			this.filtro.join = new Array();
			this.filtro.funcion = " ";
			
			if(nroDoc.length != 0){
				this.filtro.campos.push("individuo.nroDocumento");
				this.filtro.operadores.push(Filtro.LIKE);
				this.filtro.valores.push(nroDoc);
			}
			
			if(nroCuenta.length != 0){
				this.filtro.campos.push("idCliente");
				this.filtro.operadores.push(Filtro.IGUAL);
				this.filtro.valores.push(Number(nroCuenta));
			}
			
			if(cuit.length != 0){
				this.filtro.campos.push("individuo.cuil");
				this.filtro.operadores.push(Filtro.LIKE);
				this.filtro.valores.push(cuit);
			}			
			
			
			if(apellido.length != 0){
				this.filtro.campos.push("individuo.apellido");
				this.filtro.operadores.push(Filtro.LIKE_DER);
				this.filtro.valores.push(apellido);
			}				
			
			if(nombre.length != 0){
				this.filtro.campos.push("individuo.nombres");
				this.filtro.operadores.push(Filtro.LIKE_DER);
				this.filtro.valores.push(nombre);
			}
			
				
			
			
			if(general.length != 0){
				/*this.filtro.join.push("obj.individuo.mails gmails ");
				this.filtro.join.push("obj.individuo.telefonos gtelefonos ");
				this.filtro.join.push("mails.email hemail ");
				this.filtro.join.push("telefonos.telefono htelefono ");
				this.filtro.join.push("obj.plasticoClienteSet gplasticos ");*/
				
				
			/*	this.filtro.campos.push("gmails.email.email");
				this.filtro.operadores.push(Filtro.LIKE);
				this.filtro.valores.push(general);
				
				this.filtro.campos.push("gtelefonos.telefono.nroTlefono");
				this.filtro.operadores.push(Filtro.LIKE);
				this.filtro.valores.push(general); */
				
				
				
				//filtro.funcion = "  (SELECT DISTINCT obj1 FROM ClienteTransaccion obj1 left join obj1.individuo.mails gmails left join obj1.individuo.telefonos gtelefonos  Where gmails.email.email  LIKE '%" + general + "%' or  gtelefonos.telefono.nroTlefono  LIKE '%" + general + "%')  " ;
			//	this.filtro.funcion = " and  (gmails.email.email  LIKE '%" + general + "%')  or  htelefono.nroTlefono  LIKE '%" + general + "%' or gplasticos.numero LIKE '%" + general + "%' ) " ;
				
			 filtro.funcion = " and	(obj.idCliente in (select obj1.idCliente from ClienteTransaccion obj1  inner join obj1.plasticoClienteSet gplasticos " +
				 " where gplasticos.numero LIKE '%" + general + "%' ) " +
				 " or (obj.idCliente in (select obj1.idCliente from ClienteTransaccion obj1 inner join obj1.individuo.mails gmails where gmails.email.email  LIKE '%" + general + "%')) " +
				 " or (obj.idCliente in (select obj1.idCliente from ClienteTransaccion obj1 inner join obj1.individuo.telefonos gtelefonos  where gtelefonos.telefono.nroTlefono  LIKE '%" + general + "%' )))";
				 
					
			}
			
			
		}
	
	/**
	 * @id 3945
	 * abre el popUp para modificar el telefono
	 **/
	public function abrirPopUpModificarTelefono():void{
		//llamo al popUp agregar Telefono
		popUpModificarTelefono.encabezadoClienteModel = this;
		//ComboBox(event.target).selectedItem.nombre;
		//popUpModificarTelefono.cmbTipoTel.selectedItem.descripcion = descripcion;
		PopUpManager.addPopUp(popUpModificarTelefono,Application.application as  DisplayObject, true);
		PopUpManager.centerPopUp(popUpModificarTelefono);	
	}
	
	/**
	 * @id 100
	 * lanza el evento para modificar el telefono
	 **/
	public function modificarTelefono(idTelefono:Number,tel:Telefono,idIndividuo:Number):void{
		//agrega el nuevo mail al individuo actual
		var evt:ClienteEvent;
		evt = new ClienteEvent(ClienteEvent.MODIFICAR_TELEFONO);
		evt.telefono = tel;
		evt.idIndividuo = idIndividuo;
		evt.idTelefono = idTelefono;
		dispatcher.dispatchEvent(evt);
		
		ControlBlock.getInstance().add();
	}
	
	/**
	 * @id 100
	 * abre el popUp para ver el telefono laboral
	 **/
	public function abrirPopUpModificarTelefonoLaboral():void{
		//llamo al popUp agregar Telefono
		popUpModificarTelefonoLaboral.encabezadoClienteModel = this;
		//ComboBox(event.target).selectedItem.nombre;
		//popUpModificarTelefono.cmbTipoTel.selectedItem.descripcion = descripcion;
		PopUpManager.addPopUp(popUpModificarTelefonoLaboral,Application.application as  DisplayObject, true);
		PopUpManager.centerPopUp(popUpModificarTelefonoLaboral);	
	}
	
	/**
	 * @id 100
	 * abre el popUp para agregar el telefono
	 **/
	public function abrirPopUpAgregarTelefonoLaboral():void{
		//llamo al popUp agregar Telefono
		popUpAgregarTelefonoLaboral.encabezadoClienteModel = this;
		PopUpManager.addPopUp(popUpAgregarTelefonoLaboral,Application.application as  DisplayObject, true);
		PopUpManager.centerPopUp(popUpAgregarTelefonoLaboral);	
	}
	
	/**
	 * @id 100
	 * lanza en evento para eliminar telefono
	 **/
	public function eliminarTelefono(idTelefono:Number,idIndividuo:Number):void{
		
		//agrega el nuevo mail al individuo actual
		var evt:ClienteEvent;
		evt = new ClienteEvent(ClienteEvent.ELIMINAR_TELEFONO);
		evt.idIndividuo = idIndividuo;
		evt.idTelefono = idTelefono;
		
		
		dispatcher.dispatchEvent(evt);
		
		ControlBlock.getInstance().add();
		
	}
	
	/**
	 * @id 100
	 * lanza el evento para agregar el telefono
	 **/
	public function agregarTelefonoLaboral(tel:Telefono,idSucEmpresa:Number,idIndividuo:Number):void{
		//agrega el nuevo mail al individuo actual
		var evt:ClienteEvent;
		evt = new ClienteEvent(ClienteEvent.AGREGAR_TELEFONO_LABORAL);
		evt.telefono = tel;
		evt.idSucEmpresa = idSucEmpresa;
		evt.idIndividuo = idIndividuo;
		
		dispatcher.dispatchEvent(evt);
		
		ControlBlock.getInstance().add();
	}
	
	 /*@I35*/		/**
	 * @id 35
	 * abre el popUp para agregar el mail
	 **/
	public function abrirPopUpModicarMail():void{
		//llamo al popUp agregar mail
		popUpModificarMail.encabezadoClienteModel= this;
		PopUpManager.addPopUp(popUpModificarMail,Application.application as  DisplayObject, true);
		PopUpManager.centerPopUp(popUpModificarMail);	
	} 
	
	/**
	 * @id 35
	 * lanza en evento para modificar el mail
	 **/
	public function eliminarMail(idMail:Number,idIndividuo:Number):void{
		//agrega el nuevo mail al individuo actual
		var evt:ClienteEvent;
		evt = new ClienteEvent(ClienteEvent.ELIMINAR_EMAIL);
		evt.idIndividuo = idIndividuo;
		evt.idMail = idMail;
		
		
		dispatcher.dispatchEvent(evt);
		
		ControlBlock.getInstance().add();
		return;
	}
	
	/**
	 * @id 3945
	 * abre el popUp para agregar el mail
	 **/
	public function abrirPopUpAgregarMail():void{
		//llamo al popUp agregar mail
		popUpAgregarMail.encabezadoClienteModel = this;
		PopUpManager.addPopUp(popUpAgregarMail,Application.application as  DisplayObject, true);
		PopUpManager.centerPopUp(popUpAgregarMail);	
	}
	
	/**
	 * @id 3945
	 * lanza en evento para agregar el mail
	 **/
	public function agregarMail(mail:Email,idIndividuo:Number):void{
		//agrega el nuevo mail al individuo actual
		var evt:ClienteEvent;
		evt = new ClienteEvent(ClienteEvent.AGREGAR_EMAIL);
		evt.email = mail;
		evt.idIndividuo = idIndividuo;
		
		dispatcher.dispatchEvent(evt);
		
		ControlBlock.getInstance().add();
		return;
	}
	
	/**
	 * @id 35
	 * lanza en evento para modificar el mail
	 **/
	public function modificarMail(idMail:Number,mail:Email,idIndividuo:Number):void{
		//agrega el nuevo mail al individuo actual
		var evt:ClienteEvent;
		evt = new ClienteEvent(ClienteEvent.MODIFICAR_EMAIL);
		evt.email = mail;
		evt.idIndividuo = idIndividuo;
		evt.idMail = idMail;
		
		
		dispatcher.dispatchEvent(evt);
		
		ControlBlock.getInstance().add();
		return;
	}
	
	
		public function funDesbloquearPago(idCliente:Number):void {
			var evt:ClienteEvent = new ClienteEvent(ClienteEvent.DESBLOQUEAR_PAGO);
			evt.idCliente =  idCliente;
			this.dispatcher.dispatchEvent(evt);
		}
		
		public function funDesbloquearApp(idCliente:Number,operador:Number):void {
			var evt:ClienteEvent = new ClienteEvent(ClienteEvent.DESBLOQUEAR_APP);
			evt.idCliente =  idCliente;
			evt.operador = operador;
			this.dispatcher.dispatchEvent(evt);
		}
		
		/**
		 * @id 7469
		 * realizar_baja_revista
		 **/	
		public function realizar_baja_revista(operador:Number):void{
			var evt:ClienteEvent = new ClienteEvent(ClienteEvent.REVISTA_BAJA);
			evt.operador = operador;
			this.dispatcher.dispatchEvent(evt);	
		}
		
		
		public function realizar_desbloque_pagomin(operador:Number):void{
			var evt:ClienteEvent = new ClienteEvent(ClienteEvent.DESBLOQUEO_PAGOMINIMO);
			evt.operador = operador;
			this.dispatcher.dispatchEvent(evt);	
		}
		
		public function buscarClientesListado(paginador:Paginador):void{
		var evt:ClienteEvent = new ClienteEvent(ClienteEvent.BUSCAR_CLIENTES_PARM);
		evt.filtro = filtro;
		evt.paginador =  paginador; 
		this.dispatcher.dispatchEvent(evt);
		ControlBlock.getInstance().add();
		}
		/**
		 * @id 37
		 * realizar_cambio de envio resumen
		 **/	
		public function realizar_envio_resumen(operador:Number):void{
			var evt:ClienteEvent = new ClienteEvent(ClienteEvent.REALIZAR_ENVIO_RESUMEN);
			evt.operador = operador;
			this.dispatcher.dispatchEvent(evt);	
		}
		
		/**
		 * @id 3945
		 * lanza el evento para cargar el combo de TipoTelefono
		 **/
		public function cargarCmbTipoTel():void {			
			
			if(this.arrayTiposTelefono == null || this.arrayTiposTelefono.length == 0)
			{
				var evt:ClienteEvent;
				evt = new ClienteEvent(ClienteEvent.CARGAR_CMB_TIPO_TEL);
				
				dispatcher.dispatchEvent(evt);
				
				ControlBlock.getInstance().add();
			}
		/*@F3945*/		}
		
		/**
		 * @id 3945
		 * lanza el evento para agregar el telefono
		 **/
		public function agregarTelefono(tel:Telefono,idIndividuo:Number):void{
			//agrega el nuevo mail al individuo actual
			var evt:ClienteEvent;
			evt = new ClienteEvent(ClienteEvent.AGREGAR_TELEFONO);
			evt.telefono = tel;
			evt.idIndividuo = idIndividuo;
			
			dispatcher.dispatchEvent(evt);
			
			ControlBlock.getInstance().add();
		}
		
		/**
		 * @id 3945
		 * abre el popUp para agregar el telefono
		 **/
		public function abrirPopUpAgregarTelefono():void{
			//llamo al popUp agregar Telefono
			popUpAgregarTelefono.encabezadoClienteModel= this;
			PopUpManager.addPopUp(popUpAgregarTelefono,Application.application as  DisplayObject, true);
			PopUpManager.centerPopUp(popUpAgregarTelefono);	
		}
		
		/**
		 * @id 8156
		 * realizar_bloqueoDesbloqueo
		 **/	
		public function realizar_bloqueoDesbloqueo(operador:Number):void{
			var evt:ClienteEvent = new ClienteEvent(ClienteEvent.BLOQUEO_DESBLOQUEO);
			evt.operador = operador;
			this.dispatcher.dispatchEvent(evt);	
		}


	public function buscarCliente(paginador:Paginador,paramValue:String=null,tipoBusqueda:String=null,nuevoTipoCliente:ClienteTransaccion=null):void{
			 var evt:ClienteEvent; 
			 
	 			 
			 if(paramValue != null){
			 	_paramValue = paramValue;
			 }
			 
			 if(tipoBusqueda != null){
			 	_tipoBusqueda = tipoBusqueda;
			 }
			 
			switch (_tipoBusqueda){
				 
           	 case ClienteManager.BUSQ_POR_DNI:
                evt = new ClienteEvent(ClienteEvent.BUSCAR_CLIENTES_LIST);
                evt.paramBusqueda = _paramValue;
                evt.tipoBusqueda= ClienteManager.BUSQ_POR_DNI; 
                evt.paginador= paginador;
                dispatcher.dispatchEvent(evt)
		        break; 
             case ClienteManager.BUSQ_POR_PLASTICO:
                /*var evt:ClienteEvent = new ClienteEvent(ClienteEvent.BUSCAR_POR_PLASTICO);
                evt.nroTarjeta = paramValue;
		        evt.codSeguridad = null; 
		        dispatcher.dispatchEvent(evt);*/
		        evt= new ClienteEvent(ClienteEvent.BUSCAR_TITULAR_ADICIONALES);
                evt.paramBusqueda = _paramValue;
                evt.tipoBusqueda=ClienteManager.BUSQ_POR_PLASTICO
                dispatcher.dispatchEvent(evt)
		        break;
             case ClienteManager.BUSQ_POR_GENERAL: 
                evt = new ClienteEvent(ClienteEvent.BUSCAR_CLIENTES_LIST);
                evt.paramBusqueda = _paramValue;
                evt.tipoBusqueda= ClienteManager.BUSQ_POR_GENERAL; 
                evt.paginador= paginador;
                dispatcher.dispatchEvent(evt)
               break;
			 case ClienteManager.BUSQ_POR_APELLIDO: 
				 evt = new ClienteEvent(ClienteEvent.BUSCAR_CLIENTES_LIST);
				 evt.paramBusqueda = _paramValue;
				 evt.tipoBusqueda= ClienteManager.BUSQ_POR_APELLIDO; 
				 evt.paginador= paginador;
				 dispatcher.dispatchEvent(evt)
				 break;
             case  ClienteManager.BUSQ_POR_CUIL: 
                evt = new ClienteEvent(ClienteEvent.BUSCAR_TITULAR_ADICIONALES);
                evt.paramBusqueda = _paramValue;
                evt.tipoBusqueda= ClienteManager.BUSQ_POR_CUIL; 
                dispatcher.dispatchEvent(evt)
		        break;
             case ClienteManager.BUSQ_POR_NRO_CUENTA: 
                evt = new ClienteEvent(ClienteEvent.BUSCAR_TITULAR_ADICIONALES);
                evt.paramBusqueda = _paramValue;
                evt.tipoBusqueda= ClienteManager.BUSQ_POR_NRO_CUENTA;
				evt.nuevoTipoCliente = nuevoTipoCliente;
                dispatcher.dispatchEvent(evt)				
               break;
               
             case ClienteManager.BUSQ_POR_SIT_COMERCIAL: 
                evt = new ClienteEvent(ClienteEvent.BUSCAR_TITULAR_ADICIONALES);
                evt.paramBusqueda = _paramValue;
                evt.tipoBusqueda= ClienteManager.BUSQ_POR_NRO_CUENTA; 
                evt.paginador= paginador;
                dispatcher.dispatchEvent(evt)
               break;
               
             case ClienteManager.BUSQ_POR_SIT_COMERCIAL: 
                evt = new ClienteEvent(ClienteEvent.BUSCAR_TITULAR_ADICIONALES);
                evt.paramBusqueda = _paramValue;
                evt.tipoBusqueda= ClienteManager.BUSQ_POR_NRO_CUENTA; 
                evt.paginador= paginador;
                dispatcher.dispatchEvent(evt)
				
               break; 
              
			 //ModelLocatorGeneral.getInstance().clienteSeleccionado.
            
                 
		}
		/*	var evt:ClienteEvent = new ClienteEvent(ClienteEvent.BUSCAR_POR_PLASTICO);
			evt.nroTarjeta = plastico;
			evt.codSeguridad = cvv;
			nroPlastico =plastico;
			dispatcher.dispatchEvent(evt);*/
			
			  ControlBlock.getInstance().add();
		}	
		
		
			public function set cliente(cli:ClienteTransaccion):void{
			_cliente = cli;
			
			if(_cliente){
				if (cli.nuevoTipoCliente == "Garante") {
					nombreCliente = cli.apellidoConsulta+ ", "+cli.nombreConsulta;
				} else {
					nombreCliente = cli.individuo.apellido+ ", "+cli.individuo.nombres;	
				}			   
			  // mostrarPanelCliente = true;
	     	}
	  }
		
		public function get cliente():ClienteTransaccion{
			return _cliente;
		}
		
		
		
	  public function set titular(tit:ClienteTransaccion):void{
			_titular = tit;
			ModelLocatorGeneral.getInstance().clienteSeleccionado.setearTitular(titular);
	  }  	
		
		public function get titular():ClienteTransaccion{
			return _titular;
		}
		
		public function setearClienteSeleccionado(cliente:ClienteTransaccion):void
		{
			if (ModelLocatorGeneral != null)
			{
				ModelLocatorGeneral.getInstance().clienteSeleccionado.setearCliente(cliente);
				
				
			}
		}
		
		public function get clienteEncontrado():ClienteTransaccion{
			return _clienteEncontrado;
		}
		
		public function set clienteEncontrado(cli:ClienteTransaccion):void{
			_clienteEncontrado = cli;
			if(_clienteEncontrado){
				if (cli.nuevoTipoCliente == "Garante") {
					nombreCliente = cli.apellidoConsulta+ ", "+cli.nombreConsulta;
				} else {
					nombreCliente = cli.individuo.apellido+ ", "+cli.individuo.nombres;	
				}	
				setearClienteSeleccionado(cli);
			  // mostrarPanelCliente = true;
	     	}
	   }  	
		
		
		
	 
       public function set arrayClientes(arrayCli:ArrayCollection):void{
			_arrayClientes = arrayCli;
			if(arrayClientes.length >1){
				if(paginador.pagina == 0){
					PopUpManager.addPopUp(popUpMostrarClientes,Application.application as  DisplayObject, true);
	        		PopUpManager.centerPopUp(popUpMostrarClientes);	
				}								
	        	popUpMostrarClientes.paginador.paginacionCompleta(paginador);	        	
			} 
			      
	   }  	 
		
		public function get arrayClientes():ArrayCollection{
				return _arrayClientes;
		}
			
			
		public function set clienteSeleccionado(cli:ClienteTransaccion):void{
				
				_clienteSeleccionado = cli;
				setearClienteSeleccionado(cli);
				if (cli.nuevoTipoCliente == "Garante") {
					nombreCliente = cli.apellidoConsulta+ ", "+cli.nombreConsulta;
				} else {
					nombreCliente = cli.individuo.apellido+ ", "+cli.individuo.nombres;	
				}
				
				
				   
				dispatchEvent(new Event("changedClienteSeleccionado"));	
		} 	
		   	
		public function seleccionarCliente(cli:ClienteTransaccion,paginador:Paginador,buscarPor:String):void{
		   		if(cli){
					clienteSeleccionado = cli;
					setearClienteSeleccionado(cli);
					buscarCliente(paginador,_clienteSeleccionado.idCliente.toString(), buscarPor,_clienteSeleccionado);	       		
					PopUpManager.removePopUp(popUpMostrarClientes);
			 	}	
		}
			
		public function seleccionarClienteArregla(cli:ClienteTransaccion,paginador:Paginador,buscarPor:String):void{
				if(cli){
					clienteSeleccionado = cli;
					setearClienteSeleccionado(cli);
					buscarCliente(paginador,_clienteSeleccionado.idCliente.toString(), buscarPor,_clienteSeleccionado);	
				}	
		}
		   	
		[Bindable (event="changedClienteSeleccionado")]
		public function get clienteSeleccionado():ClienteTransaccion{
			return _clienteSeleccionado;
		}
					
			
		public function get arrayLineaTemporal():ArrayCollection
			{
				return _arrayLineaTemporal;
		}
			
		public function set arrayLineaTemporal(value:ArrayCollection):void
			{
				_arrayLineaTemporal = value;
		}	
		
		
	}
}