new Vue({
  el: '#app',
  vuetify: new Vuetify(),
  data: () => ({
  Operador: [],
  Operadores:[],
  Mes:['Enero','Febrero','Marzo','Abril','Mayo','Junio',
  'Julio','Agosto','Septiembre','Octubre','Noviembre','Diciembre'],
  Anio:['2021','2022','2023','2024','2025','2026','2027','2028','2029','2030','2031','2032','2033','2034','2035'],
  datos:[],
  operadorActual:'',
  mesActual:'',
  anioActual:'',
  pagination: {},
  page: 1,
  pageCount: 0,
  itemsPerPage: 100 ,
  snack: false,
  snackColor: '',
  snackText: '',
  horas:'',
  entradas:'',
  salidas:'',
  desserts: [
  {
  dia        :'1' ,
  horaEntMan : '' ,
  horaSalMan : '' ,
  horaEntTar : '' ,
  horaSalTar : '' ,
  horas      : '' ,
  observacion: '' ,
  },
  {
  dia        :'2' ,
  horaEntMan : '' ,
  horaSalMan : '' ,
  horaEntTar : '' ,
  horaSalTar : '' ,
  horas      : '' ,
  observacion: '' ,
  },
  {
  dia        :'3' ,
  horaEntMan : '' ,
  horaSalMan : '' ,
  horaEntTar : '' ,
  horaSalTar : '' ,
  horas      : '' ,
  observacion: '' ,
  },
  {
  dia        :'4' ,
  horaEntMan : '' ,
  horaSalMan : '' ,
  horaEntTar : '' ,
  horaSalTar : '' ,
  horas      : '' ,
  observacion: '' ,
  },
  {
  dia        :'5' ,
  horaEntMan : '' ,
  horaSalMan : '' ,
  horaEntTar : '' ,
  horaSalTar : '' ,
  horas      : '' ,
  observacion: '' ,
  },
  {
  dia        :'6' ,
  horaEntMan : '' ,
  horaSalMan : '' ,
  horaEntTar : '' ,
  horaSalTar : '' ,
  horas      : '' ,
  observacion: '' ,
  },
  {
  dia        :'7' ,
  horaEntMan : '' ,
  horaSalMan : '' ,
  horaEntTar : '' ,
  horaSalTar : '' ,
  horas      : '' ,
  observacion: '' ,
  },
  {
  dia        :'8' ,
  horaEntMan : '' ,
  horaSalMan : '' ,
  horaEntTar : '' ,
  horaSalTar : '' ,
  horas      : '' ,
  observacion: '' ,
  },   
   {
  dia        :'9' ,
  horaEntMan : '' ,
  horaSalMan : '' ,
  horaEntTar : '' ,
  horaSalTar : '' ,
  horas      : '' ,
  observacion: '' ,
  },
  {
  dia        :'10',
  horaEntMan : '' ,
  horaSalMan : '' ,
  horaEntTar : '' ,
  horaSalTar : '' ,
  horas      : '' ,
  observacion: '' ,
  },
  {
  dia        :'11',
  horaEntMan : '' ,
  horaSalMan : '' ,
  horaEntTar : '' ,
  horaSalTar : '' ,
  horas      : '' ,
  observacion: '' ,
  },
  {
  dia        :'12',
  horaEntMan : '' ,
  horaSalMan : '' ,
  horaEntTar : '' ,
  horaSalTar : '' ,
  horas      : '' ,
  observacion: '' ,
  },
  {
  dia        :'13',
  horaEntMan : '' ,
  horaSalMan : '' ,
  horaEntTar : '' ,
  horaSalTar : '' ,
  horas      : '' ,
  observacion: '' ,
  },
  {
  dia        :'14',
  horaEntMan : '' ,
  horaSalMan : '' ,
  horaEntTar : '' ,
  horaSalTar : '' ,
  horas      : '' ,
  observacion: '' ,
    },
  {
  dia        :'15',
  horaEntMan : '' ,
  horaSalMan : '' ,
  horaEntTar : '' ,
  horaSalTar : '' ,
  horas      : '' ,
  observacion: '' ,
  },
  {
  dia        :'16',
  horaEntMan : '' ,
  horaSalMan : '' ,
  horaEntTar : '' ,
  horaSalTar : '' ,
  horas      : '' ,
  observacion: '' ,
  },
  {
  dia        :'17',
  horaEntMan : '' ,
  horaSalMan : '' ,
  horaEntTar : '' ,
  horaSalTar : '' ,
  horas      : '' ,
  observacion: '' ,
  },
  {
  dia        :'18',
  horaEntMan : '' ,
  horaSalMan : '' ,
  horaEntTar : '' ,
  horaSalTar : '' ,
  horas      : '' ,
  observacion: '' ,
  },
  {
  dia        :'19',
  horaEntMan : '' ,
  horaSalMan : '' ,
  horaEntTar : '' ,
  horaSalTar : '' ,
  horas      : '' ,
  observacion: '' ,
  },
  {
  dia        :'20',
  horaEntMan : '' ,
  horaSalMan : '' ,
  horaEntTar : '' ,
  horaSalTar : '' ,
  horas      : '' ,
  observacion: '' ,
  },
  {
  dia        :'21',
  horaEntMan : '' ,
  horaSalMan : '' ,
  horaEntTar : '' ,
  horaSalTar : '' ,
  horas      : '' ,
  observacion: '' ,
  },
  {
  dia        :'22',
  horaEntMan : '' ,
  horaSalMan : '' ,
  horaEntTar : '' ,
  horaSalTar : '' ,
  horas      : '' ,
  observacion: '' ,
  },
  {
  dia        :'23',
  horaEntMan : '' ,
  horaSalMan : '' ,
  horaEntTar : '' ,
  horaSalTar : '' ,
  horas      : '' ,
  observacion: '' ,
  },
  {
  dia        :'24',
  horaEntMan : '' ,
  horaSalMan : '' ,
  horaEntTar : '' ,
  horaSalTar : '' ,
  horas      : '' ,
  observacion: '' ,
  },
  {
  dia        :'25',
  horaEntMan : '' ,
  horaSalMan : '' ,
  horaEntTar : '' ,
  horaSalTar : '' ,
  horas      : '' ,
  observacion: '' ,
  },
  {
  dia        :'26',
  horaEntMan : '' ,
  horaSalMan : '' ,
  horaEntTar : '' ,
  horaSalTar : '' ,
  horas      : '' ,
  observacion: '' ,
  },
  {
  dia        :'27',
  horaEntMan : '' ,
  horaSalMan : '' ,
  horaEntTar : '' ,
  horaSalTar : '' ,
  horas      : '' ,
  observacion: '' ,
  },
  {
  dia        :'28',
  horaEntMan : '' ,
  horaSalMan : '' ,
  horaEntTar : '' ,
  horaSalTar : '' ,
  horas      : '' ,
  observacion: '' ,
  },
  {
  dia        :'29',
  horaEntMan : '' ,
  horaSalMan : '' ,
  horaEntTar : '' ,
  horaSalTar : '' ,
  horas      : '' ,
  observacion: '' ,
  },
  {
  dia        :'30',
  horaEntMan : '' ,
  horaSalMan : '' ,
  horaEntTar : '' ,
  horaSalTar : '' ,
  horas      : '' ,
  observacion: '' ,
  },
  {
  dia        :'31',
  horaEntMan : '' ,
  horaSalMan : '' ,
  horaEntTar : '' ,
  horaSalTar : '' ,
  horas      : '' ,
  observacion: '' ,
  },
  // {
  // horas      : '-' ,
  // horas      : '-' ,
  // horas      : '-' ,
  // horas      : '-' ,
  // horas      : '-' ,
  // horas      : ' ' ,
  // observacion: '-' ,
  // },
  ],
  
  headers: [
  { text: 'Dia'            , value: 'dia'        ,sortable:false },
  { text: 'Entrada'       , value: 'horario1'   ,sortable:false },
  { text: 'Salida'         , value: 'horario2'   ,sortable:false },
  { text: 'Entrada'        , value: 'horario3'   ,sortable:false },
  { text: 'Salida'         , value: 'horario4'   ,sortable:false },
  { text: 'Horas'          , value: 'horas'      ,sortable:false },
  { text: 'Observaciones'  , value: 'observacion',sortable:false },
  ],
  }),
  computed: {
  formTitle () {
  return this.showIndex === -1 ? 'Nuevo Item' : 'Show Item'
  },
  },
  watch: {
  dialog (val) {
  val || this.cerrar()
  },
  },
  created () {
    this.initialize()
  },

  methods:{
async buscar(){

  //LIMPIAMOS LA TABLA
for( dato of this.desserts){
  dato.horaEntMan =''
  dato.horaSalMan =''
  dato.horaEntTar =''
  dato.horaSalTar =''
  calculo         =''
  dato.observacion=''
}
  let operadorSeleccionado = document.getElementsByClassName('v-select__selection v-select__selection--comma')  
  let mes = document.getElementsByClassName('v-select__selection v-select__selection--comma')
  let mesTexto = mes[1].innerHTML

  switch(mesTexto){
    case 'Enero'      : this.mesActual = 01;break;
    case 'Febrero'    : this.mesActual = 02;break;
    case 'Marzo'      : this.mesActual = 03;break;
    case 'Abril'      : this.mesActual = 04;break;
    case 'Mayo'       : this.mesActual = 05;break;
    case 'Junio'      : this.mesActual = 06;break;
    case 'Julio'      : this.mesActual = 07;break;
    case 'Agosto'     : this.mesActual = 08;break;
    case 'Septiembre' : this.mesActual = 09;break;
    case 'Octubre'    : this.mesActual = 10;break;
    case 'Noviembre'  : this.mesActual = 11;break;
    case 'Diciembre'  : this.mesActual = 12;break;
  }

  let anio = document.getElementsByClassName('v-select__selection v-select__selection--comma')
  this.anioActual = anio[2].innerHTML
  
  for(operador of this.Operadores){
    if (operador.nombreCompleto === operadorSeleccionado[0].innerHTML){
      this.operadorActual = operador.codigo
    }
  }


  //obtener id de operador y mostrarlo por medio de idOperador
  
  // let observacionLimpio = this.observacion.replaceAll('ñ','n')
        
  // let observacionSinAcento =this.removeAccents(observacionLimpio)
  
  // let observacionTrim = observacionSinAcento.replace(/\s/g, ' ')
  
  let datosNuevos = {
  "tipo"       :"buscar"                       ,
  "idOperador" :this.operadorActual.toString() ,
  "dia"        :"0"                            ,
  "mes"        :this.mesActual.toString()      ,
  "anio"       :this.anioActual.toString()     ,
  "horaManEnt" :""                             ,
  "horaManSal" :""                             ,
  "horaTarEnt" :""                             ,
  "horaTarSal" :""                             ,
  "horas"      :""                             ,
  "observacion":""                             ,
  "id"         :"0"
  
  }

let datosJSON = JSON.stringify(datosNuevos) 
axios.post('http://192.168.0.7:8080/Presentacion/AsistenciaPersonalServlet',datosJSON)
.then((response) => {  

this.datos = response.data
for (dato of this.datos){
 
for(fila of this.desserts){

if(fila.dia === dato.dia){
if(dato.horaEntMan === 'null'||dato.horaEntMan === '-'||dato.horaEntMan === '0'){
fila.horaEntMan = '--:--'
}
else fila.horaEntMan  = dato.horaEntMan
  
if(dato.horaSalMan === 'null'||dato.horaSalMan === '-'||dato.horaSalMan === '0'){
  fila.horaSalMan = '--:--'
}
else fila.horaSalMan  = dato.horaSalMan
  
if(dato.horaEntTar === 'null'||dato.horaEntTar === '-'||dato.horaEntTar === '0'){
fila.horaEntTar = '--:--'
}
else fila.horaEntTar  = dato.horaEntTar
 
if(dato.horaSalTar === 'null'||dato.horaSalTar === '-'||dato.horaSalTar === '0'){
fila.horaSalTar = '--:--'
}
else  fila.horaSalTar  = dato.horaSalTar

if(dato.observacion === 'null'){
fila.observacion = '-'
}
else fila.observacion = dato.observacion
  
}
          
}
  
}
this.sumarHoras()
   
}, (error) => {
console.log(error);
}); 
},

sumarHoras(){
  
  var i=-1;

  var horaEntrada1;
  var horaEntrada2;
  var horaSalida1;
  var horaSalida2;

  for(horas of this.desserts ){
    i=i+1;
  
    if(horas.horaEntMan ==='--:--'){

      horaEntrada1 = new Date();
      horaEntrada1.setHours('00');
      horaEntrada1.setMinutes('00');

    }else{
      var entrada1hora = horas.horaEntMan.toString().substr(0,2) ;
      var entrada1minutos = horas.horaEntMan.toString().substr(3,5) ;
      horaEntrada1 = new Date();
      horaEntrada1.setHours(entrada1hora);
      horaEntrada1.setMinutes(entrada1minutos);
    }

    if(horas.horaSalMan ==='--:--' ){
      horaSalida1 = new Date();
      horaSalida1.setHours('00');
      horaSalida1.setMinutes('00');
    }else{
      var salida1hora = horas.horaSalMan.toString().substr(0,2) ;
      var salida1minutos = horas.horaSalMan.toString().substr(3,5) ;
      horaSalida1  = new Date();
      horaSalida1.setHours(salida1hora);
      horaSalida1.setMinutes(salida1minutos);
    }

    if(horas.horaEntTar ==='--:--' ){
      horaEntrada2 = new Date();
      horaEntrada2.setHours('00');
      horaEntrada2.setMinutes('00');
    }else{    
      var entrada2hora = horas.horaEntTar.toString().substr(0,2) ;
      var entrada2minutos = horas.horaEntTar.toString().substr(3,5) ;
      horaEntrada2 = new Date();
      horaEntrada2.setHours(entrada2hora);
      horaEntrada2.setMinutes(entrada2minutos);
    }

    if(horas.horaSalTar ==='--:--' ){
      horaSalida2 = new Date();
      horaSalida2.setHours('00');
      horaSalida2.setMinutes('00');
    }else{
      var salida2hora = horas.horaSalTar.toString().substr(0,2) ;
      var salida2minutos = horas.horaSalTar.toString().substr(3,5) ;
      horaSalida2 = new Date();
      horaSalida2.setHours(salida2hora);
      horaSalida2.setMinutes(salida2minutos);
    }

    let diferencia1 = horaSalida1.getTime() - horaEntrada1.getTime();
    let diferencia2 = horaSalida2.getTime() - horaEntrada2.getTime();

    let diferenciaTotal = diferencia1 + diferencia2

    let horasTrabajadas = this.msToTime(diferenciaTotal);

    if(horasTrabajadas.toString() != '00:00' ){
      
      if( this.cantidadRepetidas(horasTrabajadas.toString())) {
        horas.horas = horasTrabajadas;
      }
      else horas.horas = '--:--'  
    } 
    else horas.horas = '--:--'  

   }

},

cantidadRepetidas(horas){

  var cantidad=0;
 
  for(var i=0; i<horas.length ; i++){

    if(horas[i]==='-'){

      cantidad++;

    }

  }
 

  if(cantidad === 0){
    return true
  } else return false


},

msToTime(duration) {
    minutes = Math.floor((duration / (1000 * 60)) % 60),
    hours = Math.floor((duration / (1000 * 60 * 60)) % 24);

  hours = (hours < 10) ? "0" + hours : hours;
  minutes = (minutes < 10) ? "0" + minutes : minutes;

  return hours + ":" + minutes;
},


async initialize(){
  let resp = await axios.get('http://192.168.0.7:8080/Presentacion/AsistenciaPersonalServlet')
  let operadoresApi = await resp.data
  this.Operadores = operadoresApi
  
for ( dato of this.Operadores){
  
  this.Operador.push(dato.nombreCompleto)

}

},

save (dia,horaManEnt,horaManSal,horaTarEnt,horaTarSal,observacion) {


this.snack = true
this.snackColor = 'success'
this.snackText = 'Datos guardados'

let bandera = 0
let id=0

// ejemplo
if(this.operadorActual === '' || this.mesActual === '' || this.anioActual === ''){
alert('Ingrese operador correcto') //cambiar texto
}
else{

  for(dato of this.datos){

    if(dia === dato.dia){
      bandera = 1
      id = dato.id
    }
    
  }

  let observacionLimpio = observacion.replaceAll('ñ','n')
  let observacionSinAcento =this.removeAccents(observacionLimpio)
  let observacionTrim = observacionSinAcento.replace(/\s/g, ' ')

let datosNuevos

if(bandera === 1){
  //ACTUALIZAR
  datosNuevos = {
    "tipo"       : "actualizar"                  ,
    "idOperador" : "0"                           ,
    "dia"        : "0"                           ,
    "mes"        : "0"                           ,
    "anio"       : "0"                           ,
    "horaManEnt" : horaManEnt                    ,
    "horaManSal" : horaManSal                    ,
    "horaTarEnt" : horaTarEnt                    ,
    "horaTarSal" : horaTarSal                    ,
    "observacion": observacionTrim               ,
     "id"        : id
    }
}
else{
  //INSERTAR
  datosNuevos = {
    "tipo"       : "insertar"                    ,
    "idOperador" : this.operadorActual.toString(),
    "dia"        : dia                           ,
    "mes"        : this.mesActual.toString()     ,
    "anio"       : this.anioActual.toString()    ,
    "horaManEnt" : horaManEnt                    ,
    "horaManSal" : horaManSal                    ,
    "horaTarEnt" : horaTarEnt                    ,
    "horaTarSal" : horaTarSal                    ,
    "observacion": observacionTrim               ,
    "id"         : '0'
    }
}

let datosJSON = JSON.stringify(datosNuevos) 

axios.post('http://192.168.0.7:8080/Presentacion/AsistenciaPersonalServlet',datosJSON)
.then((response) => {

  if(bandera === 0){

    let diaAInsertar =  
      { anio: " ",
        dia: dia,
        horaEntMan: " ",
        horaEntTar: "",
        horaSalMan: " ",
        horaSalTar: " ",
        horas: " ",
        id: response.data.id,
        mes: " ",
        observacionTrim: " ",
        
  }
  this.datos.push(diaAInsertar)
  }

  

},(error) => {
console.log(error);
  
});
}

this.sumarHoras()

},
cancel () {
this.snack = true
this.snackColor = 'error'
this.snackText = 'Cancelado'
},
cerrar () {
this.dialog = false
this.$nextTick(() => {
this.showitem = Object.assign({}, this.defaultItem)
this.showIndex = -1
})
},
showItems (item) {
this.showIndex = this.desserts.indexOf(item)
this.showItem = Object.assign({}, item)
this.dialog = true
},
removeAccents(str) {     
return str.normalize("NFD").replace(/[\u0300-\u036f]/g, "");
} ,
cancel () {
this.snack = true
this.snackColor = 'error'
this.snackText = 'Cancelado'
},
cerrar () {
this.dialog = false
this.$nextTick(() => {
this.showitem = Object.assign({}, this.defaultItem)
this.showIndex = -1
})
},
showItems (item) {
this.showIndex = this.desserts.indexOf(item)
this.showItem = Object.assign({}, item)
this.dialog = true
},
removeAccents(str) { 
return str.normalize("NFD").replace(/[\u0300-\u036f]/g, "");
} ,
  
},
  
})