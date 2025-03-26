const url='http://192.168.0.79:8080/Presentacion/SirtacAlicuotaServlet'

fetch(url)
.then(response=> response.json() )
.then(data => {
const table = document.getElementById("alicuotas");
    
    data.forEach(e => {    //< ---  recorremos data
    
      let tr = document.createElement("tr"); //< ---  creamos una fila
    
      for (p in e) {  //< ---  recorremos cada propiedad de cada elemento
    
        let td = document.createElement("td"); //< ---  Hacemos columna dentro de la fila
        td.classList.add(p);//<-- le podemos agregar a toda la columna la misma clase
        td.innerHTML = e[p]; 
    
        tr.appendChild(td); //< --- Agregamos la columna en la fila
    
      }
    
      table.appendChild(tr); //< --- Agregamos la fila a la tabla
    
    });
})
.catch(err=>console.log(err))
