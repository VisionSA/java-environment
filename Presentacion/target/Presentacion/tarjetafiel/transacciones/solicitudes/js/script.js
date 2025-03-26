new Vue({
  el: '#app',
  vuetify: new Vuetify(),
  data: () => ({
    return: {
      datos: null,
    },
    search: '',
    search1: '',
    hidden: false,
    max_situacion_bcra:'',
    dialogGuardar:false,
    dialog:false, 
    snack: false,
    snackColor: '',
    snackText: '',
    pagination: {},
    page: 1,
    pageCount: 0,
    itemsPerPage: 100 ,
    banderaRechazada: 1,
    banderaPendiente: 1,
    banderaEvaluacion: 1,
    banderaRevision: 1,
    datosGuardados:'',
    checkbox: true,
    estados:  ['Rechazada','Pendiente','Evaluacion', 'Revision','Aprobada','Pendiente DNI','Pendiente carga','Contactar','Sin contacto'],
    estados2: ['Rechazada','Pendiente','Evaluacion', 'Revision','Aprobada','Pendiente DNI','Pendiente carga','Contactar','Sin contacto'],
    estados3: ['Rechazada','Pendiente','Evaluacion', 'Revision','Aprobada','Pendiente DNI','Pendiente carga','Contactar','Sin contacto'],
    situacion:['0','1','2','3','4','5'],
    repetidas:['SI' ,'NO'],
    repetidas2:['SI' ,'NO'],
    canales:[],
    canales2:'',
    canalesPrueba:['FBAd','GDN','btm','GAd','GDNr','GND','GNDr'],
    headers: [
      { text: 'Acc', align: 'start',         value:'actions'            ,sortable: false},
      { text: 'ID',                          value:'idSolicitud'        ,sortable: false},
      { text: 'DNI',                         value:'dni'                ,sortable: false},
      { text: 'Nombre',                      value:'nombre'             ,sortable: false},
      { text: 'Apellido',                    value:'apellido'           ,sortable: false},
      { text: 'Correo',                      value:'email'              ,sortable: false},
      { text: 'Telefono',                    value:'telefono'           ,sortable: false},
   // { text: 'Estado civ.',                 value:'estadoCivil'        ,sortable: false},
   // { text: 'Hijos',                       value:'hijos'              ,sortable: false},
   // { text: 'Actividad',                   value:'actividad'          ,sortable: false},
   // { text: 'Vencimiento',                 value:'vencimiento'        ,sortable: false},
   // { text: 'Nro solic.',                  value:'nroSolicitud'       ,sortable: false},
   // { text: 'Fecha nac.',                  value:'fechaNacimiento'    ,sortable: false},
   // { text: 'Domicilio',                   value:'direccion'          ,sortable: false},
   // { text: 'Sexo',                        value:'sexo'               ,sortable: false},
   // { text: 'Fecha',                       value:'fecha'              ,sortable: false},   
   // { text: 'Deudas',                      value:'mora'               ,sortable: false},
      { text: 'Sit',                         value:'max_situacion_bcra' ,sortable: false},
   // { text: 'Monto (BCRA)',                value:'monto_bcra'         ,sortable: false},
   // { text: 'Cantidad (BCRA)',             value:'cant_afec_bcra'     ,sortable: false},  
   // { text: 'Cuil',                        value:'cuil'               ,sortable: false},
      { text: 'Rep',                         value:'repetida'           ,sortable: false},
   // { text: 'Cliente',                     value:'es_cliente'         ,sortable: false},
      { text: 'Canal',                       value:'canal'              ,sortable: false},
   // { text: 'Repetidas dni',               value:'repetida_dni'       ,sortable: false},
      { text: 'Observaciones',               value:'observaciones'      ,sortable: false},
      { text: 'Estado ',                     value:'aceptada'           ,sortable: false},

    ],
    headers2: [
      { text: 'ID',                               value:'idSolicitud' },
      { text: 'DNI',                              value:'dni' },
      { text: 'Nombre',                           value:'nombre'},
      { text: 'Apellido',                         value:'apellido'},
      { text: 'Correo',                           value:'email' },
      { text: 'Telefono',                         value:'telefono' },
      { text: 'fecha nac.',                       value:'fechaNacimiento'},
      { text: 'estado civ.',                      value:'estadoCivil'},
      { text: 'Hijos',                            value:'hijos' },
      { text: 'Actividad',                        value:'actividad'},
      { text: 'Vencimiento',                      value:'vencimiento'},
      { text: 'Nro solic.',                       value:'nroSolicitud'},
      { text: 'Domicilio',                        value:'direccion' },
      { text: 'Sexo',                             value:'sexo' },
      { text: 'Fecha',                            value:'fecha' },   
      { text: 'Deudas',                           value:'mora' },
      { text: 'Sit',                              value:'max_situacion_bcra' },
      { text: 'Monto',                            value:'monto_bcra' },
      { text: 'Cant.',                            value:'cant_afec_bcra' },  
      { text: 'Cuil',                             value:'cuil' },
      { text: 'Rep',                              value:'repetida' },
      { text: 'Cliente',                          value:'es_cliente' },
      { text: 'Canal',                            value:'canal' },
      { text: 'Rep dni',                          value:'repetida_dni' },
      { text: 'Observaciones',                    value:'observaciones'},
      { text: 'Estado ',                          value:'aceptada'},
    ],
    desserts: [],
    desserts2: [],
    showIndex: -1,
    showItem :{
      idSolicitud: [],
      DNI: [],
      Nombre: [],
      Apellido: [],
      email: [],
      telefono:[],
      direccion:[],
      sexo:[],
      fecha:[],
      mora:[],
      max_situacion_bcra:[],
      monto_bcra:[],
      cant_afec_bcra:[],
      cuil:[],
      repetida:[],
      es_cliente:[],
      canal:[],
      repetida_dni:[],
      observaciones:[],
      aceptada:[],

    },
    defaultItem: {
     
      idSolicitud: [],
      DNI: [],
      Nombre: [],
      Apellido: [],
      email:[],
      telefono:[],
      direccion:[],
      sexo:[],
      fecha:[],
      mora:[],
      max_situacion_bcra:[],
      monto_bcra:[],
      cant_afec_bcra:[],
      cuil:[],
      repetida:[],
      es_cliente:[],
      canal:[],
      repetida_dni:[],
      onservaciones:[],
      aceptada:[],
    },  
  }),

  computed: {
    formTitle () {
      return this.showIndex === -1 ? 'Nuevo Item' : 'Show Item'
    },
    },
headers(){
  return[
    {sortable: false},
    { text: 'Acciones', align: 'start',         value:'actions',sortable: false },
    { text: 'ID',                               value:'idSolicitud' },
    { text: 'DNI',                              value:'dni' },
    { text: 'Nombre',                           value:'nombre'},
    { text: 'Apellido',                         value:'apellido'},
    { text: 'Correo',                           value:'email' },
    { text: 'Telefono',                         value:'telefono' },
    { text: 'Domicilio',                        value:'direccion' },
    { text: 'Sexo',                             value:'sexo' },
    { text: 'Fecha',                            value:'fecha' },   
    { text: 'Deudas',                           value:'mora' },
    { text: 'Situacion(BCRA)',                  value:'max_situacion_bcra'},
    { text: 'Monto(BCRA)',                      value:'monto_bcra' },
    { text: 'Cantidad(BCRA)',                   value:'cant_afec_bcra' },  
    { text: 'Cuil',                             value:'cuil' },
    { text: 'Repetida',                         value:'repetida' },
    { text: 'Cliente',                          value:'es_cliente' },
    { text: 'Canal',                            value:'canal' },
    { text: 'Repetidas dni',                    value:'repetida_dni' },
    { text: 'Observaciones',                    value:'observaciones'},
    { text: 'Estado ',                          value:'aceptada'},

  ]
},
  watch: {
    dialog (val) {
      val || this.cerrar()
    },
  },
  
  created () {
    this.initialize(),
    this.cargarAxios()
  },

  methods: {
    initialize () {
      this.desserts = []
      
    },
    filterOnlyCapsText (value, search1, item) {
      return value != null &&
        search1 != null &&
        typeof value === 'string' &&
        value.toString().toLocaleUpperCase().indexOf(search1) !== 13
    },

    showItems (item) {
     this.showIndex = this.desserts.indexOf(item)
     this.showItem = Object.assign({}, item)
     this.dialog = true

     this.showIndex = this.desserts2.indexOf(item)
     this.showItem = Object.assign({}, item)
     this.dialog = true
    },

    recargar(){
      location.reload()
    },
    
    cerrar () {
      this.dialog = false
      this.$nextTick(() => {
        this.showitem = Object.assign({}, this.defaultItem)
        this.showIndex = -1
      })
    },


    save (id,nombre,apellido,
      telefono,estadoCivil,hijos,
      actividad,vencimiento,nroSolicitud,
      fechaNacimiento,direccion,email,
      observacion,aceptado) {

      this.snack = true
      this.snackColor = 'success'
      this.snackText = 'Datos guardados'

      switch(aceptado){
        case "Pendiente"         : aceptado = "N"; break;
        case "Evaluacion"        : aceptado = "A"; break;
        case "Rechazada"         : aceptado = "R"; break;
        case "Revision"          : aceptado = "E"; break;
        case "Aprobada"          : aceptado = "D"; break;
        case "Pendiente DNI"     : aceptado = "P"; break;
        case "Pendiente carga"   : aceptado = "L"; break;
        case "Contactar"         : aceptado = "T"; break;
        case "Sin contacto"      : aceptado = "S"; break;
      }

      let observacionLimpio = observacion.replaceAll('ñ','n')
     
    
      let observacionSinAcento =this.removeAccents(observacionLimpio)

      let observacionTrim = observacionSinAcento.replace(/\s/g, ' ')


      let datosNuevos = {
        "id"             :id,
        "nombre"         :nombre,
        "apellido"       :apellido,
        "telefono"       :telefono,
        "estadoCivil"    :estadoCivil,
        "hijos"          :hijos,
        "actividad"      :actividad,
        "vencimiento"    :vencimiento,
        "nroSolicitud"   :nroSolicitud,
        "fechaNacimiento":fechaNacimiento,
        "domicilio"      :direccion,
        "email"          :email,
        "estado"         :aceptado,
        "observacion"    :observacionTrim
    }
    let datosJSON = JSON.stringify(datosNuevos) 

    axios.post('http://192.168.0.7:8080/Presentacion/SolicitudesWebServlet',datosJSON)
    .then((response) => {
      console.log(response.data);
    }, (error) => {
      console.log(error);
    });
    
    this.funcionColorTabla()
    },

    cancel () {
      this.snack = true
      this.snackColor = 'error'
      this.snackText = 'Cancelado'
    },

    removeAccents(str) {     
      return str.normalize("NFD").replace(/[\u0300-\u036f]/g, "");
    } ,


    guardarTarjeta (id,nombre,apellido,
                    telefono,estadoCivil,hijos,
                    actividad,vencimiento,nroSolicitud,
                    fechaNacimiento,direccion,email,
                    observacion,aceptado) {

      for(let i =0;i<this.desserts.length;i++){
        if(this.desserts[i].idSolicitud === id){
          this.desserts[i].nombre= nombre
          this.desserts[i].apellido= apellido
          this.desserts[i].telefono=telefono
          this.desserts[i].estadoCivil=estadoCivil
          this.desserts[i].hijos=hijos
          this.desserts[i].actividad=actividad
          this.desserts[i].vencimiento=vencimiento
          this.desserts[i].nroSolicitud=nroSolicitud
          this.desserts[i].fechaNacimiento=fechaNacimiento
          this.desserts[i].direccion=direccion
          this.desserts[i].email=email
          this.desserts[i].observaciones = observacion
          this.desserts[i].aceptada = aceptado

        }
      }
      
      this.cerrar()
      switch(aceptado){
        case "Pendiente"         : aceptado = "N"; break;
        case "Evaluacion"        : aceptado = "A"; break;
        case "Rechazada"         : aceptado = "R"; break;
        case "Revision"          : aceptado = "E"; break;
        case "Aprobada"          : aceptado = "D"; break;
        case "Pendiente DNI"     : aceptado = "P"; break;
        case "Pendiente carga"   : aceptado = "L"; break;
        case "Contactar"         : aceptado = "T"; break;
        case "Sin contacto"      : aceptado = "S"; break;
      }
   
      let observacionLimpio = observacion.replaceAll('ñ','n')
       
      let observacionSinAcento =this.removeAccents(observacionLimpio)

      let observacionTrim = observacionSinAcento.replace(/\s/g, ' ')

      let datosNuevos = {
        "id"             :id,
        "nombre"         :nombre,
        "apellido"       :apellido,
        "email"          :email,
        "telefono"       :telefono,
        "estadoCivil"    :estadoCivil,
        "hijos"          :hijos,
        "actividad"      :actividad,
        "vencimiento"    :vencimiento,
        "nroSolicitud"   :nroSolicitud,
        "fechaNacimiento":fechaNacimiento,
        "domicilio"      :direccion,
        "estado"         :aceptado,
        "observacion"    :observacionTrim
      }
      let datosJSON = JSON.stringify(datosNuevos) 
      
      axios.post('http://192.168.0.7:8080/Presentacion/SolicitudesWebServlet',datosJSON)
      .then((response) => {
        console.log(response);
      }, (error) => {
        console.log(error);
      });

this.funcionColorTabla()
    },

    funcionColorTabla2(){
      this.eliminarColor()
      var table = document.querySelector('.v-data-table__wrapper')
      var hijo1 = table.childNodes
      var hijo2 = hijo1[0].childNodes
      var hijo3 = hijo2[2].childNodes
      if(this.banderaRechazada === 1){
      for (let i=0;i<hijo3.length;i++){
         switch(hijo3[i].lastChild.firstChild.firstChild.firstChild.innerHTML){
          case 'Rechazada'         : hijo3[i].classList.add('id1');break;
          case 'Pendiente'         : hijo3[i].classList.add('id2');break;
          case 'Evaluacion'        : hijo3[i].classList.add('id3');break;
          case 'Revision'          : hijo3[i].classList.add('id4');break;
          case "Aprobada"          : hijo3[i].classList.add('id5');break;
          case "Pendiente DNI"     : hijo3[i].classList.add('id6');break;
          case "Pendiente carga"   : hijo3[i].classList.add('id7');break;
          case "Contactar"         : hijo3[i].classList.add('id8');break;
          case "Sin contacto"      : hijo3[i].classList.add('id9');break;
         }
      }
      this.banderaRechazada = 0
    }
    else{
      for (let i=0;i<hijo3.length;i++){
        switch(hijo3[i].lastChild.firstChild.firstChild.firstChild.innerHTML){
         case 'Rechazada'         : hijo3[i].classList.remove('id1');break;
         case 'Pendiente'         : hijo3[i].classList.remove('id2');break;
         case 'Evaluacion'        : hijo3[i].classList.remove('id3');break;
         case 'Revision'          : hijo3[i].classList.remove('id4');break;
         case "Aprobada"          : hijo3[i].classList.remove('id5');break;
         case "Pendiente DNI"     : hijo3[i].classList.remove('id6');break;
         case "Pendiente carga"   : hijo3[i].classList.remove('id7');break;
         case "Contactar"         : hijo3[i].classList.remove('id8');break;
         case "Sin contacto"      : hijo3[i].classList.remove('id9');break;
        break;
      }
      this.banderaRechazada = 1
     }
    }
    },

funcionColorTabla(){
  this.eliminarColor()
  var table = document.querySelector('.v-data-table__wrapper')
  var hijo1 = table.childNodes
  var hijo2 = hijo1[0].childNodes
  var hijo3 = hijo2[2].childNodes
  
  for (let i=0;i<hijo3.length;i++){
     switch(hijo3[i].lastChild.firstChild.firstChild.firstChild.innerHTML){
      case 'Rechazada'         : hijo3[i].classList.add('id1');break;
      case 'Pendiente'         : hijo3[i].classList.add('id2');break;
      case 'Evaluacion'        : hijo3[i].classList.add('id3');break;
      case 'Revision'          : hijo3[i].classList.add('id4');break;
      case "Aprobada"          : hijo3[i].classList.add('id5');break;
      case "Pendiente DNI"     : hijo3[i].classList.add('id6');break;
      case "Pendiente carga"   : hijo3[i].classList.add('id7');break;
      case "Contactar"         : hijo3[i].classList.add('id8');break;
      case "Sin contacto"      : hijo3[i].classList.add('id9');break;
     }
  }
},

funcionColor1(){
  this.eliminarColor()
  var table = document.querySelector('.v-data-table__wrapper')
  var hijo1 = table.childNodes
  var hijo2 = hijo1[0].childNodes
  var hijo3 = hijo2[2].childNodes

  if(this.banderaRechazada === 1){
    for (let i=0;i<hijo3.length;i++){
      switch(hijo3[i].lastChild.firstChild.firstChild.firstChild.innerHTML){
       case 'Rechazada' : {
          hijo3[i].classList.add('ocultar') ;

       }     
       break;
      }
    }
   this.banderaRechazada = 0
  }
  else{
    for (let i=0;i<hijo3.length;i++){
      switch(hijo3[i].lastChild.firstChild.firstChild.firstChild.innerHTML){
       case 'Rechazada' : hijo3[i].classList.remove('ocultar');

       break;
      }
      this.banderaRechazada = 1
    }
  }

  this.funcionColorTabla()

  
},

    funcionColor2(){
      this.eliminarColor()
      var table = document.querySelector('.v-data-table__wrapper')
      var hijo1 = table.childNodes
      var hijo2 = hijo1[0].childNodes
      var hijo3 = hijo2[2].childNodes
   
  if(this.banderaPendiente === 1){
    for (let i=0;i<hijo3.length;i++){
      switch(hijo3[i].lastChild.firstChild.firstChild.firstChild.innerHTML){
       case 'Pendiente' : {
          hijo3[i].classList.add('ocultar') ;

       }     
       break;
      }
    }
   this.banderaPendiente = 0
  }
  else{
    for (let i=0;i<hijo3.length;i++){
      switch(hijo3[i].lastChild.firstChild.firstChild.firstChild.innerHTML){
       case 'Pendiente' : hijo3[i].classList.remove('ocultar');

       break;
      }
      this.banderaPendiente = 1
    }
  }

  this.funcionColorTabla()

  
},
    funcionColor3(){
      this.eliminarColor()
      var table = document.querySelector('.v-data-table__wrapper')
      var hijo1 = table.childNodes
      var hijo2 = hijo1[0].childNodes
      var hijo3 = hijo2[2].childNodes
    
      if(this.banderaEvaluacion === 1){
        for (let i=0;i<hijo3.length;i++){
          switch(hijo3[i].lastChild.firstChild.firstChild.firstChild.innerHTML){
           case 'Evaluacion' : {
              hijo3[i].classList.add('ocultar') ;
    
           }     
           break;
          }
        }
       this.banderaEvaluacion = 0
      }
      else{
        for (let i=0;i<hijo3.length;i++){
          switch(hijo3[i].lastChild.firstChild.firstChild.firstChild.innerHTML){
           case 'Evaluacion' : hijo3[i].classList.remove('ocultar');
    
           break;
          }
          this.banderaEvaluacion = 1
        }
      }
    
      this.funcionColorTabla()
    
      
    },
    funcionColor4(){
      this.eliminarColor()
      var table = document.querySelector('.v-data-table__wrapper')
      var hijo1 = table.childNodes
      var hijo2 = hijo1[0].childNodes
      var hijo3 = hijo2[2].childNodes
 
      if(this.banderaRevision === 1){
        for (let i=0;i<hijo3.length;i++){
          switch(hijo3[i].lastChild.firstChild.firstChild.firstChild.innerHTML){
           case 'Revision' : {
              hijo3[i].classList.add('ocultar') ;
    
           }     
           break;
          }
        }
       this.banderaRevision = 0
      }
      else{
        for (let i=0;i<hijo3.length;i++){
          switch(hijo3[i].lastChild.firstChild.firstChild.firstChild.innerHTML){
           case 'Revision' : hijo3[i].classList.remove('ocultar');
    
           break;
          }
          this.banderaRevision = 1
        }
      }
    
      this.funcionColorTabla()
    
      
    },
  
    eliminarColor(){
      var table = document.querySelector('.v-data-table__wrapper')
      var hijo1 = table.childNodes
      var hijo2 = hijo1[0].childNodes
      var hijo3 = hijo2[2].childNodes
      for (let i=0;i<hijo3.length;i++){
        hijo3[i].classList.remove('id1','id2','id3','id4','id5','id6','id7','id8','id9')
      }
    },
    repetidos(dni,email,telefono){

        this.desserts2.splice(0,this.desserts2.length)

        for(let i=0;i<this.datos.length;i++){

          if(this.datos[i].dni===dni || this.datos[i].email === email || this.datos[i].telefono === telefono){
            this.desserts2.push(this.datos[i])
          }
        }
    },
    

    async cargarAxios(){
      let resp = await axios.get('http://192.168.0.7:8080/Presentacion/SolicitudesWebServlet')
      this.datos = await resp.data
      // console.table(resp.data)
      this.cargarTabla()
    },

     async cargarTabla(){   
     const data = this.datos   
    //  console.log(this.datos[0]) 
     for(let i=0 ; i<data.length ; i++){

      if(!this.canales.includes(data[i].canal.toString()) && data[i].canal.toString() != 'null' && data[i].canal.toString() != 'false' ){
        this.canales.push(data[i].canal)
      }

      switch(data[i].aceptada){
        case "N" : data[i].aceptada = "Pendiente";break;
        case "A" : data[i].aceptada = "Evaluacion"; break;
        case "R" : data[i].aceptada = "Rechazada"; break;
        case "E" : data[i].aceptada = "Revision"; break;
        case "D" : data[i].aceptada = "Aprobada"; break;
        case "P" : data[i].aceptada = "Pendiente DNI"; break;
        case "L" : data[i].aceptada = "Pendiente carga"; break;
        case "T" : data[i].aceptada = "Contactar"; break;
        case "S" : data[i].aceptada = "Sin contacto"; break;
      }
      this.desserts.push(data[i])
   } 

  },   

  filtrarSituacion(max_situacion_bcra){


    this.eliminarFiltroSituacion()

    var table = document.querySelector('.v-data-table__wrapper')
      var hijo1 = table.childNodes
      var hijo2 = hijo1[0].childNodes
      var hijo3 = hijo2[2].childNodes

      for (let i=0;i<hijo3.length;i++){
        var fila = hijo3[i].childNodes

        if(fila[7].innerHTML.trim() != max_situacion_bcra){

          hijo3[i].classList.add('ocultarSituacion')
   
         }
        
      }
    
  },

  filtrarRepetida(repetidas2){

    this.eliminarFiltroRepetida()

    var table = document.querySelector('.v-data-table__wrapper')

    var hijo1 = table.childNodes
    var hijo2 = hijo1[0].childNodes
    var hijo3 = hijo2[2].childNodes

      for (let i=0;i<hijo3.length;i++){
        var fila = hijo3[i].childNodes
        var fila2 = fila[8].childNodes
        var fila3 = fila2[0].childNodes
        var fila4 = fila3[0].childNodes


        if(fila4[0].innerHTML.trim() != repetidas2){

          hijo3[i].classList.add('ocultarRepetida')
   
         }
        
      } 
  },

  
  filtrarCanal(canalPrueba){


    this.eliminarFiltroCanal()

    var table = document.querySelector('.v-data-table__wrapper')
      var hijo1 = table.childNodes
      var hijo2 = hijo1[0].childNodes
      var hijo3 = hijo2[2].childNodes

      for (let i=0;i<hijo3.length;i++){
        var fila = hijo3[i].childNodes

        if(fila[9].innerHTML.trim() != canalPrueba){

          hijo3[i].classList.add('ocultarCanal')
   
         }
        
      }
    
  },
  
  filtrarEstado(estado3){

    this.eliminarFiltroEstado()

    var table = document.querySelector('.v-data-table__wrapper')

    var hijo1 = table.childNodes
    var hijo2 = hijo1[0].childNodes
    var hijo3 = hijo2[2].childNodes

      for (let i=0;i<hijo3.length;i++){
        var fila = hijo3[i].childNodes
        var fila2 = fila[11].childNodes
        var fila3 = fila2[0].childNodes
        var fila4 = fila3[0].childNodes


        if(fila4[0].innerHTML.trim() != estado3){

          hijo3[i].classList.add('ocultarEstado')
   
         }
        
      } 
  },

  eliminarFiltro(){

    var table = document.querySelector('.v-data-table__wrapper')
      var hijo1 = table.childNodes
      var hijo2 = hijo1[0].childNodes
      var hijo3 = hijo2[2].childNodes

      for (let i=0;i<hijo3.length;i++){

          hijo3[i].classList.remove('ocultar')
  
      }

  },

  eliminarFiltroSituacion(){

    var table = document.querySelector('.v-data-table__wrapper')
      var hijo1 = table.childNodes
      var hijo2 = hijo1[0].childNodes
      var hijo3 = hijo2[2].childNodes

      for (let i=0;i<hijo3.length;i++){

          hijo3[i].classList.remove('ocultarSituacion')
  
      }

  },

  eliminarFiltroRepetida(repetidas2){
    var table = document.querySelector('.v-data-table__wrapper')
      var hijo1 = table.childNodes
      var hijo2 = hijo1[0].childNodes
      var hijo3 = hijo2[2].childNodes

      for (let i=0;i<hijo3.length;i++){
        var fila  = hijo3[i].childNodes
        var fila2 = fila[8].childNodes
        var fila3 = fila2[0].childNodes
        var fila4 = fila3[0].childNodes


        if(fila4[0].innerHTML.trim() != repetidas2){

          hijo3[i].classList.remove('ocultarRepetida')
   
         }
        
      }

  },

  eliminarFiltroCanal(){

    var table = document.querySelector('.v-data-table__wrapper')
      var hijo1 = table.childNodes
      var hijo2 = hijo1[0].childNodes
      var hijo3 = hijo2[2].childNodes

      for (let i=0;i<hijo3.length;i++){

          hijo3[i].classList.remove('ocultarCanal')
  
      }

  },
  eliminarFiltroEstado(estado3){

    var table = document.querySelector('.v-data-table__wrapper')
      var hijo1 = table.childNodes
      var hijo2 = hijo1[0].childNodes
      var hijo3 = hijo2[2].childNodes

      for (let i=0;i<hijo3.length;i++){
        var fila  = hijo3[i].childNodes
        var fila2 = fila[11].childNodes
        var fila3 = fila2[0].childNodes
        var fila4 = fila3[0].childNodes


        if(fila4[0].innerHTML.trim() != estado3){

          hijo3[i].classList.remove('ocultarEstado')
   
         }
        
      }

  },
  }, 
})

