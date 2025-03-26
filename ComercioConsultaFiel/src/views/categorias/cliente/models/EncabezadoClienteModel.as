package views.categorias.cliente.models
{
	import com.tarjetafiel.caja.vo.ClienteTransaccion;
	import com.tarjetafiel.proveedorconexion.model.ModelLocatorGeneral;
	import com.util.block.ControlBlock;
	import com.util.paginacion.Paginador;
	
	import events.ClienteEvent;
	
	import flash.display.DisplayObject;
	import flash.events.Event;
	
	import managers.ClienteManager;
	
	import mx.collections.ArrayCollection;
	import mx.core.Application;
	import mx.managers.PopUpManager;
	import mx.controls.Alert;
	
	
	
	import views.categorias.cliente.popup.MostrarClientesView;
	import com.tarjetafiel.caja.vo.util.Filtro;

	[Bindable]
	public class EncabezadoClienteModel extends  BaseModel
	{
	    private var _cliente:ClienteTransaccion;
		private var _clienteEncontrado:ClienteTransaccion;
		private var _titular:ClienteTransaccion;
		private var _arrayClientes:ArrayCollection;
		public var nombreCliente :  String =""; 
		public var popUpMostrarClientes:MostrarClientesView = new MostrarClientesView();
		private var _clienteSeleccionado:ClienteTransaccion;
		public var paginador:Paginador;
		private var _tipoBusqueda:String;
		private var _paramValue:String;
		
		
		private var filtro:Filtro = new Filtro();
		import events.ClienteManagerEvent;
		
		public function EncabezadoClienteModel()
		{
			
		}
		//txtDNI.text, txtNroCuenta.text, txtCUIL.text, txtApellido.text,  txtNombre.text,  txtGeneral.text)
		
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
		
		public function buscarClientesListado(paginador:Paginador):void{
		var evt:ClienteEvent = new ClienteEvent(ClienteEvent.BUSCAR_CLIENTES_PARM);
		evt.filtro = filtro;
		evt.paginador =  paginador; 
		this.dispatcher.dispatchEvent(evt);
		ControlBlock.getInstance().add();
		
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
	   	
		[Bindable (event="changedClienteSeleccionado")]
		public function get clienteSeleccionado():ClienteTransaccion{
			return _clienteSeleccionado;
		}
		
		
		
	}
}