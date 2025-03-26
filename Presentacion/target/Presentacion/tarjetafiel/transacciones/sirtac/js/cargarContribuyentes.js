const btnCargar = document.querySelector('#btnCargar')

btnCargar.onclick = async function () {

    $("#Loading").addClass("loading");

    const response = await fetch('http://192.168.0.7:8080/Presentacion/SirtacPadronServlet');
 
    $("#Loading").removeClass("loading");

    if(response.status  === 200){

        alert('Cargado Correctamente');

    }
    else{
        alert('Hubo un error, consultar en sistemas');
    }

}



   
