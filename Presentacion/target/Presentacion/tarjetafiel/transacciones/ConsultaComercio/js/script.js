new Vue({
  el: '#app',
  vuetify: new Vuetify(),
  data () {
    return {
      snack                :false,
      snackColor           :'',
      snackText            :'',
      page                 :1 ,
      pageCount            :0 ,
      itemsPerPage         :100,
      // ------------------------cinta de busquedas-----------------------------
      codigoCinta          :"",
      CUITCinta            :"",
      razonCinta           :[],
      fantasiaCinta        :"",
      grupoCinta           :"",
      genericoCinta        :"",
      // ------------------------contenido tabs---------------------------------
      razonTab             :[],
      fantasiaTab          :[],
      domicilioTab         :"",
      telefonoTab          :"",
      paginaWebTab         :"",
      emailTab             :"",
      rubroTab             :"",
      actividadTab         :"",
      idComercioTab        :"",
      fechaAltaTab         :"",
      fechaBajaTab         :"",
      departamentoTab      :"",
      telefonoArrayTab     :"",
      telefonoArrayTab2    :[],
      emailArrayTab        :[],
      emailArrayTab2       :"",
      telefonosTab         :"",
      emailsTab            :"",
      telefonoAInsertar    :[],
      emailAInsertar       :[],
      autorizados          :[],
      responsables         :[],
      agenda               :[],
      agendaFecha          :'',
      agendaFechaDesde     :'',
      agendaFechaHasta     :'',
      agendaDescripcion    :'',
      agendaComentarios    :'',
      agendaOperadorCodigo :'',
      agendaOperadorNombre :'',
      nombreOperador       :'',
      codigoOperadores     :[],
      nombreOperadores     :[],
      hoy                  :'',
      fechaActual          :'',
      // ------------------------tab domicilio----------------------------------
      domicilioCalle       :"",
      domicilioNumero      :"",
      domicilioOrientacion :"",
      domicilioManzana     :"",
      domicilioMonoblock   :"",
      domicilioPiso        :"",
      domicilioDepartamento:"",
      domicilioArea        :"",
      domicilioBarrio      :"",
      domicilioPartido     :"",
      domicilioCodigoPostal:"",
      domicilioProvincia   :"",
      genericoTab          :"",
           // ------------------------contenido cuit---------------------------------
      razonCuit            :[],
      fantasiaTab          :[],
      domicilioTab         :"",
      telefonoTab          :"",
      paginaWebTab         :"",
      emailTab             :"",
      rubroTab             :"",
      actividadTab         :"",
      idComercioTab        :"",
      fechaAltaTab         :"",
      fechaBajaTab         :"",
      departamentoTab      :"",
      telefonoArrayTab     :"",
      telefonoArrayTab2    :[],
      emailArrayTab        :[],
      emailArrayTab2       :"",
      telefonosTab         :"",
      emailsTab            :"",
      autorizados          :[],
      responsables         :[],
      TablaGeneral         :[],
      dialog               :false,
      dialog1              :false,
           // ------------------------tab domicilio----------------------------------
      domicilioCalle       :"",
      domicilioNumero      :"",
      domicilioOrientacion :"",
      domicilioManzana     :"",
      domicilioMonoblock   :"",
      domicilioPiso        :"",
      domicilioDepartamento:"",
      domicilioArea        :"",
      domicilioBarrio      :"",
      domicilioPartido     :"",
      domicilioCodigoPostal:"",
      domicilioProvincia   :"",
      genericoTab          :"",
      
      // ------------------------contenido tabs prueba--------------------------
      razonTab1            :"",
      fantasiaTab1         :"",
      domicilioTab1        :"",
      telefonoTab1         :"",
      paginaWebTab1        :"",
      emailTab1            :"",
      rubroTab1            :"",
      actividadTab1        :"",
      idComercioTab1       :"",
      fechaAltaTab1        :"",
      fechaBajaTab1        :"",
      emailArrayTab1       :"",
      // ------------------------datos  -----------------------------------------
      codPosnet            :"",
      cuit                 :"",
      razon_social         :"",
      nombre_fantasia      :"",
      domicilioCalle       :"",
      domicilioNumero      :"",
      domicilioOrientacion :"",
      domicilio            :"",
      telefono             :"",
      departamento         :"",
      estado               :"",
      // ------------------------datos mostrados en cinta de busquedas----------- 
      cod_comerciosVer     :"",
      cuitVer              :"",
      razon_socialVer      :"",
      nombre_fantasiaVer   :"",
      estadoVer            :"",
      grupoVer             :"",
      // ------------------------tabs cinta---------------------------------------
      tab                  :null,
      tab1                 :null,
      resultadosBusqueda   :[],
      operador:[],

      busqueda:{
      cod_comercio:'',
      cuit        :'',
      razon_social:'',
      Nn_fantasia :'',
      grupo       :'',
      generico    :'',
      fecha       :'',
      fechaActual :'',
      
      },
      // ------------------------tabs nombres-------------------------------------
      items: [
        { tab: 'INFO'                      , content: 'Contenido1'  },
        { tab: 'DETALLE DE TRANSACCIONES'  , content: 'Contenido2'  },
        { tab: 'CUENTA CORRIENTE'          , content: 'Contenido3'  },
        { tab: 'COMPOSICION DE SALDO'      , content: 'Contenido4'  },
        { tab: 'CUENTA CORRIENTE CONTABLE' , content: 'Contenido5'  },
        { tab: 'FACTURACION Y PAGO'        , content: 'Contenido6'  },
        { tab: 'PLAN DE VENTA'             , content: 'Contenido7'  },
        { tab: 'RELACIONES'                , content: 'Contenido8'  },
        { tab: 'COLOCACIONES'              , content: 'Contenido9'  },
        { tab: 'NOTAS DE C/D/ANULACIONES'  , content: 'Contenido10' },
        { tab: 'DOCUMENTOS'                , content: 'Contenido11' },
        { tab: 'WEB'                       , content: 'Contenido12' },
        { tab: 'OTROS'                     , content: 'Contenido13' },       
            ], 

      // ------------------------tabs nombres prueba------------------------------      
      items1: [
        { tab1: 'INFO'                      , content: 'Contenido1'  },
        { tab1: 'DETALLE DE TRANSACCIONES'  , content: 'Contenido2'  },
        { tab1: 'CUENTA CORRIENTE'          , content: 'Contenido3'  },
        { tab1: 'COMPOSICION DE SALDO'      , content: 'Contenido4'  },
        { tab1: 'CUENTA CORRIENTE CONTABLE' , content: 'Contenido5'  },
        { tab1: 'FACTURACION Y PAGO'        , content: 'Contenido6'  },
        { tab1: 'PLAN DE VENTA'             , content: 'Contenido7'  },
        { tab1: 'RELACIONES'                , content: 'Contenido8'  },
        { tab1: 'COLOCACIONES'              , content: 'Contenido9'  },
        { tab1: 'NOTAS DE C/D/ANULACIONES'  , content: 'Contenido10' },
        { tab1: 'DOCUMENTOS'                , content: 'Contenido11' },
        { tab1: 'WEB'                       , content: 'Contenido12' },
        { tab1: 'OTROS'                     , content: 'Contenido13' },       
              ],

      // ------------------------tabla Telefono------------------------------------        

      telefono: [
        { text: 'Telefonos' , value: 'telefono' ,sortable:false},
      ],

      contenidoTelefono: [],

      // ------------------------tabla emails-------------------------------------- 
      email: [
        { text: 'Emails', value: 'email'  ,sortable:false},
      ],
    
      contenidoEmail: [],

       // ------------------------tabla usuarios----------------------------------- 
      autorizados: [
        { text: 'Nn'      , value: 'nn'      ,sortable:false},
        { text: 'DNI'     , value: 'dni'     ,sortable:false},
        { text: 'Cargo'   , value: 'cargo'   ,sortable:false},
        { text: 'Telefono', value: 'telefono',sortable:false},
        { text: 'Email'   , value: 'email'   ,sortable:false},
      ],
    
      contenidoAutorizados: [],
       // ------------------------tabla usuarios----------------------------------- 
      responsables: [
        { text: 'Nn'      , value: 'nn'       ,sortable:false},
        { text: 'DNI'     , value: 'dni'      ,sortable:false},
        { text: 'Cargo'   , value: 'cargo'    ,sortable:false},
        { text: 'Telefono', value: 'telefono' ,sortable:false},
        { text: 'Email'   , value: 'email'    ,sortable:false},
      ],
    
      contenidoResponsables: [],

      Agenda: [
        { text: 'Fecha'       , value: 'fecha'       ,sortable:false},
        { text: 'Descripcion' , value: 'descripcion' ,sortable:false},
        { text: 'Comentarios' , value: 'comentarios' ,sortable:false},
        { text: 'Operador'    , value: 'operador'    ,sortable:false},
      ],

      contenidoAgenda: [],

      Eventos: [
        { text: 'Fecha'       , value: 'fecha'       ,sortable:false},
        { text: 'Descripcion' , value: 'descripcion' ,sortable:false},
        { text: 'Comentarios' , value: 'comentarios' ,sortable:false},
        { text: 'Operador'    , value: 'operador'    ,sortable:false},
      ],

      contenidoEventos: [],

      Sucursales: [
        { text: 'Descripcion' , value: 'descripcion' ,sortable:false},
        { text: 'Domicilio'   , value: 'domicilio'   ,sortable:false},
      ],

      contenidoSucursales: [],
        
  // -------------------tabla general---------------------
      TablaGeneral:[
        { text: 'Cod. Posnet'    , value: 'codPosnet'      ,sortable:false},
        { text: 'CUIT'           , value: 'cuit'           ,sortable:false},
        { text: 'Razon social'   , value: 'razon_social'   ,sortable:false},
        { text: 'Nn fantasia'    , value: 'nombre_fantasia',sortable:false},
        { text: 'Domicilio'      , value: 'domicilio'      ,sortable:false},
        { text: 'Partido'        , value: 'partido'        ,sortable:false},
        { text: 'Grupo'          , value: 'grupo'          ,sortable:false},   
        { text: 'Estado'         , value: 'estado'         ,sortable:false}, 
      ] ,

      // -------------------contenidoTabla--------------------
      contenidoTabla       :[], 
      
      showIndex: -1,
      computed: {
        formTitle () {
          return this.showIndex === -1 ? 'Nuevo Item' : 'Show Item'
        },
        },

      showItem     :{
        fecha      :'',
        descripcion:'',
        comentarios:'',
        operador   :''
      },

      defaultItem  :{
        fecha      :'',
        descripcion:'',
        comentarios:'',
        operador   :''
      },

}
    
  },

  created(){
    this.cargarAxios()
    this.initialize()
     },
methods: { 
  
  save () {
    this.snack = true
    this.snackColor = 'success'
    this.snackText = 'Data saved'
    },
  
  cancel () {
    this.snack = true
    this.snackColor = 'error'
    this.snackText = 'Canceled'
    },
  
  open () {
    this.snack = true
    this.snackColor = 'info'
    this.snackText = 'Dialog opened'
    },
  
  close () {
    console.log('Dialog closed')
    },
      
  clear () {
          this.razonCinta           = ''
          this.fantasiaCinta        = ''
          this.codigoCinta          = ''
          this.CUITCinta            = '' 
          this.cod_comerciosVer     = ''
          this.cuitVer              = ''
          this.razon_socialVer      = ''
          this.nombre_fantasiaVer   = ''
          this.grupoVer             = ''
          this.estadoVer            = ''
          this.razonTab             = ''
          this.fantasiaTab          = ''
          this.telefonoTab          = ''
          this.rubroTab             = ''
          this.emailTab             = ''
          this.actividadTab         = ''
          this.idComercioTab        = ''
          this.paginaWebTab         = ''
          this.fechaAltaTab         = ''
          this.fechaBajaTab         = ''
          this.domicilioTab         = ''  
          this.domicilioCalle       = ''
          this.domicilioNumero      = ''
          this.domicilioOrientacion = ''
          this.domicilioManzana     = ''
          this.domicilioMonoblock   = ''
          this.domicilioPiso        = ''
          this.domicilioDepartamento= ''
          this.domicilioArea        = ''
          this.domicilioBarrio      = ''
          this.domicilioPartido     = ''
          this.domicilioCodigoPostal= ''
          this.domicilioProvincia   = ''
          this.genericoTab          = ''
          this.genericoCinta        = ''
          this.telefonoArrayTab2    .splice(0, this.telefonoArrayTab2.length    )  
          this.contenidoTelefono    .splice(0, this.contenidoTelefono.length    )     
          this.contenidoEmail       .splice(0, this.contenidoEmail.length       ) 
          this.contenidoAutorizados. splice(0, this.contenidoAutorizados.length ) 
          this.contenidoResponsables.splice(0, this.contenidoResponsables.length) 
          this.contenidoAgenda      .splice(0, this.contenidoAgenda.length      )
          this.contenidoTabla       .splice(0, this.contenidoTabla.length       )
      }, 

  cargarInfo (response) {
    this.clear ()
console.log(response)

for(datos of response){
          this.cod_comerciosVer     = datos.codigoPosnet
          this.cuitVer              = datos.sucEmpresa.empresa.cuit
          this.razon_socialVer      = datos.sucEmpresa.empresa.razonSocial
          this.nombre_fantasiaVer   = datos.sucEmpresa.descripcion
          this.grupoVer             = "-----"
          this.estadoVer            = datos.estado
          this.razonTab             = this.razon_socialVer
          this.fantasiaTab          = this.nombre_fantasiaVer
            
          console.log('--'+'datos de cinta de busqueda'      + '--' +
                    'cod Posnet: '  +this.cod_comerciosVer   + ' '  +
                    'cuit: '        +this.cuitVer            + ' '  +
                    'razon social: '+this.razon_socialVer    + ' '  +
                    'fantasia: '    +this.nombre_fantasiaVer + ' '  +
                    'grupo: '       +this.grupoVer           + ' '  +
                    'estado: '      +this.estadoVer)

        // recorrer telefono para mostrarlos todos

        for(telefono of response[0].sucEmpresa.sucTelefonos){

          this.telefonoAInsertar = {  telefono: telefono.telefono.nroTlefono }
          this.telefonoArrayTab2.push(telefono.telefono.nroTlefono)
          this.contenidoTelefono.push(this.telefonoAInsertar)
                                                       }                                                         
              
        // recorrer emails para mostrarlos todos
        for(email of response[0].sucEmpresa.sucEmails){

          this.emailAInsertar = { email: email.email.email }
          this.emailArrayTab2 =   email.email.email
          this.contenidoEmail.push(this.emailAInsertar)
                                                       }                                                                                                       
          // recorrer usuarios para mostrarlos todos
         for(usuarios of response[0].sucEmpresa.individuosFlex){
          this.usuariosApellido    = usuarios.APELLIDO 
          this.usuariosNombre      = usuarios.NOMBRE
          this.usuariosNn          = this.usuariosApellido + ' ' + this.usuariosNombre
          this.usuariosNroDoc      = usuarios.NRODOC
          this.usuariosCargo       = "-----"
          this.usuariosTelefono    = "-----"
          this.usuariosEmail       = "-----"
          this.usuariosIDindividuo = usuarios.IDINDIVIDUO                
          this.usuariosSexo        = usuarios.SEXO  
          this.usuariosTipoDoc     = usuarios.TIPODOC  
          this.usuariosTipoInd     = usuarios.TIPOIND  
                 console.log('Nombre: '+this.usuariosNombre+ ' ' +'Apellido: '+this.usuariosApellido+' '+
                             'Nn: '    +this.usuariosNn    + ' ' +'tipo: '    +this.usuariosTipoInd)
          this.usuariosInsertar ={ nn:this.usuariosApellido+ ' ' +this.usuariosNombre,
                                  dni:this.usuariosTipoDoc + ' ' +this.usuariosNroDoc,
                                cargo:"-----",
                             telefono:"-----",
                                email:"-----"
                                  } 
                                  
         if(this.usuariosTipoInd === 'A' || this.usuariosTipoInd === 'X'){
          this.contenidoAutorizados.push(this.usuariosInsertar)
         }
                         
         if(this.usuariosTipoInd === 'X'){
          this.contenidoResponsables.push(this.usuariosInsertar)
         }                            
                                                   }
                     
          this.rubroTab             = "-----"
          this.actividadTab         = "-----"
          this.idComercioTab        = datos.idCodComercio
          this.paginaWebTab         = "-----"
          this.fechaAltaTab         = "-----"
          this.fechaBajaTab         = "-----"
          this.domicilioCalle       = datos.sucEmpresa.domicilio.calleNombre
          this.domicilioNumero      = datos.sucEmpresa.domicilio.calleNumero
          this.domicilioOrientacion = '( ' + datos.sucEmpresa.domicilio.orientacion + ' )'
          this.domicilioManzana     = "-----"
          this.domicilioMonoblock   = "-----"
          this.domicilioPiso        = "-----"
          this.domicilioDepartamento= "-----"
          this.domicilioArea        = "-----"
          this.domicilioBarrio      = datos.sucEmpresa.domicilio.barrio.descripcion
          this.domicilioPartido     = datos.sucEmpresa.domicilio.localidad.partido.descripcion
          this.domicilioCodigoPostal= "-----"
          this.departamento         = datos.sucEmpresa.domicilio.localidad.partido.descripcion     
          this.domicilioProvincia   = datos.sucEmpresa.domicilio.localidad.provincia.nombre
          this.domicilioTab         = this.domicilioCalle+' '+this.domicilioNumero+' '+'('+this.domicilioOrientacion+')' + ' '
                                      +this.departamento+' '+this.domicilioProvincia 
          this.genericoTab          = "-----"   
          this.telefonoTab          = this.telefonoArrayTab2
          this.emailTab             = this.emailArrayTab2
    
          console.log('--'+'datos de tab'+'-- '                      +
                        'razon social: ' +this.razonTab              +'-'+
                        'fantasia: '     + this.fantasiaTab          +'-'+
                        'telefono: '     + this.telefonoArrayTab     +'-'+
                        'rubro: '        +'-----'+'-'+
                        'email: '        +this.emailArrayTab         +'-'+
                        'actividad: '    +'-----'+'-'+
                        'id comercio: '  +response.idCodComercio+'-'+
                        'pagina web: '   +'-----'+' '+
                        'fecha alta: '   +'-----'+' '+
                        'fecha baja: '   +'-----'+' '+
                        'Calle: '        +this.domicilioCalle        +'-'+
                        'Numeracion: '   +this.domicilioNumero       +'-'+
                        'Orientacion: '  +this.domicilioOrientacion  +'-'+
                        'Manzana: '      +this.domicilioManzana      +'-'+
                        'Monoblock: '    +this.domicilioMonoblock    +'-'+
                        'Piso: '         +this.domicilioPiso         +'-'+
                        'Departamento: ' +this.domicilioDepartamento +'-'+
                        'Area: '         +this.domicilioArea         +'-'+
                        'Barrio: '       +this.domicilioBarrio       +'-'+
                        'Partido: '      +this.domicilioPartido      +'-'+
                        'Cod. postal: '  +this.domicilioCodigoPostal +'-'+
                        'Provincia: '    +this.domicilioProvincia    +'-'+
                        'Generico: '     +this.genericoTab) 
                                                  }
  },

  cargarInfoCodigo (response) {
    this.clear ()
console.log(response)

          this.cod_comerciosVer     = response.codigoPosnet
          this.cuitVer              = response.sucEmpresa.empresa.cuit
          this.razon_socialVer      = response.sucEmpresa.empresa.razonSocial
          this.nombre_fantasiaVer   = response.sucEmpresa.descripcion
          this.grupoVer             = "-----"
          this.estadoVer            = response.estado
          this.razonTab             = this.razon_socialVer
          this.fantasiaTab          = this.nombre_fantasiaVer
            
          console.log('--'+'datos de cinta de busqueda'      + '--' +
                    'cod Posnet: '  +this.cod_comerciosVer   + ' '  +
                    'cuit: '        +this.cuitVer            + ' '  +
                    'razon social: '+this.razon_socialVer    + ' '  +
                    'fantasia: '    +this.nombre_fantasiaVer + ' '  +
                    'grupo: '       +this.grupoVer           + ' '  +
                    'estado: '      +this.estadoVer)

        // recorrer telefono para mostrarlos todos
        for(telefono of response.sucEmpresa.sucTelefonos){

          this.telefonoAInsertar = {  telefono: telefono.telefono.nroTlefono }
          this.telefonoArrayTab2.push(telefono.telefono.nroTlefono)
          this.contenidoTelefono.push(this.telefonoAInsertar)
                                                       }                                                         
              
        // recorrer emails para mostrarlos todos
        for(email of response.sucEmpresa.sucEmails){

          this.emailAInsertar = { email: email.email.email }
          this.emailArrayTab2 =   email.email.email
          this.contenidoEmail.push(this.emailAInsertar)
                                                       } 
                                                    
          // recorrer usuarios para mostrarlos todos
         for(usuarios of response.sucEmpresa.individuosFlex){
          this.usuariosApellido    = usuarios.APELLIDO 
          this.usuariosNombre      = usuarios.NOMBRE
          this.usuariosNn          = this.usuariosApellido + ' ' + this.usuariosNombre
          this.usuariosNroDoc      = usuarios.NRODOC
          this.usuariosCargo       = "-----"
          this.usuariosTelefono    = "-----"
          this.usuariosEmail       = "-----"
          this.usuariosIDindividuo = usuarios.IDINDIVIDUO                
          this.usuariosSexo        = usuarios.SEXO  
          this.usuariosTipoDoc     = usuarios.TIPODOC  
          this.usuariosTipoInd     = usuarios.TIPOIND  
                 console.log('Nombre: '+this.usuariosNombre+ ' ' +'Apellido: '+this.usuariosApellido+' '+
                             'Nn: '    +this.usuariosNn    + ' ' +'tipo: '    +this.usuariosTipoInd)
          this.usuariosInsertar ={ nn:this.usuariosApellido+ ' ' +this.usuariosNombre,
                                  dni:this.usuariosTipoDoc + ' ' +this.usuariosNroDoc,
                                cargo:"-----",
                             telefono:"-----",
                                email:"-----"
                                  } 
                                  
         if(this.usuariosTipoInd === 'A' || this.usuariosTipoInd === 'X'){
          this.contenidoAutorizados.push(this.usuariosInsertar)
         }
                         
         if(this.usuariosTipoInd === 'X'){
          this.contenidoResponsables.push(this.usuariosInsertar)
         }                            
                                                   }
                     
          this.rubroTab             = "-----"
          this.actividadTab         = "-----"
          this.idComercioTab        = response.idCodComercio
          this.paginaWebTab         = "-----"
          this.fechaAltaTab         = "-----"
          this.fechaBajaTab         = "-----"
          this.domicilioCalle       = response.sucEmpresa.domicilio.calleNombre
          this.domicilioNumero      = response.sucEmpresa.domicilio.calleNumero
          this.domicilioOrientacion = '( ' + response.sucEmpresa.domicilio.orientacion + ' )'
          this.domicilioManzana     = "-----"
          this.domicilioMonoblock   = "-----"
          this.domicilioPiso        = "-----"
          this.domicilioDepartamento= "-----"
          this.domicilioArea        = "-----"
          this.domicilioBarrio      = response.sucEmpresa.domicilio.barrio.descripcion
          this.domicilioPartido     = response.sucEmpresa.domicilio.localidad.partido.descripcion
          this.domicilioCodigoPostal= "-----"
          this.departamento         = response.sucEmpresa.domicilio.localidad.partido.descripcion     
          this.domicilioProvincia   = response.sucEmpresa.domicilio.localidad.provincia.nombre
          this.domicilioTab         = this.domicilioCalle+' '+this.domicilioNumero+' '+'('+this.domicilioOrientacion+')' + ' '
                                      +this.departamento+' '+this.domicilioProvincia 
          this.genericoTab          = "-----"   
          this.telefonoTab          = this.telefonoArrayTab2
          this.emailTab             = this.emailArrayTab2
    
          console.log('--'+'datos de tab'+'-- '                      +
                        'razon social: ' +this.razonTab              +'-'+
                        'fantasia: '     + this.fantasiaTab          +'-'+
                        'telefono: '     + this.telefonoArrayTab     +'-'+
                        'rubro: '        +'-----'+'-'+
                        'email: '        +this.emailArrayTab         +'-'+
                        'actividad: '    +'-----'+'-'+
                        'id comercio: '  +response.idCodComercio+'-'+
                        'pagina web: '   +'-----'+' '+
                        'fecha alta: '   +'-----'+' '+
                        'fecha baja: '   +'-----'+' '+
                        'Calle: '        +this.domicilioCalle        +'-'+
                        'Numeracion: '   +this.domicilioNumero       +'-'+
                        'Orientacion: '  +this.domicilioOrientacion  +'-'+
                        'Manzana: '      +this.domicilioManzana      +'-'+
                        'Monoblock: '    +this.domicilioMonoblock    +'-'+
                        'Piso: '         +this.domicilioPiso         +'-'+
                        'Departamento: ' +this.domicilioDepartamento +'-'+
                        'Area: '         +this.domicilioArea         +'-'+
                        'Barrio: '       +this.domicilioBarrio       +'-'+
                        'Partido: '      +this.domicilioPartido      +'-'+
                        'Cod. postal: '  +this.domicilioCodigoPostal +'-'+
                        'Provincia: '    +this.domicilioProvincia    +'-'+
                        'Generico: '     +this.genericoTab) 
                                                  
  },




  cargarTabla(response){
this.clear()
    for(comercio of response.data.result){

            this.codPosnet               = comercio.codigoPosnet
            this.cuit                    = comercio.sucEmpresa.empresa.cuit
            this.razon_social            = comercio.sucEmpresa.empresa.razonSocial
            this.nombre_fantasia         = comercio.sucEmpresa.descripcion
            this.domicilioCalleTab       = comercio.sucEmpresa.domicilio.calleNombre
            this.domicilioNumeroTab      = comercio.sucEmpresa.domicilio.calleNumero
            this.domicilioOrientacionTab = comercio.sucEmpresa.domicilio.orientacion
            this.domicilioTab            = this.domicilioCalleTab+' '+this.domicilioNumeroTab+' '+'( '+this.domicilioOrientacionTab+' )'
            this.departamento            = comercio.sucEmpresa.domicilio.localidad.partido.descripcion
            this.estado                  = comercio.estado  

      this.Insertardatos={
        codPosnet      :this.codPosnet       ,
        cuit           :this.cuit            ,
        razon_social   :this.razon_social    ,
        nombre_fantasia:this.nombre_fantasia ,
        domicilio      :this.domicilioTab    ,
        partido        :this.departamento    ,
        grupo          :"-----"              ,
        estado         :this.estado          }
  
  this.contenidoTabla.push(this.Insertardatos)
  
  }                        
            
  },

  buscarCod_comercio(codigoCinta){
    this.clear ()
console.log(codigoCinta)
    let datosNuevos = {
      "tipo":"cod_posnet",
      "valor":codigoCinta

    }
   
    let datosJSON = JSON.stringify(datosNuevos) 
      axios.post('http://192.168.0.7:8080/Presentacion/BuscarComercioServlet',datosJSON)
        .then((response) => {
          console.log(response)
          this.cargarInfoCodigo(response.data)
          this.dialog=false
          
     },
       (error) => {
         alert('ERROR!! CODIGO DE COMERCIO NO ENCONTRADO');

            });
  
  },

  digitoVerificadorcuil(CUITCinta){
    var acum=0;
    var num;
 
    for(var i=0;i<10;i++){
  
      num = parseInt(CUITCinta.substring(i,i+1));
      switch(i){
        case 0: case 6: acum += num * 5;break;
        case 1: case 7: acum += num * 4;break;
        case 2: case 8: acum += num * 3;break;
        case 3: case 9: acum += num * 2;break;
        case 4: acum +=num * 7;break;
        case 5: acum +=num * 6;break;
      }
  
    }
  
    var digito = 11 - (acum % 11);
  
    switch(digito){
      case 11: digito = 0;break;
      case 10: digito = 1;break;
    }
    console.log((digito === parseInt(CUITCinta.substring(10,11))))
    return((digito === parseInt(CUITCinta.substring(10,11))))
  },

  buscarCuit(CUITCinta){
    this.clear ()
    var respuesta = this.digitoVerificadorcuil(CUITCinta)

    if(respuesta){ 
      console.log(CUITCinta.toUpperCase())
        let datosNuevos = {
          "tipo":"cuit",
          "valor":CUITCinta.toUpperCase()  // toUpperCase() transforma el texto ingresado en mayusculas
        
        }
        
        let datosJSON = JSON.stringify(datosNuevos) 
        axios.post('http://192.168.0.7:8080/Presentacion/BuscarComercioServlet',datosJSON)
        .then((response) => {
console.log(response)
         console.log(response.data.result.length)
          if(response.data.result.length === 1){
            this.cargarInfo(response.data.result)
        
          }else if(response.data.result.length >1 ){

            this.cargarTabla(response)
            this.dialog = true;   
          }
          else  alert('ERROR!! CUIT NO ENCONTRADO');
              
            }, (error) => {
              alert('ERROR!! CUIT NO ENCONTRADO');
              });
              
      }
      else{
        alert('CUIT INCORRECTO')
      }
  },

  buscarRazonSocial(razonCinta){
    this.clear ()
    console.log(razonCinta.toUpperCase())
      let datosNuevos = {
        "tipo":"razon_social",
        "valor":razonCinta.toUpperCase()  // toUpperCase() transforma el texto ingresado en mayusculas
      
      }
      
      let datosJSON = JSON.stringify(datosNuevos) 
      axios.post('http://192.168.0.7:8080/Presentacion/BuscarComercioServlet',datosJSON)
      .then((response) => {
      console.log(response)
        if(response.data.result.length === 1){
          this.cargarInfo(response.data.result)

        }
        else{

          this.cargarTabla(response)
        
          this.dialog = true;   
        }    
     
           }, (error) => {
            alert('ERROR!! RAZON SOCIAL NO ENCONTRADA');
             });
    
  },
 
  buscarNnFantasia(fantasiaCinta){
    console.log(fantasiaCinta)

    this.clear ()
    console.log(fantasiaCinta.toUpperCase())
      let datosNuevos = {
        "tipo":"nombre_fantasia",
        "valor":fantasiaCinta.toUpperCase()  // toUpperCase() transforma el texto ingresado en mayusculas
      
      }

      let datosJSON = JSON.stringify(datosNuevos) 
      axios.post('http://192.168.0.7:8080/Presentacion/BuscarComercioServlet',datosJSON)
      .then((response) => {
        console.log(response)
        if(response.data.result.length === 1){
          this.cargarInfo(response.data.result)

        }
        else{

          this.cargarTabla(response)
          this.dialog = true;   
        }
           }, (error) => {
            alert('ERROR!! NN FANTASIA NO ENCONTRADA');
             });

  },

  buscarGrupo(){
    alert('buscar grupo')
  },

  buscarGenerico(genericoCinta){
    this.clear ()
    console.log(genericoCinta.toUpperCase())
      let datosNuevos = {
        "tipo":"generico",
        "valor":genericoCinta.toUpperCase()
      
      }

      let datosJSON = JSON.stringify(datosNuevos) 
      axios.post('http://192.168.0.7:8080/Presentacion/BuscarComercioServlet',datosJSON)
      .then((response) => {
        console.log(response)
                  
        if(response.data.result.length === 1){
          this.cargarInfo(response.data.result)

        }
        else{

          this.cargarTabla(response)
        
          this.dialog = true;   
        }      
     
           }, (error) => {
            alert('ERROR!! DATO NO ENCONTRADO');
             });

  },
  
  BuscarAgenda1(agendaFechaDesde,agendaFechaHasta){

  console.log('fecha desde: ' + this.agendaFechaDesde)
  console.log('fecha hasta: ' + this.agendaFechaHasta)
  console.log('fecha tabla: ' + this.fechaActual     )
  }, 
  
  cargarAxios(){


  },
  
  getQueryVariable(variable) {
    
    // Estoy asumiendo que query es window.location.search.substring(1);
    var query = window.location.search.substring(1);
    var vars = query.split("&");
    for (var i=0; i < vars.length; i++) {
        var pair = vars[i].split("=");
        if (pair[0] == variable) {
            return pair[1];
        }
    }
    return false;
}, 

async initialize(){
  this.codigoOperadores = this.getQueryVariable('codigoOperador');
  console.log('codigo operadores: ' + this.codigoOperadores)
  // obtener operadores ,Codigo y Nombre completo
  let resp = await axios.get('http://192.168.0.7:8080/Presentacion/AsistenciaPersonalServlet')
let operadoresAPI = resp.data
console.log(operadoresAPI)

  let i = 0;
   while(this.codigoOperadores != operadoresAPI[i].codigo){
     i++;
  }

  this.nombreOperador  = operadoresAPI[i].nombreCompleto

},

  guardarEvento(){
    //guardar fecha(la fecha del dia actual ,descripcion(teclado), comentarios(teclado)
    // y operador(url,ver como hacerlo)) 
    var fecha = new Date();
    this.fechaActual=(fecha.getDate() + "/" + (fecha.getMonth() +1) + "/" + fecha.getFullYear());
 if(this.agendaDescripcion != '' || this.agendaDescripcion != undefined || this.agendaDescripcion != null &&
    this.agendaComentarios != '' || this.agendaComentarios != undefined || this.agendaComentarios != null &&
    this.agendaOperador    != '' || this.agendaOperador    != undefined || this.agendaOperador    != null ){
    console.log('fecha:      :  ' + this.fechaActual         )
    console.log('descripcion :  ' + this.agendaDescripcion   )
    console.log('comentarios :  ' + this.agendaComentarios   )
    console.log('operador    :  ' + this.nombreOperador)
    this.agendaInsertar={
        fecha      : this.fechaActual,
        descripcion: this.agendaDescripcion,
        comentarios: this.agendaComentarios,
        operador   : this.nombreOperador}
    this.contenidoAgenda.push(this.agendaInsertar)
    this.dialog1=true

}
  
  //   if(
  //   this.agendaDescripcion === "" || this.agendaDescripcion === undefined || this.agendaDescripcion === null ||
  //   this.agendaComentarios === "" || this.agendaComentarios === undefined || this.agendaComentarios === null ||
  //   this.agendaOperador    === "" || this.agendaOperador    === undefined || this.agendaOperador    === null ){
  //   alert('Rellene datos vacios')
     
  //  }
  },
  
        },
          })