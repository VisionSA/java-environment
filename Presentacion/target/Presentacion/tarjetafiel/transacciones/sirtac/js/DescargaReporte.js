
var fechaDesdeIng;
var fechaHastaIng;
    function descargarTxt() {
        
        fechaDesdeIng = document.getElementById("fechaDesde").value;
        fechaHastaIng = document.getElementById("fechaHasta").value;
                
        if(fechaDesdeIng!= false && fechaHastaIng!= false)
        {            
        var array = fechaDesdeIng.split('-')
        var fechaDesdeCorrecta = array[2] + '/'+  array[1] + '/'+ array[0];
        var array = fechaHastaIng.split('-')
        var fechaHastaCorrecta = array[2] + '/'+  array[1] + '/'+ array[0];

        var req = new XMLHttpRequest();
        req.open("POST", "http://192.168.0.7:8080/Presentacion/SirtacReporteServlet");
        req.send(
            JSON.stringify({
            fechaDesde: fechaDesdeCorrecta,
            fechaHasta: fechaHastaCorrecta
            })
        );        
        req.responseType = "blob";
        req.onload = function (event) {
            if (req.response) {
            var blob = req.response;
            var fileName = "ReporteSirtac.txt";
            var link = document.createElement("a");
            link.href = window.URL.createObjectURL(blob);
            link.download = fileName;
            link.click();
            }
        };
    }
        else{
            alert("error, inserte fecha correcta");
                    }
        }  



        

        
        