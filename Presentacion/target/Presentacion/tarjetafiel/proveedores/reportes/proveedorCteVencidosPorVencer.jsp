<%@include file="/inc/tags.jsp" %>
<jsp:useBean id="ahora" class="java.util.Date" scope="request"/>

<f:view>
<html lang="es">
<head>
    <title><h:outputText value="Tarjeta Fiel - Reporte (Proveedores Cte Vencidos por vencer)"/></title>
    <s:script language="javascript">
    	function recargar() {
    		document.getElementById('generalForm').submit();
    	}
    </s:script>    
</head>

<jsp:include page="/inc/includes.jsp"/>

<body class="hold-transition skin-blue sidebar-mini" onbeforeunload="ShowWait('generalForm');" onload="${ReporteProveedorCtaCteBean.popupReport}">

<h:form id="mainMenu" style="display: none">
	<jsp:include page="/inc/navigation_test.jsp" />
	<x:commandLink id="cerrarSesion" action="#{LoginBean.salir}"/>
</h:form>

<jsp:include page="/inc/header.jsp" />

<!-- Content Header (Page header) -->
<section class="content-header">
  <h1>
    ${ReporteProveedorCtaCteBean.tituloCorto}
    <small>${ReporteProveedorCtaCteBean.tituloLargo}</small>
  </h1>
</section>

<section class="content">
	<div class="box box-default">
		<div class="box-header"><h3></h3>
		</div>

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

				<s:layoutingTitlePane id="filtroProveedores" label=" Filtro Reporte" 
								      containerNodeClass="contentTitlePane" labelNodeClass="labelTitlePane" >
					<f:verbatim><br></f:verbatim>
					<h:panelGrid id="filtroUno" columns="3" align="center" width="500">
						<h:panelGroup>
							<h:outputText value="Id Proveedor Desde:" styleClass="texto"/>
							<h:inputText id="idDesde" value="#{ReporteProveedorCtaCteBean.idDesde}" 
				               			 styleClass="bordeceldainferior" maxlength="5" size="5" 
				               			 style="width: 90px;margin-left:10px;" onfocus="encender(this);" onblur="apagar(this);"/>
						</h:panelGroup>
						<f:verbatim>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</f:verbatim>
						<h:panelGroup>
							<h:outputText value="Id Proveedor Hasta:" styleClass="texto"/>
							<h:inputText id="idHasta" value="#{ReporteProveedorCtaCteBean.idHasta}" 
				               			 styleClass="bordeceldainferior" maxlength="5" size="5" 
				               			 style="width: 90px;margin-left:10px;" onfocus="encender(this);" onblur="apagar(this);"/>
						</h:panelGroup>						
												
					</h:panelGrid>
					
					<f:verbatim><hr align="center" width="600"></f:verbatim>
					<h:panelGrid id="panelbtn" columns="1" width="500" align="center">
					<h:panelGroup>
							<h:commandButton id="verInformeEnExcel" actionListener="#{ReporteProveedorCtaCteBean.generarCtesVencidosPorVencerAExcel}"
											 styleClass="btn btn-primary btn-flat pull-right" value="Exportar a Excel" style="margin-left:10px;"/>
							
							<h:commandButton id="verInforme" actionListener="#{ReporteProveedorCtaCteBean.generarCtesVencidosPorVencer}" 
											 value="Ver Reporte PDF" styleClass="btn btn-primary btn-flat pull-right" style="margin-left:10px;"/>
					</h:panelGroup>			
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
    document.getElementById("sideMenu").innerHTML += generateMenu(obj,"mainMenu__idJsp2_menu:A]\#{ReporteProveedorCtaCteBean.cteVencidosPorVencer}") + `</li>`;
}

</script>

<%@include file="/inc/scripts.jsp" %>    
</body>
</html>
</f:view>
