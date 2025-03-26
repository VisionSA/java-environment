<%@include file="/inc/tags.jsp" %>
<jsp:useBean id="ahora" class="java.util.Date" scope="request"/>

<f:view>
<html lang="es">
<head>
    <title><h:outputText value="#{SolicitudesWebBean.tituloLargo}" /></title>
    <s:script language="javascript">
    	function recargar() {
    		document.getElementById('generalForm').submit();
    	}
    	function popup(pagina,popW,popH) {
			var w = 0, h = 0;
		   	w = screen.width;
		   	h = screen.height;

			var leftPos = (w-popW)/2, topPos = (h-popH)/2;

		    popupWindow=open(pagina,'','resizable=no,scrollbars=yes,width='+popW+',height='+popH+',top='+topPos+',left='+leftPos);
		    if (popupWindow.opener == null){popupWindow.opener = self;}
		};
    </s:script>    
</head>

<jsp:include page="/inc/includes.jsp" />

<body class="hold-transition skin-blue sidebar-mini" onbeforeunload="ShowWait('generalForm');" onload="${SolicitudesWebBean.popupReport}">

<h:form id="mainMenu" style="display: none">
  <jsp:include page="/inc/navigation_test.jsp" />
  <x:commandLink id="cerrarSesion" action="#{LoginBean.salir}"/>
</h:form>

<jsp:include page="/inc/header.jsp" />

<!-- Content Header (Page header) -->
<section class="content-header">
  <h1>
    ${SolicitudesWebBean.tituloCorto}
    <small>${SolicitudesWebBean.tituloLargo}</small>
  </h1>
</section>

<section class="content">

<div class="box box-default">
<div class="box-header"><h3></h3></div>

<center>

<secure:check/>

<h:form id="generalForm">
    <x:panelLayout id="page" layout="#{globalOptions.pageLayout}"
            styleClass="pageLayout"
            headerClass="pageHeader"
            navigationClass="pageNavigation"
            bodyClass="pageBody"
            footerClass="pageFooter" >

        <f:facet name="body">
            <h:panelGroup id="body">
            	
			<h:panelGrid columns="1" align="center">
			<%-- INCLUDE PARA LOS ERRORES --%> 	
				<h:panelGroup id="errores">
					<jsp:include page="/inc/error.jsp" />
				</h:panelGroup>

				<s:layoutingTitlePane id="filtroProveedores" label="Solicitudes de tarjeta" 
								      containerNodeClass="contentTitlePane" labelNodeClass="labelTitlePane" >
					
					<f:verbatim><br/></f:verbatim>

						
					<h:panelGrid id="panelTipoArchivo2" columns="1" width="250"
								align="center">
							<s:fieldset legend="Descargar excel">
							<f:verbatim>&nbsp;</f:verbatim>
							
								
								<h:panelGroup>
									<h:commandButton action="#{SolicitudesWebBean.generarXls}" 
											 value="xls" styleClass="btn btn-primary btn-flat" style="width: 100px"/>
								</h:panelGroup>
								
							</s:fieldset>
					</h:panelGrid>			
					

				</s:layoutingTitlePane>
			        
			</h:panelGrid>      		
      		</h:panelGroup>
        </f:facet>
    </x:panelLayout>


	

</h:form>        
</center>

<div class="box-header"></div>

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

</script>    
<%@include file="/inc/scripts.jsp" %>

<script type="text/javascript">

document.getElementById("sideMenu").innerHTML = `<li class="header">MENU</li>`;

for(var i = 1; i < mainMenu__idJsp2_menu.length-1; i++) {
    var obj = mainMenu__idJsp2_menu[i];
    document.getElementById("sideMenu").innerHTML += generateMenu(obj,"mainMenu__idJsp2_menu:A]\#{SolicitudesWebBean.inicializar}") + `</li>`;
}

</script>

<%@include file="/inc/scripts.jsp" %>    


<script type="text/javascript">
$(function () {

    //Inicializo datepicker
    $("#fDesde").datepicker({
      autoclose: true,
      orientation: "buttom"
    });

    $("#fHasta").datepicker({
      autoclose: true,
      orientation: "buttom"
    });
    

    //Muevo fechas de datepicker a input q pasa datos al bean
    var fd = document.getElementById("generalForm:FechaDesde").value.split(" ")[0].split("/");
	var year = fd[2];
	var month = fd[1];
	month--;
	var date = fd[0];
	var fAux = new Date(year,month,date);
	$("#fDesde").datepicker("setDate", fAux);
    document.getElementById("generalForm:FechaDesde").value = $.datepicker.formatDate('dd/mm/yy', fAux) + " 03:00";
	
	fd = document.getElementById("generalForm:FechaHasta").value.split(" ")[0].split("/");
	year = fd[2];
	month = fd[1];
	month--;
	date = fd[0];
	fAux = new Date(year,month,date);
	$("#fHasta").datepicker("setDate", fAux);
	document.getElementById("generalForm:FechaHasta").value = $.datepicker.formatDate('dd/mm/yy', fAux) + " 03:00";
	
	
	
    //Muevo fechas de datepicker a input q pasa datos al bean
    $("#fDesde").change(function() {
        var fd = $(this).datepicker("getDate");
        document.getElementById("generalForm:FechaDesde").value = $.datepicker.formatDate('dd/mm/yy', fd) + " 03:00";
    });

    $("#fHasta").change(function() {
        var fh = $(this).datepicker("getDate");
		document.getElementById("generalForm:FechaHasta").value = $.datepicker.formatDate('dd/mm/yy', fh) + " 03:00";
    });
    


  });
</script>

</body>
</html>
</f:view>
