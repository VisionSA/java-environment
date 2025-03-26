new Vue({
  el: '#app',
  vuetify: new Vuetify(),
  data: () => ({
    return: {
      datos:null,  
      
    },
    toggle_exclusive: undefined,
    dialog: false,
    dialogDelete: false,
    dialogGuardar: false,
    periodoGuardado:'',
    search: '',
    periodos:[],
    estadosPeriodos:[], 
    periodoActual: 0,
    estadoActual:'',
    headers: [
      {
        align: 'start',
        sortable: false,
        value: 'name',
      },
      { text: 'Letra', value: 'letra' },
      { text: 'Alicuota ', value: 'alicuota'},
      { text: 'Acciones', value: 'actions', sortable: false },
    ],
    desserts: [],
    editedIndex: -1,
    editedItem: {
   
      Letra: [],
      Alicuota: 0,
      Periodo: 0,
      Estado: 0,
    },
    defaultItem: {
     
      Letra: [],
      Alicuota: 0,
      Periodo: 0,
      Estado: 0,
    },
  }),

  computed: {
    formTitle () {
      return this.editedIndex === -1 ? 'Nuevo Item' : 'Edit Item'
    },
  },

  watch: {
    dialog (val) {
      val || this.close()
    },
    dialogDelete (val) {
      val || this.closeDelete()
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

    editItem (item) {
      this.editedIndex = this.desserts.indexOf(item)
      this.editedItem = Object.assign({}, item)
      this.dialog = true
    },

    deleteItem (item) {
      this.editedIndex = this.desserts.indexOf(item)
      this.editedItem = Object.assign({}, item)
      this.dialogDelete = true
    },

    deleteItemConfirm () {
      this.desserts.splice(this.editedIndex, 1)
      this.closeDelete()
    },

    close () {
      this.dialog = false
      this.$nextTick(() => {
        this.editedItem = Object.assign({}, this.defaultItem)
        this.editedIndex = -1
      })
    },

    closeDelete () {
      this.dialogDelete = false
      this.$nextTick(() => {
        this.editedItem = Object.assign({}, this.defaultItem)
        this.editedIndex = -1
      })
    },
    save () {
      if (this.editedIndex > -1) {
        Object.assign(this.desserts[this.editedIndex], this.editedItem)
      } else {
        this.desserts.push(this.editedItem)
      }
      this.close()
    },
 async cargarApi(){
      try {
        //console.log(this.datos.data)
        const data=await fetch('http://192.168.0.7:8080/Presentacion/SirtacAlicuotaServlet')
        const array = await data.json()
        //this.datos = array
        return array
        
      }catch (error){

      }
    },
    async cargarAxios(){
      let resp = await axios.get('http://192.168.0.7:8080/Presentacion/SirtacAlicuotaServlet')
      this.datos = await resp.data
      this.cargarTabla()
    },

     async cargarTabla(){   

     const data = this.datos   

     for(let i=0;i<data.length;i++){

        this.desserts.push(data[i])

        //Agrego todos los periodos en un array
        if(this.periodos.indexOf(data[i].periodo) === -1){

          this.periodos.push(data[i].periodo);

          this.estadosPeriodos.push({"periodo": data[i].periodo,
          "estado" : data[i].estado } )
        }
    
     }
     this.periodos.sort()
     //Agrego el maximo periodo para mostrar
     this.periodoActual = Math.max(...this.periodos)

     var estadoAc = this.estadosPeriodos.find(estadoA => estadoA.periodo === this.periodoActual.toString());

     this.estadoActual = estadoAc.estado

 for(let j=0;j<this.desserts.length;j++){

      if(this.periodoActual.toString() != this.desserts[j].periodo.toString()){
       this.desserts.splice( [j] , 1 );
       j--;

      }
      
     }

    },
    guardarDatos(){
      this.dialogGuardar = false
      
      let datosNuevos = this.desserts
      for(let i=0;i<datosNuevos.length;i++){

        datosNuevos[i].periodo = this.periodoGuardado
    
      }
      let datosJSON = JSON.stringify(datosNuevos) 
      console.log(datosJSON)
    console.log(datosJSON)
      axios.post('http://192.168.0.7:8080/Presentacion/SirtacAlicuotaServlet',datosJSON)
      .then((response) => {
        console.log(response);
      }, (error) => {
        console.log(error);
      });
      location.reload();
     },

  periodoAnterior(){   
  const posicion = this.periodos.indexOf(this.periodoActual.toString())
  this.periodoActual = this.periodos[posicion-1]

  if(this.periodoActual!=undefined) { 

  var estadoAc = this.estadosPeriodos.find(estadoA => estadoA.periodo === this.periodoActual.toString());
  this.estadoActual = estadoAc.estado

  for(let j=0;j<this.desserts.length;j++){
    
    if(this.periodoActual.toString() != this.desserts[j].periodo.toString()){
     
      this.desserts.splice( [j] , 1 )
      j--;
    }
    console.log(this.periodoActual.toString())
   }

 for(let i=0;i<this.datos.length;i++){
    if(this.periodoActual.toString() === this.datos[i].periodo.toString()){
      this.desserts.push(this.datos[i] );
    }
    
   }
   console.log(this.datos)

}
else{
  alertify.alert('ALERTA','No hay mas periodos'); 
  this.periodoActual =this.periodos[posicion]
}

      },
  periodoSiguiente(){  
   const posicion = this.periodos.indexOf(this.periodoActual.toString())
   this.periodoActual = this.periodos[posicion+1]
   
   if(this.periodoActual!=undefined) {

    var estadoAc = this.estadosPeriodos.find(estadoA => estadoA.periodo === this.periodoActual.toString());
    this.estadoActual = estadoAc.estado

    for(let j=0;j<this.desserts.length;j++){

      if(this.periodoActual.toString() != this.desserts[j].periodo.toString()){
        this.desserts.splice( [j] , 1 );
        j--;
      
      }
      console.log(this.periodoActual.toString())
     }
    
for(let i=0;i<this.datos.length;i++){

    if(this.periodoActual.toString() === this.datos[i].periodo.toString()){
      this.desserts.push(this.datos[i] );
      }
      console.log(this.periodoActual.toString())
     }

  }
  else{
    alertify.alert('ALERTA','No hay mas periodos');
    this.periodoActual =this.periodos[posicion]
  }
  
        },     
  },
  
})