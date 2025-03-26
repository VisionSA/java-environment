package modules.pagofacil
{
	import com.tarjetafiel.caja.vo.ArchivoFarmacia;
	import com.tarjetafiel.caja.vo.ClienteTransaccion;
	import com.tarjetafiel.caja.vo.CodComercio;
	import com.tarjetafiel.caja.vo.Provincia;
	import com.tarjetafiel.caja.vo.util.Filtro;
	import com.util.block.ControlBlock;
	import com.util.components.alert.AlertError;
	import com.util.components.alert.AlertOk;
	import com.util.paginacion.Paginador;
	
	import flash.events.Event;
	import flash.events.IEventDispatcher;
	import flash.net.URLRequest;
	import flash.net.navigateToURL;
	
	import mx.collections.ArrayCollection;
	import mx.collections.Sort;
	import mx.collections.SortField;
	import mx.rpc.Fault;
	
	import vo.ArchivoCobroExternoNegocio;
	import vo.ReclamoCabecera;
	import vo.ReclamoDetalle;
	import vo.ReclamoIndividuo;
	import vo.ReclamoTipos;
	
	import flash.net.FileReference;
	import flash.utils.ByteArray;
	
	[Bindable]
	public class Manager
	{
		public var dispatcher:IEventDispatcher;
		
		public var archivosList:ArrayCollection;
		
		public var archivosList1:ArrayCollection  = new ArrayCollection();
		
		public var listaTipoCobro:ArrayCollection;
		
		public var buscaReclamoCabecera:ReclamoCabecera;
		

		
/*@F8683*/		public var listaProvincias:ArrayCollection;

/*@F8683*/		public var listaPartidos:ArrayCollection;

/*@F8683*/		public var listaLocalidades:ArrayCollection;

/*@F8683*/		public var listaBarrios:ArrayCollection;

/*@F8683*/		public var clienteDeclarante:ClienteTransaccion;

/*@F8683*/		public var clienteInteresado:ClienteTransaccion;

/*@F8683*/		public var ListReclamoTipos:ArrayCollection;

/*@F8683*/		public var ListReclamoEstados:ArrayCollection;

/*@F8683*/		public var ListReclamoResolucion:ArrayCollection;

/*@F8683*/		public var listarReclamoDetalle:ArrayCollection;

/*@F8683*/		public var listarReclamoCanales:ArrayCollection;

/*@F8683*/		public var listarReclamoSucursales:ArrayCollection;


/*@F8683*/		public var listarReclamoAdjuntos:ArrayCollection;

/*@F8683*/ public var buscaIndividuo:ReclamoIndividuo;


/*@F4*/		public var listarReclamoDoc:ArrayCollection;

/*@F6*/		public var listarCodigoPosnet:CodComercio;


 		public var ListReclamoTiposTop:ArrayCollection;
		
		public var ListLugarPlasticos:ArrayCollection;		
		
		public var ListObservacionPlasticos:ArrayCollection;


		
		public var listaCuentasCobradas:ArrayCollection;
		
		public var archivosFarmaciaList:ArrayCollection;
		
		private var filtro:Filtro;
		
		public var idReclamoNuevo:String;
		
		
		public var paginador:Paginador;
		
		public var paginadoFarmacia:Paginador;
		
		private var fechaDesde:Date;
		
		public var fechaFarmaciaDesde:Date;
		
		private var archivo:ArchivoCobroExternoNegocio;	
		
		private var archivoFarmacia:ArchivoFarmacia;
		
		private var oFile : FileReference;
		
/*I4170*/	public var fechaBuscar:Date = new Date();

/* @I4662 */	public var fechaServidor:String;

/* @I6*/  public var modoTotal:int = 3;
/* @I6*/  public var modoTipoReclamo:int = 0;

public var lugarDescripcion:String;	
		
		public function Manager()
		{
			 
		}
/* @I4662 */
		
		/* I4662 Obtener la fecha del servidor */
		public function getFechaServidor():void{
			/* AlertOk.show("procesarListaTipoCobro"); */
			var evt:ProcesarPagoEvent = new ProcesarPagoEvent(ProcesarPagoEvent.OBTENER_FECHA_SERVIDOR);
			dispatcher.dispatchEvent(evt);
			ControlBlock.getInstance().add();
		}
		
		public function envioMail(mail:String,sujeto:String,texto:String):void{
			/* AlertOk.show("procesarListaTipoCobro"); */
			var evt:ProcesarPagoEvent = new ProcesarPagoEvent(ProcesarPagoEvent.PROCESAR_ENVIO_MAIL);
			evt.mail = mail;
			evt.texto = texto;
			evt.sujeto = sujeto;
			dispatcher.dispatchEvent(evt);
			ControlBlock.getInstance().add();
		}
		
		
		public function imprimirReclamoInicio(data:Number,destino:Number):void{
			/* AlertOk.show("procesarListaTipoCobro"); */
			var evt:ProcesarPagoEvent = new ProcesarPagoEvent(ProcesarPagoEvent.PROCESAR_IMPRIMIR_RECLAMO);
			evt.idReclamo = data;
			evt.destino = destino;
			dispatcher.dispatchEvent(evt);
			//ControlBlock.getInstance().add();			
		}
		
		public function procesoSubirArchivoPdf(data:ByteArray):void{
			/* AlertOk.show("procesarListaTipoCobro"); */
			var evt:ProcesarPagoEvent = new ProcesarPagoEvent(ProcesarPagoEvent.PROCESAR_SUBIR_PDF_IMAGEN);
			evt.archivoPdf = data;			
			dispatcher.dispatchEvent(evt);
			//ControlBlock.getInstance().add();			
		}
		
		
		/*public function archivoPdf(bytes:ByteArray):void {
			oFile = new FileReference();
			
			oFile.save(bytes, "actionscript-facile.pdf");
		} */
		
		
		public function imprimirReclamoFin(data:Number,destino:Number):void{
			/* AlertOk.show("procesarListaTipoCobro"); */
			var evt:ProcesarPagoEvent = new ProcesarPagoEvent(ProcesarPagoEvent.PROCESAR_IMPRIMIR_RECLAMO_FIN);
			evt.idReclamo = data;
			evt.destino = destino;
			dispatcher.dispatchEvent(evt);
			//ControlBlock.getInstance().add();			
		}
		
		
		public function procesarListarCuentas(idArchivo:Number):void{
			/* AlertOk.show("procesarListaTipoCobro"); */
			var evt:ProcesarPagoEvent = new ProcesarPagoEvent(ProcesarPagoEvent.PROCESAR_LISTA_CUENTAS);
			evt.idArchivo = idArchivo;
			dispatcher.dispatchEvent(evt);
			ControlBlock.getInstance().add();
		}
		
		public function procesarListaTipoCobro():void{
			/* AlertOk.show("procesarListaTipoCobro"); */
			var evt:ProcesarPagoEvent = new ProcesarPagoEvent(ProcesarPagoEvent.PROCESAR_LISTA_TIPO_COBRO);
			dispatcher.dispatchEvent(evt);
			ControlBlock.getInstance().add();
		}
	
		/* I8683 procesarListaProvincias */
		public function procesarListaProvincias():void{
			/* AlertOk.show("procesarListaTipoCobro"); */
			var evt:ProcesarPagoEvent = new ProcesarPagoEvent(ProcesarPagoEvent.PROCESAR_LISTA_PROVINCIAS);
			dispatcher.dispatchEvent(evt);
			ControlBlock.getInstance().add();
		}
		
		/* I8683 procesarListaCanales */
		public function procesarListaCanales():void{
			/* AlertOk.show("procesarListaTipoCobro"); */
			var evt:ProcesarPagoEvent = new ProcesarPagoEvent(ProcesarPagoEvent.PROCESAR_LISTA_CANALES);
			dispatcher.dispatchEvent(evt);
			ControlBlock.getInstance().add();
		}
		
		/* I8683 procesarListaCanales */
		public function procesarListaReclamoDoc(data:Number):void{
			/* AlertOk.show("procesarListaTipoCobro"); */
			var evt:ProcesarPagoEvent = new ProcesarPagoEvent(ProcesarPagoEvent.PROCESAR_LISTA_RECLAMO_DOC);
			evt.idReclamo = data;
			dispatcher.dispatchEvent(evt);
			ControlBlock.getInstance().add();
		}
		
		
		/* I8683 procesarListaSucursales */
		public function procesarListaSucursales():void{
			/* AlertOk.show("procesarListaTipoCobro"); */
			var evt:ProcesarPagoEvent = new ProcesarPagoEvent(ProcesarPagoEvent.PROCESAR_LISTA_SUCURSALES);
			dispatcher.dispatchEvent(evt);
			ControlBlock.getInstance().add();
		}
		
		/* I8683 procesarListaReclamoTipos */
		public function procesarListaReclamoTipos(tipoDestino:int):void{
			/* AlertOk.show("procesarListaTipoCobro"); */
			var evt:ProcesarPagoEvent = new ProcesarPagoEvent(ProcesarPagoEvent.PROCESAR_LISTA_RECLAMOS_TIPOS);
			evt.tipoDestino = tipoDestino;
			dispatcher.dispatchEvent(evt);
			ControlBlock.getInstance().add();
		}
		
		/* I8683 procesarListaReclamoTipos */
		public function procesarListaReclamoTiposTop():void{
			/* AlertOk.show("procesarListaTipoCobro"); */
			var evt:ProcesarPagoEvent = new ProcesarPagoEvent(ProcesarPagoEvent.PROCESAR_LISTA_LUGAR_PLASTICO);
			dispatcher.dispatchEvent(evt);
			ControlBlock.getInstance().add();
		}
		
		
		public function procesarListaObservacionPlastico():void{
			/* AlertOk.show("procesarListaTipoCobro"); */
			var evt:ProcesarPagoEvent = new ProcesarPagoEvent(ProcesarPagoEvent.PROCESAR_LISTA_OBSERVACION_PLASTICO);
			dispatcher.dispatchEvent(evt);
			ControlBlock.getInstance().add();
		}
		
		
		
		
		/* I8683 procesarListaReclamoEstados */
		public function procesarListaReclamoEstados():void{
			/* AlertOk.show("procesarListaTipoCobro"); */
			var evt:ProcesarPagoEvent = new ProcesarPagoEvent(ProcesarPagoEvent.PROCESAR_LISTA_RECLAMOS_ESTADOS);
			dispatcher.dispatchEvent(evt);
			ControlBlock.getInstance().add();
		}
		
		/* I8683 procesarListaReclamoResolucion */
		public function procesarListaReclamoResolucion():void{
			/* AlertOk.show("procesarListaTipoCobro"); */
			var evt:ProcesarPagoEvent = new ProcesarPagoEvent(ProcesarPagoEvent.PROCESAR_LISTA_RECLAMOS_RESOLUCION);
			dispatcher.dispatchEvent(evt);
			ControlBlock.getInstance().add();
		}
		
		
		/* I8683 procesarListaPartido */
		public function procesarListaPartido(data:Provincia):void{
			var evt:ProcesarPagoEvent = new ProcesarPagoEvent(ProcesarPagoEvent.PROCESAR_LISTAR_PARTIDOS);
			evt.provincia = data;
			dispatcher.dispatchEvent(evt);
			ControlBlock.getInstance().add();
		}
		
		/* I8683 procesarBuscarReclamo */
		public function procesarBuscarReclamo(data:Number):void{
						var evt:ProcesarPagoEvent = new ProcesarPagoEvent(ProcesarPagoEvent.PROCESAR_BUSCAR_RECLAMO);
			evt.idReclamo = data;
			dispatcher.dispatchEvent(evt);
			ControlBlock.getInstance().add();
		}
		
		/* I8683 procesarListaLocalidad */
		public function procesarListaLocalidad(bPartido:Number):void{
			var evt:ProcesarPagoEvent = new ProcesarPagoEvent(ProcesarPagoEvent.PROCESAR_LISTAR_LOCALIDADES);
			evt.idPartido = bPartido;
			dispatcher.dispatchEvent(evt);
			ControlBlock.getInstance().add();
		}
		
		/* I8683 procesarListaBarrio */
		public function procesarListaBarrio(bLocalidad:Number):void{
			var evt:ProcesarPagoEvent = new ProcesarPagoEvent(ProcesarPagoEvent.PROCESAR_LISTAR_BARRIOS);
			evt.idLocalidad = bLocalidad;
			dispatcher.dispatchEvent(evt);
			ControlBlock.getInstance().add();
		}
		
		/* I8683 grabarReclamo */
		public function grabarReclamo(archivoReclamo:ReclamoCabecera,reclamoDetalle:ReclamoDetalle,subject:String,msq:String,msq1:String):void{
			var evt:ProcesarPagoEvent = new ProcesarPagoEvent(ProcesarPagoEvent.GRABAR_RECLAMO);
			evt.archivoReclamo = archivoReclamo;
			evt.reclamoDetalle = reclamoDetalle;
			evt.sujeto = subject;
			evt.msq = msq;
			evt.msq1 = msq1;
			dispatcher.dispatchEvent(evt);
			ControlBlock.getInstance().add();
		}
		
		/* I8683 updateReclamo */
		public function updateReclamo(archivoReclamo:ReclamoCabecera,reclamoDetalle:ReclamoDetalle,subject:String,msq:String):void{
			var evt:ProcesarPagoEvent = new ProcesarPagoEvent(ProcesarPagoEvent.UPDATE_RECLAMO);
			evt.archivoReclamo = archivoReclamo;
			evt.reclamoDetalle = reclamoDetalle;
			evt.sujeto = subject;
			evt.msq = msq;			
			dispatcher.dispatchEvent(evt);
			ControlBlock.getInstance().add();
		}
		
		
		/* I6 procesarBuscarComercio */
		public function procesarBuscarComercio(codComercio:String):void{
			
			var filtro:Filtro = new Filtro();	
			filtro.campos.push("codigoPosnet");
			filtro.operadores.push(Filtro.IGUAL);
			filtro.valores.push(codComercio);
			var evt:ProcesarPagoEvent = new ProcesarPagoEvent(ProcesarPagoEvent.PROCESAR_BUSCAR_COMERCIO);
			evt.filtro = filtro;
			dispatcher.dispatchEvent(evt);
			ControlBlock.getInstance().add();
		}
		
		
		
		
		public function procesarClienteInteresado(documento:String):void{
		
		var filtro:Filtro = new Filtro();	
		filtro.campos.push("individuo.nroDocumento");
		filtro.operadores.push(Filtro.IGUAL);
		filtro.valores.push(documento);
		var evt:ProcesarPagoEvent = new ProcesarPagoEvent(ProcesarPagoEvent.PROCESAR_BUSCARCLIENTE_INTERESADO);
		evt.filtro = filtro;
		dispatcher.dispatchEvent(evt);
		ControlBlock.getInstance().add();
		}
		
		
		public function procesarClienteDeclarante(documento:String):void{
			
			var filtro:Filtro = new Filtro();	
			filtro.campos.push("individuo.nroDocumento");
			filtro.operadores.push(Filtro.IGUAL);
			filtro.valores.push(documento);
			var evt:ProcesarPagoEvent = new ProcesarPagoEvent(ProcesarPagoEvent.PROCESAR_BUSCARCLIENTE_DECLARANTE);
			evt.filtro = filtro;
			dispatcher.dispatchEvent(evt);
			ControlBlock.getInstance().add();
		}
		
		
		
		
		
		/*public function operadorLogueado(result:Operador):void{
			operador = result;
			ControlBlock.getInstance().remove();
			dispatcher.dispatchEvent(new ProcesarPagoEvent(ProcesarPagoEvent.OPERADOR_LOGUEADO));
			
		}*/
		
		
		
		public function procesarArchivo(data:ArchivoCobroExternoNegocio):void{
			archivo = data; 
			var evt:ProcesarPagoEvent = new ProcesarPagoEvent(ProcesarPagoEvent.PROCESAR_PAGO);
			evt.archivo = data;
			dispatcher.dispatchEvent(evt);
			ControlBlock.getInstance().add();
		}
		
		public function procesarArchivoFarmacia(data:ArchivoFarmacia):void{
			archivoFarmacia = data; 
			var evt:ProcesarPagoEvent = new ProcesarPagoEvent(ProcesarPagoEvent.PROCESAR_PAGO_FARMACIA);
			evt.archivoFarmacia = data;
			dispatcher.dispatchEvent(evt);
			ControlBlock.getInstance().add();
		}
		
		public function resultProcesarArchivoFarmacia():void{
			archivoFarmacia.procesado = 1;
			AlertOk.show("El pago se proceso con exito");
			ControlBlock.getInstance().remove();
		}
		
		public function resultProcesarArchivo():void{
			archivo.procesado = "S";
			AlertOk.show("El archivo se proceso con éxito");
			ControlBlock.getInstance().remove();
		}
		
		

		/* I4662 guarga fecha del servidor */
		public function functionFechaServidor(result:String):void{
			this.fechaServidor = result;
			ControlBlock.getInstance().remove();
		}
		
		
		
		
		public function listarCuentasCobradas(result:Array):void{
			listaCuentasCobradas = new ArrayCollection(result);
			ControlBlock.getInstance().remove();
			if(result.length == 0){
				AlertOk.show("No se encontraron cuentas cobradas");
			}	
			
		}
		
		
		public function resultListAllTipoCobro(result:Array):void{
			listaTipoCobro = new ArrayCollection(result);
			ControlBlock.getInstance().remove();
			if(result.length == 0){
				AlertOk.show("No se encontraron datos");
			}	
			
		}
		
		/* I8683 resultListAllProvincias */
		public function resultListAllProvincias(result:Array):void{
			listaProvincias = new ArrayCollection(result);
			ControlBlock.getInstance().remove();
			
			dispatcher.dispatchEvent(new Event("habilitarProvincia"));
			/*if(result.length == 0){
				AlertOk.show("No se encontraron datos");
			}	*/
			
		}
		
		
		/* I8683 resultListAllPartidos */
		public function resultListAllPartidos(result:Array):void{
			listaPartidos = new ArrayCollection(result);
			ControlBlock.getInstance().remove();
			/*if(result.length == 0){
				AlertOk.show("No se encontraron datos");
			}	*/
			
		}
		
		/* I4 resultListarReclamoDoc */
		public function resultListarReclamoDoc(result:Array):void{
			this.listarReclamoDoc = new ArrayCollection(result);
			ControlBlock.getInstance().remove();
			/*if(result.length == 0){
			AlertOk.show("No se encontraron datos");
			}	*/
			
		}
		
		
		/* I6 resultCodPosnet */
		public function resultCodPosnet(result:CodComercio):void{
			this.listarCodigoPosnet = result;
			if(!listarCodigoPosnet){
				AlertOk.show("Codigo de Comercio no existe");
			}
			ControlBlock.getInstance().remove();
			/*if(result.length == 0){
			AlertOk.show("No se encontraron datos");
			}	*/
			
		}
		
		
		
		/* I8683 resultListarReclamo */
		public function resultListarReclamo(result:ReclamoCabecera):void{
			buscaReclamoCabecera = result;
			if(!buscaReclamoCabecera){
				AlertOk.show("No se encontraron datos");
			} else {
				listarReclamoDetalle = new ArrayCollection(result.reclamoDetalleSet as Array);
				listarReclamoAdjuntos = new ArrayCollection(result.reclamoDocSet as Array);
			}
			
			 
			
			var dataSortField:SortField = new SortField();
			dataSortField.name = "fecha";
			dataSortField.descending = false;
			
			var dateDataSort:Sort = new Sort();
			dateDataSort.fields = [dataSortField];
			
			listarReclamoDetalle.sort = dateDataSort;
			listarReclamoDetalle.refresh();
			
			listarReclamoAdjuntos.sort = dateDataSort;
			listarReclamoAdjuntos.refresh();
			
			
			//AlertOk.show("No se encontraron datos" + listarReclamoDetalle.reclamoEstados.descripcion);
			
			ControlBlock.getInstance().remove();
		}
		
		
		
		/* I8683 resultListAllLocalidades */
		public function resultListAllLocalidades(result:Array):void{
			listaLocalidades = new ArrayCollection(result);
			ControlBlock.getInstance().remove();
			/*if(result.length == 0){
				AlertOk.show("No se encontraron datos");
			}	*/
			
		}
		
		/* I8683 resultListAllBarrios */
		public function resultListAllBarrios(result:Array):void{
			listaBarrios = new ArrayCollection(result);
			ControlBlock.getInstance().remove();
			/*if(result.length == 0){
				AlertOk.show("No se encontraron datos");
			}	*/
			
		}
		
		
		
		
		/* I8683 resultGraboReclamo */
		public function resultGraboReclamo(result:String):void{
			var array:Array = result.split(":");
			if (array[1]) {
				idReclamoNuevo = array[1].toString().replace(' ','');
			}						
				AlertOk.show( result );	
				ControlBlock.getInstance().remove();
			
		}
		
		/* I8683 resultGraboReclamo */
		public function resultGraboInstanciaReclamo(result:String):void{
			
			AlertOk.show( result );	
			ControlBlock.getInstance().remove();
			
		}
		
		
		/* I8683 resultClienteInteresado */
		public function resultClienteInteresado(cliente : ClienteTransaccion):void{	 
			this.clienteInteresado = cliente;  
			
			
			if(!clienteInteresado){
				AlertOk.show("La Busqueda no produjo resultados");
				
			} 
			ControlBlock.getInstance().remove();
			
		}
		
		/* I8683 resultClienteDeclarante */
		public function resultClienteDeclarante(cliente : ClienteTransaccion):void{	
			if (cliente) {
				this.clienteDeclarante = cliente;  
			} else {
				this.clienteDeclarante = null;
				AlertOk.show("Declarante no es cliente ");
			}
			
				dispatcher.dispatchEvent(new Event("clienteDeclarantes"));
				
			ControlBlock.getInstance().remove();
			
		}
		
		
		/* I8683 resultListReclamoTipos */
		public function resultListReclamoTipos(result:Array):void{	 
			ListReclamoTipos = new ArrayCollection(result);			
			ControlBlock.getInstance().remove();
			if(result.length == 0){
				AlertOk.show("No se encontraron datos");
			}	
		}
		
		
		/* I8683 resultListReclamoTiposTop */
		public function resultListReclamoTiposTop(result:Array):void{	 
			ListReclamoTiposTop = new ArrayCollection(result);
			ControlBlock.getInstance().remove();
			if(result.length == 0){
				AlertOk.show("No se encontraron datos");
			}	
		}
		
		
		public function resultListLugarPlastico(result:Array):void{	 
			ListLugarPlasticos = new ArrayCollection(result);
			ControlBlock.getInstance().remove();
			if(result.length == 0){
				AlertOk.show("No se encontraron datos");
			}	
		}
		
		
		public function resultListObservacionPlastico(result:Array):void{	 
			ListObservacionPlasticos = new ArrayCollection(result);
			ControlBlock.getInstance().remove();
			if(result.length == 0){
				AlertOk.show("No se encontraron datos");
			}	
		}
		
		
		
		
		/* I8683 resultEnvioMail */
		public function resultEnvioMail(result:String):void{	 
			AlertOk.show(result);
			ControlBlock.getInstance().remove();
		}
		
	
		
		/* I8683 resultListReclamoEstados */
		public function resultListReclamoEstados(result:Array):void{	 
			ListReclamoEstados = new ArrayCollection(result);
			ControlBlock.getInstance().remove();
			if(result.length == 0){
				AlertOk.show("No se encontraron datos");
			}	
		}
		
		/* I8683 resultListReclamoResolucion */
		public function resultListReclamoResolucion(result:Array):void{	 
			ListReclamoResolucion = new ArrayCollection(result);
			ControlBlock.getInstance().remove();
			if(result.length == 0){
				AlertOk.show("No se encontraron datos");
			}	
		}
		
		/* I8683 resultListReclamoCanales */
		public function resultListReclamoCanales(result:Array):void{	 
			listarReclamoCanales = new ArrayCollection(result);
			ControlBlock.getInstance().remove();
			if(result.length == 0){
				AlertOk.show("No se encontraron datos");
			}	
		}
		
		/* I8683 resultListReclamoSucursales */
		public function resultListReclamoSucursales(result:Array):void{	 
			listarReclamoSucursales = new ArrayCollection(result);
			ControlBlock.getInstance().remove();
			if(result.length == 0){
				AlertOk.show("No se encontraron datos");
			}	
		}
		
		
		
		public function resultListAllArchivos(result:Array):void{			
			archivosList = new ArrayCollection(result);			
			//paginador = result;
			ControlBlock.getInstance().remove();
			if(result.result.length == 0){
				AlertOk.show("No se encontraron datos");
			}
			
			/* AlertOk.show("paginacionCompleta"); */
			
		//	dispatcher.dispatchEvent(new Event("paginacionCompleta"));
		}
		
		
		public function resultConsultarTodo(result:String):void{			
			//archivosList = new ArrayCollection(result);			
			//paginador = result;
			ControlBlock.getInstance().remove();
			
			AlertOk.show(result);
			
			
			/* AlertOk.show("paginacionCompleta"); */
			
			dispatcher.dispatchEvent(new Event("paginacionCompleta"));
		}
		
		
		
		public function resultListAllArchivosFarmacia(result:Paginador):void{
			archivosFarmaciaList = new ArrayCollection(result.result);
			paginador = result;
			ControlBlock.getInstance().remove();
			if(result.result.length == 0){
				AlertOk.show("No se encontraron datos");
			}
			
			dispatcher.dispatchEvent(new Event("paginacionCompletaFarmacia"));
		}
		
		public function fault(fault:Fault):void{
			AlertError.show(fault.faultString);
			ControlBlock.getInstance().remove();
		}
		
		public function listAllArchivos(fechaDesde:Date=null):void{
			/* if(paginador){
			AlertOk.show("paginador no null "  );
			} else {
			AlertOk.show("paginador en null "  );	
			} */
			if(paginador){
				if(fechaDesde == null){
					this.fechaDesde = new Date();
				} else {
					this.fechaDesde = fechaDesde;
				}
			    /* AlertOk.show("listAllArchivos " + this.fechaDesde ); */
			 
				paginador.cantidadRegistros = 17;
				paginador.pagina = 0;
				filtro = new Filtro(); 
				filtro.campos.push("fechaReclamo"); 
				filtro.operadores.push(Filtro.MAYOR_IGUAL);  
				filtro.valores.push(Filtro.toDate(this.fechaDesde)); 	
				
				
				
				if (this.modoTotal == 4) {
				filtro.campos.push(" not exists (from ReclamoDetalle as det where det.reclamoCabecera.idReclamo = obj and det.reclamoEstados.reclamoEstado  ");
				filtro.operadores.push(Filtro.IGUAL_MANO);	
				filtro.valores.push(" 3)"); 
				}
				
				
				if (this.modoTipoReclamo != 0) {
					filtro.campos.push("reclamoTipos.reclamoTipo ");
					filtro.operadores.push(Filtro.IGUAL);	
					filtro.valores.push(this.modoTipoReclamo); 
				}
				
				
				
				distachBusquedaArchivo();
			}
			
		}
		
		public function listAllArchivosFarmacia(fechaDesde:Date=null):void{
			
			if(paginadoFarmacia){
				if(fechaDesde == null){
					this.fechaFarmaciaDesde = new Date();
				} else {
					this.fechaFarmaciaDesde = fechaDesde;
				}
			
				paginadoFarmacia.cantidadRegistros = 20;
				paginadoFarmacia.pagina = 0;
				var evt:ProcesarPagoEvent = new ProcesarPagoEvent(ProcesarPagoEvent.BUSCAR_ARCHIVOS_FARMACIA);
				evt.fecha = fechaFarmaciaDesde;
				evt.paginador = paginadoFarmacia;
				dispatcher.dispatchEvent(evt);							
				ControlBlock.getInstance().add();
			}
			
		}

		public function paginar():void{
			distachBusquedaArchivo();
		}
		
		public function paginarFarmacia():void{
			var evt:ProcesarPagoEvent = new ProcesarPagoEvent(ProcesarPagoEvent.BUSCAR_ARCHIVOS_FARMACIA);
			evt.fecha = fechaFarmaciaDesde;
			evt.paginador = paginadoFarmacia;
			dispatcher.dispatchEvent(evt);							
			ControlBlock.getInstance().add();
		}
		
		private function distachBusquedaArchivo():void{
			var evt:ProcesarPagoEvent = new ProcesarPagoEvent(ProcesarPagoEvent.BUSCAR_ARCHIVOS);
			evt.filtro = filtro;
			evt.paginador = paginador;
			
			
			dispatcher.dispatchEvent(evt);
			ControlBlock.getInstance().add();
		}
		
		
		public function buscarPlasticoLugar():void{
			var evt:ProcesarPagoEvent = new ProcesarPagoEvent(ProcesarPagoEvent.BUSCAR_PLASTICOS_LUGAR);
			dispatcher.dispatchEvent(evt);
			ControlBlock.getInstance().add();
		}
		
		public function cambiarDelugar(result:ArrayCollection,operador:Number):void{
			var evt:ProcesarPagoEvent = new ProcesarPagoEvent(ProcesarPagoEvent.CAMBIAR_LUGAR_PLASTICO);
			evt.listCamiarLugarPlastico = result.source;
			evt.lugarPlastico = modoTipoReclamo;
		  	evt.operador = operador;
			dispatcher.dispatchEvent(evt);
			ControlBlock.getInstance().add();
		}
		
		
		
		/*@I1*/		public function generarReporteReclamoInicio(idReclamo:Number,destino:Number):void{			
			ControlBlock.getInstance().add();
			var url,p1,p2,page : String;
			/*var array:Array = Application.application.url.split("/");*/
			/* uploadComp.uploadURL = "/" +array[3] + "/UploadFilePagoFacil"; */
			/*uploadComp.uploadURL = "/" +array[3] + "/UploadCobroExterno";*/
			
			/*var direccion:String = array[0]+"/"+array[1]+"/"+array[2]+"/"+array[3];*/
			/*AlertOk.show(direccion);*/
			
			/*String path = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();*/
			p1 = "?v_id_reclamo="+idReclamo;		
			//p2 = "&URLImagen="+direccion+"/img/fiel/logo_fiel.jpg";
			
			if (destino == 1) {
				page = "/Presentacion/report/ReclamosInicio.jrxml";	
			} else {
				page = "/Presentacion/report/ReclamosInicioComercio.jrxml";
			}
			
			
			//url = page+p1+p2;
			
			url = page+p1;
			
			/*AlertOk.show(url);*/
			
			/*navigateToURL(new URLRequest(String(url).replace('webapps','')),'_blank');*/
			navigateToURL(new URLRequest(url),'_blank');
			ControlBlock.getInstance().remove();
		/*@F1*/		}	
			
		/*@I1*/		public function generarReporteReclamoFin(idReclamo:Number,destino:Number):void{			
			ControlBlock.getInstance().add();
			var url,p1,p2,page : String;
			/*var array:Array = Application.application.url.split("/");*/
			/* uploadComp.uploadURL = "/" +array[3] + "/UploadFilePagoFacil"; */
			/*uploadComp.uploadURL = "/" +array[3] + "/UploadCobroExterno";*/
			
			/*var direccion:String = array[0]+"/"+array[1]+"/"+array[2]+"/"+array[3];*/
			/*AlertOk.show(direccion);*/
			
			/*String path = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();*/
			p1 = "?v_id_reclamo="+idReclamo;		
			//p2 = "&URLImagen="+direccion+"/img/fiel/logo_fiel.jpg";
			
			if (destino == 1) {
			page = "/Presentacion/report/ReclamosTerminado.jrxml";
			} else {
			page = "/Presentacion/report/ReclamosTerminadoComercio.jrxml";						
			}
			//url = page+p1+p2;
			
			url = page+p1;
			
			/*AlertOk.show(url);*/
			
			/*navigateToURL(new URLRequest(String(url).replace('webapps','')),'_blank');*/
			navigateToURL(new URLRequest(url),'_blank');
			ControlBlock.getInstance().remove();
		/*@F1*/		}	
		

	}
}