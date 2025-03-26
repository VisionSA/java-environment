<%@include file="/inc/tags.jsp" %>
<jsp:useBean id="ahora" class="java.util.Date" scope="request"/>

<f:view>
<html lang="es">
<head>
    <title><h:outputText value="Tarjeta Fiel - Reporte (Fondos Saldos)"/></title>
    <s:script language="javascript">
    	function recargar() {
    		document.getElementById('fondosSaldosForm').submit();
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
</head>

<jsp:include page="/inc/includes.jsp" />

<body class="hold-transition skin-blue sidebar-mini"  onbeforeunload="ShowWait('fondosSaldosForm');" onload="${FondosSaldosBean.popupReport}">

<h:form id="mainMenu" style="display: none">
  <jsp:include page="/inc/navigation_test.jsp" />
  <x:commandLink id="cerrarSesion" action="#{LoginBean.salir}"/>
</h:form>

<jsp:include page="/inc/header.jsp" />

<!-- Content Header (Page header) -->
<section class="content-header">
  <h1>
    Fondos
    <small>Reportes</small>
  </h1>
</section>

<section class="content">

<div class="box box-default">
<div class="box-header with-border">
	<h3 class="box-title">Saldos Fondos</h3>
</div>
<br/>

<center>

<secure:check/>

<h:form id="fondosSaldosForm">
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

				<s:layoutingTitlePane id="filtroSumasYSaldos"
								      containerNodeClass="contentTitlePane" labelNodeClass="labelTitlePane" >
					<h:panelGrid id="filtroUno" columns="1" align="center">
						<h:panelGroup>
                            <h:panelGrid columns="7">
                                   <h:outputText value="Desde cuenta:" styleClass="texto"/>
                                   <f:verbatim>&nbsp;&nbsp;</f:verbatim>
                                   <h:inputText value="#{FondosSaldosBean.cuentaDesde}" />
                                   <f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
                                   <h:outputText value="Hasta cuenta:" styleClass="texto"/>
                                   <f:verbatim>&nbsp;&nbsp;</f:verbatim>
                                   <h:inputText value="#{FondosSaldosBean.cuentaHasta}" />
                             </h:panelGrid>

                             <f:verbatim>&nbsp;</f:verbatim>
                             <f:verbatim>&nbsp;</f:verbatim>

                             <h:panelGrid columns="3">
							<h:outputText value="Saldos hasta fecha:" styleClass="texto"/>
							<f:verbatim>&nbsp;&nbsp;</f:verbatim>
							<f:verbatim>
							        <div class="input-group date">
							            <div class="input-group-addon">
							                <i class="fa fa-calendar"></i>
							            </div>
							            <input type="text" class="form-control pull-right" id="fHasta">
							        </div>
							</f:verbatim>
							</h:panelGrid>
                            
		        		    <f:verbatim>&nbsp;</f:verbatim>

                            <s:fieldset legend="Tipos de Cuenta"> 
	                            <h:selectOneRadio value="#{FondosSaldosBean.tipoCuentas}" id="selectOneRadio1" styleClass="radioB">
												<f:selectItem itemValue="S" itemLabel="Solo cuentas de fondos " id="SFondos"/>
												<f:selectItem itemValue="N" itemLabel="Exceptuando cuentas de fondos" id="NFondos"/>
	                                            <f:selectItem itemValue="T" itemLabel="Todas las Cuentas" id="TodasCuentas"/>
								</h:selectOneRadio>
                            </s:fieldset>
							
							<f:verbatim>&nbsp;</f:verbatim>
                            
                            <s:fieldset legend="Tipos de Saldo">
								<h:selectOneRadio value="#{FondosSaldosBean.tipoSaldo}" id="selectOneRadio2" styleClass="radioB">
												<f:selectItem itemValue="0" itemLabel="Solo cuentas con saldo" id="SoloConSaldo"/>
												<f:selectItem itemValue="-1" itemLabel="Todas las cuentas" id="Todas"/>
								</h:selectOneRadio>
                            </s:fieldset>
                            
                            <f:verbatim>&nbsp;</f:verbatim>
                            
                            <h:panelGrid id="botonFiltros" columns="8"> 
								<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
						      	<x:commandLink id="exportarPdflLink"  action="#{FondosSaldosBean.generar}">
								  <x:graphicImage value="/img/icon/pdf_45x45.png" title="Generar reporte en formato PDF." border="0"/>				
								</x:commandLink>
							    
							    <f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
				 				<x:commandLink id="exportarExcelLink"  action="#{FondosSaldosBean.generarAExcel}">
									<x:graphicImage value="/img/icon/excel_45x45.png" title="Generar reporte en formato Excel." border="0"/>								
								 </x:commandLink>
			      			</h:panelGrid>
                    	</h:panelGroup>
					</h:panelGrid>
				</s:layoutingTitlePane>
			        
			</h:panelGrid>     		
      		</h:panelGroup>
        </f:facet>


    </x:panelLayout>

    <h:inputText id="FechaHasta" value="#{FondosSaldosBean.fechaHasta}" style="display: none;">
        <f:convertDateTime type="both" pattern = "dd/MM/yyyy HH:mm"/>
    </h:inputText>

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
    document.getElementById("sideMenu").innerHTML += generateMenu(obj,"mainMenu__idJsp2_menu:A]\#{FondosSaldosBean.inicializar}") + `</li>`;
}

</script>   

<%@include file="/inc/scripts.jsp" %>

<script type="text/javascript">
$(function () {

    //Inicializo datepicker
    $("#fHasta").datepicker({
      autoclose: true,
    });


	//Seteo fehcas que trae el bean por defecto, en el datepicker
	var fd = document.getElementById("fondosSaldosForm:FechaHasta").value.split("/");
	var year = fd[2];
	var month = fd[1];
	var date = fd[0];
	$("#fHasta").datepicker("setDate", new Date(year+"-"+month+"-"+date));
	
	//Seteo fehcas que trae el bean por defecto, en el datepicker
	var fd = document.getElementById("fondosSaldosForm:FechaHasta").value.split(" ")[0].split("/");
	var year = fd[2];
	var month = fd[1];
	month--;
	var date = fd[0];
	var fAux = new Date(year,month,date);
	$("#fHasta").datepicker("setDate", fAux);
    document.getElementById("fondosSaldosForm:FechaHasta").value = $.datepicker.formatDate('dd/mm/yy', fAux) + " 03:00";
    

    //Muevo fechas de datepicker a input q pasa datos al bean
    $("#fHasta").change(function() {
        var fh = $(this).datepicker("getDate");
		document.getElementById("fondosSaldosForm:FechaHasta").value = $.datepicker.formatDate('dd/mm/yy', fh) + " 03:00";
    });


  });
</script>


</body>
</html>
</f:view>
