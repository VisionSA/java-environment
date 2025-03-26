<%@include file="/inc/tags.jsp" %>
<jsp:useBean id="ahora" class="java.util.Date" scope="request"/>

<f:view>
<html lang="es">
<head>
   	<title><h:outputText value="#{EscritorioBean.tituloLargo}"/></title>
   	<meta http-equiv="Content-Type" content="text/html" charset=utf-8" />
    <s:script language="javascript">
    	function recargar() {
    		document.getElementById('escritorioForm').submit();
    	};
    	function popup(pagina,popW,popH) {
			var w = 0, h = 0;
		   	w = screen.width;
		   	h = screen.height;

			var leftPos = (w-popW)/2, topPos = (h-popH)/2;

		    popupWindow=open(pagina,'','resizable=no,scrollbars=yes,width='+popW+',height='+popH+',top='+topPos+',left='+leftPos);
		    if (popupWindow.opener == null){popupWindow.opener = self;}
		};
    </s:script>

<jsp:include page="/inc/includes.jsp" />


<body class="hold-transition skin-blue sidebar-mini" onbeforeunload="ShowWait('escritorioForm');" onload="if('${EscritorioBean.popup.mostrar}'=='true') {viewDialog.show();} else {window.scrollTo(0,${ScrollBean.hiddenScrollY});}">


<h:form id="mainMenu" style="display: none">
	<jsp:include page="/inc/navigation_test.jsp" />
	<x:commandLink id="cerrarSesion" action="#{LoginBean.salir}"/>
</h:form>

<jsp:include page="/inc/header.jsp" />


<section class="content">
	<div class="box box-default">


        <div class="box-header with-border">
			<h1 class="box-title">Consulta BCRA</h1>
		</div>
					
	<div class="box-body">	
		<!-- PANEL BCRA -->
		<div class="col-md-4">
			              
		<div class="box box-primary">
			 <div class="box-header with-border">
			     <h3 class="box-title">Cliente</h3>
			 </div>
			
			
			 <h:form id="formBCRA">
                  <div class="box-body">

					 <div class="col-md-12 col-xs-12">
					 	<label>DNI/CUIL</label>
					 </div>
					 
					 
	                 <div class="col-md-12 col-xs-12">
	                    <input id="inputDniBCRA" type="text" onkeyup="if (this.value.length > '8'){ ocultarElemento('radioOpts')}; if (this.value.length <= '8') { mostrarElemento('radioOpts')}" class="form-control"  name="inputDniBCRA" style="min-width: 60px; margin-bottom:10px" maxlength="11" autocomplete="off" required pattern="[0-9]{1,}" oninvalid="setCustomValidity('Complete campo: Hasta 11 dígitos numéricos.')" onchange="try{setCustomValidity('')}catch(e){}" onclick="limpiar()"/>
	                    <x:inputHidden value="#{ConsultaBCRABean.input}" id="input"/>
	                 </div>

                     <div class="col-md-12 col-xs-12" id="radioOpts">
		                  <div class="radio">
		                    <label>
		                      <input onclick="document.getElementById('formBCRA:radioInput').value  = 'M'" type="radio" name="optRadio" id="optionsRadios1" value="M" checked>
		                      Hombre
		                    </label>
		                  </div>
		                  <div class="radio">
		                    <label>
		                      <input onclick="document.getElementById('formBCRA:radioInput').value  = 'F'" type="radio" name="optRadio" id="optionsRadios2" value="F">
		                      Mujer
		                    </label>
		                  </div>
	                </div>
	                <x:inputHidden value="#{ConsultaBCRABean.radioInput}" id="radioInput"/>
	                
	                 <div class="col-md-12 col-xs-12">
	                 	<h:commandButton id="btnBCRA" action="#{ConsultaBCRABean.consultaBCRA}" onclick="consulta()" styleClass="btn btn-primary pull-right"  value="Consultar"/>
	                 </div>

                  </div>
   			 </h:form>

                  <div id="datosClienteBCRA" class="box-footer" style="display: none;">
	                  <!--  
	                  <div class="col-md-6">
	                  	<div class="text-light-blue" style="font-size: 16px">Cliente: </div> 
	                    <p id="txtNroCuenta">${inicioForm.nombreTitular}</p>
	                  </div>
	                  -->
	                  <div class="col-md-12">
	                  	<div class="text-light-blue" style="font-size: 16px">Cuil: </div> 
	                    <p id="txtNroCuenta">${ConsultaBCRABean.cuil}</p>
	                  </div>
                  </div>

              

         </div>

         </div>
         <!-- FIN PANEL CLIENTE -->
		
		
		<div class="col-md-8" style="display: none;" id="panelBCRA">
		  <div class="box box-primary">
            <div class="box-header with-border">
              <h3 class="box-title">Consulta</h3>
            </div>
            <!-- /.box-header -->
            <div class="box-body no-padding">
              <table class="table table-bordered table-striped">
                <tr>
                  <th>Entidad</th>
                  <th>Situacion</th>
                  <th>Periodo</th>
                  <th>Monto</th>
                </tr>
                <c:forEach var="clienteBCRA" items="${ConsultaBCRABean.clienteBCRA}">
		            <tr>
		                <td>${clienteBCRA.entidad}</td>
		                <td>${clienteBCRA.situacion}</td>
		                <td>${clienteBCRA.periodo}</td>
		                <td>${clienteBCRA.monto}</td>
		            </tr>       
		        </c:forEach>
              </table>
            </div>
           </div>
            <!-- /.box-body -->
        </div>
          <!-- /.box -->
	</div>
		
		    
<div style="display: none;">
 	<input type="text" id=msjError value="${ConsultaBCRABean.msjError}"/>
</div>

</div>

</section> 

</div>
<!-- ./Main content -->

<%@include file="/inc/footer.jsp" %>


<script type="text/javascript">

document.getElementById("sideMenu").innerHTML = `<li class="header">MENU</li>`;

for(var i = 1; i < mainMenu__idJsp2_menu.length-1; i++) {
    var obj = mainMenu__idJsp2_menu[i];
    document.getElementById("sideMenu").innerHTML += generateMenu(obj) + `</li>`;
}


document.getElementById("inputDniBCRA").focus();
document.getElementById("formBCRA:radioInput").value  = 'M';

var panelClienteBool = ${ConsultaBCRABean.panelCliente};
if (panelClienteBool) {
    panelBCRA.style.display = "block";
    datosClienteBCRA.style.display = "block";
}


function limpiar(){
	panelBCRA.style.display = "none";
	datosClienteBCRA.style.display = "none";
	document.getElementById("inputDniBCRA").value = "";
	document.getElementById("inputDniBCRA").focus();
}

function consulta(){
	document.getElementById('formBCRA:input').value = document.getElementById('inputDniBCRA').value;
}


var error = ${ConsultaBCRABean.error};
if(error){
  var msjError = document.getElementById("msjError").value;
  alert(msjError);
}


function ocultarElemento(elemento){
	var elem = document.getElementById(elemento);
	elem.style.display = "none";
}

function mostrarElemento(elemento){
	var elem = document.getElementById(elemento);
	elem.style.display = "block";
}
	

</script>

<%@include file="/inc/scripts.jsp" %>

</body>
</html>
</f:view>
